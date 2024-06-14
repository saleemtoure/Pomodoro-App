import java.util.ArrayList;

public class PomodoroLogic {

    static float totalSessionMinutes;
    static int breakLength;
    static int basicSessionLength;
    static int numOfWorkSessions;
    static int numOfBreaks;
    int usedMinutes;
    int minutesLeft;
    int additionalMinutesPerSession;
    int remainingMinutes;
    static int totalWorkTime;
    static int totalBreakTime;
    static ArrayList<Object> sessionSequence;
    static ArrayList<Integer> sessionLengths;

    static void calculateSessions(int totalSessionMinutes) {

        // !!!!LOGIC NEEDS TO BE IMPROVED HERE.

        PomodoroLogic.totalSessionMinutes = Float.valueOf(totalSessionMinutes);
        breakLength = 5;

        // Kun for å ha oversikt og for debugging
        sessionSequence = new ArrayList<>();
        sessionLengths = new ArrayList<>();

        int maxPossibleWorkSessions = (int) Math.floor(totalSessionMinutes / (25 + breakLength));

        numOfWorkSessions = maxPossibleWorkSessions;
        numOfBreaks = numOfWorkSessions - 1;

        totalBreakTime = numOfBreaks * breakLength;

        int availableWorkTime = totalSessionMinutes - totalBreakTime;

        int basicSessionLength = availableWorkTime / numOfWorkSessions;

        if (basicSessionLength < 20) {
            basicSessionLength = 20;
        } else if (basicSessionLength > 25) {
            basicSessionLength = 25;
        }

        int usedWorkTime = basicSessionLength * numOfWorkSessions;

        int remainingMinutes = availableWorkTime - usedWorkTime;

        for (int i = 0; i < numOfWorkSessions; i++) {
            int sessionTime = basicSessionLength;
            if (remainingMinutes > 0 && sessionTime < 25) {
                sessionTime++;
                remainingMinutes--;
            }
            sessionLengths.add(sessionTime);
            sessionSequence.add((Object) new Work());
            if (i < numOfBreaks) {
                sessionSequence.add((Object) new Break());
                sessionLengths.add(breakLength);
            }
        }

        totalWorkTime = sessionLengths.stream().filter(length -> length != breakLength).mapToInt(Integer::intValue).sum();
        totalBreakTime = numOfBreaks * breakLength;

        System.out.println("Number of work sessions: " + numOfWorkSessions);
        System.out.println("Total time spent on work: " + totalWorkTime + " minutes");
        System.out.println("Number of breaks: " + numOfBreaks);
        System.out.println("Total time spent on breaks: " + totalBreakTime + " minutes");
        System.out.println(totalWorkTime + totalBreakTime == totalSessionMinutes - breakLength * 1);

        System.out.println("\nSession sequence:");
        System.out.println(sessionSequence);

        System.out.println("\nSession lengths:");
        System.out.println(sessionLengths);
    }

}
