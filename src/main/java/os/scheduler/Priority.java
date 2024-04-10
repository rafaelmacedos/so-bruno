package os.scheduler;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import os.OsProcess;
import os.SubProcess;
import os.SystemCallType;
import os.OperationalSystem;
import os.cpu.Core;

public class Priority extends Scheduler {

    private PriorityQueue<SubProcess> queue;
    private int order;

    public Priority(int order) {
        super();
        this.order = order;

        Comparator<SubProcess> c = new Comparator<SubProcess>() {

            @Override
            public int compare(SubProcess sp1, SubProcess sp2) {
                return 0;
            }
        };
        this.queue = new PriorityQueue<>(c);
    }

    @Override
    public void execute(OsProcess p) {
        List<SubProcess> sps = OperationalSystem.systemCall(SystemCallType.READ_PROCESS, p);
        for (SubProcess sp : sps) {
            this.queue.add(sp);
        }
        while (!this.queue.isEmpty()) {
            Core[] cores = this.getCm().getCores();
            for (int i = 0; i < cores.length; i++) {
                if (cores[i].getActuallySubProcess() == null) {
                    this.getCm().registerProcess(i, this.queue.poll());
                }
            }
        }

    }

    @Override
    public void finish(OsProcess p) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

}
