package cn.com.bmsoft;

import cn.com.bmsoft.utils.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Bean
    public IdWorker initIdWorker(@Value("${idWoker.worker-id}") long workerId,
                                 @Value("${idWoker.datacenter-id}") long datacenterId) {
        return IdWorker.getInstance(workerId, datacenterId);
    }
}
