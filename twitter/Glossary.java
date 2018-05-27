package twitter;

public class Glossary {
    
    public static String GetCategory(int _index)
    {
        switch(_index)
        {
            case 0: 
                return "Sin Clasificar";
            case 1: 
                return "Economía";
            case 2: 
                return "Seguridad";
            case 3: 
                return "Sindicatos";
            case 4: 
                return "Transparencia";
            case 5: 
                return "Educación";
            case 6: 
                return "Paraestatales";
            case 7: 
                return "Relaciones Exteriores";
            case 8: 
                return "Adminsitración Pública";
            case 9: 
                return "Deportes";
            case 10: 
                return "Religión";
            case 11: 
                return "Infancia y Juventud";
            case 12: 
                return "Grupos Vulnerables";
            case 13: 
                return "Minorías y Etnias";
            case 14: 
                return "Medios de Comunicación";
            case 15: 
                return "Ciencia y Tecnología";
            case 16: 
                return "Empleo y Desempleo";
            case 17: 
                return "Política Partidista";
            case 18: 
                return "Infraestructura";
            case 19: 
                return "Agricultura y Ganadería";
            case 20: 
                return "Corrupción";
            case 21: 
                return "Salud Pública";
            case 22: 
                return "Género";
            case 23: 
                return "Campaña Electoral y Debate";
            case 24: 
                return "Relaciones México Estados Unidos";
            case 25: 
                return "Otros";
        }          
        return ""; 
    }
    
    public static String GetEmotion(int _index)
    {
        switch(_index)
        {
            case 0: 
                return "Sin Clasificar";
            case 1: 
                return "Felicidad";
            case 2: 
                return "Tristeza";
            case 3: 
                return "Miedo";
            case 4: 
                return "Sorpresa";
            case 5: 
                return "Ira";
            case 6: 
                return "Asco";
        }     
        return"";
    }
    
    public static String GetFakeNews(int _index)
    {
        switch(_index)
        {
            case 0: 
                return "Sí";
            case 1: 
                return "No";
        }
        return "";
    }
    
        
    public static String GetCandidato(int _index)
    {
        switch(_index)
        {
            case 0: 
                return "AMLO";
            case 1: 
                return "Anaya";
            case 2: 
                return "Meade";
            case 3: 
                return "Bronco";
        }
        return "";
    }
}
