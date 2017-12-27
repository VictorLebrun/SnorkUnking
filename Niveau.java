import java.awt.Font;
import java.util.List;
import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;

public class Niveau extends Titre{

	public int index;
	
	public final static int X_MAX=320;
	public final static int Y_MAX=180;
	public final static double WIDTH=0.5f;
    
    public static int deltaY = 0;
    public static int deltaY2 = 0;
    
	static Random randomGenerator = new Random();
	
    public static int niveauxC1 = 9 + randomGenerator.nextInt(12-9);
    public static int niveauxC2 = 6 + randomGenerator.nextInt(9-6);
    public static int niveauxC3 = 3+ randomGenerator.nextInt(6-3);
    
    public static int Oxygene = 2*(niveauxC3+niveauxC2+niveauxC1);  // VERSION BETA, PAS DE CLASSE DE GESTION DE l'OXYGENE POUR LE MOMENT
    
	public static double h1 = 5+5*niveauxC3+5*niveauxC2+5*niveauxC1;
    
	public static void WindowInit() { // Initialisation de la fen�tre d'affichage
		
		StdDraw.setCanvasSize(1280,720);
		StdDraw.setXscale(-WIDTH,X_MAX+WIDTH);
		StdDraw.setYscale(-WIDTH,Y_MAX+WIDTH);
	
	}
	
