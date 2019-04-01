import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Traduction {

    private String file;
    private Map<String, Integer> mapConvert = new HashMap<>();
    public Traduction(String file) throws IOException {
        this.file = file;

        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;



        System.out.println(mapConvert.size());
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

            String newLine = "";

            String[] parts = ligne.split(" ");
            for (String str : parts){
                if(!mapConvert.containsKey(str)){
                    mapConvert.put(str, mapConvert.size());
                }
                newLine = newLine + " " + mapConvert.get(str);
            }


        }
        System.out.println(mapConvert.size());
        buff.close();
    }

    public void getInLetter(String input, String output) throws IOException {
        InputStream flux=new FileInputStream(input);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        while ((ligne=buff.readLine())!=null) {
            String value = ligne.substring(ligne.indexOf('(') , ligne.indexOf(')')+1);
            ligne = ligne.substring(0,ligne.indexOf('('));
            String newLine ="";
            String[] parts = ligne.split(" ");


            for (String str : parts){
                str = str.replace(" ","");
//                    System.out.println("|" + str + "|");
                int strtoInt = 0;
                if(!str.equals("")) strtoInt = Integer.parseInt(str);
                newLine = newLine + " " + getKey(mapConvert,strtoInt);
            }
            newLine = newLine + " " + value;
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
        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        PrintStream ps = new PrintStream(new FileOutputStream(output));

        Map<String, Integer> MapConvert = new HashMap<>();

        System.out.println(MapConvert.size());
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

            String newLine = "";

            String[] parts = ligne.split(" ");
            for (String str : parts){
                if(!MapConvert.containsKey(str)){
                    MapConvert.put(str,MapConvert.size());
                }
                newLine = newLine + " " + MapConvert.get(str);
            }
            ps.println(newLine);
        }
        System.out.println(MapConvert.size());
        buff.close();
    }
}
