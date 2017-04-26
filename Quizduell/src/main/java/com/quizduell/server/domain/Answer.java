package com.quizduell.server.domain;

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
class Answer {
    @Getter
    @Setter
    @Id
    private UUID uuid;
        
    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private boolean isRight;

    //for hibernate
    @Getter
    @Setter
    @ManyToOne
    private Turn turn;

    @Getter
    @Setter
    @ManyToOne
    private Question question;
}
