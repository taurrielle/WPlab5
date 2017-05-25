package Automobile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by ira on 5/25/17.
 */

public class SpecialCar extends Sprite {

    public int speed = 7;

    public SpecialCar(String name){
        super(new Texture(name));
        this.setPosition(-90 , 230);
        this.rotate(-90);
    }

    public void move() {

        if (this.getX() < 850) {
            if (this.getX() < 260) {

                this.setPosition(this.getX() + speed, this.getY());
            } else if (this.getY() > 185) {
                this.setPosition(this.getX() + speed, this.getY() - speed);
            } else {
                this.setPosition(this.getX() + speed, this.getY());
            }
            if (this.getX() > 810) {
                this.setX(1800);
            }
        }
    }
}

