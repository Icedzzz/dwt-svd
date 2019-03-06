/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ArrayComparator;
import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author Dolores Pot Hol
 */
public class FeaturesBlock implements Comparable<FeaturesBlock> {
    private Double[] features;
    private Point startPoint;

    public FeaturesBlock() {
    }

    public FeaturesBlock(Double[] features, Point startPoint) {
        this.features = features;
        this.startPoint = startPoint;
    }
    
    public FeaturesBlock(double[] features, Point startPoint) {
        Double[] temp = new Double[features.length];
        
        for(int i=0; i<features.length; i++) {
            temp[i] = features[i];
        }
        
        this.features = temp;
        this.startPoint = startPoint;
    }

    @Override
    public String toString() {
        return Arrays.toString(features) + "  " + this.startPoint;
    }    

    public void setFeatures(Double[] features) {
        this.features = features;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    
    @Override
    public int compareTo(FeaturesBlock fb) {        
        int compare = Arrays.toString(features).compareTo(Arrays.toString(fb.features));
        
        if(compare > 0) return 1;
        else if(compare < 0) return -1;
        else return compare;
    }        

    public Double[] getFeatures() {
        return features;
    }

    public Point getStartPoint() {
        return startPoint;
    }
}
