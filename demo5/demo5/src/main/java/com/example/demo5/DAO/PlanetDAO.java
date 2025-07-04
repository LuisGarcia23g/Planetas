package com.example.demo5.DAO;

import com.example.demo5.Planet;
import com.example.demo5.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlanetDAO {
    public void savePlanet(Planet planet) throws SQLException {
        String sql = "INSERT INTO Planets (name, mass, moonCount, image) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, planet.getName());
            stmt.setDouble(2, planet.getMass());
            stmt.setInt(3, planet.getNumberOfMoons());
            stmt.setString(4, planet.getImageUrl());

            stmt.executeUpdate();
        }
    }

    public List<Planet> getAllPlanets() throws SQLException {
        List<Planet> planets = new ArrayList<>();
        String sql = "SELECT name, mass, moonCount, image FROM Planets";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                planets.add(new Planet(
                        rs.getString("name"),
                        rs.getDouble("mass"),
                        rs.getInt("moonCount"),
                        rs.getString("image")
                ));
            }
        }

        return planets;
    }

}
