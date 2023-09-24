package org.nhnacademy.lsj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem5 {


    private static final Logger logger = LoggerFactory.getLogger(Problem5.class);

    public static void problem5() {

        ScoreInfo[] scoreData = new ScoreInfo[] {
                new ScoreInfo("Smith", "John", 70),
                new ScoreInfo("Doe", "Mary", 85),
                new ScoreInfo("Page", "Alice", 82),
                new ScoreInfo("Cooper", "Jill", 97),
                new ScoreInfo("Flintstone", "Fred", 66),
                new ScoreInfo("Rubble", "Barney", 80),
                new ScoreInfo("Smith", "Judy", 48),
                new ScoreInfo("Dean", "James", 90),
                new ScoreInfo("Russ", "Joe", 55),
                new ScoreInfo("Wolfe", "Bill", 73),
                new ScoreInfo("Dart", "Mary", 54),
                new ScoreInfo("Rogers", "Chris", 78),
                new ScoreInfo("Toole", "Pat", 51),
                new ScoreInfo("Khan", "Omar", 93),
                new ScoreInfo("Smith", "Ann", 95)
        };


        logger.info("{}", countSize(scoreData));
        logger.info("{}", getAverageScore(scoreData));
        logger.info("{}", printGradeA(scoreData));
        logger.info("{}", creatListUnder70(scoreData));
        printScoreByNameOrder(scoreData);

        logger.info("\n\n");

        printScoreByScoreOrder(scoreData);


    }


    public static long countSize(ScoreInfo[] scoreInfos) {

        return Arrays.stream(scoreInfos).count();

    }

    public static double getAverageScore(ScoreInfo[] scoreInfos) {

        return Arrays.stream(scoreInfos).mapToInt(x -> x.getScore())
                .average().getAsDouble();
    }

    public static long printGradeA(ScoreInfo[] scoreInfos) {
        return Arrays.stream(scoreInfos).filter(x -> x.getScore() >= 90)
                .count();
    }

    public static List<String> creatListUnder70(ScoreInfo[] scoreInfos) {

        List<String> list = new ArrayList<>();

        Arrays.stream(scoreInfos).filter(x -> x.getScore() < 70)
                .forEach(x -> list.add(x.getFirstName() + " " + x.getLastName()));

        return list;
    }

    public static void printScoreByNameOrder(ScoreInfo[] scoreInfos) {

        Arrays.stream(scoreInfos).sorted(new Comparator<ScoreInfo>() {
                    @Override
                    public int compare(ScoreInfo o1, ScoreInfo o2) {
                        return o1.getLastName().compareTo(o2.getLastName());
                    }
                })
                .forEach(x -> logger.info("{} {}",
                        x.getFirstName() + " " + x.getLastName(), x.getScore()));

    }

    public static void printScoreByScoreOrder(ScoreInfo[] scoreInfos) {

        Arrays.stream(scoreInfos).sorted(new Comparator<ScoreInfo>() {
                    @Override
                    public int compare(ScoreInfo o1, ScoreInfo o2) {
                        return o1.getScore() - o2.getScore();
                    }
                })
                .forEach(x -> logger.info("{} {}",
                        x.getFirstName(), x.getScore()));

    }

}


class ScoreInfo {

    private String lastName;
    private String firstName;

    private int score;


    public ScoreInfo(String lastName, String firstName, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getScore() {
        return score;
    }
}