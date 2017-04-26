package com.quizduell.server.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Andre
 */
@Entity
public class Duel {
    private static final int TURNS = 6;
    @Getter
    @Setter
    @Id
    private UUID uuid;
    
    @Getter
    @Setter
    @OneToOne
    private Player player1;
    
    @Getter
    @Setter
    @OneToOne
    private Player player2;
    
    @Getter
    @Setter
    @OneToOne
    private List<Turn> turns;

    public Duel() {
        this.uuid = UUID.randomUUID();
    }    
}
