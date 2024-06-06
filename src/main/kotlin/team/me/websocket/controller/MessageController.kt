package team.me.websocket.controller

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import team.me.websocket.dto.MessageRequest

/**
 * @author Doyeop Kim
 * @since 2024. 6. 6.
 */
@Controller
class MessageController(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    @MessageMapping("/send")
    fun publishMessage(@Payload message: MessageRequest) {
        redisTemplate.convertAndSend("user:${message.userId}", message.content)
    }
}