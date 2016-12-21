package data.gather.db;

import data.gather.server.TencentNewService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by dell on 2016/12/20.
 */
public class TencentJob implements Job {

    //把要执行的操作，写在execute方法中
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        TencentNewService.getPage();
    }
}
