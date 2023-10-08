package test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
/**
 * 瞬时实例 Instant 测试
 * 
 * @author wangxiaohu
 * @version $Id: InstantTest.java, v 0.1 2019年2月28日 下午8:09:18 wangxiaohu Exp $
 */
public class InstantTest {
    
    public static void main(String[] args) {
        Instant instant = Instant.now();
        
        println("从1970-01-01 00:00:00到现在这1秒的毫秒数：" + System.currentTimeMillis());         //从1970-01-01 00:00:00开始到现在的毫秒数， 输出1551355618419
        
        //------- 访问Instant的时间, 一个instant有两个域, 分别是举例 1970-01-01 00:00:00到现在这1秒的秒数、在当前一秒内的第几纳秒 
        println("从1970-01-01 00:00:00到现在这1秒的秒数    ：" + instant.getEpochSecond());           //从1970-01-01 00:00:00到当前这1秒的偏移量秒数，输出1551355618
        println("从1970-01-01 00:00:00到现在这1秒的毫秒数 ：" + instant.toEpochMilli());             //从1970-01-01 00:00:00开始到现在的毫秒数，这个和System.currentTimeMillis()一样， 输出1551355618419
        println("在当前这1秒的纳秒：" + instant.getNano());                  //772000000  1秒=1亿纳秒
        println("------------------");
        
        //------- Instant的计算 , Instant类有一些方法，可以用于获得另一Instant的值
        // plusSeconds() plusMillis() plusNanos() minusSeconds() minusMillis() minusNanos()
        Instant now = Instant.now();
        println("当前：         " + now);
        println("获取后3秒：" + now.plusSeconds(3));  //创建了一个3秒后的Instant
        println("获取前3秒：" + now.minusSeconds(3)); //创建了一个3秒前的Instant
        println("获取后8小时：" + now.plusMillis(TimeUnit.HOURS.toMillis(8))); //创建了一个8小时的Instant，也就是北京时间
        println("------------------");
        
        //------- 获取指定时区
        println(now.atZone(ZoneId.of("GMT")).toInstant()); //2019-02-28T10:37:31.091Z  获取北京时间
        println("------------------");
        
        //------- 计算时间偏移量
        println("北京时间：" + now.atOffset(ZoneOffset.ofHours(8)));//019-02-28T17:17:47.766+08:00  获取8个小时后的时间
        
        //------- 判断时间先后
        Instant now1 = Instant.now();
        Instant now2 = now1.plusSeconds(10);
        println("对比时间：" + now2.isAfter(now1));
        
        //------- 毫秒转换成 instant
        println(Instant.ofEpochMilli(System.currentTimeMillis())); // 毫秒转换成Instant 2019-02-28T12:40:39.219Z
        
    }
    
    private static void println(Object object){
        System.out.println(object);
    }
}
