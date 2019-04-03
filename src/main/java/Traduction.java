import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Traduction {

    private String file;
    private Map<String, Integer> mapConvert = new HashMap<>();
    private List<String> motsInutiles = new ArrayList<>();
    public Traduction(String file) throws IOException {
        this.file = file;

        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;


        while ((ligne=buff.readLine())!=null){
            ligne = ligne.substring(32, ligne.length());
            ligne = ligne.replace("\";\""," ");
            ligne = ligne.replace(","," ");
            ligne = ligne.replace("\";"," ");

            ligne = ligne.replace(","," ");
            ligne = ligne.replace("."," ");
            ligne = ligne.replace(":"," ");
            ligne = ligne.replace("\""," ");
            ligne = ligne.replace("\'","");
            ligne = ligne.toLowerCase();



            String[] parts = ligne.split("\\s");
            for (String str : parts){

                str = str.trim();
                if(str.equals(""))continue;
                if(!mapConvert.containsKey(str)){
                    mapConvert.put(str, mapConvert.size());
                }
            }


        }
        buff.close();
    }

    public void getInLetter(String input, String output) throws IOException {
        System.out.println("Traduction de " + input + " vers " + output + " en lettres");
        InputStream flux=new FileInputStream(input);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        int nbLigne = 0;
        BufferedReader reader = new BufferedReader(new FileReader(input));
        while (reader.readLine() != null) nbLigne++;
        reader.close();
        int nbLigneLue =0;
        while ((ligne=buff.readLine())!=null) {

            String value = ligne.substring(ligne.indexOf('(')-1);
            ligne = ligne.substring(0,ligne.indexOf('('));
            String newLine ="";
            String[] parts = ligne.split("\\s");
            nbLigneLue++;

            if((((double)Math.round((((double)nbLigneLue/nbLigne)*100) * 1000) / 1000))%10<=0.01)
            {
                System.out.println("Traduction en cours " + (double)Math.round(((((double)nbLigneLue/nbLigne)*100) * 100) / 100) +"%");
            }

            for (String str : parts){

                str = str.trim();
                if(str.equals(""))continue;

                int strtoInt = 0;
                if(!str.equals("") && !str.equals("|")) strtoInt = Integer.parseInt(str);
                if(!str.equals("|"))
                {
                    newLine = newLine + getKey(mapConvert,strtoInt )+ " " ;
                }

                else
                {
                    newLine = newLine + " " + str;
                }

            }
            value = value.trim();
            newLine += value;
            ps.println(newLine);

        }
        buff.close();
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void getInNumber (String output) throws IOException {
        System.out.println("Traduction vers " + output + " en chiffre");
        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        PrintStream ps = new PrintStream(new FileOutputStream(output));

        Map<String, Integer> MapConvert = new HashMap<>();

        int nbLigne = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.readLine() != null) nbLigne++;
        reader.close();

        int nbLigneLue =0;
        while ((ligne=buff.readLine())!=null){
            nbLigneLue++;
            if((((double)Math.round((((double)nbLigneLue/nbLigne)*100) * 1000) / 1000))%10<=0.01)
            {
                System.out.println("Traduction en cours " + (double)Math.round(((((double)nbLigneLue/nbLigne)*100) * 100) / 100) +"%");
            }
            ligne = ligne.substring(32, ligne.length());
            ligne = ligne.replace("\";\""," ");
            ligne = ligne.replace(","," ");
            ligne = ligne.replace("\";"," ");

            ligne = ligne.replace(","," ");
            ligne = ligne.replace("."," ");
            ligne = ligne.replace(":"," ");
            ligne = ligne.replace("\""," ");
            ligne = ligne.replace("\'","");
            ligne = ligne.toLowerCase();

            String newLine = "";

            String[] parts = ligne.split("\\s");
            for (String str : parts){
                str = str.trim();
                if(str.equals(""))continue;
                if(motsInutiles.contains(str))continue;
                if(!MapConvert.containsKey(str)){
                        MapConvert.put(str,MapConvert.size());
                }
                newLine = newLine + MapConvert.get(str) +" ";

            }
            ps.println(newLine);
        }

        buff.close();
    }

    public void addMotsInutileFiles(String file) throws IOException {
        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        motsInutiles.add(" ");
        while((ligne=buff.readLine())!=null)
        {
            if (!motsInutiles.contains(ligne))
            {
                motsInutiles.add(ligne.trim());
            }
        }
    }
}
