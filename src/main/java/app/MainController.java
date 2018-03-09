package app;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    // @MessageMapping("/hello")
    // @SendTo("/topic/response")
    @MessageMapping("/hello/{chatId}")
    @SendTo("/topic/response/{chatId}")
    public Response response(@DestinationVariable String chatId, Message message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Response("Hello, " + message.getName() + "!");
    }

}
