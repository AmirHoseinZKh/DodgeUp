import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.sound.SoundFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main extends PApplet implements move, createBlocks {

    public static PApplet processing;

    public final int blockWidth = 30;
    public final int blockHeight = 70;
    public Blocks bloock = new Blocks(0,0,0,0,0);
    public Human human = new Human();
    private static int score = 0;
    private static int highScore;
    private int numberOfLives = 3;
    public static ArrayList<Blocks> block = new ArrayList<>();
    private boolean gameOver = false;
    private boolean startGame = false;
    private boolean checkStartOver = false;
    private boolean checkExitOver = false;
    public static SoundFile sound;



    PImage bg;
    PImage star;
    PFont allText;
    PFont createdText;
    PFont welcome;

    public static void main(String[] args) {
        PApplet.main("Main",args);

    }

    @Override
    public void setup() {
        processing = this;
        bloock.buildBlocks();
        getHighScore();
        star = loadImage("0star.png");
        allText = createFont("SitkaVF.ttf" , 30);
        createdText = createFont("segoesc.ttf" , 30);
        welcome = createFont("mvboli.ttf" , 30);
        sound = new SoundFile(this , "game sound .wav");
        sound.play();
    }

    @Override
    public void settings() {
        size(500 , 700);
    }

    @Override
    public void draw() {
        updateArea();
        if (startGame) {
            if (!gameOver) {
                bg = loadImage("bg1.jpg");
                image(bg , 0  ,0 , 500 , 700);
                createBlocks();
                setBackground();
                score = frameCount / 90;
                move();
                human.createHuman();

                if (score >= 20) {
                    bg = loadImage("bg2.png");
                    for (Blocks c : block) {
                        c.setBlockY(c.getBlockY() + 5);
                    }
                }
                if (score >= 55) {
                    bg = loadImage("bg3.jpg");
                    for (Blocks c : block) {
                        c.setBlockY(c.getBlockY() + 7);
                    }
                }

                float humanTop = human.getHumanHeadY() - 80;
                float humaBottom = human.getHumanHeadY() + 120;
                float humanCenter = human.getHumanHeadX();


                for (int i = 0; i < block.size(); i++) {
                    float blockTop = block.get(i).getBlockY();
                    float blockBottom = block.get(i).getBlockY() + blockHeight;
                    float blockLeft = block.get(i).getBlockX();
                    float blockRight = block.get(i).getBlockX() + blockWidth;


                    if (blockLeft <= humanCenter && blockRight >= humanCenter && blockTop > humanTop && blockBottom < humaBottom) {
                        block.remove(block.get(i));
                        numberOfLives--;
                        if (numberOfLives < 1) {
                            final int finalScore = score;
                            gameOver = true;
                            lastScreen(finalScore);
                        }
                    }
                    else if (blockLeft <= humanCenter + 40 && blockRight >= humanCenter + 40 && blockTop > humanTop && blockBottom < humaBottom) {
                        block.remove(block.get(i));
                        numberOfLives--;
                        if (numberOfLives < 1) {
                            final int finalScore = score;
                            gameOver = true;
                            lastScreen(finalScore);
                        }
                    }
                    else if (blockLeft <= humanCenter - 40 && blockRight >= humanCenter - 40 && blockTop > humanTop && blockBottom < humaBottom) {
                        block.remove(block.get(i));
                        numberOfLives--;
                        if (numberOfLives < 1) {
                            final int finalScore = score;
                            gameOver = true;
                            lastScreen(finalScore);
                        }
                    }
                }
            }
        }
        else{
            startMenu();
            frameCount = 0;
        }
    }

    public void move(){

        for (Blocks c : block){
            c.setBlockY(c.getBlockY()+4);
        }

    }

    @Override
    public void createBlocks() {
        for(Blocks c : block){
            fill(c.getColor_R() , c.getColor_G() , c.getColor_B());
            noStroke();
            rect(c.getBlockX() , c.getBlockY() , blockWidth , blockHeight);
        }

    }

    public void setBackground(){
        fill(60 , 60 , 60);
        noStroke();
        rect(0 , 600 , 500 , 100);
        fill(255 , 255 , 255);
        textFont(allText);
        text("Score :" , 10 ,630);
        textSize(30);
        text(score , 450 , 630);
        textFont(allText);
        text("Number Of Lives :" , 10 , 670);
        text(numberOfLives , 450 , 670);

    }

    public boolean checkStartHover(int x , int y , int width , int height){
        return mouseX >= x && mouseX<= x+width && mouseY >= y && mouseY <= y + height;
    }

    public boolean checkExitHover(int x , int y , int width , int height){
        return mouseX >= x && mouseX<= x+width && mouseY >= y && mouseY <= y + height;
    }

    public void updateArea(){
        if (checkStartHover(180 , 260 , 160 , 20)){
            checkStartOver = true;
            checkExitOver = false;
        }
        else if (checkExitHover(228 , 350 , 49 , 20)){
            checkExitOver = true;
            checkStartOver = false;
        }
        else if (checkExitHover2(228 , 480 , 49 , 20)){
            checkExitOver = true;
            checkStartOver = false;
        }
        else {
            checkStartOver = false;
            checkExitOver = false;
        }
    }

    public void startMenu(){
        bg = loadImage("Menu.jpg");
        image(bg , 0, 0, 500, 700);
        fill(80);
        rect(12 , 3 , 475 , 33);
        fill(255 , 210 , 0);
        textFont(welcome);
        text("Welcome to the Dodge Up Game" , 15 , 30);
        fill(80);
        rect(179 , 256 , 160 , 30);
        fill(255);
        textFont(allText);
        text("Start Game" , 180 ,280);
        textSize(30);
        fill(80);
        rect(224 , 346 , 60 , 30);
        fill(255 , 0 , 0);
        textFont(allText);
        text("Exit" , 225 , 370);
        textSize(30);
        fill(255 , 210 , 0);
        textFont(createdText);
        text("Created By AHZ" ,230 , 690);
        textSize(5);

    }

    @Override
    public void mousePressed() {
        if (checkStartOver){
            startGame = true;
        }
        else if(checkExitOver) {
            exit();
        }
    }

    public boolean checkExitHover2(int x , int y , int width , int height){
        return mouseX >= x && mouseX<= x+width && mouseY >= y && mouseY <= y + height;
    }

    public void lastScreen(int score){
        background(0);

        if (score <= 15) {
            image(star , 70 , 200 , 360 , 100);
            textFont(allText);
            text("Your Score :", 120, 400);
            textSize(30);
            text(score, 330, 400);
            textSize(30);
            fill(255 , 0 , 0);
            textFont(allText);
            text("Exit" , 225 , 500);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text("High Score :" , 120 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text(highScore , 330 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(createdText);
            text("Created By AHZ" ,230 , 690);
            textSize(5);
        }
        else if (score <= 35){
            star = loadImage("1star.png");
            image(star , 70 , 200 , 360 , 100);
            textFont(allText);
            text("Your Score :", 120, 400);
            textSize(30);
            text(score, 330, 400);
            textSize(30);
            fill(255 , 0 , 0);
            textFont(allText);
            text("Exit" , 225 , 500);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text("High Score :" , 120 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text(highScore , 330 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(createdText);
            text("Created By AHZ" ,230 , 690);
            textSize(5);
        }
        else if (score <= 65){
            star = loadImage("2star.png");
            image(star , 70 , 200 , 360 , 100);
            textFont(allText);
            text("Your Score :", 120, 400);
            textSize(30);
            text(score, 330, 400);
            textSize(30);
            fill(255 , 0 , 0);
            textFont(allText);
            text("Exit" , 225 , 500);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text("High Score :" , 120 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text(highScore , 330 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(createdText);
            text("Created By AHZ" ,230 , 690);
            textSize(5);
        }
        else {
            star = loadImage("3star.png");
            image(star , 70 , 200 , 360 , 100);
            textFont(allText);
            text("Your Score :", 120, 400);
            textSize(30);
            text(score, 330, 400);
            textSize(30);
            fill(255 , 0 , 0);
            textFont(allText);
            text("Exit" , 225 , 500);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text("High Score :" , 120 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(allText);
            text(highScore , 330 , 450);
            textSize(30);
            fill(255 , 210 , 0);
            textFont(createdText);
            text("Created By AHZ" ,230 , 690);
            textSize(5);

        }
        if (score > highScore){
            updateHighScore();
        }
    }

    public static void getHighScore(){
        String username = "root";
        String password = "ahz8213";
        String url = "jdbc:mysql://localhost:3306/dodgeup";
        try{
            Connection connection = DriverManager.getConnection(url , username , password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from score");
            while (resultSet.next()){
                highScore = resultSet.getInt(1);
            }
        }
        catch (Exception e){}
    }

    public static void updateHighScore(){
        String username = "root";
        String password = "ahz8213";
        String url = "jdbc:mysql://localhost:3306/dodgeup";
        try{
            Connection connection = DriverManager.getConnection(url , username , password);
            PreparedStatement statement = connection.prepareStatement("update score set idscore = ?");
            statement.setInt(1 , score);
            statement.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}