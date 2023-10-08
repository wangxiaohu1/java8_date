package test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
/**
 * 时间调节器
 * 
 * @author wangxiaohu
 * @version $Id: TemporalAdjustersTest.java, v 0.1 2019年5月22日 上午11:33:44 wangxiaohu Exp $
 */
public class TemporalAdjustersTest {
    public static void main(String[] args) {
        //获取这个月的第一个周1的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY)));//输出：2019-05-06
        //获取上个月的最后一个周1的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.dayOfWeekInMonth(0, DayOfWeek.MONDAY)));//输出：2019-04-29
        //获取这个月的导数第一个周1的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.dayOfWeekInMonth(-1, DayOfWeek.MONDAY)));//输出：2019-05-27

        //获取这个月第一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.firstDayOfMonth()));//输出：2019-05-01
        
        //获取下个月第一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.firstDayOfNextMonth()));//输出：2019-06-01
        
        //获取下个年第一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.firstDayOfNextYear()));//输出：2020-01-01
        
        //获取这年第一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.firstDayOfYear()));//输出：2019-01-01
        
        //获取这个月的第一个周1的时间,上面的dayOfWeekInMonth更灵活,可以定义第几周
        System.out.println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));//输出：2019-05-06
        
        //获取这个月最后一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.lastDayOfMonth()));//输出：2019-05-31
        
        //获取这年最后一天的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.lastDayOfYear()));//输出：2019-12-31
        
        //获取这个月的最后一个周1的时间,上面的dayOfWeekInMonth更灵活,可以定义第几周
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)));//输出：2019-05-27
        
        //获取下一个符合周1的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.next(DayOfWeek.MONDAY)));//输出：2019-05-27
        //获取上一个符合周1的日历日期
        println(LocalDate.parse("2019-05-22").with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));//输出：2019-05-20
    
        //获取下一个符合周1的日历日期，如果当前日期符合，则返回当前日期
        println(LocalDate.parse("2019-05-20").with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));//输出：2019-05-20
        //获取上一个符合周1的日历日期，如果当前日期符合，则返回当前日期
        println(LocalDate.parse("2019-05-20").with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));//输出：2019-05-20
        
        //使用自定义TemporalAdjuster
        println(LocalDate.parse("2019-05-24").with(new MyTemporalAdjuster()));
    }
    
    private static void println(Object obj){
        System.out.println(obj);
    }
}

//自定义TemporalAdjuster
class MyTemporalAdjuster implements TemporalAdjuster{

    @Override
    public Temporal adjustInto(Temporal temporal) {
        int addDays=0;
        DayOfWeek work = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        if (work.equals(DayOfWeek.FRIDAY)) { //周5
            addDays=3;
        }else if(work.equals(DayOfWeek.SATURDAY)){ // 周6
            addDays=2;
        }else {
            addDays=1;
        }
        return temporal.plus(addDays, ChronoUnit.DAYS);
    }
}
    

