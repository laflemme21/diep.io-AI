import java.awt.AWTException;
import java.io.IOException;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class aiming {
    
    public static void main(String[] args) throws IOException, AWTException {
        
        KeyPressDetector detector = new KeyPressDetector();
        detector.startListening();
        Target square = new Target(new int[] {247, 232, 126},new int[] {0,0},97,new int[] {200,200});
        Target triangle = new Target(new int[] {226, 126, 123},new int[] {0,0},90,new int[] {200,200});
        Target pentagon = new Target(new int[] {129, 142, 244},new int[] {0,0} ,100,new int[] {200,200});
        Target[] allTargets = {pentagon, triangle,square};
        Screenshot screenshot = new Screenshot(0, 0, 1440, 900,"fullScreen");
        Mouse mouse = new Mouse();
        while (!detector.isKeyPressed(NativeKeyEvent.VC_Q)) {
            screenshot.renewScreenshot();
            for (Target target :allTargets){
                target.locate(screenshot);
                while((!detector.isKeyPressed(NativeKeyEvent.VC_Q))&&target.getPosition()[0]!=-1){
                    System.out.println("hi2");
                    System.out.println("bye2");
                    System.out.println("---`2");
                    mouse.moveTo(target.getPosition(),screenshot);}
                    target.followTarget();
            }
        }
        System.exit(0);
    }
}
