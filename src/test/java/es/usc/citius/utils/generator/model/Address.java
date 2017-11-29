package es.usc.citius.utils.generator.model;

import es.usc.citius.utils.generator.GenerationStrategy;
import es.usc.citius.utils.generator.annotations.AllowedValues;

public class Address {
    @AllowedValues(strings = {"Calle 1", "Calle 2", "Calle 3", "Calle 4", "Calle 5", "Calle 6", "Calle 7", "Calle 8", "Calle 9"})
    private String street;
    @AllowedValues(value = GenerationStrategy.GENERATE, min = 0, max = 25)
    private int number;
    @AllowedValues(strings = {"A", "B", "C", "D", "E"})
    private String door;
    @AllowedValues(strings = {"17075", "15701", "36600", "28080"})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (number != address.number) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (door != null ? !door.equals(address.door) : address.door != null) return false;
        return postalCode != null ? postalCode.equals(address.postalCode) : address.postalCode == null;
    }
    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + number;
        result = 31 * result + (door != null ? door.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", door='" + door + '\'' +
                ", postalCode='" + postalCode + '\'' +
                "}\n";
    }

}
