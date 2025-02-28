package pt.upskill.projeto1.rogue.utils;

import java.io.Serializable;

/**
 * @author POO2016
 *
 * Cardinal directions
 *
 */
public enum Direction implements Serializable {
	LEFT, UP, RIGHT, DOWN;

	public Vector2D asVector() {
		//recebe direção e converte em vetor
		if(this==Direction.UP){
			return new Vector2D(0,-1);
		}
		if(this==Direction.DOWN){
			return new Vector2D(0,1);
		}
		if(this==Direction.LEFT){
			return new Vector2D(-1,0);
		}
		if(this==Direction.RIGHT){
			return new Vector2D(1,0);

		}
		return null;
	}
}
