package os;

import os.cpu.CpuManager;
import os.memory.MemoryManager;
import os.memory.Strategy;

import java.util.Objects;

public class OperationalSystem {

    public static MemoryManager memoryManager;
    public static CpuManager cpuManager;
    public static Schedule schedule;

    public static Object systemCall(SystemCallType type, Process process) {
        if(Objects.isNull(memoryManager)) { memoryManager = new MemoryManager(Strategy.FIRST_FIT);}
        if(Objects.isNull(cpuManager)) { cpuManager = new CpuManager();}

        if(type.equals(SystemCallType.CREATE_PROCESS)) {
            return new Process();
        }
        else if(type.equals(SystemCallType.WRITE_PROCESS)) {
            // Escrever o processo
            memoryManager.write(process);
        }
        if(type.equals(SystemCallType.READ_PROCESS)) {
            // Ler processo
        }
        if(type.equals(SystemCallType.DELETE_PROCESS)) {
            // Deletar processo
        }
        return null;
    }

}
