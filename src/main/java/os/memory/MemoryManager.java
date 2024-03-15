package os.memory;

import os.Process;

import java.util.*;

public class MemoryManager {
    private String[] memory;
    private Strategy strategy;

    public MemoryManager(Strategy strategy) {
        this.memory = new String[128];
        this.strategy = strategy;
    }

    public void write(Process process) {
        if (strategy.equals(Strategy.FIRST_FIT)) {
            this.writeUsingFirstFit(process);
        } else if (strategy.equals(Strategy.BEST_FIT)) {
            this.writeUsingBestFit(process);
        } else if (strategy.equals(Strategy.WORST_FIT)) {
            this.writeUsingWorstFit(process);
        } else if (strategy.equals(Strategy.PAGING)) {
            this.writeUsingPaging(process);
        }

    }

    private void writeUsingPaging(Process process) {
    }

    private void writeUsingWorstFit(Process process) {
    }

    private void writeUsingBestFit(Process process) {
    }

    private void writeUsingFirstFit(Process process) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();
        System.out.println("\n" + availableSizes.size());

        if (availableSizes.size() == 1 && memory.length >= process.getSizeInMemory()) {
            for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
                MemoryAddress address = entry.getValue();
                int availableBlockSize = address.getEnd() - address.getStart() + 1;
                if (availableBlockSize >= process.getSizeInMemory()) {
                    for (int i = address.getStart(); i < address.getStart() + process.getSizeInMemory(); i++) {
                        memory[i] = process.getId();
                    }
                }
            }
        }
        else {
            System.out.println("\nNÃO FOI POSSÍVEL ALOCAR O PROCESSO");
        }

        for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getStart() + "," + entry.getValue().getEnd());
        }
        printMemoryStatus();
    }

    public Map<String, MemoryAddress> returnAvailableMemorySizes() {
        Map<String, MemoryAddress> availableSizes = new HashMap<>();
        int availableSizeCount = 1;


        // Verificando se a memória está vazia
        if (Arrays.stream(memory).allMatch(Objects::isNull)) {
            MemoryAddress currentAddress = new MemoryAddress(0, memory.length - 1);
            availableSizes.put("MemorySize" + availableSizeCount, currentAddress);
            availableSizeCount++;
            return availableSizes;
        } else {
            Integer start = null;
            Integer end = null;
            for (int i = 0; i < memory.length; i++) {
                // Verificando se o espaço está vazio, se estiver um novo intervalo se inicia.
                if (memory[i] == null) {
                    if (start == null) {
                        start = i;
                    }
                    // Verificando se o é o último intervalo da memória, assim criando um novo endereço dispnível.
                    if (i == memory.length - 1) {
                        end = i;
                        MemoryAddress currentAddress = new MemoryAddress(start, end);
                        availableSizes.put("MemorySize" + availableSizeCount, currentAddress);
                        availableSizeCount++;
                        start = null;
                        end = null;
                    }
                }
                // Se não estiver vazio, o intervalo se acaba e um novo endereço disponível é salvo.
                else {
                    if (start != null) {
                        end = i - 1;
                        MemoryAddress currentAddress = new MemoryAddress(start, end);
                        availableSizes.put("MemorySize" + availableSizeCount, currentAddress);
                        availableSizeCount++;
                        start = null;
                        end = null;
                    }

                }
            }
        }

        return availableSizes;
    }


    private void printMemoryStatus() {
        for (int i = 0; i < memory.length; i++) {
            System.out.print(memory[i] + " | ");
        }

    }

}
