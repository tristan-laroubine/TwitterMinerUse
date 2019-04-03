

import twitter4j.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.sql.DriverManager.println;


public final class Main {
    /**
     * Usage: java twitter4j.examples.tweets.UpdateStatus [text]
     *
     * @param args message
     */
    public static void main(String[] args) throws IOException, TwitterException, InterruptedException {

//       getTweetInBD("Macron","MacronV2.txt","fr",50000);
       Traduction traducteur = new Traduction("MacronV2.txt","motsInutile.txt");

//        traducteur.getInNumber("FileForApriori/MacronV2");
        AprioriCreateWithFile aprioriCreateWithFile = new AprioriCreateWithFile("FileForApriori/MacronV2","FileAfterApriori/MacronV2",150);
        traducteur.getInLetter("FileAfterApriori/MacronV2", "Traduction/MacronAfterAprioriV2");
        AprioriTraitement aprioriTraitement = new AprioriTraitement("FileAfterApriori/MacronV2", "FileAfterRuleAssos/MacronV2", 0.001,"FileForApriori/MacronV2");
        aprioriTraitement.doWithLift("MacronLiftV2.txt");

        traducteur.getInLetter("MacronLiftV2.txt", "Traduction/MacronAfterLiftV2");


    }





    public static String magicCutTextFonction(String text)
    {
        text = text.replace("\n","");
        String result = "\"";
        result = result +         text.replace(" ", "\";\"");
        result = result + "\";";
        return result;
    }

    public static void getTweetInBD(String arg, String fileDirectory, String language, int nbMaxTweet) throws FileNotFoundException, InterruptedException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(arg + " -filter:retweets AND -filter:replies&count=20&result_type=recent ");
        query.setLang(language);
        query.setCount(100);
        int nombreTweet = 0;
        PrintStream ps = new PrintStream(new FileOutputStream(fileDirectory));
        while(nbMaxTweet>nombreTweet)
        {
            QueryResult result = null;
            try {
                result = twitter.search(query);
            } catch (TwitterException e) {
                System.out.println("Attente pour le prochain tweet => " + e.getRateLimitStatus().getSecondsUntilReset() + " secondes....");
                TimeUnit.SECONDS.sleep(e.getRateLimitStatus().getSecondsUntilReset()+5);
                try {
                    result = twitter.search(query);
                } catch (TwitterException e1) {
                    e1.printStackTrace();
                }


            }

            for (Status status : result.getTweets()) {
                ps.println("\""+status.getCreatedAt()+"\";\"@"+status.getUser().getScreenName()+"\";" + magicCutTextFonction(status.getText()));;
            }

            query = result.nextQuery();

            nombreTweet = nombreTweet + 100;
            System.out.println("Nombre de tweet => " + nombreTweet);


        }
    }


}
