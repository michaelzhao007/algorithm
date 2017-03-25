package com.algo.union;

public class RansomNotes {
	public boolean canRansom(String mag, String ran) {
		String[] mags = mag.split(" ");
		String[] rans = ran.split(" ");
		java.util.HashMap<String, Integer> ranMap = new java.util.HashMap();
		for(String ra: rans) {
			ranMap.put(ra, 1);
		}
		for(String ma: mags) {
			if(ranMap.containsKey(ma)) {
				ranMap.put(ma, ranMap.get(ma)-1);
			}
		}
		for(int res: ranMap.values()) {
			if(res>0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		RansomNotes r = new RansomNotes();
		System.out.println(r.canRansom("This is a beautiful day with wonderful people", "people is beautiful ok"));
	}

}
