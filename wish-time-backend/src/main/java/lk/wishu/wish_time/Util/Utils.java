package lk.wishu.wish_time.Util;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class Utils {
    private static String RESOURCE_DIR_PATH = "src/main/resources/";

    public static LocalDateTime formateDate(LocalDateTime dateTime) {
       LocalDate date =  dateTime.toLocalDate();
         return  LocalDateTime.parse(date.toString(),new DateTimeFormatterFactory().createDateTimeFormatter());
    }

    public static void initUserDir(String username){
        File dir = new File(RESOURCE_DIR_PATH+username);
        dir.mkdirs();
        initUserLog(username);
    }

   public static void initUserLog(String username){
        File file = new File(RESOURCE_DIR_PATH+username+"/logger/sign-inlog.txt");
        file.mkdirs();
   }

   public static void logSignIn(String username){
        File file = new File(RESOURCE_DIR_PATH+username+"/logger/sign-inlog.txt");
   }
}
