package dev.roder.YouTunes.models;

/**
 * Wrapper model for the data extracted from the database
 * when extracting country and amount of customers from that country.
 */
public record CustomerCountry(String country, int customerAmnt) {

}
