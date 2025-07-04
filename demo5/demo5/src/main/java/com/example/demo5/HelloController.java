package com.example.demo5;


import com.example.demo5.DAO.MoonDAO;
import com.example.demo5.DAO.PlanetDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML private TilePane tilePane;

    private ObservableList<SpaceEntity> entities;

    @FXML
    public void initialize() throws Exception {
        PlanetDAO planetDAO = new PlanetDAO();
        MoonDAO moonDAO = new MoonDAO();

        entities = FXCollections.observableArrayList();
        entities.addAll(planetDAO.getAllPlanets());
        entities.addAll(moonDAO.getAllMoons());

        for (SpaceEntity entidad : entities) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("space-cell.fxml"));
            Node cell = loader.load();
            SpaceController ctrl = loader.getController();

            String imageUrl = entidad.getImageUrl() != null
                    ? entidad.getImageUrl()
                    : "/images/default.png";
            ctrl.setData(entidad, imageUrl);

            tilePane.getChildren().add(cell);
        }
    }

    public ObservableList<SpaceEntity> getEntities() {
        return entities;
    }

    public void addEntity(SpaceEntity entity) {
        entities.add(entity);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("space-cell.fxml"));
            Node cell = loader.load();
            SpaceController ctrl = loader.getController();

            String imageUrl = entity.getImageUrl() != null
                    ? entity.getImageUrl()
                    : "/images/default.png";
            ctrl.setData(entity, imageUrl);

            tilePane.getChildren().add(cell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openAddPlanetForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form-add-planet.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        AddPlanetController addCtrl = loader.getController();
        addCtrl.setMainController(this);

        stage.setTitle("Agregar Nueva Entidad");
        stage.show();
    }
}