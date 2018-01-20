package movebox;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BoxPosController {

    @MessageMapping("/updateBoxPosition")
    @SendTo("/topic/Boxes")
    public BoxUpdate updateBox(BoxPos posSent) throws Exception {
        return new BoxUpdate(posSent.getBoxTop(), posSent.getBoxLeft());
    }

}
