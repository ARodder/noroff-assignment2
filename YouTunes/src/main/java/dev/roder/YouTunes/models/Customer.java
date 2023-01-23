package dev.roder.YouTunes.models;

public record Customer(int customerId, String firstName, String lastName,
                String country, String postalCode, String phone,
                String email) {

}
