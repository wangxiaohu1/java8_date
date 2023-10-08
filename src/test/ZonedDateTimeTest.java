package test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
    public static void main(String[] args) {        
        //-------------------------创建ZonedDateTime实例
        //创建当前时区时间
        println("当前时区时间：" + ZonedDateTime.now());//输出 2019-06-10T16:17:27.430+08:00[Asia/Shanghai]
        //创建指定时区时间
        println("指定时区时间：" + ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai")));//输出 2019-06-10T16:17:27.431+08:00[Asia/Shanghai]
        //所有可用的时区
        println("所有可用的时区：" + ZoneId.getAvailableZoneIds());//输出 [Asia/Aden,...]， 一共有599个时区，这里由于篇幅关系省略具体的时区
        
        
        
        
    }
    
    private static void println(Object object){
        System.out.println(object);
    }
}
