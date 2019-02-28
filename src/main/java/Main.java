

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;


public final class Main {
    /**
     * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
     *
     * @param args message
     */
    public static void main(String[] args) throws FileNotFoundException, TwitterException {
//       getTweetInBD("#GiletsJaunes","output.txt",10000);

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
        while(nbMaxTweet<nombreTweet)
        {
            QueryResult result = twitter.search(query);


            for (Status status : result.getTweets()) {
                ps.println("\""+status.getCreatedAt()+"\";\"@"+status.getUser().getScreenName()+"\";" + magicCutTextFonction(status.getText()));
            }
            query = result.nextQuery();

            nombreTweet = nombreTweet + 100;
            System.out.println("Nombre de tweet => " + nombreTweet);


        }
    }
}