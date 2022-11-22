package it.unikey.primaconnessionedbpostgres.DAL.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "Athletes")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AthleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String lastName;

    private Integer age;

    private Double height;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private DisciplineEntity discipline;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AthleteEntity entity = (AthleteEntity) o;
        return id != null && Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
