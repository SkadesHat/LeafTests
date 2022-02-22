package HexStrip;

import processing.core.PApplet;

public class Setup extends PApplet {
    public static final int pwidth = 1800;
    public static final int pheight = 1000;
    UnitCombine combine;

    public static void main(String[] args) {
        PApplet.main("HexStrip.Setup");
    }

    public void setup() {

        //combine = new HexStrip.CombineStandard(40);
        combine = new CombineRotate(30);
    }
public void settings(){
    size(pwidth, pheight);
}
    public void draw() {
        background(255);
        combine.display(this);
    }

    public void keyPressed() {
        if (key == 's' || key == 'S')
            combine.changePattern(2);
    }
}
