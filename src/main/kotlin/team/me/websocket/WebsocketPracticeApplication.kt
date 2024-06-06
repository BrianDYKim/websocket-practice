package team.me.websocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebsocketPracticeApplication

/**
 * @see <a href="https://growth-coder.tistory.com/157">STOMP 기초</a>
 */
fun main(args: Array<String>) {
    runApplication<WebsocketPracticeApplication>(*args)
}
