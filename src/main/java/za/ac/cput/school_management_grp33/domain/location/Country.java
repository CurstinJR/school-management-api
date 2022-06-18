/*
Author: Tarren-Marc Adams - 214041794
Date: 14 March 2022
 */
package za.ac.cput.school_management_grp33.domain.location;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Country {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Country country = (Country) o;
        return id != null && Objects.equals(id, country.id);
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
