package event.leaderboard.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LeaderBoardController {

    @MessageMapping("/update/{topic}")
    @SendTo("/topic/{topic}")
    public ArrayList<UpdatingScore> updateScore(@DestinationVariable("topic") String topic, UserScore message) throws Exception {
    	new UserScore(message.getUserFrom(), message.getUserTo(), message.getScore());
    	return UserScore.getUserScore();
    }

    @MessageMapping("/other/{topic}")
    @SendTo("/topic/{topic}")
    public ArrayList<UpdatingScore> otherScore(@DestinationVariable("topic") String topic, WebMessage message) throws Exception {
    	switch(message.getMessageType()) {
    		case "Retrieve":
    			UserScore.registerUser(message.getUserToDisplay());
    			return UserScore.getUserScore();
    		case "Push":
    			ArrayList<UpdatingScore> al = new ArrayList<UpdatingScore>();
    			UpdatingScore us = new UpdatingScore(message.getUserToDisplay(), new BigDecimal(-1));
    			al.add(us);
    			return al;
    		case "Hide":
    			ArrayList<UpdatingScore> al2 = new ArrayList<UpdatingScore>();
    			UpdatingScore us2 = new UpdatingScore(message.getUserToDisplay(), new BigDecimal(-2));
    			al2.add(us2);
    			return al2;
    		case "Show":
    			ArrayList<UpdatingScore> al3 = new ArrayList<UpdatingScore>();
    			UpdatingScore us3 = new UpdatingScore(message.getUserToDisplay(), new BigDecimal(-3));
    			al3.add(us3);
    			return al3;
    		case "End":
    			ArrayList<UpdatingScore> al4 = new ArrayList<UpdatingScore>();
    			UpdatingScore us4 = new UpdatingScore(message.getUserToDisplay(), new BigDecimal(-4));
    			al4.add(us4);
    			return al4;
    		default:
    			return null;
    	}
    }
}
