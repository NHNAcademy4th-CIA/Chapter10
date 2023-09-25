package org.nhnacademy.lsj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stream을 사용하여 메서드 구현.
 */
public class Problem5 {


    private static final Logger logger = LoggerFactory.getLogger(Problem5.class);

    /**
     * 학생 정보담고있는 배열 생성 . 및 메서드 테스트.
     */
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
        logger.info("{}", countGradeA(scoreData));
        logger.info("{}", creatListUnder70(scoreData));
        printScoreByNameOrder(scoreData);

        logger.info("\n\n");

        printScoreByScoreOrder(scoreData);


    }


    /**
     * size 체크.
     *
     * @param scoreInfos 학생배열.
     * @return 사이즈.
     */
    public static long countSize(ScoreInfo[] scoreInfos) {

        return Arrays.stream(scoreInfos).count();

    }

    /**
     * 평균 구하는 메서드.
     *
     * @param scoreInfos 학생배열.
     * @return 평균.
     */
    public static double getAverageScore(ScoreInfo[] scoreInfos) {

        return Arrays.stream(scoreInfos).mapToInt(x -> x.getScore())
                .average().getAsDouble();
    }

    /**
     * A학점 이상 == 90점이상 학생 수 구하기.
     *
     * @param scoreInfos 학생배열.
     * @return A이상 학생 수.
     */
    public static long countGradeA(ScoreInfo[] scoreInfos) {
        return Arrays.stream(scoreInfos).filter(x -> x.getScore() >= 90)
                .count();
    }

    /**
     * 70점 미만의 학생을 갖는 리스트 생성.
     *
     * @param scoreInfos 학생배열.
     * @return 리스트.
     */
    public static List<String> creatListUnder70(ScoreInfo[] scoreInfos) {

        List<String> list = new ArrayList<>();

        Arrays.stream(scoreInfos).filter(x -> x.getScore() < 70)
                .forEach(x -> list.add(x.getFirstName() + " " + x.getLastName()));

        return list;
    }

    /**
     * 이름 오름차순으로 정렬한 후 출력.
     *
     * @param scoreInfos 학생배열.
     */
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

    /**
     * 점수 오름차순으로 정렬한 후 출력.
     *
     * @param scoreInfos 학생배열.
     */
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