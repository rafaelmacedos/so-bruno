package os;

import os.cpu.CpuManager;
import os.memory.MemoryManager;
import os.memory.Strategy;

import java.util.Objects;

public class OperationalSystem {
    public static MemoryManager memoryManager;
    public static CpuManager cpuManager;
    public static Schedule schedule;

    public static Object systemCall(SystemCallType type, Process process, Integer sizeInMemory) {
        if(Objects.isNull(memoryManager)) { memoryManager = new MemoryManager(Strategy.BEST_FIT);}
        if(Objects.isNull(cpuManager)) { cpuManager = new CpuManager();}

        if(type.equals(SystemCallType.CREATE_PROCESS)) {
            return new Process(sizeInMemory);
        }
        else if(type.equals(SystemCallType.WRITE_PROCESS)) {
            // Escrever o processo
            memoryManager.write(process);
        }
        if(type.equals(SystemCallType.READ_PROCESS)) {
            // Ler processo
        }
        if(type.equals(SystemCallType.DELETE_PROCESS)) {
            memoryManager.delete(process);
        }
        return null;
    }

    public static void showMemory() {
        memoryManager.printMemoryStatus();
    }
}
