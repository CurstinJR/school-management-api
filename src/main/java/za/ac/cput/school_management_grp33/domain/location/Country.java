package za.ac.cput.school_management_grp33.domain.location;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/*
Author: Tarren-Marc Adams - 214041794
Date: 14 March 2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Country {
   @Id
    @NotNull private String id;
    @NotNull private String name;

    private Country(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Country country = (Country) o;
        return id !=null && Objects.equals(id, country.id);
    }
    public int hashCode()
    {
        return getClass().hashCode();
    }

    public static class Builder{
        private String id;
        private String name;

        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder copy(Country country){
            this.id = country.id;
            this.name = country.name;
            return this;
        }
        public Country build(){return new Country(this);}
    }
}
