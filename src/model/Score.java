package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Data model for score uses hibernate annotations
 * @author Michal Glodek
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Score {

    /** Score id auto generated*/
    @Id
    @Column(name = "SC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** Score date */
    @Column(name = "SC_DATE")
    private LocalDate date;
    /** Score value */
    @Column(name = "SC_SCORE")
    private int score;
    /** User who got this score */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_USR_ID", referencedColumnName = "USR_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    /**
     * Score constructor
     * @param date score date
     * @param score score value
     * @param user User who got this score
     */
    public Score(LocalDate date, int score, User user) {
        this.date = date;
        this.score = score;
        this.user = user;
    }
}
