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

    private static final int start_y_up = -65;


    private static final int start_x_down_1 = 305;
    private static final int start_x_down_2 = 365;

    private static final int start_y_down = 480;


    private static final int start_x_left = -65;

    private static final int start_y_left_1 = 135;
    private static final int start_y_left_2 = 185;


    private static final int start_x_right = 890;

    private static final int start_y_right_1 = 285;
    private static final int start_y_right_2 = 230;



    public String direction;
    public String direction_turn;
    public boolean rotation;
    private float orientation;
    private float start_x, start_y;
    public float speed = 10;
    public int counter_car = 0;
    public boolean accident = false;
    public boolean not_hit = true;
    public boolean hitted = false;


    public Automobile(){
        direction = "";
        rotation = false;
        orientation = 0;
        start_x = 0;
        start_y = 0;
        speed = 5;
    }


    public Automobile (String name, String direction, int counter_car){
        super(new Texture(name));
        this.counter_car = counter_car;

        this.direction = direction;
        if (direction == "up"){
            start_x = random.nextBoolean() ? start_x_up_1 : start_x_up_2;
            start_y = start_y_up - 100 * counter_car;
            direction_turn = "up";
            orientation = 0;
        }
        else if (direction == "down"){
            start_x = random.nextBoolean() ? start_x_down_1 : start_x_down_2;
            start_y = start_y_down + 100 * counter_car;
            direction_turn = "down";
            orientation = 180;
        }
        else if (direction == "left"){
            start_x = start_x_left - 100 * counter_car;
            start_y = random.nextBoolean() ? start_y_left_1 : start_y_left_2;
            orientation = -90;
        }
        else if (direction == "right"){
            start_x = start_x_right + 100 * counter_car;
            start_y = random.nextBoolean() ? start_y_right_1 : start_y_right_2;
            orientation = 90;
        }

        this.setPosition(start_x, start_y);
        this.setOriginCenter();
        this.rotate(orientation);
    }

    public void move(Semafor semafor_1, Semafor semafor_2) {


        if (direction == "up" && !accident && !hitted) {

            if (this.getY() != -25 && semafor_1.color == "red" ||
                    semafor_1.color == "green" ||
                    this.getY() != -25 && semafor_1.color == "yellow") {
                this.rotate();
                if (!this.rotation) {
                    this.setPosition(this.getX(), this.getY() + speed);

                    if (this.getY() > 500) {
                        this.setX(random.nextBoolean() ? start_x_up_1 : start_x_up_2);
                        this.setY(start_y_up - 100 * counter_car);
                    }
                }
            }
        }
        else if (direction == "down" && !accident && !hitted){

            if ((this.getY() != 440 && semafor_1.color == "red") ||
                    semafor_1.color == "green" ||
                    this.getY() != 440 &&semafor_1.color == "yellow") {

                this.rotate();
                if (!this.rotation) {
                    this.setPosition(this.getX(), this.getY() - speed);

                    if (this.getY() < -70) {
                        this.setX(random.nextBoolean() ? start_x_down_1 : start_x_down_2);
                        this.setY(start_y_down + 100 * counter_car);
                    }
                }
            }
        }
        else if (direction == "left" && !accident && !hitted){
            if (this.getX() != 155 & semafor_2.color == "red" ||
                    semafor_2.color == "green" ||
                    this.getX() != 155 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() + speed, this.getY());

                if (this.getX() > 810){
                    this.setY(random.nextBoolean() ? start_y_left_1 : start_y_left_2);
                    this.setX(start_x_left - 100 * counter_car);
                }
            }
        }
        else if (direction == "right" && !accident && !hitted){
            if (this.getX() != 625 & semafor_2.color == "red" ||
                    semafor_2.color == "green" ||
                    this.getX() != 625 && semafor_2.color == "yellow") {

                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -90){
                    this.setY(random.nextBoolean() ? start_y_right_1 : start_y_right_2);
                    this.setX(start_x_right + 100 * counter_car);
                }
            }
        }
        else if (direction == "up" && accident && rotation == false && direction_turn == "up" && not_hit){
            this.setPosition(this.getX(), this.getY() + speed + 5);

            if (this.getY() > 500) {
                this.setX(random.nextBoolean() ? start_x_up_1 : start_x_up_2);
                this.setY(start_y_up - 100 * counter_car);
            }
        }
        else if (direction == "down" && accident && rotation == false && direction_turn == "down" && not_hit){
            this.setPosition(this.getX(), this.getY() - speed - 5);

            if (this.getY() < -70) {
                this.setX(random.nextBoolean() ? start_x_down_1 : start_x_down_2);
                this.setY(start_y_down + 100 * counter_car);
            }
        }
        else if (direction == "left" && accident && rotation == false && not_hit){
            this.setPosition(this.getX() + speed + 5, this.getY());

            if (this.getX() > 810) {
                this.setY(random.nextBoolean() ? start_y_left_1 : start_y_left_2);
                this.setX(start_x_left - 100 * counter_car);
            }
        }
        else if (direction == "right" && accident && rotation == false && not_hit){
            this.setPosition(this.getX() - speed - 5, this.getY());
            if (this.getX() < -90){
                this.setY(random.nextBoolean() ? start_y_right_1 : start_y_right_2);
                this.setX(start_x_right + 100 * counter_car);
            }
        }


    }

    public void rotate (){
        if (this.direction == "up" & this.getX() > 450){
            if (this.getY() > 110 && this.getRotation() != -90 && this.speed != 0){
                direction_turn = "right";
                this.rotation = true;
                this.rotate(-5);
                this.translateY(1);
                this.translateX(2);
                this.setOriginCenter();
            }
            else if (this.getRotation() == -90){
                this.direction_turn = "up";
                this.setPosition(this.getX() + speed, this.getY());
                if (this.getX() > 890){
                    this.setPosition(random.nextBoolean() ? start_x_up_1 : start_x_up_2, start_y_up - 100 * counter_car);
                    this.setRotation(0);
                    this.rotation = false;

                    this.setOriginCenter();
                }
            }
        }
        else if (this.direction == "down" & this.getX() < 310){
            if (this.getY() <= 303 && this.getRotation() != 90 && this.speed != 0){
                this.direction_turn = "left";
                this.rotation = true;
                this.rotate(-5);
                this.translateY(-1);
                this.translateX(-2);
                this.setOriginCenter();
            }
            else if (this.getRotation() == 90){
                this.direction_turn = "down";
                this.setPosition(this.getX() - speed, this.getY());
                if (this.getX() < -100){
                    this.setPosition(random.nextBoolean() ? start_x_down_1 : start_x_down_2, start_y_down + 100 * counter_car);
                    this.setRotation(180);
                    this.rotation = false;
                    this.setOriginCenter();
                }
            }
        }
    }

}
