package dev.roder.YouTunes.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import dev.roder.YouTunes.models.Customer;

public class CustomerRepository implements CrudRepository<Integer, Customer>{

    private final String url;

    private final String username;

    private final String password;


    public CustomerRepository(

            @Value("${spring.datasource.url}") String url,

            @Value("${spring.datasource.username}") String username,

            @Value("${spring.datasource.password}") String password){

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
        try(Connection conn = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("country"),
                        resultSet.getString("postal_code"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                customers.add(customer);
            }
        }catch (SQLException e){
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
    
}
