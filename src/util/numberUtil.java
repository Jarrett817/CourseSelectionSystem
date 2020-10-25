package util;

import java.util.Calendar;

public class numberUtil {
    public static String getSysYearLastNumber() {

        Calendar date = Calendar.getInstance();

        String year = String.valueOf(date.get(Calendar.YEAR));

        return year.substring(year.length() - 1, year.length());

    }

    public static String stuNumber() {
        String number = "";
        number = "31" + getSysYearLastNumber() + "0" + System.currentTimeMillis();
        return number;
    }

    public static String teacherNumber() {
        String number = "";
        number = getSysYearLastNumber() + System.currentTimeMillis();
        return number;
    }
}
