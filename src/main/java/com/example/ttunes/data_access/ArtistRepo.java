package com.example.ttunes.data_access;

import com.example.ttunes.logger.Logger;
import com.example.ttunes.models.Artist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepo {

    // the connection that is used to perform each db operation
    private final DBConnection dbConnection = new DBConnection();

    private final Logger logger = new Logger();

    //Get list of 5 random artists
    public List<Artist> getRandArtists() {
        List<Artist> artists = new ArrayList<>();
        String query = "select * from Artist order by random() limit 5;";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    artists.add(new Artist(
                            rs.getInt(1),
                            rs.getString(2)
                    ));
                }

            }
            logger.log("Select 5 random artists successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return artists;
    }

}
