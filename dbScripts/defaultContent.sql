-- Usuario 'Sistema CATA'

insert into User (userID, active, email, name) values
	(1, True, 'sistema.cata@gmail.com', 'Sistema CATA');


-- Referências Bibliográficas

insert into Source (sourceID, title, type, url, userID) values
	(1, 'Site do Prof. Kon com dicas', 3, 'http://www.ime.usp.br/~kon/ResearchStudents/traducao.html', 1);


-- Regras

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (1, True, 'advice', 'conselho, adendo', ' advice ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (1, 1, 1);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (1, 0, True, 1, 1, 1);

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (2, True, 'aspect', 'aspecto', ' aspect ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (1, 2, 2);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (2, 0, True, 1, 1, 1);

insert into PatternSuggestionPair(patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (3, True, 'assumir', 'supor, considerar', ' assumir ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (2, 3, 3);
insert into Rule (ruleID, category, defaultRule, explanation, type, sourceID, userID) values
(3, 0, True, '''Assumir'' com o sentido de ''admitir hipótese''.', 2, 1, 1);

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (4, True, 'assumption', 'pressuposto, suposição, assunção', ' assumption ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (3, 4, 4);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (4, 0, True, 1, 1, 1);

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (5, True, 'audio', 'áudio', ' audio ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (2, 5, 5);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (5, 0, True, 1, 1, 1);

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (6, True, 'binding', 'enlace, associação', ' binding ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (4, 6, 6);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (6, 0, True, 1, 1, 1);

insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (7, True, 'cloud computing', 'computação na nuvem, computação em nuvem', ' cloud computing ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (3, 7, 7);
insert into Rule (ruleID, category, defaultRule, type, sourceID, userID) values (7, 0, True, 1, 1, 1);