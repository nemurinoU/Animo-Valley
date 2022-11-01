import java.util.ArrayList;

public class Item {
	private int itemType; //0 for Seed, 1 for Tool
	private ArrayList<Seed> seeds;
	private ArrayList<Tool> tools;

	public Item(int itemType, ArrayList<Seed> seeds, ArrayList<Tool> tools) {
		this.itemType = itemType;
		this.seeds = new ArrayList<Seed>();
		this.tools = new ArrayList<Tool>();
	}

	public int getItemType() {
		return this.itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public ArrayList<Seed> getSeeds() {
		return this.seeds;
	}

	public void setSeeds(ArrayList<Seed> seeds) {
		this.seeds = seeds;
	}

	public ArrayList<Tool> getTools() {
		return this.tools;
	}

	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}

	

}
