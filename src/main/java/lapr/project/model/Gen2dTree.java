package lapr.project.model;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.List;

public class Gen2dTree<E> {

    public static class Node<E> {
        private E element;
        private Gen2dTree.Node<E> left;
        private Gen2dTree.Node<E> right;
        private final Point2D.Double cords;
        public Node(E value, double lat, double lon) {
            this.element = value;
            this.cords = new Point2D.Double(lat,lon);
            right = null;
            left = null;
        }

        public E getPort() { return element; }
        public Point2D.Double getCords() { return cords; }
        public Double getX() { return cords.x; }
        public Double getY() { return cords.y; }
        public Gen2dTree.Node<E> getLeft() { return left; }
        public Gen2dTree.Node<E> getRight() { return right; }

        // update methods
        public void setElement(E port) { this.element = port; }
        public void setCords(Point2D cords) {
            this.cords.x=cords.getX();
            this.cords.y=cords.getY();
        }
    }

    private final Comparator<Gen2dTree.Node<E>> compareX = (p1, p2) -> Double.compare(p1.getX(), p2.getX());


    private final Comparator<Gen2dTree.Node<E>> compareY = (p1, p2) -> Double.compare(p1.getY(), p2.getY());

    protected Gen2dTree.Node<E> root; // root of the tree

    /**
     * Constructs an empty 2D tree
     */
    public Gen2dTree(){
        root=null;
    }

    /**
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    public Gen2dTree.Node<E> root() {
        return root;
    }

    /**
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
        return root==null;
    }

    public E findNearestNeighbour(double latitude, double longitude) {
        return findNearestNeighbour(root, latitude, longitude,root, true);
    }

    private E findNearestNeighbour(Gen2dTree.Node<E> node, double x, double y, Gen2dTree.Node<E> closestNode , boolean divX) {

        if (node == null)
            return null;

        double d = Point2D.distanceSq(node.getCords().x, node.getCords().y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.getCords().x, closestNode.getCords().y, x, y);

        if (closestDist > d) {
            closestNode.setElement(node.getPort());
            closestNode.setCords(node.getCords());
        }

        double delta = divX ? x - node.getCords().x : y - node.getCords().y;
        double delta2 = delta * delta;

        Gen2dTree.Node<E> node1 = delta < 0 ? node.getLeft() : node.getRight();
        Gen2dTree.Node<E> node2 = delta < 0 ? node.getRight() : node.getLeft();

        findNearestNeighbour(node1,x,y,closestNode, !divX);

        if (delta2 < closestDist) {
            findNearestNeighbour(node2, x,y,closestNode,!divX);
        }

        return closestNode.getPort();
    }

    public void buildTree(List<Node<E>> nodes) {
        root = buildTree(true, nodes);
    }

    private Node<E> buildTree(boolean divX, List<Node<E>> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        nodes.sort(divX ? compareX : compareY);
        int median = nodes.size() >> 1;
        Node<E> node = new Node<>(nodes.get(median).getPort(), nodes.get(median).getX(), nodes.get(median).getY());
        node.left = buildTree(!divX, nodes.subList(0, median));
        if (median + 1 <= nodes.size() - 1)
            node.right = buildTree(!divX, nodes.subList(median+1, nodes.size()));

        return node;
    }


}
