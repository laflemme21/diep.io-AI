
public class diepBot {
    public static void main(String[] args) {
        Thread aimingThread = new Thread(new aiming());

        aimingThread.start();
    }
}
