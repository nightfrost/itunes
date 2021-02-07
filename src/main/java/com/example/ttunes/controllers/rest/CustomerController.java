package com.example.ttunes.controllers.rest;

import com.example.ttunes.models.Country;
import com.example.ttunes.models.Customer;
import com.example.ttunes.models.Spender;
import com.example.ttunes.models.TopGenre;
import com.example.ttunes.services.CustomerServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
// setting the root path
@RequestMapping(value = "api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private CustomerServices cs = new CustomerServices();

    //Get list of costumers
    @GetMapping
    public List<Customer> getCustomers() {
        return cs.getCustomers();
    }

    //Add Customer POST
    @PostMapping
    public String addCustomer(@RequestBody() Customer customer) {
        if (cs.addCustomer(customer))
            return "Added";
        else
            return "Please try again";
    }

    //Get exact customer
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        return new CustomerServices().getCustomer(customerId);
    }

    //Update customer POST
    @PutMapping("/{customerId}")
    public String updateCustomer(@PathVariable int customerId, @RequestBody() Customer customer) {
        customer.setCustomerId(customerId);
        if (cs.updateCustomer(customer))
            return "Updated";
        return "Please try again";

    }

    //Get Customer top genre
    @GetMapping("/{customerId}/genre")
    public List<TopGenre> getTopGenre(@PathVariable int customerId) {
        return cs.getMostPopularGenre(customerId);

    }

    //Get Spenders
    @GetMapping("/spenders")
    public List<Spender> getSpenders() {
        return cs.getSpendingCustomers();
    }

    @GetMapping("/countries")
    public List<Country> getCustomersInCountries() {
        return cs.getNumberOfCustomerInCountries();
    }
}
