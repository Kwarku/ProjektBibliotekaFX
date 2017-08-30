package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
/*
*           WCZYTANIE NOWEJ FORMATKI W OGLNIE GLOWNYM
*  - tworzymy metode publiczna w klasie main contorller np. setCenter
*  - przekazujemy jej jako parametr Stringa z sciezka do pliku fxml
*  - nastepnie wzyczajnie wczytujemy plik fxml uzyuwajac FXMLLoadera
*  - zamiast wymyslac jaki kontener  uzyjemy uzywamy Parent jako rodzica wszystkich kontenerow
*  - jako sciezke podajemy nasz parametr z metody
*  - obslugujemy wyjatek
*  - do borderPane albo innej czesci naszego glownego kontenera dodajemy wczytana formatke
*  - w interesujacej nas klasie powolujemy sie na ta metode i wczytujemy pieknie ze sciezki
*
* */


public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    @FXML
    private void initialize(){
        topMenuButtonsController.setMainController(this);
    }

    public void setCenter(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            System.err.println("nie mozna wczytac okna centralnego dla border pane");
            e.printStackTrace();
        }
        borderPane.setCenter(parent);

    }


}
