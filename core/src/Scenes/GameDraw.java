package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;

import java.sql.Array;
import java.util.Random;

import Automobile.Automobile;
import Automobile.Semafor;
import Automobile.Pedestrian;

/**
 * Created by hyena on 5/19/17.
 */

public class GameDraw implements Screen {

    private MyGdxGame game;
    private long startTime_1 = 0, startTime_2 = 0, startTime_3 = 0;
    private static final String[] position = {"up", "down", "left", "right"};

    private Texture road;


    private Automobile[] automobile = new Automobile[4];
    private Semafor[] semafor = new Semafor[4];
    private Pedestrian[] pedestrian = new Pedestrian[4];


    float stateTime = 0f;


    public GameDraw (MyGdxGame game){
        this.game = game;
        road = new Texture("drum.png");


        semafor[0] = new Semafor("semafor_red.png", "up");
        semafor[0].color = "red";
        semafor[0].previous_color = "green";
        semafor[1] = new Semafor("semafor_red.png", "down");
        semafor[1].color = "red";
        semafor[1].previous_color = "green";
        semafor[2] = new Semafor("semafor_green.png", "left");
        semafor[3] = new Semafor("semafor_green.png", "right");


        automobile[0] = new Automobile("car.png", "up");
        automobile[0].rotation = false;
        automobile[1] = new Automobile("car.png", "down");
        automobile[2] = new Automobile("car.png", "left");
        automobile[3] = new Automobile("car.png", "right");

        for (int i = 0; i < 4; i++){
            generatePedestrians(i);
        }

    }

    public void generatePedestrians(int i){
        Random random = new Random();
        int index = random.nextInt(position.length);
        int speed = random.nextInt(2) + 1;
        pedestrian[i] = new Pedestrian("1.png", position[index], speed);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        //semafor-------------------------------

        if (startTime_1 > 40 && semafor[1].color == "yellow") {

            for (int i = 0; i < 4; i ++){
                semafor[i].setState();
                semafor[i].changeColor();
            }

            startTime_2 = 0;

        }
        else if (startTime_2 > 300 && semafor[1].color != "yellow")
        {
            for (int i = 0; i < 4; i ++) {
                semafor[i].setState();
                semafor[i].changeColor();
            }
            startTime_1 = 0;

        }
        startTime_1 += 1;
        startTime_2 += 1;


        game.getBatch().begin();
        game.getBatch().draw(road, 0, 0);
        for (int i = 0; i < 4; i++){
            semafor[i].draw(game.getBatch());
        }

        for (int i = 0; i < 4; i++){
            automobile[i].move(semafor[0], semafor[2]);
            automobile[i].draw(game.getBatch());
        }

        //pedestrian
        if (startTime_3 > 15) {
            for (int i=0; i < 4; i++) {
                pedestrian[i].walk();
                startTime_3 = 0;
            }
        }
        startTime_3 += 1;

        for(int i=0; i < 4; i++) {
            if(pedestrian[i].deletePedestrian == true){
                generatePedestrians(i);
            }
            pedestrian[i].move(semafor[0], semafor[2]);
            pedestrian[i].draw(game.getBatch());
        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
