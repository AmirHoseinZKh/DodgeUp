import processing.core.PApplet;

import java.util.ArrayList;

public class Blocks {

    private static  int blocksUp = -70;
    private float blockX;
    private float blockY;
    private float color_R;
    private float color_G;
    private float color_B;


    public Blocks(float blockX , float blockY , float color_R , float color_G , float color_B){
        this.blockX = blockX;
        this.blockY = blockY;
        this.color_R = color_R;
        this.color_G = color_G;
        this.color_B = color_B;
    }

    public void buildBlocks(){
        for(int i=0 ; i<150 ; i++) {
            Main.block.add(new Blocks((int) Main.processing.random(100), blocksUp, (int) Main.processing.random(255), (int) Main.processing.random(255), (int) Main.processing.random(255)));
            blocksUp-=Main.processing.random(70 , 120);
            Main.block.add(new Blocks((int) Main.processing.random(130 , 230), blocksUp, (int) Main.processing.random(255), (int) Main.processing.random(255), (int) Main.processing.random(255)));
            blocksUp-=Main.processing.random(70 , 120);
            Main.block.add(new Blocks((int) Main.processing.random(260 , 360), blocksUp, (int) Main.processing.random(255), (int) Main.processing.random(255), (int) Main.processing.random(255)));
            blocksUp-=Main.processing.random(70 , 120);
            Main.block.add(new Blocks((int) Main.processing.random(390 , 470), blocksUp, (int) Main.processing.random(255), (int) Main.processing.random(255), (int) Main.processing.random(255)));
            blocksUp-=Main.processing.random(70 , 120);

        }
    }



    public float getBlockX() {
        return blockX;
    }

    public float getBlockY() {
        return blockY;
    }

    public float getColor_R() {
        return color_R;
    }

    public float getColor_G() {
        return color_G;
    }

    public float getColor_B() {
        return color_B;
    }

    public void setBlockX(float blockX) {
        this.blockX = blockX;
    }

    public void setBlockY(float blockY) {
        this.blockY = blockY;
    }

    public void setColor_R(float color_R) {
        this.color_R = color_R;
    }

    public void setColor_G(float color_G) {
        this.color_G = color_G;
    }

    public void setColor_B(float color_B) {
        this.color_B = color_B;
    }


}
