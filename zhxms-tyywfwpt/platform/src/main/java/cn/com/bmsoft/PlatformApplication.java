package cn.com.bmsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import cn.com.bmsoft.modules.am.service.AmStrategyService;

@EnableScheduling
@SpringBootApplication
public class PlatformApplication {

	@Autowired
	private static AmStrategyService amStrategyService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PlatformApplication.class, args);

	}

}
