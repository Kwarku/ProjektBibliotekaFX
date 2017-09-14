package pl.biblioteka.projekt;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.biblioteka.projekt.controllers.MainController;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.utils.FillDatabase;
import pl.biblioteka.projekt.utils.FxmlUtils;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

//        Locale.setDefault(new Locale("en"));

        Scene scene = new Scene(FxmlUtils.fxmlLoader("/fxml/BorderPaneMain.fxml"));
        scene.getStylesheets().add(MainController.BRIGHT_THEME_CSS);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DbManager.initDatabase();
        FillDatabase.fillDatabase();

    }

}
