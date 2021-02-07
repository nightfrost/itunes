package com.example.ttunes.services;

import com.example.ttunes.data_access.CustomerRepo;
import com.example.ttunes.models.Country;
import com.example.ttunes.models.Customer;
import com.example.ttunes.models.Spender;
import com.example.ttunes.models.TopGenre;

import java.util.List;

//Middle-man class for customer repo and customer controller
public class CustomerServices {
    // instance of the customer repo to use its methods
    private final CustomerRepo customerRepo = new CustomerRepo();

    //Check if list is empty, if not , return it.
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepo.getCustomers();
        if (customers.size() > 0)
            return customers;
        return null;
    }

    //Check if customer exists, if true, return costumer
    public Customer getCustomer(int customerId) {
        if (customerRepo.isExist(customerId) > 0) {
            return customerRepo.getCustomer(customerId);
        }
        return null;
    }

    //Check if customer object is null, if not, return addNewCustomer
    public boolean addCustomer(Customer customer) {
        if (customer != null) {
            return customerRepo.addNewCustomer(customer);
        }
        return false;
    }

    //Check if customer object is null, if not, return UpdateCustomer
    public boolean updateCustomer(Customer customer) {
        if (customer != null) {
            if (customerRepo.isExist(customer.getCustomerId()) > 0)
                return customerRepo.updateCustomer(customer);
        }
        return false;
    }


    //Check if list is empty, if not, return getNumberOfCustomerInCountries
    public List<Country> getNumberOfCustomerInCountries() {
        List<Country> countries = customerRepo.getCountriesAndNumberOfCustomers();
        if (countries.size() != 0)
            return countries;
        return null;
    }

    //Check if list is empty, if not, return getSpendingCustomers
    public List<Spender> getSpendingCustomers() {
        List<Spender> spendingCustomers = customerRepo.getSpenders();
        if (spendingCustomers.size() != 0)
            return spendingCustomers;
        return null;
    }

    //Checks if customer exists, if yes, return getMostPopularGenreForSpecCustomer
    public List<TopGenre> getMostPopularGenre(int cusomterId) {
        List<TopGenre> topGenres = null;
        if (customerRepo.isExist(cusomterId) > 0) {
            topGenres = customerRepo.getMostPopularGenreForSpecCustomer(cusomterId);
            if (topGenres.size() != 0)
                return topGenres;
        }
        return topGenres;
    }


}
