package tw.com.ispan.eeit48;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@SpringBootApplication
public class IbossApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbossApplication.class, args);
	}
	
@Bean
	public RestTemplate restTemplate() {
        return new RestTemplate();
    }

@Bean
public ServletServerContainerFactoryBean createWebSocketContainer() {
    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
    int max = 1024 * 100;
    container.setMaxTextMessageBufferSize(max);
    container.setMaxBinaryMessageBufferSize(max);
    return container;
}

}
