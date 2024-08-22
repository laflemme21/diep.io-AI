

import java.awt.Robot;

public class Mouse {
    private Robot robot;

    public Mouse() {
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void moveTo(int[] position, Screenshot screenshot) {
        robot.mouseMove(position[0] + screenshot.getSize()[0],
                position[1] + screenshot.getSize()[1]);
    }
}
