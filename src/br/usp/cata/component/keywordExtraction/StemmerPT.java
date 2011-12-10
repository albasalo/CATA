package br.usp.cata.component.keywordExtraction;

import ptstemmer.Stemmer;
import ptstemmer.exceptions.PTStemmerException;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;


@Component
@ApplicationScoped
public class StemmerPT {

	private static Stemmer st;
	
	public StemmerPT() {
		try {
			st = Stemmer.StemmerFactory(Stemmer.StemmerType.ORENGO);
		} catch (PTStemmerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st.enableCaching(1000);
	}
	
	public static Stemmer getStemmerPT() {
		return st;
	}
}