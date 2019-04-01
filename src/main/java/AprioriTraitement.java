import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class AprioriTraitement {

    private String file;
    private InputStreamReader lecture;
    private Map<ArrayList<Integer>, Double> aprioriVect = new HashMap<>();
    private Double confMin;
    private String output;
    private int nbLigne;

    AprioriTraitement(String file, String output, Double confMin) throws IOException {
        this.file = file;
        InputStream flux=new FileInputStream(this.file);
        this.lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne = buff.readLine();
        this.output = output;
        this.confMin = confMin;

        nbLigne = Integer.parseInt(ligne.substring(ligne.indexOf('(')+1 , ligne.indexOf(')')));

        createMinConf();
        calculMinConf();
    }



    void createMinConf() throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        String ligne;
        InputStream flux=new FileInputStream(this.file);
        this.lecture=new InputStreamReader(flux);
        BufferedReader buff = new BufferedReader(lecture);

        while ((ligne=buff.readLine())!=null) {
            String value = ligne.substring(ligne.indexOf('(')+1 , ligne.indexOf(')'));

            ligne = ligne.substring(0,ligne.indexOf('('));
            String newLine ="";
            String[] parts = ligne.split(" ");


            ArrayList<Integer> vectorInt   = new ArrayList<>();
            for (String str : parts){
                int valueStr = 0;
                if (!str.isEmpty()) valueStr = Integer.parseInt(str);
                vectorInt.add(valueStr);
            }
                if (Double.valueOf(value) / (double) nbLigne >= confMin) aprioriVect.put(vectorInt, Double.valueOf(value));
        }
        buff.close();

    }

    void calculMinConf(){
        for (Map.Entry<ArrayList<Integer>, Double> temp  : aprioriVect.entrySet())
        {
            System.out.println(temp);
        }
    }




}
