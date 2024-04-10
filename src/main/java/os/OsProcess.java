package os;

import lombok.Getter;
import lombok.Setter;
import os.memory.MemoryAddress;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class OsProcess {

    private String id;
    private int sizeInMemory;
    private MemoryAddress ma;

    private int timeToExecute;
    private int numberOfInstructions;
    private List<String> subProcesses;
    private static int count;

    public OsProcess(int sizeInMemory) {
        count++;
        this.id = "P " + count;
        this.sizeInMemory = sizeInMemory;
        this.subProcesses = this.getSubProcesses();
    }

    // pegando processos e transformando em subrprocessos
    public List<String> getSubProcesses() {
        if (this.subProcesses == null) {
            this.subProcesses = new LinkedList<>();
            for (int i = 0; i < this.getSizeInMemory(); i++) {
                this.subProcesses.add(this.getId() + "-" + i);
            }
        }
        return this.subProcesses;
    }
}