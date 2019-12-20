package com.mall.common.util;

import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author DaoyuanWang
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);


    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    /**
     * 获取当前日期结束时间
     */
    public static Date getBeginTime(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZONE_ID).toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    /**
     * 获取当前日期开始时间
     */
    public static Date getEndTime(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZONE_ID).toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

}
