

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
        this.position = screenshot.findColor(color,step);
    }

    public int[] getPosition() {
        return this.position;
    }

    public int getX() {
        return this.position[0];
    }

    public int getY() {
        return this.position[1];
    }

    public void followTarget(){
        int x=position[0]-screenshotSize[0]/2;
        int y=position[1]-screenshotSize[1]/2;
        if (x<0) {
            x=0;
        }
        if (y<0) {
            y=0;
        }      
        if(x+screenshotSize[0]>1440){
            x=1439-screenshotSize[0];
        }
        if(y+screenshotSize[1]>900){
            y=899-screenshotSize[1];
        }
        screenshot=new Screenshot(x, y, screenshotSize[0], screenshotSize[1],"followTarget");
        int[] followPosition=screenshot.findColor(color,2);
        if(followPosition[0]!=-1){
            this.position=screenshot.centerOfShape(this.color,followPosition);
        }
        else{
            if(screenshot.findColor(new int[] {236,239,253}, y)[0]==-1&&screenshot.findColor(new int[] {224,81,50}, y)[0]==-1){
            this.position=followPosition;}
        }

    }

}
