import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyPressDetector implements NativeKeyListener {
    private static boolean keyPressed = false;

    public void startListening() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed && keyCode == NativeKeyEvent.VC_Q; // Change VC_Q to the key you want to check
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_Q) { // Change VC_Q to the key you want to check
            keyPressed = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_Q) { // Change VC_Q to the key you want to check
            keyPressed = false;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Do nothing
    }
}
