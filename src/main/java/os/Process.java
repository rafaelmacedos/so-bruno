package os;

import lombok.Getter;
import lombok.Setter;
import os.memory.MemoryAddress;

import java.util.List;
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

    public Process(Integer sizeInMemory) {
        this.id = UUID.randomUUID().toString();
        this.sizeInMemory = sizeInMemory;
    }
}
