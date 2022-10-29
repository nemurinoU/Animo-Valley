import java.util.ArrayList;

public class Item {
	private int itemType; //tool or seed
	private ArrayList<Seed> seeds;
	private ArrayList<Tool> tools;

	public Item(int itemType, ArrayList<Seed> seeds, ArrayList<Tool> tools) {
		this.itemType = itemType;
		this.seeds = seeds;
		this.tools = tools;
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
