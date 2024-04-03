package lapr.project.model;

import java.util.*;

public abstract class BSTShipPosition<E extends Comparable<E>>{
    public static class Node<ShipPosition> {
        private ShipPosition element;
        private Node left;
        private Node right;

        public Node(ShipPosition value) {
            this.element = value;
            right = null;
            left = null;
        }
        // accessor methods
        public ShipPosition getElement() { return element; }
        public BSTShipPosition.Node<ShipPosition> getLeft() { return left; }
        public BSTShipPosition.Node<ShipPosition> getRight() { return right; }

        // update methods
        public void setElement(ShipPosition e) { element = e; }
        public void setLeft(BSTShipPosition.Node<ShipPosition> leftChild) { left = leftChild; }
        public void setRight(BSTShipPosition.Node<ShipPosition> rightChild) { right = rightChild; }
    }

    private List<ShipPosition> vecnodes = new ArrayList<>();

    BSTShipPosition.Node<ShipPosition> root;

    private static final String NOT_AVAILABLE = "Not available";

    public BSTShipPosition() {
        root = null;
    }



    protected int height(BSTShipPosition.Node<ShipPosition> node){
        if(node == null)
            return -1;
        int lDepth = height(node.left);
        int rDepth = height(node.right);

        if(lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }
    public abstract void insert(ShipPosition value);


    @Override
    public String toString() {
        return "BSTMessage{" + size(root) +
                "root="+ root.getElement().getCog();
    }


    public int getSize(){
        return size(root);
    }

    int size(Node<ShipPosition> node)
    {
        if (null == node ) return 0;
        return 1 + size( node.getLeft() ) + size( node.getRight() );
    }

    public List<ShipPosition> searchSpecificDatePeriodcall(Date date1, Date date2){

        vecnodes=new ArrayList<>();

        searchSpecificDatePeriod(root,date1,date2);

        return vecnodes;
    }

    void searchSpecificDatePeriod(Node<ShipPosition> node, Date date1, Date date2) {

        if(node == null){
            return;
        }
        if(date1.compareTo(node.getElement().getAisMessage())<0){
            searchSpecificDatePeriod(node.getLeft(),date1,date2);
        }

        if(date1.compareTo(node.getElement().getAisMessage())<=0 && date2.compareTo(node.getElement().getAisMessage())>=0){
            vecnodes.add(node.getElement());
        }

        searchSpecificDatePeriod(node.getRight(),date1,date2);

    }


    public ShipPosition minDate(){

        return minvalue(root);
    }


    ShipPosition minvalue(Node<ShipPosition> node) {
        Node<ShipPosition> current = node;

        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return (current.getElement());
    }

    public ShipPosition maxDate(){

        return maxvalue(root);
    }


    ShipPosition maxvalue(Node<ShipPosition> node) {
        Node<ShipPosition> current = node;

        /* loop down to find the leftmost leaf */
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return (current.getElement());
    }

    public double calculateDistance(float lati, float loni,float latf, float lonf){

        int earthRadius = 6371000;

        double nLati= toRadian(lati);
        double nLoni= toRadian(loni);
        double nLatf= toRadian(latf);
        double nLonf= toRadian(lonf);

        double dLat= nLatf-nLati;

        double dLon= nLonf-nLoni;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(nLati)* Math.cos(nLatf) * Math.sin(dLon/2) * Math.sin(dLon/2);

        double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));


