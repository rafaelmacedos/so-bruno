package so;

import so.process.PriorityEnum;
import so.process.SoProcess;

public class Execute {
    public static void main(String[] args) throws InterruptedException {
        String greenText = "\033[32m";
        String resetColor = "\033[0m";
        String[] asciiArt = new String[]{
                greenText,
                "\n" +
                        " ██████╗    ███████╗    ███████╗██╗███╗   ███╗██╗   ██╗██╗      █████╗ ████████╗ ██████╗ ██████╗ \n" +
                        "██╔═══██╗   ██╔════╝    ██╔════╝██║████╗ ████║██║   ██║██║     ██╔══██╗╚══██╔══╝██╔═══██╗██╔══██╗\n" +
                        "██║   ██║   ███████╗    ███████╗██║██╔████╔██║██║   ██║██║     ███████║   ██║   ██║   ██║██████╔╝\n" +
                        "██║   ██║   ╚════██║    ╚════██║██║██║╚██╔╝██║██║   ██║██║     ██╔══██║   ██║   ██║   ██║██╔══██╗\n" +
                        "╚██████╔╝██╗███████║    ███████║██║██║ ╚═╝ ██║╚██████╔╝███████╗██║  ██║   ██║   ╚██████╔╝██║  ██║\n" +
                        " ╚═════╝ ╚═╝╚══════╝    ╚══════╝╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝\n" +
                        "                                                                                                 \n"
                , resetColor
        };

        Thread.sleep(1000);
        for (String line : asciiArt) {
            System.out.println(line);
        }
        Thread.sleep(1000);

        //Teste pagina faltando para alocação do frame memory
        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, null);
        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, null);
        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 34, null);

        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
//
        //A memoria fica cheia mas não estoura
//        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, null);
//        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, null);
//        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 30, null);
//
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
//
        //FCFS
//        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, null);
//        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, null);
//        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 30, null);
//
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
//
        //Priority
//        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, null, PriorityEnum.P1); //P1 ==baixo
//        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, null, PriorityEnum.P2); //P2== medio
//        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 30, null, PriorityEnum.P3); // P3==critico
//
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
//
        //SJF
//        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, 10);
//        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, 5);
//        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 30, 15);
//
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
        
    	//Lottery
//        SoProcess process1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 130, null);
//        SoProcess process2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 90, null);
//        SoProcess process3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, 30, null);
//
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process1);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process2);
//        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, process3);
    	
        
    }
}

