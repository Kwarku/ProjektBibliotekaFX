package pl.biblioteka.projekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;


/*      REFERENCJA
* Aby dodac mozliwosc sterowania przyciska z klasy TopMenuButton nalezy:
* - stworzyc puste okno np BorderPane
* - metoda include dodac tam caly plik fxml z innym wygladem
* - od razu dodac fx:id dla tego obiektu
* o tak: <fx:include source="TopMenuButtons.fxml" fx:id="topMenuButtons" />
* - wstrzyknac to dodanie do klasy glownego controllera
*
* - nastepnie dodanie id dla glownego kontenera (borderPane)
* -wstrzykniecie tego kontenera do jego controllera
* -stworzyc metoda initialize w klasie glownego controllera
*
* - stworzenie pola MainControllera w klasie TopMenuButtonsController
* - stworznie settera do tego pola prywatnego
*
* - w metodzie inizlialize po zaladowaniu kontrollera przekazujemy referencje
* topMenuButtonsController.setMainController(this);
*
* i to juz dziala
*
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

}
