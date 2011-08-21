package br.usp.cata.model;


public class Mistake implements Comparable<Mistake> {
	
	private RuleInstance brokenRule;
	private Position start, end;
	
	public Mistake(RuleInstance brokenRule, Position start, Position end) {
		this.brokenRule = brokenRule;
		this.start = start;
		this.end = end;
	}

	public RuleInstance getBrokenRule() {
		return brokenRule;
	}

	public void setBrokenRule(RuleInstance brokenRule) {
		this.brokenRule = brokenRule;
	}

	public Position getStart() {
		return start;
	}

	public void setStart(Position start) {
		this.start = start;
	}

	public Position getEnd() {
		return end;
	}

	public void setEnd(Position end) {
		this.end = end;
	}

	public int compareTo(Mistake o) {
		if(this.getStart().getLineNumber() == o.getStart().getLineNumber())
			return (this.getStart().getCharIndex() - o.getStart().getCharIndex());
		
		return (this.getStart().getLineNumber() - o.getStart().getLineNumber());
	}
	
}
