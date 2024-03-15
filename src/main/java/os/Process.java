package os;

import os.memory.MemoryAddress;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimeToExecute() {
        return timeToExecute;
    }

    public void setTimeToExecute(int timeToExecute) {
        this.timeToExecute = timeToExecute;
    }

    public int getSizeInMemory() {
        return sizeInMemory;
    }

    public void setSizeInMemory(int sizeInMemory) {
        this.sizeInMemory = sizeInMemory;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public int getInstructions() {
        return instructions;
    }

    public void setInstructions(int instructions) {
        this.instructions = instructions;
    }

    public MemoryAddress getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(MemoryAddress memoryAddress) {
        this.memoryAddress = memoryAddress;
    }
}
