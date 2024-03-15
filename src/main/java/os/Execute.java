package os;


public class Execute {
    public static void main(String[] args) {
        var p1 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        var p2 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        var p3 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        var p4 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        var p5 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);

        p5.setSizeInMemory(200);

        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p5);
    }
}
