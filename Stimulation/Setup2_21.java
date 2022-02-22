package Stimulation;

import processing.core.PApplet;

public class Setup2_21 extends PApplet {
    public static final int pwidth = 1800;
    public static final int pheight = 1000;
    StimulationGrid grids;

    public static void main(String[] args) {
        PApplet.main("Stimulation.Setup2_21");
    }

    public void setup() {
        grids = new StimulationGrid();
    }

    public void settings() {
        size(pwidth, pheight);
    }

    public void draw() {
        background(255);
        grids.display(this);
    }

    public void keyPressed() {
        if (key == 's' || key == 'S')
            ;
    }
}
