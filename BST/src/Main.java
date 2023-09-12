
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Hamza
 */
public class Main {

    public static void main(String[] args) {
        // this is just  a test class 
        Tree<Integer, String> tree = new Tree<>();
//        Node<Integer,String> root = new Node<>(5,"test");
//        tree.root = root;
        //Node<Integer,String> root = tree.root;

        //Scanner input = new Scanner(System.in);
        //String inputString = input.nextLine();
        String inputString = "merhaba";
        System.out.println("Enter your word");
        String[] m = {"merhaba","Kedi", "Köpek", "Ev", "Araba", "Kahve", "Masa", "Sandalye",
            "Telefon", "Gözlük", "Saç", "Kırmızı", "Yeşil", "Mavi", "Sarı", "Turuncu",
            "Mor", "Pembe", "Siyah",
            "Beyaz", "Elma", "Portakal", "Muz", "Üzüm", "Çilek", "Limon", "Domates",
            "Patates", "Havuç", "Kabak",
            "Sarımsak", "Soğan", "Süt", "Peynir", "Yoğurt", "Bal", "Tavuk", "Et",
            "Balık", "Pilav", "Ekmek",
            "Makarna", "Çorba", "Salata", "Pizza", "Mısır", "Çikolata"};
        for (int i = 0; i < m.length; i++) {
            Node<Integer, String> newNode = new Node<>(Tree.levenshteinDistance(m[i], inputString.toLowerCase()), m[i].toLowerCase());
            tree.insert(newNode.key, newNode.value);
        }

        if (tree.root == null) {
            System.out.println("the tree is empty");
        } else {
            tree.printInOrder();
        }
        System.out.println("the size of the tree  is:" + tree.size());
        if (tree.root == null) {
            System.out.println("the tree is empty");
        } else {
            tree.printPostOrder();
        }

        System.out.println("min Tree");
        Tree<Integer, String> minTree = new Tree<>();
        minTree =minTree.findMin(tree);
        System.out.println("printing the minTree");
        minTree.printInOrder();
      
    }
}




 // minTree.insert(0, "test");
//        minTree.printInOrder();
//        Node<Integer, String> minNode = tree.findMostLeft(tree.root);
//        minTree.insert(minNode.key, minNode.value);
//        tree.delete(minNode.key);
//        Node<Integer, String> minNode1 = tree.findMostLeft(tree.root);
//        minTree.insert(minNode1.key, minNode1.value);
//        tree.delete(minNode1.key);
//        Node<Integer, String> minNode2 = tree.findMostLeft(tree.root);
//        minTree.insert(minNode2.key, minNode2.value);
//        tree.delete(minNode2.key);
//        minTree.printInOrder();
//        System.out.println("tree after deleting");
//        tree.printInOrder();

