package data.gather.db;

/**
 * Created by dell on 2016/12/20.
 */

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 调用任务的类
 *
 * @author lhy
 */
public class SchedulerUtil implements ServletContextListener {
//    public static void main(String[] args) {

    public static void openScheduler() {
        //通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
//      通过schedulerFactory获取一个调度器
            scheduler = schedulerfactory.getScheduler();
//       创建jobDetail实例，绑定Job实现类
//       指明job的名称，所在组的名称，以及绑定job类
            JobDetail job = JobBuilder.newJob(SinaJob.class).withIdentity("job1", "group1").build();
            JobDetail job1 = JobBuilder.newJob(TencentJob.class).withIdentity("job2", "group1").build();

//          每6小时执行一次
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/6 * * ?"))
                    .startNow().build();
            //每十秒
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
//                    .startNow().build();

//       把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job1, trigger);
//       启动调度
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        openScheduler();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static void main(String[] args) {
        openScheduler();
    }
}