package za.ac.cput.school_management_grp33.domain.lookup;
/*
Name.java
Author: Kevin Lionel Mombo Ndinga(218180500)
Date: 11 of June 2022;
 */
public class Name {
    private String firstName, middleName, lastName;

    private Name(Builder builder) {
     this.firstName = builder.firstName;
     this.middleName = builder.middleName;
     this.lastName = builder.lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder {
        private String firstName, middleName, lastName;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder copy(Name name) {
            this.firstName = name.firstName;
            this.middleName = name.middleName;
            this.lastName = name.lastName;
            return this;
        }
        public Name build(){
            return new Name(this);
        }
    }
}