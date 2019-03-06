/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.FeaturesBlock;
import Model.Parameter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Dolores Pot Hol
 */
public class DetectCopyMove {
    private double[][] imgMat;
    private Parameter parameter;
    private int detectedObject;
    
    public DetectCopyMove(double[][] imgMat, Parameter parameter) {
        this.imgMat = imgMat;
        this.parameter = parameter;
    }
    
    public CitraKeabuan process(boolean useSVD) {
        Process p = new Process();
        FeaturesBlock[] overlappedBlockFeatures;
        
        if(useSVD)
             overlappedBlockFeatures = p.getOverlappedFeatureBlockSVD(imgMat, parameter.getWidthOverlappedBlock(), parameter.getHeightOverlappedBlock());
        else
            overlappedBlockFeatures = p.getOverlappedFeatureBlock(imgMat, parameter.getWidthOverlappedBlock(), parameter.getHeightOverlappedBlock());
        
        LinkedList<Point[]> coupled = new LinkedList();            
            
        Arrays.sort(overlappedBlockFeatures);

        System.out.println("loop 1");
//            System.out.println("max "+overlappedBlockFeatures.length);
        for(int i=0; i<overlappedBlockFeatures.length-1; i++) {
//                System.out.println("overlappedBlockFeatures "+i);
            for(int j=i+1; j<i+this.parameter.getNn(); j++) {
                if(j==overlappedBlockFeatures.length-1) break;

                if(overlappedBlockFeatures[i].compareTo(overlappedBlockFeatures[j]) == 0) {
                    coupled.add(new Point[]{
                        overlappedBlockFeatures[i].getStartPoint(),overlappedBlockFeatures[j].getStartPoint()
                    });                     
                }                                        
            }
        }            

        HashMap<String, LinkedList<Point[]>> coupledFrequented = new HashMap();

        for(Point[] c : coupled) {
            int xi = c[0].x,
                xj = c[1].x;
            int yi = c[0].y,
                yj = c[1].y;

            String distanceString = Math.abs(xi-xj) +","+ Math.abs(yi-yj);

            if(coupledFrequented.containsKey(distanceString)) {
                coupledFrequented.get(distanceString).add(c);
            } else {
                LinkedList<Point[]> temp = new LinkedList();

                temp.add(c);

                coupledFrequented.put(distanceString, temp);
            }
        }

        LinkedList<String> toRemove = new LinkedList();

        Set<String> keys = coupledFrequented.keySet();

        for(String key : keys) {
            if(coupledFrequented.get(key).size() < this.parameter.getNf())
                toRemove.add(key);
        }

        for(String key : toRemove) {
            coupledFrequented.remove(key);
        }
        
        this.detectedObject = coupledFrequented.keySet().size();

        BufferedImage result = new BufferedImage(this.imgMat[0].length*2, this.imgMat.length*2, BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D g = result.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, result.getWidth(), result.getHeight());                       

        g.setColor(new Color(255,255,255,127));

        for(String key : coupledFrequented.keySet()) {
            LinkedList<Point[]> couples = coupledFrequented.get(key);

            Color c = new Color(255, 255, 255);

            for(Point[] couple : couples) {   
                g.drawLine(couple[0].x *2, couple[0].y *2, couple[1].x *2+1, couple[1].y *2+1);

                result.setRGB(couple[0].x *2, couple[0].y *2, c.getRGB());
                result.setRGB(couple[0].x *2+1, couple[0].y *2, c.getRGB());
                result.setRGB(couple[0].x *2, couple[0].y *2+1, c.getRGB());
                result.setRGB(couple[0].x *2+1, couple[0].y *2+1, c.getRGB());

                result.setRGB(couple[1].x *2, couple[1].y *2, c.getRGB());
                result.setRGB(couple[1].x *2+1, couple[1].y *2, c.getRGB());
                result.setRGB(couple[1].x *2, couple[1].y *2+1, c.getRGB());
                result.setRGB(couple[1].x *2+1, couple[1].y *2+1, c.getRGB());
            }
        }
            
        return new CitraKeabuan(result);  
    }

    public int getDetectedObject() {
        return detectedObject;
    }        
}
