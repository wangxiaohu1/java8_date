package test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * DurationTest test
 * 
 * @author wangxiaohu
 * @version $Id: ClockTest.java, v 0.1 2019年6月17日 下午2:14:45 wangxiaohu Exp $
 */
public class DurationTest {
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        /*
         1、Duration的说明：
            1）处理两个时间之间的差值。关于时间差的计算，主要涉及到两个类，年月日的日期间差值的计算使用 Period 类足以，
                          而时分秒毫秒的时间的差值计算则需要使用Duration类
         2、api分类介绍：
                  
        */
        
        //-----------------获取时间差------------------
        //between
        /*
         1、between(Temporal startInclusive, Temporal endExclusive)：可通过该静态方法获取两个时间之间的差，两个参数分别代表一个时间
         */
        //案例
        LocalDateTime begin = LocalDateTime.parse("2019-06-17T15:00:00.000");
        LocalDateTime to = LocalDateTime.parse("2019-06-18T15:00:00.000");
        Duration duration = Duration.between(begin, to);
        println("时间差（天数）："+duration.toDays());      //输出 时间差（天数）：1
        
        //from
        /*
         1、from(TemporalAmount amount)：从一个时间量获取时长
         */
        duration = Duration.from(ChronoUnit.DAYS.getDuration());
        println("时间差（小时）：" + duration.toHours());   //输出 时间差（小时）：24
        
        //of
        /*
         1、of(long amount, TemporalUnit unit)：通过该方法创建，该方法接受一个时间段长度，和一个时间单位作为参数
         */
        //案例：下面这个案例将通过不同的时间单位获取1天的时差
        println("时间差：" + Duration.of(1, ChronoUnit.DAYS));      //输出 时间差：PT24H
        println("时间差：" + Duration.of(24, ChronoUnit.HOURS));    //输出 时间差：PT24H
        println("时间差：" + Duration.of(1440, ChronoUnit.MINUTES));//输出 时间差：PT24H
        
        //parse
        /**
         1、parse(CharSequence  text)：接收一个可以格式化的字符串，返回duratoin对象
         */
        //案例
        println("--------parse------");
        println(Duration.parse("PT24H"));           //PT24H    小时
        println(Duration.parse("PT24M"));           //PT24M    分钟
        println(Duration.parse("PT24.2S"));         //PT24.2S  秒
        
        
        //negated
        /*
         1、negated()：持续时间的副本，其长度为negated
         */
        //案例：
        println("--------negated------");
        println(Duration.ofDays(1).negated());           //PT-24H
        println(Duration.ofMinutes(3).negated());        //PT-3M
        
        //获取时间差的api
        /**
         * 1、除了通过上面的静态方法获取时差对象，Duration中还提供了一下一些静态方法
         *    ofDays(long s)：接收一个时长，方法内部固定了单位是天
         *    ofHours(long s)：接收一个时长，方法内部固定了单位是小时
         *    ofMinutes(long s)：接收一个时长，方法内部固定了单位是分钟
         *    ofSeconds(long s)：接收一个时长，方法内部固定了单位是秒
         *    ofMillis(long s)：接收一个时长，方法内部固定了单位是毫秒
         *    ofNanos(long s)：接收一个时长，方法内部固定了单位是纳秒
         *    ofSeconds((long s,long s2))：接收一个时秒的时长和纳秒的时长
         */
        //案例
        println("--------ofDays------");
        println("时间差：" + Duration.ofDays(5));       //5天
        println("时间差：" + Duration.ofHours(24));     //24小时
        println("时间差：" + Duration.ofMinutes(20));   //20分钟
        println("时间差：" + Duration.ofSeconds(10));   //10秒
        println("时间差：" + Duration.ofMillis(10));    //10毫秒
        println("时间差：" + Duration.ofNanos(10));     //10纳秒
        println("时间差：" + Duration.ofSeconds(10, 20));//10秒，+20纳秒
        //输出：
        //时间差：PT120H
        //时间差：PT24H
        //时间差：PT20M
        //时间差：PT10S
        //时间差：PT0.01S
        //时间差：PT0.00000001S
        //时间差：PT10.00000002S
        
