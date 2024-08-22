

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
            screenRect = new Rectangle(xMin, yMin,w, h);
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

    public void renewScreenshot() throws IOException, AWTException {
        imageFile.delete();
        imageFile.createNewFile();
        try{
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

    public int[] findColor(int[] target) {
        int[] color;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color=getPixel(x, y);
                if (color[0] == target[0] && color[1] == target[1] && color[2] == target[2]) {
                    return new int[]{ x+xMin, y+yMin };
                }
            }
        }
        return new int[] { -1, -1 };
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
