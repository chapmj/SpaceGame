package gamecontroller.input;

public interface KeySubscriber {
    void notifyPress(char key);
    void notifyRelease(char key);
}
