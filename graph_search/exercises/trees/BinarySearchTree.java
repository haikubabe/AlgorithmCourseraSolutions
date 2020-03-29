package graph_search.exercises.trees;

import java.util.Stack;

public class BinarySearchTree
{
    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Inorder recursive version
     * @param root
     */
    private static void inorderWalk(Node root) {
        if (root != null) {
            inorderWalk(root.left);
            System.out.print(root.key + " ");
            inorderWalk(root.right);
        }
    }

    /**
     * Inorder iterative version
     * @param root
     */
    private static void inorderWalkIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.print(current.key + " ");
                current = current.right;
            }
        }
    }

    /**
     * Preorder recursive version
     * @param root
     */
    private static void preorderWalk(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderWalk(root.left);
            preorderWalk(root.right);
        }
    }

    /**
     * Preorder iterative version
     * @param root
     */
    private static void preorderWalkIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node current;
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.key + " ");
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    /**
     * Postorder recursive version
     * @param root
     */
    private static void postorderWalk(Node root) {
        if (root != null) {
            postorderWalk(root.left);
            postorderWalk(root.right);
            System.out.print(root.key + " ");
        }
    }

    /**
     * Postorder iterative version
     * @param root
     */
    private static void postorderWalkIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node previous = null;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.peek();
                if (current.right == null || current.right == previous) {
                    current = stack.pop();
                    System.out.print(current.key + " ");
                    previous = current;
                    current = null;     // so we don't recurse the left subtree again
                } else {
                    current = current.right;
                }
            }
        }
    }

    /**
     * Search a node recursive version
     * @param root
     * @param key
     * @return
     */
    private static Node search(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    /**
     * Search a node iterative version
     * @param root
     * @param key
     * @return
     */
    private static Node searchIterative(Node root, int key) {
        Node current = root;
        while (current != null && current.key != key) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /**
     * Find minimum recursive version
     * @param root
     * @return
     */
    private static Node findMinimum(Node root) {
        if (root == null || root.left == null) {
            return root;
        }
        return findMinimum(root.left);
    }

    /**
     * Find minimum iterative version
     * @param root
     * @return
     */
    private static Node findMinimumIterative(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Find maximum recursive version
     * @param root
     * @return
     */
    private static Node findMaximum(Node root) {
        if (root == null || root.right == null) {
            return root;
        }
        return findMaximum(root.right);
    }

    /**
     * Find maximum iterative version
     * @param root
     * @return
     */
    private static Node findMaximumIterative(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Find the successor of a node
     * @param root
     * @param key
     * @return
     */
    private static Node findSuccessor(Node root, int key) {
        //search the node - O(h)
        Node current = searchIterative(root, key);
        if (current == null) {
            return null;
        }
        // case 1: Node has right subtree
        if (current.right != null) {
            return findMinimum(current.right);
        }
        // case 2: No right subtree
        else {
            Node successor = null;  // it will be null if the current node is the maximum node in the tree
            Node ancestor = root;
            while (current != ancestor) {
                if (current.key < ancestor.key) {
                    successor = ancestor;   // so far this is the deepest node for which the current node lies in its left subtree
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            return successor;
        }
    }

    /**
     * Find the predecessor of a node
     * @param root
     * @param key
     * @return
     */
    private static Node findPredecessor(Node root, int key) {
        Node current = searchIterative(root, key);
        if (current == null) {
            return null;
        }
        // case 1: Node has left subtree
        if (current.left != null) {
            return findMaximumIterative(current.left);
        }
        // case 2: No left subtree
        else {
            Node predecessor = null;  // it will be null if the current node is the maximum node in the tree
            Node ancestor = root;
            while (current != ancestor) {
                if (current.key > ancestor.key) {
                    predecessor = ancestor;  // so far this is the deepest node for which the current node lies in its right subtree
                    ancestor = ancestor.right;
                } else {
                    ancestor = ancestor.left;
                }
            }
            return predecessor;
        }
    }

    /**
     * Insert a node recursively
     * @param root
     * @param key
     * @return
     */
    private static Node insert(Node root, int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
        } else if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        return root;
    }

    /**
     * Insert a node iteratively
     * @param root
     * @param key
     * @return
     */
    private static Node insertIterative(Node root, int key) {
        Node node = new Node(key);
        Node parent = null;
        Node current = root;
        while (current != null) {
            parent = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (parent == null) {
            root = node;
        } else if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return root;
    }

    /**
     * replaces one subtree as a child of its parent with another subtree
     * @param root
     * @param u
     * @param v
     * @param parent
     * @return
     */
    private static Node transplant(Node root, Node u, Node v, Node parent) {
        if (parent == null) {
            root = v;
        } else if (u == parent.left) {
            parent.left = v;
        } else {
            parent.right = v;
        }
        return root;
    }

    /**
     * Find the parent of a node
     * @param root
     * @param key
     * @return
     */
    private static Node findParent(Node root, int key) {
        Node current = root;
        Node parent = null;
        while (current.key != key) {
            if (key < current.key) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        return parent;
    }

    /**
     * Delete a node
     * @param root
     * @param key
     * @return
     */
    private static Node delete(Node root, int key) {
        if (root == null) {
            return null;
        }
        Node current = searchIterative(root, key);
        // parent of the node to be deleted
        Node parent = findParent(root, current.key);

        // case 1: node has no children
        if (current.left == null && current.right == null) {
            root = transplant(root, current, null, parent);
        }
        // case 2: node has one child
        // node has right child
        else if (current.left == null) {
            root = transplant(root, current, current.right, parent);
        }
        // node has left child
        else if (current.right == null) {
            root = transplant(root, current, current.left, parent);
        }
        // case 3: node has two children
        else {
            // find the predecessor of the node to be deleted
            Node predecessor = findMaximum(current.left);
            // find the parent of the predecessor node
            Node parentPred = findParent(root, predecessor.key);
            // predecessor is not the left child of the current node
            if (parentPred != current) {
                root = transplant(root, predecessor, predecessor.left, parentPred);
                predecessor.left = current.left;
            }
            root = transplant(root, current, predecessor, parent);
            predecessor.right = current.right;
        }
        return root;
    }

    /**
     * size of a node is the no. of tree nodes in subtree rooted at x (including x)
     * Size of a node in BST
     * @param root
     * @return
     */
    private static int size(Node root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    /**
     * ith order statistic is the ith smallest element in the BST
     * Select the ith order statistic from BST
     * @param root
     * @param i
     * @return
     */
    private static Node select(Node root, int i) {
        if (root == null || i<=0) {
            return null;
        }
        int sizeLeft = size(root.left);
        if (i-1 == sizeLeft) {
            return root;
        } else if (i <= sizeLeft) {
            return select(root.left, i);
        }
        return select(root.right, i-sizeLeft-1);
    }

    /**
     * rank of a node is the no. of keys smaller than the node's key (including the node's key)
     * Rank of a node based on the given key in BST
     * @param root
     * @param key
     * @return
     */
    private static int rank(Node root, int key) {
        Node current = searchIterative(root, key);
        if (current == null) {
            return 0;
        }
        if (current.key == root.key) {
            return size(root.left) + 1;
        }
        if (current.key < root.key) {
            return rank(root.left, key);
        }
        return size(root.left) + 1 + rank(root.right, key);
    }

    public static void main(String[] args)
    {
        Node root = new Node(6);
        root.left = new Node(4);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        System.out.println("Inorder Traversal Recursive:");
        inorderWalk(root);
        System.out.println();
        System.out.println("Inorder Traversal Iterative:");
        inorderWalkIterative(root);
        System.out.println();

        System.out.println("Preorder Traversal Recursive:");
        preorderWalk(root);
        System.out.println();
        System.out.println("Preorder Traversal Iterative:");
        preorderWalkIterative(root);
        System.out.println();

        System.out.println("Postorder Traversal Recursive:");
        postorderWalk(root);
        System.out.println();
        System.out.println("Postorder Traversal Iterative:");
        postorderWalkIterative(root);
        System.out.println();

        System.out.println("Searching Recursive:");
        int key = 3;
        Node node = search(root, key);
        if (node != null) {
            System.out.println("Node " + node.key + " is found");
        } else {
            System.out.println("Node " + key + " is not found");
        }
        System.out.println("Searching Iterative:");
        Node node1 = searchIterative(root, key);
        if (node1 != null) {
            System.out.println("Node " + node1.key + " is found");
        } else {
            System.out.println("Node " + key + " is not found");
        }

        System.out.println("Finding minimum recursive:");
        Node min = findMinimum(root);
        System.out.println("Min node is " + min.key);
        System.out.println("Finding minimum iterative:");
        Node min1 = findMinimumIterative(root);
        System.out.println("Min node is " + min1.key);

        System.out.println("Finding maximum recursive:");
        Node max = findMaximum(root);
        System.out.println("Max node is " + max.key);
        System.out.println("Finding maximum iterative:");
        Node max1 = findMaximumIterative(root);
        System.out.println("Max node is " + max1.key);

        System.out.println("Find successor:");
        int current = 5;
        Node succ = findSuccessor(root, current);
        if (succ != null) {
            System.out.println("Succesor of " + current + " is " + succ.key);
        } else {
            System.out.println("No succesor of " + current + " is found");
        }

        System.out.println("Find predecessor:");
        int current1 = 6;
        Node pred = findPredecessor(root, current1);
        if (pred != null) {
            System.out.println("Predecessor of " + current1 + " is " + pred.key);
        } else {
            System.out.println("No Predecessor of " + current1 + " is found");
        }

        System.out.println("Insertion recursive");
        int insertKey = 10;
        Node newRoot = insertIterative(root, insertKey);
        System.out.println("After inserting " + insertKey);
        inorderWalk(newRoot);
        System.out.println();
        System.out.println("Insertion iterative");
        int insertKey1 = 3;
        Node newRoot1 = insert(root, insertKey1);
        System.out.println("After inserting " + insertKey1);
        inorderWalk(newRoot1);
        System.out.println();

        System.out.println("Size of root");
        System.out.println(size(root));

        System.out.println("Select ith order statistic");
        int ithOrder = 7;
        Node select = select(root, ithOrder);
        if (select != null) {
            System.out.println("Order statistic of " + ithOrder + " is " + select.key);
        } else {
            System.out.println("Order statistic of " + ithOrder + " is not found");
        }

        System.out.println("Rank");
        int rankNode = 8;
        System.out.println("Rank of node " + rankNode + " is " + rank(root, rankNode));

        Node root1 = new Node(4);
        root1.left = new Node(1);
        root1.right = new Node(5);
        root1.left.right = new Node(3);
        root1.left.right.left = new Node(2);

        inorderWalk(root1);
        System.out.println();
        System.out.println("Deletion");
        int delKey = 4;
        Node newRoot2 = delete(root1, delKey);
        System.out.println("After deletion " + delKey);
        inorderWalk(newRoot2);
    }
}
