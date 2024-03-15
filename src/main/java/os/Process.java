package os;

import lombok.Getter;
import lombok.Setter;
import os.memory.MemoryAddress;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Process {
    private String id;
    private int timeToExecute;
    private int sizeInMemory;
    private int priority;
    private List<Process> processes;
    private int instructions;
    private MemoryAddress memoryAddress;

    public Process() {
        this.id = UUID.randomUUID().toString();
        Random r = new Random();
        List<Integer> numbers = Arrays.asList(2, 4, 6, 10, 20, 50, 100);
        this.sizeInMemory = numbers.get(r.nextInt(numbers.size()));
    }
}
