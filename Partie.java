import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import edu.princeton.cs.introcs.StdDraw;

public class Partie extends Main{
	static Random randomGenerator = new Random();
	public static int niveauxC1;
	public static int niveauxC2;
	public static int niveauxC3;
	
	public static int totNiveau123;
	//public static double hauteurNiveau = 280/(totNiveau123-1);
	public static List<NiveauC> nList;
	public static int Oxygene;
	
	static Font FontSelctionTitre = new Font("Arial", Font.BOLD,(int)(40*SW));
	
	static int endBool = 0;
	
    public static int J1Score=0;
    public static int J2Score=0;
    public static int J1Coffre = 0;
    public static int J2Coffre = 0;
	public static int tempJ1Score = 0;
	public static int tempJ2Score = 0; 
    
    public static int nbPartie = 0;
    
	static double BordureOxy;
	static double OxyIni;
	static double PourcentageOxy;  // Affichage du pourcentage d'oxygene
	

	public static void createNiveau(){
		/*
		 * Cette fonction permet de creer les niveau de d�part, leur nombre varie al�atoirement.
		 * La liste nList est de type niveau et contient l'ensemble des instances de la classe Niveau
		 */
		
		nList = new ArrayList<NiveauC>();
		
		niveauxC1 =9 + randomGenerator.nextInt(12-9);
		niveauxC2 = 6 + randomGenerator.nextInt(9-6);
		niveauxC3  = 3+ randomGenerator.nextInt(6-3);
		
		totNiveau123  = niveauxC1+niveauxC2+niveauxC3 + 1;
		
		Oxygene = 2*(niveauxC3+niveauxC2+niveauxC1+1); 
		BordureOxy = 10*(niveauxC3+niveauxC2+niveauxC1+1) + 0.1;
		OxyIni  = 5*(niveauxC3+niveauxC2+niveauxC1+1);
		PourcentageOxy  = Math.round(((2.5*Oxygene)/OxyIni)*100); // on ram�ne l'oxyg�ne en pourcentage 
		
		
		
		
		nList.add(new NiveauC(0,0,totNiveau123));
		
		for (int i = 1; i <= niveauxC1; i++) {
			nList.add(new NiveauC(1,i,totNiveau123));
		}
		for (int i=niveauxC1 + 1;i<=niveauxC1+niveauxC2;i++){
			nList.add(new NiveauC(2,i,totNiveau123));
		}
		for (int i=niveauxC2+niveauxC1+1;i<=niveauxC1+niveauxC2+niveauxC3;i++){
			nList.add(new NiveauC(3,i,totNiveau123));
		}
		
		
	}
   
    
	public static void BackgroundGraphics() { // L'ensemble des elements de toujours presents a l'image.

					Font FontScore = new Font("Arial", Font.BOLD,(int) (25*SW));
				
					
    				StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg",640*SW,360*SH);
    				
    				// Indicateurs dans les coins pour dire qui va jouer
    				
    				StdDraw.picture(0.05*X_MAX, 0.9*Y_MAX, "bob.png", 35*SW, 35*SH);
    		        StdDraw.picture(X_MAX-0.05*X_MAX, 0.9*Y_MAX, "patrick.png", 35*SW, 35*SH);
    		        
    		        // Affichage des scores
    		        
    		        StdDraw.setFont(FontScore);
    		        StdDraw.setPenColor(StdDraw.RED);
    		        StdDraw.text(0.05*X_MAX, 0.78*Y_MAX, "Score");
    		        StdDraw.text(0.95*X_MAX, 0.78*Y_MAX, "Score");
    		        
    		        
    		        // impression du round 1,2 ou 3.
    		        StdDraw.setPenColor(StdDraw.BLUE);
    				StdDraw.text(X_MAX/2, 0.95*Y_MAX, "Round " + (nbPartie+1));
    		        
    		        
					// on imprime l'ensemble des niveaux.			 
					 for (int i=0;i<nList.size();i++){
						 nList.get(i).drawNiveau();
						}
        }
     				
     				
     public static void DispDeplacement() throws Exception {
    	 
    	 /*
    	  * Fonction qui g�re le nombre de partie, elle g�re aussi le calcul de l'oxyg�ne et int�ragit avec les plongeurs.
    	  * On g�re aussi l'affichage du backgrounds et des scores 
    	  */
    	 Sound2 GrandBleu = new Sound2("Grand_Bleu.wav"); // musique lors des 3 rounds
    	 GrandBleu.PlaySoundC();
    	 while(nbPartie<3) {
    		 
    		 Oxygene = 2*(totNiveau123);
    		 BackgroundGraphics(); // affichage initial du background
        	 displayScore(); // affichage initial des scores
    	 
	    	 while(Oxygene>0) { // AFFICHAGE MANCHE DE JEU
	     				
	     			// Affichage barre d'oxygene
	     			Plongeur.DispOxygene();
	    			
	    			StdDraw.show(50);
	    				        
	    			Plongeur.Deplacement(); // Appelle la methode de deplacement des plongeurs.
	    			// ceci repr�sente un tour de jeu, joueur 1 puis joueur 2 ou diff�rents cas d�pendant en fonction de la situation
	    			
	    			StdDraw.show();


	    	 } //while affichage oxyg�ne
	    	 
	    	 System.out.println("Fin phase : " + (nbPartie + 1));
	    	 
	    	 nextPartie(); // on passe � la partie (round) suivante.
    	 
    	 }
    	 GrandBleu.Stop();// � la fin des 3 rounds, on stop la musique
    	 EcranNextPartie(); // on lance l'�cran pour une prochaine partie. 
    	 
	}
	
	
	public static void sumScore() {
		
		/*
		 * Cette fonction une fois appell� fait la somme des scores sur tous les niveaux
		 * en utilisant la m�thode getTresor de la classe Niveau
		 */

		for(int i=0;i<nList.size()-1;i++) {
			tempJ1Score += nList.get(i).getTresor(1);
			tempJ2Score += nList.get(i).getTresor(2);
			
		}
	}
		

