package os;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Execute {
    public static void main(String[] args) {
        var p1 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 50);
        var p2 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 22);
        var p3 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 25);
        var p4 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 28);
        var p5 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 2);
        // var p6 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 2);

        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p5, null);
        // OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p6, null);

        OperationalSystem.systemCall(SystemCallType.DELETE_PROCESS, p2, null);
        OperationalSystem.systemCall(SystemCallType.DELETE_PROCESS, p4, null);

//        log.info("CASOS DE TESTE FIRST FIT");
//        var p7 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 1);
//        var p8 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 19);
//        var p9 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 29);
//        var p10 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 25);
//        var p11 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 3);
//        var p12 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 2);
//
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p7, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p8, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p9, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p10, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p11,  null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p12,  null);

//        log.info("CASOS DE TESTE BEST FIT");
//        var p7 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 1);
//        var p8 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 18);
//        var p9 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 22);
//        var p10 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 6);
//        var p11 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 4);
//
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p7, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p8, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p9, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p10, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p11,  null);

//        log.info("CASOS DE TESTE WORST FIT");
//        var p7 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 1);
//        var p8 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 1);
//        var p9 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 18);
//        var p10 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 17);
//        var p11 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 5);
//        var p12 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 3);
//        var p13 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 2);
//        var p14 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 1);
//
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p7, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p8, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p9, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p10, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p11, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p12, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p13, null);
//        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p14, null);

        OperationalSystem.showMemory();
    }
}
