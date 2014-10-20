package org.bw.controllers;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import org.bw.applicationconfig.AppConfiguration;
import org.bw.scheduler.SchedulerEnvironment;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bjoernweidlich on 6/2/14.
 */
@Path("/job")
@Produces(MediaType.APPLICATION_JSON)
public class JobController
{
	private static final Logger log = LoggerFactory.getLogger(JobController.class);
	private final SchedulerEnvironment env;

	public JobController(AppConfiguration cfg, SchedulerEnvironment schedulerEnvironment)
	{
		this.env = schedulerEnvironment;
	}

	@Timed
	@GET
	public void triggerJob(@QueryParam("jobName") Optional<String> jobName) throws WebApplicationException
	{
		if (!jobName.isPresent())
			throw new WebApplicationException(Response.Status.BAD_REQUEST);

		JobKey jobKey = new JobKey(jobName.get());
		try {
			boolean jobExists = env.getScheduler().checkExists(jobKey);
			if (!jobExists)
				throw new WebApplicationException(Response.Status.NO_CONTENT);
			env.getScheduler().triggerJob(jobKey);
		} catch (SchedulerException e) {
			log.error(e.getMessage());
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}
