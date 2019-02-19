/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;
import View.Home;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.tampilkanPanel(main);
    }
    
    private void tampilkanPanel(Main main){
        Home home = new Home(main);
        home.setVisible(true);
    }
    
    public CitraWarna muatGambar(){
       JFileChooser chooser = new JFileChooser();
       CitraWarna warna = null;
       if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){          
           File f = chooser.getSelectedFile();
           try {
               BufferedImage img = ImageIO.read(f);
               warna = new CitraWarna(img);
               warna.setSize((f.length()/1024));
               warna.setNama_file(f.getName());
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       return warna;
    }
    
    public CitraKeabuan do_graysacle(CitraWarna citra){
        Prapengolahan pra_proses = new Prapengolahan();
        return pra_proses.doGrayScale(citra);
    }
    
    public CitraKeabuan dekomposisi(CitraKeabuan citra){
        DWT dwt = new DWT();
        dwt.dekomposisi(citra.getP());
        return new CitraKeabuan(dwt.getLL());
    }
    
}
