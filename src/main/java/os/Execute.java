package os;


public class Execute {
    public static void main(String[] args) {
        Process p1 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        Process p2 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        Process p3 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        Process p4 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);
        Process p5 = (Process) OperationalSystem.systemCall(SystemCallType.CREATE_PROCESS, null);

        p5.setSizeInMemory(200);

        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p1);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p2);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p3);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p4);
        OperationalSystem.systemCall(SystemCallType.WRITE_PROCESS, p5);
    }
}
