package com.mall.common.util;

import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private static ThreadLocal<DateFormat> DATE_TIME =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    private static ThreadLocal<DateFormat> DATE_TIME_SIMPLE =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));
    private static ThreadLocal<DateFormat> DATE =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private static ThreadLocal<DateFormat> DATE_SIMPLE =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

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

    /**
     * yyyyMMdd
     */
    public static String getDateSimple() {
        return getDate(new Date(), false);
    }

    /**
     * yyyy-MM-dd
     */
    public static String getDate() {
        return getDate(new Date(), true);
    }

    private static String getDate(Date date, boolean delimiter) {
        if (delimiter) {
            return DATE.get().format(date);
        }
        return DATE_SIMPLE.get().format(date);
    }

    /**
     * yyyyMMddHHmmss
     */
    public static String getDateTimeSimple() {
        return getDateTime(new Date(), false);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        return getDateTime(new Date(), true);
    }

    private static String getDateTime(Date date, boolean delimiter) {
        if (delimiter) {
            return DATE_TIME.get().format(date);
        }
        return DATE_TIME_SIMPLE.get().format(date);
    }

    public static void main(String[] args) {
        // 20200218
        System.out.println(getDate());
        // 2020-02-18
        System.out.println(getDateSimple());
        // 20200218110016
        System.out.println(getDateTime());
        // 2020-02-18 11:00:16
        System.out.println(getDateTimeSimple());
    }

}
