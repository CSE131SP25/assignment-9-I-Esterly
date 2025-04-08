package assignment9;

import java.awt.Color;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int score;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		segments = new LinkedList<>();
		deltaX = 0;
		deltaY = 0;
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		score =0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment head = segments.get(0);
		for (int i = segments.size() - 1; i > 0; i--) {
	        BodySegment current = segments.get(i);
	        BodySegment previous = segments.get(i - 1);

	        // Move current to previous position
	        current.setX(previous.getX());
	        current.setY(previous.getY());
	    }

	    // updates head
	  //  BodySegment head = segments.get(0);
	    head.setX(head.getX() + deltaX);
	    head.setY(head.getY() + deltaY);
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for(int i=0; i<segments.size(); i++) {
			segments.get(i).draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.get(0);
		BodySegment tail = segments.get(segments.size()-1);
		double dis = Math.sqrt(Math.pow(head.getX()-f.getX(),2) + Math.pow(head.getY()-f.getY(),2));
		if (dis<=(head.getSize()*2)) {
			segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			score++;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.get(0);
		if((head.getX()>0 && head.getX()<1) && (head.getY()>0 && head.getY()<1)){
			return true;
		}
		return false;
	}

	public int getScore() {
		return score;
	}
	
}
