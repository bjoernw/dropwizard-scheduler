package org.bw.services;

import com.google.common.base.Optional;
import org.bw.exceptions.DataAccessLayerException;
import org.quartz.Job;

/**
 * Created by bjoernweidlich on 6/1/14.
 */
public class JobService implements IJobService
{

	@Override
	public Optional<Job> getJob(int jobId) throws DataAccessLayerException
	{
		return null;
	}

	@Override
	public void scheduleJob(int jobId) throws DataAccessLayerException
	{

	}

	@Override
	public void updateJob(int oldDealId, Job newJob) throws DataAccessLayerException
	{

	}

	@Override
	public void deleteJob(int jobId) throws DataAccessLayerException
	{

	}
}
