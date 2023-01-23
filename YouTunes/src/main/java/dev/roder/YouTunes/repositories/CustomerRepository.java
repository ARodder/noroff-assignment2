package dev.roder.YouTunes.repositories;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import dev.roder.YouTunes.models.Customer;
import dev.roder.YouTunes.models.CustomerCountry;

@Repository
public class CustomerRepository implements CrudRepository<Integer, Customer> {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepository(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Customer getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void create(Customer object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Customer object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * This method retrieves a customer object by searching the first and last name
     * in the database
     * 
     * @param name The full name of the customer in the format "first_name
     *             last_name"
     * @return A customer object with all the details of the customer, or null if no
     *         customer is found
     */
    public Customer getByName(String name) {
        // Building the query with like to retrieve customers with similar name if f.eks
        // a letter is missing in the input.
        String sql = "SELECT * FROM customer WHERE first_name LIKE ? AND last_name LIKE ?";
        // Splitting the name based on spaces
        String[] names = name.split(" ");
        Customer customer = null;

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            // Adding the first name to the prepared statement wrapping it in % so LIKE can
            // look
            // for names missing letters both in front and behind the name.
            statement.setString(1, "%" + names[0] + "%");

            // same as with first name, but taking the last element in the array in case the
            // input has middle names.
            statement.setString(2, "%" + names[names.length - 1] + "%");
            ResultSet result = statement.executeQuery();

            // If a result was found take that result and build a customer object.
            if (result.next()) {
                customer = new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email"));
            }

            // close the database connection
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }

    /**
     * 
     * This method retrieves a page of customers from the database.
     * 
     * @param pageSize The number of customers to retrieve in a single page
     * @param offset   The number of customers to skip before starting the page
     * @return A list of customer objects, representing the requested page
     */
    public List<Customer> getCustomerPage(int pageize, int offset) {
        // Building the query with LIMIT and OFFSET
        String sql = "SELECT * FROM customer LIMIT ? OFFSET ?";
        List<Customer> customerPage = new ArrayList<>();

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            // inserting the limit(also called pageSize)
            statement.setInt(1, pageize);
            // inserting the offset(amount of entries to move past)
            statement.setInt(2, offset);

            ResultSet result = statement.executeQuery();

            // Add all the customers found to the list.
            while (result.next()) {
                customerPage.add(new Customer(
                        result.getInt("customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("country"),
                        result.getString("postal_code"),
                        result.getString("phone"),
                        result.getString("email")));
            }

            // close the database connection
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customerPage;
    }

    /**
     * 
     * This method retrieves the country with the most customers.
     * 
     * @return A CustomerCountry object containing the country name and the number
     *         of customers from that country.
     */
    public CustomerCountry getCountryWithMosyCustomers() {
        // Builds query by counting the occurences of each country, ordering them in
        // descending order, and retrieving only the top one
        // with the highest count.
        String sql = "SELECT country, count(country) as amount FROM customer GROUP BY country ORDER BY amount DESC LIMIT 1";
        CustomerCountry country = null;

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            // using if since there will only be one result.
            if (result.next()) {
                country = new CustomerCountry(result.getString("country"), result.getInt("amount"));
            }

            // close the database connection
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return country;
    }

}
