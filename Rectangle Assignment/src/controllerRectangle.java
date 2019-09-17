import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class controllerRectangle {

	private ObservableList<String> settings = FXCollections.observableArrayList("Overlap", "Exercise 1");
	@FXML
	private Rectangle rec2;

	@FXML
	private Rectangle rec3;

	@FXML
	private Label recSummaries;
	
	@FXML
	private Label recArea;
	
	@FXML
	private Label recPerimeter;
	
	@FXML
	private Label recContains;
	
	@FXML
	private Label negXScale;
	
	@FXML
	private Label negYScale;
	
	@FXML
	private Label posXScale;
	
	@FXML
	private Label posYScale;
	
	@FXML
	private Rectangle rec1;

	@FXML
	private Spinner<Integer> rec1Height;

	@FXML
	private Spinner<Integer> rec1Width;

	@FXML
	private Spinner<Integer> rec1XValue;
	
	@FXML
	private Spinner<Integer> rec1YValue;
	
	@FXML
	private Spinner<Integer> rec2Height;
	
	@FXML
	private Spinner<Integer> rec2Width;
	
	@FXML
	private Spinner<Integer> rec2XValue;
	
	@FXML
	private Spinner<Integer> rec2YValue;
	
	@FXML
	private CheckBox cbHideRec1;
	
	@FXML
	private CheckBox cbHideRec2;
	
	@FXML
	private CheckBox cbHideRec3;
	
	@FXML
	private void initialize() {
		cbHideRec1.setAllowIndeterminate(false);
		cbHideRec2.setAllowIndeterminate(false);
		cbHideRec3.setAllowIndeterminate(false);
		cbHideRec1.setSelected(true);
		cbHideRec2.setSelected(true);
		cbHideRec3.setSelected(true);
		
		
		SpinnerValueFactory<Integer> rec1XValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		SpinnerValueFactory<Integer> rec1YValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		SpinnerValueFactory<Integer> rec2XValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, -5);
		SpinnerValueFactory<Integer> rec2YValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, -5);
		
		SpinnerValueFactory<Integer> rec1WidthValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 10);
		SpinnerValueFactory<Integer> rec1HeightValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 10);
		SpinnerValueFactory<Integer> rec2WidthValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 10);
		SpinnerValueFactory<Integer> rec2HeightValueVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 10);
		
		this.rec1XValue.setValueFactory(rec1XValueVF);
		this.rec1YValue.setValueFactory(rec1YValueVF);
		this.rec2XValue.setValueFactory(rec2XValueVF);
		this.rec2YValue.setValueFactory(rec2YValueVF);
		this.rec1Height.setValueFactory(rec1WidthValueVF);
		this.rec1Width.setValueFactory(rec1HeightValueVF);
		this.rec2Height.setValueFactory(rec2WidthValueVF);
		this.rec2Width.setValueFactory(rec2HeightValueVF);
		
		rec1XValueVF.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec1YValueVF.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec2XValueVF.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec2YValueVF.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec1Height.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec1Width.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec2Height.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		rec2Width.valueProperty().addListener((obs, oldValue, newValue) -> drawRec());
		
		rec1XValue.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
			if (!"".equals(newValue)) {
	           drawRec();
	        } 
	    });
		drawRec();
	}
	
	@FXML
	private void drawRec() {
		
		IcsRectangle convertRec1 = new IcsRectangle();
		IcsRectangle convertRec2 = new IcsRectangle();
		IcsRectangle overlapRec = new IcsRectangle();
		
		int xScaleDegree = IcsRectangle.scaleDegree(rec1XValue.getValue(), rec1XValue.getValue() + rec1Width.getValue(), rec2XValue.getValue(), rec2XValue.getValue() + rec2Width.getValue()) -1;
		
		int yScaleDegree = IcsRectangle.scaleDegree(rec1YValue.getValue(), rec1YValue.getValue() + rec1Height.getValue(), rec2YValue.getValue(), rec2YValue.getValue() + rec2Height.getValue()) -1;
		posXScale.setText(Integer.toString(xScaleDegree+1));
		negXScale.setText(Integer.toString(xScaleDegree+1));
		posYScale.setText(Integer.toString(yScaleDegree+1));
		negYScale.setText(Integer.toString(yScaleDegree+1));
		
		
		convertRec1.set((double) rec1XValue.getValue(), (double) rec1YValue.getValue(), (double) rec1Width.getValue(), (double) rec1Height.getValue());
		
		convertRec2.set((double) rec2XValue.getValue(), (double) rec2YValue.getValue(), (double) rec2Width.getValue(), (double) rec2Height.getValue());
		

		if (convertRec1.getLeft() < convertRec2.getLeft()) {
			overlapRec = IcsRectangle.partialOverlap(convertRec1,convertRec2);
		 } else if(convertRec1.getLeft() > convertRec2.getLeft()) {
			 overlapRec = IcsRectangle.partialOverlap(convertRec2,convertRec1);
		 } else if (convertRec1.area() > convertRec2.area()){
			 overlapRec = IcsRectangle.partialOverlap(convertRec1,convertRec2); // if both rectangles have the same left most point, then pass the rectangle with the larger area
		 } else {
			 overlapRec = IcsRectangle.partialOverlap(convertRec2,convertRec1);
		 }
		
		if(convertRec1.area() < convertRec2.area()) {//make sure the one with bigger area is behind the other rectangle
		
			rec1.setWidth(convertRec1.getWidth()/Math.pow(10, xScaleDegree)*25);
			rec1.setHeight(convertRec1.getHeight()/Math.pow(10, yScaleDegree)*25);
			rec1.setTranslateX(convertRec1.getLeft()/Math.pow(10, xScaleDegree)*25);
			rec1.setTranslateY((convertRec1.getBottom() + convertRec1.getHeight())/Math.pow(10, yScaleDegree)*-25);//add because this rectangle uses top left as point, I use bottom left
			
			rec2.setWidth(convertRec2.getWidth()/Math.pow(10, xScaleDegree)*25);
			rec2.setHeight(convertRec2.getHeight()/Math.pow(10, yScaleDegree)*25);
			rec2.setTranslateX(convertRec2.getLeft()/Math.pow(10, xScaleDegree)*25);
			rec2.setTranslateY((convertRec2.getBottom() + convertRec2.getHeight())/Math.pow(10, yScaleDegree)*-25);//add because this rectangle uses top left as point, I use bottom left
			
			
		} else {
			rec2.setWidth(convertRec1.getWidth()/Math.pow(10, xScaleDegree)*25);
			rec2.setHeight(convertRec1.getHeight()/Math.pow(10, yScaleDegree)*25);
			rec2.setTranslateX(convertRec1.getLeft()/Math.pow(10, xScaleDegree)*25);
			rec2.setTranslateY((convertRec1.getBottom() + convertRec1.getHeight())/Math.pow(10, yScaleDegree)*-25);//add because this rectangle uses top left as point, I use bottom left
			
			rec1.setWidth(convertRec2.getWidth()/Math.pow(10, xScaleDegree)*25);
			rec1.setHeight(convertRec2.getHeight()/Math.pow(10, yScaleDegree)*25);
			rec1.setTranslateX(convertRec2.getLeft()/Math.pow(10, xScaleDegree)*25);
			rec1.setTranslateY((convertRec2.getBottom() + convertRec2.getHeight())/Math.pow(10, yScaleDegree)*-25);//add because this rectangle uses top left as point, I use bottom left
			

		}
		
		rec3.setWidth(overlapRec.getWidth()/Math.pow(10, xScaleDegree)*25);
		rec3.setHeight(overlapRec.getHeight()/Math.pow(10, yScaleDegree)*25);
		rec3.setTranslateX(overlapRec.getLeft()/Math.pow(10, xScaleDegree)*25);
		rec3.setTranslateY((overlapRec.getBottom()+overlapRec.getHeight())/Math.pow(10, yScaleDegree)*-25);
		recSummaries.setText("rec1: " + convertRec1.toString() + "\nrec2: " + convertRec2.toString() + "\noverlap: " + overlapRec.toString());
		recArea.setText("rec1: " + convertRec1.area() + ", rec2: " + convertRec2.area() );
		recPerimeter.setText("rec1: " + convertRec1.perimeter() + ", rec2: " + convertRec2.perimeter() + "\ntotal perimeter: " + IcsRectangle.totalPerimeter(convertRec1, convertRec2));

		if(convertRec1.contains(convertRec2) == true) {
			recContains.setText("Rectangle 2 is inside Rectangle 1");
		} else if (convertRec2.contains(convertRec1) == true){
			recContains.setText("Rectangle 1 is inside Rectangle 2");
		} else if (convertRec2.area() > convertRec1.area()) {
			recContains.setText("Rectangle 1 is not inside Rectangle 2");
		} else {
			recContains.setText("Rectangle 2 is not inside Rectangle 1");
		}
			
		
		
		
	}
	
	@FXML
	private void hideRec1() {
		if (cbHideRec1.isSelected() == false) {
			this.rec1.setVisible(false);
		} else {
			this.rec1.setVisible(true);
		}	
	}
	
	@FXML
	private void hideRec2() {
		if (cbHideRec2.isSelected() == false) {
			this.rec2.setVisible(false);
		} else {
			this.rec2.setVisible(true);
		}	
	}
	
	@FXML
	private void hideRec3() {
		if (cbHideRec3.isSelected() == false) {
			this.rec3.setVisible(false);
		} else {
			this.rec3.setVisible(true);
		}
		
	}
}
