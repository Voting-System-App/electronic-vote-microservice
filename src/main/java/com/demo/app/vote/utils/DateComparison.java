package com.demo.app.vote.utils;


import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DateComparison {
    public Date minusDays(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        return calendar.getTime();
    }
    public Date plusDays(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();
    }
    public String compareHours(LocalTime time){
        int flag = 0;
        List<String> group = Arrays.asList("A","B","C","D","E","F","G","H","I","J","Z");
        List<LocalTime> rangeStart = Arrays.asList(LocalTime.parse("07:00:00"),LocalTime.parse("08:00:00"),LocalTime.parse("09:00:00")
        ,LocalTime.parse("10:00:00"),LocalTime.parse("11:00:00"),LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"),
                LocalTime.parse("14:00:00"),LocalTime.parse("15:00:00"),LocalTime.parse("16:00:00"),LocalTime.parse("17:00:00"));
        List<LocalTime> rangeEnd = Arrays.asList(LocalTime.parse("08:00:00"),LocalTime.parse("09:00:00")
                ,LocalTime.parse("10:00:00"),LocalTime.parse("11:00:00"),LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"),
                LocalTime.parse("14:00:00"),LocalTime.parse("15:00:00"),LocalTime.parse("16:00:00"),LocalTime.parse("17:00:00"),LocalTime.parse("18:00:00"));
        for (int i = 0; i < 11; i++) {
            flag = !time.isBefore(rangeStart.get(i)) && time.isBefore(rangeEnd.get(i))?i:10;
        }
        System.out.println(group.get(flag));
        return group.get(flag);
    }
}
