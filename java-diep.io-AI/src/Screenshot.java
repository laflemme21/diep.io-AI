

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.AWTException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public class Screenshot {
    private BufferedImage image;
    private int xMin;
    private int yMin;
    private byte[] pixels;
    private int width;
    private int height;
    private File imageFile;
    private String pathname;
    private Rectangle screenRect;
    private Robot robot;

    public Screenshot(int xmin,int ymin,int w,int h, String n) {
        try {
            pathname="C:\\Users\\carlk\\Documents\\GitHub\\diep.io-AI\\screenshots"+"\\"+n+".png";
            xMin = xmin;
            yMin = ymin;
            width = w;
            height = h;
            screenRect = new Rectangle(xMin, yMin,width, height);
            robot=new Robot();
            image = robot.createScreenCapture(screenRect);
            imageFile = new File(pathname);
            if(!imageFile.createNewFile()){
                imageFile.delete();
                imageFile.createNewFile();
            }
            ImageIO.write(image, "png", imageFile);
            image = ImageIO.read(new File(pathname));
            pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void renewScreenshot(){
        try{
        imageFile.delete();
        imageFile.createNewFile();
        image = robot.createScreenCapture(screenRect);
        ImageIO.write(image, "png", imageFile);
        image = ImageIO.read(new File(pathname));
        pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        }
        catch(Exception e){

            System.err.println(e);
            System.exit(3);
        }
    }

    public int[] findColor(int[] target, int step) {
        int[] color;
        for (int y = 0; y < height; y+=step) {
            for (int x = 0; x < width; x+=step) {
                color=getPixel(x, y);
                if (color[0] == target[0] && color[1] == target[1] && color[2] == target[2]) {
                    return new int[]{ x+xMin, y+yMin };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    public int[] centerOfShape(int[] target,int[] position){
        int[] width1=widthOfShape(target,position);
        int[] height1=heightOfShape(target,position);
        int[] modPosition={position[0]+width1[1],position[1]};
        int[] width2=widthOfShape(target, modPosition);
        modPosition=new int[] {position[0],position[1]+height1[1]};
        int[] height2=heightOfShape(target, modPosition);
        return new int[] {((width1[2]+width2[2])/4)+position[0],((height1[2]+height2[2])/4)+position[1]};
    }

    private int[] heightOfShape(int[] target,int[] position){
        int widthRight=0;
        int widthLeft=0;
        int x=position[0]-xMin;
        int y=position[1]-yMin;
        int[] color=target;
        while(color[0] == target[0] && color[1] == target[1] && color[2] == target[2]){
            widthRight+=1;
            y-=1;
            if(y>=0){
            color=getPixel(x,y);
            }
            else{break;}
        }

        y=position[1]-yMin;
        color=target;
        while(color[0] == target[0] && color[1] == target[1] && color[2] == target[2]){
            widthLeft+=1;
            y+=1;
            if(y<this.width-1){
            color=getPixel(x,y);
            }
            else{break;}
        }

        return new int[] {widthLeft,widthRight,widthLeft+widthRight};

    }
    
    private int[] widthOfShape(int[] target,int[] position){
        int heightUnder=0;
        int heightAbove=0;
        int x=position[0]-xMin;
        int y=position[1]-yMin;
        int[] color=target;
        while(color[0] == target[0] && color[1] == target[1] && color[2] == target[2]){
            heightUnder+=1;
            x-=1;
            if(x>=0){
            color=getPixel(x,y);
            }
            else{break;}
        }

        x=position[0]-xMin;
        color=target;
        while(color[0] == target[0] && color[1] == target[1] && color[2] == target[2]){
            heightAbove+=1;
            x+=1;
            if(x<this.height-1){
            color=getPixel(x,y);
            }
            else{break;}
        }

        return new int[] {heightUnder,heightAbove,heightUnder+heightAbove};

    }

    public int[] getSize(){
        return new int[] {xMin,yMin,width,height};
    }


    public int[] getPixel(int x, int y){
        int pixelIndex = (y * width + x) * 3;
        int blue = pixels[pixelIndex] & 0xFF;
        int green = pixels[pixelIndex + 1] & 0xFF;
        int red = pixels[pixelIndex + 2] & 0xFF;
        return new int[] {red,green,blue};
    }


}
