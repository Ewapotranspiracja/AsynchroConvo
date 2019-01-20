package Asynchro;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;

public class Pictures {

    
     public static final int MAXVAL = 255;
     static AtomicInteger foo = new AtomicInteger(1);   

     public static float[] image1D;
     public static float[] image1Dold;
     float[][] image = new float[1024][1024];
   
        int height = 1024;
        int width = 1024;
        
        
        
    public float[][] getImage() {
        return image;
    }
 
 
    public void setImage(float[][] data) {
        image = data;
    }
   
    
    //////Zapisanie pliku w formacie pgm
    public void writePGM(String path) throws Exception {

        try {
            try (PrintStream output = new PrintStream(new FileOutputStream(path))) {
                output.println("P2");
                output.println(width + " " + height);
                output.println(MAXVAL);
                for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                        output.println(image[row][col]); // One pixel per line!
                    }
                }
            }
        } catch (IOException e) {
            throw new Exception("ERROR: cannot write .pgm file " + path);
        }
    }

    /////Utworzenie szachownicy
    public void CreateCheckerboard(int l) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int liczba_pikseli_na_pole_w = height / l;
                int liczba_pikseli_na_pole_h = width / l;
                if (((i / liczba_pikseli_na_pole_h) + (j / liczba_pikseli_na_pole_w)) % 2 == 0) {
                    image[i][j] = 0;
                } else {
                    image[i][j] = 255;
                }
            }
        }
    }
    
    ///Wyświetlenie tablicy
    public void drukuj(){
    for (int i=0; i<height;i++){
               for (int j=0; j<width;j++)
               {
               System.out.print(image[i][j] + " ");
               
               }
               System.out.println();
           }
    
    }
    
    ////Konwertowanie tablicy dwuwymiarowej na jednowymiarową
    public  float[][] conv1Dto2D(float[] image) {
        
        
        float[][] result = new float[1024][1024];
        for (int i = 0; i < 1024; i++) {
            for (int j = 0; j < 1024; j++) {
                result[i][j] = image[i * 1024 + j];
            }
        }
        return result;
    }
    
    ////Konwertowanie tablicy dwuwymiarowej na jednowymiarową
    public  float[] conv2Dto1D(float[][] image) {
        
        float[] result = new float[image.length * image[0].length];
        for (int i = 0; i < image.length; i++) {
            System.arraycopy(image[i], 0, result, i * image.length, image[0].length);
        }
        return result;
    }
    
    
    
     
    
}
