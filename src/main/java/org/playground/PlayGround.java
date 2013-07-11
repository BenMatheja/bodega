package org.playground;

import java.util.ArrayList;
import java.util.Random;

public class PlayGround {
	public static void main(String[] args){
		Random randomGenerator = new Random();
		int fin = randomGenerator.nextInt(50);
		ArrayList<String> al = new ArrayList<String>();
		for(int i=0; i<50; i++){
			al.add("Test"+String.valueOf(i));
		}
		System.out.println(al.get(fin));
	}

}
