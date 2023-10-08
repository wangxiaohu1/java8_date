package test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;

/**
 * LocalTimeTest test
 * 
 * @author wangxiaohu
 * @version $Id: LocalTimeTest.java, v 0.1 2019年6月17日 下午2:14:45 wangxiaohu Exp $
 */
public class LocalTimeTest {
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        /*
         1、LocalTime的说明：
            1）时间对象，只包含时间精度
         2、api分类介绍：
             1）获取时间：
                 static from(TemporalAccessor temporal)：从temporal获取LocalTime实例
                 static now()：从系统时钟获取默认时区的当前LocalTime实例
                 static now(Clock clock)：从指定的时钟获得LocalTime实例
                 static now(ZoneId zone)：从指定时区的系统时钟获取当前LocalTime实例
                 static of(int hour, int minute)：指定小时、分钟获得LocalTime实例
                 static of(int hour, int minute, int second)：从指定小时、分钟、秒钟获取LocalTime实例
                 static of(int hour, int minute, int second, int nanoOfSecond)：从指定小时、分钟、秒、纳秒获得LocalTime实例
                 static ofNanoOfDay(long nanoOfDay)：从一天的纳米值获得LocalTime实例
                 static ofSecondOfDay(long secondOfDay)：从一天的秒值获取LocalTime实例
                 parse(CharSequence text)：从文本字符串中获取LocalTime实例，默认的格式：12:01:02
                 parse(CharSequence text, DateTimeFormatter formatter)：从文本字符串中获取LocalTime实例，可以指定格式
             2）获取不同精度
                 get(TemporalField field)：获取指定字段的值作，返回int
                 getLong(TemporalField field)：获取指定字段的值作，返回long
                 getHour()：获取当日时间字段，返回int
                 getMinute()：获取分钟字段的值，返回int
                 getSecond()：获取秒字段的值，返回int
                 getNano()：获取纳秒字段的值，返回int
                 query(TemporalQuery query)：使用指定的查询进行查询的时间
             3）格式化：
                 format(DateTimeFormatter formatter)：使用指定格式的格式化，返回一个字符串
             4）调整时间：
                 adjustInto(Temporal temporal)：调整指定Temporal对象以使其具有与此对象相同的日期和时间
                 with(TemporalAdjuster adjuster)：将localtime实例按照adjuster调整，返回调整后的副本
                 with(TemporalField field, long newValue)：将localtime实例的指定字段设置成新值，返回调整后的副本
                 withHour(int hour)：将localtime实例的的小时字段设置成新值，返回调整后的副本
                 withMinute(int minute)：将localtime实例的的分钟字段设置成新值，返回调整后的副本
                 withSecond(int second)：将localtime实例的的秒字段设置成新值，返回调整后的副本
                 withNano(int nanoOfSecond)：：将localtime实例的的纳秒字段设置成新值，返回调整后的副本
             5）时间加减：
                 minus(long amountToSubtract, TemporalUnit unit)：从localtime实例减去指定数量的时间单元，返回localtime的副本
                 minus(TemporalAmount amountToSubtract)：从localtime实例减去指定的数量，返回localtime的副本
                 minusHours(long hoursToSubtract)：从localtime实例减去指定的小时数，返回localtime的副本
                 minusMinutes(long minutesToSubtract)：从localtime实例减去指定的分钟，返回localtime的副本
                 minusSeconds(long seconds)：从localtime实例减去指定的秒，返回localtime的副本
                 minusNanos(long nanos)：从localtime实例减去指定的纳秒，返回localtime的副本
                 plus(long amountToAdd, TemporalUnit unit)：从localtime实例加上指定数量的时间单元，返回localtime的副本
                 plus(TemporalAmount amountToAdd)：从localtime实例加上指定的数量，返回localtime的副本
                 plusHours(long hoursToAdd)：从localtime实例加上指定的小时数，返回localtime的副本
                 plusMinutes(long minutesToAdd)：从localtime实例加上指定的分钟，返回localtime的副本
                 plusSeconds(long seconds)：从localtime实例加上指定的秒，返回localtime的副本
                 plusNanos(long nanos)：从localtime实例加上指定的纳秒，返回localtime的副本
             6）时间比较：
                 compareTo(LocalTime other)：这个时间与另一个时间比较，大于other时返回1、小于other时返回1，相等返回0
                 equals(Object obj)：检查此时间是否等于另一个时间，返回boolean
                 isAfter(LocalTime other)：检查此时间是否在另一个时间(other)之后，返回boolean，在之后则返回true，在之前或者相等返回false
                 isBefore(LocalTime other)：检查此时间是否在另一个时间(other)之前，返回boolean，在之前则返回true，在之后或者相等返回false
                 until(Temporal endExclusive, TemporalUnit unit)：根据指定的单位计算到另一个时间的时间量
             7）衍生和转换成其他单位：
                atDate(LocalDate date)：将此时间与日期相结合以创建LocalDateTime
                atOffset(ZoneOffset offset)：将此时间与偏移时间相结合以创建OffsetDateTime
                toNanoOfDay()：提取时间为一天的纳秒数，返回long值，从0到24 * 60 * 60 * 1,000,000,000 - 1
                toSecondOfDay()：将时间提取为一天中的秒数，返回long值，从0到24 * 60 * 60 - 1
                truncatedTo(TemporalUnit unit)：将localtime实例使用unit单位精度截断时间，返回localtime副本
             8）测试
                 isSupported(TemporalField field)：检查是否支持指定的字段，返回boolean
                 isSupported(TemporalUnit unit)：检查指定的单元是否受支持，返回boolean
                 range(TemporalField field)：获取指定字段的有效值范围
                 
                  
        */
        
