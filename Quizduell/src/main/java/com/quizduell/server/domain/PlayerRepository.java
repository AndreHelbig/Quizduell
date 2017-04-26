package com.quizduell.server.domain;


/**
 *
 * @author Andre
 */
public interface PlayerRepository {
    Player getPlayerById(long id);
    Player getPlayerByName(String name);
    
     
    void persistPlayer(Player player);
}
