package org.antlr.v4.runtime.tree.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Tree of Objects of generic type T. The Tree is represented as a single rootElement which
 * points to a List<Node<T>> of children. There is no restriction on the number of children that a particular
 * node may have. This Tree provides a method to serialize the Tree into a List by doing a pre-order
 * traversal. It has several methods to allow easy updating of Nodes in the Tree.
 * @param <T>
 *            The tree's type
 */
public class Tree<T> {

    /**
     * The tree's root element
     */
    private Node<T> rootElement;

    /**
     * A marker for the current tree traversal
     */
    private Node<T> currentRunMarker;

    /**
     * A marker where the last tree traversal stopped
     */
    private Node<T> lastRunMarker;

    /**
     * Default ctor.
     */
    public Tree() {
        super();
        this.rootElement = new Node<T>(null, null);
    }

    /**
     * Default ctor.
     * @param rootData
     *            The root's data.
     */
    public Tree(T rootData) {
        super();
        this.rootElement = new Node<T>(null, rootData);
    }

    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public Node<T> getRootElement() {
        return this.rootElement;
    }

    /**
     * Returns the field 'lastRunMarker'
     * @return value of lastRunMarker
     */
    public Node<T> getLastRunMarker() {
        return this.lastRunMarker;
    }

    /**
     * Sets the field 'lastRunMarker'.
     * @param lastRunMarker
     *            new value of lastRunMarker
     */
    public void setLastRunMarker(Node<T> lastRunMarker) {
        this.lastRunMarker = lastRunMarker;
    }

    /**
     * Returns the field 'currentRunMarker'
     * @return value of currentRunMarker
     */
    public Node<T> getCurrentRunMarker() {
        return this.currentRunMarker;
    }

    /**
     * Sets the field 'currentRunMarker'.
     * @param currentRunMarker
     *            new value of currentRunMarker
     */
    public void setCurrentRunMarker(Node<T> currentRunMarker) {
        this.currentRunMarker = currentRunMarker;
    }

    /**
     * Finds the next Node that has not already been visited.
     *
     * @return The next Node that has not already been visited. Returns null if no more not visited nodes
     *         exist.
     */
    public Node<T> getNextNonVisitedNode() {

        Node<T> nextNode;

        if (lastRunMarker == null) {
            nextNode = rootElement;
        } else if (lastRunMarker.nextSibling() != null) {
            nextNode = lastRunMarker.nextSibling();
        } else {
            Node<T> currentParent = lastRunMarker.getParent();
            while (true) {
                if (currentParent == null) {
                    nextNode = null;
                    break;
                } else {
                    if (currentParent.nextSibling() != null) {
                        nextNode = currentParent.nextSibling();
                        break;
                    } else {
                        currentParent = currentParent.getParent();
                    }
                }
            }

        }

        return nextNode;
    }

    /**
     * Returns the Tree<T> as a List of Node<T> objects. The elements of the List are generated from a
     * pre-order traversal of the tree.
     * @return a List<Node<T>>.
     */
    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        walk(rootElement, list);
        return list;
    }

    /**
     * Returns a String representation of the Tree. The elements are generated from a pre-order traversal of
     * the Tree.
     * @return the String representation of the Tree.
     */
    @Override
    public String toString() {
        return toList().toString();
    }

    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is called from the toList() method
     * with the root element as the first argument. It appends to the second argument, which is passed by
     * reference * as it recurses down the tree.
     * @param element
     *            the starting element.
     * @param list
     *            the output of the walk.
     */
    private void walk(Node<T> element, List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            walk(data, list);
        }
    }
}
