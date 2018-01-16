package event.leaderboard;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import moveBox.BoxPos;
import moveBox.BoxUpdate;

@Controller
public class VideoStreamController {

    @MessageMapping("/sendVideoStream")
    @SendTo("/topic/VideoStream")
    public BoxUpdate updateBox(BoxPos posSent) throws Exception {
        return new BoxUpdate(posSent.getBoxTop(), posSent.getBoxLeft());
    }

}
