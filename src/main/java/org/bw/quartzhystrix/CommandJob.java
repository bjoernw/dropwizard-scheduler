package org.bw.quartzhystrix;

import com.netflix.hystrix.HystrixCommand;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Created by bjoernweidlich on 10/19/14.
*/
public class CommandJob implements Job
{
	private static final Logger log = LoggerFactory.getLogger(CommandJob.class);
	private HystrixCommand command;

	public CommandJob()
	{
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		HystrixCommand command = (HystrixCommand) dataMap.get("command");
		if (command != null)
			command.execute();
	}
}
