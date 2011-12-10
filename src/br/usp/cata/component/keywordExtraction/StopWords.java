package br.usp.cata.component.keywordExtraction;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;


@Component
@ApplicationScoped
public class StopWords {
	
	private String stopWordsPTFile = "/br/usp/cata/resources/keywordExtraction/stopWordsPT.txt";
	private String stopWordsENFile = "/br/usp/cata/resources/keywordExtraction/stopWordsEN.txt";
	private static HashSet<String> stopWordsPT;
	private static HashSet<String> stopWordsEN;
	
	public StopWords() {
		addStopWordsPT();
		addStopWordsEN();
	}
	
	private void addStopWords(String file, HashSet<String> stopWords) {
		InputStream is;
		try {
			is = StopWords.class.getResourceAsStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			
			String line;
			while((line = br.readLine()) != null)
				stopWords.add(line);
				
			br.close();
			is.close();
		} catch (Exception e) { //FIXME
			e.printStackTrace();
		}
	}

	private void addStopWordsPT() {
		stopWordsPT = new HashSet<String>();	
		addStopWords(stopWordsPTFile, stopWordsPT);
	}
	
	private void addStopWordsEN() {
		stopWordsEN = new HashSet<String>();
		addStopWords(stopWordsENFile, stopWordsEN);
	}
	
	public static HashSet<String> getStopWordsPT() {
		return stopWordsPT;
	}
	
	public static HashSet<String> getStopWordsEN() {
		return stopWordsEN;
	}
}
