package pl.biblioteka.projekt.modelFx;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// ta klasa bedzei odzwierciedleniem bazy dancyh te same pola tylko to beda properties
public class CategoryFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // dzieki metodzie wypisania spokojnie obiekty w comboboxie sie wypisuja
    @Override
    public String toString() {
        return getName();
    }
}
