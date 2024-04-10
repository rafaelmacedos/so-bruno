package os;


import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class SubProcess implements Comparable<SubProcess> {
    private int instructions;
    private String id;
    private static int count;
    private String processId;
    private int timeToExecute;

    public SubProcess(String processId, int instructions) {
        count++;
        this.id = processId + " - " + count;
        this.processId = processId;
        this.instructions = instructions;
        Random rand = new Random();
        List<Integer> givenList = Arrays.asList(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000);
        this.timeToExecute = givenList.get(rand.nextInt(givenList.size()));
    }

    @Override
    public int compareTo(SubProcess other) {
        return Integer.compare(this.timeToExecute, other.timeToExecute);
    }
}
