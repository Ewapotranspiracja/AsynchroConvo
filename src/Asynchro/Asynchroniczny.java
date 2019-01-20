
package Asynchro;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Asynchroniczny extends Pictures {
   
    
    public  Asynchroniczny (){
    
    
        try {
           
            Pictures p = new Pictures();
            
            ////Utworzenie szachownicy
            p.CreateCheckerboard(20);
            
            ////Przekonwertowanie tablicy dwuwymiarowej na jednowymiarową
            image1Dold = (conv2Dto1D(p.getImage()));
            image1D = image1Dold;
           
            ///Utworzenie siedmiu wątków pobocznych
                Thread Mythreads[] = new Thread[7];
    
                for (int i =0;i<7;i++){
                Mythreads[i] = new Thread(new MyThread(i));
               
                    }
                 
           
           
           ///Uruchomienie obliczeń
           long start = System.currentTimeMillis();
           for (int i =0;i<7;i++){
               
               Mythreads[i].start();
                    } 
           
           ///200 Iteracji
           for(int b=0; b<200;b++){
             ///Wątek główny wypełnia obramowanie górę + boki
             DoTop();
             DoBorders();
               
               ////Wątek główny czeka, aż wszystkie pozostałe siedem wątków zakończy pracę
               for (int j=0; j<7;j++){
               while (true){
               if(Mythreads[j].getState()== Thread.State.WAITING){
                        break;
                    }
                }
               }
               
               //Wątek główny wypełnia dolne obramowanie
               DoBottom(); 
               
               ////Stara tablica zostaje podmieniona na nową
               image1Dold = image1D;
               
               ////Informujemy pozostałe wątki, że mogą wznowić pracę
               synchronized(foo){
               foo.notifyAll();
               }
           }
           
           long stop = System.currentTimeMillis();
           
           System.out.println("KONIEC: " + (stop-start) + "ms.");
           p.setImage(conv1Dto2D(image1D));
        /// p.writePGM("C:\\Users\\Lena\\Documents\\NetBeansProjects\\Asynchro\\AsynchroImage2.pgm");
           System.out.println("ZAPISANO");
           
           
           
            
        } catch (Exception ex) {
            
        }}
    
    

    
    private void DoTop(){
   
    
     image1D[1023] = image1Dold[1023] * 0.6f + image1Dold[1023 + 1024] * 0.1f + image1Dold[1022] * 0.1f;  ///prawy górny róg
    
    
    
    for (int i = 1022; i >0; i--) {
            image1D[i] = image1Dold[i] * 0.6f + image1Dold[i + 1] * 0.1f + image1Dold[i - 1] * 0.1f + image1Dold[i + 1024] * 0.1f;  ////górna krawędź

        } 
    
    image1D[0] = image1Dold[0] * 0.6f + image1Dold[1] * 0.1f + image1Dold[1024] * 0.1f; ////lewy górny róg
    }
    
    
    private void DoBorders(){
        
        int index;
    for (int i = 1; i < 1023; i++) {
            index = i * 1024;
            image1D[index] = image1Dold[index] * 0.6f + image1Dold[index + 1024] * 0.1f + image1Dold[index - 1024] * 0.1f + image1Dold[index + 1] * 0.1f;
        }/////lewa krawędź
    
    
     for (int i = 1; i < 1023; i++) {

            index = (i * 1024) + 1023;

            image1D[index] = image1Dold[index] * 0.6f + image1Dold[index + 1024] * 0.1f + image1Dold[index - 1024] * 0.1f + image1Dold[index - 1] * 0.1f;

        }/////prawa krawędź
    
    
    
    }
    
    
    private void DoBottom(){
    
          int index = (1024 * 1023)-1;
        image1D[index] = image1Dold[index] * 0.6f + image1Dold[index - 1024] * 0.1f + image1Dold[index + 1] * 0.1f; ///prawy róg

         for (int i = ((1024*1024)-2); i > (1024*1023); i--) {
            image1D[i] = image1Dold[i] * 0.6f + image1Dold[i - 1] * 0.1f + image1Dold[i + 1] * 0.1f + image1Dold[i - 1024] * 0.1f;
        } ////dolna krawędź
    
          index = 1024 * 1023;
        image1D[index] = image1Dold[index] * 0.6f + image1Dold[index - 1024] * 0.1f + image1Dold[index - 1] * 0.1f; ///lewy róg
        
        
         
         
    }
    
    
}
