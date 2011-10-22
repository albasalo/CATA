package br.usp.cata.service;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.model.PatternSuggestionPair;
import br.usp.cata.util.Tokenizer;


@RequestScoped
@Component
public class PatternSuggestionPairService {
	
	private final Tokenizer tokenizer;
	
	public PatternSuggestionPairService(ServletContext servletContext) {
		tokenizer = new Tokenizer(servletContext);
	}
	
	public void tokenizePattern(PatternSuggestionPair patternSuggestionPair) {
		patternSuggestionPair.setTokenizedPatternBytes(
				tokenizer.tokenize(patternSuggestionPair.getPattern()));
	}

}
