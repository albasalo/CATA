package br.usp.correcao.tests.model;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.usp.correcao.component.Tree;
import br.usp.correcao.model.AnalyzedLine;
import br.usp.correcao.model.AnalyzedSegment;
import br.usp.correcao.tests.CorrecaoTestCase;

public class TestAnalyzedLine extends CorrecaoTestCase {
	
	AnalyzedLine analyzedLine;
	ArrayList<AnalyzedSegment> line;
	Tree tree;
	
	public TestAnalyzedLine(String name) {
		super(name);
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tree = new Tree();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testWordCluster1() {
		analyzedLine = new AnalyzedLine("Cluster");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() != null);
		assertEquals("cluster", line.get(0).getBrokenRule().getPattern());
		assertEquals("Cluster", line.get(0).getText());
	}
	
	@Test
	public void testWordCluster2() {
		analyzedLine = new AnalyzedLine("cluster");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() != null);
		assertEquals("cluster", line.get(0).getBrokenRule().getPattern());
		assertEquals("cluster", line.get(0).getText());
	}
	
	@Test
	public void testWordCluster3() {
		analyzedLine = new AnalyzedLine("CLUSTER");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() != null);
		assertEquals("cluster", line.get(0).getBrokenRule().getPattern());
		assertEquals("CLUSTER", line.get(0).getText());
	}
	
	@Test
	public void testTextCluster1() {
		analyzedLine = new AnalyzedLine(" cluster ");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertEquals(" ", line.get(0).getText());
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("cluster", line.get(1).getText());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertEquals(" ", line.get(2).getText());
	}
	
	@Test
	public void testTextCluster2() {
		analyzedLine = new AnalyzedLine("Test cluster.");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertEquals("Test ", line.get(0).getText());
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("cluster", line.get(1).getText());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertEquals(".", line.get(2).getText());
	}
	
	@Test
	public void testTextClusterCore1() {
		analyzedLine = new AnalyzedLine("Test core test cluster.");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertEquals("Test ", line.get(0).getText());
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("core", line.get(1).getText());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertEquals(" test ", line.get(2).getText());
		assertTrue(line.get(3).getBrokenRule() != null);
		assertEquals("cluster", line.get(3).getText());
		assertTrue(line.get(4).getBrokenRule() == null);
		assertEquals(".", line.get(4).getText());
	}
	
	@Test
	public void testTextClusterCore2() {
		analyzedLine = new AnalyzedLine("Test Core test CLUSTER.");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertEquals("Test ", line.get(0).getText());
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("Core", line.get(1).getText());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertEquals(" test ", line.get(2).getText());
		assertTrue(line.get(3).getBrokenRule() != null);
		assertEquals("CLUSTER", line.get(3).getText());
		assertTrue(line.get(4).getBrokenRule() == null);
		assertEquals(".", line.get(4).getText());
	}
	
	@Test
	public void testTextClusterCore3() {
		analyzedLine = new AnalyzedLine("Test Core test CLUSTER.");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("core", line.get(1).getBrokenRule().getPattern());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertTrue(line.get(3).getBrokenRule() != null);
		assertEquals("cluster", line.get(3).getBrokenRule().getPattern());
		assertTrue(line.get(4).getBrokenRule() == null);
	}
	
	@Test
	public void testLongLine() {
		analyzedLine = new AnalyzedLine("Test: Cluster, cluster, core. Test, Core core.");
		line = analyzedLine.getAnalyzedLine();
		assertTrue(!line.isEmpty());
		assertTrue(line.get(0).getBrokenRule() == null);
		assertEquals("Test: ", line.get(0).getText());
		assertTrue(line.get(1).getBrokenRule() != null);
		assertEquals("Cluster", line.get(1).getText());
		assertTrue(line.get(2).getBrokenRule() == null);
		assertEquals(", ", line.get(2).getText());
		assertTrue(line.get(3).getBrokenRule() != null);
		assertEquals("cluster", line.get(3).getText());
		assertTrue(line.get(4).getBrokenRule() == null);
		assertEquals(", ", line.get(4).getText());
		assertTrue(line.get(5).getBrokenRule() != null);
		assertEquals("core", line.get(5).getText());
		assertTrue(line.get(6).getBrokenRule() == null);
		assertEquals(". Test, ", line.get(6).getText());
		assertTrue(line.get(7).getBrokenRule() != null);
		assertEquals("Core", line.get(7).getText());
		assertTrue(line.get(8).getBrokenRule() == null);
		assertEquals(" ", line.get(8).getText());
		assertTrue(line.get(9).getBrokenRule() != null);
		assertEquals("core", line.get(9).getText());
		assertTrue(line.get(10).getBrokenRule() == null);
		assertEquals(".", line.get(10).getText());
	}

}
