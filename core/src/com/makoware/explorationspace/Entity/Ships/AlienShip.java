package com.makoware.explorationspace.Entity.Ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makoware.explorationspace.ESModel;
import com.makoware.explorationspace.Entity.Player;
import com.makoware.explorationspace.Entity.Ships.Equipment.Engines.BasicEngines;
import com.makoware.explorationspace.Framework.AssetManager;

public class AlienShip extends Ship {


	private Player target;
	private float fireRate;
	private float fireTime;
	private float range;
	private double angle;

	public AlienShip(int id){
		super(id);
		sprite = new Sprite(AssetManager.get("ship"));
		sprite.flip(true, false);
		sprite.setBounds(0, 0, 1.5f, 1.5f);
		this.worth = 100;
		prop.setEngines(new BasicEngines());
		
		
		this.category = ESModel.ENEMY;
		this.mask = ESModel.PLAYER_WEAPONS;
	}
	
	public AlienShip(int id, float x, float y){
		this(id);
		sprite.setPosition(x, y);
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
	
	public void setFireRange(float r){
		this.range = r;
	}
	
	public void setFireRate(float r){
		this.fireRate = r;
	}

	public void assignTarget(Player player) {
		target = player;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		
		if(fireTime<fireRate){
			fireTime+=Gdx.graphics.getDeltaTime();
		} else {
			fireTime = 0;
			Gdx.app.log("alien", "fire "+target.getShip().getPosition().dst(getPosition()));
			if(target.getShip().getPosition().dst(getPosition())<range){
				
//				angle = Math.acos( target.getShip().getPosition().dot(getPosition()) / (target.getShip().getPosition().len()*getPosition().len()) );
//				angle = Math.atan2(target.getShip().getPosition().y-getPosition().y, target.getShip().getPosition().x-getPosition().x);
//				Gdx.app.log("alien", "fire in range "+angle);
//				this.weapons.targetVector().x = (float) (getPosition().x + (2*Math.cos(angle)));
//				this.weapons.targetVector().y = (float) (getPosition().y + (2*Math.sin(angle)));
//				this.weapons.targetVector().set(weapons.targetVector().cpy().sub(target.getShip().getPosition()));
				this.weapons.targetVector().set(target.getShip().getPosition().sub(getPosition()));
				firePrimary();
			}
		}
		
		
	}

}
