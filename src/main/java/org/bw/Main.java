package org.bw;

/**
 * Created by bjoernweidlich on 5/18/14.
 */

import com.codahale.metrics.JmxReporter;
import com.google.common.collect.ImmutableMultimap;
import com.sun.jersey.api.client.Client;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bw.applicationconfig.AppConfiguration;
import org.bw.clients.ReportClient;
import org.bw.controllers.JobController;
import org.bw.scheduler.SchedulerEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;


public class Main extends Application<AppConfiguration>
{
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception
	{
		if (args == null || args.length < 1) {
			new Main().run(new String[]{"server", "src/main/resources/config.yml"});
		}
		else if (args != null) {
			new Main().run(args);
		}
		else {
			new Main().run(new String[]{"server", System.getProperty("dropwizard.config")});
		}
	}

	@Override
	public void initialize(Bootstrap<AppConfiguration> bootstrap)
	{
		bootstrap.addBundle(new AssetsBundle());
	}

	@Override
	public void run(AppConfiguration cfg, Environment env)
	{
		configMetrics(env);
		configAdminTasks(env);
		configHttpClients(cfg,env);

		/*
			Register all managed Environments
		 */
		SchedulerEnvironment schedulerEnvironment = configSchedulerEnvironment();
		env.lifecycle().manage(schedulerEnvironment);

		registerControllers(cfg,env, schedulerEnvironment);
		configDatabases(cfg, env);




	}

	private SchedulerEnvironment configSchedulerEnvironment()
	{
		return new SchedulerEnvironment("main_context.xml");
	}


//	private DataSource configSchedulerDatasource(AppConfiguration cfg, Environment env) throws ClassNotFoundException
//	{
//
//		DataSourceFactory f = cfg.getDataSourceFactory();
//		f.setDriverClass("org.postgresql.Driver");
//		f.setUrl("someUrl");
//		f.setPassword("somepass");
//		f.setUser("someuser");
//		ManagedDataSource dataSource = f.build(env.metrics(), "datasourcename");
//		return dataSource;
//
//	}

	private void configDatabases(AppConfiguration cfg, Environment env)
	{
		//DBI dbi = null;
//        try {
//            dbi = new DBIFactory().build(env, cfg.getDataSourceFactory(), "db");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        env.jersey().register(new DBResource(dbi));
	}

	private void configMetrics(Environment env)
	{
		JmxReporter.forRegistry(env.metrics()).build().start(); // Manually add JMX reporting (Dropwizard regression)
	}

	private void registerControllers(AppConfiguration cfg, Environment env, SchedulerEnvironment schedulerEnvironment)
	{
		env.jersey().register(new JobController(cfg, schedulerEnvironment));
	}

	private void configHttpClients(AppConfiguration cfg, Environment env)
	{
		Client client = new JerseyClientBuilder(env).using(cfg.getJerseyClientConfiguration()).build("client");
		env.jersey().register(new ReportClient(client));
	}

	private void configAdminTasks(Environment env)
	{
		env.admin().addTask(new Task("sometask")
		{
			@Override
			public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception
			{
				System.out.println("whatup");
			}
		});
	}


//	void initializeAtmosphere(AppConfiguration configuration, Environment environment)
//	{
//		FilterRegistration.Dynamic filter = environment.servlets().addFilter("/chat", new CrossOriginFilter());
//		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
//
//		AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
//		atmosphereServlet.framework().addInitParameter("com.sun.jersey.config.property.packages", "org.bw.controllers.atmosphere");
//		atmosphereServlet.framework().addInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
//		//atmosphereServlet.framework().addInitParameter("org.atmosphere.cpr.broadcastFilterClasses", "com.example.helloworld.filters.BadWordFilter");
//
//		ServletHolder holder = new ServletHolder();
//		holder.setServlet(atmosphereServlet);
//
//		environment.getAdminContext().addServlet(holder, "/chat/*");
//
//	}

}
