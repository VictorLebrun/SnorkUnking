import java.awt.event.KeyEvent;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


public class Niveau extends Titre{

	public int index;
	public final static int X_MAX=320;
	public final static int Y_MAX=180;
	public final static double WIDTH=0.5f;
    
	static Random randomGenerator = new Random();
	
    public static int niveauxC1 = 9 + randomGenerator.nextInt(12-9);
    public static int niveauxC2 = 6 + randomGenerator.nextInt(9-6);
    public static int niveauxC3 = 3+ randomGenerator.nextInt(6-3);
    
	public static void Graphique() throws Exception {	
		
        double deltaY=0;
        double deltaY2=0;
        int positionJ1 = 0;
        int positionJ2 = 0;
	    boolean bool = true;
	    boolean boolMove1 = true;
	    boolean boolMove2 = true;
	    boolean boolMove3 = true;
	    
		StdDraw.setCanvasSize(1280,720);
		StdDraw.setXscale(-WIDTH,X_MAX+WIDTH);
		StdDraw.setYscale(-WIDTH,Y_MAX+WIDTH);
        
        while(true){	

    				StdDraw.picture(X_MAX/2, Y_MAX/2,"water.gif");  //Génération graphique du niveau
    				
    				StdDraw.picture(0.05*X_MAX, 0.9*Y_MAX, "bob.png", 15, 15);
    		        StdDraw.picture(X_MAX-0.05*X_MAX, 0.9*Y_MAX, "patrick.png", 18, 18);
    				
    				StdDraw.setPenColor(StdDraw.BLUE);
    		        StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*niveauxC2+5*niveauxC1),X_MAX/2-0.05*X_MAX,2.5);
    				
    		        StdDraw.setPenColor(StdDraw.YELLOW);
    		        
    		        for (int i = 0; i < niveauxC1; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*niveauxC2+5*i),X_MAX/2-0.05*X_MAX,2.5);
    					StdDraw.picture(X_MAX/2, 5*niveauxC3+5*niveauxC2+5*i, "coffre.png", 7, 7);
    		        }
    		        
    		        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
    				
    		        for (int i = 0; i < niveauxC2; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*i),X_MAX/2-0.05*X_MAX,2.5);
    					StdDraw.picture(X_MAX/2, 5+5*niveauxC3+5*i, "coffre.png", 7, 7);
    		        }
    		        
    		        StdDraw.setPenColor(StdDraw.RED);
    		        
    				for (int i = 0; i < niveauxC3; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*i),X_MAX/2-0.05*X_MAX,2.5);
    					StdDraw.picture(X_MAX/2, 5+5*i, "coffre.png", 7, 7);
    				}
    				
        }
	}
}     	