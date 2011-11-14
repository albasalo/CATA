package br.usp.cata.util.lemmatizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.usp.cata.component.lemmatizer.LemmatizerTrees;
import br.usp.cata.model.Position;


public class Lemmatizer {
	
	private AccentsChar accentsRoot;
	private ExpressionsChar expressionsRoot;
	private SuffixesChar suffixesRoot;
	
	private SuffixesChar longestSuffix;
	private int greaterColumn;
	private String[] sfxLemma;
	private double[] sfxTag;
	private LemmatizerToken tkReady;
	private LemmatizerToken tkNeig1;
	private LemmatizerToken tkNeig2;
	private LemmatizerToken tkCurrent;
	private LemmatizerToken tkReading;
	private boolean combined;
	
	private List<String> tokenizedText;
	private int tokenizedTextIndex;
	private int offset;
	private HashMap<Integer, Position> startsList;
	private HashMap<Integer, Position> endsList;
	private HashMap<Integer, Position> startsLemmatized;
	private HashMap<Integer, Position> endsLemmatized;
	private List<Byte> lemmatizedText;
	private int blankBytesLength = " ".getBytes().length;

	public Lemmatizer() {
		this.sfxLemma = new String[LemmatizerConstants.numberOfTags];
		this.sfxTag = new double[LemmatizerConstants.numberOfTags];
		
		accentsRoot = LemmatizerTrees.getAccentsRoot();
		expressionsRoot = LemmatizerTrees.getExpressionsRoot();
		suffixesRoot = LemmatizerTrees.getSuffixesRoot();
	}
	
	public void lemmatize(List<String> tokenizedText, int offset, List<Byte> lemmatizedText,
			HashMap<Integer, Position> startsList, HashMap<Integer, Position> endsList,
			HashMap<Integer, Position> startsLemmatized, HashMap<Integer, Position> endsLemmatized) {
		this.tokenizedText = tokenizedText;
		this.offset = offset;
		this.tokenizedTextIndex = 0;
		this.lemmatizedText = lemmatizedText;
		this.startsList = startsList;
		this.endsList = endsList;
		this.startsLemmatized = startsLemmatized;
		this.endsLemmatized = endsLemmatized;
		
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			sfxLemma[i] = "";
			sfxTag[i] = 0.00;
		}