	public static void displayScore() {
		
		// print the scores
		Font FontScore = new Font("Arial", Font.BOLD,(int) (25*SW));
		StdDraw.setFont(FontScore);
        StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(0.05*X_MAX, 0.74*Y_MAX,20*SW,7*SH);
		StdDraw.filledRectangle(0.95*X_MAX, 0.74*Y_MAX,20*SW,7*SH);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.05*X_MAX, 0.735*Y_MAX, Integer.toString(J2Score));
        StdDraw.text(0.95*X_MAX, 0.735*Y_MAX, Integer.toString(J1Score));
        
	}
	
	public static void nextPartie() throws Exception {
		
		
		/*
		 * Cette fonction permet le changement de partie, elle effectue plusieurs actions selon 
		 * les diff�rents sc�narios ; plongeur en bas, � la surface etc..
		 * 
		 * Elle enl�ve les niveaux qui ont d�j� �t� visit�, pour ce faire elle .remove de la liste l'instance niveau.
		 * 
		 * Si un niveau est visit�,que le coffre � �t� prit et que le joueur n'a pas remont� le coffre � la surface. 
		 * Comme attendu le coffre tombe au dernier niveau. 
		 * 
		 */
		nbPartie += 1 ;
		
		if (nbPartie < 3) {
		
		if(Plongeur.positionJ1 !=0 && Plongeur.positionJ2!=0) { // si les deux joueurs ne sont pas remont� a la surface
			
			for(int i=1;i<nList.size()-2;i++) {
				
				
				if (nList.get(i).coffreList.get(0).presence == false && nList.get(i).coffreList.get(0).dropped == false) { //D�placement coffres perdus
					nList.get(nList.size()-1).coffreList.add(nList.get(i).coffreList.get(0));
					nList.remove(i);
					i -=1 ;
					nList.get(nList.size()-1).coffreList.get(nList.get(nList.size()-1).coffreList.size()-1).NiveauType = 3;
					nList.get(nList.size()-1).coffreList.get(nList.get(nList.size()-1).coffreList.size()-1).presence = true;
					
				}
				if(nList.get(i).coffreList.get(0).presence == false && nList.get(i).coffreList.get(0).dropped == true) { //Suppression niveau
					nList.remove(i);
					i -=1 ;
				}
				
				
			}
			
			tempJ1Score = 0;
			tempJ2Score = 0;
			
		}
		
		else {
			
			for(int i=1;i<nList.size();i++) {
				
				if (nList.get(i).coffreList.get(0).presence == false && nList.get(i).coffreList.get(0).dropped == false) { //D�placement coffres perdus
					nList.get(nList.size()-1).coffreList.add(nList.get(i).coffreList.get(0));
					nList.get(nList.size()-1).coffreList.get(nList.get(nList.size()-1).coffreList.size()-1).NiveauType = 3;
					nList.remove(i);
					i -=1 ;
					nList.get(nList.size()-1).coffreList.get(nList.get(nList.size()-1).coffreList.size()-1).presence = true;
					
				}
				if(nList.get(i).coffreList.get(0).presence == false && nList.get(i).coffreList.get(0).dropped == true) { //Suppression niveau
					nList.remove(i);
					i -=1 ;
				}
				
			}
			
			tempJ1Score = 0;
			tempJ2Score = 0;
			
		} 
		
		}
		
		updateNiveauInfo();
		
		
		
	} // fin de nextPartie
	
	public static void updateNiveauInfo() {
		
		/*
		 * Fonction qui remet correctement les �lements dans les instances de la classe niveau
		 * Elle est neccessaire � chaque changement de partie/round 1,2,3
		 */
		
		for(int i=0;i<nList.size();i++) {
			nList.get(i).position =i;
			nList.get(i).totalNiveau =nList.size();
			nList.get(i).presenceJoueur1[0]=false;
			nList.get(i).presenceJoueur2[0]=false;
				
		}
		
		Plongeur.positionJ1 = 0;
		Plongeur.positionJ2 = 0;
		J1Coffre = 0;
		J2Coffre = 0;
		nList.get(0).presenceJoueur1[0]=true;
		nList.get(0).presenceJoueur2[0]=true;
		
	}
	public static void EcranNextPartie() throws Exception { 
		
		/* Ceci est un �cran qui affiche les scores en fin de partie
		 * Il n'a rien de bien compliqu�, il dispose de deux boutons droite gauche 
		 * qui permmettent de terminer la session (ferme le jeu) ou de recommencer une partie
		 * L'unique difficult� est de bien remettre � z�ro les scores 
		 */ 
		
		Boolean bool1 = false;
		StdDraw.picture(X_MAX/2, Y_MAX/2,"SnorkUnkingLogo.png", 380*SW, 240*SH);
		StdDraw.show(50);
		
		StdDraw.picture(X_MAX/2, Y_MAX/2,"ocean.jpg",640*SW,360*SH);  // Ecran titre
		StdDraw.picture(X_MAX/2, Y_MAX/2+60,"SnorkUnkingLogo.png", 380*SW, 240*SH);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(FontSelctionTitre);
		StdDraw.rectangle(X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
		StdDraw.text(X_MAX/4,0.15*Y_MAX*SH - 2*SH,"REPLAY");
		StdDraw.rectangle(3*X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
		StdDraw.text(3*X_MAX/4,0.15*Y_MAX*SH - 2*SH,"EXIT");	
		
		StdDraw.picture(0.25*X_MAX, 0.32*Y_MAX, "bob.png", 50*SW, 50*SH);
        StdDraw.picture(0.75*X_MAX, 0.32*Y_MAX, "patrick.png", 50*SW, 50*SH);
		StdDraw.text(0.35*X_MAX, 0.32*Y_MAX, Integer.toString(J2Score));
        StdDraw.text(0.65*X_MAX, 0.32*Y_MAX, Integer.toString(J1Score));
		
		StdDraw.show();
		
		while(bool1 == false) {
			
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) { //Selection 1 JOUEUR
        	
        	endBool = 0;
        	
			StdDraw.setFont(FontSelctionTitre);
        	StdDraw.setPenColor(StdDraw.RED);
        	StdDraw.rectangle(X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
			StdDraw.text(X_MAX/4,0.15*Y_MAX*SH - 2*SH,"REPLAY");
		
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.rectangle(3*X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
			StdDraw.text(3*X_MAX/4,0.15*Y_MAX*SH - 2*SH,"EXIT");	
			StdDraw.show();
			}
        
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) { //Selection 2 JOUEURS
        	
        	endBool = 1;
        	
			StdDraw.setFont(FontSelctionTitre);
        	StdDraw.setPenColor(StdDraw.RED);
        	StdDraw.rectangle(3*X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
			StdDraw.text(3*X_MAX/4,0.15*Y_MAX*SH - 2*SH,"EXIT");	
			
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.rectangle(X_MAX/4,0.15*Y_MAX*SH,60*SW,20*SH);
			StdDraw.text(X_MAX/4,0.15*Y_MAX*SH - 2*SH,"REPLAY");
			StdDraw.show();
			}
         
        if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {bool1 = true;
        	
	        if (endBool == 0) {
	        	nbPartie = 0;
	        	
	        	J1Score=0;
	            J2Score=0;
	        	
	        	try {
					Thread.sleep(400);   }             
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();  }
	        	titre = new Titre(false);
	        	Partie.createNiveau();
	    		Partie.DispDeplacement();
	    			
	        }
	        
	        if (endBool == 1) {
	        	
	        	Runtime.getRuntime().exit(0);
	        	System.exit(0);
	        	return; 
	        	// Sans la commande return, System.exit(0) ne fonctionne pas. 	        	
	        }
        	
        	} // Detection choix mode de jeu
		}
	}
		
}