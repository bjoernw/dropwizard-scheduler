package org.bw.quartzhystrix;

/**
* Created by bjoernweidlich on 10/19/14.
*/
public class CommandDetail
{
	public String description;

	public boolean isDurable = false;
	public String name;
	public boolean shouldRecover = false;
	public boolean persistJobDataAfterExecution = false;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean isDurable()
	{
		return isDurable;
	}

	public void setDurable(boolean isDurable)
	{
		this.isDurable = isDurable;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isShouldRecover()
	{
		return shouldRecover;
	}

	public void setShouldRecover(boolean shouldRecover)
	{
		this.shouldRecover = shouldRecover;
	}

	public boolean isPersistJobDataAfterExecution()
	{
		return persistJobDataAfterExecution;
	}

	public void setPersistJobDataAfterExecution(boolean persistJobDataAfterExecution)
	{
		this.persistJobDataAfterExecution = persistJobDataAfterExecution;
	}
}
