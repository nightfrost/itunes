package com.example.ttunes.data_access;

import com.example.ttunes.logger.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //Open DB connection, return the connection.
    public Connection conn(){

       String url = "jdbc:sqlite::resource:Chinook_Sqlite.sqlite";
        try {
            new Logger().log("Connection to SQLite has been established.");
            return  DriverManager.getConnection(url);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
