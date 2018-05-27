package twitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql_db.Insertar;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter_Listener extends Thread{
    
    private static final int MAX_QUERIES = 5;
    private static final int TWEETS_PER_QUERY = 100;
    private final long time;
    private final String keyword;
    private final String thread_name;
    private final double factor_influencia;
    private final String consumer_key;
    private final String consumer_secret;
    private final String access_token;
    private final String access_token_secret;
    private final String candidato_index;
    private boolean keep_thread_alive;
    private long seconds_until_reset;
    private int relevant_tweets;
    private int irrelevant_tweets;
    
    public Twitter_Listener(long _time, String _keyword, String _thread_name, String _candidato_index, double _factor_influencia, String _consumer_key, String _consumer_secret, String _access_token, String _access_token_secret) throws TwitterException
    {
        time = _time;
        keyword = _keyword;
        thread_name = _thread_name;
        this.candidato_index = _candidato_index;
        factor_influencia = _factor_influencia;
        consumer_key = _consumer_key;
        consumer_secret = _consumer_secret;
        access_token = _access_token;
        access_token_secret = _access_token_secret;
        keep_thread_alive = true;
        relevant_tweets = 0;
        irrelevant_tweets = 0;
    }
    
    @Override
    public void run() 
    {        
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey(consumer_key);
        configurationBuilder.setDebugEnabled(true).setOAuthConsumerSecret(consumer_secret);
        configurationBuilder.setDebugEnabled(true).setOAuthAccessToken(access_token);
        configurationBuilder.setDebugEnabled(true).setOAuthAccessTokenSecret(access_token_secret);
        configurationBuilder.setTweetModeExtended(true);
        
        long maxID = -1;
        int totalTweets = 0;
        
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter = twitterFactory.getInstance();
        
        Thread.currentThread().setName(thread_name);
        
        //OBTENER FECHA DE HACE UNA SEMANA
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date date_since = new Date(System.currentTimeMillis() - (10 * DAY_IN_MS));
        Date date_until = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        String string_since = new SimpleDateFormat("yyyy-MM-dd").format(date_since);
        String string_until = new SimpleDateFormat("yyyy-MM-dd").format(date_until);
        System.out.println(Thread.currentThread().getName() + ": Extrayendo tweets de " + string_since + " con el keyword " + keyword);
        
        //EMPIEZA EL TIMER POR N CANTIDAD DE TIEMPO (PARÁMETRO)
        long start_time = System.currentTimeMillis();
        long end_time = start_time + time;
        
        Map<String, RateLimitStatus> rateLimitStatus;
        Insertar insertar = new Insertar();
        
        try 
        {
            rateLimitStatus = twitter.getRateLimitStatus("search");  
            while (System.currentTimeMillis() < end_time && keep_thread_alive)
            {
                try
                {
                    RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
                    /*System.out.printf(Thread.currentThread().getName() + ": Llamadas restantes: %d de un total de %d, El límite se reinicia en %d segundos\n",
                                                      searchTweetsRateLimit.getRemaining(),
                                                      searchTweetsRateLimit.getLimit(),
                                                      searchTweetsRateLimit.getSecondsUntilReset());*/


                    for (int queryNumber=0;queryNumber < MAX_QUERIES; queryNumber++)
                    {
                        //System.out.printf(Thread.currentThread().getName() + ": Ciclo %d, Keyword %s", queryNumber, keyword);
                        if (searchTweetsRateLimit.getRemaining() == 0)
                        {
                            System.out.printf(Thread.currentThread().getName() + ": Se excedió el límite, esperando %d segundos", searchTweetsRateLimit.getSecondsUntilReset() + "\n");
                            Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset()+2) * 1000l);
                        }

                        //Query q = new Query(keywords[keyword_iterator]+ " +exclude:retweets");
                        Query q = new Query(keyword + " +exclude:retweets");
                        q.setCount(TWEETS_PER_QUERY);
                        q.setSince(string_since);
                        q.setUntil(string_until);
                        q.resultType(Query.RECENT);

                        if (maxID != -1)
                        {
                            q.setMaxId(maxID - 1);
                        }

                        QueryResult r = twitter.search(q);

                        if (r.getTweets().size() == 0)
                        {
                            keep_thread_alive = false;
                            break;
                        }

                        for (Status s: r.getTweets())			
                        {
                            totalTweets++;
                            if (maxID == -1 || s.getId() < maxID)
                            {
                                    maxID = s.getId();
                            }
                            
                            //EJECUTAR ANALIZADOR DE FAKE NEWS, CATEGORÍA Y EMOCIÓN EN HILOS INDEPENDIENTES
                            String tweet = cleanText(s.getText());
                            String[] tokens = Tokenize(tweet);
                            ExecutorService executor = Executors.newFixedThreadPool(25);
                            Future<String> fake_news = executor.submit(new Analyze_Fake_News(tokens));
                            Future<String> category = executor.submit(new Analyze_Category(tokens));
                            Future<String> emotion = executor.submit(new Analyze_Emotion(tokens));
                            
                            if(Verificar_Relevancia(s.getRetweetCount(), s.getFavoriteCount(), s.getUser().getFollowersCount())) //EN CASO QUE EL TWEET SEA RELEVANTE
                            {                                     
                                if(insertar.Insertar_Tweet_Relevante(String.valueOf(s.getId()), s.getUser().getScreenName(), tweet, s.getFavoriteCount(), s.getRetweetCount(), new SimpleDateFormat("yyyy-MM-dd").format(s.getCreatedAt()), candidato_index, fake_news.get(), category.get(), emotion.get()))
                                {
                                    relevant_tweets++;
                                    System.out.printf(Thread.currentThread().getName() + ":Relevante.  Fecha: %s, @%-20s RT: @%-5s Likes: @%-5s Tweet: %s\n",
                                                              s.getCreatedAt().toString(),
                                                              s.getUser().getScreenName(),
                                                              s.getRetweetCount(),
                                                              s.getFavoriteCount(),                                                               
                                                              cleanText(s.getText()));
                                }
                                else
                                {
                                    System.err.printf(Thread.currentThread().getName() + ":Duplicado. Fecha: %s, @%-20s RT: @%-5s Likes: @%-5s Tweet: %s\n",
                                                              s.getCreatedAt().toString(),
                                                              s.getUser().getScreenName(),
                                                              s.getRetweetCount(),
                                                              s.getFavoriteCount(),                                                               
                                                              cleanText(s.getText()));
                                }
                            }
                            else //EN CASO QUE NO SEA RELEVANTE
                            {
                                if(insertar.Insertar_Tweet_Irrelevante(String.valueOf(s.getId()), s.getUser().getScreenName(), tweet, s.getFavoriteCount(), s.getRetweetCount(), new SimpleDateFormat("yyyy-MM-dd").format(s.getCreatedAt()), candidato_index, fake_news.get(), category.get(), emotion.get()))
                                {
                                    irrelevant_tweets++;
                                    System.out.printf(Thread.currentThread().getName() + ":No relevante. Fecha: %s, @%-20s RT: @%-5s Likes: @%-5s Tweet: %s\n",
                                                              s.getCreatedAt().toString(),
                                                              s.getUser().getScreenName(),
                                                              s.getRetweetCount(),
                                                              s.getFavoriteCount(),                                                               
                                                              cleanText(s.getText()));
                                }
                                else
                                {
                                    System.err.printf(Thread.currentThread().getName() + ":Duplicado. Fecha: %s, @%-20s RT: @%-5s Likes: @%-5s Tweet: %s\n",
                                                              s.getCreatedAt().toString(),
                                                              s.getUser().getScreenName(),
                                                              s.getRetweetCount(),
                                                              s.getFavoriteCount(),                                                               
                                                              cleanText(s.getText()));
                                }
                            }
                            executor.shutdown();
                        }
                        searchTweetsRateLimit = r.getRateLimitStatus();
                        seconds_until_reset = searchTweetsRateLimit.getSecondsUntilReset();
                    }
                }
                catch (Exception e)
                {
                    System.out.printf(Thread.currentThread().getName() + ": Se excedió el límite, esperando %d segundos", seconds_until_reset + "\n");
                    try {
                        Thread.sleep((seconds_until_reset+2) * 1000l);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            System.out.printf("\n" + Thread.currentThread().getName() + ": Se encontraron un total de %d tweets, de los cuales %d fueron relevantes y %d irrelevantes\n", totalTweets, relevant_tweets, irrelevant_tweets);
        } catch (TwitterException ex) {
            Logger.getLogger(Twitter_Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String cleanText(String text)
    {
            text = text.replace("\n", "\\n");
            text = text.replace("\t", "\\t");
            text = text.replace("'", "\"");
            return text;
    }
    
    public boolean Verificar_Relevancia(int _retweets, int _likes, int _followers)
    {
        double relevancia = ((_retweets *0.70) + (_likes * 0.30)) * this.factor_influencia;
        if(relevancia > 50)
            return true;
        else
            return false;
    }
    
    public String[] Tokenize(String _tweet)
    {
        String[] tokens = _tweet.split("\\s+");
        for(String token: tokens)
        {
            token.toLowerCase();
        }
        return tokens;
    }
}

