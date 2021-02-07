package com.example.ttunes.data_access;

import com.example.ttunes.logger.Logger;
import com.example.ttunes.models.Country;
import com.example.ttunes.models.Customer;
import com.example.ttunes.models.Spender;
import com.example.ttunes.models.TopGenre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    // the connection that is used to perform each db operation
    private final DBConnection dbConnection = new DBConnection();

    private final Logger logger = new Logger();

    //Get list of all customers
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    customers.add(new Customer(
                            rs.getInt("CustomerId"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Country"),
                            rs.getString("PostalCode"),
                            rs.getString("Phone"),
                            rs.getString("Email")
                    ));
                }

            }
            logger.log("Select all customers successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return customers;
    }

    //Get specific customer from DB
    public Customer getCustomer(int customerId) {
        Customer customer = null;
        String query = "SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE CustomerId = ?";

        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    customer = new Customer(
                            rs.getInt("CustomerId"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Country"),
                            rs.getString("PostalCode"),
                            rs.getString("Phone"),
                            rs.getString("Email")
                    );
                }

            }
            logger.log("Select specific customer successful");
        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return customer;
    }


    //Check wether customer exists or not. Returns 1 if true, 0 if not.
    public int isExist(int customerId) {
        int count = 0;
        String query = "SELECT count(*) FROM Customer WHERE CustomerId = ?";

        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }

            }
            logger.log("Check if specific customer exist successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return count;
    }

    //Add new customer to DB
    public boolean addNewCustomer(Customer customer) {
        boolean status = true;
        String query = "INSERT into Customer ( FirstName, LastName, Country, PostalCode, Phone, Email) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCountry());
            ps.setString(4, customer.getPostalCode());
            ps.setString(5, customer.getPhoneNumber());
            ps.setString(6, customer.getEmail());

            ps.executeUpdate();
            logger.log("Add specific customer successful");


        } catch (SQLException e) {
            logger.log(e.toString());
            status = false;
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }

        return status;
    }

    //Update existing customer
    public boolean updateCustomer(Customer updatedCustomer) {
        boolean status = true;
        String query = "UPDATE Customer set FirstName = ?, LastName = ?, Country=?, PostalCode =?, Phone =?,Email=? where CustomerId = ?";

        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {

            ps.setString(1, updatedCustomer.getFirstName());
            ps.setString(2, updatedCustomer.getLastName());
            ps.setString(3, updatedCustomer.getCountry());
            ps.setString(4, updatedCustomer.getPostalCode());
            ps.setString(5, updatedCustomer.getPhoneNumber());
            ps.setString(6, updatedCustomer.getEmail());
            ps.setInt(7, updatedCustomer.getCustomerId());

            ps.executeUpdate();
            logger.log("Update specific customer successful");

        } catch (SQLException e) {
            logger.log(e.toString());
            status = false;
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return status;
    }

    //Get list of countries with number of costumers in each country, DESC order.
    public List<Country> getCountriesAndNumberOfCustomers() {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT  Country, count(*)  AS NumberOfCustomers from Customer group by Country order by NumberOfCustomers desc ;";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    countries.add(new Country(
                            rs.getString("Country"),
                            rs.getInt("NumberOfCustomers")
                    ));
                }
            }
            logger.log("Select Countries with number of customers successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return countries;
    }

    //Return a list of the most spending customers by summing the total spending for each customer. Order DESC.
    public List<Spender> getSpenders() {
        List<Spender> spenders = new ArrayList<>();
        String query = "SELECT  Customer.CustomerId,Customer.FirstName,Customer.LastName, SUM(Invoice.Total) AS Spending\n" +
                "                FROM Invoice\n" +
                "                         INNER JOIN Customer on Invoice.CustomerId = Customer.CustomerId GROUP BY Customer.CustomerId ORDER BY Spending DESC;";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    spenders.add(new Spender(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            Math.round(rs.getDouble(4))
                    ));
                }

            }
            logger.log("Select most spending customers successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return spenders;
    }

    //Returns a list of top genres for a specific customer in DESC order.
    public List<TopGenre> getMostPopularGenreForSpecCustomer(int customerId) {
        List<TopGenre> topGenres = new ArrayList<>();
        String query = "SELECT GenreId, Name AS Genre, Number\n" +
                "FROM (SELECT G.GenreId, G.Name, COUNT(G.Name) AS Number\n" +
                "      FROM Customer\n" +
                "               LEFT JOIN Invoice I on Customer.CustomerId = I.CustomerId\n" +
                "               LEFT JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId\n" +
                "               LEFT JOIN Track T on T.TrackId = IL.TrackId\n" +
                "               LEFT JOIN Genre G on G.GenreId = T.GenreId\n" +
                "      WHERE Customer.CustomerId = ?\n" +
                "      GROUP BY G.Name\n" +
                "      ORDER BY Number DESC)\n" +
                "WHERE Number = (SELECT MAX(Number)\n" +
                "                FROM (SELECT  G.Name, COUNT(G.Name) AS Number\n" +
                "                      FROM Customer\n" +
                "                               LEFT JOIN Invoice I on Customer.CustomerId = I.CustomerId\n" +
                "                               LEFT JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId\n" +
                "                               LEFT JOIN Track T on T.TrackId = IL.TrackId\n" +
                "                               LEFT JOIN Genre G on G.GenreId = T.GenreId\n" +
                "                      WHERE Customer.CustomerId = ?\n" +
                "                      GROUP BY G.Name\n" +
                "                      ORDER BY Number DESC))";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    topGenres.add(
                            new TopGenre(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getInt(3)
                            )
                    );
                }
            }

            logger.log("Select top generes successful");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return topGenres;
    }

}
