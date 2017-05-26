package Automobile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.badlogic.gdx.math.MathUtils.random;


/**
 * Created by ira on 5/25/17.
 */

public class Pedestrian extends Sprite {

    private static final int start_x_up_right = 550;
    private static final int start_x_up_left = 250;

    private static final int start_y_up = -5;

    private static final int start_x_down_right = 520;
    private static final int start_x_down_left = 230;

    private static final int start_y_down = 480;


    private static final int start_y_left_upper = 360;
    private static final int start_y_left_lower = 60;

    private static final int start_x_left = -10;

    private static final int start_y_right_upper = 390;
    private static final int start_y_right_lower = 90;

    private static final int start_x_right = 800;


    private String direction;
    private float orientation;
    private float start_x, start_y;
    public float speed = 1;

    private int view = 1;
    public boolean walkFlag = true;
    public boolean deletePedestrian = false;
    public boolean hitted = false;


    public Pedestrian (String name, String direction, int speed){
        super(new Texture(name));

        this.speed = speed;
        this.deletePedestrian = false;

        this.direction = direction;
        if (direction == "up"){
            start_x = random.nextBoolean() ? start_x_up_right : start_x_up_left;
            start_y = start_y_up;
            orientation = 0;
        }
        else if (direction == "down"){
            start_x = random.nextBoolean() ? start_x_down_right : start_x_down_left;
            start_y = start_y_down;
            orientation = 180;
        }
        else if (direction == "left"){
            start_x = start_x_left;
            start_y = random.nextBoolean() ? start_y_left_upper : start_y_left_lower;
            orientation = -90;
        }
        else if (direction == "right"){
            start_x = start_x_left;
            start_y = random.nextBoolean() ? start_y_right_upper : start_y_right_lower;
            orientation = 90;
        }

        this.setPosition(start_x, start_y);
        this.setOriginCenter();
        this.rotate(orientation);

    }

    public void move(Semafor semafor_1, Semafor semafor_2) {


        if (direction == "up" && !hitted) {

            if (this.getY() != 110 && semafor_1.color == "red" || semafor_1.color == "green" ||
                    this.getY() != 110 && semafor_1.color == "yellow") {

                    walkFlag = true;

                    this.setPosition(this.getX(), this.getY() + speed);

                    if (this.getY() > 500) {
                        this.deletePedestrian = true;
                    }
            }
            else{
                walkFlag = false;
            }
        }

        else if (direction == "down" && !hitted) {

            if ((this.getY() != 340 && semafor_1.color == "red") || semafor_1.color == "green" ||
                    this.getY() != 340 && semafor_1.color == "yellow") {

                walkFlag = true;
                this.setPosition(this.getX(), this.getY() - speed);

                if (this.getY() < -70) {
                    this.deletePedestrian = true;
                }
            } else {
                walkFlag = false;
            }
        }

        else if (direction == "left" && !hitted){
            if (this.getX() != 270 & semafor_2.color == "red" || semafor_2.color == "green" ||
                    this.getX() != 270 && semafor_2.color == "yellow") {

                walkFlag = true;
                this.setPosition(this.getX() + speed, this.getY());

                if (this.getX() > 810){
                    this.deletePedestrian = true;
                }
            }
            else{
                walkFlag = false;
            }
        }
        else if (direction == "right" && !hitted){
            if (this.getX() != 500 & semafor_2.color == "red" || semafor_2.color == "green" ||
                    this.getX() != 500 && semafor_2.color == "yellow") {

                walkFlag = true;
                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -90){
                    this.deletePedestrian = true;
                }
            }
            else{
                walkFlag = false;
            }

        }

    }

    public void walk(){
        if(walkFlag) {
            if (view == 1) {

                this.setTexture(new Texture("2.png"));
                view = 2;
            } else if (view == 2) {

                this.setTexture(new Texture("3.png"));
                view = 3;
            } else if (view == 3) {

                this.setTexture(new Texture("4.png"));
                view = 4;
            } else if (view == 4) {

                this.setTexture(new Texture("1.png"));
                view = 1;
            }
        }
        else{
            this.setTexture(new Texture("1.png"));
        }
    }

}
