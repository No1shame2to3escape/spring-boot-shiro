package cn.realphago.springbootshiro.uitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 11:50
 */
public class DateFormatUtils {

    private static SimpleDateFormat dateFormat = null;

    public static String format(Date date) {
        return format(date, "yyyy/MM/dd HH:mm:ss");
    }

    public static String format(Date date, String formater) {
        dateFormat = new SimpleDateFormat(formater);
        return dateFormat.format(date);
    }

    public static String getTime() {
        return format(new Date());
    }

    public static String getOrderNum(String prefix) {
        return prefix + format(new Date(), "yyyyMMdd") + getTimeMills();
    }


    private static String getTimeMills() {
        return System.currentTimeMillis() + "";
    }

    public static Date parse(String source, String formater) throws ParseException {
        dateFormat = new SimpleDateFormat(formater);
        return dateFormat.parse(source);
    }

    public static Date parse(String source) throws ParseException {
        return parse(source, "yyyy/MM/dd HH:mm:ss");
    }


}
