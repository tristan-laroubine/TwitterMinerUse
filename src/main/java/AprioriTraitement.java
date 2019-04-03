import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

@SuppressWarnings("Duplicates")
public class AprioriTraitement {

    private String file;
    private InputStreamReader lecture;

    private List<AproriTuple> aproriTuples = new ArrayList<>();
    private List<RegleAssociation> regleAssociations = new ArrayList<>();
    private Double confMin;
    private String output;
    private int nbLigne;

    AprioriTraitement(String file, String output, Double confMin, String fichierOrigin) throws IOException {
        this.file = file;
        InputStream flux=new FileInputStream(this.file);
        this.lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne = buff.readLine();
        this.output = output;
        this.confMin = confMin;

        BufferedReader buff2 = new BufferedReader(new InputStreamReader(new FileInputStream (fichierOrigin)));

        while (buff2.readLine() != null) nbLigne++;


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

                aproriTuples.add(new AproriTuple(vectorInt,  Integer.valueOf(value)));
        }
        buff.close();

    }



    void calculMinConf() throws FileNotFoundException {
        Collections.sort(aproriTuples);
        aproriTuples.remove(0);
        PrintStream ps = new PrintStream(new FileOutputStream(output));
      for (AproriTuple aproriTuple : aproriTuples)
      {
          for (AproriTuple aproriTuple1 : aproriTuples)
          {
              if(!aproriTuple.isContainsIn(aproriTuple1)) continue;
              if (aproriTuple.getMyNumber() == aproriTuple1.getMyNumber()) continue;
              if (aproriTuple.getMyNumber().size() == aproriTuple1.getMyNumber().size()) continue;
              RegleAssociation regleAssociation = new RegleAssociation(aproriTuple, aproriTuple1, confMin);
              if (regleAssociation.isRightRuleAssociation())
              {
                  regleAssociations.add(regleAssociation);
                  ps.println(regleAssociation);
              }
          }
      }

    }


    public void doWithLift(String output) throws FileNotFoundException {

        PrintStream ps = new PrintStream(new FileOutputStream(output));
        for (RegleAssociation regleAssociation : regleAssociations)
        {
          ps.println(regleAssociation + " (" + regleAssociation.getConfiance() / (regleAssociation.getMyBefore().myOccurence /(double)nbLigne) +')' + '(' + (regleAssociation.getMyAfter().myOccurence /(double)nbLigne)+')' );

        }
    }
}
