package br.usp.cata.service;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.usp.cata.model.PatternSuggestionElement;
import br.usp.cata.util.Tokenizer;


@RequestScoped
@Component
public class PatternSuggestionElementService {
	
	private final Tokenizer tokenizer;
	
	public PatternSuggestionElementService(ServletContext servletContext) {
		tokenizer = new Tokenizer(servletContext);
	}
	
	public void tokenizePattern(PatternSuggestionElement patternSuggestionElement) {
		patternSuggestionElement.setTokenizedPatternBytes(
				tokenizer.tokenize(patternSuggestionElement.getPattern()));
	}

}
