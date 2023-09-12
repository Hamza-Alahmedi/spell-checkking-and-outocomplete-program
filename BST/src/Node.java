/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hamza
 */
public class Node<K extends Comparable<K>, V> {
    K key; // the distance of the word
    V value; // the string word
    Node<K, V> left, right;
    //parent;
    
    public Node(){
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
       left = right  = null;

    }
}
