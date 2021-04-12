package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi(int N){
		this.N = N;
		List <Integer> parziale = new ArrayList<Integer>(); //FACCIO get() QUINDI ARRAYLIST!!
		this.soluzione=null; //se non ho soluzioni restituisco null
		
		//caso iniziale
		cerca(parziale, 0);
		
		return this.soluzione;	
	}
	
	// cerca == true : trovato; cerca == false: cerca ancora
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			//System.out.println(parziale);
			this.soluzione = new ArrayList<Integer>(parziale);
			return true; //al livello 8 restituisco vero, ho la soluzione
		} else {
			for(int colonna=0; colonna<N; colonna++) {//<- tutte le posizioni per mettere la regina
				// if la mossa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
			
				if(posValida(parziale, colonna)) {
					parziale.add(colonna);//aggiungo a parziale
					boolean trovato = cerca(parziale, livello+1);
					if(trovato)//se ho la soluzione, ritorno tutto vero
						return true;
					parziale.remove(parziale.size()-1); //backtracking, quando risalgo di livello devo mettere le cose a posto!
				
					//posso fare una nuova lista, parzialeNuovo, che contenga parziale e faccio add
					//è meno efficiente perchè devo copiare un arraylist in un altro per aggiungerne uno.
				}
			}
			return false;//non ho trovato neppure io la soluzione
		}
	}

	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello = parziale.size();//riga della regina da posizionare
		//controlla se viene mangiata in verticale
		if(parziale.contains(colonna))
			return false;
		//controllo per riga non serve, per costruzione
		
		//controllo le diagonale: confronto posizione(livello, colonna)
		// con le regine già esistenti
		//riga + colonna = costante (per le diag)
		//riga - colonna = costante (in base all'orientamento)
		for(int r=0; r< livello; r++) {
			int c = parziale.get(r);
			
			if(r+c == livello+colonna || r-c==livello-colonna) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
