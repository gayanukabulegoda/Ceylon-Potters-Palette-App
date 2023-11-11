package lk.grb.ceylonPottersPalette.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {

    public static String dateNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    public static String yearNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }

    public static int getDays(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();

    }

    public static String monthNow() {
        LocalDate localDate = LocalDate.now();
        return String.valueOf(localDate.getMonth());
    }

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        System.out.println(dateFormat.format(new Date()));
        return dateFormat.format(new Date()) ;
    }
    public static int getCountOfDayForYear(int year ){
        return Year.of(year).length();
    }

    public static String timeNowRefresh() throws InterruptedException {

        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
        int totalIterations = Math.toIntExact(TimeUnit.HOURS.toSeconds(2));
        for (int sec = 0; sec < totalIterations; sec++) {
            timeNow();
            TimeUnit.SECONDS.sleep(1);
        }
        return dateFormat.format(new Date());
    }

}
