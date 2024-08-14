package Keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class Keyboard {
    private static volatile boolean qPressed = false;


    public static boolean isQPressed() {
        synchronized (Keyboard.class) {
            return qPressed;
        }
    }

    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (Keyboard.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_Q) {
                                qPressed = true;
                            }
                            break;
                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_Q) {
                                qPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });

        // Your other application code here
    }
}
