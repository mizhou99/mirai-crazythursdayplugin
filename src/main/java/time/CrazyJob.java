package time;

import file.CrazyPluginManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CrazyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CrazyPluginManager.sendCrazyToGroup();
    }
}
