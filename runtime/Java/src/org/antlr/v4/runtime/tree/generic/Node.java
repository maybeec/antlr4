package org.antlr.v4.runtime.tree.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a node of the Tree<T> class. The Node<T> is also a container, and can be thought of as
 * instrumentation to determine the location of the type T in the Tree<T>.
 */
public class Node<T> {

    private T data;

    private List<Node<T>> children;

    private Node<T> parent;

    private boolean visited;

    /**
     * Convenience constructor to create a Node<T> with an instance of T.
     * @param data
     *            an instance of T.
     */
    public Node(Node<T> parent, T data) {
        this.data = data;
        this.parent = parent;
        visited = false;
    }

    /**
     * Return the children of Node<T>. The Tree<T> is represented by a single root Node<T> whose children are
     * represented by a List<Node<T>>. Each of these Node<T> elements in the List can have children. The
     * getChildren() method will return the children of a Node<T>.
     * @return the children of Node<T>
     */
    public List<Node<T>> getChildren() {
        if (this.children == null) {
            return new ArrayList<Node<T>>();
        }
        return this.children;
    }

    /**
     * Adds a list of elements of type <T> to the list of children for this Node<T>. For each element a new
     * child is created. The addition of the first child will create a new List<Node<T>>.
     * @param child
     *            a Node<T> object to set.
     */
    public void addChildren(Collection<T> elements) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }

        for (T element : elements) {
            children.add(new Node<T>(this, element));
        }
    }
    
    public void addChild(T element) {
        if (children == null) {
            children = new ArrayList<Node<T>>();
        }
        children.add(new Node<T>(this, element));
    }

    /**
     * Returns the field 'visited'
     * @return value of visited
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * Sets the field 'visited'.
     * @param visited
     *            new value of visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node<T> nextSibling() {
        if (parent == null) {
            return null;
        }
        int ownPosition = parent.getChildren().indexOf(this);
        if (ownPosition == -1 || ownPosition == parent.getChildren().size() - 1) {
            return null;
        } else {
            return parent.getChildren().get(ownPosition + 1);
        }

    }

    /**
     * Returns the field 'parent'
     * @return value of parent
     */
    public Node<T> getParent() {
        return this.parent;
    }

    /**
     * Finds the next node on the path in the tree from the current node to the destination node.
     *
     * @param destinationNode
     *            The destiny node.
     * @return The next node on the path.
     */
    public Node<T> getNextNodeOnPath(Node<T> destinationNode) {
        Node<T> currentParent = destinationNode.getParent();

        // if the current node is the destination node's parent there is no node in between
        if (currentParent == this) {
            return destinationNode;
        }

        while (currentParent != null) {
            if (currentParent.getParent() == this) {
                return currentParent;
            } else {
                currentParent = currentParent.getParent();
            }
        }

        return null;
    }

    /**
     * Returns the field 'data'
     * @return value of data
     */
    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append((getData() != null ? getData() : "ROOT").toString()).append(",[");
        int i = 0;
        for (Node<T> e : getChildren()) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.getData().toString());
            i++;
        }
        sb.append("]").append("}").append(":").append(visited);
        return sb.toString();
    }

}
