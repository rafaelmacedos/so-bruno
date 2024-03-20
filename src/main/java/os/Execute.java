package os;


public class Execute {
    public static void main(String[] args) {
        var p1 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 10);
        var p2 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 10);
        var p3 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 10);
        var p4 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 10);
        var p5 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 10);
        var p6 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 5);
        var p7 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 60);
        var p8 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 4);
        var p9 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 5);
        var p10 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 20);
        var p11 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 40);
        var p12 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 7);
        var p13 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null, 2);

        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p5, null);

        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p6, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p7, null);
        OperationalSystem.systemCall(SystemCallType.DELETE_PROCESS, p1, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p8, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p9, null);
        OperationalSystem.systemCall(SystemCallType.DELETE_PROCESS, p7, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p10, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p11, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p12, null);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p13, null);

        OperationalSystem.showMemory();
    }
}