		createCircularList();		
		tagAndLemmatize();		
	}
	
	private void createCircularList() {
		tkReady = new LemmatizerToken();		
		tkNeig1 = tkReady;
		
		for(int i = 0; i < LemmatizerConstants.maxCircularListElements; i++) {
			tkNeig1.setNext(new LemmatizerToken());
			tkNeig1.getNext().setPrev(tkNeig1);
			tkNeig1 = tkNeig1.getNext();
		}
		
		tkNeig1.setNext(tkReady);
		tkReady.setPrev(tkNeig1);
		
		tkNeig2 = tkReady.getNext();
		tkNeig1 = tkNeig2.getNext();
		tkCurrent = tkNeig1.getNext();
		tkReading = tkCurrent.getNext().getNext().getNext();
	}
	
	private void tagAndLemmatize() {
		getNextToken(tkReading);
		combine();
		
		while(!tkReady.getWord().equals(".EOF")) {
			if(tkCurrent.getTag() >= LemmatizerConstants.undefinedTag) {
				if(tkCurrent.getTag() == LemmatizerConstants.undefinedTag)
					analyzeTkCurrent();
				
				analyzeExpressionTkCurrent();
				
				if(tkCurrent.getTag() == LemmatizerConstants.undefinedTag)
					analyzeAccentsTkCurrent();
				
				if(tkCurrent.getTag() == LemmatizerConstants.undefinedTag)
					analyzeSuffixTkCurrent();
			}
			
			if(tkNeig1.getTag() == LemmatizerConstants.undefinedTag)
				analyzeTkNeig(tkNeig1);
			
			if(tkNeig2.getTag() != LemmatizerConstants.defaultTag &&
					tkNeig2.getTag() != LemmatizerConstants.notPrintable &&
					tkNeig2.getResult().equals(""))
				analyzeTkNeig(tkNeig2);
			
			tkReady = tkNeig2;
			tkNeig2 = tkNeig1;
			tkNeig1 = tkCurrent;
			tkCurrent = tkCurrent.getNext();
			tkReading = tkReading.getNext();
			
			if(!tkReady.getWord().equals(".EOF")) {
				if(tkReady.getTag() != LemmatizerConstants.defaultTag &&
						tkReady.getTag() != LemmatizerConstants.notPrintable)
					analyzeTkReady();
				
				if(tkReady.isPrintable()) {
					Position start = startsList.get(tkReady.getIndex());
					Position end = endsList.get(tkReady.getIndex());
					
					int startLemma = lemmatizedText.size();
					byte[] lemmaBytes;
					if(!tkReady.getLemma().equals("")) 
						lemmaBytes = (" " + tkReady.getLemma()).getBytes();
					else
						lemmaBytes = (" " + tkReady.getWord()).getBytes();
					for(byte lemmaByte : lemmaBytes)
						lemmatizedText.add(lemmaByte);
					int endLemma = lemmatizedText.size() + blankBytesLength;
					
					startsLemmatized.put(startLemma, start);
					endsLemmatized.put(endLemma, end);
				}
				
				if(combined)
					combined = false;
				else {
					getNextToken(tkReading);
					combine();
				}
			}
		}
	}
	
	private void searchAccents(String word, int col, LemmatizerToken token, AccentsChar root) {
		if(root != null) {
			if(word.charAt(col) == root.getCharacter()) {
				if(root.getTagLemma() != null) {
					token.setLemma(root.getTagLemma().getLemma());
					token.setTag(root.getTagLemma().getTag());
				}
				else if(col < word.length()-1) {
					col++;
					searchAccents(word, col, token, root.getEqual());
				}
			}
			
			else if(word.charAt(col) < root.getCharacter())
				searchAccents(word, col, token, root.getLess());
			
			else
				searchAccents(word, col, token, root.getGreater());
		}
	}
	
	private int searchExpressions(String word, int col, ExpressionsChar root) {
		int tag = LemmatizerConstants.undefinedTag, tag2;
		
		if (root != null) {
			if (word.charAt(col) == root.getCharacter()) {
				if(col == word.length() - 1) {
					greaterColumn = col;
					tag = root.getTag();
				}
				else {
					if(word.charAt(col+1) == '=') {
						if(root.getEqual() != null) {
							if(root.getEqual().getCharacter() == '=') {
								greaterColumn = col;
								tag = root.getTag();
							}
						}
					}
					else {
						greaterColumn = col;
						tag = root.getTag();
					}
				}
				if(col < word.length() - 1) {
					tag2 = searchExpressions(word, col + 1, root.getEqual());
					if(tag2 != LemmatizerConstants.undefinedTag)
						tag = tag2;
				}
			}
			else if(word.charAt(col) < root.getCharacter())
				tag = searchExpressions(word, col, root.getLess());
			else
				tag = searchExpressions(word, col, root.getGreater());
		}
		
		return(tag);
	}
	
	private void searchSuffixes(String word, int col, SuffixesChar root) {
		if(root != null) {
			if(word.charAt(col) == root.getCharacter()) {
				greaterColumn = col;
				longestSuffix = root;
				if(col < word.length() - 1)
					searchSuffixes(word, col + 1, root.getEqual());
			}
			
			else if(word.charAt(col) < root.getCharacter())
				searchSuffixes(word, col, root.getLess());
			
			else
				searchSuffixes(word, col, root.getGreater());
		}
	}
	
	private void getNextToken(LemmatizerToken t) {
		if(tokenizedTextIndex >= tokenizedText.size())
			t.setWord(".EOF");
		else {
			t.reset();
			t.setIndex(tokenizedTextIndex + offset);
			t.setPrintable(true);
			t.setWord(tokenizedText.get(tokenizedTextIndex++));
			t.setLemma("");
			t.setSWordS("");
			t.setTag(LemmatizerConstants.undefinedTag);
		}
	}
	
	private void combination(String prevWord, String prevSWordS, String prevLemma,
			int prevTag, String nextWord, String nextSWordS, String nextLemma,
			int nextTag) {
		
		if(prevTag != LemmatizerConstants.undefinedTag)
			tkReading.setResult(
					prevWord + " " + prevLemma + " " + LemmatizerTags.getTag(prevTag));
		
		if(prevWord.length() > 0) {
			tkReading.setWord(prevWord);
			tkReading.setLemma(prevLemma);
			tkReading.setSWordS(prevSWordS);
			tkReading.setTag(prevTag);
		}
		
		if(nextTag >= 0)
			tkReading.getNext().setResult(
					nextWord + " " + nextLemma + " " + LemmatizerTags.getTag(nextTag));
		else
			tkReading.getNext().setResult(
					nextWord + " " + nextLemma + " _UN");
		
		if(nextWord.length() > 0) {
			tkReading.getNext().setPrintable(true);
			tkReading.getNext().setWord(nextWord);
			tkReading.getNext().setLemma(nextLemma);
			tkReading.getNext().setSWordS(nextSWordS);
			tkReading.getNext().setTag(nextTag);
		}
		
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			tkReading.getNext().getPrevs()[i] = 0.00;
			tkReading.getNext().getSfxTags()[i] = 0.00;
			tkReading.getNext().getNexts()[i] = 0.00;
			tkReading.getNext().getSfxLemmas()[i] = "";
		}
		
		combined = true;
	}
	
	private boolean equalsList(String s, List<String> termos) {
		for(String termo : termos)
			if(s.equals(termo))
				return true;
				
		return false;
	}
	
	// TODO remove magic numbers - use tags
	private void combine() {
		String word, sWordS, line, nextWord;
		
		tkReading.setSWordS(" " + tkReading.getWord() + " ");		
		nextWord = tkReading.getWord().substring(1);
		
		String auxWord = tkReading.getWord();
		if(auxWord.equals("à"))
			combination("a"," a ", "a", 16, "a", " a ", "a", 0);
		
		else if(equalsList(auxWord, Arrays.asList("ao", "aos")))
			combination("a", " a ", "a", 16, nextWord, " o ", "o", 0);
		
		else if(equalsList(auxWord, Arrays.asList("da", "do", "das", "dos")))
			combination("de", " de ", "de", 16, nextWord," o ", "o", 0);
		
		else if(equalsList(auxWord, Arrays.asList("na", "no", "nas", "nos")))
			combination("em", " em ", "em", 16, nextWord, " o ", "o", 0);
		
		else if(equalsList(auxWord, Arrays.asList("num", "nums")))
			combination("em", " em ", "em", 16, nextWord, " um ", "um", 1);
		
		else if(equalsList(auxWord, Arrays.asList("noutro", "noutra", "noutros", "noutras")))
			combination("em", " em ", "em", 16, nextWord, " outro ", "outro", 12);
		
		else if(equalsList(auxWord, Arrays.asList("doutro", "doutra", "doutros", "doutras")))
			combination("de", " de ", "de", 16, nextWord, " outro ", "outro", 12);
		
		else if(equalsList(auxWord, Arrays.asList("naquele", "naquela", "naqueles", "naquelas")))
			combination("em", " em ", "em", 16, nextWord, " aquele ", "aquele", 11);
		
		else if(equalsList(auxWord, Arrays.asList("neste", "nesta", "nestes", "nestas")))
			combination("em", " em ", "em", 16, nextWord, " este ", "este", 11);
		
		else if(equalsList(auxWord, Arrays.asList("deste", "desta", "destes", "destas")))
			combination("de", " de ", "de", 16, nextWord, " este ", "este", 11);
		
		else if(equalsList(auxWord, Arrays.asList("nesse", "nessa", "nesses", "nessas")))
			combination("em", " em ", "em", 16, nextWord, " esse ", "esse", 11);
		
		else if(equalsList(auxWord, Arrays.asList("desse", "dessa", "desses", "dessas")))
			combination("de", " de ", "de", 16, nextWord, " esse ", "esse", 11);
		
		else if(equalsList(auxWord, Arrays.asList("daquele", "daquela", "daqueles", "daqueles")))
			combination("de", " de ", "de", 16, nextWord, " aquele ", "aquele", 11);
		
		else if(auxWord.equals("daquilo"))
			combination("de", " de ", "de", 16, "aquilo", " aquilo ", "aquilo", 11);
		
		else if(auxWord.equals(" àquilo "))
			combination("a", " a ", "a", 16, "aquilo", " aquilo ", "aquilo", 11);
		
		else if(tkReading.getWord().charAt(0) == 'à' && tkReading.getWord().charAt(1) == 'q')
			combination("a", " a ", "a", 16, "aquele", " aquele ", "aquele", 11);
		
		else if(equalsList(auxWord, Arrays.asList("daqui", "dali", "dacolá"))) {
			String lemma;
			word = tkReading.getWord().substring(1);
			lemma = toNoAccentLowerCase(word);
			sWordS = " " + word + " ";
			combination("de", " de ", "de", 16, word, sWordS, lemma, 3);
		}
		
		else if(tkReading.getWord().contains("-")) {
			int x, y, w, z, count;
			
			word = tkReading.getSWordS().substring(1);
			line = word.substring(0, word.length() - 1);
			count = 0; w = -1; z = 1; y = 1;
			for(x = 0; x < line.length(); x++) {
				if(line.charAt(x) == '-') {
					count++;
					if(count == 1)
						y = x;
					if(count == 2)
						w = x;
				}
			}
			if(w == -1)
				w = x;
			
			String aux = " ";
			for(z = 1; z < w - y; z++)
				aux += line.charAt(y + z);
			z++;
			aux += " ";
			sWordS = aux;
			
			if(equalsList(sWordS, Arrays.asList(" me ", " te ", " se ", " no ", " nos ",
					" vos ", " o ", " a ", " lo ", " la ", " los ", " las ", " lha ",
					" lhe ", " os ", " as ", " lhes "))) {
				word = word.substring(0, y);
				tkReading.setSWordS(tkReading.getSWordS().substring(0, y + 1));
				if(equalsList(sWordS, Arrays.asList(" me ", " te ", " se ", " nos ",
						" vos ", " lha ", " lhe ", "lhes "))) {
					String lineSubstring = "";
					if((w >= 0) && (w + 1 <= line.length() - 1))
						lineSubstring = line.substring(w+1);
					word += lineSubstring;
					tkReading.setSWordS(tkReading.getSWordS() + lineSubstring);
				}
				else {
					if(w != - 1 && w != x) {
						sWordS = sWordS.substring(0, sWordS.length() - 1);
						sWordS += ("-" + line.substring(w+1) + " ");
					}
				}
				tkReading.setSWordS(tkReading.getSWordS() + " ");
				sWordS = sWordS.substring(0, sWordS.length() - 1);
				nextWord = sWordS.substring(1);
				if(w != -1 && w != x)
					combination(word, tkReading.getSWordS(), "",
							LemmatizerConstants.undefinedTag, nextWord, sWordS, "",
							LemmatizerConstants.undefinedTag);
				else {
					if(sWordS.charAt(1) == 'o' || sWordS.charAt(1) == 'a')
						combination(word, tkReading.getSWordS(), "",
								LemmatizerConstants.undefinedTag, nextWord, " o ", "o", 15);
					else
						combination(word, tkReading.getSWordS(), "",
								LemmatizerConstants.undefinedTag, nextWord, " me ", "me", 15);
					if(tkReading.getWord().charAt(tkReading.getWord().length() - 1) == 's') {
						String prevWord = tkReading.getPrev().getWord();
						if(prevWord.length() > 0) {
							int length = prevWord.length() - 1;
							if(prevWord.charAt(length) == 'a' ||
									prevWord.charAt(length) == 'e' ||
									prevWord.charAt(length) == 'o') {
								tkReading.getPrev().setWord(prevWord.substring(0, word.length()) + "s");
								tkReading.getSfxTags()[6] = -9999;
							}
						}
					}
					for(x = 0; x < LemmatizerConstants.numberOfTags; x++)
						tkReading.getSfxTags()[x] = -1.00;
					
					tkReading.getSfxTags()[18] = 0.50;
					tkReading.getSfxTags()[19] = 0.90;
				}
			}
			else {
				word = word.substring(0, y);
				tkReading.setSWordS(tkReading.getSWordS().substring(0, y+1) + " ");
				sWordS = sWordS.substring(0, sWordS.length() - 1);
				if(w != -1 && w != x)
					sWordS += ("-" + line.substring(w+1));
				nextWord = sWordS.substring(1);
				sWordS += " ";
				combination(word, tkReading.getSWordS(), "", LemmatizerConstants.undefinedTag,
						nextWord, sWordS, "", LemmatizerConstants.undefinedTag);
				tkReading.getSfxTags()[17] = 1.00;
				tkReading.getNext().getSfxTags()[17] = 1.00;
			}
		}
	}
	
	private void setTkCurrent(String lemma, int tag, String tagString) {
		tkCurrent.setLemma(lemma);
		tkCurrent.setTag(tag);
		tkCurrent.getSfxTags()[tag] = 1.00;
		tkCurrent.setResult(tkCurrent.getWord() + " " + tkCurrent.getLemma() + " " + tagString);
	}
	
	private boolean analyzeTkCurrentAux(String sWordS, List<String> sWordSS, int tag,
			String lemma, String tagString) {
		for(String termo : sWordSS) {
			if(sWordS.equals(termo)) {
				setTkCurrent(lemma, tag, tagString);
				return true;
			}
		}
		
		return false;
	}
	
	private void analyzeTkCurrent() {
		String word = tkCurrent.getWord();
		
		if(analyzeTkCurrentAux(word,
				Arrays.asList("o", "os", "as"), 0, "o", "_AD"));
		
		else if(analyzeTkCurrentAux(word,
				Arrays.asList("um", "uma", "uns", "umas"), 1, "um", "_AI"));
		
		else if(analyzeTkCurrentAux(word,
				Arrays.asList("e", "ou"), 4, word, "_CC"));
		
		else if(analyzeTkCurrentAux(word,
				Arrays.asList("em", "de", "com", "sem", "até", "contra", "durante",
						"desde", "após", "sob", "dentre", "ante", "apud", "exceto", "in",
						"per", "perante"), 16, toNoAccentLowerCase(word), "_PR"));
		
		if(word.length() == 1) {
			if(analyzeTkCurrentAux(word,
					Arrays.asList(".", "!", "?", "¿", ":", "[", "]", "{", "}", "=",
							"*", "§", "#", "<", ">", "-"), 14, word, "_PN"));
			
			else if(analyzeTkCurrentAux(word,
					Arrays.asList(",", ";", "(", ")"), 20, word, "_VG"));
			
			else if(analyzeTkCurrentAux(word,
					Arrays.asList("$", "¢", "£", "%", "Å", "å"), 17, word, "_SU"));
			
			else if(analyzeTkCurrentAux(word,
					Arrays.asList("&", "+"), 4, word, "_CC"));
			
			else if(analyzeTkCurrentAux(word,
					Arrays.asList("/"), 16, word, "_PR"));
			
			else if(analyzeTkCurrentAux(word,
					Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
							7, word, "_NC"));
		}
		else {
			int cont = 0;
			
			List<Character> caracs =
				Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',',
						'.');
			
			for(int i = 0; i < word.length(); i++) {
				for(char c : caracs) {
					if(word.charAt(i) == c) {
						cont++;
						break;
					}
				}
			}
			if(cont > 0) {
				if(cont == word.length())
					setTkCurrent(word, 7, "_NC");
				else
					setTkCurrent(toNoAccentLowerCase(word), 17, "_SU");
			}
		}
	}
	
	private void analyzeExpressionTkCurrent() {
		LemmatizerToken expressionAux, initialShift, finalShift;
		String line;
		int x, i, shiftedTokens;
		
		line = tkCurrent.getWord();
		expressionAux = tkCurrent.getNext();
		for(x = 0; x < 3; x++) {
			line += ("=" + expressionAux.getWord());
			expressionAux = expressionAux.getNext();
		}
		line = toNoAccentLowerCase(line);
		
		i = searchExpressions(line, 0, expressionsRoot);
		if(i != LemmatizerConstants.undefinedTag) {
			shiftedTokens = 0;
			for(x = 0; x < greaterColumn; x++)
				if(line.charAt(x) == '=')
					shiftedTokens++;
			
			initialShift = tkCurrent.getNext();
			if(shiftedTokens == 1) {
				tkCurrent.setWord(
						tkCurrent.getWord() + "=" + tkCurrent.getNext().getWord());
				finalShift = initialShift;
			}
			else if(shiftedTokens == 2) {
				tkCurrent.setWord(
						tkCurrent.getWord() + "=" + tkCurrent.getNext().getWord()
						+ "=" + tkCurrent.getNext().getNext().getWord());
				finalShift = initialShift.getNext();
			}
			else {
				tkCurrent.setWord(
						tkCurrent.getWord() + "=" + tkCurrent.getNext().getWord()
						+ "=" + tkCurrent.getNext().getNext().getWord()
						+ "=" + tkCurrent.getNext().getNext().getNext().getWord());
				finalShift = initialShift.getNext().getNext();
			}
			
			tkCurrent.setSWordS(" " + tkCurrent.getWord() + " ");
			tkCurrent.setLemma(toNoAccentLowerCase(tkCurrent.getWord()));
			tkCurrent.setTag(i);
			
			String tag;
			if(i >= 0) {
				tkCurrent.getSfxTags()[i] = 1.00;
				tag = LemmatizerTags.getTag(i);
			}
			else
				tag = "";
			tkCurrent.setResult(
					tkCurrent.getWord() + " " + tkCurrent.getLemma() + " " + tag);
			
			tkCurrent.setNext(finalShift.getNext());
			finalShift.getNext().setPrev(tkCurrent);
			tkReading.getNext().setNext(initialShift);
			initialShift.setPrev(tkReading.getNext());
			finalShift.setNext(tkReady);
			tkReady.setPrev(finalShift);
			
			for(int j = 0; j < shiftedTokens; j++) {
				tkReading = tkReading.getNext();
				if(combined)
					combined = false;
				else {
					getNextToken(tkReading);
					combine();
				}
			}
		}
	}
	
	private void setResult(LemmatizerToken t) {
		if(t.getTag() >= 0) {
			t.setResult(
					t.getWord() + " " + t.getLemma() + " " + LemmatizerTags.getTag(t.getTag()));
		}
		
		else
			t.setResult(t.getWord() + " " + t.getLemma() + " _UN");
	}
	
	private void analyzeAccentsTkCurrent() {
		searchAccents(tkCurrent.getWord(), 0, tkCurrent, accentsRoot);
		
		if(tkCurrent.getTag() != LemmatizerConstants.undefinedTag)
			setResult(tkCurrent);
	}
	
	private void analyzeLongestSuffix(String word) {
		boolean found;
		int level, tot, cut;
		String initialPortion;
		SuffixesChar aux, equalAux;
		
		tot = word.length() - 1;
		cut = tot - greaterColumn;
		
		if(longestSuffix.getExactTag() != LemmatizerConstants.undefinedTag &&
				cut == 0) {
			sfxTag[longestSuffix.getExactTag()] = 1.0;
			if(longestSuffix.getLemma()[longestSuffix.getExactTag()] == null)
				sfxLemma[longestSuffix.getExactTag()] = "";
			else {
				sfxLemma[longestSuffix.getExactTag()] =
					longestSuffix.getLemma()[longestSuffix.getExactTag()];
			}
		}
		else {
			initialPortion = toNoAccentLowerCase(word.substring(0, cut));
			
			for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
				if(longestSuffix.getTags()[i] > 0.0) {
					sfxTag[i] = longestSuffix.getTags()[i];
					
					if(longestSuffix.getLemma()[i] != null)
						sfxLemma[i] = initialPortion + longestSuffix.getLemma()[i];
					
					else {
						found = false;
						sfxTag[i] = (longestSuffix.getTags()[i])/2;
						equalAux = longestSuffix.getEqual();
						level = 0;
						while(equalAux != null) {
							level++;
							if(equalAux.getTags()[i] > 0.0 &&
									equalAux.getLemma()[i] != null) {
								found = true;
								break;
							}
							aux = equalAux.getLess();
							while(aux != null) {
								if(aux.getTags()[i] > 0.0 &&
										aux.getLemma()[i] != null) {
									found = true;
									equalAux = aux;
									break;
								}
								aux = aux.getLess();
							}
							if(found)
								break;
							aux = equalAux.getGreater();
							while(aux != null) {
								if(aux.getTags()[i] > 0.0 &&
										aux.getLemma()[i] != null) {
									found = true;
									equalAux = aux;
									break;
								}
								aux = aux.getGreater();
							}
							if(found)
								break;
							equalAux = equalAux.getEqual();
						}
						
						if(found)
							sfxLemma[i] =
							initialPortion + equalAux.getLemma()[i].substring(level);
					}
				}
			}
		}
	}
	
	private void analyzeSuffixTkCurrent() {
		String drow = "";
		double greatestFreq = 0.98;
		
		for(int i = tkCurrent.getWord().length() - 1; i >= 0; i--)
			drow += tkCurrent.getWord().charAt(i);
		drow = toNoAccentLowerCase(drow);
		
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			sfxLemma[i] = "";
			sfxTag[i] = 0.0;
		}
		
		longestSuffix = null;
		
		searchSuffixes(drow, 0, suffixesRoot);
		if(longestSuffix != null)
			analyzeLongestSuffix(tkCurrent.getWord());
		
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			if(!sfxLemma[i].equals(""))
				tkCurrent.getSfxLemmas()[i] = sfxLemma[i];
			
			if(tkCurrent.getSfxTags()[i] < sfxTag[i])
				tkCurrent.getSfxTags()[i] = sfxTag[i];
		}
	
		for(int i = 0; i < LemmatizerConstants.numberOfTags; i++) {
			if(tkCurrent.getSfxTags()[i] > greatestFreq) {
				tkCurrent.setTag(i);
				if(!tkCurrent.getSfxLemmas()[i].equals(""))
					tkCurrent.setLemma(tkCurrent.getSfxLemmas()[i]);
				greatestFreq = tkCurrent.getSfxTags()[i];
			}
		}
		
		if(tkCurrent.getLemma().equals(""))
			tkCurrent.setLemma(toNoAccentLowerCase(tkCurrent.getWord()));
	}
	
	private void analyzeTkNeig(LemmatizerToken t) {
		double greatestProb, prob;
		int x, i;
		
		if(t.getPrev().getTag() != LemmatizerConstants.undefinedTag) {
			if(t.getPrev().getTag() >= 0) {
				for(x = 0; x < LemmatizerConstants.numberOfTags; x++) {
					t.getPrevs()[x] =
					LemmatizerConstants.nextNeighbour[t.getPrev().getTag()][x];
				}
			}
			
			else 
				t.setPrevs(LemmatizerConstants.negNextNeighbour);
		}
		
		if(t.getNext().getTag() != LemmatizerConstants.undefinedTag)
			if(t.getNext().getTag() >= 0) {
				for(x = 0; x < LemmatizerConstants.numberOfTags; x++) {
					t.getNexts()[x] =
					LemmatizerConstants.prevNeighbour[t.getNext().getTag()][x];
				}
			}
		
			else
				t.setNexts(LemmatizerConstants.negPrevNeighbour);
		
		i = 17;
		greatestProb = (t.getPrevs()[i] + 3*t.getSfxTags()[i] + t.getNexts()[i])/5;
		for(x = 0; x < LemmatizerConstants.numberOfTags; x++) {
			prob = (t.getPrevs()[x] + 3*t.getSfxTags()[x] + t.getNexts()[x])/5;
			if(prob > greatestProb) {
				if(x != 0 && x != 1 && x != 16) {
					i = x;
					greatestProb = prob;
				}
				else if(x == 0) {
					if(t.getWord().equals("a")) {
						i = x;
						greatestProb = prob;
					}
				}
				else if(x == 16) {
					List<String> termos = Arrays.asList("a", "entre", "para", "por",
							"pos", "sobre");
					
					for(String termo : termos) {
						if(t.getWord().equals(termo)) {
							i = x;
							greatestProb = prob;
						}
					}
				}
			}
		}
		t.setTag(i);
		
		if((i >= 3 && i <= 8) || i == 13 || i == 16)
			t.setLemma(toNoAccentLowerCase(
					t.getSWordS().substring(1, t.getSWordS().length() - 1)));
		
		else if(!t.getSfxLemmas()[i].equals(""))
			t.setLemma(t.getSfxLemmas()[i]);
	}
	
	private void analyzeTkReady() {
		int i, u;
		
		if(tkReady.getNext().getSfxTags()[6] == -9999) {
			String word = tkReady.getWord();
			if(word.length() > 0)
				tkReady.setWord(word.substring(0, word.length() - 1));
		}
		
		i = tkReady.getPrev().getTag();
		u = tkReady.getNext().getTag();
		
		String ePalavraE = tkReady.getSWordS();
		if(ePalavraE.equals(" a ")) {
			if((i == 17 && u == 17) || u == 18 || u == 19 || u == 7)
				tkReady.setResult(tkReady.getWord() + " a _PR");
			
			else if(i == 14)
				tkReady.setResult(tkReady.getWord() + " o _AD");
		}
		
		else if(tkReady.getTag() == 19 && tkReady.getLemma().equals("dever")) {
			if(u == 18 || u == 19) {
				tkReady.setResult(
						tkReady.getWord() + " " + tkReady.getLemma() + " _VA");
			}
			
			else {
				tkReady.setResult(
						tkReady.getWord() + " " + tkReady.getLemma() + " _VB");
			}
		}
		
		else if(tkReady.getTag() == 19 && tkReady.getLemma().equals("poder")) {
			if(u == 18 || u == 19) {
				tkReady.setResult(
						tkReady.getWord() + " " + tkReady.getLemma() + " _VA");
			}
			else {
				tkReady.setResult(
						tkReady.getWord() + " " + tkReady.getLemma() + " _VB");
			}
		}
		
		else if(ePalavraE.equals(" são ")) {
			if(i == 0  || i == 1 || i == 16 || i == 19)
				tkReady.setResult("são sao _SU");
			
			else
				tkReady.setResult("são ser _VB");
		}
		
		else if(ePalavraE.equals(" pela ") || ePalavraE.equals(" pelas ") ||
				ePalavraE.equals(" pelo ") || ePalavraE.equals(" pelos ")) {
			if (ePalavraE.equals("pelo")) {
				if(i == 0 || i == 1 || i == 16)
					tkReady.setResult(tkReady.getWord() + " pelo _SU");
				else {
					if(u == 0 || u == 1 || u == 16 || i == 15)
						tkReady.setResult(tkReady.getWord() + " pelar _VB");
					else
						tkReady.setResult("por por _PR\no o _AD");
				}
			}
			
			else if(ePalavraE.equals(" pelos ")) {
				if(i == 0  || i == 1 || i == 16)
					tkReady.setResult(tkReady.getWord() + " pelo _SU");	
				else
					tkReady.setResult("por por _PR\nos o _AD");
			}
			
			else if(ePalavraE.equals(" pela ") || ePalavraE.equals(" pelas ")) {
				if(i != 15 || i == 19 || (u != 0 && u != 1))
					tkReady.setResult(
							"por por _PR\n" + ePalavraE.substring(4) + " o _AD");
				else
					tkReady.setResult(tkReady.getWord() + " pelar _VB");
			}
		}
		
		else if(ePalavraE.equals(" foi ")) {
			if (u == 19 || u == 16)
				tkReady.setResult(tkReady.getWord() + " ir _VB");
			
			else
				tkReady.setResult(tkReady.getWord() + " ser _VA");
		}
		
		else if((i == 0 || i == 1 || i == 11 || i == 12) && tkReady.getTag() == 9) {
			if (!tkReady.getSfxLemmas()[17].equals(""))
				tkReady.setLemma(tkReady.getSfxLemmas()[17]);
			
			else {
				tkReady.setLemma(
						toNoAccentLowerCase(
								tkReady.getSWordS().substring(1, tkReady.getSWordS().length() - 1)));
			}
			
			tkReady.setResult(tkReady.getWord() + " " + tkReady.getLemma() + " _SU");
		}
		
		else
			setResult(tkReady);
		
		
		if(tkReady.getResult().equals(""))
			setResult(tkReady);
	}
	
	public static String toNoAccentLowerCase(String word) {
		String newWord = "";
		for(int i = 0; i < word.length(); i++) {
			switch(word.charAt(i)) {
				case 'Á': newWord += 'a'; break;
				case 'À': newWord += 'a'; break;
				case 'Â': newWord += 'a'; break;
				case 'Ã': newWord += 'a'; break;
				case 'É': newWord += 'e'; break;
				case 'Ê': newWord += 'e'; break;
				case 'Í': newWord += 'i'; break;
				case 'Ó': newWord += 'o'; break;
				case 'Ô': newWord += 'o'; break;
				case 'Õ': newWord += 'o'; break;
				case 'Ú': newWord += 'u'; break;
				case 'Ç': newWord += 'c'; break;
				case 'Ñ': newWord += 'n'; break;
				case 'È': newWord += 'e'; break;
				case 'Ì': newWord += 'i'; break;
				case 'Ò': newWord += 'o'; break;
				case 'Ù': newWord += 'u'; break;
				case 'Û': newWord += 'u'; break;
				case 'Î': newWord += 'i'; break;
				case 'Ä': newWord += 'a'; break;
				case 'Ë': newWord += 'e'; break;
				case 'Ï': newWord += 'i'; break;
				case 'Ö': newWord += 'o'; break;
				case 'Ü': newWord += 'u'; break;
				case 'Ý': newWord += 'y'; break;
				case 'á': newWord += 'a'; break;
				case 'à': newWord += 'a'; break;
				case 'â': newWord += 'a'; break;
				case 'ã': newWord += 'a'; break;
				case 'é': newWord += 'e'; break;
				case 'ê': newWord += 'e'; break;
				case 'í': newWord += 'i'; break;
				case 'ï': newWord += 'i'; break;
				case 'ó': newWord += 'o'; break;
				case 'ô': newWord += 'o'; break;
				case 'õ': newWord += 'o'; break;
				case 'ú': newWord += 'u'; break;
				case 'ç': newWord += 'c'; break;
				case 'ñ': newWord += 'n'; break;
				case 'è': newWord += 'e'; break;
				case 'ì': newWord += 'i'; break;
				case 'ò': newWord += 'o'; break;
				case 'î': newWord += 'i'; break;
				case 'ù': newWord += 'u'; break;
				case 'û': newWord += 'u'; break;
				case 'ä': newWord += 'a'; break;
				case 'ë': newWord += 'e'; break;
				case 'ö': newWord += 'o'; break;
				case 'ü': newWord += 'u'; break;
				case 'ý': newWord += 'y'; break;
				default: newWord += Character.toLowerCase(word.charAt(i));
					break;
			}
		}
		
		return newWord;
	}
}
