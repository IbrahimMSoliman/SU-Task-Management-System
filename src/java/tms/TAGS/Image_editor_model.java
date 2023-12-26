package tms.TAGS;

import tms.common.Images_functions;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import jakarta.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import tms.beans.common.msgr;
//onclick="remoteFunctionForUpdate([{name: 'param1', value: imageEditor.toDataURL()}]);" it must be in the command button which save the new image

/**
 *
 * @author hima
 */
public class Image_editor_model
    {

    private String imageBase64;
    private String hidden_image_variable = "First Value";

    public Image_editor_model()
        {
        }

    public Image_editor_model(String image_path)
        {
        try
            {
            File file = new File(image_path);
            if(file.exists())
                imageBase64 = "data:image/jpeg;base64," + encodeFileToBase64Binary(file);
            else
                imageBase64 = "";
            }
        catch(Exception ee)
            {
            }
        }

    private static String encodeFileToBase64Binary(File file) throws Exception
        {
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
        }

    public void updateImageContents()
        {
        imageBase64 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param1");
        }

    public void handleFileUpload(FileUploadEvent event)
        {
        String extension = FilenameUtils.getExtension(event.getFile().getFileName()).toUpperCase();
        if(extension.equals("PNG") || extension.equals("JPG") || extension.equals("JPEG") || extension.equals("BMP") || extension.equals("GIF"))
            try
            {
            BufferedImage buf = ImageIO.read(event.getFile().getInputStream());
            BufferedImage new_buf = Images_functions.resize_photo(buf, 800);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(new_buf, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            imageBase64 = new String(Base64.encodeBase64(imageBytes), "UTF-8");
            imageBase64 = "data:image/jpeg;base64," + imageBase64;
            }
        catch(Exception ee)
            {
            msgr.error("Error :" + ee.getMessage());
            }
        else
            msgr.error("Invalid image format......");
        }

    public String getImageBase64()
        {
        return imageBase64;
        }

    public void setImageBase64(String imageBase64)
        {
        this.imageBase64 = imageBase64;
        }

    public String getHidden_image_variable()
        {
        return hidden_image_variable;
        }

    public void setHidden_image_variable(String hidden_image_variable)
        {
        this.hidden_image_variable = hidden_image_variable;
        }
    }
