package dyke.ui;

/**
 * FakeUi created for testing
 */
public class FakeUi extends Ui {
    public String lastMessage = "";
    @Override
    public void printMessage(String msg) {
        lastMessage = msg;
    }
}
