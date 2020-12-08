package game.frontend;

import javafx.application.Platform;
import javafx.scene.control.*;
import java.util.Optional;

public class AppMenu extends MenuBar {

    public AppMenu() {
        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        file.getItems().add(exitMenuItem);
        Menu levels = new Menu("Niveles");
        MenuItem level1 = new MenuItem("Clásico");
        MenuItem level2 = new MenuItem("GoldenBoard");
        MenuItem level3 = new MenuItem("TimeBomb");
        MenuItem level4 = new MenuItem("TimeLimit");

        levels.getItems().addAll(level1, level2, level3, level4);

        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2020.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).\n" +
                    "Implementación extendida: Ezequiel Pérez y Gonzalo Baliarda");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);
        getMenus().addAll(file, levels ,help);

    }

}
