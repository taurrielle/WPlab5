package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;

import java.sql.Array;
import java.util.Random;

import Automobile.Automobile;
import Automobile.Semafor;
import Automobile.Pedestrian;
import Automobile.SpecialCar;

/**
 * Created by hyena on 5/19/17.
 */

public class GameDraw implements Screen {

    private MyGdxGame game;
    private long startTime_1 = 0, startTime_2 = 0, startTime_3 = 0, wait_time = 300, semafor_contor = 0;
    private static final String[] position = {"up", "down", "left", "right"};

    private boolean colision_1 = false, colision_2 = false;
    private boolean colision;
    private Texture road;


    private Automobile[] automobile = new Automobile[20];
    private int nr_cars = 4, break_statement = 0;

    private Semafor[] semafor = new Semafor[4];
    private Pedestrian[] pedestrian = new Pedestrian[4];
    private SpecialCar[] specialCars = new SpecialCar[2];



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


        automobile[0] = new Automobile("car.png", "up", 1);
        automobile[1] = new Automobile("car.png", "down", 1);
        automobile[2] = new Automobile("car.png", "left", 1);
        automobile[3] = new Automobile("car.png", "right", 1);
        automobile[4] = new Automobile("car.png", "up", 2);
        automobile[5] = new Automobile("car.png", "down", 2);
        for (int i = 6; i < 8; i++){
            automobile[i] = new Automobile("car.png", "left", i - 4);
            automobile[i+2] = new Automobile("car.png", "right", i - 4);

        }

        specialCars[0] = new SpecialCar("ambulance.png");
        specialCars[1] = new SpecialCar("police.png");



