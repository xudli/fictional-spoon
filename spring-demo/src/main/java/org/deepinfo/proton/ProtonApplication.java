package org.deepinfo.proton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:/config/hbase-spring.xml"})
public class ProtonApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProtonApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProtonApplication.class);
	}
}
