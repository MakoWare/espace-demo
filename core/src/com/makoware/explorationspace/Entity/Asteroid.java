package com.makoware.explorationspace.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Framework.AssetManager;

public class Asteroid extends SpaceObject {
	
	
	public Asteroid(float x, float y){
		super();
		
		this.resistance = 0.4f;
		this.worth = 10;
		
		sprite = new Sprite(AssetManager.get("star"));
		sprite.setColor(Color.GRAY);
		sprite.setBounds(x, y, 2, 2);
	}

	@Override
	public void addToWorld(World world) {
		BodyDef def = new BodyDef();
		def.type = BodyType.KinematicBody;
		def.position.set(sprite.getX(), sprite.getY());
		def.fixedRotation = true;
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2f,sprite.getHeight()/2f);
//		shape.set(new float[]{0f,0.05f,0.025f,0.025f,0.225f,0.025f,0.3f,0.05f,0.3f,0.7f,0.25f,0.75f,0.05f,0.75f,0f,0.7f});
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;	
		fdef.filter.categoryBits = ESModel.ENEMY;
		fdef.filter.maskBits = (ESModel.PLAYER_WEAPONS);
		
		body.createFixture(fdef);
		body.setLinearDamping(0f);
		body.setUserData(this);
		shape.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		Vector2 v = body.getPosition();
		sprite.setPosition(v.x-(sprite.getWidth()/2f), v.y-(sprite.getHeight()/2f));
		sprite.draw(batch);
		
		super.draw(batch);
	}

	@Override
	public void dispose() {
	}

}
