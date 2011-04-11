package br.usp.correcao.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.arabidopsis.ahocorasick.SearchResult;

import br.usp.correcao.component.Dictionary;


//FIXME arrumar o nome dessa classe para denotar que e a linha mais a analise
public class Line {
	
	private String lineText;
	private byte[] originalLineBytes;
	private byte[] lineBytes;
	private ArrayList<ErrorOccurrence> errors;
	private int errorsFound;
	
	public Line(String lineText) {
		this.originalLineBytes = lineText.getBytes();
		this.lineBytes = lineText.toLowerCase().getBytes();
		this.lineText = null;
		this.errors = null;
		this.errorsFound = 0;
	}
	
	public void analyze() {
		errors = new ArrayList<ErrorOccurrence>();
		Iterator<?> searcher = Dictionary.getTree().search(lineBytes);
	    while (searcher != null && searcher.hasNext()) {
	    	errorsFound++;
	        SearchResult result = (SearchResult)searcher.next();
	        errors.add(new ErrorOccurrence((DictionaryEntry)result.getOutputs().iterator().next(), result.getLastIndex()));
	    }
	}
	
	//FIXME tirar isso daqui - construir isso no jsp
	// O que este metodo tem que fazer eh mapear os indices dos erros
	public String getLineText() {
		if(errors == null)
			analyze();
		if(lineText == null) {
			lineText = "";
			byte[] newLineBytes = new byte[originalLineBytes.length];
			Iterator<ErrorOccurrence> errorsIterator = errors.iterator();
			if(errorsIterator != null && errorsIterator.hasNext()) {
				ErrorOccurrence occurrence = (ErrorOccurrence) errorsIterator.next();
				int i, j;
				int first = occurrence.getFirstIndex();
				int last = occurrence.getLastIndex();
				for(i = 0, j = 0; i < originalLineBytes.length;) {
					if(i == first) {
						lineText += (new String(newLineBytes, 0, j)
									+ "<span class=\"popupConf\"><span class=\"highlightedText\">");
						if(occurrence.getSuggestion().getType() == Type.ERROR)
							lineText += "<font style=\"background-color: #FF7373\">";
						else
							lineText += "<font style=\"background-color: #FFD34F\">";
						for(j = 0; i != last; i++, j++)
							newLineBytes[j] = originalLineBytes[i];
						lineText += (new String(newLineBytes, 0, j) + "</font></span><span class=\"popup\">"
										+ "<span class=\"popupContent\">"
										+ "<b>&nbsp;" + occurrence.getSuggestion().getType().getErrorType().replaceAll(" ", "&nbsp;") + "&nbsp;</b> "
										+ "&nbsp;<b>Sugestão:</b>&nbsp;"
										+ occurrence.getSuggestion().getSuggestion().replaceAll(" ", "&nbsp;")
										+ "&nbsp;</span></span>"
										+ "</span>");
						if(errorsIterator.hasNext()) {
							occurrence = (ErrorOccurrence) errorsIterator.next();
							first = occurrence.getFirstIndex();
							last = occurrence.getLastIndex();
						}
						j = 0;
					}
					else {
						newLineBytes[j] = originalLineBytes[i];
						i++; j++;
					}
				}
				lineText += new String(newLineBytes, 0, j);
			}
			else
				lineText += new String(originalLineBytes);
		}
		
		return lineText;
	}
	
	public int getErrorsFound() {
		return errorsFound;
	}

}
