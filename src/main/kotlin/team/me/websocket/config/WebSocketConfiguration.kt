package team.me.websocket.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/**
 * @author Doyeop Kim
 * @since 2024. 6. 6.
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration: WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic") // 퍼블리셔가 /topics 주소로 메시지를 발행하면 구독자들에게 전달

        // 퍼블리셔가 /app 주소로 메시지를 발행하면 가공해서 구독자들에게 전달
        // 만일 /app/hello 주소로 퍼블리셔가 메시지를 발행하면, /hello prefix가 달린 MessageMapping 어노테이션에 대응하는 핸들러가 동작
        // 해당 핸들러가 데이터를 가공하여 SendTo 에 대응하는 연결 핸들러로 메시지를 전송한다
        // enableSimpleBroker 설정으로 인해, /topic/greetings 으로 메시지가 발행된다
        registry.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // 커넥션을 맺는 경로 설정. 만약 WebSocket을 사용할 수 없는 브라우저라면 다른 방식을 사용하도록 설정
        registry.addEndpoint("/gs-guide-websocket").withSockJS()
    }
}