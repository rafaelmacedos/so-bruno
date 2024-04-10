package os.scheduler;

import os.OsProcess;
import os.SubProcess;
import os.SystemCallType;
import os.OperationalSystem;

import java.util.List;

public class Lotery extends Scheduler {

    @Override
    public void execute(OsProcess p) {
        List<SubProcess> sps = OperationalSystem.systemCall(SystemCallType.READ_PROCESS, p);
        // Implementação específica para Loteria
    }

    @Override
    public void finish(OsProcess p) {
        // Implementação específica para Loteria
    }

    @Override
    public boolean isEmpty() {
        return false;
        // Implementação específica para Loteria
    }
}
