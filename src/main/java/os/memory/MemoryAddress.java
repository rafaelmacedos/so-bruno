package os.memory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoryAddress {
    private int start;
    private int end;

    public MemoryAddress(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int size() {
        return (end - start) + 1;
    }
}
