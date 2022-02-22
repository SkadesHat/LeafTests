package HexStrip;

class CombineRotate extends UnitCombine {

    CombineRotate(int extend) {
        super(extend);
        units.add(new HexUnit(0, 0, 0, 0));
        createParaLog(extend,0);
        createParaLog(extend,1);
        createParaLog(extend,2);
//        super.searchUnit(1,0,-1).changeMode_R();
//        super.searchUnit(1,-1,0).changeMode_R();
    }

    private void createParaLog(int extend,int mode) {
        for (int i = 1; i <= extend; i++)
            for (int j = 0; j <= extend; j++) {
                int []paras=new int[]{j, i - j,-i,};
                units.add(new HexUnit(paras[mode%3], paras[(mode+1)%3], paras[(mode+2)%3], mode));
            }
    }
}
//
//        for (int i = 1; i <= extend; i++)
//            for (int j = 0; j <= extend; j++) {
//                int x = j;
//                int y = i-j;
//                int z = -i;
//                int []paras=new int[]{j, i - j,-i,};
//                units.add(new HexStrip.HexUnit(x, y, z, 1));
//            }
//        //
//        for (int i = 1; i <= extend; i++)
//            for (int j = 0; j <= extend; j++) {
//                int x = -i;
//                int z = i - j;
//                int y = j;
//                units.add(new HexStrip.HexUnit(x, y, z, 2));
//            }
//        //
//
//        for (int i = 1; i <= extend; i++)
//            for (int j = 0; j <= extend; j++) {
//                int x = i-j;
//                int y = -i;
//                int z = j;
//                units.add(new HexStrip.HexUnit(x, y, z, 0));
//            }