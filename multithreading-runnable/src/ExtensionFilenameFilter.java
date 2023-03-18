import java.io.File;
import java.io.FilenameFilter;

public class ExtensionFilenameFilter implements FilenameFilter {

    public static final String TXT = "txt";

    private final String extension;

    public ExtensionFilenameFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("." + extension);
    }
}
