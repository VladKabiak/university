package board;

import java.io.*;

public class Read {
    private final InputStream is;

    public Read(String url) throws FileNotFoundException {
        this.is = new FileInputStream(url);
    }

//    public int[] getNextPiece() {
//        int type = getIntFromBytes( );
//        int hPos = getIntFromBytes( );
//        int vPos = getIntFromBytes( );
//        int color = getIntFromBytes( );
//        return new int[]{type, hPos, vPos, color};
//    }

//    private int getIntFromBytes(int numBytes) {
//
//    }
}
