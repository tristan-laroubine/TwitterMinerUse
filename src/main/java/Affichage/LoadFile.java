package Affichage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LoadFile {

    BufferedReader bufferedReader;
    String file ;
    ArrayList<ArrayList<String>> dataValuesGlobal =new ArrayList<>();

    public ArrayList<ArrayList<String>> getDataValuesGlobal() {
        return dataValuesGlobal;
    }

    public LoadFile(String file){
        this.file = file;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                String attr1;
                String attr2;
                String freq;
                String conf;
                String lift;

                String[] parts = line.split("\\|");
                attr1 = parts[0];
                parts = parts[1].split("\\(");
                attr2 = parts[0];
                conf = parts[1].replace(")" , "");
                lift = parts[2].replace(")","");
                freq = parts[3].replace(")","");

//                System.out.println(attr1 + " "+ attr2 + " "+ conf + " " + lift + " " + freq );

                ArrayList<String> arrayTemp = new ArrayList<String>(0);
                arrayTemp.add(attr1);
                arrayTemp.add(attr2);
                arrayTemp.add(conf);
                arrayTemp.add(freq);
                arrayTemp.add(lift);
                dataValuesGlobal.add(arrayTemp);
//                System.out.println(arrayTemp + "\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
