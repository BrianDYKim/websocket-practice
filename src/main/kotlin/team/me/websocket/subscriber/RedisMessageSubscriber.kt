package team.me.websocket.subscriber

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

/**
 * @author Doyeop Kim
 * @since 2024. 6. 6.
 */
@Service
class RedisMessageSubscriber(
    private val messagingTemplate: SimpMessagingTemplate
): MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val channel = message.channel.toString()
        val messageBody = message.body.toString()

        // Assuming the channel is in the format "user:userid"
        val userId = channel.split(":")[1]
        messagingTemplate.convertAndSend("/queue/${userId}", messageBody)
    }
}
