package Screenshot;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.Robot;
import java.awt.Rectangle;

public class Screenshot {
    private BufferedImage image;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private byte[] pixels;
    private int width;
    private int height;

    public Screenshot(int xmin,int ymin,int xmax,int ymax) {
        try {  
            xMin = xmin;
            yMin = ymin;
            xMax = xmax;
            yMax = ymax;
            renewScreenshot();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void renewScreenshot() {
        Rectangle screenRect = new Rectangle(xMin, yMin,xMax-xMin, yMax-yMin);
        try{
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        File imageFile = new File("screenshot.bmp");
        ImageIO.write(capture, "bmp", imageFile);
        image = ImageIO.read(new File("screenshot.bmp"));
        pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        width = image.getWidth();
        height = image.getHeight();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }

    public int[] findColor(int[] target) {
        int[] color;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color=getPixel(x, y);
                if (color[0] == target[0] && color[1] == target[1] && color[2] == target[2]) {
                    int[] position = { x, y };
                    return position;
                }
            }
        }
        int[] notFound = { -1, -1 };
        return notFound;
    }

    public int[] getSize(){
        int[] size={xMin,yMin,xMax,yMax};
        return size;
    }


    public int[] getPixel(int x, int y){
        int pixelIndex = (y * width + x) * 3; // Each pixel has 3 bytes (RGB)
        int red = pixels[pixelIndex] & 0xFF;
        int green = pixels[pixelIndex + 1] & 0xFF;
        int blue = pixels[pixelIndex + 2] & 0xFF;
        return new int[] {red,green,blue};
    }


}
