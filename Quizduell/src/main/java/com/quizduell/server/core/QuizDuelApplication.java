package com.quizduell.server.core;

import com.quizduell.server.domain.Duel;
import com.quizduell.server.resources.AnswerResource;
import com.quizduell.server.resources.DuelResource;
import com.quizduell.server.resources.TurnResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;


/**
 *
 * @author Andre
 */
public class QuizDuelApplication extends Application {
    
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        router.attach("/duels", DuelResource.class);
        router.attach("/turns", TurnResource.class);
        router.attach("/answers", AnswerResource.class);
        
        return router;
    }
    
}
