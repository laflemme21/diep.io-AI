import Target.Target;
import Keyboard.Keyboard;
import Screenshot.Screenshot;
import Mouse.Mouse;
public class aiming {
    
    public static void main(String[] args) {

        
        //working on detecting keyboard press
        Target square = new Target(new int[] {247, 232, 126},new int[] {0,0},97);
        Target triangle = new Target(new int[] {226, 126, 123},new int[] {0,0},90);
        Target pentagon = new Target(new int[] {129, 142, 244},new int[] {0,0} ,100);
        Target[] allTargets = {pentagon, triangle,square};
        Screenshot screenshot = new Screenshot(415, 160, 2500, 1520);
        Mouse mouse = new Mouse();
        while (Keyboard.isQPressed()==true){
            screenshot.renewScreenshot();
            for (Target target :allTargets){
                target.locate(screenshot);
                /////while (Keyboard.isQPressed()==true && target.getX() != -1){
                    mouse.moveTo(target.getPosition(),screenshot);
                    ////target.followTarget(screenshot,30,80);
                

            }

        }
    }
}
