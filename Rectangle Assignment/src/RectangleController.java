import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RectangleController {
	@FXML
	private javafx.scene.shape.Rectangle rectangle1;

	@FXML
	private javafx.scene.shape.Rectangle rectangle2;

	@FXML
	private Button moveButton;

	@FXML
	private Button overlapButton;

	@FXML
	private VBox vbox;

	// Add a public no-args constructor
	public RectangleController() {
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void calculateOverlap() {

		IcsRectangle convertedRectangle1 = convertJavaFxRectangle(rectangle1);
		IcsRectangle convertedRectangle2 = convertJavaFxRectangle(rectangle2);
		IcsRectangle overlapRectangle = IcsRectangle.partialOverlap(convertedRectangle1, convertedRectangle2);

		displayResults(convertedRectangle1, convertedRectangle2, overlapRectangle);

	}

	private void displayResults(IcsRectangle rectangle1, IcsRectangle rectangle2, IcsRectangle convertedRectangle) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Overlap Rectangle Information");

		StringBuffer sb = new StringBuffer();
		sb.append("Rectangle 1 (green):\n");
		sb.append(formatRectangleInfo(rectangle1));
		sb.append("\n\nRectangle 2 (blue):\n");
		sb.append(formatRectangleInfo(rectangle2));
		sb.append("\n\nOverlap Rectangle (red):\n");
		if (convertedRectangle.getWidth() > -1) {
			sb.append(formatRectangleInfo(convertedRectangle));
			javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(convertedRectangle.getLeft(),
					convertedRectangle.getBottom(), convertedRectangle.getWidth(), convertedRectangle.getHeight());
			r.setStroke(Color.RED);
			r.setFill(Color.RED);
			vbox.getChildren().add(r);
		} else {
			sb.append("Rectangles do not overlap!!! Too bad so sad!!!");
		}
		alert.setContentText(sb.toString());
		alert.showAndWait();
	}

	private String formatRectangleInfo(IcsRectangle rectangle) {
		StringBuffer sb = new StringBuffer();
		sb.append("bottom left X=" + rectangle.getLeft());
		sb.append("\nbottom left Y=" + rectangle.getBottom());
		sb.append("\nwidth=" + rectangle.getWidth());
		sb.append("\nheight=" + rectangle.getHeight());
		return sb.toString();
	}

	private IcsRectangle convertJavaFxRectangle(javafx.scene.shape.Rectangle rectangle) {
		Bounds bound = rectangle.localToScene(rectangle.getBoundsInLocal());
		IcsRectangle r = new IcsRectangle();
		r.set((int) bound.getMinX(), (int) bound.getMaxY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
		return r;
	}

	@FXML
	private void move() {

		sleep(1000L);
		rectangle1.setTranslateX(100L);
		rectangle1.setTranslateY(500L);

		sleep(1000L);

		rectangle2.setTranslateX(500L);
		rectangle2.setTranslateY(600L);

	}

	@FXML
	private void close() {
		System.exit(0);
	}

	private void sleep(long value) {

		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
