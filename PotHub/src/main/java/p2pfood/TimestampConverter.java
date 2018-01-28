package p2pfood;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TimestampConverter {
	public static String longToDateTimeString(long time){
		Timestamp ts = new Timestamp(time);
		Scanner sc = new Scanner(ts.toString().replace(" ", "T"));
		sc.useDelimiter(":");
		String toRet = sc.next()+":"+sc.next();
		sc.close();
		return toRet;
	}
	
	public static Timestamp stringToTimestamp(String timestamp) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		f.setLenient(false);
		return new Timestamp(f.parse(timestamp).getTime());
	}
}