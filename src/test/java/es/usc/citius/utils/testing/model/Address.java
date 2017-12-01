package es.usc.citius.utils.testing.model;

import es.usc.citius.utils.generator.annotations.RandomInteger;
import es.usc.citius.utils.generator.annotations.RandomString;
import es.usc.citius.utils.testing.generator.CustomIntegerGenerator;

public class Address {
    @RandomString()
    private String street;
    @RandomInteger(generator = CustomIntegerGenerator.class)
    private int number;
    @RandomInteger(min = 10, max = 100)
    private int[] rating;
    @RandomString()
    private String door;
    @RandomString()
    private String postalCode;

    public Address(){}
    public Address(String street, int number, String door, String postalCode) {
        this.street = street;
        this.number = number;
        this.door = door;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDoor() {
        return door;
    }
    public void setDoor(String door) {
        this.door = door;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public int[] getRating() {
        return rating;
    }
    public void setRating(int[] rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", rating=" + rating +
                ", door='" + door + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (number != address.number) return false;
        if (rating != address.rating) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (door != null ? !door.equals(address.door) : address.door != null) return false;
        return postalCode != null ? postalCode.equals(address.postalCode) : address.postalCode == null;
    }
    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + rating[0];
        result = 31 * result + (door != null ? door.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
