

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
    public static void main(String[] args) throws TwitterException, FileNotFoundException, UnsupportedEncodingException {
        // The factory instance is re-useable and thread safe.
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("#GiletsJaunes");
        int numberOfTweets = 10;
        long lastID = Long.MAX_VALUE;
        ArrayList<Status> tweets = new ArrayList<Status>();
        while (tweets.size () < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100)
                query.setCount(100);
            else
                query.setCount(numberOfTweets - tweets.size());
            try {
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());
                println("Gathered " + tweets.size() + " tweets");
                for (Status t: tweets)
                    if(t.getId() < lastID) lastID = t.getId();

            }

            catch (TwitterException te) {
                println("Couldn't connect: " + te);
            };
            query.setMaxId(lastID-1);
        }









        System.out.println("Showing home timeline.");

        PrintWriter writer = new PrintWriter("donne.txt", "UTF-8");


        for (Status status : tweets) {
            writer.println("\""+status.getCreatedAt()+"\";\""+status.getGeoLocation()+"\";\"@"+status.getUser().getScreenName()+"\";" + magicCutTextFonction(status.getText()));
        }
        writer.close();

    }

    public static String magicCutTextFonction(String text)
    {
        text = text.replace("\n","");
        String result = "\"";
        result = result +         text.replace(" ", "\";\"");
        result = result + "\";";
        return result;
    }
}