package com.algo.bst;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Test {
  public static void main(String[] args) {
      Node n1 = new Node("1", "NY", null);
      Node n2 = new Node("2", "CA", "1");
      Node n3 = new Node("4", "MA", "1");
      Node n4 = new Node("7", "NC", "2");
      List<Node> list = new ArrayList<Node>();
      list.add(n1);
      list.add(n2);
      list.add(n3);
      printNodesInOrder(list);
      
  }
  
  static class Node {
    String id;
    String name;
    String parentId;
    public Node(String id, String name, String pid) {
      this.id=id;
      this.name=name;
      this.parentId=pid;
  }
  }
  
  public static void printNodesInOrder(List<Node> list) {
    
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for(Node node: list) {
      if(map.get(node.parentId)!=null) {
        List<String> children=map.get(node.parentId);
        children.add(node.id);
      }
      else {
         List<String> childrenList = new ArrayList<String>();
         childrenList.add(node.id);
         if(node.parentId==null) map.put("root", childrenList);
         else map.put(node.parentId, childrenList);
        }
      }
     int level = 0;
     dfs(map, "root", 0);
    
  }
  
  public static void dfs(Map<String, List<String>> map, String parentId, int level) {     
          List<String> childrenlist = map.get(parentId);
          if(childrenlist==null) return;      
          String space="";
          for(int i = 0; i <= level; i++) space+=" ";           
          System.out.println(space+parentId);
          level++;   
          for(String str : childrenlist) {
              dfs(map, str, level); 
          }
   }
}
