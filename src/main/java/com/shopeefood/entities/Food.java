package com.shopeefood.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;
import static org.hibernate.annotations.CascadeType.ALL;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Food extends Base {

    @Id
    @Setter(NONE)
    @Column(updatable = false)
    @SequenceGenerator(
            name = "food_sequence",
            sequenceName = "food_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "food_sequence")
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Lob
    @Column
    @Nationalized
    private String description;

    @ColumnDefault("0.0")
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Byte[] image;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "food_category_id_fk"))
    private Category category;

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<BillDetail> billDetails = new ArrayList<>();

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<Voucher> vouchers = new ArrayList<>();

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<CartDetail> cartDetails = new ArrayList<>();

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<ShopFood> shopFoods = new ArrayList<>();

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "food")
    private List<EvaluationFood> evaluationFoods = new ArrayList<>();
}
