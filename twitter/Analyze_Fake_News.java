package twitter;

import java.util.concurrent.Callable;

public class Analyze_Fake_News implements Callable<String>
{
    private String [] tokens;
    public Analyze_Fake_News(String [] _tokens)
    {
        this.tokens = _tokens;
    }
    
    @Override
    public String call() throws Exception {
        String [] keywords = {"falsas", "noticiasfalsas", "falsa", "falsa", "fake", "fakenews", "engaño", "falacia", "patraña", "choro", "fake", "desinformación", "rumor"};
        for(String keyword: keywords)
        {
            for(String token: tokens)
            {
                if(keyword.equals(token))
                {
                    return "1";
                }
            }
        }
        return "0";
    }    
}
