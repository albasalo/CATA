package br.usp.cata.component.lemmatizer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

import br.usp.cata.util.lemmatizer.AccentsChar;
import br.usp.cata.util.lemmatizer.AccentsLemmaTagChar;
import br.usp.cata.util.lemmatizer.ExpressionsChar;
import br.usp.cata.util.lemmatizer.Lemmatizer;
import br.usp.cata.util.lemmatizer.LemmatizerConstants;
import br.usp.cata.util.lemmatizer.SuffixesChar;


@Component
@ApplicationScoped
public class LemmatizerTrees {
	
	private final String accentsFile = "/br/usp/cata/resources/lemmatizer/accents.txt";
	private final String expressionsFile = "/br/usp/cata/resources/lemmatizer/expressions.txt";
	private final String suffixesFile = "/br/usp/cata/resources/lemmatizer/suffixes.txt";
	private final String suffixesFile2 = "/br/usp/cata/resources/lemmatizer/suffixes-b.txt";

	private static AccentsChar accentsRoot;
	private static ExpressionsChar expressionsRoot;
	private static SuffixesChar suffixesRoot;
	
	public LemmatizerTrees() {
		readAccentsFile();
		readExpressionsFile();
		readSuffixesFiles();
	}

	public static AccentsChar getAccentsRoot() {
		return accentsRoot;
	}

	public static ExpressionsChar getExpressionsRoot() {
		return expressionsRoot;
	}

	public static SuffixesChar getSuffixesRoot() {
		return suffixesRoot;
	}

	private AccentsChar insertAccentsTree(String word, int col, String lemma, int tag, AccentsChar root) {		
		if(root == null) {
			root = new AccentsChar();
			root.setCharacter(word.charAt(col));
			if(col == word.length() - 1) {
				root.setTagLemma(new AccentsLemmaTagChar());
				root.getTagLemma().setLemma(lemma);
				root.getTagLemma().setTag(tag);
			}
			else
				root.setTagLemma(null);
			
			root.setLess(null);
			root.setEqual(null);
			root.setGreater(null);
			
			if(col < word.length() - 1)
				root.setEqual(insertAccentsTree(word, col + 1, lemma, tag,
						root.getEqual()));
		}
		else {
			if(root.getCharacter() == word.charAt(col)) {
				if(col == word.length() - 1) {
					if(root.getTagLemma() == null) {
						root.setTagLemma(new AccentsLemmaTagChar());
						root.getTagLemma().setLemma(lemma);
						root.getTagLemma().setTag(tag);
					}
					else if(col < word.length() - 1)
						root.setEqual(insertAccentsTree(word, col + 1, lemma,
								tag, root.getEqual()));
				}
				else if(word.charAt(col) < root.getCharacter())
					root.setLess(insertAccentsTree(word, col, lemma,
							tag, root.getLess()));
				else
					root.setGreater(insertAccentsTree(word, col, lemma,
							tag, root.getGreater()));
			}
		}
		
		return root;
	}
	
