package com.company.dua_lab;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TripTime {
    public static Integer resultTime(String a, String b){
        Integer minutes = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date1 = dateFormat.parse(a);
            Date date2 = dateFormat.parse(b);
            long milliseconds =  Math.abs(date2.getTime() - date1.getTime());
            minutes = (int) (milliseconds / (60 * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return minutes;
    }

}
