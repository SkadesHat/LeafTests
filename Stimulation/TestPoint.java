package Stimulation;

import processing.core.PApplet;
import wblut.geom.WB_Point;
import wblut.geom.WB_Segment;
import wblut.processing.WB_Render2D;

import java.util.ArrayList;

public class TestPoint {
    final static float HalfStep = StimulationGrid.Step / 2f;
    private Expression exp;
    WB_Point pos;
    public boolean isChosen;
    ArrayList<WB_Segment> segs = new ArrayList<>();

    TestPoint(WB_Point p, Expression exp) {
        this.pos = p;
        this.exp=exp;
        double base_value = exp.operation(p);
        boolean flag1 = (base_value *exp.operation(p.add(0, HalfStep, 0))) >= 0;
        boolean flag2 = (base_value * exp.operation(p.add(0, -HalfStep, 0))) >= 0;
        boolean flag3 = (base_value * exp.operation(p.add(HalfStep, 0, 0))) >= 0;
        boolean flag4 = (base_value * exp.operation(p.add(-HalfStep, 0, 0))) >= 0;
        isChosen = !((flag1 && flag2 && flag3 && flag4));
        //if (isChosen) System.out.println("" + p + flag1 + " " + flag2 + " " + flag3 + " " + flag4 + " :" + isChosen);
//
        WB_Point[] pts = new WB_Point[4];
        pts[0] = p.add(-HalfStep, -HalfStep, 0);
        pts[1] = p.add(HalfStep, -HalfStep, 0);
        pts[2] = p.add(HalfStep, HalfStep, 0);
        pts[3] = p.add(-HalfStep, HalfStep, 0);
        for (int i = 0; i < 4; i++) {
            segs.add(new WB_Segment(pts[i], pts[(i + 1) % 4]));
        }
    }

    public void display(PApplet app) {
        WB_Render2D hpp = new WB_Render2D(app);
        if (isChosen) {
            app.fill(255, 0, 0);
            app.noStroke();
           // hpp.drawPoint2D(pos, 2);
            app.stroke(0);
            app.noFill();
           // hpp.drawSegment2D(segs);
        } else {
            app.fill(200);
            app.noStroke();
            hpp.drawPoint2D(pos, 2);
        }

    }
}
