package test;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.Locale;

public class LocalDateTest {
    public static void main(String[] args) {
        
        //-------------日期创建
        //创建实例 LocalDate.now()，输出:2019-04-28
        LocalDate localDate = LocalDate.now();
        System.out.println("创建一个实例LocalDate.now() : " + localDate);
       
        //创建实例LocalDate.parse 输出:2019-04-29
        localDate = LocalDate.parse("2019-04-29");//yyyy-MM-dd 这个是默认的解析格式
        System.out.println("创建一个实例LocalDate.parse(str) : " + localDate);
        localDate = LocalDate.parse("20190429",DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("创建一个实例LocalDate.parse(str,DateTimeFormatter) : "+localDate);
        localDate = LocalDate.now(Clock.systemDefaultZone());
        System.out.println("创建一个实例LocalDate.now(Clock.systemDefaultZone()): " + localDate);
        localDate = LocalDate.now(ZoneId.systemDefault());
        System.out.println("创建一个实例LocalDate.now(ZoneId.systemDefault()): " + localDate);
        
        //使用 年份、月份、天，创建实例
        localDate = LocalDate.of(2019, 4, 29);
        System.out.println("使用 年份、月份、天，创建实例：" + localDate);//输出:2019-04-29
        
        //使用 年份、月份枚举、天，创建实例
        localDate = LocalDate.of(2019, Month.APRIL, 29);
        System.out.println("使用 年份、月份枚举、天，创建实例：" + localDate);//输出:2019-04-29
        
        //从一年和一年中的自然日，创建实例//
        localDate = LocalDate.ofYearDay(2019, 1);
        System.out.println("从一年和一年中的自然日，创建实例：" + localDate);//输出:2019-01-01
        
        //从纪元日计数中，创建实例
        localDate = LocalDate.ofEpochDay(1);
        System.out.println("从纪元日计数中，创建实例：" + localDate);//输出:1970-01-02
        
        
        
        //-------日期转换-----------
        //从瞬时时间Instant转换成LocalDate
        localDate = LocalDate.from(Instant.now().atZone(ZoneId.systemDefault()));
        System.out.println("LocalDate.from() : " + localDate);
        //传统Date转 LocalDate
        java.util.Date date = new java.util.Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("传统Date转 LocalDate" + date + " ->" + localDate);
        
        localDate = LocalDate.from(LocalDateTime.parse("2019-04-29T01:02:03"));
        localDate = LocalDate.from(OffsetDateTime.parse("2019-04-29T01:02:03+08:00"));
        localDate = LocalDate.from(ZonedDateTime.parse("2019-04-29T01:02:03+08:00[Asia/Shanghai]"));
        
        
        //-------------日期合成--------------------------------
        //将指定的时态对象(入参对象)调整为与此对象具有相同的日期
        //原始：2019-03-08T01:02:03 叠加时态：2019-04-29
        //最终：2019-04-29T01:02:03
        LocalDateTime sourceDateTime = LocalDateTime.parse("2019-03-08T01:02:03");
        LocalDate adjustInfoLocalDate = LocalDate.parse("2019-04-29");
        Temporal finallyLocalDateTime = adjustInfoLocalDate.adjustInto(sourceDateTime);
        System.out.println("原始：" + sourceDateTime + " 叠加时态：" +adjustInfoLocalDate);
        System.out.println("最终：" + finallyLocalDateTime);
        
        localDate = LocalDate.parse("2019-04-29");
        //将此日期与午夜时间相结合，以在此日期开始时创建LocalDateTime，输出：2019-04-29T00:00
        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println("localDate.atStartOfDay()：" + localDateTime);
        
        // 将次日期和时区规则相结合，创建时区最早的有效分区日期时间ZonedDateTime ，输出：019-04-29T00:00+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        System.out.println("localDate.atStartOfDay(ZoneId.systemDefault())：" + zonedDateTime);
        
        //将此日期和时间相结合，创建普通的时态localDateTime，输出：2019-04-29T10:36:18.289
        LocalTime localTime = LocalTime.parse("10:36:18.289");
        localDate = LocalDate.parse("2019-04-29");
        localDateTime = localDate.atTime(localTime);
        System.out.println("localDate和localTime组合成localDateTime：" + localDateTime);
        
        //将此日期和小时，分钟相结合，创建localDateTime，输出：2019-04-29T05:20
        localDate = LocalDate.parse("2019-04-29");
        localDateTime = localDate.atTime(5, 20);
        System.out.println("使用时间+分钟 和localDate组合成localDateTime：" + localDateTime);
        
        //将此日期和小时，分钟，秒相结合，创建localDateTime，输出：2019-04-29T05:20:40
        localDateTime = localDate.atTime(5, 20, 40);
        System.out.println("使用时间+分钟+秒 和localDate组合成localDateTime：" + localDateTime);
        
        //将此日期和小时+分钟+秒+纳秒结合，创建localDateTime，输出：2019-04-29T05:20:40.000000013
        localDateTime = localDate.atTime(5, 20, 40, 13);
        System.out.println("使用时间+分钟+秒+纳秒 和localDate组合成localDateTime：" + localDateTime);
        
        //将此日期和offsetTime结合，创建OffserDateTime，输出：2019-04-29T10:38:54.057+08:00
        localDate = LocalDate.parse("2019-04-29");
        OffsetTime offsetTime = OffsetTime.parse("10:38:54.057+08:00");
        OffsetDateTime offsetDateTime = localDate.atTime(offsetTime);
        System.out.println("localDate和offsetTime组成成OffserDateTime：" + offsetDateTime);
        
        //-------------日期加减--------------
        //在日期基础上加上一定天数
        localDate = LocalDate.parse("2019-04-29");
        LocalDate newLocalDate = localDate.plusDays(1l);
        System.out.println("在日期基础上加上一定天数：" + newLocalDate);//输出：2019-04-30
        //在日期基础上加上一定礼拜周期(1周7天)，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.plusWeeks(1l);
        System.out.println("在日期基础上加上一定礼拜数：" + newLocalDate);//输出：2019-05-06
        //在日期基础上加上一定月，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.plusMonths(1l);
        System.out.println("在日期基础上加上一定月：" + newLocalDate);//输出：2019-05-29
        //在日期基础上加上一定年，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.plusYears(1l);
        System.out.println("在日期基础上加上一定年：" + newLocalDate);//输出：2020-04-29
        
        //在日期基础上加上一定的根据自定义(TemporalAmount)生成的时间
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.plus(Period.ofDays(1));
        System.out.println("在日期基础上加上一定的根据自定义(TemporalAmount)生成的时间：" + newLocalDate);//输出：2018-04-30
        newLocalDate = localDate.plus(Period.ofMonths(1));
        System.out.println("在日期基础上加上一定的根据自定义(TemporalAmount)生成的时间：" + newLocalDate);//输出：2018-05-29
        
        //在日期基础上加上一定的根据自定义(TemporalUnit)生成的时间
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.plus(1l,ChronoUnit.DAYS);
        System.out.println("在日期基础上加上一定的根据自定义(TemporalUnit)生成的时间：" + newLocalDate);//输出：201-04-30
        
        
        //在日期基础上减去一定天数
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minusDays(1l);
        System.out.println("在日期基础上减去一定天数：" + newLocalDate);//输出：2019-04-28
        //在日期基础上减去一定礼拜周期(1周7天)，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minusWeeks(1l);
        System.out.println("在日期基础上减去一定礼拜数：" + newLocalDate);//输出：2019-04-22
        //在日期基础上减去一定月，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minusMonths(1l);
        System.out.println("在日期基础上减去一定月：" + newLocalDate);//输出：2019-03-29
        //在日期基础上减去一定年，返回新的日期
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minusYears(1l);
        System.out.println("在日期基础上减去一定年：" + newLocalDate);//输出：2018-04-29
        
        //在日期基础上减去一定的根据自定义(TemporalAmount)生成的时间
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minus(Period.ofDays(1));
        System.out.println("在日期基础上减去一定的根据自定义(TemporalAmount)生成的时间：" + newLocalDate);//输出：2018-04-28
        newLocalDate = localDate.minus(Period.ofMonths(1));
        System.out.println("在日期基础上减去一定的根据自定义(TemporalAmount)生成的时间：" + newLocalDate);//输出：2018-03-29
        
        //在日期基础上减去一定的根据自定义(TemporalUnit)生成的时间
        localDate = LocalDate.parse("2019-04-29");
        newLocalDate = localDate.minus(1l,ChronoUnit.DAYS);
        System.out.println("在日期基础上减去一定的根据自定义(TemporalUnit)生成的时间：" + newLocalDate);//输出：201-04-28
        
        
        //-------------日期调整--------------
        //按月调整日期, 使用Month枚举
        localDate = LocalDate.parse("2019-05-05");
        LocalDate localDate2 = localDate.with(Month.JULY);//调整月份为7月
        System.out.println("调整日期：" + localDate2);//输出：2019-07-05
        
        
        //按照指定固定的增加量调整日期，使用TemporalAdjusters
        //调整为1年最后1天 TemporalAdjusters
        localDate2 = localDate.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("调整日期：" + localDate2);//输出：2019-12-31
        //调整为1个月最后1天
        localDate = LocalDate.parse("2019-05-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.lastDayOfMonth()));//输出：2019-05-31
        //调整为1年第1天
        localDate = LocalDate.parse("2019-05-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.firstDayOfYear()));//输出：2019-01-01
        //调整为1个月第1天
        localDate = LocalDate.parse("2019-05-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.firstDayOfMonth()));//输出：2019-05-01
        //调整为下1年的第1天
        localDate = LocalDate.parse("2019-05-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.firstDayOfNextYear()));//输出：2020-01-01
        //调整为下1月的第1天
        localDate = LocalDate.parse("2019-05-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.firstDayOfNextMonth()));//输出：2019-06-01
        
        //调整到当月内最后一个指定礼拜
        //星期一：Monday;；星期二：Tuesday；星期三：Wednesday；星期四：Thursday；星期五：Friday；星期六：Saturday；星期日：Sunday
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)));//输出：2019-04-29  (4月最后一个礼拜1是4-29日)
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));//输出：2019-04-26  (4月最后一个礼拜5是4-26日)
        
        //调整到当月内指定礼拜周期的指定礼拜，第1个参数是用于指定礼拜周期，第2个参数用于指定礼拜几
        //如果超出当前月份的礼拜周期，不会报错，而是自动累加成1一个月，依次累加
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY)));//输出：2019-04-01  （4月第1个个礼拜1是4-01）
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.dayOfWeekInMonth(5, DayOfWeek.MONDAY)));//输出：2019-04-29  （4月第5个个礼拜1是4-01）
        System.out.println("调整日期：" + localDate.with(TemporalAdjusters.dayOfWeekInMonth(6, DayOfWeek.MONDAY)));//输出：2019-04-29  （4月第6个个礼拜1是5-06）
        
        //创建一个新的日期，并将其值设定为日期调整后，第一个符合指定星期几要求的日期
        //4-05日礼拜5，指定获取第1礼拜5的日期，第1个符合的礼拜5是下个礼拜5，则返回下个礼拜5的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));//输出：2019-04-12
        //4-05日礼拜5，指定获取第1个礼拜6的日期，第1个符合的礼拜6是礼拜6，则返回礼拜6的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));//输出：2019-04-06
        //4-05日礼拜5，指定获取第1个礼拜1的日期，第1个符合的礼拜1是下礼拜1，则返回下个礼拜1的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));//输出：2019-04-08
        
        //创建一个新的日期，并将其值设定为日期调整后前，第一个符合指定星期几要求的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建上一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)));//输出：2019-03-29
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建上一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)));//输出：2019-03-30
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建上一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));//输出：2019-04-01
        
        //创建一个新的日期，并将其值设定为日期调整后，第一个符合指定星期几要求的日期，如果当前日期已经符合，直接返回相同的日期
        //4-05日礼拜5，指定获取第1礼拜5的日期，当前日期已经符合，直接回当前日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));//输出：2019-04-05
        //4-05日礼拜5，指定获取第1个礼拜6的日期，第1个符合的是礼拜6，则返回礼拜6的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)));//输出：2019-04-06
        //4-05日礼拜5，指定获取第1个礼拜1的日期，第1个符合的礼拜1是下礼拜1，则返回下个礼拜1的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));//输出：2019-04-08

        //创建一个新的日期，并将其值设定为日期调整前，第一个符合指定星期几要求的日期，如果当前日期已经符合，直接返回相同的日期
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("创建下一个符合礼拜几的日期：" + localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)));//输出：2019-04-05
        
        //with(TemporalField field, long newValue) :返回此日期的副本，并将指定的字段设置为新值
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.with(ChronoField.DAY_OF_WEEK, 7));//按照日期所在礼拜的，获取礼拜（1-7）的日期，输出：2019-04-07 
        System.out.println("调整日期：" + localDate.with(ChronoField.DAY_OF_MONTH, 3));//输出：2019-04-03
        System.out.println("调整日期：" + localDate.with(ChronoField.DAY_OF_YEAR, 1));//输出：2019-01-01
        System.out.println("调整日期：" + localDate.with(ChronoField.MONTH_OF_YEAR, 2));//输出：2019-02-05
        
        //withDayOfMonth(int dayOfMonth)：按每月自然日修改日期，返回此LocalDate的副本
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.withDayOfMonth(7));//输出：2019-04-07

        //withDayOfYear(int dayOfYear)：按每年自然日修改日期，返回此LocalDate的副本
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.withDayOfYear(7));//输出：2019-01-07
        
        //withMonth(int month)：按自然月(1-12)改日期月份，返回此LocalDate的副本
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.withMonth(3));//输出：2019-03-05
        
        //withYear(int year)：按年改日期年份，返回此LocalDate的副本
        localDate = LocalDate.parse("2019-04-05");
        System.out.println("调整日期：" + localDate.withYear(2018));//输出：2018-04-05

        
        //------------日期比较
        //两个日期比较的年/月/日差
        //注意：当对比的两个日期 年份不同，则只返回年份差 ，相当于 localDate.getYear() - compareToLocalDate.getYear()
        //注意：当对比的两个日期 年份相同，月份不同，则返回月份差
        //注意：当对比的两个日期 年份相同，月份相同，则返回日期差
        localDate = LocalDate.parse("2019-04-29");
        LocalDate compareToLocalDate = LocalDate.parse("2018-03-27");
        int compareResult = localDate.compareTo(compareToLocalDate);
        System.out.println("对比的差：" + compareResult);
        System.out.println("对比的差：" + (localDate.get(ChronoField.YEAR) - compareToLocalDate.getYear()) );
        
        //检查两个日期是否相等
        LocalDate localDate1 = LocalDate.parse("2019-04-29");
        localDate2 = LocalDate.parse("2019-04-29");
        System.out.println(localDate1.equals(localDate2));//输出true
        localDate2 = LocalDate.parse("2018-03-27");
        System.out.println(localDate.equals(localDate2));//输出  false
        
        localDate1 = LocalDate.parse("2019-04-29");
        localDate2 = LocalDate.parse("2019-04-29");
        System.out.println(localDate1.isEqual(localDate2));//输出true
        localDate2 = LocalDate.parse("2019-03-27");
        System.out.println(localDate1.isEqual(localDate2));//输出fasle
        
        //判断日期是否另一个日期之前
        localDate1 = LocalDate.parse("2019-04-29");
        localDate2 = LocalDate.parse("2019-04-30");
        System.out.println("判断日期是否另一个日期之前：" + localDate1.isBefore(localDate2));//输出：true
        System.out.println("判断日期是否另一个日期之前：" + localDate1.isBefore(localDate1));//输出：fasle
        System.out.println("判断日期是否另一个日期之前：" + localDate2.isBefore(localDate1));//输出：fasle
        
        //判断日期是否另一个日期之后
        localDate1 = LocalDate.parse("2019-04-29");
        localDate2 = LocalDate.parse("2019-04-30");
        System.out.println("判断日期是否另一个日期之后：" + localDate1.isAfter(localDate2));//输出：fasle
        System.out.println("判断日期是否另一个日期之后：" + localDate1.isAfter(localDate1));//输出：fasle
        System.out.println("判断日期是否另一个日期之后：" + localDate2.isAfter(localDate1));//输出：true
        
        
        //------------日期格式化------
        //localDate格式化成字符串，yyyy-MM-dd输出：2019-04-29 ；yyyyMMdd输出20190429
        String localDateStr = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("localDate格式化成字符串yyyy-MM-dd：" + localDateStr);
        localDateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("localDate格式化成字符串yyyyMMdd：" + localDateStr);
        
        
        //----------获取年月日字段------
        //获取当天所在的星期对象，返回DayOfWeek枚举，输出MONDAY
        localDate = LocalDate.parse("2019-04-29");
        DayOfWeek dayOfWeekEnum  =localDate.getDayOfWeek();
        System.out.println("获取当天的星期几对象：" + dayOfWeekEnum);
        
        //获取当天所在的星期第几天，输出1
        localDate = LocalDate.parse("2019-04-29");
        int dayOfWeen = localDate.getDayOfWeek().getValue();
        System.out.println("获取当天是星期几：" + dayOfWeen);
        
        //获取当天是当月的第几天，输出29
        localDate = LocalDate.parse("2019-04-29");
        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("获取当天是当月的第几天："+ dayOfMonth);
       
        //获取当天是当年的第几天，输出：119
        int dayOfYear = localDate.getDayOfYear();
        System.out.println("获取当天是当年的第几天：" + dayOfYear);
        
        //获取当天所在的1年的自然月枚举，返回Month枚举，输出APRIL
        localDate = LocalDate.parse("2019-04-29");
        Month month = localDate.getMonth();
        System.out.println("获取当天所在的1年1-12月的月份枚举：" + month);
        
        //获取日期所在的1年内的自然月数值，输出：4
        localDate = LocalDate.parse("2019-04-29");
        System.out.println("获取当天所在的1年1-12月的月份数值：" + localDate.getMonth().getValue());
        
        //获取日期所在的1年内的自然月数值，输出：4;4;4
        localDate = LocalDate.parse("2019-04-29");
        int monthValue = localDate.getMonthValue();
        int monthValue2 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int monthValue3 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("获取当月份数值：" + monthValue + ";" + monthValue2 + ";" + monthValue3);

        
        //获取年份，输出：2019;2019;2019
        localDate = LocalDate.parse("2019-04-29");
        int year = localDate.getYear();
        int year2 = localDate.get(ChronoField.YEAR);
        long year3 = localDate.getLong(ChronoField.YEAR);
        System.out.println("获取年份：" + year + ";" + year2 + ";" + year3);
        
        //获取当天在1年内1~365天的自然天，相当于localDate.getDayOfYear()，输出：119
        localDate = LocalDate.parse("2019-04-29");
        dayOfYear = localDate.get(ChronoField.DAY_OF_YEAR);
        System.out.println("获取当天在1年内1~365天的自然天：" + dayOfYear);
        
        //获取当天所在的当月1~31天的自然天，输出：29，相当于 localDate.getDayOfMonth();
        localDate = LocalDate.parse("2019-04-29");
        dayOfMonth  =localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("获取当天的当月1~31天的自然天：" + dayOfMonth);
        
        //获取当天所在的当周1~7天的自然天，输出：2
        localDate = LocalDate.parse("2019-04-29");
        int dayOfWeek = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("获取当天所在的当周1~7天的自然天：" + dayOfWeek);
        
        //获取当月所在1年1~12月所在的自然月，输出：4
        localDate = LocalDate.parse("2019-04-29");
        monthValue = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("获取当月所在1年1~12月所在的自然月：" + monthValue);
        
        //获取年，输出：2019
        localDate = LocalDate.parse("2019-04-29");
        year = localDate.get(ChronoField.YEAR);
        System.out.println("获取年：" + year);
        
        //获取纪元年，公元后输出当前年份，公元前输出 1+公元前的年份，也就是公元前年份~公元1年的间隔，例如公元前1年，输出：2
        localDate = LocalDate.parse("-0001-04-29");
        year = localDate.get(ChronoField.YEAR_OF_ERA);
        System.out.println("获取纪元年：" + year);
        
        //获取纪元，公元前输出0，公元后输出1，输出：1,
        localDate = LocalDate.parse("2019-04-29");
        year = localDate.get(ChronoField.ERA);
        System.out.println("获取纪元：" + year);

        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天，注意不是自然日所在的'礼拜几'
        for(int i=1;i<31;i++){
            String str = String.format("%02d", i);
            LocalDate localDateTemp = LocalDate.parse("2019-03-" + str);
            int number = localDateTemp.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
            System.out.println("获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天: " + localDateTemp + " : " + number);
        }
        //输入如下：
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：2019-03-01 : 1
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：2
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：3
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：4
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：5
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：6
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：7
        //获取每月自然日(1~31)在1个礼拜周期（1~7）处于第几天：1
        //...
        
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天，注意不是自然日所在的'礼拜几'
        for(int i=1;i<31;i++){
            String str = String.format("%02d", i);
            LocalDate localDateTemp = LocalDate.parse("2019-03-" + str);
            int number = localDateTemp.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR);
            System.out.println("获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: " + localDateTemp + " : " + number);
        }
        //输出如下：
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-01 : 4
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-02 : 5
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-03 : 6
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-04 : 7
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-05 : 1
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-06 : 2
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-07 : 3
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-08 : 4
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-09 : 5
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-10 : 6
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-11 : 7
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-12 : 1
        //获取每年自然日(1~365)在1个礼拜周期（1~7）处于第几天: 2019-03-13 : 2
        
        //获取每月自然日(1~31)处于一礼拜周期的几个周期，每7天为一个周期，以此累加：1~7为第1周，8~14为第2周，15~21为第3周，22~28为第4周，29~31为第5周
        for(int i=1;i<31;i++){
            String str = String.format("%02d", i);
            LocalDate localDateTemp = LocalDate.parse("2019-03-" + str);
            int number = localDateTemp.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
            System.out.println("获取每月自然日(1~31)在是处于一个月中的礼拜周期: " + localDateTemp + " : " + number);
        }
        //输出如下：
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-01 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-02 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-03 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-04 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-05 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-06 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-07 : 1
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-08 : 2
        //获取每月自然日(1~31)处于一礼拜周期的几个周期: 2019-03-09 : 2
        
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期，每7天为一个礼拜周期，以此累加：1~7为第1周，8~14为第2周，15~21为第3周，22~28为第4周，29~35为第5周...
        for(int i=1;i<31;i++){
            String str = String.format("%02d", i);
            LocalDate localDateTemp = LocalDate.parse("2019-03-" + str);
            int number = localDateTemp.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
            System.out.println("获取每年自然日(1~365)在处于礼拜周期的第几个周期: " + localDateTemp + " : " + number);
        }
        //输出如下：
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期: 2019-02-01 : 5
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期: 2019-02-02 : 5
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期: 2019-02-03 : 5
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期: 2019-02-04 : 5
        //获取每年自然日(1~365)在处于礼拜周期的第几个周期: 2019-02-05 : 6
        
        //---------计算日期差-------
        //计算此日期与另一个日期之间的间隔期间
        localDate1 = LocalDate.parse("2019-05-05");
        localDate2 = LocalDate.parse("2018-03-09");
        Period period = localDate1.until(localDate2);
        System.out.println("两个日期的间隔：" + period);//输出：P-1Y-1M-27D
        System.out.println("两个日期的相隔：年：" + period.getYears() + "；月：" + period.getMonths() + "；日：" + period.getDays());//输出：年：-1；月：-1；日：-27
        
        //根据指定的单位计算到另一个日期的时间量
        localDate1 = LocalDate.parse("2019-05-05");
        localDate2 = LocalDate.parse("2018-03-09");
        long untilDays  = localDate1.until(localDate2, ChronoUnit.DAYS);
        System.out.println("相隔的天数：" + untilDays);//输出：-422
        long untilMonths  = localDate1.until(localDate2, ChronoUnit.MONTHS);
        System.out.println("相隔的月份：" + untilMonths);//输出：-13
        
        //---------其他API------
        //获取纪元
        //获取纪元枚举(公元后)
        localDate = LocalDate.parse("2019-04-29");
        Era era = localDate.getEra();
        System.out.println(era);//输出：CE
        System.out.println(era.getValue());//输出：1
        int eraValue = localDate.get(ChronoField.ERA);
        System.out.println(eraValue);//输出：1
        //获取纪元枚举(公元后)
        localDate = LocalDate.parse("-2019-04-29");
        era = localDate.getEra();
        System.out.println(era);//输出：BCE
        System.out.println(era.getValue());//输出：0
        eraValue = localDate.get(ChronoField.ERA);
        System.out.println(eraValue);//输出：0
        
        
        //获取此日期的年表，即ISO日历系统 ，API无法理解 
        //TODO
        localDate = LocalDate.parse("2019-04-29");
        String calendarType = localDate.getChronology().getCalendarType();
        String id = localDate.getChronology().getId();
        String displayName = localDate.getChronology().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println("获取此日期IOS系统年历：" + calendarType + ";" + id + ";" + displayName);//输出：iso8601;ISO;ISO
        
        //根据ISO符号日历系统规则，检查年份是否为闰年
        localDate = LocalDate.parse("2019-04-29");
        boolean isLeapYear = localDate.isLeapYear();
        System.out.println("是否润年：" + isLeapYear);//输出：fasle
        
        //检查是否支持指定的字段
        localDate = LocalDate.parse("2019-04-29");
        boolean isSupported1 = localDate.isSupported(ChronoField.MINUTE_OF_DAY);
        boolean isSupported2 = localDate.isSupported(ChronoField.DAY_OF_MONTH);
        System.out.println("检查是否支持指定的字段：" + isSupported1 + ";" + isSupported2);//输出：fasle;true
        
        //获取日期所在月的天数
        localDate = LocalDate.parse("2019-05-05");
        int lengthOfMonth = localDate.lengthOfMonth();
        System.out.println("获取日期所在月份的天数：" + lengthOfMonth);//输出：31
        
        //获取日期所在年的天数
        localDate = LocalDate.parse("2019-05-05");
        int lengthOfYear = localDate.lengthOfYear();
        System.out.println("获取日期所在月份的天数：" + lengthOfYear);//输出：365
        
        //使用指定的查询查询此日期的属性
        //精度
        localDate = LocalDate.parse("2019-05-05");
        TemporalUnit temporalUnit = localDate.query(TemporalQueries.precision());
        System.out.println("使用指定的查询查询此日期的属性：" + temporalUnit);//输出：Days
        //年表
        localDate = LocalDate.parse("2019-05-05");
        Chronology chronology = localDate.query(TemporalQueries.chronology());
        System.out.println("使用指定的查询查询此日期的属性：" + chronology);//输出：ISO
        
        //获取指定字段的有效值的范围
        //年的自然天范围
        localDate = LocalDate.parse("2019-05-05");
        ValueRange valueRange = localDate.range(ChronoField.DAY_OF_YEAR);
        System.out.println("获取指定字段的有效值的范围：" + valueRange);//输出：1 - 365
        //月的自然天范围
        localDate = LocalDate.parse("2019-05-05");
        valueRange = localDate.range(ChronoField.DAY_OF_MONTH);
        System.out.println("获取指定字段的有效值的范围：" + valueRange);//输出：1 - 31
        
        //将此日期转换为大纪元日
        localDate = LocalDate.parse("2019-05-05");
        long epochDay = localDate.toEpochDay();
        System.out.println("大纪元日：" + epochDay);
        
        
    }
    
}
