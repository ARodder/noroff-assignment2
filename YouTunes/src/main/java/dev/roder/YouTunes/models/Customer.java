package dev.roder.YouTunes.models;

public record Customer(int customerId, String firstName, String lastName,
        String address,
        String city, String country, String postalCode, String phone,
        String email) {

}
