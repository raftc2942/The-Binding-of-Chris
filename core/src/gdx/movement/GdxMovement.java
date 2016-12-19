package gdx.movement;


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
    float SpriteX = 550;
    float SpriteY = 400;
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

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (isHitLeft(SpriteX, SpriteY)) {
                SpriteSpeed = 150f;
                SpriteX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                CurrentFrame = animation.getKeyFrame(8 + Time);
            } else {
                SpriteSpeed = 0f;
                SpriteX += 1;
                SpriteSpeed = 150f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (isHitRight(SpriteX, SpriteY)) {
                SpriteX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                CurrentFrame = animation.getKeyFrame(12 + Time);
            } else {
                SpriteSpeed = 0f;
                SpriteX -= 1;
                SpriteSpeed = 150f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (isHitTop(SpriteX, SpriteY)) {
                SpriteY += Gdx.graphics.getDeltaTime() * SpriteSpeed;
                CurrentFrame = animation.getKeyFrame(4 + Time);
            } else {
                SpriteSpeed = 0f;
                SpriteY -= 1;
                SpriteSpeed = 150f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (isHitBottom(SpriteX, SpriteY)) {
                SpriteY -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
                CurrentFrame = animation.getKeyFrame(0 + Time);
                //else if(nH == ContainY(nX, nY)){
            } else {
                SpriteSpeed = 0f;
                SpriteY += 1;
                SpriteSpeed = 150f;
            }
        }
        batch.begin();
        batch.draw(BackGround, 0, 0, nW, nH);
        batch.draw(CurrentFrame, (int) SpriteX, (int) SpriteY);
        batch.end();
    }

    public boolean isHitLeft(float SpriteX, float SpriteY) {
        if (SpriteX <= 42 && SpriteY >= -10) {
            return false;
        }
        return true;
    }

    public boolean isHitRight(float SpriteX, float SpriteY) {
        if (SpriteX >= 1115 && SpriteY >= -10) {
            return false;
        }
        return true;
    }

    public boolean isHitTop(float SpriteX, float SpriteY) {
        if (SpriteX >= -10 && SpriteY >= 712) {
            return false;
        }
        return true;
    }

    public boolean isHitBottom(float SpriteX, float SpriteY) {
        if (SpriteX >= -10 && SpriteY <= 63) {
            return false;
        }
        return true;
    }
}
