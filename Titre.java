import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioInputStream.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Scanner;
import java.io.*;
import javax.sound.sampled.AudioInputStream.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Titre extends Main{
		
	public int index;
	public static boolean Overall;
	public static boolean avertissement;
	
	public Titre(boolean avertissement) throws Exception {
		
		Titre.avertissement = avertissement;
		Titre.Overall =true;
		Launch(); // chaque nouvelle instance appelle la fonction launch()
		
	}
	
	
	public static void Launch() throws Exception {	
		
		/*
		 * Cette fonction g�re le g�n�rique du d�but et le menu int�ractif
		 * le bool�en avertissement permet de passer (ou pas) l'animation �pique de d�part
		 */
		Boolean bool1 = false;
		
		Font FontSelctionTitre = new Font("Arial", Font.BOLD,(int)(40*SW));
        Sound2 menuTheme = new Sound2("GuileTheme.wav");
        Sound2 Explosion = new Sound2("explosion2.wav");
       
        
        
        while(Overall){	
        	
        	if(avertissement) {
        	
        	
        	StdDraw.picture(X_MAX/2, Y_MAX/2, "Avertissement.png",640*SW,360*SH);

        	
        	try {
				Thread.sleep(4000);   }             
			catch(InterruptedException ex) {
				Thread.currentThread().interrupt();  }
        	
    		
    			for (int i = Y_MAX; i> 20; i=(int) (i-5)) { //Chute de la bouteille
    			
    			StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg",640*SW,360*SH); 
    		    StdDraw.picture(X_MAX/2, i,"bouteille.png",50*SW,50*SH);
    		    
    		    	if (i<= (int)(25*SW)) { 
	
    		    	StdDraw.picture(X_MAX/2, i,"explosion.gif");
    		    	StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg",640*SW,360*SH);
    		    	StdDraw.picture(X_MAX/2, Y_MAX/2,"explosion.gif", 350*SW, 350*SH);	
    		    	StdDraw.picture(X_MAX/2, Y_MAX/2,"SnorkUnkingLogo.png", 380*SW, 240*SH); }
    		    	StdDraw.show((int)(20/SH));}
					Explosion.PlaySound(); // Explosion son
    				
    				try {
    					Thread.sleep((int) (300/SW));   }             
    				catch(InterruptedException ex) {
    					Thread.currentThread().interrupt();  }
    				
    				for (int i=0; i <50; i = (int)(i +5)) {  // Explosions + Titre
    					
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"explosion.gif", 350*SW, 350*SH);
    				StdDraw.show(5/(int)(SH)); 
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"SnorkUnkingLogo.png", 380*SW, 240*SH);
    				StdDraw.show(5/(int)(SH));
    				StdDraw.picture(X_MAX/4, 3*Y_MAX/4,"explosion.gif", 350*SW, 350*SH);
    				StdDraw.show(5/(int)(SH)); 
    				StdDraw.picture(X_MAX/4, Y_MAX/4,"explosion.gif", 350*SW, 350*SH);
    				StdDraw.show(5/(int)(SH)); 
    				StdDraw.picture(3*X_MAX/4, 3*Y_MAX/4,"explosion.gif", 350*SW, 350*SH);
    				StdDraw.show(5/(int)(SH)); 
    				StdDraw.picture(3*X_MAX/4, Y_MAX/4,"explosion.gif", 350*SW, 350*SH);
    				StdDraw.show(5/(int)(SH)); }
    				
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"SnorkUnkingLogo.png", 380*SW, 240*SH);
    				StdDraw.show(1000/(int)(SH));
    				
        	}
    				
    		
    				menuTheme.PlaySoundC();
    				
    				
    				
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg",640*SW,360*SH);  // Ecran titre
    				StdDraw.picture(X_MAX/2, Y_MAX/2+60,"SnorkUnkingLogo.png", 380*SW, 240*SH);
    				StdDraw.setPenColor(StdDraw.WHITE);
    				StdDraw.setFont(FontSelctionTitre);
    				StdDraw.rectangle(X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
    				StdDraw.text(X_MAX/4,Y_MAX/2-52*SH,"1 JOUEUR");
    				StdDraw.rectangle(3*X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
    				StdDraw.text(3*X_MAX/4,Y_MAX/2-52*SH,"2 JOUEURS");	
    				StdDraw.show();
    		
    				while(bool1 == false) {
    					
    		        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) { //Selection 1 JOUEUR
    		        	
    		        	IAStatus = true;

        				StdDraw.setFont(FontSelctionTitre);
    		        	StdDraw.setPenColor(StdDraw.RED);
        				StdDraw.rectangle(X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
        				StdDraw.text(X_MAX/4,Y_MAX/2-52*SH,"1 JOUEUR");
        			
        				StdDraw.setPenColor(StdDraw.WHITE);
        				StdDraw.rectangle(3*X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
        				StdDraw.text(3*X_MAX/4,Y_MAX/2-52*SH,"2 JOUEURS");
        				StdDraw.show();
        				}
    		        
    		        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) { //Selection 2 JOUEURS
    		        	
    		        	IAStatus = false;
    		        	
        				StdDraw.setFont(FontSelctionTitre);
    		        	StdDraw.setPenColor(StdDraw.RED);
        				StdDraw.rectangle(3*X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
        				StdDraw.text(3*X_MAX/4,Y_MAX/2-52*SH,"2 JOUEURS");
        				
        				StdDraw.setPenColor(StdDraw.WHITE);
        				StdDraw.rectangle(X_MAX/4,Y_MAX/2-50*SH,60*SW,20*SH);
        				StdDraw.text(X_MAX/4,Y_MAX/2-52*SH,"1 JOUEUR");
        				StdDraw.show();
        				}
    		         
    		        if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
    		        	bool1 = true;
    		        	menuTheme.Stop();
    		        	Explosion.Stop();
    		        	Overall = false;
    		        	
    		        	
    		        	//Niveau.DispDeplacement();
    		        	} // Detection choix mode de jeu
    			}     	
        
        	}
        }
	
}