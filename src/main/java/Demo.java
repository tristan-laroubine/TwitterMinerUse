import AprioriAlgorithme.AprioriFrequentItemsetGenerator;
import AprioriAlgorithme.FrequentItemsetData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {

    public static void main(String[] args) {
        AprioriFrequentItemsetGenerator<Integer> generator =
                new AprioriFrequentItemsetGenerator<>();

        List<Set<Integer>> itemsetList = new ArrayList<>();

        itemsetList.add(new HashSet<Integer>(Arrays.asList(1,8,5)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(5,6,1,8,2)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(1,2,3,6)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(1,8)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(1,8,3)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(8)));
        itemsetList.add(new HashSet<Integer>(Arrays.asList(8,3)));



        FrequentItemsetData<Integer> data = generator.generate(itemsetList, 0.2);
        int i = 1;

        for (Set<Integer> itemset : data.getFrequentItemsetList()) {

            String ligne ="";
            for (Integer integer : itemset)
            {
                ligne = ligne + integer + " ";
            }
            ligne = ligne + "(" +  data.getSupportAbsolute(itemset) + ")";
            System.out.println(ligne);
        }
    }
}