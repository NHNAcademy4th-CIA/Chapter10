package org.nhnacademy.minju;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .with StreamApi
 *
 */
public class Exercise5 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise5.class);

    private static class ScoreInfo {
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

    private static ScoreInfo[] scoreData = new ScoreInfo[] {
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

    /**
     * .print the number of students (without using scoreData.length)
     * print the average score for all of the students
     * print the number of students who got an A (score greater than or equal to 90)
     * use the collect() stream operation to create a List<String> that contains the names of students whose score was less than 70; the names should be in the form first name followed by last name
     * print the names from the List that was generated in the previous task
     * print out the students' names and scores, ordered by last name
     * print out the students' names and scores, ordered by score
     * <p>
     * filter() : applies a Predicate to a stream and create new stream
     * map() : applies function to each value in a stream and creat new stream
     */
    public static void exercise5() {

        logger.info("number of students : {}", Arrays.stream(scoreData).count());
        logger.info("the average score for all of the students : {}",
                Arrays.stream(scoreData).mapToInt(x -> x.getScore()).average().getAsDouble());
        logger.info("the number of students who got an A(score greater than or equal to 90) : {}",
                Arrays.stream(scoreData).filter(x -> x.getScore() >= 90).count());
        logger.info("the names from the List that was generated in the previous task : {}", under70(scoreData));
        logger.info("student's names and scores, ordered by last name : {}",
                Arrays.stream(scoreData).sorted(Comparator.comparing(ScoreInfo::getLastName))
                        .map(x -> x.getLastName() + " " + x.getFirstName() + ": " + x.getScore())
                        .collect(Collectors.toList()));
        logger.info("student's names and scores, ordered by score : {}",
                Arrays.stream(scoreData).sorted(Comparator.comparing(ScoreInfo::getScore))
                        .map(x -> x.getLastName() + " " + x.getFirstName() + ": " + x.getScore())
                        .collect(Collectors.toList()));
    }

    private static List<String> under70(ScoreInfo[] scoreData) {
        List<String> list = Arrays.stream(scoreData).filter(x -> x.getScore() < 70)
                .map(x -> x.getLastName() + " " + x.getFirstName())
                .collect(Collectors.toList());
        return list;
    }
}

