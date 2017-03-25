package com.algo.union;




public class LinkedList<Key, Value> {

	  private Node start;
	  public LinkedList(Node node) {
		  start = node;
	  }
	  
	  public void addNode(Node node) {
		  Node cur = start;
		  while(cur.next!=null) {
			  cur  = cur.next;
		  }
		  cur.next = node;
	  }
	  
	  public Value getNode(Key key) {
		  Node cur = start;
		  Value valRet = null;
		  while(cur!=null) {
			  if(cur.key == key) {
				  valRet = (Value) cur.value;
				  return valRet;
			  }
			  cur = cur.next;
		  }
		  return valRet;
	  }
}
