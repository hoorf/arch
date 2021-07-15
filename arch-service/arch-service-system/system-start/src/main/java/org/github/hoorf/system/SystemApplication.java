package org.github.hoorf.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("org.github.hoorf.**.mapper")
@SpringBootApplication(scanBasePackages = "org.github.hoorf",exclude = { DataSourceAutoConfiguration.class })
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
