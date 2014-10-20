package org.bw.scheduler.tasks.economicseries.impl;

import org.bw.scheduler.tasks.economicseries.IEconomicSeriesDataLoadTask;
import org.bw.scheduler.tasks.economicseries.dto.EconomicDataLoadDTO;

/**
 * Created by bjoernweidlich on 10/18/14.
 */
public class EconomicSeriesDataLoadTask implements IEconomicSeriesDataLoadTask
{
	protected EconomicDataLoadDTO dto;

	public EconomicSeriesDataLoadTask(EconomicDataLoadDTO dto)
	{
		this.dto = dto;
	}

	@Override
	public void load()
	{
		System.out.println(dto.getSeriesId() + " hi");
	}
}
