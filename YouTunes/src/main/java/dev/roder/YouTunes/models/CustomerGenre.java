package dev.roder.YouTunes.models;

/**
 * Wrapper model for the data extracted from the database
 * when extracting genre and amount of sales.
 */
public record CustomerGenre(String genre, int amount) {

}
