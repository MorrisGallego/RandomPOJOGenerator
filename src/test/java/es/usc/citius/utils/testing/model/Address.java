package es.usc.citius.utils.testing.model;

import es.usc.citius.utils.generator.annotations.RandomCharacter;
import es.usc.citius.utils.generator.annotations.RandomInteger;
import es.usc.citius.utils.generator.annotations.RandomString;

public class Address {
    @RandomString()
    private String street;
    @RandomInteger(min = 0, max = 150)
    private int number;
    @RandomInteger(min = 0, max = 100)
    private int rating;
    @RandomCharacter()
    private char door;
    @RandomString(from = {"16700", "19800", "20500", "35768", "46753"})
    private String postalCode;

    public Address(){}
    public Address(String street, int number, char door, String postalCode) {
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
    public char getDoor() {
        return door;
    }
    public void setDoor(char door) {
        this.door = door;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
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
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (number != address.number) return false;
        if (rating != address.rating) return false;
        if (door != address.door) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        return postalCode != null ? postalCode.equals(address.postalCode) : address.postalCode == null;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + rating;
        result = 31 * result + (int) door;
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
}
