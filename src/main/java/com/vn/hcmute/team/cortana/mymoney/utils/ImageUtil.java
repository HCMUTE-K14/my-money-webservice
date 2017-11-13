package com.vn.hcmute.team.cortana.mymoney.utils;

import com.vn.hcmute.team.cortana.mymoney.Constraint;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtil {
    
    public static boolean uploadImage_Local(String name, String folder, InputStream inputStream) {
        boolean flag = false;
        try {
            File _file = new File(Constraint.LOCATION_STORE_IMAGE + folder);
            _file.mkdirs();
            Image image = ImageIO.read(inputStream);
            
            BufferedImage bi = createResizedCopy(image, 500, 500, true);
            ImageIO.write(bi, "png",
                      new File(Constraint.LOCATION_STORE_IMAGE + folder + "/" + name + ".png"));
            
            flag = true;
        } catch (IOException e) {
            flag = false;
        }
        return flag;
    }
    
    public static boolean deleteImage(String name, String folder) {
        File file = new File(Constraint.LOCATION_STORE_IMAGE + folder + "/" + name + ".png");
        
        if (file.exists()) {
            file.delete();
            return true;
        } else {
            file = new File(Constraint.LOCATION_STORE_IMAGE + folder + "/" + name + ".jpg");
            if (file.exists()) {
                file.delete();
                return true;
            }
        }
        return false;
    }
    
    public static BufferedImage createResizedCopy(Image originalImage, int scaledWidth,
              int scaledHeight,
              boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
