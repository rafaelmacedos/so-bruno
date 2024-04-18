package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import so.memory.AddressMemory;

public class Process {
	
	private String id;
	private int sizeInMemory;
	private AddressMemory am;
	
	//Segunda etapa
	private int timeToExecute;
	private int numberOfInstructions;
	private List<Process> processes;
	private int priority;
	
	public Process() {
		
		this.id = UUID.randomUUID().toString();
		Random rand = new Random();
		List<Integer> numbers = Arrays.asList(2, 4, 0, 10, 20, 30, 50, 100);
		this.sizeInMemory = numbers.get(rand.nextInt(numbers.size()));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSizeInMemory() {
		return sizeInMemory;
	}

	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	public AddressMemory getAm() {
		return am;
	}

	public void setAm(AddressMemory am) {
		this.am = am;
	}
	
}
