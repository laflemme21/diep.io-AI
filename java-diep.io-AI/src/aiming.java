import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class aiming implements Runnable{
    
    public void run(){
        
        KeyPressDetector detector = new KeyPressDetector();
        detector.startListening();
        Target square = new Target(new int[] {247, 232, 126},new int[] {0,0},30,new int[] {100,100}); //239,195,107
        Target triangle = new Target(new int[] {226, 126, 123},new int[] {0,0},30,new int[] {100,100});
        Target pentagon = new Target(new int[] {129, 142, 244},new int[] {0,0} ,40,new int[] {100,100}); //160,85,137
        Target[] allTargets = {pentagon, triangle,square};
        Screenshot screenshot = new Screenshot(0, 0, 1440, 900,"fullScreen");
        Mouse mouse = new Mouse();
        while (!detector.isKeyPressed(NativeKeyEvent.VC_Q)) {
            screenshot.renewScreenshot();
            for (Target target :allTargets){
                target.locate(screenshot);
                while((!detector.isKeyPressed(NativeKeyEvent.VC_Q))&&target.getX()!=-1){
                    mouse.moveTo(target.getPosition(),screenshot);
                    try {
                        TimeUnit.MILLISECONDS.sleep(270);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    target.followTarget();
                }
            }
        }
        System.exit(0);
    }

}
