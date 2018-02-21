package es.usc.citius.utils.testing.model;

import es.usc.citius.utils.generator.annotations.RandomString;
import es.usc.citius.utils.testing.generator.NameGenerator;

import java.util.Arrays;
import java.util.List;

public class Name {
    @RandomString(generator = NameGenerator.class)
    private String firstname;
    @RandomString(generator = NameGenerator.class, count = 2)
    private List<String> lastname;

    public Name(){}
    public Name(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = Arrays.asList(lastname);
    }

    public String getFirstname() {
        return firstname;
    }
    public List<String> getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(List<String> lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (firstname != null ? !firstname.equals(name.firstname) : name.firstname != null) return false;
        return lastname != null ? lastname.equals(name.lastname) : name.lastname == null;
    }
    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Name{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                "}\n";
    }
}
