package os;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Execute {
    public static void main(String[] args) {
        // Criar um novo processo com sub-processos
        OsProcess p1 = OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, 100);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);
        OsProcess p2 = OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, 150);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
        OsProcess p3 = OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, 6);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);

        OsProcess p4 = OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, 6);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4);
        // Escrever o processo na memória
        // Exibir o estado da memória após escrever o processo
        System.out.println("Estado da memória após escrever o processo:");
        OperationalSystem.showMemory();
    }
}
