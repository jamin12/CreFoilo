package emyo.jamin.jej.crefoilo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
public class CrefoiloApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrefoiloApplication.class, args);
	}

}
