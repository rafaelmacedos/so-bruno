package os.memory;

import lombok.extern.slf4j.Slf4j;
import os.OsProcess;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class MemoryManagerOriginal {
    private String[] memory;
    private Strategy strategy;

    public MemoryManagerOriginal(Strategy strategy) {
        this.memory = new String[128];
        this.strategy = strategy;
    }

    public void write(OsProcess osProcess) {
        if (strategy.equals(Strategy.FIRST_FIT)) {
            this.writeUsingFirstFit(osProcess);
        } else if (strategy.equals(Strategy.BEST_FIT)) {
            this.writeUsingBestFit(osProcess);
        } else if (strategy.equals(Strategy.WORST_FIT)) {
            this.writeUsingWorstFit(osProcess);
        } else if (strategy.equals(Strategy.PAGING)) {
            this.writeUsingPaging(osProcess);
        }
    }

    public void delete(OsProcess osProcess) {
        for (int i = 0; i < memory.length; i++) {
            if (Objects.equals(memory[i], osProcess.getId())){
                memory[i] = null;
                log.info("DELETANDO DA MEMÓRIA PROCESSO. ID: " + osProcess.getId());
            }
        }
    }

    private void writeUsingPaging(OsProcess osProcess) {
    }

    private void writeUsingWorstFit(OsProcess osProcess) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();

        for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
            log.info("Tamanho restante da memória -> {}", (entry.getValue().getEnd() - entry.getValue().getStart()) + 1);
        }

        if (!availableSizes.isEmpty() && memory.length >= osProcess.getSizeInMemory()) {
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

            if (blockSizeWorst >= osProcess.getSizeInMemory()) {
                for (int i = worstAddress.getValue().getStart(); i < worstAddress.getValue().getStart() + osProcess.getSizeInMemory(); i++) {
                    log.info("Alocando o processo {} com tamanho {}", osProcess.getId(), osProcess.getSizeInMemory());
                    memory[i] = osProcess.getId();
                }
            } else {
                log.error("Não foi possível alocar o processo {} com tamanho {} no espaço {}", osProcess.getId(), osProcess.getSizeInMemory(), blockSizeWorst);
            }

        } else {
            log.error("Não foi possível alocar o processo {} com tamanho {}, Não existem espaços disponíveis ou o processo tem um tamanho não compatível com a memória", osProcess.getId(), osProcess.getSizeInMemory());
        }

    }

    private void writeUsingBestFit(OsProcess osProcess) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();

        if (!availableSizes.isEmpty() && memory.length >= osProcess.getSizeInMemory()) {
            MemoryAddress bestAddress = null;

            for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
                MemoryAddress address = entry.getValue();
                int availableBlockSize = address.getEnd() - address.getStart() + 1;

                if (availableBlockSize >= osProcess.getSizeInMemory() && (bestAddress == null || availableBlockSize < (bestAddress.getEnd() - bestAddress.getStart() + 1))) {
                    bestAddress = address;
                }
            }

            if (bestAddress != null && bestAddress.getEnd() - bestAddress.getStart() + 1 >= osProcess.getSizeInMemory()) {
                for (int i = bestAddress.getStart(); i < bestAddress.getStart() + osProcess.getSizeInMemory(); i++) {
                    log.info("Alocando o processo {} com tamanho {}", osProcess.getId(), osProcess.getSizeInMemory());
                    memory[i] = osProcess.getId();
                }
            } else {
                log.error("Não foi possível alocar o processo {} com tamanho {}, Não existem espaços disponíveis ou o processo tem um tamanho não compatível com a memória", osProcess.getId(), osProcess.getSizeInMemory());
            }
        } else {
            log.error("Não foi possível alocar o processo {} com tamanho {}", osProcess.getId(), osProcess.getSizeInMemory());
        }
    }

    private void writeUsingFirstFit(OsProcess osProcess) {
        Map<String, MemoryAddress> availableSizes = returnAvailableMemorySizes();

        for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
            log.info("Tamanho restante da memória -> {}", (entry.getValue().getEnd() - entry.getValue().getStart()) + 1);
        }

        if (!availableSizes.isEmpty() && memory.length >= osProcess.getSizeInMemory()) {
            for (Map.Entry<String, MemoryAddress> entry : availableSizes.entrySet()) {
                MemoryAddress address = entry.getValue();
                int availableBlockSize = address.getEnd() - address.getStart() + 1;

                if (availableBlockSize >= osProcess.getSizeInMemory()) {
                    for (int i = address.getStart(); i < address.getStart() + osProcess.getSizeInMemory(); i++) {
                        log.info("Alocando o processo {} com tamanho {}", osProcess.getId(), osProcess.getSizeInMemory());
                        memory[i] = osProcess.getId();
                    }
                    break;
                } else {
                    log.error("Não foi possível alocar o processo {} com tamanho {} no espaço {}", osProcess.getId(), osProcess.getSizeInMemory(), availableBlockSize);
                }
            }
        } else {
            log.error("Não foi possível alocar o processo {} com tamanho {}", osProcess.getId(), osProcess.getSizeInMemory());
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
