package tms.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author hima
 * https://simplesolution.dev/generate-thumbnail-images-in-java-with-thumbnailator-library/
 */
public class Images_functions
    {
    public static boolean createthumbnail(String orginal_photo_path, String thumbnail_folder_path, int width)
        {
        File file = new File(thumbnail_folder_path + File.separator + FilenameUtils.getName(orginal_photo_path));
        if(file.exists())
            file.delete();
        try
            {
            Thumbnails.of(new File(orginal_photo_path)).width(width).toFile(new File(thumbnail_folder_path + File.separator + FilenameUtils.getName(orginal_photo_path)));
            }
        catch(Exception ee)
            {
            ee.printStackTrace();
            return false;
            }
        return true;
        }

    public static BufferedImage resize_photo(BufferedImage bufimage, int width) throws IOException
        {
        //BufferedImage buf = ImageIO.read(in);
        int photo_width = bufimage.getWidth();
        if(photo_width > width)
            return Thumbnails.of(bufimage).width(width).asBufferedImage();
        else
            return bufimage;
        }

    private static boolean is_file_exist(String thumbnail_path)
        {
        File file = new File(thumbnail_path);
        return file.exists();
        }

    public static String encodeFileToBase64Binary(String file_path)
        {
        try
            {
            File file = new File(file_path);
            if(file.exists())
                {
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fileInputStreamReader.read(bytes);
                return "data:image/jpg;base64," + new String(Base64.encodeBase64(bytes), "UTF-8");
                }
            else
                return "";
            }
        catch(Exception ee)
            {
            return "";
            }
        }

    }
