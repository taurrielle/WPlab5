package Automobile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by hyena on 5/20/17.
 */

public class Semafor extends Sprite {

    public String color = "green";
    public String previous_color = "green";
    public String direction;
    public float start_x, start_y;
    private long startTime;

    public Semafor(String name, String direction) {
        super(new Texture(name));
        this.direction = direction;

        if (direction == "up"){
            start_x = 500;
            start_y = 80;
        }
        else if (direction == "down"){
            start_x = 250;
            start_y = 350;
            this.rotate(180);
        }
        else if (direction == "left"){
            start_x = 250;
            start_y = 80;
            this.rotate(-90);
        }
        else if (direction == "right"){
            start_x = 520;
            start_y = 350;
            this.rotate(90);
        }

        this.setPosition(start_x, start_y);
    }

    public void changeColor(){

        if (color == "green"){

            this.setTexture(new Texture ("semafor_green.png"));
        }
        else if (color == "red"){

            this.setTexture(new Texture ("semafor_red.png"));
        }
        else if (color == "yellow"){

            this.setTexture(new Texture("semafor_yellow.png"));
        }
    }

    public void setState(){
        if (color == "green") {
            previous_color = "green";
            color = "yellow";
        }
        else if (color == "red"){
            previous_color = "red";
            color = "yellow";
        }
        else if (color == "yellow" && previous_color == "green"){
            color = "red";
        }
        else if (color == "yellow" && previous_color == "red"){
            color = "green";
        }
    }
}
