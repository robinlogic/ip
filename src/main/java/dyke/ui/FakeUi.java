package dyke.ui;

public class FakeUi extends Ui {
    public String lastMessage = "";
    @Override
    public void printMessage(String msg) {
        lastMessage = msg;
    }
}
