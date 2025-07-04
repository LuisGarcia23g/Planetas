package com.example.demo5;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class SpaceController {
    @FXML
    private ImageView imgEntity;
    @FXML private Label lblName;
    @FXML private Label lblMass;

    public void setData(SpaceEntity entity, String imagePathOrUrl) {
        lblName.setText(entity.getName());
        lblMass.setText(String.format("Masa: %.2e kg", entity.getMass()));

        Image image = null;
        try {
            if (imagePathOrUrl.startsWith("http")) {
                image = new Image(imagePathOrUrl,
                        100,
                        100,
                        true,
                        true,
                        false
                );
            } else {
                InputStream is = getClass().getResourceAsStream(imagePathOrUrl);
                if (is != null) {
                    image = new Image(is);
                }
            }

            // Comprueba si hubo error interno
            if (image != null && image.isError()) {
                System.err.println("Error cargando imagen");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (image != null) {
            imgEntity.setImage(image);
        }
    }

}
