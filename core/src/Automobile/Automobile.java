package Automobile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.badlogic.gdx.math.MathUtils.random;


/**
 * Created by hyena on 5/20/17.
 */

public class Automobile extends Sprite {

    private static final int start_x_up_1 = 460;
    private static final int start_x_up_2 = 415;

    private static final int start_y_up = -60;


    private static final int start_x_down = 305;
    private static final int start_y_down = 480;

    private static final int start_x_left = -65;
    private static final int start_y_left = 135;

    private static final int start_x_right = 890;
    private static final int start_y_right = 285;


    private String direction;
    public boolean rotation;
    private float orientation;
    private float start_x, start_y;
    private float speed = 5;




    public Automobile (String name, String direction){
        super(new Texture(name));

        this.direction = direction;
        if (direction == "up"){
            start_x = random.nextBoolean() ? start_x_up_1 : start_x_up_2;;
            start_y = start_y_up;
            orientation = 0;
        }
        else if (direction == "down"){
            start_x = start_x_down;
            start_y = start_y_down;
            orientation = 180;
        }
        else if (direction == "left"){
            start_x = start_x_left;
            start_y = start_y_left;
            orientation = -90;
        }
        else if (direction == "right"){
            start_x = start_x_right;
            start_y = start_y_right;
            orientation = 90;
        }

        this.setPosition(start_x, start_y);
        this.setOriginCenter();
        this.rotate(orientation);


    }

    public void move(Semafor semafor_1, Semafor semafor_2) {

        if (direction == "up") {

            if (this.getY() != -25 && semafor_1.color == "red" ||
                    semafor_1.color == "green" ||
                    this.getY() != -25 && semafor_1.color == "yellow") {
                this.rotate();
                if (!this.rotation) {
                    this.setPosition(this.getX(), this.getY() + speed);

                    if (this.getY() > 500) {
                        this.setY(start_y_up);
                    }
                }
            }
        }
        else if (direction == "down"){

            if ((this.getY() != 440 && semafor_1.color == "red") ||
                    semafor_1.color == "green" ||
                    this.getY() != 440 &&semafor_1.color == "yellow") {

                this.rotate();
                if (!this.rotation) {
                    this.setPosition(this.getX(), this.getY() - speed);

                    if (this.getY() < -70) {
                        this.setY(start_y_down);
                    }
                }
            }
        }
        else if (direction == "left"){
            if (this.getX() != 155 & semafor_2.color == "red" ||
                    semafor_2.color == "green" ||
                    this.getX() != 155 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() + speed, this.getY());

                if (this.getX() > 810){
                    this.setX(start_x_left);
                }
            }
        }
        else if (direction == "right"){
            if (this.getX() != 625 & semafor_2.color == "red" ||
                    semafor_2.color == "green" ||
                    this.getX() != 625 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -90){
                    this.setX(start_x_right);
                }
            }
        }

    }

    public void rotate (){
        if (this.direction == "up"){
            if (this.getY() >= 117 && this.getRotation() != -90){
                this.rotation = true;
                this.rotate(-5);
                this.translateY(1);
                this.translateX(2);
                }
            else if (this.getRotation() == -90){
                this.setPosition(this.getX() + speed, this.getY());
                if (this.getX() > 890){
                    this.setPosition(start_x_up_1, start_y_up);
                    this.setRotation(0);
                    this.rotation = false;
                }
            }
        }
        else if (this.direction == "down"){
            if (this.getY() <= 303 && this.getRotation() != 90){
                this.rotation = true;
                this.rotate(-5);
                this.translateY(-1);
                this.translateX(-2);
            }
            else if (this.getRotation() == 90){
                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -90){
                    this.setPosition(start_x_down, start_y_down);
                    this.setRotation(180);
                    this.rotation = false;
                }
            }
        }
    }

}
