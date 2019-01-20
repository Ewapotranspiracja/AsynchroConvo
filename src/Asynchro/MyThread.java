
package Asynchro;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Pictures implements Runnable{

    int id;
    int a;
    int imax;
    
            
    public MyThread(int id){
    
    this.id = id;
    this.a=id*128+1;  ///start
    this.imax=a+128;  ////end
         
    }
    
    @Override
    public  void run() {
       
       int index; 
        
        for(int b=0; b<200; b++){ ////200 iteracji
     

        for (int i = a; i <imax; i++) {
            for (int j = 1; j < 1023; j++) {
                index = (i * 1024) + j;

                image1D[index] = image1Dold[index - 1024] * 0.1f + image1Dold[index + 1024] * 0.1f + image1Dold[index] * 0.6f 
                        + image1Dold[index + 1] * 0.1f + image1Dold[index - 1] * 0.1f;

            }
        }////Wątek wylicza swój fragment tablicy od pozycji startowej w prawo i następnie w dół
      
      
      ////Wątek zakończył pracę i czeka
         try {
             synchronized(foo){
            foo.wait();
             }
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
         
         
    }
    
}
}
