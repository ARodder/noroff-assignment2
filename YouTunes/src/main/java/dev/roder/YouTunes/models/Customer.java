package dev.roder.YouTunes.models;

/**
 * Wrapper model for the Customers extracted from the database.
 */
public record Customer(int customerId, String firstName, String lastName,
                String country, String postalCode, String phone, String email) {

}
