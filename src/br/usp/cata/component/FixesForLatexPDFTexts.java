package br.usp.cata.component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;


@Component
@ApplicationScoped
public class FixesForLatexPDFTexts {

	public static Pattern pattern;
	public static Map<String, String> tokens;
	
	public FixesForLatexPDFTexts() {
		tokens = new HashMap<String,String>();
		tokens.put("c¸", "ç");
		tokens.put("a˜", "ã");
		tokens.put("o˜", "õ");
		tokens.put("a´", "á");
		tokens.put("e´", "é");
		tokens.put("i´", "í");
		tokens.put("´ı", "í");
		tokens.put("ı´", "í");
		tokens.put("o´", "ó");
		tokens.put("u´", "ú");
		tokens.put("aˆ", "â");
		tokens.put("eˆ", "ê");
		tokens.put("oˆ", "ô");
		tokens.put("a`", "à");

		String patternString = "(" + StringUtils.join(tokens.keySet(), "|") + ")";
		pattern = Pattern.compile(patternString);
	}
	
	public static Pattern getPattern() {
		return pattern;
	}
	
	public static Map<String, String> getTokens() {
		return tokens;
	}
	
}
