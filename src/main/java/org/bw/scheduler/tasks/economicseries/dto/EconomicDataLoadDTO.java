package org.bw.scheduler.tasks.economicseries.dto;

import org.bw.scheduler.tasks.AbstractTaskDTO;

/**
 * Created by bjoernweidlich on 10/18/14.
 */
public class EconomicDataLoadDTO extends AbstractTaskDTO
{
	private String asofDate;
	private String dataSource;
	private String seriesId;

	public EconomicDataLoadDTO(String asofDate, String dataSource, String seriesId)
	{
		this.asofDate = asofDate;
		this.dataSource = dataSource;
		this.seriesId = seriesId;
	}

	public String getAsofDate()
	{
		return asofDate;
	}

	public String getDataSource()
	{
		return dataSource;
	}

	public String getSeriesId()
	{
		return seriesId;
	}
}
