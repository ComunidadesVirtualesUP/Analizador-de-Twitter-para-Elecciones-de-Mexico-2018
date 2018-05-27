package twitter;

public class Tweet {
    
    private String id;
    private String username;
    private String content;
    private int favorite_count;
    private int retweet_count;
    private String tweet_date;
    
    
    public Tweet(String id, String username, String content, int favorite_count, int retweet_count, String tweet_date) 
    {
        this.id = id;
        this.username = username;
        this.content = content;
        this.favorite_count = favorite_count;
        this.retweet_count = retweet_count;
        this.tweet_date = tweet_date;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public String getTweet_date() {
        return tweet_date;
    }
    
    
}
