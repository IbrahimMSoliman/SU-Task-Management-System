package tms.beans.common;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import tms.beans.users.user_details;
import tms.common.common_functions;

@Named("pdf_document_viewer")
@SessionScoped
public class PDF_document_viewer implements Serializable
    {
    @Inject
    user_details user_details;

    private String window_title;
    private String download_file_name;
    private byte[] data;
    private StreamedContent stream;
    private String error_message;

    public void start(String window_title, String download_file_name, byte[] data)
        {
        this.window_title = window_title;
        this.download_file_name = download_file_name;
        this.data = data;
        stream = DefaultStreamedContent.builder().stream(() -> new ByteArrayInputStream(data)).contentType("application/pdf").name(download_file_name).build();
        common_functions.getInstance().open_closable_dialog("/common/pdf_document_viewer", false, 850, 600, false, true, true);
        }

    public String getWindow_title()
        {
        return window_title;
        }

    public void setWindow_title(String window_title)
        {
        this.window_title = window_title;
        }

    public String getDownload_file_name()
        {
        return download_file_name;
        }

    public void setDownload_file_name(String download_file_name)
        {
        this.download_file_name = download_file_name;
        }

    public byte[] getData()
        {
        return data;
        }

    public void setData(byte[] data)
        {
        this.data = data;
        }

    public StreamedContent getStream()
        {
        return stream;
        }

    public String getError_message()
        {
        return error_message;
        }

    public void setError_message(String error_message)
        {
        this.error_message = error_message;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    }
