package HexStrip;

import processing.core.PApplet;

import java.util.ArrayList;

public class UnitCombine {
    public ArrayList<HexUnit> units;

    UnitCombine(int extend) {
        units = new ArrayList<>();
    }

    protected int getMode() {
        return (int) (Math.random() * 3f);
    }

    public HexUnit searchUnit(int x, int y, int z) {
        for (HexUnit u : units)
            if (u.x == x && u.y == y && u.z == z) {
                return u;
            }
        return null;
    }

    public void changePattern(float rate) {
        int times = (int) (units.size() / rate);
        for (int i = 0; i <= times; i++) {
            int index = (int) (Math.random() * units.size());
            units.get(index).changeMode();
        }
    }

    public void display(PApplet app) {
        for (HexUnit u : units) {
            if (u.x == 0 && u.y == 0 && u.z == 0)
                app.strokeWeight(4);
            else
                app.strokeWeight(1);
            u.display(app);
        }
    }
}
