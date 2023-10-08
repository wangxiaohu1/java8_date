package test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * Clock test
 * 
 * @author wangxiaohu
 * @version $Id: ClockTest.java, v 0.1 2019年6月17日 下午2:14:45 wangxiaohu Exp $
 */
public class ClockTest {
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        //--------------获取时钟------------------
        //fixed
        //返回指定时刻的时钟
        println("返回指定时刻的时钟：" + Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault()));//输出 FixedClock[1970-01-01T01:00:00Z,Asia/Shanghai]
        println("返回指定时刻的时钟：" + Clock.fixed(LocalDateTime.parse("2019-06-10T12:01:02").toInstant(ZoneOffset.ofHours(0)),  ZoneId.systemDefault()));//输出 FixedClock[2019-06-10T12:01:02Z,Asia/Shanghai]
        
        //systemDefaultZone
        //获取系统默认时区的当前实时时钟时间
        println("返回指定时刻的时钟：" + Clock.systemDefaultZone());//输出 SystemClock[Asia/Shanghai]
        
        //systemUTC
        //获取系统默认时区的当前实时时钟时间
        println("返回指定时刻的时钟：" + Clock.systemUTC());//输出 SystemClock[Z]
        
        //system
        //获取指定时区的当前实时时钟时间
        println("返回指定时刻的时钟：" + Clock.system(ZoneId.of("Asia/Shanghai")));//输出 SystemClock[Asia/Shanghai]
        println("返回指定时刻的时钟：" + Clock.system(ZoneId.of("Asia/Tokyo")));//输出 SystemClock[Asia/Tokyo]
        
        //withZone
        //获取时区的此时钟的副本
        Clock clock1 = Clock.systemUTC();
        Clock clock2 = clock1.withZone(ZoneId.systemDefault());
        Clock clock3 = clock1.withZone(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(8)));
        Clock clock4 = clock1.withZone(ZoneId.of("GMT+8"));
        println("原始时钟：" + clock1);
        println("指定时区的时钟副本clock2：" + clock2);
        println("指定时区的时钟副本clock3：" + clock3);
        println("指定时区的时钟副本clock4：" + clock4);
        println("clock1 to LocalDateTime：" + LocalDateTime.now(clock1));
        println("clock2 to LocalDateTime：" + LocalDateTime.now(clock2));
        println("clock3 to LocalDateTime：" + LocalDateTime.now(clock3));
        println("clock4 to LocalDateTime：" + LocalDateTime.now(clock4));
        //输出
        /*
                原始时钟：SystemClock[Z]
                指定时区的时钟副本clock2：SystemClock[Asia/Shanghai]
                指定时区的时钟副本clock3：SystemClock[GMT+08:00]
                指定时区的时钟副本clock4：SystemClock[GMT+08:00]
         clock1 to LocalDateTime：2019-06-17T09:29:32.065
         clock2 to LocalDateTime：2019-06-17T17:29:32.065
         clock3 to LocalDateTime：2019-06-17T17:29:32.065
         clock4 to LocalDateTime：2019-06-17T17:29:32.065
       */

        
        //--------------获取时钟不同的形态---------------
        //instant
        //获取时钟的当前时刻,注意是当前系统时区
        /**
         * 比如亚洲上海时区的时间为2019-06-17T16:43:11.397 ，Clock.systemDefaultZone().instant()读数为，2019-06-17T08:43:11.397Z
         */
        println("当前时区的时间：" + LocalDateTime.now().toString());//输出 2019-06-17T16:43:11.397
        println("当前时区的时刻：" + Clock.systemDefaultZone().instant());//输出 2019-06-17T06:33:59.034Z
        println("指定时刻和时区的的时刻：" + Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault()).instant());//输出 1970-01-01T01:00:00Z
        
        //Clock.millis
        //获取时钟当前的毫秒数
        println("获取时钟当前的毫秒数：" + Clock.systemDefaultZone().millis());//输出 1560753466241
        println("获取时钟当前的毫秒数：" + System.currentTimeMillis());//输出 1560753466241
        
        //Clock.getZone
        //获取创建时间的时区
        println("获取创建时间的时区：" + Clock.systemDefaultZone().getZone());//输出  Asia/Shanghai
        println("获取创建时间的时区：" + Clock.systemUTC().getZone());//输出 Z
        println("获取创建时间的时区：" + Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault()).getZone());//输出 Asia/Shanghai
        
        //------------------时钟精度-------------------------
        
        //tick
        /**
         * 1）tick的作用：获取一个设定好滴答间隔的时钟时间，这个“滴答”可以理解为 精度
         * 2）原始的clock：没有设置滴答间隔(tickDuration)的钟表的即时读数，由以下格式显示：时、分、秒(精确到小数点3位)
         * 3）钟表的滴答间隔(tickDuration)，规定了提供下一个读数的时间间隔。
         *    比如：滴答间隔为 1 秒的钟表，读数的分辨率就到 1 秒。滴答间隔为 5 秒的钟表，读数的"分辨率" 就到 5 秒。
         *    这里，5 秒的"分辨率"是指，当实际时间数据是 0 或 1、2、3、4 秒时，从它那里得到的读数都是 0 秒。
         *    当实际时间数据是 5 或 6、7、8、9 秒时，从它那里得到的读数都是 5 秒。
         * 4）用Clock类的静态方法 Clock tick(Clock baseClock, Duration tickDuration)，可以获得一座设定好滴答间隔的钟表。
         *    这座设定好滴答间隔(比如，duration 为 5 秒)的钟表，将提供秒读数 output =  ((int) s/(int)duration)*duration
         *    tickDuration不能使负数。此处 s 是  从 baseClock 得到的秒读数
         * 
         */
        clock1 = Clock.systemDefaultZone();
        clock2 = Clock.tick(clock1, Duration.ofSeconds(5));
        for(int i=0;i<12;i++){
            TimeUnit.MILLISECONDS.sleep(1000);//每隔1秒取样一次
            println("---");
            System.out.println(clock1.instant());//原始钟表打印时间戳
            System.out.println(clock2.instant() + " tick钟表");//tickClock钟表打印时间戳
        }
        //输出
        /*
        ---
        2019-06-17T09:05:54.810Z
        2019-06-17T09:05:50Z tick钟表
        ---
        2019-06-17T09:05:55.810Z
        2019-06-17T09:05:55Z tick钟表
        ---
        2019-06-17T09:05:56.811Z
        2019-06-17T09:05:55Z tick钟表
        ---
        2019-06-17T09:05:57.811Z
        2019-06-17T09:05:55Z tick钟表
        ---
        2019-06-17T09:05:58.812Z
        2019-06-17T09:05:55Z tick钟表
        ---
        2019-06-17T09:05:59.814Z
        2019-06-17T09:05:55Z tick钟表
        ---
        2019-06-17T09:06:00.814Z
        2019-06-17T09:06:00Z tick钟表
        ---
        2019-06-17T09:06:01.815Z
        2019-06-17T09:06:00Z tick钟表
        ---
        2019-06-17T09:06:02.816Z
        2019-06-17T09:06:00Z tick钟表
        ---
        2019-06-17T09:06:03.816Z
        2019-06-17T09:06:00Z tick钟表
        ---
        2019-06-17T09:06:04.817Z
        2019-06-17T09:06:00Z tick钟表
        ---
        2019-06-17T09:06:05.817Z
        2019-06-17T09:06:05Z tick钟表
        */
        
        println("---------------------");
        //tickSeconds
        //和tick类似，可以获得一座滴答间隔(tickDuration)设成 1 秒钟的钟表。
        clock1 = Clock.systemDefaultZone();
        clock2 = Clock.tickSeconds(ZoneId.of("GMT"));
        for(int i=0;i<3;i++){
            TimeUnit.MILLISECONDS.sleep(1000);//每隔1秒取样一次
            println("---");
            System.out.println(clock1.instant());//原始钟表打印时间戳
            System.out.println(clock2.instant() + " tick钟表");//tickClock钟表打印时间戳
        }
        //输出
        /*
        ---
        2019-06-17T09:10:52.797Z
        2019-06-17T09:10:52Z tick钟表
        ---
        2019-06-17T09:10:53.799Z
        2019-06-17T09:10:53Z tick钟表
        ---
        2019-06-17T09:10:54.800Z
        2019-06-17T09:10:54Z tick钟表
        */
        
        //tickMinutes
        //和tick类似，可以获得一座滴答间隔(tickDuration)设成 1 分钟的钟表，这座钟表，当baseClock 提供 0 至 不到第 1分钟的数据时，它提供的数据都是 0 分钟。
        
        
        //-------------------时钟加减
        
        /**
         * 1）offset有两个参数，
         *    第一个Clock对象：就是基于此时钟进行时间加减
         *    第二个参数是Duration对象：表示持续的时长，可通过Duration的静态方法获取，方法入参为正数代表累加时间(后移)，入参为负数代表减少时间(迁移)，如
         *    ofDays：天
         *    ofHours：小时
         *    ofMinutes：分钟
         *    ofSeconds：秒
         *    ofMillis：毫秒
         * 2）例如在clock1这个时钟基础上增加5小时：Clock.offset(clock1, Duration.ofHours(5))
         */
        //示例：在一个固定时钟的基础上，增加5小时
        clock1 = Clock.fixed(LocalDateTime.parse("2019-06-17T12:00:00").toInstant(ZoneOffset.ofHours(0)),ZoneId.systemDefault());
        clock2 = Clock.offset(clock1, Duration.ofHours(5));
        println("原始时钟       ：" + clock1.instant());//输出 2019-06-17T12:00:00Z
        println("增加后的时钟：" + clock2.instant());//输出 2019-06-17T17:00:00Z
        //示例：在一个固定时钟的基础上，减少24小时
        clock1 = Clock.fixed(LocalDateTime.parse("2019-06-17T12:00:00").toInstant(ZoneOffset.ofHours(0)),ZoneId.systemDefault());
        clock2 = Clock.offset(clock1, Duration.ofHours(-24));
        println("原始时钟       ：" + clock1.instant());//输出 2019-06-17T12:00:00Z
        println("增加后的时钟：" + clock2.instant());//输出 2019-06-16T12:00:00Z
        //示例：在一个固定时钟的基础上，24小时
        clock1 = Clock.fixed(LocalDateTime.parse("2019-06-17T12:00:00").toInstant(ZoneOffset.ofHours(0)),ZoneId.systemDefault());
        clock2 = Clock.offset(clock1, Duration.ofMinutes(30));
        println("原始时钟       ：" + clock1.instant());//输出 2019-06-17T12:00:00Z
        println("增加后的时钟：" + clock2.instant());//输出 2019-06-17T12:30:00Z
        
        
    }
    
    
    private static void println(Object object){
        System.out.println(object);
    }
}
