package lut.lijihu.airtransport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by kj on 2017/1/19.
 */
@SpringBootApplication(exclude =HibernateJpaAutoConfiguration.class )
@ComponentScan(basePackages = "lut.lijihu.airtransport")
public class AirTransportApplication {
    public static void main(String[] args){
        SpringApplication.run(AirTransportApplication.class, args);
    }
}