        //-----------------获取时间------------------
        //from
        /*
         1、from(TemporalAccessor temporal)：从temporal获取LocalTime实例
         */
        //案例
        println("-------from--------");
        println(LocalTime.from(LocalDateTime.parse("2019-06-01T12:01:02.555")));                         //12:01:02.555
        println(LocalTime.from(ZonedDateTime.parse("2019-06-01T12:01:02.555+08:00[Asia/Shanghai]")));    //12:01:02.555
        
        //now
        /*
         1、now：从系统时钟获取默认时区的当前LocalTime实例
         2、now(Clock clock)：从指定的时钟获得LocalTime实例
         3、now(ZoneId zoneId)：从指定时区的系统时钟获取当前LocalTime实例
         */
        //案例
        println("-------nwo--------");
        println(LocalTime.now());                               //118:54:47.692    现在时间是18:54:47.692，返回18:54:47.692 
            
        Clock clock = Clock.fixed(LocalDateTime.parse("2019-06-01T12:01:02").toInstant(ZoneOffset.ofHours(8)),  ZoneId.systemDefault());
        println(LocalTime.now(clock));                         //12:01:02
        
        println(LocalTime.now(ZoneId.systemDefault()));        //19:00:33.739       现在时间是19:00:33.739，返回19:00:33.739 
        println(LocalTime.now(ZoneOffset.ofHours(8)));         //19:01:57.892       现在时间是19:01:57.892，返回19:01:57.892
        println(LocalTime.now(ZoneOffset.ofHours(7)));         //18:01:57.892       现在北京时间是19:01:57.892，返回18:01:57.892
        
        //of
        /*
         1、of(int hour, int minute)：指定小时、分钟获得LocalTime实例，hour是范围是0~23，minute范围是0~59，都包含起始值和结尾值
         2、of(int hour, int minute, int second)：从指定小时、分钟、秒钟获取LocalTime实例，hour是范围是0~23，minute范围是0~59，second范围是0~59，都包含起始值和结尾值
         3、of(int hour, int minute, int second, int nanoOfSecond)：从指定小时、分钟、秒、纳秒获得LocalTime实例，hour是范围是0~23，minute范围是0~59，second范围是0~59，nanoOfSecond范围是0~999999999，都包含起始值和结尾值
         */
        //案例
        println("-------of--------");
        println(LocalTime.of(0, 00));   //00:00
        println(LocalTime.of(12, 01));  //12:01
        println(LocalTime.of(23, 59));  //23:59
        
