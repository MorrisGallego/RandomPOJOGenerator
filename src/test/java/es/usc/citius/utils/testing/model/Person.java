package es.usc.citius.utils.testing.model;

import es.usc.citius.utils.generator.annotations.RandomBoolean;
import es.usc.citius.utils.generator.annotations.RandomObject;
import es.usc.citius.utils.generator.annotations.RandomString;
import es.usc.citius.utils.testing.generator.IdGenerator;

public class Person {
    @RandomBoolean
    private boolean active;
    @RandomObject
    private Name name;
    @RandomObject
    private Address address;
    @RandomString(generator = IdGenerator.class)
    private String id;
    @RandomString(from = {"666777888", "876947521", "554671892"})
    private String phoneNumber;

    public Person(){}
    public Person(boolean active, Name name, Address address, String id, String phoneNumber) {
        this.active = active;
        this.name = name;
        this.address = address;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return  (name != null ? name.equals(person.name) : person.name == null) &&
                (address != null ? address.equals(person.address) : person.address == null) &&
                (id != null ? id.equals(person.id) : person.id == null) &&
                (phoneNumber != null ? phoneNumber.equals(person.phoneNumber) : person.phoneNumber == null);
    }
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", address=" + address +
                ", id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "}\n";
    }
}