        for (int i = 0; i < 4; i++){
            generatePedestrians(i);
        }

    }

    public void generatePedestrians(int i){
        Random random = new Random();
        int index = random.nextInt(position.length);
        int speed = random.nextInt(2) + 1;
        pedestrian[i] = new Pedestrian("1.png", position[index], speed, i);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched() & Gdx.input.getX() < 240 & Gdx.input.getY() < 400){
            if (nr_cars < 10){
                nr_cars += 1;
            }
        }
        else if (Gdx.input.justTouched() & Gdx.input.getX() < 240 & Gdx.input.getY() > 400){
            if (nr_cars > 4){
                nr_cars -= 1;
            }
        }

        //semafor-----------------------------------------------------------------------------------

        if (Gdx.input.justTouched() & Gdx.input.getX() > 240 & Gdx.input.getY() < 400){
            if (wait_time < 1200){
                wait_time += 100;
            }
        }
        else if (Gdx.input.justTouched() & Gdx.input.getX() > 240 & Gdx.input.getY() > 400){
            if (wait_time > 100){
                wait_time -= 100;
            }
        }

        if (startTime_1 > 40 && semafor[1].color == "yellow") {

            for (int i = 0; i < 4; i ++){
                semafor[i].setState();
                semafor[i].changeColor();
            }

            startTime_2 = 0;

        }
        else if (startTime_2 > wait_time && semafor[1].color != "yellow")
        {
            for (int i = 0; i < 4; i ++) {
                semafor[i].setState();
                semafor[i].changeColor();
            }
            startTime_1 = 0;
            semafor_contor += 1;
        }
        startTime_1 += 1;
        startTime_2 += 1;


        game.getBatch().begin();
        game.getBatch().draw(road, 0, 0);
        for (int i = 0; i < 4; i++){
            semafor[i].draw(game.getBatch());
        }

        //semafor end -----------------------------------------------------------------------------

        if (semafor_contor > 2){


                for (int i = 0; i < 4; i++) {
                    pedestrian[i].specialCarFlag = true;
                }

                for (int i = 0; i < nr_cars; i++) {
                    automobile[i].specialCarFlag = true;
                }

            specialCars[0].move();
            specialCars[0].draw(game.getBatch());
        }
        if (specialCars[0].getX() > 900){
            for(int i=0; i < 4; i++) {
                pedestrian[i].specialCarFlag = false;
            }

            for (int i = 0; i < nr_cars; i++) {
                automobile[i].specialCarFlag = false;
            }
        }


        if (semafor_contor > 4){


            for (int i = 0; i < 4; i++) {
                pedestrian[i].specialCarFlag = true;
            }

            for (int i = 0; i < nr_cars; i++) {
                automobile[i].specialCarFlag = true;
            }

            specialCars[1].move();
            specialCars[1].draw(game.getBatch());
        }
        if (specialCars[1].getX() > 900){
            for(int i=0; i < 4; i++) {
                pedestrian[i].specialCarFlag = false;
            }

            for (int i = 0; i < nr_cars; i++) {
                automobile[i].specialCarFlag = false;
            }
        }

        //pedestrian
        if (startTime_3 > 15) {
            for (int i=0; i < 4; i++) {
                pedestrian[i].walk();
                startTime_3 = 0;
            }
        }
        startTime_3 += 1;

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (i != j) {
                    colision = colisionTwoPed(pedestrian[i], pedestrian[j]);
                    if (colision){
                        pedestrian[i].speed = 0;
                        pedestrian[i].walkFlag = false;
                        break;
                    }
                    else {
                        pedestrian[i].speed = 1;
                        pedestrian[i].walkFlag = true;
                    }
                }
            }
        }

        for(int i=0; i < 4; i++) {
            if(pedestrian[i].deletePedestrian == true){
                generatePedestrians(i);
            }
            pedestrian[i].move(semafor[0], semafor[2]);
            pedestrian[i].draw(game.getBatch());
        }


        for (int i = 0; i < nr_cars; i++) {
            for (int j = 0; j < nr_cars; j++) {
                if (i != j) {
                    colision_1 = colision(automobile[i], automobile[j]);
                }
                for (int k = 0; k < 4; k++) {
                    colision_2 = colision_pedestrian(automobile[i], pedestrian[k]);

                    if (colision_1 == true || colision_2 == true) {
                        automobile[i].speed = 0;
                        break_statement = 1;
                        break;
                    }
                    else {
                        automobile[i].speed = 5;
                    }
                }

                if (break_statement == 1){
                    break_statement = 0;
                    break;
                }
            }
        }

        for (int i = 0; i < nr_cars; i++){
            automobile[i].move(semafor[0], semafor[2]);
            automobile[i].draw(game.getBatch());
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

    public boolean colision(Automobile automobile, Automobile automobile_2){
        Rectangle rect_1;
        Rectangle rect_2;
        rect_1 = automobile.getBoundingRectangle();
        rect_2 = automobile_2.getBoundingRectangle();

        if (automobile.direction == "up" && automobile.direction_turn == "up") {
            rect_1.setY(rect_1.getY() + 20);
        }
        else if (automobile.direction == "down" && automobile.direction_turn == "down"){
            rect_1.setY(rect_1.getY() - 20);
        }
        else if (automobile.direction == "left"){
            rect_1.setX(rect_1.getX() + 20);
        }
        else if (automobile.direction == "right"){
            rect_1.setX(rect_1.getX() - 20);
        }
        else if(automobile.direction_turn == "right"){
            rect_1.setX(rect_1.getX() + 20);
            rect_1.width = rect_1.width + 11;
        }
        else if(automobile.direction_turn == "left"){
            rect_1.setX(rect_1.getX() - 20);
            rect_1.width = rect_1.width + 11;
        }


        boolean isOverlaping = rect_1.overlaps(rect_2);
        return isOverlaping;
    }

    public boolean colision_pedestrian(Automobile automobile, Pedestrian pedestrian){
        Rectangle rect_1;
        Rectangle rect_2;
        rect_1 = automobile.getBoundingRectangle();
        rect_2 = pedestrian.getBoundingRectangle();

        if (automobile.direction == "up" && automobile.direction_turn == "up"){

            rect_1.setY(rect_1.getY() + 30);
        }
        else if (automobile.direction == "down" && automobile.direction_turn == "down"){
            rect_1.setY(rect_1.getY() - 30);
        }
        else if (automobile.direction == "left"){
            rect_1.setX(rect_1.getX() + 30);
        }
        else if (automobile.direction == "right"){
            rect_1.setX(rect_1.getX() - 30);
        }
        else if (automobile.direction_turn == "right"){
            rect_1.setX(rect_1.getX() + 20);
        }
        else if (automobile.direction_turn == "left"){
            rect_1.setX(rect_1.getX() - 20);
        }

        boolean isOverlaping = rect_1.overlaps(rect_2);
        return isOverlaping;
    }



    public boolean colisionTwoPed(Pedestrian P1, Pedestrian P2){
        Rectangle rect_1;
        Rectangle rect_2;
        rect_1 = P1.getBoundingRectangle();
        rect_2 = P2.getBoundingRectangle();

        if (P1.direction == "up") {
            rect_1.setY(rect_1.getY() + 5);
        }
        else if (P1.direction == "down"){
            rect_1.setY(rect_1.getY() - 5);
        }
        else if (P1.direction == "left"){
            rect_1.setX(rect_1.getX() + 5);
        }
        else if (P1.direction == "right"){
            rect_1.setX(rect_1.getX() - 5);
        }

        boolean isOverlaping = rect_1.overlaps(rect_2);
        return isOverlaping;
    }
}

