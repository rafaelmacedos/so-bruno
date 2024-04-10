package os.cpu;


import lombok.Getter;
import lombok.Setter;
import os.SubProcess;

@Getter
@Setter
public class Core implements Runnable {
    private int id;
    private int numberOfInstructionsPerClock;
    private SubProcess actuallySubProcess;
    private int numberOfInstructionsExecuted;

    public Core(int numberOfInstructionsPerClock, int id) {
        this.numberOfInstructionsPerClock = numberOfInstructionsPerClock;
        this.id = id;
    }

    public Core(int id) {
        this(id, 7);
    }

    @Override
    public void run() {
        this.numberOfInstructionsExecuted += numberOfInstructionsPerClock;
        if (this.numberOfInstructionsExecuted >= actuallySubProcess.getInstructions()) {
            this.finishSubProcess();
        }
    }

    public void finishSubProcess() {
        this.actuallySubProcess = null;
        this.numberOfInstructionsExecuted = 0;
    }

}
