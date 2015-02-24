package com.makoware.explorationspace;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.Entity.Entity;

public class Wall extends Entity {
	
	
	private float x;
	private float y;
	private float w;
	private float h;

	public Wall(float x, float y, float w, float h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public void draw(SpriteBatch batch) {
	}

	@Override
	public void addToWorld(World world) {
		BodyDef def = new BodyDef();
		def.type = BodyType.StaticBody;
		def.position.set(x, y);
		def.fixedRotation = true;
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(w/2f,h/2f);
//		shape.set(new float[]{0f,0.05f,0.025f,0.025f,0.225f,0.025f,0.3f,0.05f,0.3f,0.7f,0.25f,0.75f,0.05f,0.75f,0f,0.7f});
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;	
		fdef.filter.categoryBits = ESModel.WALL;
		fdef.filter.maskBits = (ESModel.PLAYER);
		
		body.createFixture(fdef);
		body.setLinearDamping(0f);
		body.setUserData(this);
		shape.dispose();
	}
	
	public void setPosition(float x, float y){
		this.body.setTransform(x, y, body.getAngle());
	}

	@Override
	public void removeFromWorld() {
	}

	@Override
	public void dispose() {
	}

	public void setX(float x) {
		this.setPosition(x, body.getPosition().y);
	}

}
