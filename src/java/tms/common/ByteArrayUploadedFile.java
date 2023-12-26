package tms.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.file.UploadedFile;

public class ByteArrayUploadedFile implements UploadedFile
    {

    private byte[] data = null;

    private String filename = "";

    private String contentType = "";

    public ByteArrayUploadedFile(String file_path) throws IOException
        {
        File file = new File(file_path);
        if(file.exists())
            {
            this.data = FileUtils.readFileToByteArray(file);
            this.filename = file.getName();
            this.contentType = "application/octet-stream";
            }
        }

    @Override
    public String getFileName()
        {
        return filename;
        }

    public InputStream getInputstream() throws IOException
        {
        return new ByteArrayInputStream(data);
        }

    @Override
    public long getSize()
        {
        return data.length;
        }

    public byte[] getContents()
        {
        return data;
        }

    @Override
    public String getContentType()
        {
        return contentType;
        }

    @Override
    public void write(String filePath) throws Exception
        {
        try(FileOutputStream fos = new FileOutputStream(filePath))
            {
            fos.write(data);
            }
        }

    public List<String> getFileNames()
        {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    @Override
    public InputStream getInputStream() throws IOException
        {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    @Override
    public byte[] getContent()
        {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    @Override
    public void delete() throws IOException
        {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
