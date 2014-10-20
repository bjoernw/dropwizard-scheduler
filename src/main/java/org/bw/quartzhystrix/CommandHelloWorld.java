package org.bw.quartzhystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bjoernweidlich on 10/19/14.
 */
public class CommandHelloWorld extends HystrixCommand<String>
{
	private static final Logger log = LoggerFactory.getLogger(CommandHelloWorld.class);
	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() {
		String s = "Hello " + name + "!";
		log.info(s);
		return s;
	}
}
