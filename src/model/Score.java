package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Score {

    @Id
    @Column(name = "SC_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "SC_DATE")
    private LocalDate date;
    @Column(name = "SC_SCORE")
    private int score;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SC_USR_ID", referencedColumnName = "USR_ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    public Score(LocalDate date, int score, User user) {
        this.date = date;
        this.score = score;
        this.user = user;
    }
}
