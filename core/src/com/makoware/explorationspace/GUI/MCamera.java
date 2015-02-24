package com.makoware.explorationspace.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pools;
import com.makoware.explorationspace.Entity.Entity;

public class MCamera extends OrthographicCamera{
	public final static String tag = "MCamera";
	
	private float topPad;
	private float bottomPad;
	private float leftPad;
	private float rightPad;
	
	private boolean doChase;
	private Entity chaseEnt;

	private float mBoundsXMin;
	private float mBoundsXMax;
	private float mBoundsYMin;
	private float mBoundsYMax;
	private float mBoundsWidth;
	private float mBoundsHeight;
	private float mBoundsCenterY;
	private float mBoundsCenterX;

	private boolean bounded;
	
	private boolean doMario;
	
	public MCamera(){
		super();
		this.topPad = 0f;
		this.bottomPad = 0f;
		this.leftPad = 0f;
		this.rightPad = 0f;
		bounded = false;
	}
	


	public void setChaseEntity(Entity ent) {
		if(ent==null)
			setChaseEnable(false);
		else
			this.chaseEnt = ent;
	}

	public void setChaseEnable(boolean doChase) {
		this.doChase = doChase;
	}
	
	public void setPadding(float pad){
		this.topPad = pad;
		this.bottomPad = pad;
		this.leftPad = pad;
		this.rightPad = pad;
	}
	
	public void setPadding(float top, float bottom, float left, float right){
		this.topPad = top;
		this.bottomPad = bottom;
		this.leftPad = left;
		this.rightPad = right;
	}
	
