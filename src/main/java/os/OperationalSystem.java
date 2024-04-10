package os;

import os.memory.MemoryManager;
import os.scheduler.FCFS;
import os.scheduler.Scheduler;

import java.util.List;
import java.util.Objects;


public class OperationalSystem {

    private static MemoryManager memoryManager;
    private static Scheduler scheduler;

    public static OsProcess systemCall(SystemCallType type, int processSize) {
        switch (type) {
            case CREATE_PROCESS:
                if (Objects.isNull(memoryManager)) {
                    memoryManager = new MemoryManager();
                }
                if (Objects.isNull(scheduler)) {
                    scheduler = new FCFS();
                }
                return new OsProcess(processSize);
            default:
                return null;
        }
    }

    public static List<SubProcess> systemCall(SystemCallType type, OsProcess p) {
        switch (type) {
            case WRITE_PROCESS:
                memoryManager.write(p);
                scheduler.execute(p);
                break;
            case DELETE_PROCESS:
                memoryManager.delete(p);
                scheduler.finish(p);
                break;
            case READ_PROCESS:
                return memoryManager.read(p);
            default:
                return null;
        }
        return null;
    }

    public static void showMemory() {
        memoryManager.printMemoryStatus();
    }
}