        println(LocalTime.of(12, 01, 59));  //12:01:59
        
        println(LocalTime.of(12, 01, 59, 999)); //12:01:59.000000999
        
        //ofNanoOfDay
        /*
         1、ofNanoOfDay(long nanoOfDay)：从一天的纳米值获得LocalTime实例
         */
        //案例
        println("-------ofNanoOfDay--------");
        println(LocalTime.ofNanoOfDay(999999999));                  //00:00:00.999999999
        println(LocalTime.ofNanoOfDay(999999999 * 2));              //00:00:01.999999998
        println(LocalTime.ofNanoOfDay(1000000000));                 //00:00:01      1000000000就是1秒
        println(LocalTime.ofNanoOfDay(1000000000L * 10));           //00:00:10      1000000000 * 10就是10秒
        println(LocalTime.ofNanoOfDay(1000000000L * 10 * 6));       //00:01     1000000000 * 10 * 6 就是1分钟
        println(LocalTime.ofNanoOfDay(1000000000L * 10 * 6 * 60));  //01:00      1000000000 * 10 * 6 * 60 就是1小时
        
        //ofSecondOfDay
        /*
         1、ofSecondOfDay(long secondOfDay)：从一天的秒值获取LocalTime实例
         */
        //案例
        println("-------ofSecondOfDay--------");
        println(LocalTime.ofSecondOfDay(1));                        //00:00:01
        println(LocalTime.ofSecondOfDay(1 * 60));                   //00:01
        println(LocalTime.ofSecondOfDay(1 * 60 * 60));              //01:00
        
        //parse
        /*
         1、parse(CharSequence text)：从文本字符串中获取LocalTime实例，默认的格式：2018-06-20T12:01:02
         2、parse(CharSequence text, DateTimeFormatter formatter)：从文本字符串中获取LocalTime实例，可以指定格式
         */
        //案例
        println("-------parse--------");
        println(LocalTime.parse("12:01:02"));                        //12:01:02
        println(LocalTime.parse("12:01:02.888"));                        //12:01:02.888
        println(LocalTime.parse("12:01:02.999", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));   //12:01:02.999
        println(LocalTime.parse("12.01.02.999", DateTimeFormatter.ofPattern("HH.mm.ss.SSS")));   //12:01:02.999
        
        //-----------------获取不同的精度------------------
        //get
        /*
         1、get(TemporalField field)：获取指定字段的值作，返回int
         */
        //案例
        println("-------get--------");
        println(LocalTime.parse("12:01:02.988").get(ChronoField.HOUR_OF_DAY));          //12
        println(LocalTime.parse("17:01:02.988").get(ChronoField.HOUR_OF_AMPM));         //5
        println(LocalTime.parse("17:01:02.988").get(ChronoField.MINUTE_OF_HOUR));       //1   获取这个小时0~59内的分钟数
        println(LocalTime.parse("17:01:02.988").get(ChronoField.MINUTE_OF_DAY));        //1021   获取这个时间点处于当天的第多少分钟
        println(LocalTime.parse("17:01:02.988").get(ChronoField.SECOND_OF_MINUTE));     //2   获取这个分钟的0~59内的秒数
        println(LocalTime.parse("17:01:02.988").get(ChronoField.SECOND_OF_DAY));        //61262   获取这个时间点处于当天的第多少秒
        
        //getLong
        /*
         1、getLong(TemporalField field)：获取指定字段的值作，返回long
         */
        //案例
        println("-------getLong--------");
        println(LocalTime.parse("12:01:02.988").getLong(ChronoField.HOUR_OF_DAY));          //12
        println(LocalTime.parse("17:01:02.988").getLong(ChronoField.HOUR_OF_AMPM));         //5
        println(LocalTime.parse("17:01:02.988").getLong(ChronoField.MINUTE_OF_HOUR));       //1   获取这个小时0~59内的分钟数
        println(LocalTime.parse("17:01:02.988").getLong(ChronoField.MINUTE_OF_DAY));        //1021   获取这个时间点处于当天的第多少分钟
        println(LocalTime.parse("17:01:02.988").getLong(ChronoField.SECOND_OF_MINUTE));     //2   获取这个分钟的0~59内的秒数
        println(LocalTime.parse("17:01:02.988").getLong(ChronoField.SECOND_OF_DAY));        //61262   获取这个时间点处于当天的第多少秒
        
