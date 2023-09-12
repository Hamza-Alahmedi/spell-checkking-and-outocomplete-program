
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Scanner;
import jdk.nashorn.api.tree.ContinueTree;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Hamza
 * @param <K>
 * @param <V>
 */
public class Tree<K extends Comparable<K>, V> {

    Node<K, V> root;

    public Tree() {
        root = null;

    }

    public void insert(K key, V value) {

        Node<K, V> newNode = new Node<>(key, value);

        if (root == null) {
            root = newNode;
        } else {

            Node<K, V> temp = root;
            Node<K, V> parent = null;

            while (temp != null) {
                parent = temp;

                if (newNode.key.compareTo(temp.key) > 0) {

                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }

                    temp = temp.right;

                } else if (newNode.key.compareTo(temp.key) < 0) {

                    if (temp.left == null) {
                        temp.left = newNode;
                        return;
                    }

                    temp = temp.left;

                } else {

                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }

                    temp = temp.right;

                }

            }
        }
    }

    public Tree<Integer, String> createTreeFromDict(String input) {
        Tree<Integer, String> dictTree = new Tree<>();
        try {
            File file = new File("C:\\Users\\hamza\\OneDrive\\Documents\\NetBeansProjects\\BST\\src\\Dict.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int distance = Tree.levenshteinDistance(line, input.toLowerCase());
                dictTree.insert(distance, line.toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return dictTree;
    }

    public Tree<K, V> findMin(Tree<K, V> tree) {
        Tree<K, V> minimumTree = new Tree<>();
        Node<K, V> root = tree.root;
        int count = 0;
        Node<K, V> first = new Node<>();
        Node<K, V> second = new Node<>();
        Node<K, V> third = new Node<>();
        Node<K, V> parent = new Node<>();
        if (root == null) {
            System.out.println("this tree is empty");
            return minimumTree;
        }

        if (root.left == null) {  //if the tree does not hava a left subtrees
            first = root;          //the minimum node will be the root
            if (root.right != null && root.right.left != null) {
                second = findMinHelper(root.right);
                if (second.right != null) {
                    third = findMinHelper(second.right);
                } else {
                    third = findParent(root, third);
                }
            }

        } else {
            first = findMinHelper(root);
            parent = findParent(root, first);

            count++;
            // System.out.println(first.value);
            if (first.right != null) {
                second = findMinHelper(first.right);

            } else {
                second = parent;

                count++;
            }
            if (parent.right != null) {
                third = findMinHelper(parent.right);

                count++;
            } else {
                parent = findParent(root, parent);
                third = parent;

            }
        }

        System.out.println(first.value);

        System.out.println(second.value);

        System.out.println(third.value);

        minimumTree.insert(first.key, first.value);

        minimumTree.insert(second.key, second.value);

        minimumTree.insert(third.key, third.value);

        return minimumTree;
    }

    public Node<K, V> findParent(Node<K, V> root, Node<K, V> node) {
        if (root == null || node == null) {
            return null;
        }

        Node<K, V> curr = root;
        Node<K, V> prev = null;

        while (curr != null) {
            if (node.key.compareTo(curr.key) < 0) {
                prev = curr;
                curr = curr.left;
            } else if (node.key.compareTo(curr.key) > 0) {
                prev = curr;
                curr = curr.right;
            } else {
                // found the node, so return its parent
                return prev;
            }
        }

        // the node was not found in the tree, so return null
        return null;
    }

    public Node<K, V> findMinHelper(Node<K, V> node) {
        if (node == null) {
            System.out.println("null node");
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void printInOrder() {
        System.out.println("inOrder");
        printInOrder(root);
    }

    private void printInOrder(Node<K, V> node) {
        if (node != null) {

            printInOrder(node.left);
            System.out.println(node.key + ": " + node.value);
            printInOrder(node.right);
        }
    }

    public void printPostOrder() {
        System.out.println("Post Order");
        printPostOrder(root);
    }

    private void printPostOrder(Node<K, V> node) {
        if (node != null) {

            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.println(node.key + ": " + node.value);

        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public void delete(K key) {
        root = deleteNode(root, key);
    }

    private Node<K, V> deleteNode(Node<K, V> current, K key) {
        // Base case: if the tree is empty
        if (current == null) {
            return null;
        }

        // If the key to be deleted is less than the current node's key, go left
        if (key.compareTo(current.key) < 0) {
            current.left = deleteNode(current.left, key);
        } // If the key to be deleted is greater than the current node's key, go right
        else if (key.compareTo(current.key) > 0) {
            current.right = deleteNode(current.right, key);
        } // If the key to be deleted is equal to the current node's key, delete the current node
        else {
            // Case 1: current node has no children
            if (current.left == null && current.right == null) {
                current = null;
            } // Case 2: current node has one child
            else if (current.left == null) {
                current = current.right;
            } else if (current.right == null) {
                current = current.left;
            } // Case 3: current node has two children
            else {
                Node<K, V> successor = findSuccessor(current.right);
                current.key = successor.key;
                current.value = successor.value;
                current.right = deleteNode(current.right, successor.key);
            }
        }

        return current;
    }

    private Node<K, V> findSuccessor(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static int levenshteinDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] d = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(d[i - 1][j], Math.min(d[i][j - 1], d[i - 1][j - 1])) + 1;
                }
            }
        }

        return d[m][n];
    }
}
