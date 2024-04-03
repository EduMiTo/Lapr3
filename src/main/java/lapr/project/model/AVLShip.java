package lapr.project.model;

public class AVLShip extends BSTShip<Ships> {

    private int balanceFactors(Node<Ships> node){
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<Ships> rightRotation(Node<Ships> node){
        BSTShip.Node leftson = node.getLeft();
        node.setLeft(leftson.getRight());
        leftson.setRight(node);
        return leftson;
    }

    private Node<Ships> leftRotation(Node<Ships> node){
        Node<Ships> rightson = node.getRight();
        node.setRight(rightson.getLeft());
        rightson.setLeft(node);
        return rightson;
    }

    private Node<Ships> twoRotations(Node<Ships> node){
        if(balanceFactors(node) < -1) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        }
        if(balanceFactors(node) > 1) {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;

    }

    private Node<Ships> balanceNodes(Node<Ships> node) {
        if(balanceFactors(node) < -1) {
            if (balanceFactors(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if(balanceFactors(node) > 1) {
            if(balanceFactors(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        return node;

    }

    @Override
    public void insert(Ships Value){
        if(Value==null)
            return;
        root = insert(Value, root);
    }

    private Node<Ships> insert(Ships element , Node<Ships> node){
        if(node == null)
            return new Node(element);
        if(element.getMmsi().equals(node.getElement().getMmsi())) {
            node.setElement(element);
        } else {
            if(node.getElement().getMmsi().compareTo(element.getMmsi()) > 0) {
                node.setLeft(insert(element, node.getLeft()));
                node = balanceNodes(node);
            } else {
                node.setRight(insert(element, node.getRight()));
                node = balanceNodes(node);
            }
        }
        return node;
    }

    public boolean equals(Object Obj) {
        if (this == Obj)
            return true;
        if (Obj == null || this.getClass() != Obj.getClass())
            return false;
        AVLShip second = (AVLShip) Obj;
        return equals(root, second.root);
    }

    public boolean equals(Node<Ships> root1, Node<Ships> root2) {
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
