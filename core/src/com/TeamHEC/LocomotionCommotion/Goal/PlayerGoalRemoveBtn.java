package com.TeamHEC.LocomotionCommotion.Goal;

import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.SpriteButton;
import com.badlogic.gdx.graphics.Texture;
/**
 * 
 * @author Robert Precious <rp825@york.ac.uk>
 * The Remove Button has two different modes, remove or undo. 
 * Remove (if undo is false) removes the goal completely from player goals.
 * Undo (if undo is true and only available when you select a goal from the goal menu BEFORE closing.) allows you to put the goal back.
 *
 */
public class PlayerGoalRemoveBtn extends SpriteButton {
	public int index, newgoalindex;
	public boolean undo;

	public PlayerGoalRemoveBtn(float x , float y, Texture texture,int index) {
		super(x, y, texture);
		// TODO Auto-generated constructor stub
		this.index= index;
		this.undo = true;
		this.newgoalindex=0;
	}
	@Override
	protected void onClicked(){
		if (undo)
			PlayerGoals.undoGoalSelection(index);
		else
			PlayerGoals.removeGoal(index);
	}
	
	/**
	 * Gets the new goal index of the goal
	 * @return Int new goal index
	 */
	public int getnewgoalindex(){
		return this.newgoalindex;
	}
	
	/**
	 * Sets the goal index
	 * @param i
	 */
	public void setnewgoalindex(int i){
		this.newgoalindex=i;
	}
	
	/**
	 * returns if you can undo selecting the goal
	 * @return Boolean can undo
	 */
	public boolean getUndo(){
		return this.undo;
	}
	
	/**
	 * Sets if you can undo selecting the goal
	 * @param boolean undo
	 */
	public void setUndo(boolean b){
		this.undo=b;
		if (undo){
			setTexture(Game_TextureManager.getInstance().game_menuobject_redobtn);
		}
		else
			setTexture(Game_TextureManager.getInstance().game_menuobject_removegoalbtn);
	}
	
	/**
	 * resets the button texture
	 */
	public void resetButtons(){
		setTexture(Game_TextureManager.getInstance().game_menuobject_removegoalbtn);
	}

	/**
	 * Sets the redo button
	 */
	public void setRedoBtn() {
		this.undo=true;
		setTexture(Game_TextureManager.getInstance().game_menuobject_redobtn);
	}

}