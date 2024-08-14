package Target;

import Screenshot.Screenshot;

public class Target {
    protected int[] color;
    protected int[] position;
    private int step;

    public Target(int[] c, int[] p, int s) {
        color = c;
        position = p;
        step = s;
    }

    public void locate(Screenshot screenshot) {
        position = screenshot.findColor(color);
    }

    public int[] getPosition() {
        return position;
    }

    public int getX() {
        return position[0];
    }

    public int getY() {
        return position[1];
    }

}
