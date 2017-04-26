package com.quizduell.server.domain;

import javax.persistence.*;

/**
 *
 * @author Andre
 */
public class Manager {
    
    public static EntityManager manager;
    
    public static EntityManager create() {
        if(manager == null) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("cassandra");
            manager = factory.createEntityManager();
            Player player1 = new Player();
        }
        return manager;
    }
}
