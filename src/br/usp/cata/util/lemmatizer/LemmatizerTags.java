package br.usp.cata.util.lemmatizer;

import java.util.Arrays;
import java.util.List;


public enum LemmatizerTags {
	AD, AI, AJ, AV, CC, CS, IN, NC, NO, AP,
	PS, PD, PI, PL, PN, PP, PR, SU, VA, VB, VG;
	
	private static List<String> tags =
		Arrays.asList("_AD","_AI","_AJ","_AV","_CC","_CS","_IN","_NC","_NO","_AP",
				"_PS", "_PD","_PI","_PL","_PN","_PP","_PR","_SU","_VA","_VB","_VG");
			
	public static String getTag(int i) {
		if(i >= 0 && i < tags.size())
			return tags.get(i);
		else
			return "";
	}

}
