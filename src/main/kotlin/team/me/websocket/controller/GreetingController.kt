package team.me.websocket.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils
import team.me.websocket.message.Greeting
import team.me.websocket.message.HelloMessage

/**
 * @author Doyeop Kim
 * @since 2024. 6. 6.
 */
@Controller
class GreetingController {
    // /hello 경로로 메시지가 들어오면, /topics/greetings 채널을 구독중인 모든 구독자에게 메시지가 발행된다
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000); // for simulating
        return Greeting("Hello, ${HtmlUtils.htmlEscape(message.name)}!")
    }
}