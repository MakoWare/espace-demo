package com.makoware.explorationspace.Entity.Powerup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Entity.Entity;

public abstract class Powerup extends Entity{
	
	protected Sprite sprite;
	
	protected boolean isDead;
	
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition(body.getPosition().x-sprite.getWidth()/2, body.getPosition().y-sprite.getHeight()/2);
		sprite.draw(batch);
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
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;	
		fdef.isSensor = true;
		fdef.filter.categoryBits = ESModel.POWERUP;
		fdef.filter.maskBits = (ESModel.PLAYER);
		
		body.createFixture(fdef);
		body.setLinearDamping(0f);
		body.setUserData(this);
		shape.dispose();
	}

	@Override
	public void removeFromWorld() {
		ESModel.world.destroyBody(body);
	}
	
	@Override
	public void dispose() {
		isDead = true;
	}
	
	@Override
	public boolean isDead() {
		return isDead;
	}
}
