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
public class Hasil {
    private int detectedObjectCount;
    private CitraKeabuan citra;

    public Hasil(int detectedObjectCount, CitraKeabuan citra) {
        this.detectedObjectCount = detectedObjectCount;
        this.citra = citra;
    }

    public int getDetectedObjectCount() {
        return detectedObjectCount;
    }

    public CitraKeabuan getCitra() {
        return citra;
    }
}
