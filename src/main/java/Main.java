

import twitter4j.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.println;


public final class Main {
    /**
     * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
     *
     * @param args message
     */
    public static void main(String[] args) throws IOException, TwitterException {
//       getTweetInBD("#GoT","GoTTweet.txt",15000);
//         createNumberData("GoTTweet.txt","GoTTweetResult.txt");
        AprioriTraitement aprioriTraitement = new AprioriTraitement("GOTResultApriori.out", "RegleAssosiationOutPut.txt", 0.20);

        Traduction traducteur = new Traduction("GoTTweet.txt");
//        traducteur.getInLetter("GOTResultApriori.out", "test");
//        traducteur.getInNumber("GoTTweetResult.txt");
//        traducteur.getInLetter("RegleAssosiationOutPut.txt","RegleAssosiationOutPut.txtOutput");

    }



    public static void convertNumberToText( Map<String, Integer> mapConvert, String source, String output) throws IOException {
        InputStream flux=new FileInputStream(source);
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
    static public <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public static String magicCutTextFonction(String text)
    {
        text = text.replace("\n","");
        String result = "\"";
        result = result +         text.replace(" ", "\";\"");
        result = result + "\";";
        return result;
    }

    public static void getTweetInBD(String arg, String fileDirectory, int nbMaxTweet) throws FileNotFoundException, TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(arg);
        query.setCount(100);
        int nombreTweet = 0;
        PrintStream ps = new PrintStream(new FileOutputStream(fileDirectory));
        while(nbMaxTweet>nombreTweet)
        {
            QueryResult result = twitter.search(query);

            for (Status status : result.getTweets()) {
                ps.println("\""+status.getCreatedAt()+"\";\"@"+status.getUser().getScreenName()+"\";" + magicCutTextFonction(status.getText()));;
            }
            query = result.nextQuery();

            nombreTweet = nombreTweet + 100;
            System.out.println("Nombre de tweet => " + nombreTweet);


        }
    }
    public static Map<Integer,String> transformMap() throws IOException {
        int index=-1;
        Map<Integer,String> myMap = new HashMap<Integer, String>();

        String strLine="";
        String splitStr=";";

        try (FileReader fstream = new FileReader("donne.txt")){
            BufferedReader br = new BufferedReader(fstream);
            while ((strLine = br.readLine()) != null)   {
                String[] tweet= strLine.split(splitStr);
                for (int i = 1; i < tweet.length - 1 ; i++) {
                    if (!(isOnMap(tweet[i],myMap))){
                        myMap.put(++index,tweet[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myMap;
    }
    public static boolean isOnMap(String elem, Map<Integer, String> myMap){
        for (Integer key: myMap.keySet()) {
            if (elem.equals(myMap.get(key)))return true;
        }
        return false;
    }
}
