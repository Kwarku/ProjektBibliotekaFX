package pl.biblioteka.projekt.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    public static void dialogAboutApplication() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("about.title"));
        informationAlert.setHeaderText(bundle.getString("about.header"));
        informationAlert.setContentText(bundle.getString("about.content"));

        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog() {
        return getButtonType(bundle.getString("exit.title"), bundle.getString("exit.header"));
    }

    public static Optional<ButtonType> deleteConfirmationDialog() {
        return getButtonType(bundle.getString("delete.title"), bundle.getString("delete.header"));
    }

    private static Optional<ButtonType> getButtonType(String title, String header) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(header);

        return confirmationDialog.showAndWait();
    }

    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();
    }

    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        // pobieramy stringa i jezeli jest to go zwracamy jezeli nie to zwracamy null
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}














