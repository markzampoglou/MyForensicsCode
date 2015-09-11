/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buildcnntrainingset;

import Utils.Misalign;
import Utils.Util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author marzampoglou
 */
public class BuildCNNTrainingSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GetFileList("/media/marzampoglou/3TB/markzampoglou/ImageForensics/Datasets/UCID/ucid.v2");
    }
    
    
    public static void GetFileList(String SourcePath) {
        File F=new File(SourcePath);
        File[] AllFiles=F.listFiles();
        System.out.println(AllFiles.length);
        BufferedImage RecompressedImage;
        BufferedImage NARecompressedImage;
        BufferedImage OrigImage=null;
        int BaseJPEGQuality;
        int SecondJPEGQuality;
        for(int ii=0;ii<AllFiles.length;ii++){
            BaseJPEGQuality=95;
            SecondJPEGQuality=30;
            try {
                System.out.println(AllFiles[ii].getAbsolutePath());
                OrigImage = ImageIO.read(new File(AllFiles[ii].getAbsolutePath()));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            RecompressedImage = Util.RecompressImage(OrigImage, BaseJPEGQuality);
            NARecompressedImage=Misalign.misalign(OrigImage, 3, 6, SecondJPEGQuality);
            
            try {
                ImageIO.write(NARecompressedImage,"PNG",new File("tmp.png"));
            } catch (IOException ex) {
                Logger.getLogger(BuildCNNTrainingSet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Thread.sleep(5000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
                    
            int[][][] OrigByteImage = Util.GetRGBArray(OrigImage);
            int[][][] RecompressedByteImage = null;
            
        }
    }
    
}
