package tp1;

import tp1.Noeud;

public class ArbreBinaire {
	private Noeud noeud;
	
	public ArbreBinaire (int element) {
		this.noeud = new Noeud (element, null, null);
	}
	
	public void inserer (int element){
		insererRec (element, this.noeud);
	}
	
	public Noeud insererRec (int element, Noeud noeud){	
		if (noeud.getElement() > element){
			if (noeud.getFg() != null){
				return insererRec (element, noeud.getFg());							
			} else  {
				noeud.setFg(new Noeud (element, null, null));
				return noeud;
			}
		} else if (noeud.getElement() < element) {
			if(noeud.getFd() != null){
				return insererRec (element, noeud.getFd());							
			} else{
				noeud.setFd(new Noeud (element, null, null));
				return noeud;
			}
		} else {
			System.out.println("L'�lement existe deja");
			return null;			
		}
	}
	
	
	public void supprimer (int element) {
		Noeud pere = null;
		Noeud courant = this.noeud;
		
		boolean estNoeudGauche = false;
		
		while (courant != null && courant.getElement() != element) {
			pere = courant;
			if (courant.getElement() > element) {
				estNoeudGauche = true;
				courant = courant.getFg();
			}else{
				estNoeudGauche = false;
				courant = courant.getFd();
			}
		}
		if (courant != null) {
			if(courant.getFg() == null && courant.getFd() == null){
				if(courant == this.noeud){
					this.noeud = null;
				}
				if(estNoeudGauche){
					pere.setFg(null);
				}else{
					pere.setFd(null);
				}
			}else if(courant.getFd() == null){
				if(courant == this.noeud){
					this.noeud = courant.getFg();
				}else if(estNoeudGauche){
					pere.setFg(courant.getFg());
				}else{
					pere.setFd(courant.getFg());			
				}
			} else if(courant.getFg() == null){
				if(courant == this.noeud){
					this.noeud = courant.getFd();
				}else if(estNoeudGauche){
					pere.setFg(courant.getFd());
				}else{
					pere.setFd(courant.getFd());
				}
			} else if(courant.getFg() !=null && courant.getFd() != null){
				Noeud sousArbre	= getSousArbre(courant);
				if(courant == this.noeud){
					this.noeud = sousArbre;
				}else if(estNoeudGauche){
					pere.setFg(sousArbre);
				}else{
					pere.setFd(sousArbre);
				}			
				sousArbre.setFg(courant.getFg());
			}	
		} else {
			System.out.println("L'element n'est pas present");
		}
		
	}
	
	public Noeud getSousArbre(Noeud noeudSupprimmer){
		Noeud pere =null;
		Noeud grandPere =null;
		Noeud courant = noeudSupprimmer.getFd();
		while(courant != null){
			grandPere = pere;
			pere = courant;
			courant = courant.getFg();
		}
		if(pere != noeudSupprimmer.getFd()){
			grandPere.setFg(pere.getFd());
			pere.setFd(noeudSupprimmer.getFd());
		}
		return pere;
	}
	
	public Noeud getNoeud() {
		return noeud;
	}

	public void setNoeud(Noeud noeud) {
		this.noeud = noeud;
	}

	public void affichage(Noeud a) 
	{
	 
 	  System.out.print(a.getElement() + " ");
 	  if (a.getFg() != null) {
 	 	  affichage(a.getFg()); 		  
 	  }
 	  if (a.getFd() != null) {
 		  affichage(a.getFd()); 		  
 	  }
	}
}
