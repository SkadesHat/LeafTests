package HexStrip;

class CombineStandard extends UnitCombine {

    CombineStandard(int extend) {
        super(extend);
        for (int i = -extend; i <= extend; i++)
            for (int j = -extend; j <= extend; j++) {
                int x = i;
                int y = j;
                int z = -x - y;
                units.add(new HexUnit(x, y, z, getMode()));
            }
    }

}