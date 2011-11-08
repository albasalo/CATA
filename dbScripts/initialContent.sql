-- Usuario 'Sistema CATA'

insert into User (userID, active, email, name) values
	(1, True, 'sistema.cata@gmail.com', 'CATA');


-- Referências Bibliográficas

insert into Source (sourceID, title, type, url, userID) values
	(1, 'Site do Professor Kon com dicas', 3, 'http://www.ime.usp.br/~kon/ResearchStudents/traducao.html', 1);

insert into Source (sourceID, title, type, authors, publisher, date, userID) values
	(2, 'Manual da Redação', 2, 'Folha de São Paulo', 'Publifolha', '2010', 1);


-- Regras

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (1, 0, 1, True, '''Assumir'' com o sentido de ''admitir por hipótese'', tradução do inglês ''to assume'' (e.g. ''Assume x is a multi-dimensional array.'').', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (1, True, 'assumir', 'considerar, supor', ' assumir ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (1, 1, 1);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (2, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (2, True, 'audio', 'áudio', ' audio ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (1, 2, 2);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (3, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (3, True, 'Autonomic Computing', 'Computação Autônoma', ' autonomic computing ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (2, 3, 3);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (4, False, 'Computação Autonômica', 'Computação Autônoma', ' computação autonômica ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (3, 4, 3);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (4, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (5, True, 'binding', 'enlace, associação', ' binding ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (2, 5, 4);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (5, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (6, True, 'cloud computing', 'computação na nuvem, computação em nuvem', ' cloud computing ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (4, 6, 5);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (6, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (7, True, 'cluster', 'aglomerado, agrupamento', ' cluster ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (3, 7, 6);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (7, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (8, True, 'container', 'contêiner', ' container ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (4, 8, 7);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (8, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (9, True, 'core', 'núcleo', ' core ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (5, 9, 8);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (9, 0, 0, True, 'Tradução incorreta de ''correctness''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (10, True, 'corretude', 'correção', ' corretude ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (6, 10, 9);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (10, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (11, True, 'data mining', 'mineração de dados', ' data mining ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (5, 11, 10);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (11, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (12, True, 'deadlock', 'impasse, travamento', ' deadlock ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (7, 12, 11);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (12, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (13, True, 'debugger', 'depurador', ' debugger ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (8, 13, 12);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (14, False, 'debugador', 'depurador', ' debugador ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (9, 14, 12);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (13, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (15, True, 'debugging', 'depuração', ' debugging ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (10, 15, 13);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (16, False, 'debugação', 'depuração', ' debugacao ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (11, 16, 13);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (14, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (17, True, 'default', 'padrão, valor padrão', ' default ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (6, 17, 14);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (15, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (18, True, 'delay', 'atraso', ' delay ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (12, 18, 15);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (16, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (19, True, 'deployment', 'implantação', ' deployment ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (13, 19, 16);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (17, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (20, True, 'dynamic binding', 'enlace dinâmico, associação', ' dynamic binding ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (14, 20, 17);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (18, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (21, False, 'embedded', 'embutido', ' embedded ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (7, 21, 18);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (22, True, 'embebido', 'embutido', ' embebido ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (15, 22, 18);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (19, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (23, True, 'eXtreme Programming', 'Programação eXtrema', ' extreme programming ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (8, 23, 19);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (20, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (24, True, 'failure', 'defeito, avaria, (falha pode ser usada se o contexto permitir)', ' failure ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (16, 24, 20);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (21, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (25, True, 'fault', 'falha, falta', ' fault ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (17, 25, 21);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (22, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (26, True, 'framework', 'arcabouço', ' framework ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (18, 26, 22);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (23, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (27, True, 'grid', 'grade', ' grid ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (19, 27, 23);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (24, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (28, True, 'hack', 'gambiarra', ' hack ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (20, 28, 24);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (25, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (29, True, 'host', 'máquina, hospedeiro, computador', ' host ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (21, 29, 25);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (26, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (30, True, 'job', 'tarefa', ' job ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (22, 30, 26);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (27, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (31, True, 'kernel', 'núcleo', ' kernel ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (23, 31, 27);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (28, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (32, True, 'layout', 'leiaute, formato', ' layout ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (24, 32, 28);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (29, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (33, True, 'lock', 'bloqueio', ' lock ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (25, 33, 29);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (30, 0, 0, True, 'Tradução incorreta do inglês ''mandatory''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (34, True, 'mandatório', 'obrigatório', ' mandatorio ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (26, 34, 30);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (31, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (35, True, 'middlewares', 'sistemas de middleware', ' middlewares ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (9, 35, 31);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (32, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (36, True, 'nested', 'aninhado, encaixado', ' nested ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (10, 36, 32);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (33, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (37, True, 'offset', 'deslocamento', ' offset ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (27, 37, 33);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (34, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (38, True, 'overhead', 'carga extra, custo adicional, sobrecarga', ' overhead ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (28, 38, 34);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (35, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (39, True, 'parser', 'analisador, analisador sintático', ' parser ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (29, 39, 35);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (36, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (40, True, 'performance', 'desempenho', ' performance ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (30, 40, 36);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (37, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (41, True, 'Pervasive Computing', 'Computação Ubíqua', ' pervasive computing ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (11, 41, 37);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (42, False, 'Computação Pervasiva', 'Computação Ubíqua', ' computação pervasiva ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (12, 42, 37);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (38, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (43, True, 'prefecthing', 'transferência antecipada, busca antecipada', ' prefetching ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (31, 43, 38);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (39, 0, 0, True, 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (44, True, 'procedural', 'procedimental', ' procedural ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (32, 44, 39);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (40, 0, 0, True, 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (45, True, 'randômico', 'aleatório', ' randomico ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (33, 45, 40);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (41, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (46, True, 'request', 'pedido, requisição', ' request ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (34, 46, 41);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (42, 0, 1, True, 'Tradução incorreta do inglês ''requirement''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (47, True, 'requerimento', 'requisitos', ' requerimento ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (35, 47, 42);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (43, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (48, True, 'router', 'roteador', ' router ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (36, 48, 43);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (44, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (49, True, 'softwares', 'sistemas de software', ' softwares ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (13, 49, 44);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (45, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (50, True, 'stakeholders', 'interessados, partes interessadas', ' stakeholders ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (14, 50, 45);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (46, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (51, True, 'switch', 'comutador [de pacotes]', ' switch ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (37, 51, 46);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (47, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (52, True, 'testbed', 'ambiente de/para testes', ' testbed ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (38, 52, 47);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (48, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (53, True, 'test-driven development', 'programação dirigida por testes', ' test-driven development ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (15, 53, 48);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (49, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (54, True, 'throughput', 'vazão', ' throughput ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (39, 54, 49);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (50, 0, 0, True, 'Tradução incorreta do inglês ''to check''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (55, True, 'checar', 'verificar', ' checar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (40, 55, 50);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (51, 0, 0, True, 'Tradução incorreta do inglês ''to customize''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (56, True, 'customizar', 'configurar, particularizar, personalizar', ' customizar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (41, 56, 51);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID)
	values (52, 0, 0, True, 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (57, True, 'debugar', 'depurar', ' debugar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (42, 57, 52);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (53, 0, 0, True, 'Tradução incorreta do inglês ''to elicit''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (58, True, 'elicitar', 'capturar, coletar, descobrir, eliciar (fig.), extrair, obter, prospectar', ' elicitar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (43, 58, 53);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (54, 0, 1, True, 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (59, True, 'retornar', 'devolver', ' retornar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (44, 59, 54);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (55, 0, 1, True, 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (60, True, 'rodar', 'executar', ' rodar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (45, 60, 55);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (56, 0, 1, True, 'Tradução incorreta do inglês ''to support''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (61, True, 'suportar', 'comportar, contemplar, dar suporte, disponibilizar, implementar, incluir, oferecer, prover', ' suportar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (46, 61, 56);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (57, 0, 0, True, 'Tradução incorreta do inglês ''unit tests''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (62, True, 'teste unitário', 'teste de unidade', ' teste unitario ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (47, 62, 57);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (58, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (63, True, 'assembler', 'montador', ' assembler ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (48, 63, 58);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (59, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (64, True, 'assembly language', 'linguagem de montagem', ' assembly language ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (49, 64, 59);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (60, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (65, True, 'browser', 'navegador', ' browser ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (50, 65, 60);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (61, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (66, True, 'bug', 'erro', ' bug ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (51, 66, 61);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (62, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (67, True, 'checkpoint', 'ponto de salvaguarda', ' checkpoint ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (52, 67, 62);

insert into Rule (ruleID, language, category, defaultRule, explanation, type, sourceID, userID)
	values (63, 0, 0, True, 'Tradução incorreta do inglês ''to commit''.', 2, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (68, True, 'comitar', 'consolidar, efetivar', ' comitar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (53, 68, 63);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (69, False, 'commitar', 'consolidar, efetivar', ' commitar ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (54, 69, 63);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (64, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (70, True, 'commit', 'consolidação', ' commit ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (55, 70, 64);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (65, 0, 1, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (71, True, 'design', 'desenho, projeto', ' design ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (56, 71, 65);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (66, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (72, True, 'hash function', 'função de espalhamento, função de resumo criptográfico ou, simplesmente, resumo criptográfico', ' hash function ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (57, 72, 66);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (67, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (73, True, 'hash table', 'tabela de dispersão, tabela de espalhamento', ' hash table ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (58, 73, 67);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (68, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (74, True, 'I/O', 'E/S', ' i/o ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (16, 74, 68);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (69, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (75, True, 'linker', 'ligador', ' linker ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (59, 75, 69);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (70, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (76, True, 'log', 'registro', ' log ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (60, 76, 70);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (71, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (77, True, 'patch', 'componente de atualização, remendo', ' patch ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (61, 77, 71);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (72, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (78, True, 'peer', 'par', ' peer ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (62, 78, 72);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (73, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (79, True, 'pipe', 'duto', ' pipe ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (63, 79, 73);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (74, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (80, True, 'polling', 'consulta periódica, varredura', ' polling ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (64, 80, 74);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (75, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (81, True, 'proxy', 'procurador, representante', ' proxy ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (65, 81, 75);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (76, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (82, True, 'round-robin', 'rodízio', ' round-robin ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (17, 82, 76);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (77, 0, 1, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (83, True, 'site', 'sítio', ' site ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (66, 83, 77);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (78, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (84, True, 'starvation', 'inanição', ' starvation ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (67, 84, 78);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (79, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (85, True, 'string', 'cadeia de caracteres', ' string ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (68, 85, 79);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (80, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (86, True, 'template', 'gabarito, modelo, molde', ' template ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (69, 86, 80);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (81, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (87, True, 'thread', 'fluxo de execução, linha de execução, processo leve', ' thread ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (70, 87, 81);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (82, 0, 0, True, 1, 1, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (88, True, 'trade-off', 'dicotomia (é melhor explicar a relação por extenso)', ' trade-off ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (71, 88, 82);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (83, 0, 0, True, 3, 2, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (89, True, 'a grosso modo', 'grosso modo', ' a grosso modo ');
insert into ExactMatching (exactMatchingID, pair_patternSuggestionPairID, rule_ruleID) values (18, 89, 83);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (84, 0, 0, True, 1, 2, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (90, True, 'paper', 'documento, texto acadêmico', ' paper ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (72, 90, 84);

insert into Rule (ruleID, language, category, defaultRule, type, sourceID, userID) values (85, 0, 0, True, 1, 2, 1);
insert into PatternSuggestionPair (patternSuggestionPairID, defaultPair, pattern, suggestions, tokenizedPatternBytes)
	values (91, True, 'sketch', 'esquete', ' sketch ');
insert into Lemma (lemmaID, pair_patternSuggestionPairID, rule_ruleID) values (73, 91, 85);