package com.quizduell.server.core;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 *
 * @author Andre
 */
public class RestServer {
    public static void main(String[] args) throws Exception {
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8085);
        component.getDefaultHost().attach("/api", new QuizDuelApplication());
        component.start();
        
    }
}
