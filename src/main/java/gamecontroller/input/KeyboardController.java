package gamecontroller.input;

import javafx.scene.input.KeyEvent;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.Collections.emptyList;

/***
 * Receive input, send commands to _update queue
 */
public class KeyboardController {

    //init:  register publishers
    //_update: trap input on _update
    //notify publishers

    public static KeyboardController instance;

    Map<Character, List<KeySubscriber>> subscribers;
    Queue<KeyEvent> qEvents = new ArrayBlockingQueue<>(4);


    String KEY_PRESSED = KeyEvent.KEY_PRESSED.getName();
    String KEY_RELEASED = KeyEvent.KEY_RELEASED.getName();

    private KeyboardController(Map<Character, List<KeySubscriber>> subscribers) {
        this.subscribers = subscribers;
    }

    public static KeyboardController getInstance() {

        if(instance == null) {
            instance = new KeyboardController(new Hashtable<>());
        }
        return instance;
    }

    public static void init() {
        getInstance();
    }

    public void receiveKeyEvent(KeyEvent keyEvent) {
        qEvents.add(keyEvent);
    }

    public void update() {

        while(!qEvents.isEmpty()) {
            var event = qEvents.remove();
            var keyChar = (char) event.getCode().getCode();

            if (event.getEventType().getName().equals(KEY_PRESSED)) {
                subscribers.getOrDefault(keyChar, emptyList()).forEach((keySubscriber) -> keySubscriber.notifyPress(keyChar));
            }
            else if (event.getEventType().getName().equals(KEY_RELEASED)) {
                subscribers.getOrDefault(keyChar, emptyList()).forEach((keySubscriber) -> keySubscriber.notifyRelease(keyChar));
            }
        }

    }


    public void createSubscription(KeySubscriber keySubscriber, char key) {

        if(key == 0) {
            Objects.requireNonNull(null);
        }

        subscribers.put(key,Collections.singletonList(keySubscriber));

    }
}
