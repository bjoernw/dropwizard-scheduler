package org.bw.services;

import com.google.common.base.Optional;
import org.bw.exceptions.DataAccessLayerException;
import org.quartz.Job;

/**
 * Created by bjoernweidlich on 6/1/14.
 */
public interface IJobService
{
	public Optional<Job> getJob(int jobId) throws DataAccessLayerException;
	public void scheduleJob(int jobId) throws DataAccessLayerException;
	public void updateJob(int oldDealId, Job newJob) throws DataAccessLayerException;
	public void deleteJob(int jobId) throws DataAccessLayerException;
}
