package HexStrip;

import processing.core.PApplet;
import wblut.geom.*;
import wblut.processing.WB_Render2D;

import java.util.ArrayList;

public class HexUnit {
    static final float R = 20;
    static final float ArcDiv = 8f;
    static final WB_Point Bias = new WB_Point(Setup.pwidth/2f, Setup.pheight/2f);
    WB_Point pos;
    public float x, y, z;
    WB_Polygon bound;
    ArrayList<WB_PolyLine> stripes;
    int mode;

    public HexUnit(float x, float y, float z, int mode) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.mode = mode;
        pos = AxistoPos().add(Bias);
        bound = getHexBound();
        createStrips();
        setMode(mode);
    }

    public void changeMode() {
        mode=(mode+1)%3;
        setMode(mode);
    }
    public void changeMode_R() {
        mode=(mode+2)%3;
        setMode(mode);
    }

    public void setMode(int mode) {
        mode = mode % 3;
        WB_Transform T = new WB_Transform().addRotateAboutAxis2P(mode * Math.PI / 3f, pos, pos.add(0, 0, R));
        bound.apply(T);
        for (WB_PolyLine s : stripes) {
            s.apply(T);
        }
    }

    public void display(PApplet app) {
        WB_Render2D hpp = new WB_Render2D(app);
        app.stroke(200);
        app.noFill();
        hpp.drawPolygonEdges2D(bound);
        app.stroke(0,0,200);
        for (WB_PolyLine pline : stripes)
            hpp.drawPolyLine2D(pline);
    }

    private WB_Point AxistoPos() {
        double nx = x * R * 3 / 2;
        double ny = y * Math.sqrt(3) * R + x * Math.sqrt(3) / 2 * R;
        return new WB_Point(nx, ny);
    }

    private WB_Polygon getHexBound() {
        ArrayList<WB_Point> plist = new ArrayList<>();
        WB_Point basep = new WB_Point(pos.xf(), pos.yf() - R, 0);
        basep.rotateAboutAxis2PSelf(-Math.PI / 6f, pos, pos.add(0, 0, 1));
        for (int i = 0; i <= 5; i++) {
            plist.add(basep.rotateAboutAxis2P(Math.PI / 3f * i, pos, pos.add(0, 0, 1)));
        }
        return new WB_Polygon(plist);
    }

    private void createStrips() {
        stripes = new ArrayList<>();
        stripes.add(new WB_PolyLine(bound.toSegments().get(1).getCenter(), bound.toSegments().get(4).getCenter()));
        stripes.add(getArcPlines(2, 3));
        stripes.add(getArcPlines(5, 0));
    }

    private WB_PolyLine getArcPlines(int a, int b) {
        WB_Point start = bound.toSegments().get(a).getCenter();
        WB_Point end = bound.toSegments().get(b).getCenter();
        WB_Point center = bound.getPoint(a);
//
        ArrayList<WB_Point> arcplist = new ArrayList<>();
        double r = start.getDistance2D(center);
        for (int i = 0; i <= ArcDiv; i++) {
            WB_Point interp = start.mul((ArcDiv - i) / ArcDiv).add(end.mul(i / ArcDiv));
            WB_Vector dir = interp.sub(center);
            dir.normalizeSelf();
            WB_Point arcp = center.add(dir.mul(r));
            arcplist.add(arcp);
        }
        return new WB_PolyLine(arcplist);
    }
}
