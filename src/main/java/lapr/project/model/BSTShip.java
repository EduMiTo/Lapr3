package lapr.project.model;

import lapr.project.utils.BST_Interface;

import java.util.*;

public abstract class BSTShip<E> implements BST_Interface<Ships> {

    public static class Node<Ships> {
        private Ships element;
        private Node<Ships> left;
        private Node<Ships> right;

        public Node(Ships value) {
            this.element = value;
            right = null;
            left = null;
        }

        // accessor methods
        public Ships getElement() { return element; }
        public Node<Ships> getLeft() { return left; }
        public Node<Ships> getRight() { return right; }

        // update methods
        public void setElement(Ships e) { element = e; }
        public void setLeft(Node<Ships> leftChild) { left = leftChild; }
        public void setRight(Node<Ships> rightChild) { right = rightChild; }
    }

    Node<Ships> root;

    public BSTShip() {
        root = null;
    }

    public int height(){
        return height(root);
    }

    protected int height(Node<Ships> node){
        if(node == null)
            return -1;
        int lDepth = height(node.left);
        int rDepth = height(node.right);

        if(lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

    public abstract void insert(Ships value);

    public Node<Ships> search(String value){
        return searchRec(root, value);
    }

    Node<Ships> searchRec(Node<Ships> node, String elem){
        if (node == null) {

            return null;
        }
        if (Long.parseLong(node.getElement().getMmsi())==(Long.parseLong(elem))) {

            return node;
        }
        if (Long.parseLong(node.getElement().getMmsi())>(Long.parseLong(elem))) {

            return searchRec(node.getLeft(), elem);
        }
        else {

            return searchRec(node.getRight(), elem);

        }
    }

    public Iterable<Ships> inOrderIterable(){
        List<Ships> snapshot = new ArrayList<>();
        if (root!=null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    private void inOrderSubtree(BSTShip.Node<Ships> node, List<Ships> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }


    public int getSize(){
        return size(root);
    }

    int size(BSTShip.Node<Ships> node)
    {
        if (null == node ) return 0;
        return 1 + size( node.getLeft() ) + size( node.getRight() );
    }

    public List<SmallSummary> organizeSmallSummaryInfo(){
        List<SmallSummary> smallSummaries= new ArrayList<>();
        Iterable<Ships> shipsIterable= inOrderIterable();
        for (Ships s : shipsIterable){
            BSTShipPosition bstShipPosition = s.getBstMessage();
            String mmsi = s.getMmsi();
            double realDist= bstShipPosition.inorderCalculateDistance();
            int numberOfMovements= bstShipPosition.getSize();
            ShipPosition firstDate= bstShipPosition.minDate();
            ShipPosition lastDate= bstShipPosition.maxDate();
            double deltaDista= bstShipPosition.calculateDistance(Float.parseFloat(firstDate.getLatitude()),Float.parseFloat(firstDate.getLongitude()),Float.parseFloat(lastDate.getLatitude()),Float.parseFloat(lastDate.getLongitude()));

            SmallSummary smallSummary= new SmallSummary(mmsi,numberOfMovements,deltaDista,realDist);
            smallSummaries.add(smallSummary);
        }
        return smallSummaries;
    }

    public Map<Ships,List<Ships>> pairShips(){
        boolean flag = false;
        List<Ships> shipsIterable= (List<Ships>)inOrderIterable();
        List<Double> distances;
        List<Ships> shipsList;
        Map<Ships,List<Ships>> map= new TreeMap<>();

        for (int i = 0; i < shipsIterable.size() - 1; i++) {
            shipsList = new ArrayList<>();
            distances = new ArrayList<>();

            BSTShipPosition bstShipPosition = shipsIterable.get(i).getBstMessage();

            double travelDistance = bstShipPosition.inorderCalculateDistance();

            for (int j = i + 1; j < shipsIterable.size(); j++) {
                BSTShipPosition bstShipPositionOtherShip = shipsIterable.get(j).getBstMessage();

                double depdist = bstShipPosition.calculateDistance(Float.parseFloat(bstShipPosition.minDate().getLatitude()), Float.parseFloat(bstShipPosition.minDate().getLongitude()), Float.parseFloat(bstShipPositionOtherShip.minDate().getLatitude()), Float.parseFloat(bstShipPositionOtherShip.minDate().getLongitude()));



                double arrdist = bstShipPosition.calculateDistance(Float.parseFloat(bstShipPosition.maxDate().getLatitude()), Float.parseFloat(bstShipPosition.maxDate().getLongitude()), Float.parseFloat(bstShipPositionOtherShip.maxDate().getLatitude()), Float.parseFloat(bstShipPositionOtherShip.maxDate().getLongitude()));

                double otherShipTravelDist = bstShipPositionOtherShip.inorderCalculateDistance();


                if ((Math.abs(depdist) < 5000.0 || Math.abs(arrdist) < 5000.0) && (otherShipTravelDist != travelDistance) && otherShipTravelDist > 10000.0 && travelDistance > 10000.0) {
                    distances.add(Math.abs(otherShipTravelDist - travelDistance));
                    shipsList.add(shipsIterable.get(j));

                    flag = true;

                }

            }
            if (flag) {
                shipsList=organize(distances,shipsList);
                map.put(shipsIterable.get(i), shipsList);
                flag = false;
            }
        }

        return map;
    }

    public List<Ships> organize(List<Double> distances, List<Ships> shipsList){
        for(int l=0; l < distances.size(); l++) {
            for (int j = 1; j < distances.size() - l; j++) {
                if (distances.get(j - 1) < distances.get(j)) {
                    Collections.swap(distances, j - 1, j);
                    Collections.swap(shipsList, j - 1, j);
                }
            }
        }
        return shipsList;
    }

    public Ships getShipsByMMSI(String mmsi) {
        BSTShip.Node<Ships> ship = search(mmsi);
        if(ship != null) {
            return ship.getElement();
        }
        return null;
    }

    public Ships getShipsByIMO(String imo) {
        for (Ships ship : inOrderIterable()) {
            if (ship.getImo().equals(imo)) {
                return ship;
            }
        }
        return null;
    }

    public Ships getShipsByCallSign(String callSign) {
        for (Ships ship : inOrderIterable()) {
            if (ship.getCallSign().equals(callSign)) {
                return ship;
            }
        }
        return null;
    }

    public List<SmallSummary> smallSummariesBeetwenDate(Date date1, Date date2){

    List<SmallSummary> smallSummaries= new ArrayList<>();


        for (Ships s : inOrderIterable()) {

            BSTShipPosition treeInfo = s.getBstMessage();
            double distance = treeInfo.distanceBetweenDate(date1, date2);

            SmallSummary smallSummary = new SmallSummary(s.getMmsi(), distance, s.getVesselType(), (float) treeInfo.inorderMinSOG());

            smallSummaries.add(smallSummary);
        }

        return smallSummaries;
    }

    public Map<String, List<SmallSummary>> sameVessel(List<SmallSummary> smallSummaries) {
        Map<String, List<SmallSummary>> map= new TreeMap<>();
        for(int i=0;i<smallSummaries.size();i++){
            List<SmallSummary> smallSummaries2= new ArrayList<>();
            String vesselType1= smallSummaries.get(i).getVesselType();
            smallSummaries2.add(smallSummaries.get(i));
            smallSummaries.remove(i);
            i--;
            for (int j=i+1;j<smallSummaries.size();j++){
                String vesselType2= smallSummaries.get(j).getVesselType();

                if (vesselType1.equals(vesselType2)){

                    smallSummaries2.add(smallSummaries.get(j));

                    smallSummaries.remove(j);
                    j--;

                }
            }

            smallSummaries2=orderVesselDes(smallSummaries2);

            map.put(vesselType1,smallSummaries2);
        }
        return map;
    }

    public List<SmallSummary> orderVesselDes(List smallSummaries2){
        Collections.sort(smallSummaries2, (Comparator<SmallSummary>) (o1, o2) -> {
            if(o1.getRealDistance()<o2.getRealDistance()){
                return 1;
            }
            else {
                if (o1.getRealDistance()>o2.getRealDistance()){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        return smallSummaries2;
    }


}
