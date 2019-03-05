

import twitter4j.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.sql.DriverManager.println;


public final class Main {
    /**
     * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
     *
     * @param args message
     */
    public static void main(String[] args) throws FileNotFoundException, TwitterException {
       getTweetInBD("#GiletsJaunes","output.txt",10000);

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
