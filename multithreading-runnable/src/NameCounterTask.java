import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NameCounterTask implements Runnable {

    private final File[] files;
    private final String name;
    private final long startTime;

    public NameCounterTask(File[] files, String name, long startTime) {
        this.files = files;
        this.name = name;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        int counter = 0;
        for (File file : files) {
            if (file == null) continue;
            String fileName = file.getName();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    if (line.contains(name)) {
                        counter++;
                    }
                }
            } catch (IOException e) {
                System.err.println(fileName + " " + e.getMessage());
            }
        }
        System.out.println("The number of mentions of the name \"" + name +"\": " + counter + "."
                + " Done in " + (System.currentTimeMillis() - startTime) + " ms."
                + " Thread: " + Thread.currentThread().getName());
    }

}
