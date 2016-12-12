package gdx.movement;
//package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

public class GdxMovement extends ApplicationAdapter {

    private static final int COLS = 4;
    private static final int ROWS = 4;
    SpriteBatch batch;
    Texture Sprite;
    Texture BackGround;
    TextureRegion[] frames;
    TextureRegion CurrentFrame;
    float SpriteX = 0;
    float SpriteY = 0;
    float SpriteSpeed = 150f;
    float Time = 0f;
    Animation animation;

    @Override
    public void create() {
        batch = new SpriteBatch();
        BackGround = new Texture(Gdx.files.internal("Moo.jpg"));
        Sprite = new Texture(Gdx.files.internal("Chicken.png"));
        TextureRegion[][] tmp = TextureRegion.split(Sprite, Sprite.getWidth() / COLS, Sprite.getHeight() / ROWS);
        frames = new TextureRegion[COLS * ROWS];
        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(1f, frames);
    }

    @Override
    public void render() {
        int nX, nY;
        int nH = Gdx.graphics.getHeight(), nW = Gdx.graphics.getWidth();
        if (Time < 4) {
            Time += Gdx.graphics.getDeltaTime();
        } else {
            Time = 0;
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        CurrentFrame = animation.getKeyFrame(0);
        
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            SpriteX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
            CurrentFrame = animation.getKeyFrame(8 + Time);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            SpriteX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
            CurrentFrame = animation.getKeyFrame(12 + Time);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            SpriteY += Gdx.graphics.getDeltaTime() * SpriteSpeed;
            CurrentFrame = animation.getKeyFrame(4 + Time);
        }
            //else if(nH == ContainY(nX, nY)){
                
        //}
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            SpriteY -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
            CurrentFrame = animation.getKeyFrame(0 + Time);
            //else if(nH == ContainY(nX, nY)){
        }

        batch.begin();
        batch.draw(BackGround, 0, 0, nW, nH);
        batch.draw(CurrentFrame, (int) SpriteX, (int) SpriteY);
        batch.end();
        System.out.println(SpriteX);
        System.out.println(SpriteY);
    }

    public boolean ContainX(int nXMin, int nXMax) {
       if (SpriteX >= 1200) {
            SpriteSpeed = 0f;
        } else if (SpriteX <= 0) {
            SpriteSpeed = 0f;
        }
        return false;
    }public boolean ContainY(int nYMin, int nYMax){
         if (SpriteY >= 800) {
            SpriteSpeed = 0f;
        } else if (SpriteY <= 0) {
            SpriteSpeed = 0f;
        }
        return false;
    }
}
//Left wall: X= -5 Y = 0 to 425
//Right wall: X = 600 Y = 0 to 425
//Bottom: X = 5 to 600 Y = -5
//Top