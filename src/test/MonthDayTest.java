package test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQueries;

/**
 * @author wangxiaohu
 * @version Id: MonthDayTest.java, v0.1 2020年06月08日 19:36:31 wangxiaohu Exp $
 */
public class MonthDayTest {

    public static void main(String[] args){
        /*
         1、MonthDay说明
            1）MonthDay类表示ISO-8601日历系统中的月和日，例如 12-03
         2、api分类介绍
            1）获取月日
                static MonthDay from(TemporalAccessor temporal)：从时态对象获取MonthDay的实例
                int get(TemporalField field)：获取此月-日指定字段的int值
                static MonthDay now()：在默认时区中从系统时钟获取当前月日实例
                static MonthDay now(Clock clock)：从指定的时钟获得当前月日实例
                static MonthDay now(ZoneId zone)：从指定时区的系统时钟获取月日实例
                static MonthDay of(int month, int dayOfMonth)：从指定的月份和日期数字构建一个月日实例
                static MonthDay of(Month month, int dayOfMonth)：从指定的月份枚举和日期数字构建一个月日实例
                static MonthDay parse(CharSequence text)：解析文本字符串并且构建一个月日实例
                static MonthDay parse(CharSequence text, DateTimeFormatter formatter)：按照指定格式解析文本字符串并且构建一个月日实例
            2）获取不同的精度
                int getDayOfMonth()：获取日期字段
                long getLong(TemporalField field)：获取指定字段的的值
                Month getMonth()：获取月份枚举
                int getMonthValue()：获取月份数字(1~12)
                R query(TemporalQuery query)：使用指定的查询进行查询
            3）调整月日：
                Temporal adjustInto(Temporal temporal)：调整指定的时间对象以具有此月-日
                LocalDate atYear(int year)：将此月-日与一年组合以创建LocalDate
                MonthDay with(Month month)：调整月-日实例的月份值，返回副本，不影响原来的实例
                MonthDay withMonth(int month)：调整月-日实例的月份值，返回副本，不影响原来的实例
                MonthDay withDayOfMonth(int dayOfMonth)：调整月-日实例的日期值，返回副本，不影响原来的实例

            4）比较月日：
                int compareTo(MonthDay other)：将这个月份的日期与另一个月份的日期进行比较
                boolean equals(Object obj)：检查此月-日是否等于另一个月-日
                boolean isAfter(MonthDay other)：检查此月-日是否在指定的月-日之后
                boolean isBefore(MonthDay other)：检查此月-日是否在指定的月-日之前
            5）格式化：
                String format(DateTimeFormatter formatter)：使用指定的格式化程序在本月创建格式
            6）测试：
                boolean isSupported(TemporalField field)：检查是否支持指定的字段
                boolean isValidYear(int year)：检查年份是否适用于本月
                ValueRange range(TemporalField field)：获取指定字段的有效值范围

     测试到get了



        //-----------------获取月日------------------
        //from
        /*
         * static MonthDay from(TemporalAccessor temporal)：从时态对象获取MonthDay的实例
         */
        println("-------from--------");
        LocalDateTime localDateTime = LocalDateTime.parse("2019-06-01T12:01:02.555");
        println(localDateTime);
        println("from结果：" + MonthDay.from(localDateTime));    //输出 from结果：--06-01

        //get
        /*
         * int get(TemporalField field)：获取此月-日指定字段的int值
         */
        println("-------get--------");
        MonthDay monthDay = MonthDay.parse("--12-20");
        println(monthDay);
        println("get结果：" + monthDay.get(ChronoField.DAY_OF_MONTH));    //输出 get结果：20
        println("get结果：" + monthDay.get(ChronoField.MONTH_OF_YEAR));    //输出 get结果：12

        //now
        /*
         * static MonthDay now()：在默认时区中从系统时钟获取当前月日实例
         * static MonthDay now(Clock clock)：从指定时钟获取月日实例
         * static MonthDay now(ZoneId zone)：从指定时区的系统时钟获取当前时间
         */
        println("-------now--------");
        println("now()：" + MonthDay.now());                                 //输出 now()：--06-09
        println("now(Clock)：" + MonthDay.now(Clock.systemUTC()));           //输出 now(Clock)：--06-09
        println("now(ZoneId)：" + MonthDay.now(ZoneId.systemDefault()));     //输出 now(ZoneId)：--06-09


        //of
        /*
         * static MonthDay of(int month, int dayOfMonth)：从指定的月份和日期数字构建一个月日实例
         * static MonthDay of(Month month, int dayOfMonth)：从指定的月份枚举和日期数字构建一个月日实例
         */
        println("-------of--------");
        println("of：" + MonthDay.of(12 ,15));                //输出 of：--12-15
        println("of：" + MonthDay.of(Month.DECEMBER, 15));           //输出 of：--12-15

        //parse
        /*
         * static MonthDay parse(CharSequence text)：解析文本字符串并且构建一个月日实例
         * static MonthDay parse(CharSequence text, DateTimeFormatter formatter)：按照指定格式解析文本字符串并且构建一个月日实例
         */
        println("-------parse--------");
        println("parse：" + MonthDay.parse("--12-15"));       //输出 parse：--12-15

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("--MM-dd");
        println("parse：" + MonthDay.parse("--12-15",dateTimeFormatter));                //输出 parse：--12-15






        //-----------------获取不同的精度------------------
        //getDayOfMonth
        /*
         * int getDayOfMonth()：获取日期字段
         */
        println("-------getDayOfMonth--------");
        monthDay = MonthDay.parse("--12-30");
        println("getDayOfMonth结果：" + monthDay.getDayOfMonth());    //输出 getDayOfMonth结果：30

        //getLong
        /*
         * long getLong(TemporalField field)：获取指定字段的的值
         */
        println("-------getLong--------");
        monthDay = MonthDay.parse("--12-30");
        println("getLong结果：" + monthDay.getLong(ChronoField.DAY_OF_MONTH));    //输出 getLong结果：30

        //getMonth
        /*
         * Month getMonth()：获取月份枚举
         */
        println("-------getMonth--------");
        monthDay = MonthDay.parse("--12-30");
        println("getMonth结果：" + monthDay.getMonth());    //输出 getMonth结果：DECEMBER

        //getMonthValue
        /*
         * int getMonthValue()：获取月份数字(1~12)
         */
        println("-------getMonthValue--------");
        monthDay = MonthDay.parse("--12-30");
        println("getMonthValue结果：" + monthDay.getMonthValue());    //输出 getMonthValue结果：12

        //query
        /*
         * R query(TemporalQuery query)：使用指定的查询进行查询
         */
        println("-------query--------");
        monthDay = MonthDay.parse("--12-30");
        println("query：" + monthDay.query(TemporalQueries.chronology()));    //输出 query：ISO

        //-----------------调整月日------------------
        //adjustInto
        /*
         Temporal adjustInto(Temporal temporal)：调整指定的时间对象以具有此月-日
         */
        println("-------adjustInto--------");
        localDateTime = LocalDateTime.parse("2019-06-01T12:01:02.555");
        println(localDateTime);
        localDateTime = (LocalDateTime)MonthDay.parse("--12-30").adjustInto(localDateTime);
        println("调整后的时间：" + localDateTime);     //输出 调整后的时间：2019-12-30T12:01:02.555

        //atYear
        /*
         * LocalDate atYear(int year)：将此月-日与一年组合以创建LocalDate
         */
        println("-------atYear--------");
        println("atYear组合结果：" + MonthDay.parse("--12-30").atYear(2019));    //输出 atYear组合结果：2019-12-30

        //with
        /*
         * MonthDay with(Month month)：调整月-日实例的月份值，返回副本，不影响原来的实例
         */
        println("-------with--------");
        println(MonthDay.parse("--12-30").with(Month.APRIL));    //输出 --04-30

        //withMonth
        /*
         * MonthDay withMonth(int month)：调整月-日实例的月份值，返回副本，不影响原来的实例
         */
        println("-------withMonth--------");
        println(MonthDay.parse("--12-30").withMonth(5));    //输出 --05-30

        //withDayOfMonth
        /*
         * MonthDay withDayOfMonth(int dayOfMonth)：调整月-日实例的日期值，返回副本，不影响原来的实例
         */
        println("-------withMonth--------");
        println(MonthDay.parse("--12-30").withMonth(8));    //输出 --08-30




        //-----------------比较月日------------------
        //compareTo
        /*
         * int compareTo(MonthDay other)：将这个月份的日期与另一个月份的日期进行比较
         */
        println("-------compareTo--------");
        println("比较结果：" + MonthDay.parse("--12-30").compareTo(MonthDay.parse("--12-20")));    //输出 比较结果：10
        println("比较结果：" + MonthDay.parse("--12-20").compareTo(MonthDay.parse("--12-30")));    //输出 比较结果：-10

        //equals
        /*
         * boolean equals(Object obj)：检查此月-日是否等于另一个月-日
         */
        println("-------equals--------");
        println("比较结果：" + MonthDay.parse("--12-30").equals(MonthDay.parse("--12-20")));    //输出 比较结果：false
        println("比较结果：" + MonthDay.parse("--12-20").equals(MonthDay.parse("--12-20")));    //输出 比较结果：true

        //isAfter
        /*
         * boolean isAfter(MonthDay other)：检查此月-日是否在指定的月-日之后
         */
        println("-------isAfter--------");
        println("比较结果：" + MonthDay.parse("--12-30").isAfter(MonthDay.parse("--12-20")));    //输出 比较结果：true

        //isBefore
        /*
         * boolean isBefore(MonthDay other)：检查此月-日是否在指定的月-日之前
         */
        println("-------isBefore--------");
        println("比较结果：" + MonthDay.parse("--12-30").isBefore(MonthDay.parse("--12-20")));    //输出 比较结果：false


        //-----------------格式化------------------
        //format
        /*
         * String format(DateTimeFormatter formatter)：使用指定的格式化程序在本月创建格式
         */
        println("-------format--------");
        monthDay = MonthDay.parse("--12-30");
        dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd");
        println("格式化结果：" + monthDay.format(dateTimeFormatter));    //输出 格式化结果：12/30


        //-----------------测试------------------
        //isSupported
        /*
         * boolean isSupported(TemporalField field)：检查是否支持指定的字段
         */
        println("-------isSupported--------");
        monthDay = MonthDay.parse("--12-30");
        println("结果：" + monthDay.isSupported(ChronoField.DAY_OF_MONTH));    //输出 结果：true

        //isValidYear
        /*
         * boolean isValidYear(int year)：检查年份是否适用于本月
         */
        println("-------isValidYear--------");
        monthDay = MonthDay.parse("--12-30");
        println("结果：" + monthDay.isValidYear(2016));    //输出 结果：true

        //range
        /*
         * ValueRange range(TemporalField field)：获取指定字段的有效值范围
         */
        println("-------range--------");
        monthDay = MonthDay.parse("--12-30");
        println("结果：" + monthDay.range(ChronoField.DAY_OF_MONTH));    //输出 结果：1 - 31

    }


    private static void println(Object object){
        System.out.println(object);
    }

}
