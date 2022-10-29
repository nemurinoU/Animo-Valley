
public class Item {
	private int itemType; //tool or seed
	private Seed seed;
	private Tool tools;

	public Item(int itemType, Seed seed, Tool tools) {
		this.itemType = itemType;
		this.seed = new Seed();
		this.tools = new Tool();
	}

	public int getItemType() {
		return this.itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public Seed getSeed() {
		return this.seed;
	}

	public void setSeed(Seed seed) {
		this.seed = seed;
	}

	public Tool getTools() {
		return this.tools;
	}

	public void setTools(Tool tools) {
		this.tools = tools;
	}

}
