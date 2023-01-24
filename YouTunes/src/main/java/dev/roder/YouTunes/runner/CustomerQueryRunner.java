package dev.roder.YouTunes.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import dev.roder.YouTunes.models.Customer;
import dev.roder.YouTunes.repositories.CustomerRepository;

@Component
public class CustomerQueryRunner implements ApplicationRunner {

    @Autowired
    private CustomerRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Run getAll by printing the size of the array.
        int customerAmnt = repo.getAll().size();
        System.out.println(customerAmnt);

        // Run create new customer and getById by creating and adding a new customer,
        // then trying to read the customer with the id of the newly added customer
        Customer newCustomer = new Customer(customerAmnt + 1, "Aleksander", "Roder", "Norway", "7014", "99347308",
                "example@epost.no");
        repo.create(newCustomer);
        System.out.println(repo.getById(customerAmnt + 1));

        //Run the update customer method by modifying the previously made customer with a new last name
        Customer updatedCustomer = new Customer(newCustomer.customerId(), newCustomer.firstName(), "fylling",
                newCustomer.country(), newCustomer.postalCode(), newCustomer.phone(), newCustomer.email());
        repo.update(updatedCustomer);
        System.out.println(repo.getById(customerAmnt + 1));

        //Run the getCustomerPage by retrieving a page of Customers and printing them.
        for (Customer customer : repo.getCustomerPage(10, 10)) {
            System.out.println(customer);
        }

        //Running the getCountryWithMostCustomers by printing the result.
        System.out.println(repo.getCountryWithMostCustomers());
        //Running the getMostPopularGenre by printing the result.
        System.out.println(repo.getMostPopularGenre(5));
        //Running the getHighestSpendingCustomer by printing the result.
        System.out.println(repo.getHighestSpendingCustomer());
    }

}
