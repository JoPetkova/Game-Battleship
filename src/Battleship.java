import java.util.Random;
import java.util.Scanner;

public class Battleship {

	public static void main(String[] args) {
		int[][] board = new int[5][5];
		for (int row = 0; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				board[row][column] = -1;
			}
		}	
		
		int[][] ships = new int[3][2];
		Random random = new Random();

		for (int ship = 0; ship < 3; ship++) {
			ships[ship][0] = random.nextInt(5);
			ships[ship][1] = random.nextInt(5);

			for (int last = 0; last < ship; last++) {
				if ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1])) {
					do {
						ships[ship][0] = random.nextInt(5);
						ships[ship][1] = random.nextInt(5);
					} while ((ships[ship][0] == ships[last][0]) && (ships[ship][1] == ships[last][1]));
				}
			}

		}

		int[] shot = new int[2];
		int attempts = 0; 
		int	shotHit = 0;

		do {
			showBoard(board);
			
			System.out.println();
			
			shooting(shot);
			attempts++;
			
			if (shotHit(shot, ships)) {
				hint(shot, ships, attempts);
				shotHit++;
			} else {
				hint(shot, ships, attempts);
			}
			
			System.out.println();
			System.out.println();
			changeBoard(shot, ships, board);

		} while (shotHit != 3);

		System.out.println("\n\nCongratulations!!! Battleship Java game finished! You hit 3 ships in " + attempts + " attempts.");
		System.out.println();
		showBoard(board);
	}


	public static void showBoard(int[][] board) {
		System.out.println("\t1 \t2 \t3 \t4 \t5");
		System.out.println();

		for (int row = 0; row < 5; row++) {
			System.out.print((row + 1) + " ");
			for (int column = 0; column < 5; column++) {
				if (board[row][column] == -1) {
					System.out.print("\t" + "~");
				} else { 
					if (board[row][column] == 0) {
						System.out.print("\t" + "*");
					} else {
						if (board[row][column] == 1) {
							System.out.print("\t" + "X");
						}
					}	
				}
			}
			System.out.println();
		}
		
	}

	public static void shooting(int[] shot) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the coordinates (position) of your shot: ");
		
		System.out.println("Row: ");
		shot[0] = input.nextInt();
		shot[0]--;
		while (shot[0] < 0 || shot[0] >= 5) {
			System.out.println("Invalid input! Please, enter a number between 1 and 5. ");
			System.out.println("Row: ");
			shot[0] = input.nextInt();
			shot[0]--;
		}
		
		System.out.println("Column: ");
		shot[1] = input.nextInt();
		shot[1]--;
		while (shot[1] < 0 || shot[1] >= 5) {
			System.out.println("Invalid input! Please, enter a number between 1 and 5. ");
			System.out.println("Column: ");
			shot[1] = input.nextInt();
			shot[1]--;
		}
		
	}

	public static boolean shotHit(int[] shot, int[][] ships) {
		for (int ship = 0; ship < ships.length; ship++) {
			if (shot[0] == ships[ship][0] && shot[1] == ships[ship][1]) {
				System.out.printf("You hit a ship located in (%d,%d)\n", shot[0] + 1, shot[1] + 1);
				return true;
			}
		}
		return false;
	}

	public static void hint(int[] shot, int[][] ships, int attempts) {
		int row = 0;
		int	column = 0;

		for (int line = 0; line < ships.length; line++) {
			if (ships[line][0] == shot[0]) {
				row++;
			}
			if (ships[line][1] == shot[1]) {
				column++;
			}
		}

		System.out.printf("\nHint %d: \nRow %d -> %d ships\n" + "Column %d -> %d ships\n", attempts, shot[0] + 1, row,
				shot[1] + 1, column);
	}

	public static void changeBoard(int[] shot, int[][] ships, int[][] board) {
		if (shotHit(shot, ships)) {
			System.out.println();
			board[shot[0]][shot[1]] = 1;
		} else {
			board[shot[0]][shot[1]] = 0;
		}
	}

	
}
