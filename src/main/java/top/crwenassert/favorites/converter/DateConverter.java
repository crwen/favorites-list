package top.crwenassert.favorites.converter;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter {

    private static final String dataFormat = "yyyy-MM-dd";
    private static SimpleDateFormat df = new SimpleDateFormat(dataFormat);

    public static Date convert(String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date =  df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convert(Date date) {
        if (date == null)
            return null;
        return date.toString();
    }

}