package test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateTimeTest {
    public static void main(String[] args) {        
        //-------------------------创建localDateTime实例，默认精确到到毫秒，也可以指定精度到秒
        //now()
        println("根据当前系统时间创建实例：" + LocalDateTime.now());//输出 2019-06-10T14:23:09.806
        
        //parse
        println("指定日期时间创建实例：" + LocalDateTime.parse("2019-06-10T12:01:02"));//输出 2019-06-10T12:01:02
        println("指定日期时间创建实例：" + LocalDateTime.parse("20190610 12:01:02", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")));//输出：2019-06-10T12:01:02
        println("指定日期时间创建实例：" + LocalDateTime.parse("20190610 12:01:02.785", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSS")));//输出：2019-06-10T12:01:02.785
        
        //of
        println("指定日期时间创建实例：" + LocalDateTime.of(2019, 6, 10, 12, 01, 02)); //输出 2019-06-10T12:01:02
        println("指定日期时间创建实例：" + LocalDateTime.of(2019, Month.JULY, 10, 12, 01, 02));//输出 2019-07-10T12:01:02
        //of方法中传入6个参数，表示年月日时分秒。关于月份，既可以传入Month对象，也可以传入int值（当然1~12表示1年中12个自然月）。也可省略秒(传入5个参数)；也可以纳秒参数(传入7个参数)
        
        //-------------------------其它时间转换成LocalDateTime
        //Instant -> LocalDateTime
        println("Instant转换成日期时间：" + Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());//输出 当前时间 2019-06-10T14:26:06.063
        //long毫秒 -> LocalDateTime
        println("毫秒转换成日期时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));//输出 当前时间 2019-06-10T14:26:58.693
        println("毫秒转换成日期时间：" + LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()),ZoneId.of("Asia/Shanghai")));//输出 当前时间 2019-06-10T14:26:58.693
        
        //-------------------------LocalDateTime转换成其它时间
        //LocalDateTime -> Instant
        println("转换成Instant：" + LocalDateTime.parse("2019-06-10T12:01:02").toInstant(ZoneOffset.ofHours(8)));//输出 2019-06-10T04:01:02Z  ，获取减去8小时差后的时间，例如当前时间为2019-06-10T12:01:02，减去后变成2019-06-10T04:01:02Z
        println("转换成Instant：" + LocalDateTime.parse("2019-06-10T12:01:02").atZone(ZoneId.systemDefault()).toInstant());//输出 2019-06-10T04:01:02Z  ，获取减去默认时区小时差后的时间，例如当前时间为2019-06-10T12:01:02，处于东八区（8小时偏移量），减去后变成2019-06-10T04:01:02Z
        //LocalDateTime -> 秒(10位)
        println("转换成秒：" + LocalDateTime.parse("2019-06-10T12:01:02").toEpochSecond(ZoneOffset.ofHours(8)));//输出 1560139262 ，即当前时区所在的时间2019-06-10T12:01:02
        //LocalDateTime -> 毫秒(13位)
        println("转换成毫秒：" + LocalDateTime.parse("2019-06-10T12:01:02").toInstant(ZoneOffset.ofHours(8)).toEpochMilli());//输出1560139262000 ，即当前时区所在的时间2019-06-10T12:01:02，13位毫秒数,相当于System.currentTimeMillis()
        //LocalDateTime -> Date
        println("转换成Date" + Date.from(LocalDateTime.parse("2019-06-10T12:01:02").atZone(ZoneId.systemDefault()).toInstant()));//输出 DateMon Jun 10 12:01:02 CST 2019 ，即当前时区所在的时间2019-06-10T12:01:02
        //LocalDateTime -> LocalDate
        println("转换成LocalDate：" + LocalDateTime.parse("2019-06-10T12:01:02").toLocalDate());//输出 2019-06-10
        //LocalDateTime -> LocalTime
        println("转换成LocalTime：" + LocalDateTime.parse("2019-06-10T12:01:02").toLocalTime());//输出 12:01:02
        //LocalDateTime -> OffsetDateTime
        println(LocalDateTime.parse("2019-06-10T12:01:02").atOffset(ZoneOffset.ofHours(2)));//输出   2019-06-10T12:01:02+02:00
        //LocalDateTime -> ZoneDateTime
        println(LocalDateTime.parse("2019-06-10T12:01:02").atZone(ZoneId.systemDefault()));//输出   2019-06-10T12:01:02+08:00[Asia/Shanghai]
        
        
        //-------- 时间格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        println("格式化时间：" + dtf.format(LocalDateTime.parse("2019-06-10T12:01:02")));//输出 2019-06-10 12:01:02.000
        println("格式化时间：" + dtf.format(LocalDateTime.parse("2019-06-10T12:01:02.768")));//输出 2019-06-10 12:01:02.768
        
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        println("格式化时间：" + dtf.format(LocalDateTime.parse("2019-06-10T12:01:02")));//输出 2019-06-10 12:01:02
        println("格式化时间：" + dtf.format(LocalDateTime.parse("2019-06-10T12:01:02.768")));//输出 2019-06-10 12:01:02
        
        println("格式化时间：" + LocalDateTime.parse("2019-06-10T12:01:02.768").format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));//输出  2019-06-10 12:01:02.768
        
        //----------其它
        //truncatedTo(精度截断)
        //截断日期字段以后的精度，只保留到日期精度
        println("：" + LocalDateTime.parse("2019-06-10T12:01:02.768").truncatedTo(ChronoUnit.DAYS));//输出 2019-06-10T00:00
        //截断小时字段以后的精度，只保留到小时精度
        println("：" + LocalDateTime.parse("2019-06-10T12:01:02.768").truncatedTo(ChronoUnit.HOURS));//输出 2019-06-10T12:00
        
        
    }
    
    private static void println(Object object){
        System.out.println(object);
    }
}
