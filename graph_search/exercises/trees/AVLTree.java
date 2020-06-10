package graph_search.exercises.trees;

public class AVLTree
{

    /**
     * Iterative version
     * @param root
     * @param key
     * @return
     */
    private static Node insertNode(Node root, int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            return root;
        }
        Node current = root;
        Node prev = null;
        while (current != null) {
            if (key < current.key) {
                prev = current;
                current = current.left;
            } else {
                prev = current;
                current = current.right;
            }
        }
        if (key < prev.key) {
            prev.left = node;
        } else {
            prev.right = node;
        }
        node.parent = prev;
        return root;
    }

    /**
     * Recursive version
     * @param root
     * @param key
     * @return
     */
    private static Node insertNodeR(Node root, int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
        } else if (key < root.key) {
            root.left = insertNodeR(root.left, key);
            root.left.parent = root;
        } else {
            root.right = insertNodeR(root.right, key);
            root.right.parent = root;
        }
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }

    private static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println("Node : " + root.key);
            System.out.println("Parent of " + root.key + " is :" + getParentVal(root));
            System.out.println("Height of " + root.key + " is :" + root.height);
            inorder(root.right);
        }
    }

    private static int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private static int getParentVal(Node node) {
        Node parent = node.parent;
        return (parent == null) ? -1 : parent.key;
    }

    private static int getBalanceFactor(Node node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private static Node rotateLeft(Node X) {
        Node Z = X.right;
        Node parentX = X.parent;
        Z.parent = parentX;
        // if X is the left child of its parent
        if (parentX != null && parentX.left == X) {
            parentX.left = Z;
        }
        // if X is the right child of its parent
        else if (parentX != null && parentX.right == X) {
            parentX.right = Z;
        }
        X.right = Z.left;
        if (X.right != null) {
            X.right.parent = X;
        }
        // X is now the left child of Z
        Z.left = X;
        X.parent = Z;
        // update the height of X and Z
        X.height = Math.max(getHeight(X.left), getHeight(X.right)) + 1;
        Z.height = Math.max(getHeight(Z.left), getHeight(Z.right)) + 1;
        // Z is the new root
        return Z;
    }

    private static Node rotateRight(Node X) {
        Node Z = X.left;
        Node parentX = X.parent;
        Z.parent = parentX;
        // if X is the left child of its parent
        if (parentX != null && parentX.left == X) {
            parentX.left = Z;
        }
        // if X is the right child of its parent
        else if (parentX != null && parentX.right == X) {
            parentX.right = Z;
        }
        X.left = Z.right;
        if (X.left != null) {
            X.left.parent = X;
        }
        // X is now the right child of Z
        Z.right = X;
        X.parent = Z;
        // update the height of X and Z
        X.height = Math.max(getHeight(X.left), getHeight(X.right)) + 1;
        Z.height = Math.max(getHeight(Z.left), getHeight(Z.right)) + 1;
        // Z is the new root
        return Z;
    }

    private static Node rebalance(Node root) {
        Node X = root;
        int bFactorX = getBalanceFactor(X);
        // X is right heavy
        if (bFactorX >= 2) {
            Node Z = X.right;
            int bFactorZ = getBalanceFactor(Z);
            // Z is right heavy
            // Z is the right child of X and Z is right heavy
            // Case 1: Right Right
            if (bFactorZ > 0) {
                X = rotateLeft(X);
            }
            // Z is left heavy
            // Z is the right child of X and Z is left heavy
            // Case 2: Right Left
            else {
                rotateRight(Z);
                X = rotateLeft(root);
            }
        }
        // X is left heavy
        else if (bFactorX <= -2) {
            Node Z = X.left;
            int bFactorZ = getBalanceFactor(Z);
            // Z is left heavy
            // Z is the left child of X and Z is left heavy
            // Case 3: Left Left
            if (bFactorZ < 0) {
                X = rotateRight(X);
            }
            // Z is right heavy
            // Z is the left child of X and Z is right heavy
            // Case 4: Left Right
            else {
                rotateLeft(Z);
                X = rotateRight(root);
            }
        }
        return X;
    }

    private static Node insert(Node root, int key) {
        Node newRoot = insertNodeR(root, key);
        return rebalance(newRoot);
    }

    public static void main(String[] args)
    {
        // Right Right Example
        /*Node root = insert(null, 20);
        root = insert(root, 15);
        root = insert(root, 27);
        root = insert(root, 24);
        root = insert(root, 29);
        root = insert(root, 30);
        inorder(root);*/

        // Right Left Example
        /*Node root = insert(null, 20);
        root = insert(root, 15);
        root = insert(root, 27);
        root = insert(root, 25);
        root = insert(root, 29);
        root = insert(root, 24);
        root = insert(root, 26);
        inorder(root);*/

        // Left Left Example
        /*Node root = insert(null, 30);
        root = insert(root, 35);
        root = insert(root, 25);
        root = insert(root, 24);
        root = insert(root, 26);
        root = insert(root, 23);
        inorder(root);*/

        // Left Right Example
        Node root = insert(null, 30);
        root = insert(root, 35);
        root = insert(root, 24);
        root = insert(root, 23);
        root = insert(root, 26);
        root = insert(root, 25);
        root = insert(root, 27);
        inorder(root);
    }
}
