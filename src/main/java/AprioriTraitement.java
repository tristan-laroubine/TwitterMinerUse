import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class AprioriTraitement {

    private String file;
    private InputStreamReader lecture;
    private Map<String, Double> minConf = new HashMap<>();
    private int nbLigne;

    AprioriTraitement(String file) throws IOException {
        this.file = file;
        InputStream flux=new FileInputStream(file);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        while(buff.readLine() != null)
        {
            ++nbLigne;
        }
    }


    void createMinConf() throws IOException {
        String ligne;
        BufferedReader buff=new BufferedReader(lecture);
        while ((ligne=buff.readLine())!=null) {
            String value = ligne.substring(ligne.indexOf('(')+1 , ligne.indexOf(')'));
            ligne = ligne.substring(0,ligne.indexOf('('));
            String newLine ="";
            String[] parts = ligne.split(" ");
            if (parts.length == 1)
            {

                minConf.put(parts[0], (double) (Integer.parseInt(value)/nbLigne));
            }

        }
        buff.close();
        System.out.println(minConf);
    }






}
