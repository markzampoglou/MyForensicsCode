/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.image.BufferedImage;

/**
 *
 * @author marzampoglou
 * 
 * Realign images by adding N horizontal and M vertical pixels to the top/left, and 8-N and 8-M pixels to the bottom right.
 */

public class Misalign {
    public static BufferedImage dealign(BufferedImage ImageIn,int OffsetX, int OffsetY) {
        int[][][] ByteImageIn=Util.GetRGBArray(ImageIn);
        int[][][] ByteDealigned=new int[ByteImageIn.length][ByteImageIn[1].length+8][ByteImageIn[1][1].length+8];
        
        for(int C=0;C<ByteDealigned.length;C++){
            for(int X=OffsetX;X<ByteDealigned[1].length-(8-OffsetX);X++){
                for(int Y=OffsetY;Y<ByteDealigned[1][1].length-(8-OffsetY);Y++){
                    ByteDealigned[C][X][Y]=ByteImageIn[C][X-OffsetX][Y-OffsetY];
                }
            }
        }
        System.out.println(ByteDealigned[2][15][15]);
        BufferedImage Realigned=Util.CreateImFromArray(ByteDealigned);
        return Realigned;
    }
    
    public static BufferedImage realign(BufferedImage ImageIn,int OffsetX, int OffsetY) {
        int[][][] ByteImageIn=Util.GetRGBArray(ImageIn);
        int[][][] ByteRealigned=new int[ByteImageIn.length][ByteImageIn[1].length-8][ByteImageIn[1][1].length-8];
        
        for(int C=0;C<ByteRealigned.length;C++){
            for(int X=OffsetX;X<ByteRealigned[0].length;X++){
                for(int Y=OffsetY;Y<ByteRealigned[0][0].length;Y++){
                    ByteRealigned[C][X-OffsetX][Y-OffsetY]=ByteImageIn[C][X][Y];
                }
            }
        }
        BufferedImage Realigned=Util.CreateImFromArray(ByteRealigned);
        return Realigned;
    }
    public static BufferedImage misalign(BufferedImage ImageIn, int OffsetX, int OffsetY, int JPEGQuality){
        BufferedImage DealignedImageIn=dealign(ImageIn,OffsetX,OffsetY);
        BufferedImage Recompressed=Util.RecompressImage(DealignedImageIn, JPEGQuality);
        BufferedImage RealignedImage=realign(Recompressed,OffsetX,OffsetY);
        
        return RealignedImage;
    }
    
    
}
