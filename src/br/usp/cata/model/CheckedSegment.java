package br.usp.cata.model;

import java.util.ArrayList;


public class CheckedSegment {
	
	private String segment;
	private ArrayList<Mistake> mistakes;
	
	public CheckedSegment(String segment, ArrayList<Mistake> mistakes) {
		this.segment = segment;
		this.mistakes = mistakes;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public ArrayList<Mistake> getMistakes() {
		return mistakes;
	}

	public void setMistakes(ArrayList<Mistake> mistakes) {
		this.mistakes = mistakes;
	}
	
}
