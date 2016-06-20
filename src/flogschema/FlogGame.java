package flogschema;

import java.util.Random;

public abstract class FlogGame {

	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	

	// binary search
	
	public static int getLetterValue(int[] array, char key) {

		int low = 0;
		int high = array.length - 1;
	
		while (low <= high) {

			int mid = low + (high - low) / 2;

			if (key < array[mid]) {
				high = mid - 1;
			} else if (key > array[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
