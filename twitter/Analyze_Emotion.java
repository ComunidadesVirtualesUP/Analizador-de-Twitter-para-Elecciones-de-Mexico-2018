package twitter;

import java.util.concurrent.Callable;

public class Analyze_Emotion implements Callable<String>
{
    private String [] tokens;
    public Analyze_Emotion(String [] _tokens)
    {
        this.tokens = _tokens;
    }
    
    @Override
    public String call() throws Exception {
        String [] keywords_felicidad = {"feliz", "alegría", "dichoso", "dichosa", "alegre", "alegra", "gusta", "acuerdo", "encanta", "contento", "satisfecho", "triunfo", "triunfar", "vencer", "triunfador", "ganador", "agradecimiento", "agradecer", "agradecido", "amo", "amor", "quiero", "divertido"};
        String [] keywords_tristeza = {"triste", "entristece", "desilusión", "desilusionado", "decepción", "digusto", "desagrado", "dolor", "desolado", "devastado", "destruido", "afligido", "desesperanza", "deseperanzado", "llorar", "lloro" ,"entristece", "melancólico" ,"abatido" ,"decepcionado" ,"desencanto" ,"desencantado"};
        String [] keywords_miedo = {"miedo", "susto", "asustado" ,"peligro", "peligroso", "temor", "angustia", "ansiedad", "horror", "terror" ,"preocupación", "temeroso", "atemoriza", "preocupado" ,"angustiado", "preocupado", "atemorizado", "inseguro", "desconfianza", "desconfiado", "aterra", "pánico", "espantado" , "pavor"};
        String [] keywords_sorpresa = {"asombro", "asombrado", "asombra", "desconcierto", "desconcentrado", "inesperado", "imprevisible", "perplejo", "admiración", "admirado", "conmocionado", "conmoción", "consternación", "consternado", "impresionado", "impresiona"};
        String [] keywords_ira = {"ira", "enojo", "enojado", "enoja", "molestia", "molesto", "molesta", "enfurecer", "enfurecido", "enfurece", "indignación", "indignado", "enfado", "enfadado", "encorajinado", "coraje", "rabia", "furioso", "enfurece", "fastidiado"};
        String [] keywords_asco = {"asco", "desagrado", "descontento", "desagrada", "aversión", "rechazo", "repulsión", "asqueado", "desprecio", "despreciable", "nauseabundo", "náuseas", "repugnancia", "repugnante", "repugna", "indeseable", "fastidioso", "fastidiado", "tirria", "odio", "odiado", "odioso"};
        
        int[] emotion_count = new int [6];
        emotion_count[0] = Check_Emotion(tokens, keywords_felicidad);
        emotion_count[1] = Check_Emotion(tokens, keywords_tristeza);
        emotion_count[2] = Check_Emotion(tokens, keywords_miedo);
        emotion_count[3] = Check_Emotion(tokens, keywords_sorpresa);
        emotion_count[4] = Check_Emotion(tokens, keywords_ira);
        emotion_count[5] = Check_Emotion(tokens, keywords_asco);
        
        int max_count_index = 0;
        
        for(int i = 1; i < emotion_count.length; i++)
        {
            if(emotion_count[max_count_index] < emotion_count[i])
                max_count_index = i;
        }
        
        if(emotion_count[max_count_index] == 0)
            return "0";

        return String.valueOf(max_count_index + 1);
    }    
    
    public int Check_Emotion(String[] tokens, String[] keywords)
    {
        int count = 0;
        for(String keyword: keywords)
        {
            for(String token: tokens)
            {
                if(keyword.equals(token))
                {
                    count++;
                }
            }
        }
        return count;
    }
}