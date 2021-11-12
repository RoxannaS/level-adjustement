package main;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditColoursController {
	
	@FXML
	private AnchorPane background;

	@FXML
	private Pane pane;	
	
	@FXML
    private Slider redLevelSlider;

    @FXML
    private Slider yellowLevelSlider;

    @FXML
    private Slider greenLevelSlider;

    @FXML
    private Slider cyansLevelSlider;

    @FXML
    private Slider blueLevelSlider;

    @FXML
    private Slider magentaLevelSlider;
	

    @FXML
    private ToggleButton redLevelToggleButton;

    @FXML
    private ToggleButton yellowLevelToggleButton;

    @FXML
    private ToggleButton greenLevelToggleButton;

    @FXML
    private ToggleButton cyansLevelToggleButton;

    @FXML
    private ToggleButton blueLevelToggleButton;

    @FXML
    private ToggleButton magentaLevelToggleButton;

    @FXML
    private ToolBar toolBar;

    @FXML
    private TextField magentaLevelValue;

    @FXML
    private TextField blueLevelValue;

    @FXML
    private TextField cyansLevelValue;

    @FXML
    private TextField yellowLevelValue;

    @FXML
    private TextField greenLevelValue;

    @FXML
    private TextField redLevelValue;
    
    @FXML 
    private ListView selectedImagePathListView;
    
    @FXML
    private CheckBox sameNameCheckBox;
	
    //Calea celui de-al doilea fisier BMP care se creea dupa modificarea imaginii
    @FXML 
    private TextField newImageName;
    
    private String selectedImagePath;
  	
	public EditColoursController() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * This method accepts a person to initialize the view
     * @FXML private TextField imagePath; from LevAdjustController.java 
     */
    public void setPath(String imagePath) {
    	selectedImagePath = imagePath;
    	
    	// Adauga in ListView calea imaginii ce urmeaza sa fie modificata
    	selectedImagePathListView.getItems().add(imagePath);
   
    }
    
	//When this method is called, it will change EditColours Scene to About Scene
    @FXML
    void changeToAboutScene(ActionEvent event) throws Exception {
		//Parent helpSceneParent = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		/**
	     * When this method is called, it will pass the selected Person object to
	     * a the detailed view
	     */
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Help.fxml"));
    	
    	//When it is reading the LevAdjust.fxml file could occur an IOException
		Parent helpSceneParent = null;
		try {
			helpSceneParent = loader.load();
		} catch (IOException helpSceneParentFXMLLoaderException) {
			// TODO Auto-generated catch block
			helpSceneParentFXMLLoaderException.printStackTrace();
		}
		
		Scene helpScene = new Scene(helpSceneParent);
        
		//This method is not having directly access to the stage. Therefore, it should get the Stage information
		//Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Stage window = (Stage) toolBar.getScene().getWindow();
		
        //access the controller and call a method
        HelpController helpController = loader.getController();
        helpController.setItemsTableView();
		
		window.setScene(helpScene);
		window.show();
    }
	
	//When this method is called, it will change EditColours Scene to LevAdjust Scene 
    @FXML
	public void changeEditColoursScene(ActionEvent event) throws Exception {
		//When it is reading the LevAdjust.fxml file could occur an IOException
		Parent levAdjustSceneParent = FXMLLoader.load(getClass().getResource("LevAdjust.fxml"));
		
		Scene levAdjustScene = new Scene(levAdjustSceneParent);
		
		//This method is not having directly access to the stage. Therefore, it should get the Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(levAdjustScene);
		window.show();
	}
	
	@FXML
	public void changeSliderValues(MouseEvent event) {
		redLevelValue.setText(String.valueOf((int) redLevelSlider.getValue()));
		yellowLevelValue.setText(String.valueOf((int) yellowLevelSlider.getValue()));
		greenLevelValue.setText(String.valueOf((int) greenLevelSlider.getValue()));
		cyansLevelValue.setText(String.valueOf((int) cyansLevelSlider.getValue()));
		blueLevelValue.setText(String.valueOf((int) blueLevelSlider.getValue()));
		magentaLevelValue.setText(String.valueOf((int) magentaLevelSlider.getValue()));
		
		Color color = Color.rgb((int) redLevelSlider.getValue(), (int) greenLevelSlider.getValue(), (int) blueLevelSlider.getValue());
		background.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	@FXML
	public void pressRedLevelToggleButton(ActionEvent event) {
		if (redLevelToggleButton.isSelected()) {
			redLevelSlider.setDisable(false);
			//Debug message
			//System.out.println("Activeaza Slider");
		} else {
			redLevelSlider.setDisable(true); 
			//Debug message
			//System.out.println("Dezactiveaza Slider");
		}
	}

	@FXML
	public void pressYellowLevelToggleButton(ActionEvent event) {
		if (yellowLevelToggleButton.isSelected()) {
			yellowLevelSlider.setDisable(false);
		} else {
			yellowLevelSlider.setDisable(true);
		}
	}
	
	@FXML
	public void pressGreenLevelToggleButton(ActionEvent event) {
		if (greenLevelToggleButton.isSelected()) {
			greenLevelSlider.setDisable(false);
		} else {
			greenLevelSlider.setDisable(true);
		}
	}
	
	@FXML
	public void pressCyansLevelToggleButton(ActionEvent event) {
		if (cyansLevelToggleButton.isSelected()) {
			cyansLevelSlider.setDisable(false);
		} else {
			cyansLevelSlider.setDisable(true);
		}
	}
	
	@FXML
	public void pressBlueLevelToggleButton(ActionEvent event) {
		if (blueLevelToggleButton.isSelected()) {
			blueLevelSlider.setDisable(false);
		} else {
			blueLevelSlider.setDisable(true);
		}
	}
	
	@FXML
	public void pressMagentaLevelToggleButton(ActionEvent event) {
		if (magentaLevelToggleButton.isSelected()) {
			magentaLevelSlider.setDisable(false);
		} else {
			magentaLevelSlider.setDisable(true);
		}
	}
	
	@FXML
	public void saveModidifiedColoursImage(ActionEvent event) {
		if ( !(redLevelToggleButton.isSelected() || yellowLevelToggleButton.isSelected() || greenLevelToggleButton.isSelected() ||  
				cyansLevelToggleButton.isSelected() || blueLevelToggleButton.isSelected() || magentaLevelToggleButton.isSelected()) ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nu ati modificat valoarea nici unei culori!");
			alert.setHeaderText("Atentie!");
			alert.setContentText("Modificati valoarea cel putin al unei culori!");

			alert.showAndWait();
		} else if (newImageName.getText() == null || newImageName.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Cale invalida!");
			alert.setHeaderText("Atentie!");
			alert.setContentText("Introduceti o cale valida !");

			alert.showAndWait();
		} else {
			//Crearea unui obiect al clasei Image
			Image myImage = new Image();
			
			//Creare unei instante a clasei ImageEditor pentru a putea prelucra imaginea folosind metodele implementate de aceasta
			ImageEditor editor = new ImageEditor();
			
			//Citirea fisierului BMP
			myImage.readImage(selectedImagePath); // C:\\eclipse-workspace\\images\\Birds.bmp
			
			//Crearea unui vector de obiecte ale clasei PixelImage(creearea pixelilor imaginii-gruparea a celor trei valori ce constituie pixelul)
			PixelImage[] pixels = myImage.getPixels(); //pixelii din imaginea initiala(originala)
			
			//Modificarea imaginii
			//Modificarea pixelilor imaginii originale si salvarea lor intr-un alt vector de pixeli
			PixelImage[] cPixels2 = editor.changeBlackWhiteLevelImage(pixels, Integer.valueOf(redLevelValue.getText()), 
					Integer.valueOf(yellowLevelValue.getText()), Integer.valueOf(greenLevelValue.getText()), 
					Integer.valueOf(cyansLevelValue.getText()), Integer.valueOf(blueLevelValue.getText()), 
					Integer.valueOf(magentaLevelValue.getText())); 
			
			String pathImageOut2 = (new File(selectedImagePath)).getParentFile().getAbsolutePath() + "\\" + newImageName.getText() + ".bmp";
			
			//Crearea noii imagini
			myImage.writeImage(pathImageOut2, cPixels2); //C:\\eclipse-workspace\\images\\NewBirds2.bmp
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Imaginea a fost modificata!");
			alert.setHeaderText("Atentie!");
			alert.setContentText("Imaginea selectata a fost modificata in condormitate cu optiunile selectate!");

			alert.showAndWait();
		}
	}
	
}
