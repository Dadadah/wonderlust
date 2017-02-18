package space.jacobschlecht.www.server;

public class Dungeon extends Room {
	
	private Dungeon left;
	private Dungeon top;
	private Dungeon right;
	private Dungeon bottom;
	

	public Dungeon(String name) {
		this(name, null, null, null, null);
	}
	
	public Dungeon(String name, Dungeon left, Dungeon top, Dungeon right, Dungeon bottom) {
		super(name);
		this.left = left;
		if (this.left != null) this.left.setRight(this);
		this.top = top;
		if (this.top != null) this.top.setBottom(this);
		this.right = right;
		if (this.right != null) this.right.setLeft(this);
		this.bottom = bottom;
		if (this.bottom != null) this.bottom.setTop(this);
	}
	
	public boolean isDungeon() {
		return true;
	}
	
	// GETTERS
	
	public Dungeon getLeft() {
		return this.left;
	}
	
	public Dungeon getTop() {
		return this.top;
	}
	
	public Dungeon getRight() {
		return this.right;
	}
	
	public Dungeon getBottom() {
		return this.bottom;
	}
	
	// SETTERS
	
	public void setLeft(Dungeon left) {
		this.left = left;
	}
	
	public void setTop(Dungeon top) {
		this.top = top;
	}
	
	public void setRight(Dungeon right) {
		this.right = right;
	}
	
	public void setBottom(Dungeon bottom) {
		this.bottom = bottom;
	}

}