        //abs
        /*
         1、abs：返回duration对象的副本对象，并且取决对着，范围值正数
         */
        //案例
        println("--------abs------");
        println(Duration.ofDays(-1).toHours());         //输出 -24
        println(Duration.ofDays(-1).abs().toHours());   //输出 24
        
        //get
        /*
         1、get(TemporalUnit unit)：获取所请求单元的值，入参只ChronoUnit.SECONDS，ChronoUnit.NANOS
            ChronoUnit.NANOS则是获取这“秒数内的纳秒数”
         */
        //案例
        println("--------get------");
        println(Duration.ofDays(1).get(ChronoUnit.SECONDS));    //输出 86400
        println(Duration.ofNanos(100).get(ChronoUnit.NANOS));   //输出 100
        println(Duration.ofMillis(1).get(ChronoUnit.NANOS));    //输出 1000000
        println(Duration.ofSeconds(10).get(ChronoUnit.NANOS));   //输出 0
        
        //getNano
        /**
         1、getNano()：获取此持续时间内秒数内的纳秒数，注意是“秒数内的纳秒数”
         */
        //案例
        println("--------getNano------");
        println(Duration.ofNanos(100).getNano());               //输出 100
        println(Duration.ofSeconds(10, 1000).getNano());        //输出 1000
        println(Duration.ofSeconds(10).getNano());              //输出0 
            
        //getSeconds
        /**
         1、getSeconds()：获取此持续时间内的秒数
         */
        //案例
        println("--------getSeconds------");
        println(Duration.ofNanos(100).getSeconds());               //输出 0
        println(Duration.ofSeconds(10, 1000).getSeconds());        //输出 10
        println(Duration.ofSeconds(10).getSeconds());              //输出10
        println(Duration.ofMillis(100).getSeconds());              //输出0
        println(Duration.ofMinutes(10).getSeconds());              //输出600
        println(Duration.ofHours(10).getSeconds());                //输出36000
        println(Duration.ofDays(1).getSeconds());                  //输出86400
        
        //其它获取不同形态的api
        /*
         1、通过Duration实例的方法，toDays、toHours、toMinutes、toMillis、toNanos可获取 不同精度的时差
         */
        //案例
        begin = LocalDateTime.parse("2019-06-17T15:00:00.000");
        to = LocalDateTime.parse("2019-06-18T15:00:00.000");
        duration = Duration.between(begin, to);
        println("时间差（天数）："+duration.toDays());      //输出 时间差（天数）：1
        println("时间差（小时）："+duration.toHours());     //输出 时间差（小时）：24
        println("时间差（分中）："+duration.toMinutes());   //输出 时间差（分中）：1440
        println("时间差（毫秒）："+duration.toMillis());    //输出 时间差（毫秒）：86400000
        println("时间差（纳秒）："+duration.toNanos());     //输出 时间差（毫秒）：86400000000000
        
        
        
        //-----------------时间加减--------------
        //addTo
        /*
         1、addTo(Temporal temporal)：给指定的temporal增加一个duration
         */
        //案例：在原始sourceDate基础上增加1天，并返回增加后的结果
        println("--------addTo------");
        LocalDateTime sourceDateTime = LocalDateTime.parse("2019-06-18T14:01:02.999");
        println(sourceDateTime);                            //2019-06-18T14:01:02.999
        println(Duration.ofDays(1).addTo(sourceDateTime));  //2019-06-19T14:01:02.999
        
        //案例：在原始sourceDate基础上增加10小时，并返回增加后的结果
        Instant sourceInstant = LocalDateTime.parse("2019-06-18T14:01:02.999").toInstant(ZoneOffset.ofHours(0));
        println(sourceInstant);                                 //2019-06-18T14:01:02.999Z
        println(Duration.ofHours(5).addTo(sourceInstant));      //2019-06-18T19:01:02.999Z
        
        //minus
        /*
         1、minus(Duration duration)：并减去指定的持续时间，返回一个duration对象的副本
         2、minus(long amountToSubtract, TemporalUnit unit)：接收一个数值和时间单位，减去指定的持续时间，返回一个duration对象的副本
         */
        //案例：
        println("--------minus------");
        println(Duration.ofDays(1).minus(Duration.ofHours(1)));             //PT23H
        println(Duration.ofHours(10).minus(Duration.ofHours(1)));           //PT9H
        println(Duration.ofHours(2).minus(Duration.ofMinutes(130)));        //PT-10M
        println(Duration.ofMinutes(120).minus(Duration.ofMinutes(10)));     //PT1H50M
        println(Duration.ofMinutes(120).minus(Duration.ofMinutes(150)));    //PT-30M
        
