/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dolores Pot Hol
 */
public class Parameter {
    private int Nn;
    private int Nf;
    private int widthOverlappedBlock;
    private int heightOverlappedBlock;

    public int getNn() {
        return Nn;
    }

    public int getNf() {
        return Nf;
    }

    public int getWidthOverlappedBlock() {
        return widthOverlappedBlock;
    }

    public int getHeightOverlappedBlock() {
        return heightOverlappedBlock;
    }

    public Parameter(int Nn, int Nf, int widthOverlappedBlock, int heightOverlappedBlock) {
        this.Nn = Nn;
        this.Nf = Nf;
        this.widthOverlappedBlock = widthOverlappedBlock;
        this.heightOverlappedBlock = heightOverlappedBlock;
    }
}
