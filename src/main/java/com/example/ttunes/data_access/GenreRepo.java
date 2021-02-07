package com.example.ttunes.data_access;

import com.example.ttunes.logger.Logger;
import com.example.ttunes.models.TopGenre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepo {
    // the connection that is used to perform each db operation
    private final DBConnection dbConnection = new DBConnection();

    private final Logger logger = new Logger();

    //Get a list of 5 random artists
    public List<TopGenre> getRandGenres() {
        List<TopGenre> genres = new ArrayList<>();
        String query = "select Name from Genre order by random() limit 5;";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    genres.add(new TopGenre(
                            rs.getString(1)
                    ));
                }

            }
            logger.log("Select 5 random genres successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return genres;
    }
}