        println(Duration.ofDays(1).minus(1,ChronoUnit.HOURS));              //PT23H
        println(Duration.ofHours(10).minus(1,ChronoUnit.HOURS));            //PT9H
        println(Duration.ofHours(2).minus(130,ChronoUnit.MINUTES));         //PT-10M
        println(Duration.ofMinutes(120).minus(10,ChronoUnit.MINUTES));      //PT1H50M
        println(Duration.ofMinutes(120).minus(150,ChronoUnit.MINUTES));     //PT-30M
        
        //minusDays
        /*
         1、minusDays(long daysToSubtract)：在标准的24小时内减去指定的持续时间，返回一个duration对象的副本
         */
        //案例：
        println("--------minusDays------");
        println(Duration.ofDays(2).minusDays(2));              //PT0S
        println(Duration.ofDays(2).minusDays(1));              //PT24H
        println(Duration.ofDays(2).minusDays(3));              //PT-24H
        println(Duration.ofHours(10).minusDays(1));            //PT-14H
        
        //minusHours
        /*
         1、minusHours(long hoursToSubtract)：减去指定的持续时间(以小时为单位)，返回一个duration对象的副本
         */
        //案例：
        println("--------minusHours------");
        println(Duration.ofDays(2).minusHours(48));              //PT0S
        println(Duration.ofDays(2).minusHours(10));              //PT38H
        println(Duration.ofDays(2).minusHours(25));              //PT23H
        println(Duration.ofHours(10).minusHours(10));            //PT0S
        println(Duration.ofMinutes(60).minusHours(2));           //PT-1H
        
        //minusMinutes
        /*
         1、minusMinutes(long minutesToSubtract)：减去指定的持续时间(以分钟为单位)，返回一个duration对象的副本
         */
        //案例：
        println("--------minusMinutes------");
        println(Duration.ofDays(1).minusMinutes(1440));                 //PT0S
        println(Duration.ofDays(1).minusMinutes(1550));                 //PT-1H-50M
        println(Duration.ofDays(2).minusMinutes(1380));                 //PT25H
        
        //minusSeconds
        /*
         1、minusSeconds(long secondsToSubtract)：减去指定的持续时间(以秒为单位)，返回一个duration对象的副本
         */
        //案例：
        println("--------minusSeconds------");
        println(Duration.ofDays(1).minusSeconds(10000));                 //PT21H13M20S
        println(Duration.ofSeconds(1000).minusSeconds(100));             //PT15M
        
        //minusMillis
        /*
         1、minusMillis(long millisToSubtract)：减去指定的持续时间(以毫秒为单位)，返回一个duration对象的副本
         */
        //案例：
        println("--------minusMillis------");
        println(Duration.ofDays(1).minusMillis(10000));                  //PT23H59M50S
        println(Duration.ofMillis(100000).minusMillis(100));             //PT1M39.9S
        
        //minusNanos
        /*
         1、minusNanos(long nanosToSubtract)：减去指定的持续时间(以纳秒为单位)，返回一个duration对象的副本
         */
        //案例：
        println("--------minusNanos------");
        println(Duration.ofDays(1).minusNanos(10000));                 //PT23H59M59.99999S
        println(Duration.ofNanos(100000).minusNanos(100));             //PT0.0000999S
        
        //plus相关的api
        /*
         1、plus相关的api是minus上面相关的接口类似，plus的相关api是 加上一个持续时间，返回一个duration对象的副本
         2、接口有一下一些
             
         */
        //案例：
        println("--------plus相关的api------");
        println(Duration.ofDays(1).plus(Duration.ofDays(1)));           //PT48H
        println(Duration.ofDays(1).plus(1, ChronoUnit.DAYS));           //PT48H
        println(Duration.ofDays(1).plusDays(1));                        //PT48H
        println(Duration.ofDays(1).plusHours(1));                       //PT25H
        println(Duration.ofDays(1).plusMinutes(60));                    //PT25H
        println(Duration.ofDays(1).plusSeconds(3600));                  //PT25H
        println(Duration.ofDays(1).plusMillis(36000000));               //PT34H
        println(Duration.ofDays(1).plusNanos(10000));                   //PT24H0.00001S
        
