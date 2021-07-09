import java.awt.*;
import java.util.Collection;
import java.sql.Array;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}

	public int getHeight() {
		return 250;
	}

	public int getXPosition() {
		return 200;
	}

	public int getYPosition() {
		return 200;
	}

	public String getTitle() {
		return "Number Puzzle";
	}

	public int getShuffleButtonFontSize() {
		return 12;
	}

	public int getNumbersFontSize() {
		return 12;
	}

	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}

	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game) {
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();

		int clickCellId = 0;
		for (int i = 0; i <= 15; i++) { // We traverse the buttons array to find at which index is the clicked button
			if (buttons[i].getLabel() == buttonClicked.getLabel())
				clickCellId = i;
		}

		// Now we calculate the row and column for empty and clicked cell
		int clickRow = clickCellId / 4;
		int clickCol = clickCellId % 4;
		int emptyRow = emptyCellId / 4;
		int emptyCol = emptyCellId % 4;
		int rowDiff = Math.abs(clickRow - emptyRow);
		int colDiff = Math.abs(clickCol - emptyCol);

		// If the clicked cell is not adjacent or is on diagonal to empty cell then do
		// nothing and return older emptycell value
		if (rowDiff > 1 || colDiff > 1 || (rowDiff > 0 && colDiff > 0))
			return emptyCellId;

		// Else if
		swapButton(buttons[emptyCellId], buttonClicked);
		System.out.println();
		emptyCellId = clickCellId;
		return clickCellId;

		// Your logic here

	}

	public int[] getRandomNumbersForGrid() {
		ArrayList<Integer> a = new ArrayList<Integer>(15);
		for (int i = 1; i <= 15; i++) {
			a.add(i);
		}

		Collections.shuffle(a);

		int arr[] = new int[15];
		for (int i = 0; i < 15; i++) {
			arr[i] = a.get(i);
		}

		return arr;
	}

	public boolean checkForWinner(Button[] buttons) {
		boolean winner = true;

		// Your Logic here
		int arr[] = getIntegerArrayOfButtonIds(buttons);
		for (int i = 0; i < 15; i++) {
			if (arr[i] != i + 1) {
				winner = false;
			}
		}

		return winner;
	}
}