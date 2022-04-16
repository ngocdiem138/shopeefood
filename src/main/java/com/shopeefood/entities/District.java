package com.shopeefood.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.NONE;
import static org.hibernate.annotations.CascadeType.ALL;

@Table
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class District extends Base {

    @Id
    @Setter(NONE)
    @Column(updatable = false)
    @SequenceGenerator(
            name = "district_sequence",
            sequenceName = "district_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "district_sequence")
    private Long id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "district_province_id_fk"))
    private Province province;

    @Cascade(ALL)
    @ToString.Exclude
    @OneToMany(mappedBy = "district")
    private List<Ward> wards = new ArrayList<>();

    public void addWard(@NonNull Ward ward) {
        if (!this.wards.contains(ward)) {
            this.wards.add(ward);
            ward.setDistrict(this);
        }
    }

    public void removeWard(@NonNull Ward ward) {
        if (!this.wards.contains(ward)) {
            this.wards.remove(ward);
            ward.setDistrict(null);
        }
    }
}
