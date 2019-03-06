/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Jama.Matrix;
import Model.CitraKeabuan;
import Model.CitraWarna;
import Model.FeaturesBlock;
import java.awt.Point;



/**
 *
 * @author M.Hakim Amransyah
 */
public class Process {
           
    public Process(){
   
    }
    
    public CitraKeabuan doGrayScale(CitraWarna gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();     
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
              new_p[i][j] = (gambar.getR()[i][j] + gambar.getG()[i][j] + gambar.getB()[i][j])/3;
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    public CitraKeabuan doBinerisasi(CitraKeabuan gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
             new_p[i][j] = this.treshold(gambar.getP()[i][j]);
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }
    
    public CitraKeabuan doInvers(CitraKeabuan gambar){
       int baris = gambar.getBaris();
       int kolom = gambar.getKolom();
       int new_p[][] = new int[baris][kolom];
       for(int i=0;i<baris;i++){
           for(int j=0;j<kolom;j++){
             new_p[i][j] = 255-gambar.getP()[i][j]; //or G or B
           }
       }
       CitraKeabuan gambar_2 = new CitraKeabuan(new_p);
       return gambar_2;
    }        
    
    private int treshold(int x){
        int n= 0;
        if(x <= 128){
            n = 0;
        }else{
            n = 255;
        }
        return n;
    }
     
    public double[][] normalisasi_citra(int citra[][]){
        double norm_citra[][] = new double[citra.length][citra[0].length];
        double max  = 255;
        double min  = 0;
        
        for(int i=0;i<citra.length;i++){
            for(int j=0;j<citra[i].length;j++){
                norm_citra[i][j] = (citra[i][j] - min)/(max-min);
            }
        }
        
        return norm_citra;
    }
    
    public double[][][] getOverlappedFeature(double[][] matrix, int heightBlock, int widthBlock) {
        int blockCount = (matrix.length-heightBlock+1) * (matrix[0].length-widthBlock+1);
        
        double[][][] result = new double[blockCount][heightBlock][widthBlock];
        
        for(int blockRow=0, blockN=0; blockRow<matrix.length-heightBlock+1; blockRow++) {
            for(int blockCol=0; blockCol<matrix[0].length-widthBlock+1; blockCol++, blockN++) {
                for(int i=0; i<heightBlock; i++) {
                    for(int j=0; j<widthBlock; j++) {
                        result[blockN][i][j] = matrix[blockRow+i][blockCol+j];
                    }
                }
            }
        }
        
        return result;
    }
    
    public FeaturesBlock[] getOverlappedFeatureBlock(double[][] matrix, int heightBlock, int widthBlock) {
        int blockCount = (matrix.length-heightBlock+1) * (matrix[0].length-widthBlock+1);
        
        ArrayHandle ah = new ArrayHandle();
        
        FeaturesBlock[] result = new FeaturesBlock[blockCount];
//        System.out.println("getOverlappedFeatureBlock "+blockCount);    
        for(int blockRow=0, blockN=0; blockRow<matrix.length-heightBlock+1; blockRow++) {
            for(int blockCol=0; blockCol<matrix[0].length-widthBlock+1; blockCol++, blockN++) {                                                
                Point startPoint = new Point(blockCol, blockRow);
                Double[][] blockFeature = new Double[heightBlock][widthBlock];
                
                for(int i=0; i<heightBlock; i++) {
                    for(int j=0; j<widthBlock; j++) {
                        blockFeature[i][j] = matrix[blockRow+i][blockCol+j];
                    }
                }
                
                result[blockN] = new FeaturesBlock(ah.to1DArray(blockFeature), startPoint);
//                System.out.println(blockN);
            }
        }        
            
        return result;
    }
    
    public FeaturesBlock[] getOverlappedFeatureBlockSVD(double[][] matrix, int heightBlock, int widthBlock) {
        int blockCount = (matrix.length-heightBlock+1) * (matrix[0].length-widthBlock+1);                
        
        FeaturesBlock[] result = new FeaturesBlock[blockCount];
//        System.out.println("getOverlappedFeatureBlock "+blockCount);    
        for(int blockRow=0, blockN=0; blockRow<matrix.length-heightBlock+1; blockRow++) {
            for(int blockCol=0; blockCol<matrix[0].length-widthBlock+1; blockCol++, blockN++) {                                                
                Point startPoint = new Point(blockCol, blockRow);
                double[][] blockFeature = new double[heightBlock][widthBlock];
                
                for(int i=0; i<heightBlock; i++) {
                    for(int j=0; j<widthBlock; j++) {
                        blockFeature[i][j] = matrix[blockRow+i][blockCol+j];
                    }
                }
                
                ;
                result[blockN] = new FeaturesBlock(new Matrix(blockFeature).svd().getSingularValues(), startPoint);
//                System.out.println(blockN);
            }
        }        
            
        return result;
    }
}
