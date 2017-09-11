package pl.biblioteka.projekt.modelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BookFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<AuthorFx> authorFx = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFx = new SimpleObjectProperty<>();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> releaseDate = new SimpleObjectProperty<>();
    private StringProperty isbn = new SimpleStringProperty();
    private IntegerProperty note = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>(LocalDate.now());

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public AuthorFx getAuthorFx() {
        return authorFx.get();
    }

    public ObjectProperty<AuthorFx> authorFxProperty() {
        return authorFx;
    }

    public void setAuthorFx(AuthorFx authorFx) {
        this.authorFx.set(authorFx);
    }

    public CategoryFx getCategoryFx() {
        return categoryFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxProperty() {
        return categoryFx;
    }

    public void setCategoryFx(CategoryFx categoryFx) {
        this.categoryFx.set(categoryFx);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getNote() {
        return note.get();
    }

    public IntegerProperty noteProperty() {
        return note;
    }

    public void setNote(int note) {
        this.note.set(note);
    }

    public LocalDate getReleaseDate() {
        return releaseDate.get();
    }

    public ObjectProperty<LocalDate> releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public LocalDate getAddedDate() {
        return addedDate.get();
    }

    public ObjectProperty<LocalDate> addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate.set(addedDate);
    }

    @Override
    public String toString() {
        return "BookFx{" +
                "id=" + id.get() +
                ", authorFx=" + authorFx.get() +
                ", categoryFx=" + categoryFx.get() +
                ", title=" + title.get() +
                ", description=" + description.get() +
                ", releaseDate=" + releaseDate.get() +
                ", isbn=" + isbn.get() +
                ", note=" + note.get() +
                ", addedDate=" + addedDate.get() +
                '}';
    }
}

