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
public class Question {
    @Getter
    @Setter
    @Id
    private UUID uuid;
    
    @Getter
    @Setter
    private String text;
    
    @Getter
    @Setter
    @OneToMany
    private List<Answer> answers;
    
    @Getter
    @Setter
    @ManyToOne
    private Turn turn;

    public void setDuel(Duel duel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
