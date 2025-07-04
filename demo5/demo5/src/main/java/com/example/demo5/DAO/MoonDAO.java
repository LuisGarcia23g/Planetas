package com.example.demo5.DAO;

import com.example.demo5.Moon;
import com.example.demo5.Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoonDAO {
    public void insertarMoon(Moon moon) throws SQLException {
        String sql = "INSERT INTO Moons (name, mass, parentPlanet, image) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, moon.getName());
            stmt.setDouble(2, moon.getMass());
            stmt.setString(3, moon.getParentPlanet());
            stmt.setString(4, moon.getImageUrl());

            stmt.executeUpdate();
        }
    }

    public List<Moon> getAllMoons() throws SQLException {
        List<Moon> moons = new ArrayList<>();
        String sql = "SELECT name, mass, parentPlanet, image FROM Moons";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                moons.add(new Moon(
                        rs.getString("name"),
                        rs.getDouble("mass"),
                        rs.getString("parentPlanet"),
                        rs.getString("image")
                ));
            }
        }

        return moons;
    }
}