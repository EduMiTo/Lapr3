package lapr.project.model.graph;

import lapr.project.model.CountryStore;

import java.util.*;

/**
 *
 * @author DEI-ISEP
 *
 */
public class MatrixGraph<V,E> extends CommonGraph<V,E> {

    public static final int INITIAL_CAPACITY = 10;
    public static final float RESIZE_FACTOR = 1.5F;

    public Edge<V,E> [][] edgeMatrix;


    @SuppressWarnings("unchecked")
    public MatrixGraph(boolean directed, int initialCapacity) {
        super(directed);
        edgeMatrix = (Edge <V,E> [][])( new Edge<?, ?>[initialCapacity][initialCapacity]);
    }

    public MatrixGraph(boolean directed) {
        this(directed, INITIAL_CAPACITY);
    }

    public MatrixGraph(Graph <V,E> g) {
        this(g.isDirected(), g.numVertices());
        copy(g, this);
    }


    @Override
    public Collection<V> adjVertices(V vert) {
        int index = key(vert);
        if (index == -1)
            return null;

        ArrayList<V> outVertices = new ArrayList<>();
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[index][i] != null)
                outVertices.add(vertices.get(i));
        return outVertices;
    }

    @Override
    public Collection<Edge<V, E>> edges() {

        Collection<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E>[] matrix : edgeMatrix) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (matrix[j] != null) {
                    edges.add(matrix[j]);
                }
            }

        }

        return edges;
    }

    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0))
            return null;

        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        if (vOrigKey >= numVerts && vDestKey >= numVerts)
            return null;
        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public int outDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[vertKey][i] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public int inDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        Collection<Edge<V, E>> outgoingEdges = new ArrayList<>();
        for (int i = 0; i < edgeMatrix.length; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                if (edgeMatrix[i][j] != null && edgeMatrix[i][j].getVOrig().equals(vert)) {
                    outgoingEdges.add(edgeMatrix[i][j]);
                }
            }
        }
        return outgoingEdges;
    }

    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        Collection <Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1)
            return ce;

        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                ce.add(edgeMatrix[i][vertKey]);
        return ce;
    }

    @Override
    public boolean addVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey != -1)
            return false;

        vertices.add(vert);
        numVerts++;
        resizeMatrix();
        return true;
    }

    /**
     * Resizes the matrix when a new vertex increases the length of ArrayList
     */
    private void resizeMatrix() {
        if(edgeMatrix.length < numVerts){
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Edge <V,E>[][] temp = (Edge <V,E>[][]) new Edge<?, ?> [newSize][newSize];
            for (int i = 0; i < edgeMatrix.length; i++)
                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);
            edgeMatrix = temp;
        }
    }

    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {
        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        edgeMatrix[vOrigKey][vDestKey] = new Edge<>(vOrig, vDest, weight );
        numEdges++;
        if (!isDirected) {
            edgeMatrix[vDestKey][vOrigKey] = new Edge<>(vDest, vOrig, weight );
            numEdges++;
        }
        return true;
    }





    /**
     * Returns a string representation of the graph.
     * Matrix only represents existence of Edge
     */


   public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Vertices:\n");
        for (int i = 0 ; i < numVerts ; i++)
            sb.append(vertices.get(i)).append("\n");

        sb.append("\nMatrix:\n");

        sb.append("  ");
        for (int i = 0 ; i < numVerts ; i++)
        {
            sb.append(" |  ").append(i).append(" ");
        }
        sb.append("\n");

        // aligned only when vertices < 10
        for (int i = 0 ; i < numVerts ; i++)
        {
            sb.append(" "+ i + " ");
            for (int j = 0 ; j < numVerts ; j++)
                if(edgeMatrix[i][j] != null)
                    sb.append("|  X  ");
                else
                    sb.append("|     ");
            sb.append("\n");
        }

        sb.append("\nEdges:\n");

        for (int i = 0; i < numVerts ; i++)
            for (int j = 0 ; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("From " + i + " to " + j + "-> "+ edgeMatrix[i][j] + "\n");

        sb.append("\n");

        return sb.toString();
    }

    public Map<GenericElement, Integer> cGraph(MatrixGraph graph, List<GenericElement> elements){



        Map<GenericElement, Integer> map = new HashMap<>();

        List<Integer> listOfColors= new ArrayList<>();

        listOfColors.add(0);


        List<GenericElement> listOfColored = new ArrayList<>();

        boolean flag = true;
        for (int i = 0; i < listOfColors.size() && flag; i++){
            for (GenericElement element : elements) {
                if(map.get(element)==null) {
                    if (graph.adjVertices(element).stream().noneMatch(listOfColored::contains) && map.get(element) == null) {
                        map.put(element, listOfColors.get(i));
                        listOfColored.add(element);
                    }
                }
            }
            listOfColors.add(i+1);
            listOfColored.clear();

            if(map.size()== elements.size()){
                flag=false;
            }

        }



        return map;
    }

    public List<Edge> coloredDFS(V startingElement) {

        int [] colors = new int[edgeMatrix.length];
        List <Edge> currentPath = new LinkedList<>();
        List <Edge> largest = new LinkedList<>();

        visit(startingElement, colors, startingElement, currentPath, largest);

        return largest;
    }

    public boolean visit (V vertex, int [] colors, V startingElement, List <Edge> currentPath, List<Edge> largest) {

        Collection<Edge<V, E>> edges = outgoingEdges(vertex);

        int key = key(vertex);
        colors [key] = 1;

        boolean hasACircuit = false;
        for(Edge<V, E> edge: edges) {
            V vDest = edge.getVDest();
            int cDest = colors[key(vDest)];

            if (cDest == 0) {
                currentPath.add(edge);
                boolean childCircuit = visit(edge.getVDest(), colors, startingElement, currentPath, largest);
                if (!hasACircuit) {
                    hasACircuit = childCircuit;
                }
                currentPath.remove(edge);
            }

            if (vDest.equals(startingElement)) {
                hasACircuit = true;
                List <Edge> listToAdd = new LinkedList<>(currentPath);
                listToAdd.add(edge);

                if (largest.size() <= listToAdd.size()) {
                    largest.clear();
                    largest.addAll(listToAdd);
                };
            }
        }
        if (hasACircuit) colors [key] = 0;
        else colors [key] = 2;
        return hasACircuit;
    }


    public void dijkstra(int s, String continent, CountryStore countryStore, double[] nList)
    {

        GenericElement genericElement=null;
        for (int i=0; i<numVertices();i++){
            if (edgeMatrix[s][i]!=null){
                genericElement= (GenericElement)edgeMatrix[s][i].getVOrig();

            }
        }

       if (countryStore.verifyifCountryinContinent(genericElement.getCountry(),continent)) {
           double[] distance = new double[numVertices()];


           boolean[] spSet = new boolean[numVertices()];


           for (int j = 0; j < numVertices(); j++) {
               distance[j] = Double.MAX_VALUE;
               spSet[j] = false;
           }

           distance[s] = 0;

           for (int cnt = 0; cnt < numVertices() - 1; cnt++) {

               int ux = minimumDistance(distance, spSet);


               spSet[ux] = true;


               for (int vx = 0; vx < numVertices(); vx++) {

                   if(edgeMatrix[ux][vx]!=null) {

                       GenericElement dest = (GenericElement) edgeMatrix[ux][vx].getVDest();

                      if (countryStore.verifyifCountryinContinent(dest.getCountry(), continent)) {

                           if (!spSet[vx] && (Double) edgeMatrix[ux][vx].getWeight() != -1 && distance[ux] != Double.MAX_VALUE && distance[ux] + (Double) edgeMatrix[ux][vx].getWeight() < distance[vx]) {
                               distance[vx] = distance[ux] + (Double) edgeMatrix[ux][vx].getWeight();
                           }
                       }
                   }
               }
           }
           printSolution(distance, numVertices(), nList,s);
       }
    }


    int minimumDistance(double[] distance, boolean[] spSet)
    {
        double m = Double.MAX_VALUE;
        int m_index = -1;

        for (int vx = 0; vx < numVertices(); vx++)
        {
            if (!spSet[vx] && distance[vx] <= m)
            {
                m = distance[vx];
                m_index = vx;
            }
        }
        return m_index;

    }

    void printSolution(double[] distance, int n, double[] nList, int pos)
    {
        int total=0;
        int count=0;
        //System.out.println("pais: "+pos);
        for (int j = 0; j < n; j++) {
           // System.out.println("Distancia a j:" + j + "= " + distance[j]);
            if (distance[j]!=Double.MAX_VALUE) {
                total += distance[j];
                count++;

            }
        }

        total=total/(count-1);

        nList[pos]=total;
    }




    public double[] dijkstraNotContinent(int s, int[] lastPos)
    {



            double[] distance = new double[numVertices()];


            boolean spSet[] = new boolean[numVertices()];


            for (int j = 0; j < numVertices(); j++) {
                distance[j] = Double.MAX_VALUE;
                spSet[j] = false;
                lastPos[j]=-1;
            }

            distance[s] = 0;

            for (int cnt = 0; cnt < numVertices() - 1; cnt++) {

                int ux = minimumDistance(distance, spSet);


                spSet[ux] = true;

                //System.out.println(Arrays.toString(spSet));


                for (int vx = 0; vx < numVertices(); vx++) {

                    if(edgeMatrix[ux][vx]!=null) {
                        if (!spSet[vx] && (Double) edgeMatrix[ux][vx].getWeight() != -1 && distance[ux] != Double.MAX_VALUE && distance[ux] + (Double) edgeMatrix[ux][vx].getWeight() < distance[vx]) {
                            distance[vx] = distance[ux] + (Double) edgeMatrix[ux][vx].getWeight();
                            lastPos[vx] = ux;
                        }
                    }
                }
            }



        return distance;
    }


    public List<Integer> getSmallestPath(List<double[]> distances, List<Integer> positions){
        boolean flag=false;
        List<Integer> path = new ArrayList<>();
        System.out.println(distances.size());
        path.add(positions.get(0));
        for (int i=0; i< distances.size() && !flag ;i++){
            System.out.println(i);
            double [] array = distances.get(i);

            Double min= Double.MAX_VALUE;
            int pos= -1;
            int posCiclo=-1;
            for (int j=0; j< distances.size();j++){

                if (array[positions.get(j)]<min && array[positions.get(j)]>0){
                    min= array[positions.get(j)];
                    pos= positions.get(j);
                    posCiclo=j;
                }

            }
            System.out.println("pos ciclo seguinte: " + posCiclo);
            path.add(pos);
            positions.remove(i);
            distances.remove(i);

            if(i<posCiclo)
                i = posCiclo - 2;
            else
                i=posCiclo-1;


            if (distances.size()==1){
                flag=true;
            }
            System.out.println("dist: " + distances.size());

            System.out.println(i);


        }

        path.add(positions.get(positions.size()-1));

        return path;
    }


}


