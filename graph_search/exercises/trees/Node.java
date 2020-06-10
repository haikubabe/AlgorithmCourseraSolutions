package graph_search.exercises.trees;

public class Node
{
    public int key;
    public Node left;
    public Node right;
    public Node parent;
    public int height;

    public Node(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
    }
}
