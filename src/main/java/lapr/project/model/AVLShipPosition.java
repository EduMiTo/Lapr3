package lapr.project.model;

public class AVLShipPosition extends BSTShipPosition<ShipPosition> {
    private int balanceFactor(BSTShipPosition.Node<ShipPosition> node){
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<ShipPosition> rightRotation(Node<ShipPosition> node){
        BSTShipPosition.Node leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        return leftson;
    }

    private Node<ShipPosition> leftRotation(Node<ShipPosition> node){
        BSTShipPosition.Node<ShipPosition> rightson = node.getRight();
        node.setRight(rightson.getLeft());
        rightson.setLeft(node);
        return rightson;
    }

    private Node<ShipPosition> twoRotations(Node<ShipPosition> node){
        if(balanceFactor(node) < -1) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        }
        if(balanceFactor(node) > 1) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;

    }

    private Node<ShipPosition> balanceNode(Node<ShipPosition> node) {
        if(balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if(balanceFactor(node) > 1) {
            if(balanceFactor(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        return node;

    }

    @Override
    public void insert(ShipPosition Value){
        if(Value==null)
            return;
        root = insert(Value, root);
    }

    private Node<ShipPosition> insert(ShipPosition element , Node<ShipPosition> node){
        if(node == null)
            return new Node(element);

        if(element.getAisMessage() == node.getElement().getAisMessage()) {
            node.setElement(element);
        } else {
            if(node.getElement().getAisMessage().compareTo(element.getAisMessage()) > 0) {
                node.setLeft(insert(element, node.getLeft()));
                node = balanceNode(node);
            } else {
                node.setRight(insert(element, node.getRight()));
                node = balanceNode(node);
            }
        }
        return node;

    }

    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVLShipPosition second = (AVLShipPosition) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<ShipPosition> root1, Node<ShipPosition> root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else
                return false;
        }
        else return false;
    }
}
