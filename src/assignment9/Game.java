package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	private Snake snake;
	private Food food;
	
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
	}
	
	public void play() {
		while (snake.isInbounds()==true) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
		//	System.out.println("Keypress: " + dir);
			if(dir !=-1) { //1
				snake.changeDirection(dir);
			}
			snake.move(); //2
			if(snake.eatFood(food)==true) { //3
				food = new Food();
			}
			updateDrawing(); //4
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
		gameOver();
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
		StdDraw.clear();
		snake.draw();
		food.draw();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.7);
		StdDraw.text(0.1, 0.1, "Score: " + snake.getScore());
		StdDraw.pause(50);
		StdDraw.show();
	}
	
	private void gameOver() {
		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.7);
		StdDraw.text(0.5, 0.5, "GAME OVER");
		StdDraw.text(0.5, 0.3, "Score: " + snake.getScore());
		StdDraw.show();
		StdDraw.pause(20000);
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
