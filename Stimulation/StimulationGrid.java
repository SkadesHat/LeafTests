package Stimulation;

import processing.core.PApplet;
import wblut.geom.WB_Point;
import wblut.geom.WB_Segment;
import wblut.processing.WB_Render2D;

import java.util.ArrayList;

public class StimulationGrid {
    final static int Step = 15;
    final static int width = Setup2_21.pwidth;
    final static int height = Setup2_21.pheight;
    Expression exp1 = (WB_Point p) -> Math.pow(p.xf() - 900, 2) - Math.pow(p.yf() - 500, 2) - 8000.1;
    Expression exp2 = (WB_Point p) -> Math.pow(p.xf() - 900, 2)/2f + Math.pow(p.yf() - 500, 2) - 60000.1;
    Expression exp3 = (WB_Point p) -> Math.pow(p.xf() - 900, 2) - Math.pow(p.yf() - 500, 2) - 8000.1;
    Expression exp = exp1;

    //    public static double expression(WB_Point p) {
//        return  Math.pow(p.xf()-900, 2)-Math.pow(p.yf()-500, 2)-8000.1;
//        //return p.xf() / 10f * Math.cos(p.xf() / 30f) - p.yf() + 500;
//    }
    int X = width / Step + 2;
    int Y = height / Step + 2;
    WB_Point[][] PtGrid = new WB_Point[X][Y];
    ArrayList<WB_Segment> segs = new ArrayList<>();
    ArrayList<TestSeg> test_segs = new ArrayList<>();
    ArrayList<TestPoint> test_pts = new ArrayList<>();

    StimulationGrid() {
        //
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                PtGrid[i][j] = new WB_Point(i * Step, j * Step, 0);
                test_pts.add(new TestPoint(PtGrid[i][j], exp));
            }
        }
        //
        WB_Point offset = new WB_Point(Step / 2f, Step / 2f, 0);
        for (int i = 1; i < X; i++) {
            for (int j = 1; j < Y; j++) {
                WB_Segment segI = new WB_Segment(PtGrid[i][j - 1].add(offset), PtGrid[i][j].add(offset));
                WB_Segment segJ = new WB_Segment(PtGrid[i - 1][j].add(offset), PtGrid[i][j].add(offset));
                segs.add(segI);
                segs.add(segJ);
            }
        }
        //
        for (WB_Segment s : segs) {
            test_segs.add(new TestSeg(s, exp));
        }

    }

    public void display(PApplet app) {
        WB_Render2D hpp = new WB_Render2D(app);
        //
        app.stroke(200);
        hpp.drawSegment2D(segs);
        //
        app.noStroke();
        app.fill(0);
        for (TestPoint p : test_pts)
            p.display(app);
        //
        app.stroke(255, 0, 0);
        for (TestSeg s : test_segs)
            if (s.isChosen)
                hpp.drawSegment2D(s.seg);

    }
}
