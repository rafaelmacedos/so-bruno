package os.memory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FrameMemory {

    private int pageNumber;
    private int displacement;

    public FrameMemory(int pageNumber, int displacement) {
        super();
        this.pageNumber = pageNumber;
        this.displacement = displacement;
    }

}
