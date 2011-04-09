package br.usp.correcao.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.arabidopsis.ahocorasick.AhoCorasick;

public class DictionaryTree {
	
	private String dicFile;
	private AhoCorasick dicTree;
	private Pattern firstDelimiter, secondDelimiter;
	
	public DictionaryTree() {
		dicTree = new AhoCorasick();
		dicFile = "/br/usp/correcao/resources/dictionary.txt";
		firstDelimiter = Pattern.compile("\t");
		secondDelimiter = Pattern.compile(";");
		buildTree();
	}
	
	private void buildTree() {
		InputStream is = DictionaryTree.class.getResourceAsStream(dicFile);
		Reader reader = new BufferedReader(new InputStreamReader(is));
		BufferedReader br = new BufferedReader(reader);
	    String line;
	    try {
	    	while ((line = br.readLine()) != null) {
				String[] entry = firstDelimiter.split(line);
				if(entry != null) {
					String[] errors = secondDelimiter.split(entry[0]);
					for(int i = 0; i < errors.length; i++) {
						dicTree.add(errors[i].getBytes(),
								new DictionaryEntry(errors[i].toLowerCase(), entry[1]));
					}
				}
	    	}
	    } catch (IOException ioe) {
	    	//TODO
	    }
	    try {
			is.close();
		} catch (IOException ioe) {
			//TODO
		}
		dicTree.prepare();
	}
	
	public Iterator<?> search(String text) {
		return dicTree.search(text.getBytes());
	}

}
