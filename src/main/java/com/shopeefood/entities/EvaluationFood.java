package com.shopeefood.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class EvaluationFood extends Base {

    @Id
    @Setter(NONE)
    @Column(updatable = false)
    @SequenceGenerator(
            name = "evaluation_food_sequence",
            sequenceName = "evaluation_food_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "evaluation_food_sequence")
    private Long id;

    @ColumnDefault("0")
    @Column(name = "level_star", nullable = false)
    private Integer levelStar;

    @Lob
    @Column
    @Nationalized
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "evaluation_food_user_id_fk"))
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "evaluation_food_food_id_fk"))
    private Food food;
}
