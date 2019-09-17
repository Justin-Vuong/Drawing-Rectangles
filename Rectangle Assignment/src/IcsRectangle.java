//If you want to know what I changed, I deleted line 88 for testing but forgot to readd it

class IcsRectangle {
	private double left;
	private double width;
	private double height;
	private double bottom;
	private double right;
	private double top;

	public IcsRectangle() {
		left = 0;
		width = 0;
		height = 0;
		bottom = 0;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getBottom() {
		return bottom;
	}

	public IcsRectangle(int left, int bottom, int width, int height) {
		set(left, bottom, width, height);
		this.top = bottom + height;
		this.right = left + width;
	}

	public void set(double left, double bottom, double width, double height) {
		this.left = left;
		this.bottom = bottom;
		this.top = bottom + height;
		this.right = left + width;
		if (width < 0) {
			width = 0;
		} else {
			this.width = width;
		}
		if (height < 0) {
			height = 0;
		} else {
			this.height = height;
		}

	}

	public String toString() {
		return "base: (" + (int) this.left + "," + (int) this.bottom + ") w:" + (int) this.width + " h:"
				+ (int) this.height;
	}

	public double getLeft() {
		return this.left;
	}

	public double getRight() {
		return this.right;
	}

	public double getTop() {
		return this.top;
	}

	public int area() {
		return (int) (this.width * this.height);
	}

	public int perimeter() {
		if (this.height == 0) {
			return (int) width;
		} else if (this.width == 0) {
			return (int) height;
		} else {
			return (int) ((2 * this.width) + (2 * this.height));
		}
	}

	public static IcsRectangle partialOverlap(IcsRectangle r1, IcsRectangle r2) { // the leftmost one goes first
		IcsRectangle overlap = new IcsRectangle();
		overlap.left = r2.left;

		if (r1.bottom == r2.bottom) { //The 2 rectangles have the same bottom coordinate
			overlap.bottom = r1.bottom; //I WAS MISSING THIS LINE
			if (r1.top == r2.top) {
				overlap.height = r2.height;
			} else if (r1.top > r2.top) {
				overlap.height = r2.height;
			} else {
				overlap.height = r1.height;
			}
		} else if (r1.bottom < r2.bottom) {
			overlap.bottom = r2.bottom;
			if (r1.top == r2.bottom) {// if the top of r1 touches the bottom of r2
				overlap.height = 0.1;
				overlap.bottom -= 0.05;
			} else if (r1.top > r2.bottom && r1.top < r2.top) {// r2 not inside r1
				overlap.height = r1.top - r2.bottom;
			} else if (r1.top > r2.bottom) { // One inside other
				overlap.height = r2.height;
			} else { // they do not overlap
				overlap.height = 0;
			}
		} else { // R2 on bottom
			overlap.bottom = r1.bottom;
			if (r2.top > r1.bottom && r2.top < r1.top) {// r2 not inside r1
				overlap.height = r2.top - r1.bottom;
			} else if (r2.top > r1.bottom) { // One inside other
				overlap.height = r1.height;
			} else if (r2.top == r1.bottom) {// if the top of r1 touches the bottom of r2
				overlap.height = 0.1;
				overlap.bottom -= 0.05;
			} else { // they do not overlap
				overlap.height = 0;
			}
		}

		if (r1.right == r2.left) { // They touch
			overlap.width = 0.1;
			overlap.left -= 0.05;
		} else if (r1.right > r2.left && r1.right >= r2.right) { // r2 is in r1
			overlap.width = r2.width;
		} else if (r1.right > r2.left) {// r2 not inside r1
			overlap.width = r1.right - r2.left;
		} else { // they do not overlap
			overlap.width = 0;
		}

		return overlap;
	}

	public static int totalPerimeter(IcsRectangle r1, IcsRectangle r2) { //Returns the total perimeter
		double perimeterLength, perimeterWidth;
		if (r1.left + r1.width >= r2.left + r2.width) {
			perimeterLength = r1.width;
		} else {
			perimeterLength = (r2.left + r2.width) - r1.left;
		}
		if (r1.bottom <= r2.bottom && r1.bottom + r1.height <= r2.bottom + r2.height) {
			perimeterWidth = r2.bottom + r2.height - r1.bottom;
		} else if (r1.bottom <= r2.bottom) {
			perimeterWidth = r1.height;
		} else if (r1.bottom + r1.height <= r2.bottom + r2.height) {
			perimeterWidth = r2.height;
		} else {
			perimeterWidth = r1.bottom + r1.height - r2.bottom;
		}
		return (int) ((2 * perimeterWidth) + (2 * perimeterLength));
	}

	public boolean contains(IcsRectangle r1) { //If one is fully contained by another return true
		if (this.left <= r1.left && this.bottom <= r1.bottom && this.bottom + this.height >= r1.bottom + r1.height
				&& this.left + this.width >= r1.left + r1.width) {
			return true;
		} else {
			return false;
		}
	}

	public static int scaleDegree(int min1, int max1, int min2, int max2) {

		boolean blnCheck = false;
		int checker = 1;
		int degree = 0;
		do {
			checker *= 10;
			degree++;

			if ((double) Math.abs(min1) <= checker) {
				blnCheck = true; // Passable value for min1
			}
			if ((double) Math.abs(max1) > checker && blnCheck == true) {
				blnCheck = false; //not possible value for max1
			}

		} while (blnCheck == false); // blnCheck will leave this loop as true

		do {
			if ((double) Math.abs(min2) <= checker) {
				blnCheck = false; // no need to change checker
			}
			if ((double) Math.abs(max2) > checker && blnCheck == false) {
				blnCheck = true; // would need to increase checker
			}

			if (blnCheck == true) {
				checker *= 10;
				degree++;
			}

		} while (blnCheck == true);

		return degree;

	}

}
