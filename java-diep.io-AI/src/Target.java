

public class Target {
    protected int[] color;
    protected int[] position;
    private int step;
    private int[] screenshotSize;
    private Screenshot screenshot;

    public Target(int[] c, int[] p, int s, int[] scrSize) {
        color = c;
        position = p;
        step = s;
        screenshotSize = scrSize;
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

    public void followTarget(){
        System.out.println("hi1");
        System.out.println("bye1");
        System.out.println("---`1");
        int x=position[0]-screenshotSize[0]/2;
        int y=position[1]-screenshotSize[1]/2;
        if (x<0) {
            x=0;
        }
        if (y<0) {
            y=0;
        }      
        if(x+screenshotSize[0]>1440){
            x=1440-screenshotSize[0];
        }
        if(y+screenshotSize[1]>900){
            y=900-screenshotSize[1];
        }
        screenshot=new Screenshot(x, y, screenshotSize[0], screenshotSize[1],"followTarget");
        this.locate(screenshot);

    }

}
