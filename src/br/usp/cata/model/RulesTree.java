package br.usp.cata.model;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.arabidopsis.ahocorasick.AhoCorasick;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class RulesTree {
	
	private String rulesFile;
	private AhoCorasick rulesTree;
	private String rulesTag;
	private String[] ruleProperties;
	private String[] ruleArguments;
	
	public RulesTree() {
		rulesFile = "/br/usp/cata/resources/rules.xml";
		rulesTree = new AhoCorasick();
		rulesTag = Rule.rulesTag;
		ruleProperties = Rule.ruleProperties;
		ruleArguments = new String[ruleProperties.length];
		buildTree();
	}
	
	private Iterator<Rule> parseRulesFile() {
		List<Rule> rules = new ArrayList<Rule>();
		
		try {
			InputStream is = RulesTree.class.getResourceAsStream(rulesFile);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);			
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName(rulesTag);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element ruleElement = (Element) nodeList.item(i);
				int j = 0;
				for(String property : ruleProperties) {
					NodeList propertyNodes =
						ruleElement.getElementsByTagName(property);
					ruleArguments[j++] =
						propertyNodes.item(0).getFirstChild().getNodeValue();
				}
				rules.add(new Rule(ruleArguments));
			}
			
			is.close();
		} catch (Exception e) { //FIXME Exception
			//TODO
			e.printStackTrace();
		}
		
		return rules.iterator();
	}
	
	private void buildTree() {
		Iterator<Rule> rules = parseRulesFile();
		
		while(rules.hasNext()) {
			Rule rule = rules.next();
			rulesTree.add((" " + rule.getPattern() + " ").toLowerCase().getBytes(), rule);
		}
		
		rulesTree.prepare();
	}
	
	public Iterator<?> search(byte[] text) {
		return rulesTree.search(text);
	}

}
