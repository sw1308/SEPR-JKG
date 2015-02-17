package com.TeamHEC.LocomotionCommotion.Scene;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

/**
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 */

public class Scene implements Screen{
	
	public Stage stage;
	public static Camera camera;
	public static int screenX = 1680;
	public static int screenY = 1050;
	
	public static Array<Actor> actors;
	
	public Scene()
	{
		stage = new Stage();
		camera = stage.getCamera();
		camera.viewportHeight= screenY;
		camera.viewportWidth= screenX;
		camera.update();
       
		actors = new Array<Actor>();
	}
	/**
	 *  Adds the actors to the scene for all actors 
	 */
	public void addToStage()
	{
		for (Actor a : actors)
		{
			a.setTouchable(Touchable.enabled);
			stage.addActor(a);
		}
		
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Removes actors from the scene for all actors
	 */
	public void removeFromStage()
	{
		for (Actor a : actors)
		{
			stage.getActors().removeValue(a, true);
		}
	}
	
	/**
	 * 
	 * @param visible boolean value denoting the visiblilty the actor is becoming
	 */
	public void setVisibility(boolean visible)
	{
		for (Actor a : actors)
		{
			a.setVisible(visible);
		}
	}
	
	/**
	 * 
	 * @param touchable boolean value denoting the interactiblity of an actor
	 */
	public void setActorsTouchable(boolean touchable)
	{
		for (Actor a : actors)
		{
			if(touchable)
				a.setTouchable(Touchable.enabled);
			else
				a.setTouchable(Touchable.disabled);
		}
	}

	/**
	 * Can be used to change or animate the cameras position
	 * @param x New x coordinate
	 * @param y New y coordinate
	 */
	public void changeCam(int x,int y)
	{
		stage.getCamera().translate(x, y, 0);
	}

	/**
	 *  Draws an actor onto screen
	 */
	@Override
	public void render(float delta) {
		stage.getCamera().update();
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	/**
	 * Changes the size of an actor in the scene
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);	
		LocomotionCommotion.screenX = width;
		LocomotionCommotion.screenY = height;
	}

	/**
	 * Clears the stage
	 */
	@Override
	public void dispose()
	{
		stage.clear();
	}

	@Override
	public void show()
	{
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
	}
}