        //get时分秒
        /*
         1、getHour()：获取当日时间字段，返回int
         2、getMinute()：获取分钟字段的值，返回int
         3、getSecond()：获取秒字段的值，返回int
         4、getNano()：获取纳秒字段的值，返回int
         */
        //案例
        println("-------getHour--------");
        println(LocalTime.parse("13:01:02.988").getHour());          //13
        println(LocalTime.parse("13:01:02.988").getMinute());        //1
        println(LocalTime.parse("13:01:02.988").getSecond());        //2
        println(LocalTime.parse("13:01:02.988").getNano());          //988000000
        
        //query
        /*
         1、query(TemporalQuery query)：使用指定的查询进行查询的时间
         */
        //案例
        println("-------query--------");
        println(LocalTime.parse("13:01:02.988").query(TemporalQueries.precision()));          //13  返回精度，TemporalUnit
        
        //-----------------格式化------------------
        //format
        /*
         1、format(DateTimeFormatter formatter)：使用指定格式的格式化，返回一个字符串
         2、格式HH:mm:ss：24小时制
         3、格式hh:mm:ss：12小时制
         4、格式HH:mm:ss.SSSSSSSSS 时:分:秒:毫秒，其中毫秒的最大长度为9位，大写S
         */
        //案例
        println("-------format--------");
        println(LocalTime.parse("12:01:02.988").format(DateTimeFormatter.ofPattern("HH.mm.ss")));          //12.01.02
        println(LocalTime.parse("14:01:02.988").format(DateTimeFormatter.ofPattern("HH.mm.ss")));          //01.01.02
        println(LocalTime.parse("01:01:02.988").format(DateTimeFormatter.ofPattern("HH.mm.ss")));          //14.01.02
        println(LocalTime.parse("12:01:02.988").format(DateTimeFormatter.ofPattern("hh.mm.ss")));          //12.01.02
        println(LocalTime.parse("12:59:02.988").format(DateTimeFormatter.ofPattern("hh.mm.ss")));          //12.59.02
        println(LocalTime.parse("13:01:02.988").format(DateTimeFormatter.ofPattern("hh.mm.ss")));          //01.01.02
        println(LocalTime.parse("14:01:02.988").format(DateTimeFormatter.ofPattern("hh.mm.ss")));          //02.01.02
        println(LocalTime.parse("01:01:02.988").format(DateTimeFormatter.ofPattern("hh.mm.ss")));          //01.01.02
        println(LocalTime.parse("01:01:02").format(DateTimeFormatter.ofPattern("hh.mm.ss.SSSSSSSSS")));    //01.01.02.000000000
        println(LocalTime.parse("01:01:02.999").format(DateTimeFormatter.ofPattern("hh.mm.ss.SSSSSSSSS")));//01.01.02.999000000
        println(LocalTime.parse("01:01:02.000009").format(DateTimeFormatter.ofPattern("hh.mm.ss.SSS")));   //01.01.02.000
        
        //-----------------调整时间------------------
        //adjustInto
        /*
         1、adjustInto(Temporal temporal)：调整指定Temporal对象以使其具有与此对象相同的日期和时间
         */
        //案例
        println("-------adjustInto--------");
        LocalTime localTime = LocalTime.parse("12:01:02.988");
        //案例：将localTime 覆盖掉LocalDateTime的时分秒毫秒
        println(localTime.adjustInto(LocalDateTime.parse("2019-06-01T01:10:30")));          //2019-06-01T12:01:02.988
        localTime = LocalTime.parse("12:01:02");
        println(localTime.adjustInto(LocalDateTime.parse("2019-06-01T01:10:30.888")));      //2019-06-01T12:01:02
        //案例：
        localTime = LocalTime.parse("12:01:02");
        println(localTime.adjustInto(ZonedDateTime.now()));      //2019-06-20T12:01:02+08:00[Asia/Shanghai]
        
