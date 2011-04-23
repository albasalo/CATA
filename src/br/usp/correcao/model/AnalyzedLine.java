package br.usp.correcao.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.arabidopsis.ahocorasick.SearchResult;

import br.usp.correcao.component.Tree;


public class AnalyzedLine {
	
	private ArrayList<AnalyzedSegment> analyzedLine;
	private int errorsFound;
	
	public AnalyzedLine(String line) {
		analyze(line);
	}
	
	private void buildLine(Iterator<BrokenRule> errorsIterator, byte[] lineBytes) {
		analyzedLine = new ArrayList<AnalyzedSegment>();
		
		if(errorsIterator != null && errorsIterator.hasNext()) {
			byte[] segmentBytes = new byte[lineBytes.length];
			BrokenRule occurrence = (BrokenRule) errorsIterator.next();
			int first = occurrence.getFirstIndex();
			int last = occurrence.getLastIndex();
			int i, j;
		    for(i = 0, j = 0; i < lineBytes.length;) {
				if(i == first) {
					if(j != 0)
						analyzedLine.add(new AnalyzedSegment(
								new String(segmentBytes, 0, j), null));
					for(j = 0; i != last; i++, j++)
						segmentBytes[j] = lineBytes[i];
					analyzedLine.add(new AnalyzedSegment(
							new String(segmentBytes, 0, j), occurrence.getSuggestion()));
					if(errorsIterator.hasNext()) {
						occurrence = (BrokenRule) errorsIterator.next();
						first = occurrence.getFirstIndex();
						last = occurrence.getLastIndex();
					}
					j = 0;
				}
				else {
					segmentBytes[j] = lineBytes[i];
					i++; j++;
				}
			}
		    if(j != 0)
		    	analyzedLine.add(new AnalyzedSegment(new String(segmentBytes, 0, j), null));
		}
		else
			analyzedLine.add(new AnalyzedSegment(new String(lineBytes), null));	
	}
	
	private void analyze(String line) {
		ArrayList<BrokenRule> errors = new ArrayList<BrokenRule>();
		byte[] lineBytes = line.getBytes();
		
		Iterator<?> searcher = Tree.getTree().search(line.toLowerCase().getBytes());
	    while(searcher != null && searcher.hasNext()) {
	    	errorsFound++;
	        SearchResult result = (SearchResult) searcher.next();
	        errors.add(new BrokenRule(
	        	(Rule) (result.getOutputs().iterator().next()), result.getLastIndex()));
	    }
	    
	    buildLine(errors.iterator(), lineBytes);	
	}
	
	public ArrayList<AnalyzedSegment> getAnalyzedLine() {
		return analyzedLine;
	}
	
	public int getErrorsFound() {
		return errorsFound;
	}

}
