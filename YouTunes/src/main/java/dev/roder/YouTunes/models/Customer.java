package dev.roder.YouTunes.models;

public record Customer(int customerId, String firstName, String lastName,
        String company, String address, String city, String state, String country, String postalCode, String phone,
        String fax, String email, int supportRepId) {

}
