import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static regex.RegexConst.*;

/**
 * Created by i.viktor on 04/11/2016.
 */
public class MyStream {
    private final String resourceName;
    private LinkType type;

    public MyStream(String resourceName) {
        this.resourceName = resourceName;
    }

    private void validateLink() {
        if (resourceName.matches(URL_VALIDATE_REGEX)) type = LinkType.URL;
        else type = LinkType.Local_File;
    }

    public InputStream getInputStream() throws IOException {
        validateLink();
        switch (type) {

            case URL: {
                return new URL(resourceName).openStream();
            }
            case Local_File:
                return new FileInputStream(resourceName);
            default:
                return null;
        }
    }
}
