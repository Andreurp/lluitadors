package net.andreu.Lluitadors;

public class Lluitadors {

	private String nom;
	private int victories;
	private int derrotes;
	private int empats;
	
	public Lluitadors() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public int getDerrotes() {
		return derrotes;
	}

	public void setDerrotes(int derrotes) {
		this.derrotes = derrotes;
	}

	public int getEmpats() {
		return empats;
	}

	public void setEmpats(int empats) {
		this.empats = empats;
	}

	@Override
	public String toString() {
		return nom;
		// + ", victories=" + victories	+ ", derrotes=" + derrotes + ", empats=" + empats;
	}
	
	
	
}
