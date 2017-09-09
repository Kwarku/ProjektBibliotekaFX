package pl.biblioteka.projekt.utils.converters;


import pl.biblioteka.projekt.database.models.Category;
import pl.biblioteka.projekt.modelFx.CategoryFx;

public class CategoryConverter {

    public static CategoryFx convertToCategoryFx(Category category) {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());

        return categoryFx;
    }
}
