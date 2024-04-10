package os.cpu;

import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;
import os.SubProcess;

@Getter
public class CpuManager {

    private Core[] cores;
    public static int CLOCK = 5000;

    public static int
            NUMBER_OF_CORES = 4,
            NUMER_OF_INSTRUCTIONS_PER_PROCESS = 7;


    public CpuManager () {
        this.cores = new Core[NUMBER_OF_CORES];
        for (int i = 0; i < this.cores.length; i++) {
            this.cores[i] = new Core(NUMER_OF_INSTRUCTIONS_PER_PROCESS);
        }
        this.clock();
    }

    public void registerProcess (int coreIndex, SubProcess sp) {
        this.cores[coreIndex].setActuallySubProcess(sp);
    }

    public void clock( ) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                executeProcesses();
            }
        }, 0, CLOCK);
    }

    private void executeProcesses() {
        for(Core core: this.cores) {
            if(core.getActuallySubProcess() != null ) {
                core.run();

            }
        }
    }

}