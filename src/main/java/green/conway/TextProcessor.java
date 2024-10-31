package green.conway;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TextProcessor {

    public TextProcessor() {

    }

    public String urlToString(URL url) throws IOException {
        InputStream in = url.openStream();
        return IOUtils.toString(in);
    }

    public String fileToString(File file) throws IOException {
        FileInputStream fisTargetFile = new FileInputStream(file);
        return IOUtils.toString(fisTargetFile, "UTF-8");
    }

    public boolean isUrl(String url) throws MalformedURLException {
        new URL(url);
        return true;
    }

}
