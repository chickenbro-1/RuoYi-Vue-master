package com.ruoyi.fuhang_booking.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class TimeUtil {

    public String transfertime() throws ParseException {
        String dateTime = "2023-07-22T10:00:00.000Z";
        dateTime = dateTime.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = format.parse(dateTime);
        String result = defaultFormat.format(time);
        log.info(result);
        return result;
    }


}
