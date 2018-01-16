package event.leaderboard.model;

import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LeaderBoardController {

    @MessageMapping("/update")
    @SendTo("/topic/TeaParty")
    public ArrayList<UpdatingScore> updateScore(UserScore message) throws Exception {
        return UserScore.getUserScore();
    }

}
