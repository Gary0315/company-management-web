package tw.com.ispan.eeit48.config;

import tw.com.ispan.eeit48.service.WebRtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Autowired
    WebRtcService service;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //加入 webrtc,且任意ip皆可訪問
        registry.addHandler(service, "/webrtc").setAllowedOrigins("*");
    }
}
