package dev.roder.YouTunes.repositories;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.roder.YouTunes.models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import dev.roder.YouTunes.models.Customer;
import dev.roder.YouTunes.models.CustomerCountry;
import dev.roder.YouTunes.models.CustomerGenre;

/**
 * Creates a repository for the customer table
 * serves as an interface between the application and the database
 * and gives the software the possibility to interact with the user data.
 */
@Repository
public class CustomerRepository implements CrudRepository<Integer, Customer> {

    private final String url;
    private final String username;
    private final String password;

    /**
     * Creates an instance of the Customer repository by injecting
     * the values of the url, username and password from the
     * application.properties-file
     * 
     * @param url      url address of the sql database.
     * @param username username to access the database.
     * @param password password to access the database.
     */
    public CustomerRepository(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param id Integer corresponding to the customer_id in the customer table
     *           that one wants to find the corresponding table entry for.
     * @return Returns a Customer record data object of the retrieved entry
     *         in the customer table corresponding to the provided customer id
     *         Can return null if no such table entry is found
     */
    @Override
    public Customer getById(Integer id) {
        Customer customer = null;

        // Initializing SQL statement, using WHERE clause to find specific customer by
        // id
        // and ?-syntax for creating a parameterized statement where Integer id can be
        // inputted
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        // Connection to database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            // We set our predefined variable denoted by ?
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            // Even though we only expect one returned entry
            // we loop through in case the query returns null
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * This method retrieves all customers found in the customer table
     * 
     * @return List of all customers formatted as a Customer record data object
     *         returns List of size 0 if no customers present in customer table
     */
    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        // Initializing SQL statement, no WHERE clause present as all customers are to
        // be retrieved
        String sql = "SELECT * FROM customer";

        // Connection to database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Looping through retrieved customers, if any, and building a
            // record object Customer based on parameters from result
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

    /**
     * This method creates a new entry of a customer in the customer table
     * This is a DML statement which does not return a ResultSet, but instead
     * it prints the number of rows affected which indicates whether
     * the query was successful in completing its manipulation or not.
     * 
     * @param object Customer record data object representing a customer
     *               that is to be added into the database as a new entry.
     */
    @Override
    public void create(Customer object) {

        // Initializing the SQL statement. We make use of several '?'
        // to denote all the values that we will supply the statement with
        // a prepared statement and setting each value
        String sql = "INSERT INTO " +
                "customer(first_name, last_name, country, postal_code, phone, email)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        // Connection to database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            // We set each ? in the SQL statement with appropriate values in
            // the supplied object of type Customer record
            statement.setString(1, object.firstName());
            statement.setString(2, object.lastName());
            statement.setString(3, object.country());
            statement.setString(4, object.postalCode());
            statement.setString(5, object.phone());
            statement.setString(6, object.email());

            // This is a DML statement which does not return any ResultSet
            // object but instead returns an integer representing how many rows were
            // affected
            int result = statement.executeUpdate();
            System.out.println("Rows affected: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method updates an existing customer entry in the customer table
     * with new data supplied in the form of a Customer record data object
     * This is a DML statement which does not return a ResultSet, but instead
     * it prints the number of rows affected which indicates whether
     * the query was successful in completing its manipulation or not.
     * 
     * @param object Customer record data object containing the values of
     *               the parameters that are to be updated in the database.
     */
    @Override
    public void update(Customer object) {

        // Initializing SQL statement with all parameters that are to be updated
        // and using '?' where values are to inputted via the prepared statement
        // Customer_id is used together with the WHERE clause to specify which customer
        // entry to update
        String sql = "UPDATE customer SET " +
                "first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? " +
                "WHERE customer_id = ?";

        // Connection to database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            PreparedStatement statement = conn.prepareStatement(sql);

            // Setting the values of parameters that are to be updated
            statement.setString(1, object.firstName());
            statement.setString(2, object.lastName());
            statement.setString(3, object.country());
            statement.setString(4, object.postalCode());
            statement.setString(5, object.phone());
            statement.setString(6, object.email());

            // Setting the customer_id which will specify which table entry to update
            statement.setInt(7, object.customerId());

            System.out.println(statement.toString());

            // This is a DML statement which does not return any ResultSet
            // object but instead returns an integer representing how many rows were
            // affected
            int result = statement.executeUpdate();
            System.out.println("Rows affected: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    /**
     * 
     * This method retrieves the most popular genre among customers by querying the
     * database.
     * The query uses an inner join on the invoice_line, track, and genre tables to
     * retrieve the name of the genre
     * and the total amount of sales in that genre. The sum of the quantity in the
     * invoice_line table is used
     * to add up the amount of sales in each line instead of just counting the
     * invoice lines.
     * The results are then ordered by the amount of sales and the top result is
     * returned.
     * 
     * @return a CustomerGenre object representing the most popular genre and the
     *         total amount of sales in that genre.
     */
    public CustomerGenre getMostPopularGenre() {
        // Builds query to select genre and total amount of sales in the genre
        // uses sum on invoice_line.quantity to add up the amount sales in each
        // line instead of just counting invoice line.
        // uses inner join on track and genre to retrieve the name of each genre.
        String sql = "SELECT genre.name as genreName, sum(invoice_line.quantity) as amount FROM invoice_line" +
                " INNER JOIN track on track.track_id = invoice_line.track_id" +
                " INNER JOIN genre on track.genre_id = genre.genre_id" +
                " GROUP BY genre.name" +
                " ORDER BY amount DESC LIMIT 1";
        CustomerGenre genre = null;

        // Connecting to the database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            // using if since there will only be one result.
            if (result.next()) {
                genre = new CustomerGenre(result.getString("genreName"), result.getInt("amount"));
            }

            // close the database connection
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genre;
    }

    /**
     * This method locates the highest spending customer
     * by summing all spending of customers found by their invoices
     * in the invoice table
     * @return CustomerSpender record data object that contains
     *         the total sum spent by the highest spending customer
     *         and the id associated with this customer to identify him/her
     */
    public CustomerSpender getHighestSpendingCustomer(){
        CustomerSpender highestSpender = null;

        // Initializing SQL statement. The statement groups all entries in the invoice
        // table by customer_id and then sums all the values of the total column in order
        // to find the customer_id that is associated with the highest total spending
        // It sorts the list in descending order and then limits it to 1 entry to automatically
        // select the single customer.
        String sql = "SELECT customer_id, SUM(total) as sum_total FROM invoice GROUP BY customer_id ORDER BY sum_total DESC LIMIT 1";

        // Connection to database
        try (Connection conn = DriverManager.getConnection(url, username, password)){

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                highestSpender = new CustomerSpender(
                        resultSet.getInt("customer_id"),
                        resultSet.getDouble("sum_total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highestSpender;
    }

}
