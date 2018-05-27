package twitter;

import java.util.concurrent.Callable;

public class Keyword_Analyzer implements Callable<String>
{
    String [] keywords;
    String[] tokens;
    String keywords_name;
    
    public Keyword_Analyzer(String [] _keywords, String [] _tokens, String _keywords_name)
    {
        this.keywords = _keywords;
        this.tokens = _tokens;
        keywords_name = _keywords_name;
    }
    
    @Override
    public String call() throws Exception {
        for(String keyword: keywords)
        {
            for(String token: tokens)
            {
                if(keyword.equals(token))
                {
                    return keywords_name;
                }
            }
        }
        return "";
    }
}

