package twitter;

import java.util.concurrent.Callable;

public class Analyze_Category implements Callable<String>
{
    private String [] tokens;
    public Analyze_Category(String [] _tokens)
    {
        this.tokens = _tokens;
    }
    
    @Override
    public String call() throws Exception {
        String [] keywords_economia = {"economia", "monetaria", "economica", "cotizacion", "peso", "devaluacion", "depreciacion", "bolsadevalores", "bancarias", "inflacion", "banco", "dinero", "comercio", "impuestos", "importacion", "exportacion"};
        String [] keywords_seguridad = {"seguridad", "inseguridad", "narcotrafico", "narcotráfico", "narcomenudeo", "cárteles", "droga", "marihuana", "legalización", "homicidios", "violencia", "delitos", "secuestros", "desapariciones", "asaltos", "crimen", "criminales", "policía", "denuncias", "aministía", "cárceles", "feminicidio"};
        String [] keywords_sindicatos = {"sindicatos", "sindical", "ctm", "croc", "cnop", "paro", "huelga"};
        String [] keywords_transparencia = {"transparencia", "acceso", "informaciónpública", "3de3", "7de7"};
        String [] keywords_educacion = {"educacion", "educativa", "magistral", "coordinadora", "maestros", "profesores", "estudiantes", "alumnos", "becas", "universidad", "universidades", "escuelas", "escuela", "institutos", "instituto", "sep", "unam"};
        String [] keywords_paraestatatales = {"paraestatales", "empresas", "cfe", "pemex"};
        String [] keywords_relaciones_exteriores = {"exteriores", "migracion", "migrantes", "migración", "inmigración", "embajadas", "latinoamérica"};
        String [] keywords_administracion_publica = {"administración", "pública", "funcionarios"};
        String [] keywords_deportes = {"deporte", "deportes", "deportiva", "actividad", "física", "atletas", "rendimiento", "futbol", "tri"};
        String [] keywords_religion = {"religión", "religion", "religioso", "religiosos", "iglesia", "fe", "dios", "sacerdotes", "pastores", "clérigos", "papa", "católicos", "evangelistas", "evangélicos"};
        String [] keywords_infancia_y_juventud = {"infancia", "juventud", "niños", "niñas", "adolescentes", "jóvenes"};
        String [] keywords_grupos_vulnerables = {"grupos", "vulnerables", "tercera", "edad", "adultos", "mayores", "discapacitados", "pobres", "indígenas", "madres", "solteras", "mujeres"};
        String [] keyords_minorias_y_etnias = {"minorías", "etnias", "étnicas", "discriminación", "homosexuales", "lgbt"};
        String [] keywords_medios_de_comunicacion = {"comunicación", "prensa", "radio", "televisión", "periódicos", "cine", "redes", "sociales"};
        String [] keywords_ciencia_y_tecnologia = {"ciencia", "tecnología", "investigación", "investigadores", "conacyt", "innovación"};
        String [] keywords_empleo_y_desempleo = {"empleo", "desempleo", "trabajo", "empresas", "pymes", "comercio", "ambulantes", "salario"};
        String [] keywords_politica_partidista = {"política", "partidista", "partidos", "políticos", "pri", "pan", "prd", "morena", "pvem", "pt", "coalición", "electorales", "ine" ,"candidatos", "políticos", "encuestas"};
        String [] keywords_infraestructura = {"infraestructura", "movilidad", "transporte", "carreteras", "puentes", "ferrocarriles", "aeropuerto", "puerto"};
        String [] keywords_agricultura_y_ganaderia = {"agricultura", "ganadería", "campo", "alimentos", "campesinos", "trabajadores del campo", "sequía", "desbasto", "agua", "naturales"};
        String [] keywords_corrupcion = {"corrupción", "impunidad", "abuso", "poder"};
        String [] keywords_salud_publica = {"salud", "enfermedades", "obsesidad", "diabetes", "desnutrición", "vacunación", "imss", "seguro"};
        String [] keywords_genero = {"género", "equidad", "igualdad"};
        String [] keywords_campaña_electoral_y_debate = {"campaña", "debate", "elección", "presupuesto", "mítines"};
        String [] keywords_relaciones_mexico_estados_unidos = {"muro", "frontera", "fronterizo", "tlc", "tlcan", "migración", "usa", "eua", "trump"};
        String [] keywords_otros = {"familia", "sustentabilidad", "fiscal", "energética", "ambiente", "renovables"};
        
        int[] category_count = new int [25];
        category_count[0] = Check_Category(tokens, keywords_economia);
        category_count[1] = Check_Category(tokens, keywords_seguridad);
        category_count[2] = Check_Category(tokens, keywords_sindicatos);
        category_count[3] = Check_Category(tokens, keywords_transparencia);
        category_count[4] = Check_Category(tokens, keywords_educacion);
        category_count[5] = Check_Category(tokens, keywords_paraestatatales);
        category_count[6] = Check_Category(tokens, keywords_relaciones_exteriores);
        category_count[7] = Check_Category(tokens, keywords_administracion_publica);
        category_count[8] = Check_Category(tokens, keywords_deportes);
        category_count[9] = Check_Category(tokens, keywords_religion);
        category_count[10] = Check_Category(tokens, keywords_infancia_y_juventud);
        category_count[11] = Check_Category(tokens, keywords_grupos_vulnerables);
        category_count[12] = Check_Category(tokens, keyords_minorias_y_etnias);
        category_count[13] = Check_Category(tokens, keywords_medios_de_comunicacion);
        category_count[14] = Check_Category(tokens, keywords_ciencia_y_tecnologia);
        category_count[15] = Check_Category(tokens, keywords_empleo_y_desempleo);
        category_count[16] = Check_Category(tokens, keywords_politica_partidista);
        category_count[17] = Check_Category(tokens, keywords_infraestructura);
        category_count[18] = Check_Category(tokens, keywords_agricultura_y_ganaderia);
        category_count[19] = Check_Category(tokens, keywords_corrupcion);
        category_count[20] = Check_Category(tokens, keywords_salud_publica);
        category_count[21] = Check_Category(tokens, keywords_genero);
        category_count[22] = Check_Category(tokens, keywords_campaña_electoral_y_debate);
        category_count[23] = Check_Category(tokens, keywords_relaciones_mexico_estados_unidos);
        category_count[24] = Check_Category(tokens, keywords_otros);
        
        int max_count_index = 0;
        
        for(int i = 1; i < category_count.length; i++)
        {
            if(category_count[max_count_index] < category_count[i])
                max_count_index = i;
        }
        
        if(category_count[max_count_index] == 0)
            return "0";
        
        return String.valueOf(max_count_index + 1);
    }    
    
    public int Check_Category(String[] tokens, String[] keywords)
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
