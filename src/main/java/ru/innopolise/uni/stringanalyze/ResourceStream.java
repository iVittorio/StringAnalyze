package ru.innopolise.uni.stringanalyze;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static ru.innopolise.uni.stringanalyze.constants.Const.*;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class ResourceStream {
    private final String resourceName;
    private LinkType type;

    public ResourceStream(String resourceName) {
        this.resourceName = resourceName;
    }

    private void validateLink() {
        if (resourceName.matches(URL_VALIDATE_REGEX)) type = LinkType.URL;
        else type = LinkType.Local_File;
    }


    /**
     * Returns InputStream depending on the name. If name stars with "word://" return InputStream
     * from the URL connection, else return FileInputStream.
     *
     * @return an input stream for reading from the URL connection or from the file.
     * @throws IOException if an I/O exception occurs.
     */
    public InputStream getInputStream() throws IOException {
        validateLink();
        switch (type) {

            case URL: {
                return new URL(resourceName).openStream();
            }
            case Local_File:
                return new FileInputStream(resourceName);
            default:
                throw new IOException("Wrong file name");
        }
    }
}
