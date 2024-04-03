package lapr.project.utils;

public interface BST_Interface<E> {

    void insert(E element);

    int height();

    int getSize();

    Iterable<E> inOrderIterable();
}
