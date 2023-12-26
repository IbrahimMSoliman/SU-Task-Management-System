package tms.TAGS;

import tms.application.TMSApplication;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author isoliman
 */
public class Files_uploader
    {
    private List<Files_class> files_list = new ArrayList<>();

    public void handleFileUpload(FileUploadEvent event)
        {
        String file_name = event.getFile().getFileName();
        files_list.add(new Files_class(file_name, FilenameUtils.getBaseName(file_name), FilenameUtils.getExtension(file_name).toUpperCase(), event.getFile().getContent()));
        }

    public void clear()
        {
        files_list.clear();
        }

    public boolean clear_then_upload_files_to_directory(String relative_directory_path)
        {
        remove_directory(relative_directory_path);
        return upload_files_to_directory(relative_directory_path);
        }

    public boolean upload_files_to_directory(String relative_directory_path)
        {
        try
            {
            if(!files_list.isEmpty())
                for(Files_class uf : files_list)
                    {
                    //make dirs
                    File dir = new File(TMSApplication.getUpload_folder_path() + File.separator + relative_directory_path);
                    if(dir.exists() == false)
                        dir.mkdirs();

                    String relative_path = relative_directory_path + File.separator + RandomStringUtils.randomAlphanumeric(12) + "." + uf.file_type;
                    File file = new File(TMSApplication.getUpload_folder_path() + File.separator + relative_path);
                    //create directory
                    Files.createDirectories(file.toPath().getParent());
                    //copy the file to destination
                    Files.copy(new ByteArrayInputStream(uf.file_byte), Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
                    //update relative path
                    uf.setRelative_path(relative_path);
                    }
            }
        catch(Exception ee)
            {
            remove_directory(relative_directory_path);
            return false;
            }
        return true;
        }

    public void remove_directory(String relative_directory_path)
        {
        try
            {
            Files.walk(Paths.get(TMSApplication.getUpload_folder_path() + File.separator + relative_directory_path)).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
        catch(Exception ee)
            {
            }
        }

    public void add_to_files_list(String file_name, String file_title, String file_type, byte[] file_byte)
        {
        if(files_list == null)
            files_list = new ArrayList<>();
        files_list.add(new Files_class(file_name, file_title, file_type, file_byte));
        }

    public class Files_class
        {
        private String file_name;
        private String file_title;
        private String file_type;
        private String relative_path;
        private byte[] file_byte;

        public Files_class(String file_name, String file_title, String file_type, byte[] file_byte)
            {
            this.file_name = file_name;
            this.file_title = file_title;
            this.file_type = file_type;
            this.file_byte = file_byte;
            }

        //New constructor to get file from server
        public Files_class(String relative_path, String file_title)
            {
            this.relative_path = relative_path;
            this.file_name = FilenameUtils.getName(relative_path);
            this.file_title = file_title;
            this.file_type = FilenameUtils.getExtension(file_name);
            try
                {
                File file = new File(TMSApplication.getUpload_folder_path() + File.separator + relative_path);
                if(file.exists())
                    file_byte = FileUtils.readFileToByteArray(file);
                }
            catch(Exception ee)
                {
                }
            }

        public void delete()
            {
            files_list.remove(this);
            }

        public StreamedContent download()
            {
            StreamedContent downloadFile = null;
            try
                {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                downloadFile = DefaultStreamedContent.builder().contentType(externalContext.getMimeType(file_name)).stream(() -> new ByteArrayInputStream(file_byte)).name(file_name).build();//=new DefaultStreamedContent(new ByteArrayInputStream(file_byte), externalContext.getMimeType(file_name), file_name);
                }
            catch(Exception ex)
                {
                }
            return downloadFile;
            }

        public String getRelative_path()
            {
            return relative_path;
            }

        public void setRelative_path(String relative_path)
            {
            this.relative_path = relative_path;
            }

        public String getFile_name()
            {
            return file_name;
            }

        public void setFile_name(String file_name)
            {
            this.file_name = file_name;
            }

        public String getFile_title()
            {
            return file_title;
            }

        public void setFile_title(String file_title)
            {
            this.file_title = file_title;
            }

        public String getFile_type()
            {
            return file_type;
            }

        public void setFile_type(String file_type)
            {
            this.file_type = file_type;
            }

        public byte[] getFile_byte()
            {
            return file_byte;
            }

        public void setFile_byte(byte[] file_byte)
            {
            this.file_byte = file_byte;
            }

        }

    public List<Files_class> getFiles_list()
        {
        return files_list;
        }

    public void setFiles_list(List<Files_class> files_list)
        {
        this.files_list = files_list;
        }

    }
