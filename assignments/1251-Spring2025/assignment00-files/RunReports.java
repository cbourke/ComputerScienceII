package unl.soc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * TODO: Documentation, author, etc.
 */
public class RunReports {

	/**
	 * Returns the difference in time in *seconds* between two
	 * <code>LocalDateTime</code> instances. You may use, modify or adapt this
	 * method as you see fit.
	 *
	 * Returns a negative value if <code>b</code> represents a time *before*
	 * <code>a</code>.
	 *
	 * @param b
	 * @return
	 */
	public static int getTimeDiff(LocalDateTime a, LocalDateTime b) {
		return (int) ChronoUnit.SECONDS.between(a, b);
	}

	/**
	 * Returns a string formatted as "00:00:00" based on the given number of
	 * seconds. For example, if seconds = 4284, it would return "01:11:24" (1 hour,
	 * 11 minutes, 24 seconds).
	 *
	 * @param seconds
	 * @return
	 */
	public static String formatTime(int seconds) {
		int hours = seconds / 3600;
		seconds -= hours * 3600;
		int minutes = seconds / 60;
		seconds -= minutes * 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public static void main(String args[]) {
		//TODO: implement

		//an example of how to convert an ISO8601 string to a LocalDateTime:
		String s = "2024-07-27T10:36:48";
		LocalDateTime ldt = LocalDateTime.of(s);
	}

}
