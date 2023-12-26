package tms.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author isoliman
 */
public class FilesManagement
    {
    public static boolean Save_File_To_Path(String path, InputStream stream)
        {
        try
            {
            //create directory
            Files.createDirectories(new File(path).toPath().getParent());
            //copy the file to destination
            Files.copy(stream, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            }
        catch(Exception ee)
            {
            ee.printStackTrace();
            return false;
            }
        return true;
        }

    public static void delete_directory(String DirPath)
        {
        try
            {
            FileUtils.deleteDirectory(new File(DirPath));
            }
        catch(IOException ex)
            {
            }
        }

    public static UploadedFile create_UploadFile_from_path(String path)
        {
        UploadedFile uploadfile = null;
        try
            {
            uploadfile = new ByteArrayUploadedFile(path);
            }
        catch(IOException ex)
            {
            }
        return uploadfile;
        }

    public void create_zipped_file_from_path(String folder_path, String zipped_file_name)
        {

        }

    public static StreamedContent CreateStreamedContent(InputStream targetStream, String fileName)
        {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return DefaultStreamedContent.builder().contentType(fileNameMap.getContentTypeFor(fileName)).stream(() -> targetStream).name(fileName).build();
        }

    }
