package pl.biblioteka.projekt;

import com.sun.istack.internal.localization.Localizable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/*      INTERNACJONALIZACJA
* Aby wprowadzic mozliwosc zmiany jezyka w calej aplikacji nalezy:
* - upewnic sie ze mamy poprawny sposob kodowania w srodowisku ( w intellij to Editor -> file encodings UTF-8 wszedzie)
* - w folderze resources stworzyc nowy folder o nazwie bundles
* - stworzyc nowy i wybrac Resource Bundle
* - na gorze wybieramy nazwe, i po prawej dodajemy lokalizajce pl (jezeli chcemy wiecej jezykow luz)
* - powstaja pliki do kazdego jezyka i default, gdy system nie wykryje wlasciwej lokalizacji uzyje default
* - wchodzimy do pliku, na dole wybieramy z zakladki Resource Bundle i:
* - dodajemy klucz nastepnie dodajemy tresc czyli napis jaki ma byc: np title.app -> default Application, pl Aplikacja
*
* - podpinamy w klasie main nasze bundle
* - uzywamy klasy ResourceBundles i metody statycznej getBundle o tak:
*   ResourceBundle XXXXXXX = ResourceBundle.getBundle("bundles.messages")
* - podajemy sciezke tylko do glownej nazwy tych bundlesow
*
* - nastepnie uzywamy loadera zeby dodac resoursy
*   loader.setResources(resourceBundle);
*
* - teraz gdy chcemy uzyc tego tytulu w pliku javy nalezy uzyc metody XXXXX.getString
*  oraz podac klucz tego stringa np "title.application" no i dziala
*
* ABY podac to w Scene Builderze nalezy :
* - w zakladce Preview - > Internaationalien -> set Resources i podac nasz wybrany plik jezykowy
* - wejsc w dowolny np przycisk z napisem
* - wybrac rubryke Text
* - z opcji wybrac Replace with Internacionalize String
* - podac interesujacy nas klucz
*
* - aby sprawdzic czy dziala nalezy zmienic lokalizacje:
* sluzy do tego metoda Locale.setDefault(new Locale("en" albo inny jezyk ))
*
* */


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
//        Locale.setDefault(new Locale("en"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/BorderPaneMain.fxml"));

        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);

        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("title.application"));
        primaryStage.show();


    }
}
