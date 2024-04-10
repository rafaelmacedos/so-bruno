package os.scheduler;

import lombok.Getter;
import os.OsProcess;
import os.cpu.CpuManager;

@Getter
public abstract class Scheduler {
    private CpuManager cm;

    public Scheduler() {
        this.cm = new CpuManager();
    }

    public abstract void execute(OsProcess p);

    public abstract void finish(OsProcess p);

    public abstract boolean isEmpty();
}
