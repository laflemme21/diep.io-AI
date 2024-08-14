package Player;

import Screenshot.Screenshot;
import Target.Target;
import java.awt.Robot;

public class Player extends Target {
    private Robot robot;
    private int x;
    private int y;

    public Player(int[] p, int s){
        super(new int[] {0,0,0},p,s);
        x=0;
        y=0;
        try{
        robot = new Robot();}
        catch(Exception e){
            System.err.println(e);
        }
    }

    public void detectPlayerColor(Screenshot minimap) {
        int out = 0;
        int inn = 0;
        int red = 0;
        int blue = 0;
        for (int x = minimap.getSize()[0]; x < minimap.getSize()[2]; x += 13) {
            for (int y = minimap.getSize()[1]; y < minimap.getSize()[3]; y += 13) {
                if (minimap.getPixel(x - minimap.getSize()[0],
                        y - minimap.getSize()[1]) == new int[] { 199, 199, 199 }) {
                    out += 1;
                } else if (minimap.getPixel(x - minimap.getSize()[0],
                        y - minimap.getSize()[1]) == new int[] { 205, 205, 205 }) {
                    inn += 1;
                } else if (minimap.getPixel(x - minimap.getSize()[0],
                        y - minimap.getSize()[1]) == new int[] { 205, 201, 201 }) {
                    red += 1;
                } else if (minimap.getPixel(x - minimap.getSize()[0],
                        y - minimap.getSize()[1]) == new int[] { 200, 204, 205 }) {
                    blue += 1;
                }
            }
        }
        if (out > inn) {
            if (red > blue) {
                if (out > red) {
                    color = new int[] { 55, 55, 55 };
                } else {
                    color = new int[] { 61, 57, 57 };
                }
            } else {
                if (out > blue) {
                    color = new int[] { 55, 55, 55 };
                } else {
                    color = new int[] { 56, 60, 61 };
                }
            }
        } else {
            if (red > blue) {
                if (inn > red) {
                    color = new int[] { 61, 61, 61 };
                } else {
                    color = new int[] { 61, 57, 57 };
                }
            } else {
                if (inn > blue) {
                    color = new int[] { 61, 61, 61 };
                } else {
                    color = new int[] { 56, 60, 61 };
                }
            }
        }
    }

    public void movePlayer(int xTo,int yTo){
        // pyautogui.moveTo(xTo+2592, yTo+1416)

        if (x-xTo < -10){
            robot.keyRelease('a');
            robot.keyPress('d');}
        else if (x-xTo > 10){
            robot.keyRelease('d');
            robot.keyPress('a');}
        else{
            robot.keyRelease('a');
            robot.keyRelease('d');}
        if (y-yTo < -10){
            robot.keyRelease('w');
            robot.keyPress('s');}
        else if (y-yTo > 10){
            robot.keyRelease('s');
            robot.keyPress('w');}
        else{
            robot.keyRelease('s');
            robot.keyRelease('w');}
    }
}
