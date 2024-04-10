package os.memory;

import java.util.ArrayList;
import java.util.List;

import os.OsProcess;
import os.SubProcess;

public class MemoryManager {
    private static final int INSTRUCTIONS_PER_PROCESS = 7;
    private List<List<SubProcess>> physicalMemory;
    private int pageSize;
    private int totalPages;

    public MemoryManager(int pageSize, int physicalMemorySize) {
        this.pageSize = pageSize;
        this.totalPages = physicalMemorySize / pageSize;
        this.physicalMemory = new ArrayList<>(totalPages);
        for (int i = 0; i < totalPages; i++) {
            this.physicalMemory.add(new ArrayList<>(pageSize));
        }
    }

    public MemoryManager() {
        this(4, 256);
    }

    public void write(OsProcess p) {
        List<SubProcess> subProcesses = createSubProcesses(p);
        allocateSubProcesses(subProcesses);
        printMemoryStatus();
    }

    private List<SubProcess> createSubProcesses(OsProcess p) {
        List<SubProcess> subProcesses = new ArrayList<>();
        List<String> subProcessIds = p.getSubProcesses();
        for (String subProcessId : subProcessIds) {
            subProcesses.add(new SubProcess(subProcessId, INSTRUCTIONS_PER_PROCESS));
        }
        return subProcesses;
    }

    private void allocateSubProcesses(List<SubProcess> subProcesses) {
        int currentFrame = 0;

        for (int currentPage = 0; currentPage < totalPages; currentPage++) {
            List<SubProcess> page = physicalMemory.get(currentPage);
            for (int j = 0; j < pageSize; j++) {
                if (currentFrame >= subProcesses.size()) {
                    return; // Todos os sub-processos foram alocados
                }
                if (j < page.size()) {
                    continue; // A página já está ocupada, passa para a próxima posição
                }
                page.add(subProcesses.get(currentFrame++));
            }
        }
    }


    public List<SubProcess> read(OsProcess p) {
        List<SubProcess> subProcesses = new ArrayList<>();
        List<String> subProcessIds = p.getSubProcesses();
        for (String subProcessId : subProcessIds) {
            SubProcess subProcess = findSubProcess(subProcessId);
            if (subProcess != null) {
                subProcesses.add(subProcess);
            } else {
                System.out.println("SubProcesso " + subProcessId + " não encontrado na memória física.");
            }
        }
        return subProcesses;
    }

    private SubProcess findSubProcess(String subProcessId) {
        for (List<SubProcess> page : physicalMemory) {
            for (SubProcess subProcess : page) {
                if (subProcess != null && subProcess.getId().equals(subProcessId)) {
                    return subProcess;
                }
            }
        }
        return null;
    }

    public void delete(OsProcess process) {
        System.out.println("Removendo processo da memória: " + process.getId());

        for (List<SubProcess> page : physicalMemory) {
            for (int j = 0; j < page.size(); j++) {
                SubProcess sp = page.get(j);
                if (sp != null && sp.getId().equals(process.getId())) {
                    page.set(j, null);
                }
            }
        }

        System.out.println("Processo removido com sucesso");
        printMemoryStatus();
    }

    public void printMemoryStatus() {
        System.out.println("Estado da memória:");
        for (int i = 0; i < totalPages; i++) {
            for (int j = 0; j < pageSize; j++) {
                SubProcess sp = (j < physicalMemory.get(i).size()) ? physicalMemory.get(i).get(j) : null;
                String spId = (sp != null) ? sp.getId() : "null";
                System.out.printf("| %-5s ", spId);
            }
            System.out.println("|");
        }
    }
}
