package com.makoware.explorationspace.Entity.Ships;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Entity.Ships.Equipment.Engines.BasicEngines;
import com.makoware.explorationspace.Framework.AssetManager;

public class HumanShip extends Ship {
	

	public HumanShip(int id){
		super(id);
		sprite = new Sprite(AssetManager.get("ship"));
		sprite.setBounds(0, 0, 1.5f, 1.5f);
		
		prop.setEngines(new BasicEngines());
		
		this.category = ESModel.PLAYER;
		this.mask = (ESModel.ENEMY_WEAPONS | ESModel.POWERUP | ESModel.WALL);
	}
	
	public HumanShip(int x, int y) {
		this(0);
		sprite.setPosition(x, y);
	}

	@Override
	public void addToWorld(World world) {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
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
		fdef.filter.categoryBits = this.category;
		fdef.filter.maskBits = this.mask;
		
		body.createFixture(fdef);
		body.setLinearDamping(0f);
		body.setUserData(this);
		shape.dispose();
	}

	@Override
	public void dispose() {
	}

}
