package br.usp.cata.service;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

import br.usp.cata.model.Languages;
import br.usp.cata.model.PatternSuggestionPair;
import br.usp.cata.util.Tokenizer;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;


@RequestScoped
@Component
public class PatternSuggestionPairService {
	
	private final Tokenizer tokenizer;
	private final StanfordCoreNLP pipeline;
	
	public PatternSuggestionPairService(ServletContext servletContext) {
		tokenizer = new Tokenizer(servletContext);
		
		Properties props = new Properties();
	    props.put("annotators", "tokenize, ssplit");
	    pipeline = new StanfordCoreNLP(props);
	}
	
	public void tokenizePattern(PatternSuggestionPair patternSuggestionPair, Languages language) {
		if(language == Languages.PORTUGUESE)
			patternSuggestionPair.setTokenizedPatternBytes(
					tokenizer.tokenize(patternSuggestionPair.getPattern()));
		
		else if(language == Languages.ENGLISH) {
			String text = patternSuggestionPair.getPattern();
			String tokenizedText = "";
			
		    Annotation document = new Annotation(text);
		    pipeline.annotate(document);
		    
		    List<CoreMap> sentences = document.get(SentencesAnnotation.class);	    
		    for(CoreMap sentence: sentences)
		    	for(CoreLabel token: sentence.get(TokensAnnotation.class))
		    	tokenizedText += (" " + token.get(TextAnnotation.class));
		    tokenizedText += " ";
		    			
		    patternSuggestionPair.setTokenizedPatternBytes(tokenizedText.getBytes());
		}	
	}

}
