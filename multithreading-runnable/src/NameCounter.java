import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class NameCounter {

    private final String name;
    private int badCounter;
    private AtomicInteger goodCounter;

    public NameCounter(String name) {
        this.name = name;
        badCounter = 0;
        goodCounter = new AtomicInteger(0);
    }

    public void countInFile(File file) {
        if (file == null) return;
        String fileName = file.getName();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.contains(name)) {
                    badCounter++;
                    goodCounter.incrementAndGet();
                }
            }
        } catch (IOException e) {
            System.err.println(fileName + " " + e.getMessage());
        }
        System.out.println("Plain integer counter: " + badCounter + "; Atomic integer counter: " + goodCounter.get());
    }
}
