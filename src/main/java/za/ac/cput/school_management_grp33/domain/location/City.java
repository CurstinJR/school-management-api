package za.ac.cput.school_management_grp33.domain.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class City {
    @Id
    private String id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name="contryId")
    private Country country;
}