	public void onUpdate(){

		if(doChase){
			Vector2 pos = chaseEnt.getPosition();

			
//			Gdx.app.log(tag, "x="+pos.x+" y="+pos.y);
			if(bounded){
				setCenter(pos.x, pos.y);
				ensureInBounds();
			} else if(doMario){
//				this.mBoundsXMin = this.getXMin();
//				this.mBoundsXMax = viewportWidth*2;
				setBounds(this.getXMin(), 0, this.getXMax()+10, this.viewportHeight);
//				Gdx.app.log(tag, "do mario");
				setCenter(pos.x, pos.y);
				ensureInBounds();
			} else {
				setCenter(pos.x, pos.y);
			}
			update();
		}
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public void setBounded(boolean b){
		bounded = b;
	}
	
	public void setBounds(Rectangle bounds){
		if(bounds==null){
			bounded = false;
		} else {
			bounded = true;
			setBounds(bounds.x, bounds.y, bounds.x+bounds.width, bounds.y+bounds.height);
		}
		
	}
	
	public void setBounds(final float pBoundsXMin, final float pBoundsYMin, final float pBoundsXMax, final float pBoundsYMax) {
		this.mBoundsXMin = pBoundsXMin;
		this.mBoundsXMax = pBoundsXMax;
		this.mBoundsYMin = pBoundsYMin;
		this.mBoundsYMax = pBoundsYMax;

		this.mBoundsWidth = this.mBoundsXMax - this.mBoundsXMin;
		this.mBoundsHeight = this.mBoundsYMax - this.mBoundsYMin;

		this.mBoundsCenterX = this.mBoundsXMin + this.mBoundsWidth * 0.5f;
		this.mBoundsCenterY = this.mBoundsYMin + this.mBoundsHeight * 0.5f;
	}
	
	protected void ensureInBounds() {
		final float centerX;
//		Gdx.app.log(tag, "ensure: bw: "+mBoundsWidth+" vw: "+viewportWidth);
		if(this.mBoundsWidth < this.viewportWidth) {
			centerX = this.mBoundsCenterX;
		} else {
			centerX = getBoundedX(this.position.x);
		}
		final float centerY;
		if(this.mBoundsHeight < this.viewportHeight) {
			centerY = this.mBoundsCenterY;
		} else {
			centerY = getBoundedY(this.position.y);
		}
		setCenter(centerX, centerY);
	}

	private void setCenter(float centerX, float centerY) {
		this.position.x = centerX;
		this.position.y = centerY;
	}

	protected float getBoundedX(final float pX) {
		final float minXBoundExceededAmount = this.mBoundsXMin - this.getXMin();
		final boolean minXBoundExceeded = minXBoundExceededAmount > 0;

		final float maxXBoundExceededAmount = this.getXMax() - this.mBoundsXMax;
		final boolean maxXBoundExceeded = maxXBoundExceededAmount > 0;
//		Gdx.app.log(tag, "getbounded x; min ammt: "+minXBoundExceededAmount+" max ammt: "+maxXBoundExceededAmount);
		if(minXBoundExceeded) {
			if(maxXBoundExceeded) {
				/* Min and max X exceeded. */
//				Gdx.app.log(tag, "min and max");
				return pX - maxXBoundExceededAmount + minXBoundExceededAmount;
			} else {
				/* Only min X exceeded. */
//				Gdx.app.log(tag, "min");
				return pX + minXBoundExceededAmount;
			}
		} else {
			if(maxXBoundExceeded) {
				/* Only max X exceeded. */
//				Gdx.app.log(tag, "max");
				return pX - maxXBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
//				Gdx.app.log(tag, "none");
				return pX;
			}
		}
	}

	public float getXMax() {
		return (position.x+viewportWidth/2f);
	}

	public float getXMin() {
		return (position.x-viewportWidth/2f);
	}

	protected float getBoundedY(final float pY) {
		final float minYBoundExceededAmount = this.mBoundsYMin - this.getYMin();
		final boolean minYBoundExceeded = minYBoundExceededAmount > 0;

		final float maxYBoundExceededAmount = this.getYMax() - this.mBoundsYMax;
		final boolean maxYBoundExceeded = maxYBoundExceededAmount > 0;
//		Gdx.app.log(tag, "getbounded y");
		if(minYBoundExceeded) {
			if(maxYBoundExceeded) {
				/* Min and max Y exceeded. */
				return pY - maxYBoundExceededAmount + minYBoundExceededAmount;
			} else {
				/* Only min Y exceeded. */
				return pY + minYBoundExceededAmount;
			}
		} else {
			if(maxYBoundExceeded) {
				/* Only max Y exceeded. */
				return pY - maxYBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pY;
			}
		}
	}

	public float getYMax() {
		return (position.y+viewportHeight/2f);
	}

	public float getYMin() {
		return (position.y-viewportHeight/2f);
	}
	
	/* ############################################
	 * begin section for mario style scrolling
	 * ############################################
	 */
	
	public void setMarioStyle(boolean b) {
		this.doMario = b;
		setBounds(0,0,2*viewportWidth,viewportHeight);
	}
	
	public void setMarioStyle(boolean b, float height){
		setMarioStyle(b);
		setBounds(0, 0, viewportWidth, height);
	}
	
	/* ############################################
	 * end section for mario style scrolling
	 * ############################################
	 */
	
	
	/* ############################################
	 * begin section for paralax scrolling
	 * ############################################
	 */
	private Matrix4 parallaxView = new Matrix4();
	private Matrix4 parallaxCombined = new Matrix4();
	private Vector3 tmp = new Vector3();
	private Vector3 tmp2 = new Vector3();
	private Vector2 center = new Vector2();
	
	private float paraX=1f;
	private float paraY=1f;

	public Matrix4 calculateParallaxMatrix (float parallaxX, float parallaxY) {
		update();
		tmp.set(position);
		tmp.x *= parallaxX;
		tmp.y *= parallaxY;
		
		paraX = parallaxX;
		paraY = parallaxY;

		parallaxView.setToLookAt(tmp, tmp2.set(tmp).add(direction), up);
		parallaxCombined.set(projection);
		Matrix4.mul(parallaxCombined.val, parallaxView.val);
		center.set(tmp.x, tmp.y);
		return parallaxCombined;
	}
	
	public Vector2 getCenterVector2(){
		return center;
	}
	
	public Vector2 getParalaxCenter(){
		return Pools.obtain(Vector2.class).set(tmp.x,tmp.y);
	}
	
	public Vector2 getParalaxCenter(float para){
		return getParalaxCenter(para, para);
	}
	
	public Vector2 getParalaxCenter(float paraX, float paraY){
		calculateParallaxMatrix(paraX, paraY);
		return Pools.obtain(Vector2.class).set(tmp.x,tmp.y);
	}
	
	public float getParaXMin(){
		return tmp.x-(viewportWidth/2f);
	}
	
	public float getParaXMax(){
		return tmp.x+(viewportWidth/2f);
	}
	
	public float getParaYMin(){
		return tmp.y-(viewportHeight/2f);
	}
	
	public float getParaYMax(){
		return tmp.y+(viewportHeight/2f);
	}
	/* ############################################
	 * end section for paralax scrolling
	 * ############################################
	 */

}
