import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");

        // Find all TXT files in parent directory
        String srcDirStr = "../";
        File srcDir = new File(srcDirStr);
        File[] files = srcDir.listFiles(new ExtensionFilenameFilter(ExtensionFilenameFilter.TXT));
        if (files == null) {
            throw new FileNotFoundException("No TXT files in " + srcDirStr);
        }

        // Firstly, run it for all files in this thread
        NameCounter allFilesNameCounter = new NameCounter(files, "Alexander", System.currentTimeMillis());
        allFilesNameCounter.run();

        // Now create two Runnable and run it in different Threads
        // Split array
        int middle = files.length / 2;
        File[] firstHalf = new File[middle];
        File[] secondHalf = new File[files.length - middle];
        System.arraycopy(files, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(files, middle, secondHalf, 0, secondHalf.length);
        // Create Runnable
        NameCounter firstHalfNameCounter = new NameCounter(firstHalf, "Alexander", System.currentTimeMillis());
        NameCounter secondHalfNameCounter = new NameCounter(secondHalf, "Alexander", System.currentTimeMillis());
        // Start in new Thread
        new Thread(firstHalfNameCounter, "firstHalf").start();
        new Thread(secondHalfNameCounter, "secondHalf").start();
    }
}