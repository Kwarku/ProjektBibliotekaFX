package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.biblioteka.projekt.database.dao.AuthorDao;
import pl.biblioteka.projekt.database.dao.CategoryDao;
import pl.biblioteka.projekt.database.models.Author;
import pl.biblioteka.projekt.database.models.Category;
import pl.biblioteka.projekt.utils.converters.AuthorConverter;
import pl.biblioteka.projekt.utils.converters.CategoryConverter;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.util.List;

/*Dodanie obslugi klasy w bazie danych
    * tworzymy BookFX jako odpowiednik klasy Book ktora znajduje sie do obslugi samej bazy danych i przypisujemy jej property do
    * kazdej zmiennej znajdujacej sie w bazie Integer, Sting Object itd
    * tworzymy gettery settery do wszystkiego
    * nastpenie tworzymy klase BookModel i tworzymy ObjectProperty do klasy bookFx
    * private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());
    * koniecznie najwazniejsze dodac nowa klase bookFx parametrach propertki
    * tworzymy getery settery do wszystkeigo
    * nastpenie w klasie controllera wsrzytkujemy potrzbene elementy
    * dodajemy instancje klasy BookModel (BookModel bookModel = new BookModel())
    * bindujemy kazda wartosc z klasy BookFx z naszymi oknami do sterowania
    *  this.bookModel.bookFxObjectPropertyProperty().get().authorFxProperty().bind(this.authorComboBox.valueProperty());

    * */
public class BookModel {

    private ObjectProperty<BookFx> bookFxObjectProperty = new SimpleObjectProperty<>(new BookFx());

    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFx> authorFxObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException {
        initAuthorList();
        initCategoryList();

    }

    /*przypisanie potrzebne dla obslugi ComboBoxa
    * otwiedzmay polaczenie z baza dancyh nastepnie tworzymy lsite obiektow klasy z bazy danych
    * i szukamy po wszystkich obietkach
    * czyscimy ObservableList klasy CategoryFX
    * dla wszytkich obiektow tworzymy nowy obiekt klasy CategoryFx i uzywajac konwertego konwertujemy go
    * dodajemy do listy categoryFX ObservableList
    * wyjatki przekazujemy dalej az by moc je obsluzyc w klasie kontrollera
    * zamykamy połączenie */
    private void initCategoryList() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categoryList = categoryDao.queryForAll(Category.class);
        categoryFxObservableList.clear();
        categoryList.forEach(category -> {
            CategoryFx categoryFx = CategoryConverter.convertToCategoryFx(category);
            categoryFxObservableList.add(categoryFx);
        });

    }

    private void initAuthorList() throws ApplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        authorFxObservableList.clear();
        authorList.forEach(author -> {
            AuthorFx authorFx = AuthorConverter.convertToAuthorFx(author);
            authorFxObservableList.add(authorFx);
        });


    }

    public BookFx getBookFxObjectProperty() {
        return bookFxObjectProperty.get();
    }

    public ObjectProperty<BookFx> bookFxObjectPropertyProperty() {
        return bookFxObjectProperty;
    }

    public void setBookFxObjectProperty(BookFx bookFxObjectProperty) {
        this.bookFxObjectProperty.set(bookFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<AuthorFx> getAuthorFxObservableList() {
        return authorFxObservableList;
    }

    public void setAuthorFxObservableList(ObservableList<AuthorFx> authorFxObservableList) {
        this.authorFxObservableList = authorFxObservableList;
    }
}
