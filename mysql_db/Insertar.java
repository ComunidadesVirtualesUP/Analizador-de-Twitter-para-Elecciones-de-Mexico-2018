package mysql_db;

import java.sql.SQLException;
import java.sql.Statement;

public class Insertar extends Conexion {
    public boolean Insertar_Tweet_Irrelevante(String _id, String _username, String _content, int _favorite_count, int _retweet_count, String _tweet_date, String _candidato, String _fake_news, String _category, String _emotion)throws SQLException
    {
        Statement statement= connection.createStatement();
        String query = "INSERT INTO `tweets_irrelevantes`(`id`, `username`, `content`, `favorite_count`, `retweet_count`, `date`, `candidato`, `fake_news`, `categoria`, `emocion`) VALUES ('"+ _id + "', '"+ _username + "', '"+ _content + "', '"+ _favorite_count + "', '"+ _retweet_count + "', '"+ _tweet_date + "', '"+ _candidato + "', '"+ _fake_news + "', '"+ _category + "', '"+ _emotion + "')";
        //System.out.println(query);
        try
        {
            statement.executeUpdate(query);
        }catch(SQLException e)
        {
            //System.out.println(e);
            return false;
        }
        return true;
    }
    
    public  boolean  Insertar_Tweet_Relevante(String _id, String _username, String _content, int _favorite_count, int _retweet_count, String _tweet_date, String _candidato, String _fake_news, String _category, String _emotion)throws SQLException
    {
        Statement statement= connection.createStatement();
        String query = "INSERT INTO `tweets_relevantes`(`id`, `username`, `content`, `favorite_count`, `retweet_count`, `date`, `candidato`, `fake_news`, `categoria`, `emocion`) VALUES ('"+ _id + "', '"+ _username + "', '"+ _content + "', '"+ _favorite_count + "', '"+ _retweet_count + "', '"+ _tweet_date + "', '"+ _candidato + "', '"+ _fake_news + "', '"+ _category + "', '"+ _emotion + "')";
        //System.out.println(query);
        try
        {
            statement.executeUpdate(query);
        }catch(SQLException e)
        {
            //System.out.println(e);
            return false;
        }
        return true;
    }
}
