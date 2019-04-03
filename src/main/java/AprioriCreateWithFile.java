import AprioriAlgorithme.AprioriFrequentItemsetGenerator;
import AprioriAlgorithme.FrequentItemsetData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AprioriCreateWithFile {

    AprioriCreateWithFile(String intput, String output, int minSuppAbsolue) throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream(output));
        BufferedReader reader = new BufferedReader(new FileReader(intput));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();

        System.out.println("Lancement de l'algorithme Apriori pour " + lines + " tweets  et comme minSuppRelatif " + 1.0 * ((double) minSuppAbsolue / (double) lines));
        Double minSuppRelatif = 1.0 * ((double) minSuppAbsolue/ (double) lines);
        String ligne;
        InputStream flux=new FileInputStream(intput);
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff = new BufferedReader(lecture);
        List<Set<Integer>> itemsetList = new ArrayList<>();
        while ((ligne=buff.readLine())!=null) {

            String[] parts = ligne.split(" ");


            HashSet<Integer> hashMapInteger = new HashSet<>();


            for (String str : parts){
                int nb = 0;
                if (!str.isEmpty()){
                    try{
                        nb = Integer.parseInt(str);

                    }catch (java.lang.NumberFormatException e){

                    }
                }
                hashMapInteger.add(nb);
            }
            itemsetList.add(hashMapInteger);


        }
        AprioriFrequentItemsetGenerator<Integer> generator =
                new AprioriFrequentItemsetGenerator<>();


        FrequentItemsetData<Integer> data = generator.generate(itemsetList, minSuppRelatif);
        int i = 1;

        for (Set<Integer> itemset : data.getFrequentItemsetList()) {

            String ligne1 ="";
            for (Integer integer : itemset)
            {
                ligne1 = ligne1 + integer + " ";
            }
            ligne1 = ligne1 + "(" +  data.getSupportAbsolute(itemset) + ")";
            ps.println(ligne1);
        }
        buff.close();


    }
}