	public static void BackgroundGraphics() { // L'ensemble des �l�ments de toujours pr�sents � l'image. J'ai inclu les coffres pour l'instant

					Font FontScore = new Font("Arial", Font.BOLD, 25);
					Font FontPourcent = new Font("Arial", Font.PLAIN, 20);
							
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg");  // Je suis revenu au jpg parce que le gif �tait super chiant � afficher durant les boucles
    				
    				// Indicateurs dans les coins pour dire qui va jouer
    				
    				StdDraw.picture(0.05*X_MAX, 0.9*Y_MAX, "bob.png", 15, 15);
    		        StdDraw.picture(X_MAX-0.05*X_MAX, 0.9*Y_MAX, "patrick.png", 15, 15);
    		        
    		        // Affichage des scores
    		        
    		        StdDraw.setFont(FontScore);
    		        StdDraw.setPenColor(StdDraw.RED);
    		        StdDraw.text(0.05*X_MAX, 0.78*Y_MAX, "Score");
    		        StdDraw.text(0.95*X_MAX, 0.78*Y_MAX, "Score");
    				
    		        // Affichage des niveaux
    		        
    				StdDraw.setPenColor(StdDraw.BLUE);
    		        StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*niveauxC2+5*niveauxC1),X_MAX/2-0.05*X_MAX,2.5);
    				
    		        StdDraw.setPenColor(StdDraw.YELLOW);
    		        
    		        for (int i = 0; i < niveauxC1; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*niveauxC2+5*i),X_MAX/2-0.05*X_MAX,2.5);
    		        }
    		        
    		        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
    				
    		        for (int i = 0; i < niveauxC2; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*niveauxC3+5*i),X_MAX/2-0.05*X_MAX,2.5);
    		        }
    		        
    		        StdDraw.setPenColor(StdDraw.RED);
    		        
    				for (int i = 0; i < niveauxC3; i++) {
    					StdDraw.rectangle(X_MAX/2,(5+5*i),X_MAX/2-0.05*X_MAX,2.5);
    				}
    				
    				// Affichage des coffres 
    				
    				for (int i = 0; i < niveauxC1; i++) { StdDraw.picture(X_MAX/2, 5+5*niveauxC3+5*niveauxC2+5*i, "coffre.png", 7, 7); }
     				
     		        for (int i = 0; i < niveauxC2; i++) { StdDraw.picture(X_MAX/2, 5+5*niveauxC3+5*i, "coffre.png", 7, 7); }
     		        
     				for (int i = 0; i < niveauxC3; i++) { StdDraw.picture(X_MAX/2, 5+5*i, "coffre.png", 7, 7); }
     				
        }
     				
     				
     public static void DispDeplacement() {
    	 
    	 Niveau.WindowInit();
    	 
    	 Niveau.BackgroundGraphics();
    	 
    	 while(Oxygene>0) { // AFFICHAGE MANCHE DE JEU - UNE BOUCLE = UN TOUR D'UN JOUEUR
     				
     				// Affichage barre d'oxyg�ne
     				
     				double BordureOxy = 4*(niveauxC3+niveauxC2+niveauxC1) + 0.1;
     				double OxyIni = 2*(niveauxC3+niveauxC2+niveauxC1);
     				
     				StdDraw.setPenColor(StdDraw.BLACK);
     				StdDraw.rectangle(X_MAX/2, 0.9*Y_MAX, BordureOxy, 6.1);
     				StdDraw.setPenColor(StdDraw.BLUE);
     				StdDraw.filledRectangle(X_MAX/2, 0.9*Y_MAX, 2*Oxygene, 6);
     				
     				double PourcentageOxy = Math.round(((Oxygene/OxyIni)*100));  // Affichage du pourcentage d'oxyg�ne
     				StdDraw.setPenColor(StdDraw.WHITE);
     				StdDraw.text(X_MAX/2, 0.9*Y_MAX, PourcentageOxy + " %");
    				
    				StdDraw.show(50);
    				
    				// Affichage des personnages avant les premiers d�placements
    				
    				StdDraw.picture(X_MAX-0.12*X_MAX, h1-deltaY+4.2,"patrick.png", 12, 12);
    				StdDraw.picture(0.12*X_MAX, h1-deltaY2+4.5,"bob.png", 10, 10);
    				
    				int Ordre = Plongeur.Ordre();  // On r�cup�re l'info de qui va jouer
    				if (Ordre == 1) { // J1 va jouer en premier
    					
    					// Tour J1
    					StdDraw.setPenColor(StdDraw.RED);
    			        StdDraw.rectangle(X_MAX-0.05*X_MAX, 0.9*Y_MAX, 15, 15); // Indicateur de tour
    			        StdDraw.show();
    			        System.out.println("Return Ordre vers Niveau J1 checked");
    			        deltaY = Plongeur.Deplacement(1); // Appelle la m�thode de d�placement pour le J1
    			        
    			        Niveau.BackgroundGraphics(); // Efface l'image pour le fond de base mais pas les joueurs
    			        
    			        // Affichage des joueurs apr�s d�placement de J1 
    			        
    			        StdDraw.picture(0.12*X_MAX, h1-deltaY2+4.5,"bob.png", 10, 10);
    	        		StdDraw.picture(X_MAX-0.12*X_MAX, h1-deltaY+4.2,"patrick.png", 12, 12);

    	        		// Mise � jour de la barre d'oxygene	
    	        		
    	        		StdDraw.setPenColor(StdDraw.BLACK);
         				StdDraw.rectangle(X_MAX/2, 0.9*Y_MAX, BordureOxy, 6.1);
         				StdDraw.setPenColor(StdDraw.BLUE);
         				StdDraw.filledRectangle(X_MAX/2, 0.9*Y_MAX, 2*Oxygene, 6);
         				StdDraw.setPenColor(StdDraw.WHITE);
         				StdDraw.text(X_MAX/2, 0.9*Y_MAX, PourcentageOxy + " %");
    	        		
    	        		StdDraw.show();
    	        		
    	        		printList(Plongeur.SysOxygene());
    	        		
    	        		// Tour J2
    	        		StdDraw.setPenColor(StdDraw.RED);
    	        		StdDraw.rectangle(0.05*X_MAX, 0.9*Y_MAX, 15, 15); // Indicateur de tour
    			        StdDraw.show();
    			        System.out.println("Return Ordre vers Niveau J2 checked");
    			        deltaY2 = Plongeur.Deplacement(2); // Appelle la m�thode de d�placement pour le J2
    			        
    			        Niveau.BackgroundGraphics();
    			        
    			        StdDraw.picture(0.12*X_MAX, h1-deltaY2+4.5,"bob.png", 10, 10);
    	        		StdDraw.picture(X_MAX-0.12*X_MAX, h1-deltaY+4.2,"patrick.png", 12, 12);

    	        		StdDraw.setPenColor(StdDraw.BLACK);
         				StdDraw.rectangle(X_MAX/2, 0.9*Y_MAX, BordureOxy, 6.1);
         				StdDraw.setPenColor(StdDraw.BLUE);
         				StdDraw.filledRectangle(X_MAX/2, 0.9*Y_MAX, 2*Oxygene, 6);
         				StdDraw.setPenColor(StdDraw.WHITE);
         				StdDraw.text(X_MAX/2, 0.9*Y_MAX, PourcentageOxy + " %");
    	        		
    	        		StdDraw.show();
    	        		
    	        		printList(Plongeur.SysOxygene()); 	        		
    				}
    					
    				if (Ordre == 2) { // J2 va jouer en premier
    	        		
    	        		// Tour J2
    					StdDraw.setPenColor(StdDraw.RED);
    	        		StdDraw.rectangle(0.05*X_MAX, 0.9*Y_MAX, 15, 15); // Indicateur de tour
    			        StdDraw.show();
    			        System.out.println("Return Ordre vers Niveau J2 checked");
    			        deltaY2 = Plongeur.Deplacement(2); // Appelle la m�thode de d�placement pour le J2

    			        Niveau.BackgroundGraphics();
    			        
    			        StdDraw.picture(X_MAX-0.12*X_MAX, h1-deltaY+4.2,"patrick.png", 12, 12);
    	        		StdDraw.picture(0.12*X_MAX, h1-deltaY2+4.5,"bob.png", 10, 10);

    	        		StdDraw.setPenColor(StdDraw.BLACK);
         				StdDraw.rectangle(X_MAX/2, 0.9*Y_MAX, BordureOxy, 6.1);
         				StdDraw.setPenColor(StdDraw.BLUE);
         				StdDraw.filledRectangle(X_MAX/2, 0.9*Y_MAX, 2*Oxygene, 6);
         				StdDraw.setPenColor(StdDraw.WHITE);
         				StdDraw.text(X_MAX/2, 0.9*Y_MAX, PourcentageOxy + " %");
    	        		
    	        		StdDraw.show();
    	        		
    	        		printList(Plongeur.SysOxygene());
    	        		
    	        		// Tour J1
    	        		StdDraw.setPenColor(StdDraw.RED);
    			        StdDraw.rectangle(X_MAX-0.05*X_MAX, 0.9*Y_MAX, 15, 15); // Indicateur de tour
    			        StdDraw.show();
    			        System.out.println("Return Ordre vsers Niveau J1 checked");
    			        deltaY = Plongeur.Deplacement(1); // Appelle la m�thode de d�placement pour le J1
    			        
    			        Niveau.BackgroundGraphics();
    			        
    			        StdDraw.picture(0.12*X_MAX, h1-deltaY2+4.5,"bob.png", 10, 10);
    	        		StdDraw.picture(X_MAX-0.12*X_MAX, h1-deltaY+4.2,"patrick.png", 12, 12);

    	        		StdDraw.setPenColor(StdDraw.BLACK);
         				StdDraw.rectangle(X_MAX/2, 0.9*Y_MAX, BordureOxy, 6.1);
         				StdDraw.setPenColor(StdDraw.BLUE);
         				StdDraw.filledRectangle(X_MAX/2, 0.9*Y_MAX, 2*Oxygene, 6);
         				StdDraw.setPenColor(StdDraw.WHITE);
         				StdDraw.text(X_MAX/2, 0.9*Y_MAX, PourcentageOxy + " %");
    	        		
    	        		StdDraw.show();
    	        		
    	        		printList(Plongeur.SysOxygene());
    	        		
    				} 
    				
        } // While affichage global
		System.exit(0); // BETA - Fin de programme actuelle pour �viter des boucles ind�sirables
	}
	
	public static void printList(List<String> list) {
		for (int d=0; d<list.size();d++) {
			if (d==list.size()) {
				System.out.println(list.get(d));
			}
			System.out.println(list.get(d));
		}
	}
}   