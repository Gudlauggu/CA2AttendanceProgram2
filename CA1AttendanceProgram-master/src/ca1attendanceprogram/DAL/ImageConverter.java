/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mecaa
 */
public class ImageConverter
  {

    public static BufferedImage createRGBImage(byte[] bytes, int width, int height)
      {
        DataBufferByte buffer = new DataBufferByte(bytes, bytes.length);
        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[]
          {
            8, 8, 8
          }, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, Raster.createInterleavedRaster(buffer, (width-1), (height-1), (width-1) * 3, 3, new int[]
          {
            0, 1, 2
          }, null), false, null);
      }

    public byte[] getAImageAsBytesForTest()
      {
        try
          {

            BufferedImage originalImage
                    = ImageIO.read(new File("DATA/Tester.jpg"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream(64000);
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;

          }
        catch (IOException e)
          {
            System.out.println(e.getMessage());
          }
        return null;
      }
  }
