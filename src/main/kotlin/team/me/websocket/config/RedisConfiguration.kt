package team.me.websocket.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import team.me.websocket.subscriber.RedisMessageSubscriber

/**
 * @author Doyeop Kim
 * @since 2024. 6. 6.
 */
@Configuration
class RedisConfiguration(
    @Value("\${spring.redis.host}") private val host: String,
    @Value("\${spring.redis.port}") private val port: Int,
    private val redisMessageSubscriber: RedisMessageSubscriber
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration(host, port))
    }

    fun redisContainer(): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            this.setConnectionFactory(redisConnectionFactory())
            this.addMessageListener(listenerAdapter(), PatternTopic("user:*"))
        }
    }

    @Bean
    fun listenerAdapter(): MessageListenerAdapter {
        return MessageListenerAdapter(redisMessageSubscriber)
    }
}