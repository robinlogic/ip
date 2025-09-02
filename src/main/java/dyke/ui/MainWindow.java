package dyke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dyke dyke;

    private Image USER_IMAGE =
            new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image DYKE_IMAGE =
            new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDyke(Dyke d) {
        dyke = d;

        // Show welcome message immediately
        dialogContainer.getChildren().add(DialogBox.getDykeDialog(
                dyke.welcome(), DYKE_IMAGE));

        // Load tasks and show info
        String loadMessage = dyke.init();
        dialogContainer.getChildren().add(DialogBox.getDykeDialog(loadMessage, DYKE_IMAGE));

        // Scroll to bottom
        //scrollPane.setVvalue(1.0);

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dyke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDykeDialog(response, DYKE_IMAGE)
        );
        userInput.clear();
    }
}
