/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author M.Hakim Amransyah
 */
public class DWT {
   
   private int[][] LL;
   private int[][] LH;
   private int[][] HL;
   private int[][] HH;
   
   public int[][] dekomposisi(int[][] img){   
       int dec_img[][] = new int[img.length][img[0].length];
       dec_img = this.dekomposisi_horizontal(dec_img, img);
       dec_img = this.dekomposisi_vertikal(dec_img);
       this.setPartImage(dec_img);
       return dec_img;
   }
   
   private void setPartImage(int dec_img[][]){
       int dimensi_baris = (dec_img.length/2);
       int dimensi_kolom = (dec_img[0].length)/2; 
       this.LL      = new int[dimensi_baris][dimensi_kolom];
       this.LH      = new int[dimensi_baris][dimensi_kolom];
       this.HL      = new int[dimensi_baris][dimensi_kolom];
       this.HH      = new int[dimensi_baris][dimensi_kolom];
       
       //set LL
       for(int i=0;i<dimensi_baris;i++){
           for(int j=0;j<dimensi_kolom;j++){
               this.LL[i][j] = dec_img[i][j];
           }
       }
       
       //set LH
       int index_kolom;
       for(int i=0;i<this.LH.length;i++){
           index_kolom = dimensi_kolom;
           for(int j=0;j<this.LH[i].length;j++){
               this.LH[i][j] = dec_img[i][index_kolom];
               index_kolom++;
           }
       }
       
       //set HL
       int index_baris = dimensi_baris;
       for(int i=0;i<this.HL.length;i++){
           for(int j=0;j<this.HL[i].length;j++){
               this.HL[i][j] = dec_img[index_baris][j];
           }
           index_baris++;
       }
              
       //set HH
       index_baris = dimensi_baris;
       for(int i=0;i<this.HH.length;i++){
           index_kolom = dimensi_kolom;
           for(int j=0;j<this.HH[0].length;j++){
               this.HH[i][j] = dec_img[index_baris][index_kolom];
               index_kolom++;
           }
            index_baris++;
       }
       
       
   }
   
   private int[][] dekomposisi_horizontal(int dec_img[][],int img[][]){
       int temp_value;
       int index_kolom;
       for(int i=0;i<img.length;i++){
           index_kolom = 0;
           for(int j=0;j<img[i].length;j++){
               if((index_kolom + 2) <= img[i].length && j < (img[i].length/2)){
                  temp_value = img[i][index_kolom];
                  index_kolom++;
                  temp_value = (temp_value+img[i][index_kolom])/2;
                  dec_img[i][j] = temp_value; 
                  index_kolom++;   
               }else{
                   if(j == (img[i].length/2)){
                     index_kolom = 0;                       
                   }
                   temp_value = img[i][index_kolom];
                   index_kolom++;
                   temp_value = (temp_value-img[i][index_kolom])/2;
                   dec_img[i][j] = temp_value; 
                   index_kolom++;
               }
               
           }
       }
       return dec_img;
   }
   
   public int[][] dekomposisi_vertikal(int dec_img[][]){
       int res[][]  = new int[dec_img.length][dec_img[0].length];
       int temp_value;
       int index_baris;
       for(int j=0;j<dec_img[0].length;j++){
           index_baris = 0;
           for(int i=0;i<dec_img.length;i++){
               if((index_baris+2) <= dec_img.length && i < (dec_img.length/2)){
                  temp_value = dec_img[index_baris][j];
                  index_baris++;
//                  System.out.print("("+temp_value+" + "+dec_img[index_baris][j]+")");
                  temp_value = (temp_value+dec_img[index_baris][j])/2;
                  res[i][j]= temp_value; 
                  index_baris++;   
               }else{
                   if(i == (dec_img.length/2)){
                     index_baris = 0;                       
                   }
                   temp_value = dec_img[index_baris][j];
                   index_baris++;
//                   System.out.print("("+temp_value+" - "+dec_img[index_baris][j]+") (ib="+index_baris+")");
                   temp_value = (temp_value-dec_img[index_baris][j])/2;
                   res[i][j]= temp_value; 
                   index_baris++;
               }
           }
//           System.out.println("");
       }
       return res;
   }
   
    public void cetakImage(int[][] img){
        for(int i=0;i<img.length;i++){
            for(int j=0;j<img[i].length;j++){
                System.out.print(img[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public double[][] getLLDouble(){
        double[][] _LL = new double[this.LL.length][this.LL[0].length];
        for(int i=0;i<this.LL.length;i++){
            for(int j=0;j<this.LL[i].length;j++){
                _LL[i][j] = this.LL[i][j];
            }
        }
        return _LL;
    }
    
    public double[][] getLHDouble(){
        double[][] _LH = new double[this.LH.length][this.LH[0].length];
        for(int i=0;i<this.LH.length;i++){
            for(int j=0;j<this.LH[i].length;j++){
                _LH[i][j] = this.LH[i][j];
            }
        }
        return _LH;
    }
    
    public double[][] getHLDouble(){
        double[][] _HL = new double[this.HL.length][this.HL[0].length];
        for(int i=0;i<this.HL.length;i++){
            for(int j=0;j<this.HL[i].length;j++){
                _HL[i][j] = this.HL[i][j];
            }
        }
        return _HL;
    }
    
    public double[][] getHHDouble(){
        double[][] _HH = new double[this.HH.length][this.HH[0].length];
        for(int i=0;i<this.HH.length;i++){
            for(int j=0;j<this.HH[i].length;j++){
                _HH[i][j] = this.HH[i][j];
            }
        }
        return _HH;
    }
    
    public int[][] getLL() {
        return LL;
    }

    public int[][] getLH() {
        return LH;
    }

    public int[][] getHL() {
        return HL;
    }

    public int[][] getHH() {
        return HH;
    }
   
}