        return earthRadius*b;
    }


    public double toRadian(float degree){

        return (degree*Math.PI)/180;


    }

    public Iterable<ShipPosition> inOrderIterable(){
        List<ShipPosition> snapshot = new ArrayList<>();
        if (root!=null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    private void inOrderSubtree(Node<ShipPosition> node, List<ShipPosition> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    public double inorderCalculateDistance() {
        Iterable<ShipPosition> messages = inOrderIterable();
        Iterator<ShipPosition> iterator = messages.iterator();

        ShipPosition aux = iterator.next();



        double totalDistance = 0;
        while (iterator.hasNext()){
            ShipPosition naux= iterator.next();
            if(aux.getLatitude().equals(NOT_AVAILABLE) || aux.getLongitude().equals(NOT_AVAILABLE) || naux.getLatitude().equals(NOT_AVAILABLE) || naux.getLongitude().equals(NOT_AVAILABLE)){

                totalDistance=totalDistance+0;
            }
            else {
                totalDistance = totalDistance + calculateDistance(Float.parseFloat(aux.getLatitude()), Float.parseFloat(aux.getLongitude()), Float.parseFloat(naux.getLatitude()), Float.parseFloat(naux.getLongitude()));
                aux=naux;
            }




        }
        return totalDistance;
    }

    public double inorderMaxSOG(){
        Iterable<ShipPosition> messages = inOrderIterable();
        double maxSog = 0;
        for (ShipPosition s : messages){
            if (s.getSog() > maxSog) maxSog = s.getSog();
        }
        return maxSog;
    }

    public double inorderMinSOG(){
        List<ShipPosition> messages = (List<ShipPosition>) inOrderIterable();

        double soma=0.0;
        for (ShipPosition s : messages){
            soma+=s.getSog();
        }
        return soma/messages.size();
    }

    public double inorderMaxCOG(){
        Iterable<ShipPosition> messages = inOrderIterable();
        double maxCog = 0;
        for (ShipPosition s : messages){
            if (s.getCog() > maxCog) maxCog = s.getCog();
        }
        return maxCog;
    }

    public double inorderMinCOG(){
        List<ShipPosition> messages = (List<ShipPosition>)inOrderIterable();
        double soma=0.0;
        for (ShipPosition s : messages){
            soma+=s.getCog();
        }
        return soma/messages.size();
    }

    public String dateDiff(Date date1, Date date2){

        long seconds = date1.getTime()-date2.getTime();


        int hours= (int) (seconds/3600000);

        int min= (int) ((seconds - (hours*3600000))/60000);

        int sec= (int) (seconds-(hours*3600000)-(min*60000));

        String time= hours+"h "+min+"m "+sec+"s";



        return time;
    }

    public double distanceBetweenDate(Date date1, Date date2) {
        Iterable<ShipPosition> messages = inOrderIterable();

        Iterator<ShipPosition> iterator = messages.iterator();

        ShipPosition aux = iterator.next();

        double totalDistance = 0;
        while (iterator.hasNext()){

            ShipPosition naux= iterator.next();
            if(aux.getLatitude().equals(NOT_AVAILABLE) || aux.getLongitude().equals(NOT_AVAILABLE) || naux.getLatitude().equals(NOT_AVAILABLE) || naux.getLongitude().equals(NOT_AVAILABLE)){
                totalDistance=totalDistance+0;
                if(aux.getLatitude().equals(NOT_AVAILABLE)|| aux.getLongitude().equals(NOT_AVAILABLE)){

                    aux=naux;
                }
            }
            else {

                if (aux.getAisMessage().compareTo(date1) > 0 && aux.getAisMessage().compareTo(date2) < 0 && naux.getAisMessage().compareTo(date1) > 0 && naux.getAisMessage().compareTo(date2) < 0) {

                    totalDistance = totalDistance + calculateDistance(Float.parseFloat(aux.getLatitude()), Float.parseFloat(aux.getLongitude()), Float.parseFloat(naux.getLatitude()), Float.parseFloat(naux.getLongitude()));

                }
                aux=naux;
            }




        }

        return totalDistance;
    }


    public BSTShipPosition.Node<ShipPosition> search(Date value){
        return searchRec(root, value);
    }


    BSTShipPosition.Node<ShipPosition> searchRec(BSTShipPosition.Node<ShipPosition> node, Date elem){
        if (node == null) {

            return null;
        }

        if (node.getElement().getAisMessage().compareTo(elem)==0){

            return node;
        }
        if (node.getElement().getAisMessage().compareTo(elem)>0){

            return searchRec(node.getLeft(), elem);
        }
        else {
            return searchRec(node.getRight(), elem);
        }
    }

}
