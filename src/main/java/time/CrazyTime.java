package time;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;

public class CrazyTime {
    /**
     *定时任务
     */
    public static void sendCrazyTime()throws SchedulerException {
        DailyTimeIntervalScheduleBuilder dailyTimeIntervalScheduleBuilder = DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                .onDaysOfTheWeek(Calendar.THURSDAY)
                .startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(8, 0,0))
                .endingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(20,0,0))
                .withIntervalInHours(1);

        // CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 8-20 ? * 4 *");
        JobDetail jobDetail = JobBuilder.newJob(CrazyJob.class)
                .withIdentity("CrazyJob","sendCrazyGroupJob")
                .withDescription("oneHourSendCrazyJob")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("CrazyTrigger","sendCrazyGroupTrigger")
                .withDescription("oneHourSendCrazyTrigger")
                .withSchedule(dailyTimeIntervalScheduleBuilder)
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }
}
