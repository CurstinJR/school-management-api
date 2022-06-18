/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.domain.location;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "cities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @Column(name = "city_id", nullable = false)
    private String id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @NotNull(message = "{validation.field.Country.mandatory}")
    @ToString.Exclude
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id)
                && Objects.equals(name, city.name)
                && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
