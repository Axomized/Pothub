package movebox;

public class BoxUpdate {
	private int boxTop;
	private int boxLeft;
	
	public BoxUpdate() {
	}

	public BoxUpdate(int boxTop, int boxLeft) {
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
