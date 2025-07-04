package com.example.demo5;


import com.example.demo5.DAO.MoonDAO;
import com.example.demo5.DAO.PlanetDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class AddPlanetController {
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField nameField;
    @FXML private TextField massField;
    @FXML private TextField imageField;
    @FXML private TextField moonCountField;
    @FXML private ComboBox<String> parentPlanetCombo;

    private HelloController mainController;

    public void setMainController(HelloController controller) {
        this.mainController = controller;
    }

    @FXML
    private void initialize() {
        typeComboBox.getItems().addAll("Planeta", "Luna");

        typeComboBox.valueProperty().addListener((obs, old, nuevo) -> {
            boolean isPlanet = "Planeta".equals(nuevo);

            moonCountField.setVisible(isPlanet);
            parentPlanetCombo.setVisible(!isPlanet);

            if (!isPlanet) {
                List<String> planetNames = mainController.getEntities().stream()
                        .filter(e -> e instanceof Planet)
                        .map(SpaceEntity::getName)
                        .collect(Collectors.toList());
                parentPlanetCombo.setItems(FXCollections.observableArrayList(planetNames));
            }
        });
    }

    @FXML
    private void addEntity() throws SQLException {
        String tipo = typeComboBox.getValue();
        String nombre = nameField.getText().trim();
        double masa = Double.parseDouble(massField.getText().trim());
        String imagen = imageField.getText().trim();

        SpaceEntity nuevaEntidad;
        if ("Planeta".equals(tipo)) {
            int numLunas = Integer.parseInt(moonCountField.getText().trim());
            Planet planeta = new Planet(nombre, masa, numLunas, imagen);
            new PlanetDAO().savePlanet(planeta);  //Agrega a la base de datos
            nuevaEntidad = planeta;
        } else {
            String planetaPadre = parentPlanetCombo.getValue();
            Moon luna = new Moon(nombre, masa, planetaPadre, imagen);
            new MoonDAO().insertarMoon(luna);  //Agrega a la base de datos
            nuevaEntidad = luna;
        }

        mainController.addEntity(nuevaEntidad);

        clearFields();
        nameField.getScene().getWindow().hide();
    }

    private void clearFields() {
        nameField.clear();
        massField.clear();
        imageField.clear();
        moonCountField.clear();
        parentPlanetCombo.getSelectionModel().clearSelection();
        typeComboBox.getSelectionModel().clearSelection();
    }
}