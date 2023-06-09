public class Human {

    private float humanHeadY = 490;
    private float xPos = 250;
    private float humanHeadX;


    public void createHuman(){

        float leftHandPos = Main.processing.mouseX - 40;
        float rightHandPos = Main.processing.mouseX + 40;
        float leftLegPos = Main.processing.mouseX - 20;
        float rightLegPos = Main.processing.mouseX + 5;
        float mouseX_LeftEye = Main.processing.mouseX - 10 ;
        float mouseX_RightEye = Main.processing.mouseX + 10 ;
        float pocketPos = Main.processing.mouseX + 5;

        xPos = Main.processing.mouseX;
        humanHeadX = xPos;

        Main.processing.noStroke();
        Main.processing.fill(38 ,38 ,200);
        Main.processing.circle(humanHeadX , humanHeadY , 50); //Head
        Main.processing.ellipse(humanHeadX , 545 , 50 , 70); //Body
        Main.processing.stroke(38 , 38 , 200);
        Main.processing.line(leftHandPos , 555 , leftHandPos + 18 , 535); //leftHand
        Main.processing.line(rightHandPos , 555 , rightHandPos - 18 , 535); //rightHand
        Main.processing.strokeWeight(7);
        Main.processing.noStroke();
        Main.processing.rect(leftLegPos , 560 , 15 ,40); //LeftLeg
        Main.processing.rect(rightLegPos , 560 , 15 ,40); //RightLeg
        Main.processing.fill(222 , 222 , 249);
        Main.processing.rect(pocketPos , 535 , 12 , 10); //Pocket
        Main.processing.ellipse(mouseX_LeftEye , 485 , 12 , 12);  //leftEye
        Main.processing.ellipse(mouseX_RightEye , 485 , 12 , 12);  //rightEye


    }

    public void setHumanHeadY(float humanHeadY) {
        this.humanHeadY = humanHeadY;
    }

    public void setHumanHeadX(float humanHeadX) {
        this.humanHeadX = humanHeadX;
    }

    public float getHumanHeadY() {
        return humanHeadY;
    }

    public float getHumanHeadX() {
        return humanHeadX;
    }
}
