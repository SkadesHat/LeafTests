package Stimulation;

import wblut.geom.WB_Point;
import wblut.geom.WB_Segment;
import wblut.geom.WB_Vector;

public class TestSeg {
    final static float HalfStep = StimulationGrid.Step / 2f;
    WB_Point[] test_point = new WB_Point[2];
    public boolean isChosen;
    WB_Segment seg;
    Expression exp;

    TestSeg(WB_Segment seg, Expression exp) {
        this.seg = seg;
        this.exp = exp;
        //
        WB_Vector dir = WB_Vector.cross(seg.getDirection(), new WB_Vector(0, 0, 1));
        dir.normalizeSelf();
        WB_Point center = seg.getCenter();
        test_point[0] = new WB_Point(center.add(dir.mul(HalfStep)));
        test_point[1] = new WB_Point(center.sub(dir.mul(HalfStep)));
//
        isChosen = exp.operation(test_point[0]) * exp.operation(test_point[1]) <= 0;
        System.out.println(center + " " + test_point[0] + " " + test_point[1]);
    }
}