	private void readAccentsFile() {
		accentsRoot = null;
		
		InputStream accents;
		try {
			accents = Lemmatizer.class.
					getResourceAsStream(accentsFile);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(accents, Charset.forName("UTF-8")));
			
			String line;
			while((line = br.readLine()) != null) {
				String[] elements = line.split(" ");
				accentsRoot = insertAccentsTree(elements[0], 0, 
						elements[1], Integer.parseInt(elements[2]), accentsRoot);
			}
			br.close();
			accents.close();
		} catch (Exception e) { //FIXME
			e.printStackTrace();
		}
	}
	
	private ExpressionsChar insertExpressionsTree(String word, int col, int tag, ExpressionsChar root) {		
		if (root == null) {
			root = new ExpressionsChar();
			root.setCharacter(word.charAt(col));
			if(col == word.length() - 1)
				root.setTag(tag);
			else
				root.setTag(LemmatizerConstants.undefinedTag);
			
			root.setLess(null);
			root.setEqual(null);
			root.setGreater(null);
			
			if(col < word.length() - 1)
				root.setEqual(insertExpressionsTree(word, col + 1, tag, root.getEqual()));
		}
		else {
			if(root.getCharacter() == word.charAt(col)) {
				if(col == word.length() - 1) {
					if(root.getTag() == LemmatizerConstants.undefinedTag)
						root.setTag(tag);
				}
				
				else if(col < word.length() - 1)
					root.setEqual(insertExpressionsTree(word, col + 1, tag, root.getEqual()));
			}		
			else if(word.charAt(col) < root.getCharacter())
				root.setLess(insertExpressionsTree(word, col, tag, root.getLess()));			
			else
				root.setGreater(insertExpressionsTree(word, col, tag, root.getGreater()));
		}
		
		return root;
	}
	
	private void readExpressionsFile() {
		expressionsRoot = null;
		
		InputStream expressions;
		try {
			expressions = Lemmatizer.class.getResourceAsStream(expressionsFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(expressions));
			
			String line;
			while((line = br.readLine()) != null) {
				String[] elements = line.split(" ");
				expressionsRoot = insertExpressionsTree(elements[0], 0,
						Integer.parseInt(elements[1]), expressionsRoot);
			}
			br.close();
			expressions.close();
		} catch (Exception e) { //FIXME
			e.printStackTrace();
		}
	}
	
	private SuffixesChar insertSuffixesTree(String word, int col, int exact,
			String lemmaTag, SuffixesChar root) {
		int x, y, i = 0, put;
		String tag, freq, sfxLemma = "";
		
		if(root == null) {
			root = new SuffixesChar();
			root.setCharacter(word.charAt(col));
			for(x = 0; x < LemmatizerConstants.numberOfTags; x++) {
				root.getLemma()[x] = null;
				root.getTags()[x] = 0.0;
			}
			root.setExactTag(LemmatizerConstants.undefinedTag);
			if(col == word.length() - 1) {
				for(x = 0; x < lemmaTag.length();) {
					tag = lemmaTag.substring(x, x+2);
					x += 2;
					freq = lemmaTag.substring(x, x+4);
					x += 4;
					i = Integer.parseInt(tag);
					if(exact != 0)
						root.setExactTag(i);
					root.getTags()[i] = Double.parseDouble(freq);
					y = 0;
					x++;
					for(; lemmaTag.charAt(x) != '!';)
						sfxLemma = sfxLemma.substring(0, y++) + lemmaTag.charAt(x++);
					x++;
					if(!sfxLemma.equals(""))
						root.getLemma()[i] = sfxLemma;
				}
			}
			root.setLess(null);
			root.setEqual(null);
			root.setGreater(null);
			if(col < word.length() - 1)
				root.setEqual(insertSuffixesTree(word, col + 1, exact,
						lemmaTag, root.getEqual()));
		}
		else {
			if(root.getCharacter() == word.charAt(col)) {
				if(col == word.length() - 1) {
					for(x = 0; x < lemmaTag.length();) {
						tag = lemmaTag.substring(x, x+2);
						x += 2;
						freq = lemmaTag.substring(x, x+4);
						x += 4;
						i = Integer.parseInt(tag);
						put = 0;
						if(exact != 0) {
							root.setExactTag(i);
							put = 1;
						}
						else if(root.getExactTag() != i)
							put = 1;
						
						if(put != 0) {
							root.getTags()[i] = Double.parseDouble(freq);
							y = 0;
							x++;
							for(; lemmaTag.charAt(x) != '!';)
								sfxLemma = 
								sfxLemma.substring(0, y++) + lemmaTag.charAt(x++);
							x++;
							if(!sfxLemma.equals(""))
								root.getLemma()[i] = sfxLemma;
						}
					}
				}
				else if(col < word.length() - 1)
					root.setEqual(
							insertSuffixesTree(word, col+1, exact, lemmaTag, root.getEqual()));
			}
			else if(word.charAt(col) < root.getCharacter()) {
				root.setLess(
						insertSuffixesTree(word, col, exact, lemmaTag, root.getLess()));
			}
			else
				root.setGreater(
						insertSuffixesTree(word, col, exact, lemmaTag, root.getGreater()));
		}
		return root;
	}

	private void readSuffixesFiles() {	
		suffixesRoot = null;
		String tag;

		InputStream suffixes;
		try {
			for(int i = 0; i < 2; i++) {
				if(i == 0)
					suffixes = Lemmatizer.class.getResourceAsStream(suffixesFile);
				else
					suffixes = Lemmatizer.class.getResourceAsStream(suffixesFile2);
				BufferedReader br = new BufferedReader(new InputStreamReader(suffixes));
				
				String line;
				while((line = br.readLine()) != null) {
					String[] elements = line.split(" ");
					String suffix = elements[0];
					tag = elements[1];
					suffixesRoot = insertSuffixesTree(suffix, 0, i, tag, suffixesRoot);
				}
				br.close();
				suffixes.close();
			}
		} catch (Exception e) { //FIXME
			e.printStackTrace();
		}
	}
	
}
