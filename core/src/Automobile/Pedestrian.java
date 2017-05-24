package Automobile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by ira on 5/25/17.
 */

public class Pedestrian extends Sprite {

    private static final int start_x_up_right = 510;
    private static final int start_x_up_left = 510;

    private static final int start_y_up = 10;

    private static final int start_x_down_right = 510;
    private static final int start_x_down_left = 510;

    private static final int start_y_down = 10;


    private static final int start_y_right_upper = 305;
    private static final int start_y_right_lower = 305;

    private static final int start_x_right = 480;

    private static final int start_y_left_upper = -65;
    private static final int start_y_left_lower = 135;

    private static final int start_x_left = 890;


    private String direction;
    private float orientation;
    private float start_x, start_y;
    private float speed = 5;

    private int view = 1;

    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames = new TextureRegion[4];

    walkFrames[0] = new TextureRegion(new Texture("1.png"));
    walkFrames[1] = new TextureRegion(new Texture("2.png"));
    walkFrames[2] = new TextureRegion(new Texture("3.png"));
    walkFrames[3] = new TextureRegion(new Texture("4.png"));

    walkAnimation = new Animation<TextureRegion>(0.25f, walkFrames);



    public Pedestrian (String name, String direction){
        super(new Texture(name));

        this.direction = direction;
        if (direction == "up"){
            //start_x = random.nextBoolean() ? start_x_up_right : start_x_up_left;
            start_x = start_x_up_right;
            start_y = start_y_up;
            orientation = 0;
        }
        else if (direction == "down"){
            //start_x = random.nextBoolean() ? start_x_down_right : start_x_down_left;
            start_x = start_x_down_right;
            start_y = start_y_down;
            orientation = 180;
        }
        else if (direction == "left"){
            start_x = start_x_right;
            start_y = start_y_right_upper;
            //start_y = random.nextBoolean() ? start_y_right_upper : start_y_right_lower;
            orientation = -90;
        }
        else if (direction == "right"){
            start_x = start_x_left;
            start_y = start_y_left_upper;
            //start_y = random.nextBoolean() ? start_y_left_upper : start_y_left_lower;
            orientation = 90;
        }

        this.setPosition(start_x, start_y);
        this.setOriginCenter();
        this.rotate(orientation);

    }

    public void move(Semafor semafor_1, Semafor semafor_2) {

        if (direction == "up") {

            if (this.getY() != -25 && semafor_1.color == "red" || semafor_1.color == "green" ||
                    this.getY() != -25 && semafor_1.color == "yellow") {

                    this.setPosition(this.getX(), this.getY() + speed);

                    if (this.getY() > 500) {
                        this.setY(start_y_up);
                    }
                }
            }

        else if (direction == "down"){

            if ((this.getY() != 440 && semafor_1.color == "red") || semafor_1.color == "green" ||
                    this.getY() != 440 &&semafor_1.color == "yellow") {


                    this.setPosition(this.getX(), this.getY() - speed);

                    if (this.getY() < -70) {
                        this.setY(start_y_down);
                    }
                }
            }

        else if (direction == "left"){
            if (this.getX() != 155 & semafor_2.color == "red" || semafor_2.color == "green" ||
                    this.getX() != 155 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() + speed, this.getY());

                if (this.getX() > 810){
                    this.setX(start_x_left);
                }
            }
        }
        else if (direction == "right"){
            if (this.getX() != 625 & semafor_2.color == "red" || semafor_2.color == "green" ||
                    this.getX() != 625 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -90){
                    this.setX(start_x_right);
                }
            }
        }

    }





}
