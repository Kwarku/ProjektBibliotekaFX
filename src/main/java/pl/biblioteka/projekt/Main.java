package pl.biblioteka.projekt;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.utils.FillDatabase;
import pl.biblioteka.projekt.utils.FxmlUtils;


public class Main extends Application {

    private static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {

//        Locale.setDefault(new Locale("en"));

        Scene scene = new Scene(FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML));
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("title.application"));
        primaryStage.show();

        DbManager.initDatabase();
        FillDatabase.fillDatabase();

    }
}
