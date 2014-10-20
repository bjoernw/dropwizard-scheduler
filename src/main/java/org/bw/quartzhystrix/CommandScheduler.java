package org.bw.quartzhystrix;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by bjoernweidlich on 10/18/14.
 */
@ThreadSafe
public class CommandScheduler
{
	private final static boolean IS_DURABLE = false;
	private static final Logger log = LoggerFactory.getLogger(CommandScheduler.class);

	private Scheduler scheduler;

	public CommandScheduler(Scheduler scheduler)
	{
		this.scheduler = scheduler;
	}

	public void schedule(JobDetail jobDetail, Trigger trigger) throws SchedulerException
	{
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public void schedule(Class<? extends Job> job, @Nullable JobDataMap jobData, Trigger trigger) throws SchedulerException
	{
		JobDetail jobDetail = newJob(CommandJob.class)
				.withIdentity("myJob", "group1") // name "myJob", group "group1"
				.usingJobData(jobData)
				.build();
		this.schedule(jobDetail, trigger);
	}

}
