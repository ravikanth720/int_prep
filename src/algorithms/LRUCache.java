package algorithms;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Node front;
    Node back;
    int capacity;
    
    Map<Integer, Node> cacheLookup = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheLookup = new HashMap<Integer, Node>();
        front = null;
        back = null;
    }
    
    public int get(int key) {
        if(cacheLookup.containsKey(key)){
            Node curr = cacheLookup.get(key);
            if(curr != front){
                if(curr == back){
                    Node newItem = back;
                    back = newItem.prev;
                    back.next = null;
                }else{
                    Node nextTmp = curr.next;
                    Node prevTmp = curr.prev;
                    prevTmp.next = nextTmp;
                    nextTmp.prev = prevTmp;
                }
                
                front.prev = curr;
                curr.next = front;
                curr.prev = null;
                front = curr;
            }
            
            return curr.value;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        Node newItem = new Node(key, value);
        
        //condition for size = 1
        if(this.capacity == 1){
            front = back = newItem;
            if(cacheLookup.size() == 0){
	            cacheLookup.put(key, newItem);
	            return;
            }else{
            	cacheLookup = new HashMap<Integer, LRUCache.Node>();
            	cacheLookup.put(key, newItem);
            	return;
            }
        }
        
        if(cacheLookup.containsKey(key)){
            Node curr = cacheLookup.get(key);
            if(curr == front){
                curr.value = value;
                return;
            }else if(curr == back){
                newItem = back;
                back = newItem.prev;
                back.next = null;
            }else{
                Node nextTmp = curr.next;
                Node prevTmp = curr.prev;
                prevTmp.next = nextTmp;
                nextTmp.prev = prevTmp;
            }
        }else{
            if(cacheLookup.size() >= this.capacity){
                int backKey = back.key;
                back = back.prev;
                back.next.prev = null;
                cacheLookup.remove(backKey);
            }
        }
        if(front == null && back == null){
                front = newItem;
                back = newItem;
            }else{
                //Add new element to front of list
                front.prev = newItem;
                newItem.next = front;
                front = newItem;
            }
            cacheLookup.put(key, newItem);
    }
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        
        Node(int key, int value){
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
    }
    
    public static void main(String[] args) {
		LRUCache c = new LRUCache(1);
		c.set(2, 1);
		c.get(2);
		c.set(3, 2);
		c.get(2);
		c.get(3);
	}
}