        //with
        /*
         1、with(TemporalAdjuster adjuster)：将localtime实例按照adjuster调整，返回调整后的副本
         2、with(TemporalField field, long newValue)：将localtime实例的指定字段设置成新值，返回调整后的副本
         */
        //案例
        println("-------with--------");
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.with(LocalTime.NOON));        //12:00
        println(localTime.with(LocalTime.parse("13:10:11")));        //13:10:11
        
        println(localTime.with(ChronoField.HOUR_OF_DAY,3));         //03:01:02.988    调整时字段的值
        println(localTime.with(ChronoField.MINUTE_OF_HOUR,10));         //12:10:02.988  调整分字段的值
        println(localTime.with(ChronoField.SECOND_OF_MINUTE,10));         //12:01:10.988  调整秒字段的值
        
        //调整时分秒纳秒
        /*
         1、withHour(int hour)：将localtime实例的的小时字段设置成新值，返回调整后的副本
         2、withMinute(int minute)：将localtime实例的的分钟字段设置成新值，返回调整后的副本
         3、withSecond(int second)：将localtime实例的的秒字段设置成新值，返回调整后的副本
         4、withNano(int nanoOfSecond)：：将localtime实例的的纳秒字段设置成新值，返回调整后的副本
         */
        //案例
        println("-------调整时分秒纳秒--------");
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.withHour(10));        //10:01:02.988
        println(localTime.withMinute(10));      //12:10:02.988
        println(localTime.withSecond(10));      //12:01:10.988
        println(localTime.withNano(10));        //12:01:02.000000010
        
        //-----------------时间加减------------------
        //minus
        /*
         1、minus(long amountToSubtract, TemporalUnit unit)：从localtime实例减去指定数量的时间单元，返回localtime的副本
         2、minus(TemporalAmount amountToSubtract)：从localtime实例减去指定的数量，返回localtime的副本
         */
        println("-------minus--------");
        //案例：根据指定数值和单位减去时间
        localTime = LocalTime.parse("12:01:02.988");
        //减去10小时
        println(localTime.minus(10,ChronoUnit.HOURS));          //02:01:02.988
        //减去10分钟
        println(localTime.minus(10,ChronoUnit.MINUTES));        //11:51:02.988
        
        //案例：根据指定数值和单位减去时间
        localTime = LocalTime.parse("12:01:02.988");
        //减去10小时
        println(localTime.minus(Duration.ofHours(10)));          //02:01:02.988
        
        
        //减去时分秒api
        /*
         1、minusHours(long hoursToSubtract)：从localtime实例减去指定的小时数，返回localtime的副本
         2、minusMinutes(long minutesToSubtract)：从localtime实例减去指定的分钟，返回localtime的副本
         3、minusSeconds(long seconds)：从localtime实例减去指定的秒，返回localtime的副本
         4、minusNanos(long nanos)：从localtime实例减去指定的纳秒，返回localtime的副本
         */
        println("-------减去时分秒api--------");
        //案例：根据指定数值和单位减去时间
        localTime = LocalTime.parse("12:01:02.988");
        //减去10小时
        println(localTime.minusHours(10));          //02:01:02.988
        //减去10分钟
        println(localTime.minusMinutes(10));          //11:51:02.988
        //减去70秒钟
        println(localTime.minusSeconds(70));          //11:59:52.988
        //减去988000000纳秒
        println(localTime.minusSeconds(988000000L));  //07:34:22.988
        
        println("-------plus--------");
        localTime = LocalTime.parse("12:01:02.988");
        //案例：增加10小时
        println(localTime.plus(10, ChronoUnit.HOURS));  //22:01:02.988
        //其他API和munus类似
        
        
        //-----------------时间比较------------------
        //compareTo
        /*
         1、compareTo(LocalTime other)：大于other时返回1、小于other时返回1，相等返回0
         */
        println("-------compareTo--------");
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.compareTo(LocalTime.parse("12:01:02.988")));            //0
        println(localTime.compareTo(LocalTime.parse("12:02:02.988")));            //-1
        println(localTime.compareTo(LocalTime.parse("12:00:02.988")));            //1
        
        //equals
        /*
         1、equals(Object obj)：检查此时间是否等于另一个时间，返回boolean
         */
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.equals(LocalTime.parse("12:01:02.988")));            //true
        println(localTime.equals(LocalTime.parse("12:02:02.988")));            //false
        println(localTime.equals(LocalTime.parse("12:00:02.988")));            //false
        
        //isAfter
        /*
         1、isAfter(LocalTime other)：检查此时间是否在另一个时间(other)之后，返回boolean，在之后则返回true，在之前或者相等返回false
         */
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.isAfter(LocalTime.parse("12:01:02.988")));            //false
        println(localTime.isAfter(LocalTime.parse("12:02:02.988")));            //false
        println(localTime.isAfter(LocalTime.parse("12:00:02.988")));            //true
        
        //isBefore
        /*
         1、isBefore(LocalTime other)：检查此时间是否在另一个时间(other)之前，返回boolean，在之前则返回true，在之后或者相等返回false
         */
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.isBefore(LocalTime.parse("12:01:02.988")));            //false
        println(localTime.isBefore(LocalTime.parse("12:02:02.988")));            //true
        println(localTime.isBefore(LocalTime.parse("12:00:02.988")));            //false
        
        //until
        /*
         1、until(Temporal endExclusive, TemporalUnit unit)：根据指定的单位计算到另一个时间的时间量
                    注意不满下一个单位的时候，返回0，例如相差59分钟，如果计算的精度是小时，则返回0小时
         */
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.until(LocalTime.parse("12:01:02.988"),ChronoUnit.MINUTES));            //0
        println(localTime.until(LocalTime.parse("12:00:02.988"),ChronoUnit.MINUTES));            //-1
        println(localTime.until(LocalTime.parse("13:00:02.988"),ChronoUnit.MINUTES));            //59
        println(localTime.until(LocalTime.parse("13:00:02.988"),ChronoUnit.HOURS));            //0
        
        
        //-----------------衍生和转换成其他单位------------------
        //衍生和转换成其他单位
        /*
         1、atDate(LocalDate date)：将此时间与日期相结合以创建LocalDateTime
         2、atOffset(ZoneOffset offset)：将此时间与偏移时间相结合以创建OffsetDateTime
         3、toNanoOfDay()：提取时间为一天的纳秒数，返回long值，从0到24 * 60 * 60 * 1,000,000,000 - 1
         4、toSecondOfDay()：将时间提取为一天中的秒数，返回long值，从0到24 * 60 * 60 - 1
         5、truncatedTo(TemporalUnit unit)：将localtime实例使用unit单位精度截断时间，返回localtime副本
         */
        println("-------compareTo--------");
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.atDate(LocalDate.parse("2019-06-20")));            //2019-06-20T12:01:02.988
        println(localTime.atOffset(ZoneOffset.ofHours(8)));                  //12:01:02.988+08:00
        println(localTime.toNanoOfDay());                                    //43262988000000
        println(localTime.toSecondOfDay());                                  //43262
        
        //案例：使用 秒阶段，精度保留到秒
        println(localTime.truncatedTo(ChronoUnit.SECONDS));                  //12:01:02
        //案例：使用 分钟阶段，精度保留到分
        println(localTime.truncatedTo(ChronoUnit.MINUTES));                  //12:01

        //-----------------时间支持的测试------------------
        //测试
        /*
         1、isSupported(TemporalField field)：检查是否支持指定的字段，返回boolean
         2、isSupported(TemporalUnit unit)：检查指定的单元是否受支持，返回boolean
         3、range(TemporalField field)：获取指定字段的有效值范围
         */
        println("-------compareTo--------");
        localTime = LocalTime.parse("12:01:02.988");
        println(localTime.isSupported(ChronoField.YEAR));            //false
        println(localTime.isSupported(ChronoUnit.YEARS));            //false
        println(localTime.range(ChronoField.HOUR_OF_DAY));            //0 - 23
        println(localTime.range(ChronoField.HOUR_OF_AMPM));            //0 - 11
        
        
    }
    
    
    private static void println(Object object){
        System.out.println(object);
    }
}
