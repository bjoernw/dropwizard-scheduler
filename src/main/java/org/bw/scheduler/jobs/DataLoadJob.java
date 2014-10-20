package org.bw.scheduler.jobs;

import com.netflix.hystrix.HystrixCommand;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by bjoernweidlich on 10/18/14.
 */
public class DataLoadJob extends QuartzJobBean
{

	private HystrixCommand command;

	public DataLoadJob(HystrixCommand command)
	{
		this.command = command;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	{

		command.execute();
	}
}
