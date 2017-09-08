package pl.biblioteka.projekt;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.utils.FxmlUtils;
import pl.biblioteka.projekt.utils.exceptions.FillDatabase;


public class Main extends Application {

    public static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

        Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DbManager.initDatabase();
        FillDatabase.fillDatabase();

    }
}
