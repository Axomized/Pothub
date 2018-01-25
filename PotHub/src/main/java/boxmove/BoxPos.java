package boxmove;

public class BoxPos {
	private int boxTop;
	private int boxLeft;
	
	public BoxPos() {
	}

	public BoxPos(int boxTop, int boxLeft) {
		this.boxTop = boxTop;
		this.boxLeft = boxLeft;
	}

	public int getBoxTop() {
		return boxTop;
	}

	public int getBoxLeft() {
		return boxLeft;
	}
}
