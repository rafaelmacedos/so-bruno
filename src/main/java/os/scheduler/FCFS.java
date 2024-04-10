package os.scheduler;

import os.OperationalSystem;
import os.OsProcess;
import os.SubProcess;
import os.SystemCallType;
import os.cpu.Core;

import java.util.List;
import java.util.PriorityQueue;

public class FCFS extends Scheduler {

    private PriorityQueue<SubProcess> queue;

    public FCFS() {
        super();
        this.queue = new PriorityQueue<>();
    }

    @Override
    public void execute(OsProcess p) {
        // Verificar se a lista de SubProcess está nula antes de continuar
        List<SubProcess> sps = OperationalSystem.systemCall(SystemCallType.READ_PROCESS, p);
        if (sps != null) {
            for (SubProcess sp : sps) {
                this.queue.add(sp);
            }
        } else {
            System.out.println("A lista de SubProcess está nula. Verifique o método systemCall.");
            return; // Encerrar a execução do método se a lista estiver nula
        }

        while (!this.queue.isEmpty()) {
            Core[] cores = this.getCm().getCores();
            // Verificar se há processos na fila antes de atribuir a um núcleo
            if (!this.queue.isEmpty()) {
                for (int i = 0; i < cores.length; i++) {
                    if (cores[i].getActuallySubProcess() == null) {
                        this.getCm().registerProcess(i, this.queue.poll());
                    }
                }
            }
        }
    }

    @Override
    public void finish(OsProcess p) {
        // Método ainda não implementado
        System.out.println("Método 'finish' ainda não implementado.");
    }

    @Override
    public boolean isEmpty() {
        // Verificar se a fila está vazia
        return this.queue.isEmpty();
    }
}
