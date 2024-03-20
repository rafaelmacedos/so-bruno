package os.memory;

import lombok.extern.slf4j.Slf4j;
import os.Process;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
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

    public void delete(Process process) {
        for (int i = 0; i < memory.length; i++) {
            if (Objects.equals(memory[i], process.getId())){
                memory[i] = null;
                log.info("DELETANDO DA MEMÓRIA PROCESSO. ID: " + process.getId());
            }
        }
    }

    private void writeUsingPaging(Process process) {
    }

    private void writeUsingWorstFit(Process process) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();

        for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
            log.info("Tamanho restante da memória -> {}", (entry.getValue().getEnd() - entry.getValue().getStart()) + 1);
        }

        if (!availableSizes.isEmpty() && memory.length >= process.getSizeInMemory()) {
            Map.Entry<String, MemoryAddress> worstAddress = null;
            int blockSizeWorst = 0;

            // Obtendo um endereço de memória como parâmetro para validar o pior espaço
            for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
                MemoryAddress address = entry.getValue();
                int availableBlockSize = address.getEnd() - address.getStart() + 1;

                if (availableBlockSize > blockSizeWorst) {
                    worstAddress = entry;
                    blockSizeWorst = availableBlockSize;
                }

            }

            if (blockSizeWorst >= process.getSizeInMemory()) {
                for (int i = worstAddress.getValue().getStart(); i < worstAddress.getValue().getStart() + process.getSizeInMemory(); i++) {
                    log.info("Alocando o processo {} com tamanho {}", process.getId(), process.getSizeInMemory());
                    memory[i] = process.getId();
                }
            } else {
                log.error("Não foi possível alocar o processo {} com tamanho {} no espaço {}", process.getId(), process.getSizeInMemory(), blockSizeWorst);
            }

        } else {
            log.error("Não foi possível alocar o processo {} com tamanho {}, Não existem espaços disponíveis ou o processo tem um tamanho não compatível com a memória", process.getId(), process.getSizeInMemory());
        }

    }

    private void writeUsingBestFit(Process process) {

    }

    private void writeUsingFirstFit(Process process) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();

        for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
            log.info("Tamanho restante da memória -> {}", (entry.getValue().getEnd() - entry.getValue().getStart()) + 1);
        }

        if (!availableSizes.isEmpty() && memory.length >= process.getSizeInMemory()) {
            for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
                MemoryAddress address = entry.getValue();
                int availableBlockSize = address.getEnd() - address.getStart() + 1;

                if (availableBlockSize >= process.getSizeInMemory()) {
                    for (int i = address.getStart(); i < address.getStart() + process.getSizeInMemory(); i++) {
                        log.info("Alocando o processo {} com tamanho {}", process.getId(), process.getSizeInMemory());
                        memory[i] = process.getId();
                    }
                } else {
                    log.error("Não foi possível alocar o processo {} com tamanho {} no espaço {}", process.getId(), process.getSizeInMemory(), availableBlockSize);
                }
            }
        } else {
            log.error("Não foi possível alocar o processo {} com tamanho {}", process.getId(), process.getSizeInMemory());
        }
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


    public void printMemoryStatus() {
        for (int i = 0; i < memory.length; i++) {
            System.out.println((i + 1) + " | " + memory[i]);
        }
    }
}
