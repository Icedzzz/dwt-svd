/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Dolores Pot Hol
 */
public class ArrayHandle {
    
    public Double[] to1DArray(Double[][] arr) {
        Double[] result = new Double[arr.length*arr[0].length];
        
        for(int i=0, a=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++, a++) {
                result[a] = arr[i][j];
            }
        }
        
        return result;
    }
    
    public double[] to1DArray(double[][] arr) {
        double[] result = new double[arr.length*arr[0].length];
        
        for(int i=0, a=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++, a++) {
                result[a] = arr[i][j];
            }
        }
        
        return result;
    }
    
    public Double[][] arr1DArraysTo1DArray(Double[][][] arr) {
        Double[][] result = new Double[arr.length][arr[0].length*arr[0][0].length];
        
        for(int n=0; n<arr.length; n++) {
            result[n] = this.to1DArray(arr[n]);
        }
        
        return result;
    }
    
    public double[][] arr1DArraysTo1DArray(double[][][] arr) {
        double[][] result = new double[arr.length][arr[0].length*arr[0][0].length];
        
        for(int n=0; n<arr.length; n++) {
            result[n] = this.to1DArray(arr[n]);
        }
        
        return result;
    }
    
    public Double[][] toDouble(double[][] arr) {
        Double[][] result = new Double[arr.length][arr[0].length];
        
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                result[i][j] = arr[i][j];
            }
        }
        
        return result;
    }
    
    public double[][] todouble(Double[][] arr) {
        double[][] result = new double[arr.length][arr[0].length];
        
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                result[i][j] = arr[i][j];
            }
        }
        
        return result;
    }
    
    public void print(Double[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j]+ "  ");
            }
            System.out.println("");
        }
    }
    
    public void print(double[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j]+ "  ");
            }
            System.out.println("");
        }
    }
}
