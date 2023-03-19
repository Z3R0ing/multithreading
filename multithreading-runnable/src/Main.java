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
        NameCounterTask allFilesNameCounterTask = new NameCounterTask(files, "Alexander");
        allFilesNameCounterTask.run();

        // Now create two Runnable and run it in different Threads
        // Split array
        int middle = files.length / 2;
        File[] firstHalf = new File[middle];
        File[] secondHalf = new File[files.length - middle];
        System.arraycopy(files, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(files, middle, secondHalf, 0, secondHalf.length);
        // Create Runnable
        NameCounterTask firstHalfNameCounterTask = new NameCounterTask(firstHalf, "Alexander");
        NameCounterTask secondHalfNameCounterTask = new NameCounterTask(secondHalf, "Alexander");
        // Start in new Thread
        new Thread(firstHalfNameCounterTask, "firstHalf").start();
        new Thread(secondHalfNameCounterTask, "secondHalf").start();
    }
}