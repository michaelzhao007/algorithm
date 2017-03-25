package com.algo.union;

public class LinearProbHashMap<Key, Value> {
	
	private Key[] keys;
	private Value[] values;
	private int m;
	
	public LinearProbHashMap(int initCap) {
		m = initCap;
		keys = (Key[]) new Object[initCap];
		values = (Value[]) new Object[initCap];
	}
	
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % m;
	}
	
	public void put(Key key, Value val) {
		int idx = hash(key);
		if(keys[idx] == null)  {
			keys[idx] = key;
		    values[idx] = val;
		}
		while(keys[idx]!=null) {
			idx++;
		}
		keys[idx] = key;
		values[idx] = val;
	}
	
	public Value get(Key key) {
		int idx = hash(key);
		while(keys[idx]!=key) {
			idx++;
		}
		return values[idx];
	}
	
	public static void main(String[] args) {
		LinearProbHashMap<Integer, String> map  =  new LinearProbHashMap<Integer, String>(20);
		map.put(1, "ha");
		map.put(35, "xixi");
		System.out.println(map.get(1));
	}

}
