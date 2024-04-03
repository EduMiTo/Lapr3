package lapr.project.model.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class MatrixGraphTest {


    final ArrayList<String> co = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "C", "D", "E", "E"));
    final ArrayList<String> cd = new ArrayList<>(Arrays.asList("B", "C", "D", "D", "E", "A", "D", "E"));
    final ArrayList<Integer> cw = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    final ArrayList<String> ov = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
    MatrixGraph<String, Integer> instance = null;

    @BeforeEach
    public void initializeGraph() {
        instance = new MatrixGraph<>(true);
    }
    

    /**
     * Test of isDirected method, of class Graph.
     */
    @Test
    public void testIsDirected() {
        System.out.println("Test isDirected");

        assertTrue(instance.isDirected(), "result should be true");
        instance = new MatrixGraph<>(false);
        assertFalse(instance.isDirected(), "result should be false");
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");

        assertEquals(0, instance.numVertices(), "result should be zero");
        instance.addVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.addVertex("B");
        assertEquals(2, instance.numVertices(), "result should be two");

    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        assertEquals(0, instance.vertices().size(), "vertices should be empty");

        instance.addVertex("A");
        instance.addVertex("B");

        Collection<String> cs = instance.vertices();
        assertEquals(2, cs.size(), "Must have 2 vertices");
        cs.removeAll(Arrays.asList("A", "B"));
        assertEquals(0, cs.size(), "Vertices should be A and B");



        cs = instance.vertices();
        assertEquals(2, cs.size(), "Must have 1 vertice1");
        cs.removeAll(Arrays.asList("B"));
        assertEquals(1, cs.size(), "Vertice should be B");


        cs = instance.vertices();
        assertEquals(2, cs.size(), "Must not have any vertice");
    }

    /**
     * Test of validVertex method, of class Graph.
     */
    @Test
    public void testValidVertex() {
        System.out.println("Test validVertex");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (String v : co)
            assertTrue(instance.validVertex(v), "vertices should exist");


        assertFalse(instance.validVertex("Z"), "vertice should not exist");
    }

    /**
     * Test of key method, of class Graph.
     */
    @Test
    public void testKey() {
        System.out.println("Test key");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < ov.size(); i++)
            assertEquals(i, instance.key(ov.get(i)), "vertices should exist");

        assertEquals(-1, instance.key("Z"), "vertice should not exist");
    }

    /**
     * Test of testAdjVertices method, of class Graph.
     */
    @Test
    public void testAdjVertices() {
        System.out.println("Test adjVertices");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<String> cs = instance.adjVertices("A");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("B") || s.equals("C"));
        assertEquals(0, cs.size(), "Adjacents should be B and C");

        cs = instance.adjVertices("B");
        assertEquals(1, cs.size(), "Num adjacents should be 1");
        cs.removeIf(s -> s.equals("D"));
        assertEquals(0, cs.size(), "Adjacents should be S");

        cs = instance.adjVertices("E");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("D") || s.equals("E"));
        assertEquals(0, cs.size(), "Adjacents should be D and E");
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");

        assertEquals(0, instance.numEdges(), "result should be zero");

        instance.addEdge("A", "B", 1);
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.addEdge("A", "C", 2);
        assertEquals(2, instance.numEdges(), "result should be two");


    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        assertEquals(0, instance.edges().size(), "edges should be empty");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 8 edges");
        for (int i = 0; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");


        ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 7 edges");
        for (int i = 1; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(1, ced.size(), "Edges should be as inserted");


        ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 6 edges");
        for (int i = 1; i < co.size() - 1; i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(2, ced.size(), "Edges should be as inserted");



        assertEquals(8, instance.edges().size(), "edges should be empty");
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(co.get(i), cd.get(i)).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge("A", "E"), "edge should be null");
        assertNull(instance.edge("D", "B"), "edge should be null");


    }

    /**
     * Test of getEdge by key method, of class Graph.
     */
    @Test
    public void testGetEdgeByKey() {
        System.out.println("Test getEdge");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(instance.key(co.get(i)), instance.key(cd.get(i))).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge(instance.key("A"), instance.key("E")), "edge should be null");
        assertNull(instance.edge(instance.key("D"), instance.key("B")), "edge should be null");


    }


    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.outDegree("G"), "degree should be -1");
        assertEquals(2, instance.outDegree("A"), "degree should be 2");
        assertEquals(1, instance.outDegree("B"), "degree should be 1");
        assertEquals(2, instance.outDegree("E"), "degree should be 2");
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.inDegree("G"), "degree should be -1");
        assertEquals(1, instance.inDegree("A"), "degree should be 1");
        assertEquals(3, instance.inDegree("D"), "degree should be 3");
        assertEquals(2, instance.inDegree("E"), "degree should be 2");
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> coe = instance.outgoingEdges("C");
        assertEquals(2, coe.size(), "Outgoing edges of vert C should be 2");
        coe.removeIf(e -> e.getWeight() == 4 || e.getWeight() == 5);
        assertEquals(0, coe.size(), "Outgoing edges of vert C should be 4 and 5");

        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be 2");
        coe.removeIf(e -> e.getWeight() == 7 || e.getWeight() == 8);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7 and 8");



        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be 1");
        coe.removeIf(e -> e.getWeight() == 7);
        assertEquals(1, coe.size(), "Outgoing edges of vert E should be 7");



        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be empty");
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        System.out.println(" Test incomingEdges");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> cie = instance.incomingEdges("D");
        assertEquals(3, cie.size(), "Incoming edges of vert C should be 3");
        cie.removeIf(e -> e.getWeight() == 3 || e.getWeight() == 4 || e.getWeight() == 7);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 3, 4 and 7");

        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert E should be 2");
        cie.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 8);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5 and 8");



        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert E should be 1");
        cie.removeIf(e -> e.getWeight() == 5);
        assertEquals(1, cie.size(), "Incoming edges of vert C should be 5");



        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert C should be empty");
    }


    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("Test removeVertex");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));


        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");


    }


    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        System.out.println("Test Clone");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");





        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");

    }



    @Test
    public void testUnDirectedGraph() {
        instance = new MatrixGraph<>(false);

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }



        for (int i = 1; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }
    }
}


