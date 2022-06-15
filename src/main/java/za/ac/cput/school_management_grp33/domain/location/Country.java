package za.ac.cput.school_management_grp33.domain.location;

import org.hibernate.Hibernate;

import java.util.Objects;

/*
Author: Tarren-Marc Adams - 214041794
Date: 14 March 2022
 */
public class Country {
    private String id;
    private String name;

    private Country(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
