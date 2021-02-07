package com.example.ttunes.data_access;

import com.example.ttunes.logger.Logger;
import com.example.ttunes.models.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackRepo {
    // the connection that is used to perform each db operation
    private final DBConnection dbConnection = new DBConnection();

    private final Logger logger = new Logger();

    //Return a list of 5 random artists
    public List<Track> getRandTracks() {
        List<Track> tracks = new ArrayList<>();
        String query = "select TrackId, Name, UnitPrice from Track order by random() limit 5;";
        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    tracks.add(new Track(
                            rs.getInt("TrackId"),
                            rs.getString("Name"),
                            rs.getDouble("UnitPrice")
                    ));
                }
            }
            logger.log("Select 5 random tracks successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return tracks;
    }

    //returns a list of track names that matches the search word, or has the letters in it
    public List<Track> getSearchedTrack(String name) {
        List<Track> tracks = new ArrayList<>();
        String query = "select Track.Name, A2.Name, A.Title, G.Name\n" +
                "from Track\n" +
                "         inner join Genre G on G.GenreId = Track.GenreId\n" +
                "         inner join Album A on Track.AlbumId = A.AlbumId\n" +
                "         inner join Artist A2 on A.ArtistId = A2.ArtistId where Track.Name like '%" + name + "%';";

        try (PreparedStatement ps = dbConnection.conn().prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    tracks.add(new Track(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4)
                    ));
                }
            }
            logger.log("Search tracks successful");

        } catch (SQLException e) {
            logger.log(e.toString());
        } finally {
            try {
                dbConnection.conn().close();
            } catch (SQLException e) {
                logger.log(e.toString());
            }
        }
        return tracks;
    }

}
