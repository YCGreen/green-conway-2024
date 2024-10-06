package green.conway;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TextProcessor {

    public TextProcessor() {

    }

    public String UrlToString(URL url) {

        InputStream in = null;
        try {
            in = url.openStream();
            return  IOUtils.toString( in );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String FileToString(File file) {
        FileInputStream fisTargetFile = null;
        try {
            fisTargetFile = new FileInputStream(file);
            return IOUtils.toString(fisTargetFile, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUrl(Object url) {
        try {
            String urlString = url.toString();
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
