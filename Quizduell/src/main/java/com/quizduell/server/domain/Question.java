package com.quizduell.server.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author Andre
 */
@Entity
@Indexed
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
}
