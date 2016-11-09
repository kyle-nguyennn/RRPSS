package Helper;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Nguyen Dang Duy Nghia
 *
 */
public class TimeHandler {
	TimeHandler(){}
	/**
	 * check whether a LocalTime instance is in AM session or PM session
	 * @param time
	 * @return
	 */
	public static String whatSession(LocalTime time){
		LocalTime AMfirst = Time.valueOf("11:00:00").toLocalTime();
		LocalTime AMsecond = Time.valueOf("15:00:00").toLocalTime();
		LocalTime PMfirst = Time.valueOf("18:00:00").toLocalTime();
		LocalTime PMsecond = Time.valueOf("22:00:00").toLocalTime();
		if (time.compareTo(AMfirst)>=0 && time.compareTo(AMsecond)<=0)
			return "AM";
		else if (time.compareTo(PMfirst)>=0 && time.compareTo(PMsecond)<=0)
			return "PM";
		else return null;
	}
	/**
	 * check a time if it is valid to make a reservation
	 * i.e: not more than 30 days from now,
	 * must be in either AM or PM session,
	 * and of course must be in the future
	 * @param time
	 * @return
	 */
	public static boolean isValidToBook(LocalDateTime time){
		return (whatSession(time.toLocalTime())!=null) &&
				LocalDateTime.now().compareTo(time) < 0 &&
				Duration.between(LocalDateTime.now(), time).toDays() <= 30;
	}
}
