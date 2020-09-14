//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....
package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
	
		// Count of the number of cows in a given direction
		int NORTH = 0;
		int SOUTH = 1;
		int EAST = 2;
		int WEST = 3;
		
		int[] counts = {0, 0, 0, 0};   // N, S, E, W

		// An array of 4 arrays.  
		// This holds the most recent cow found in each direction (N, S, E, W)
		int[][] all = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
		
		for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[y].length; x++) {
				if (field[y][x] == 'c') {
					// Check the direction of the cow (be careful to stay in bounds!
					if (y >= 2 && field[y - 1][x] == 'o' && field[y - 2][x] == 'w') {
						counts[SOUTH] = counts[SOUTH] + 1;
						all[SOUTH] = new int[] { x, y };
					} else if (y < field.length - 2 && field[y + 1][x] == 'o' && field[y + 2][x] == 'w') {
						counts[NORTH] = counts[NORTH] + 1;
						all[NORTH] = new int[] { x, y };
					} else if (x >= 2 && field[y][x - 1] == 'o' && field[y][x - 2] == 'w') {
						counts[EAST] = counts[EAST] + 1;
						all[EAST] = new int[] { x, y };
					} else if (x < field[y].length - 2 && field[y][x + 1] == 'o' && field[y][x + 2] == 'w') {
						counts[WEST] = counts[WEST] + 1;
						all[WEST] = new int[] { x, y };
					}
				}
			}
		}
		
		// Which direction has the smallest number of cows?
		int[] oddCow = null;
		int count = Integer.MAX_VALUE;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0 && counts[i] < count) {
				count = counts[i];
				oddCow = all[i];
			}
		}
		System.out.println(oddCow[0] + ", " + oddCow[1]);
		return oddCow;
	}
}
