package lapr.project.model.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


/**
 *
 * @author DEI-ISEP
 *
 */
public abstract class CommonGraph <V,E> implements Graph<V,E> {
    protected int numVerts;
    protected int numEdges;
    protected final boolean isDirected;
    protected ArrayList<V> vertices;       // Used to maintain a numeric key to each vertex

    public CommonGraph(boolean directed) {
        numVerts = 0;
        numEdges = 0;
        isDirected = directed;
        vertices = new ArrayList<>();
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public int numVertices() {
        return numVerts;
    }

    @Override
    public ArrayList<V> vertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public boolean validVertex(V vert) { return vertices.contains(vert);   }

    @Override
    public int key(V vert) {
        return vertices.indexOf(vert);
    }



    @Override
    public int numEdges() {
        return numEdges;
    }

    /** Copy graph from to graph to
     *
     * @param from graph from which to copy
     * @param to graph for which to copy
     */
    protected void copy(Graph <V,E> from, Graph <V,E> to) {
        //insert all vertices
        for (V v : from.vertices()) {
            to.addVertex(v);
        }

        //insert all edges
        for (Edge<V, E> e : from.edges()) {
            to.addEdge(e.getVOrig(), e.getVDest(), e.getWeight());
        }
    }




    @Override
    public int hashCode() {
        return Objects.hash(numVerts, numEdges, isDirected, vertices);
    }
}

