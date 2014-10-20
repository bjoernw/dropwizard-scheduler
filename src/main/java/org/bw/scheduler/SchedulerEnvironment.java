package org.bw.scheduler;

import io.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Nonnull;

/**
 * Created by bjoernweidlich on 10/19/14.
 */
public class SchedulerEnvironment implements Managed
{
	private final String configLocation;
	private ApplicationContext springContext;
	private Scheduler scheduler;

	public SchedulerEnvironment(@Nonnull String configLocation)
	{
		this.configLocation = configLocation;
	}

	private Scheduler configSchedulers(ApplicationContext ctx)
	{
		Scheduler defaultScheduler = (Scheduler) ctx.getBean("defaultScheduler");

		return defaultScheduler;
	}

	public ApplicationContext getSpringContext()
	{
		return springContext;
	}

	public Scheduler getScheduler()
	{
		return scheduler;
	}

	@Override
	public void start() throws Exception
	{
		this.springContext = new ClassPathXmlApplicationContext(configLocation);
		this.scheduler = configSchedulers(springContext);
	}

	@Override
	public void stop() throws Exception
	{
		this.scheduler.shutdown(true);
	}
}
