package app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // @Override
    // public void configureMessageBroker(MessageBrokerRegistry registry) {
    //     registry.enableSimpleBroker("/topic");
    //     registry.setApplicationDestinationPrefixes("/chat");
    // }

    @Value("${spring.rabbitmq.host}")
		private String host;
    @Value("${spring.rabbitmq.port}")
		private int port;
    @Value("${spring.rabbitmq.username}")
		private String login;
    @Value("${spring.rabbitmq.password}")
		private String passcode;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

      registry.setApplicationDestinationPrefixes("/chat");

      // registry.enableStompBrokerRelay("/topic")
      //         .setRelayHost("furry-pipkin-43.bigwig.lshift.net")
      //         .setVirtualHost("g0dTtITeQ9Ag")
      //         .setRelayPort(10410)
      //         .setClientLogin("AyGEPF8v")
      //         .setClientPasscode("IGJDhxDhZtctiH9Pje1JgnH0AcYbNx-g");

      // Use this for enabling a Full featured broker like RabbitMQ
      registry.enableStompBrokerRelay("/topic")
              .setRelayHost(host)
              // .setVirtualHost("/chat")
              .setRelayPort(port)
              .setClientLogin(login)
              .setClientPasscode(passcode);

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
        // To enable CORS
        // .setAllowedOrigins("*")
        .withSockJS();
        // The .setWebSocketEnabled(false) disables WebSockets in order to use XHR-Long Pooling
        // registry.addEndpoint("/ws").withSockJS().setWebSocketEnabled(false);
    }

}
