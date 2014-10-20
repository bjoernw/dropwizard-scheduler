package org.bw.applicationconfig;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* Created by bjoernweidlich on 5/31/14.
*/ // YAML Configuration
public class AppConfiguration extends Configuration
{
	@Valid
	@NotNull
	@JsonProperty
	JerseyClientConfiguration httpClient = new JerseyClientConfiguration();
	@JsonProperty
	private
	@NotEmpty
	String template;
	@JsonProperty
	private
	@NotEmpty
	String defaultName;
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory database = new DataSourceFactory();

	@Valid
	@NotNull
	@JsonProperty
	private String schedulerConfig;

	public DataSourceFactory getDataSourceFactory()
	{
		return database;
	}

	public JerseyClientConfiguration getJerseyClientConfiguration()
	{
		return httpClient;
	}

	public String getTemplate()
	{
		return template;
	}

	public String getDefaultName()
	{
		return defaultName;
	}

}
