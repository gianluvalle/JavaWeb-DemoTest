package it.unikey.primaconnessionedbpostgres.DAL.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "Disciplines")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AthleteEntity> athletes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DisciplineEntity entity = (DisciplineEntity) o;
        return id != null && Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
