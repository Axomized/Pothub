package event.leaderboard;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/update")
    @SendTo("/topic/TeaParty")
    public UpdatingScore updateScore(UserScore message) throws Exception {
        return new UpdatingScore(message.getUserTo(),message.getScore());
    }

}