        //subtractFrom
        /*
         1、subtractFrom(Temporal temporal)：减去次持续时间，返回duration对象的副本
         
         */
        //案例：
        println("--------multipliedBy------");
        LocalDateTime time1 = (LocalDateTime)Duration.ofDays(1).subtractFrom( LocalDateTime.parse("2019-04-04T12:01:02.555") ); // 减去1天
        LocalDateTime time2 = (LocalDateTime)Duration.ofHours(25).subtractFrom( LocalDateTime.parse("2019-04-04T12:01:02.555") ); // 减去25天
        println(time1);           //2019-04-03T12:01:02.555
        println(time2);           //2019-04-03T11:01:02.555

        //multipliedBy
        /*
         1、multipliedBy(long multiplicand)：在duration基础上乘以一个数量，返回duration对象的副本
         
         */
        //案例：
        println("--------multipliedBy------");
        println(Duration.ofDays(1).multipliedBy(3));           //PT72H
        println(Duration.ofMinutes(3).multipliedBy(3));        //PT9M


        
        //withNanos
        /*
         1、withNanos(int nanoOfSecond)：在duration基础上使用指定纳秒数，返回duration对象的副本，注意不是+，而是调整成指定的纳秒
         
         */
        //案例：
        println("--------withNanos------");
        println(Duration.ofDays(1).withNanos(3));           //PT24H0.000000003S
        println(Duration.ofNanos(2).withNanos(3));          //PT0.000000003S
        
        //-----------------比较------------------
        //compareTo(Duration duration)
        /*
         1、compareTo(Duration duration1)：将此对象和入参duration1对象进行比较，比duration1大返回1，比duration1小返回-1，相等返回0
         */
        //案例
        println("--------compareTo------");
        duration = Duration.ofDays(1);
        Duration duration1 = Duration.ofHours(5);
        println(duration.compareTo(duration1));     //输出 1
        println(duration1.compareTo(duration));     //输出 -1
        println(duration.compareTo(duration));      //输出 0
        
        //equals
        /*
         1、equals(Duration duration1)：将此对象和入参duration1对象进行比较，只要是最终等价军返回ture，例如 1days 和 1days的时间间隔比较fanhui true
            1days 和 24hours的时间间隔比较也返回true，
         */
        println("--------equals------");
        duration = Duration.ofDays(1);
        duration1 = Duration.ofHours(5);
        println(duration.equals(duration1));                //输出 fasle
        println(duration.equals(Duration.ofDays(1)));       //书册 true
        println(duration.equals(Duration.ofHours(24)));     //输出 true
        println(duration.equals(Duration.ofMinutes(1440)));  //输出 true
        
        //-----------------测试------------------
        //getUnits
        /*
         1、getUnits()：获取此持续时间支持的单位集
         */
        //案例
        println("--------getUnits------");
        println(Duration.ofDays(1).getUnits());         //输出 [Seconds, Nanos]
        
        //isNegative
        /**
         1、isNegative()：检查此持续时间是否为负，不包括零。负数返回true，0和正数返回false
         */
        //案例
        println("--------isNegative------");
        println(Duration.ofDays(-1).isNegative());        //输出 true
        println(Duration.ofDays(1).isNegative());         //输出 false
        println(Duration.ofDays(0).isNegative());         //输出 false
        println(Duration.ofSeconds(-100).isNegative());   //输出 true
        
        //isZero
        /**
         1、isZero()：检查此持续时间是否为零长度。为0返回true，非0返回false
         */
        //案例
        println("--------isZero------");
        println(Duration.ofDays(-1).isZero());        //输出 fasle
        println(Duration.ofDays(0).isZero());         //输出 true
        println(Duration.ofSeconds(100, 1).isZero()); //输出 false
        
        //-----------------其它------------------
        
    }
    
    
    private static void println(Object object){
        System.out.println(object);
    }
}
