package com.algo.union;


public class HashMap<Key,Value> {
 private int M;
 
 private com.algo.union.LinkedList<Key,Value>[] list;

 public HashMap(int init) {
	 M = init;
	 list =(LinkedList<Key, Value>[]) new LinkedList[M];
 }

  
  private int hash(Key key) {
	  return (key.hashCode() & 0x7fffffff) % M;
  }
  
  public void put(Key key, Value val) {
	  int idx = hash(key);
	  if(list[idx] == null) {
		 list[idx] =  new com.algo.union.LinkedList(new Node(key,val));
	  }
	  else {
		  list[idx].addNode(new Node(key,val));
	  }
  }
  
  public Value get(Key key) {
	  int idx = hash(key);
	  Value result = null;
	  if(list[idx]==null) return result;
	  else {
		  return (Value) list[idx].getNode(key);
	  }
  }
  
  public static void main(String[] args) {
	HashMap<Integer, String> map = new HashMap<Integer, String>(20);
	map.put(1, "flag");
	map.put(2, "google");
	map.put(90, "haha");
	System.out.println(map.get(1));
  }
  
}


