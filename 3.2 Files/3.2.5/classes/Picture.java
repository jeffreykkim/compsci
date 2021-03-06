import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println(String.valueOf(count));
    }

    public void mirrorArms()
    {
        Pixel upPixel = null;
        Pixel downPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        int count = 0;
        // loop through the rows
        for (int row = 159; row < 225; row++)
        {
            for (int col = 103; col < 293; col++)
            {
                upPixel = pixels[row][col];      
                downPixel = pixels[295 - count][col];
                downPixel.setColor(upPixel.getColor());
            }
            count++;
        }
    }

    public void mirrorGull()
    {
        Pixel upPixel = null;
        Pixel downPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        // loop through the rows
        for (int row = 235; row < 343; row++)
        {
            for (int col = 235; col < 347; col++)
            {
                upPixel = pixels[row][col];      
                downPixel = pixels[row][(120+347)-col];
                downPixel.setColor(upPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    public void testMirrorHorizontal()
    {  
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < pixels.length / 2; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void testGrayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray: pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int i = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen())/3;
                pixelObj.setRed(i);
                pixelObj.setGreen(i);
                pixelObj.setBlue(i);
            }
        }
    }

    public void testNegate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray: pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }

    public void copy(Picture fromPic, int startRow, 
    int endRow, int startCol, int endCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = startRow, toRow = 0; 
        fromRow < endRow &&
        toRow < endRow - startRow; 
        fromRow++, toRow++)
        {
            for (int fromCol = startCol, toCol = 0; 
            fromCol < endCol &&
            toCol < endCol - startCol;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture robot = new Picture("robot.jpg");
        Picture door = new Picture("thruDoor.jpg");
        this.copy(robot,0,0);
        Picture robotGrey = new Picture("robot.jpg");
        robotGrey.testGrayscale();
        this.copy(robotGrey,100,0);
        this.copy(robot,300,0);
        Picture robotNegate = new Picture("robot.jpg");
        robotNegate.testNegate();
        this.copy(robotNegate,200,0);
        Picture robotMirror = new Picture("robot.jpg");
        robotMirror.testMirrorHorizontal();
        this.copy(robotMirror,400,0);
        this.copy(door,50,103);
        Picture flower = new Picture("flower1.jpg");
        this.copy(flower,70,50);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel thisPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        Color leftColor = null;
        Color topColor = null;
        Color bottomColor = null;
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                thisPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                bottomPixel = pixels[row+1][col];
                bottomColor = bottomPixel.getColor();
                if (thisPixel.colorDistance(rightColor) > 
                edgeDist && rightColor != Color.BLACK)
                {
                    thisPixel.setColor(Color.BLACK);
                }
                if (thisPixel.colorDistance(bottomColor) > 
                edgeDist && bottomColor!= Color.BLACK)
                {
                    thisPixel.setColor(Color.BLACK);
                }

                else
                {
                    thisPixel.setColor(Color.WHITE);
                }
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
