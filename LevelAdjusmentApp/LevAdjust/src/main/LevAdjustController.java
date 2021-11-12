package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LevAdjustController {
    @FXML
    private Accordion accordion;
	
	@FXML
    private Button modifyImageButton;

	@FXML
    private ImageView unmodifiedImageView;

    @FXML
    private ImageView modifiedContrastImageView;

    @FXML
    private ImageView modifiedColoursImageView;
	
	@FXML 
	private ToggleButton contrastButton;
	
	@FXML 
	private ToggleButton coloursButton;
	
	@FXML 
	private ToolBar toolBar;
	
	@FXML 
	private ListView listView;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private TextField imagePath;
	
	//Definirea unui obiect al clasei Image din pachetul main si alocare memorie pentru obiect myImage
	//Daca nu s-ar fi specificat pachetul ar fi aparut o confuzie; importul javafx.scene.image.Image necesar pentru a seta imagini in 
	//controloale de tip ImageView din scene
	private main.Image myImage = new main.Image();    	    	

	//Definirea unei instante a clasei ImageEditor pentru a putea prelucra imaginea folosind metodele implementate de aceasta
	private ImageEditor editor = new ImageEditor();
	
	//Vector ce contine pixelii imaginii
	PixelImage[] pixels;
	
	public LevAdjustController() {
		// TODO Auto-generated constructor stub
	}
	
	public Button getModifyImageButton() {
		return modifyImageButton;
	}

	public void setModifyImageButton(Button modifyImageButton) {
		this.modifyImageButton = modifyImageButton;
	}

	public ToggleButton getContrastButton() {
		return contrastButton;
	}

	public void setContrastButton(ToggleButton contrastButton) {
		this.contrastButton = contrastButton;
	}
	
	@FXML
	//When this method is called, it will change LevAdjust Scene to EditContrast Scene
	public void changeLevAdjustScene(ActionEvent event) throws Exception {
		//When it is reading the EditContrast.fxml file could occur an IOException
		if (!(imagePath.getText().trim().isEmpty())) {
			
			//This method is not having directly access to the stage. Therefore, it should get the Stage information
			//This line gets the Stage information
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
			if (contrastButton.isSelected()) {
				setEditContrastScene(window);
			} else if (coloursButton.isSelected()) {
				//Parent editSceneParent = FXMLLoader.load(getClass().getResource("EditColours.fxml"));
			
				//Scene editScene = new Scene(editSceneParent);

				//window.setScene(editScene);
				//window.show();

				/**
				 * When this method is called, it will pass the selected Person object to
				 * a the detailed view
				 */
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("EditColours.fxml"));
				Parent editColoursSceneParent = loader.load();
	        
				Scene editColoursScene = new Scene(editColoursSceneParent);
	        
				//access the controller and call a method
				EditColoursController editColoursController = loader.getController();
				editColoursController.setPath(imagePath.getText());
//	        	editColoursController.setImageObject(myImage);
//	        	editColoursController.setEditor(editor);
//	        	editColoursController.setPixels(pixels);
	        
				window.setScene(editColoursScene);
				window.show();
			}
		} else {
			System.out.println("Null text field");
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nume pentru fisier sau cale invalida!");
			alert.setHeaderText("Atentie!");
			alert.setContentText("Alegeti un nume de fisier sau introduceti o cale valida !");

			alert.showAndWait();

		}
	}

	public void setEditContrastScene(Stage window) {
    	//Debug message
        //System.out.println("EditConstrastScene!");
		
        ToolBar toolBar = new ToolBar();
        
        //Adauga un separator in tool bar
        Separator separatorToolBar = new Separator();
        separatorToolBar.setMinWidth(660);
        toolBar.getItems().add(separatorToolBar);
         
       //A button with the specified text caption and icon.
       //Create a input stream 
        FileInputStream inputHelpIcon = null;
		try {
			inputHelpIcon = new FileInputStream("..\\images\\used images\\help.png");
		} catch (FileNotFoundException imageViewPathHelpButtonException) {
			// TODO Auto-generated catch block
			imageViewPathHelpButtonException.printStackTrace();
		} 
        Image imageButton = new Image(inputHelpIcon);
        Button helpButton = new Button("Help");
        //helpButton.setMaxWidth(Double.MAX_VALUE);
        helpButton.setPrefWidth(90);
        ImageView imageViewHelpButton = new ImageView(imageButton);
        imageViewHelpButton.setFitWidth(15);
        imageViewHelpButton.setFitHeight(15);
        helpButton.setGraphic(imageViewHelpButton);
        
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override 
    	    public void handle(ActionEvent e) {
    	    	
    	    	/**
    		     * When this method is called, it will pass the selected Person object to
    		     * a the detailed view
    		     */
    	    	FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(getClass().getResource("Help.fxml"));
    	    	
    	    	//When it is reading the LevAdjust.fxml file could occur an IOException
    			Parent levAdjustSceneParent = null;
				try {
					levAdjustSceneParent = loader.load();
				} catch (IOException levAdjustSceneParentFXMLLoaderException) {
					// TODO Auto-generated catch block
					levAdjustSceneParentFXMLLoaderException.printStackTrace();
				}
    			
    			Scene levAdjustScene = new Scene(levAdjustSceneParent);
    	        
    	        //access the controller and call a method
    	        HelpController helpController = loader.getController();
    	        System.out.println("DEBUG 2");
    	        helpController.setItemsTableView();
    	        System.out.println("DEBUG 3");
    			
    			window.setScene(levAdjustScene);
    			window.show();
    	    }
    	});
        
        //Adauga butonul help in tool bar  
        toolBar.getItems().add(helpButton);
        
        //Crearea unui VBox principal (un tip de panel/chenar veritical) folosind controlul de tip tool bar anterior creat
        //Acest VBox reprezinta scena pe care se vor pune alte controale
        VBox vBox = new VBox(toolBar);
        //vBox.setPadding(new Insets(10, 50, 50, 50));
         
        //Crearea unui HBox(un tip de panel/chenar orizontal)
        HBox hBoxWithSeparator = new HBox();
        //Adauga un separator intre tool bar si elementelor urmatoare
        Separator separatorVBox = new Separator();
        //Setarea dimensiunilor separatorului
        separatorVBox.setMinWidth(1000);
        separatorVBox.setMinHeight(50);
        //separatorVBox.setOrientation(Orientation.VERTICAL);\
        //Adaugarea separatorului in HBox
        hBoxWithSeparator.getChildren().add(separatorVBox);
        
        //Adaugarea HBox-ului cu separator in VBox-ul scenei
        vBox.getChildren().add(hBoxWithSeparator);
        
        HBox hBoxWithContrastValueField = new HBox();  
        
        Label labelContrastValue = new Label("Introduceti valoarea contrastului pe care o doriti sa fie folosita la modificarea imaginea: "); 
        labelContrastValue.setPadding(new Insets(0, 0, 50, 50));
        
        TextField contrastValue = new TextField ();
        
        //Filtrarea valorii din Contrast TextField
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };

        contrastValue.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        
        hBoxWithContrastValueField.getChildren().addAll(labelContrastValue, contrastValue);
        hBoxWithContrastValueField.setSpacing(10);
        
        vBox.getChildren().add(hBoxWithContrastValueField);
      
        HBox hBoxWithTextField = new HBox();
        
        Label labelContrastEditScene = new Label("Introduceti calea imaginii noi: "); 
        labelContrastEditScene.setPadding(new Insets(0, 0, 0, 50));
        
        TextField newImageNameTextField = new TextField ();
        
        
        hBoxWithTextField.getChildren().addAll(labelContrastEditScene, newImageNameTextField);
        hBoxWithTextField.setSpacing(38);
        
        vBox.getChildren().add(hBoxWithTextField);
        
        HBox hBoxWithRadioButton = new HBox();
        
        //Crearea unui buton de tip Radio Button prin care se poate alege daca se va folosi acelasi nume pentru 
        //imaginea modificata
        RadioButton folosireAcelasiNumeRadioButton = new RadioButton("Foloseste acelasi nume");
        folosireAcelasiNumeRadioButton.setPadding(new Insets(0, 0, 0, 260));
        
        hBoxWithRadioButton.getChildren().add(folosireAcelasiNumeRadioButton);
        
        vBox.getChildren().add(hBoxWithRadioButton);
        
        Label continuareLabelContrastEditScene = new Label(" sau foloseste una dintre sugestiile "); 
        continuareLabelContrastEditScene.setPadding(new Insets(0, 0, 0, 50));
        
        HBox  hBoxWithChoiceBox  = new HBox();
        
        ChoiceBox choiseBoxMenuName = new ChoiceBox();
        choiseBoxMenuName.setPrefWidth(150);
        choiseBoxMenuName.setItems(FXCollections.observableArrayList(
            "ImagineContrastModificat",  new Separator(), "ImagineModificata", 
            new Separator(), "ImagineNoua")
        );  
        
        //A Save button with the specified text caption and icon.
        //Create a input stream 
        FileInputStream inputSaveIcon = null;
 		try {
 			inputSaveIcon = new FileInputStream("..\\images\\used images\\save.png");
 		} catch (FileNotFoundException imageViewPathHelpButtonException) {
 			// TODO Auto-generated catch block
 			imageViewPathHelpButtonException.printStackTrace();
 		} 
   
        Image imageSaveButton = new Image(inputSaveIcon);
        Button saveButton = new Button("Salveaza");
        //saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setPrefWidth(90);
        ImageView imageViewSaveButton = new ImageView(imageSaveButton);
        imageViewSaveButton.setFitWidth(15);
        imageViewSaveButton.setFitHeight(15);
        saveButton.setGraphic(imageViewSaveButton);

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override 
    	    public void handle(ActionEvent e) {
    	    	//Modifica imaginea folosind valoarea tastata a contrastului preluata din TextField corespunzator si 
    	    	//salveaz-o folosind numele optat
    	    	//Debug messages
    	    	//System.out.println("Save button");
    	    	//System.out.println("Valoare contrast " + Integer.valueOf(contrastValue.getText()));
    	    	
    	    	//Calea fisiserului .bmp selectat anterior
    	    	String pathImageIn = imagePath.getText();
    	    	
    			String pathImageOut = null;
    			
    			//Setarea a caii fisierului BMP care se va creea dupa modificarea imaginii
    			if (folosireAcelasiNumeRadioButton.isSelected()) {
    				pathImageOut = pathImageIn;
    				System.out.println("Radio button");
    			} else if (!newImageNameTextField.getText().trim().isEmpty()) {
    				    System.out.println("Text Field");
    					pathImageOut = (new File(pathImageIn)).getParentFile().getAbsolutePath() + "\\" + newImageNameTextField.getText() + ".bmp";
    			} else if (choiseBoxMenuName.getValue() != null) { //(new File(pathImageIn)).getParentFile().getName()
    					System.out.println("Choice Box " + (new File(pathImageIn)).getParentFile().getAbsolutePath() + "  " + choiseBoxMenuName.getValue().toString());
    					pathImageOut = (new File(pathImageIn)).getParentFile().getAbsolutePath() + "\\" + choiseBoxMenuName.getValue().toString() + ".bmp";
    			} else {
    				
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Nume pentru fisier sau cale invalida!");
    				alert.setHeaderText("Atentie!");
    				alert.setContentText("Alegeti un nume de fisier sau introduceti o cale valida !");

    				alert.showAndWait();

    			}
    	    	
    	    	if ( !((Integer.valueOf(contrastValue.getText()) > 0) && (Integer.valueOf(contrastValue.getText()) < 256) && 
    	    			!(contrastValue.getText().trim().isEmpty())) ) {
    	    		Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Nume pentru fisier sau cale invalida!");
    				alert.setHeaderText("Atentie!");
    				alert.setContentText("Alegeti un nume de fisier sau introduceti o cale valida !");

    				alert.showAndWait();
    	    	}
    			
    	    	if ( ((Integer.valueOf(contrastValue.getText()) > 0) && (Integer.valueOf(contrastValue.getText()) < 256) && 
    	    	!contrastValue.getText().trim().isEmpty()) && ((folosireAcelasiNumeRadioButton.isSelected()) ||
    	    	(!newImageNameTextField.getText().trim().isEmpty()) || (choiseBoxMenuName.getValue() != null)) ) {
    	    		System.out.println("Modificare contrast");
    	    		//Citirea fisierului BMP
    	    		myImage.readImage(pathImageIn); // C:\\eclipse-workspace\\images\\Birds.bmp
    			
    	    		//Crearea unui vector de obiecte ale clasei PixelImage(creearea pixelilor imaginii-gruparea a celor trei valori ce constituie pixelul)
    	    		pixels = myImage.getPixels(); //pixelii din imaginea initiala(originala)
    			
    	    		//Modificarea imaginii
    	    		//Modificarea pixelilor imaginii si salvarea lor intr-un alt vector de pixeli
    	    		PixelImage[] cPixels = editor.changeContrastImage(pixels, Integer.valueOf(contrastValue.getText())); 
    			
    	    		//Debug messages
    	    		//System.out.println("Path Image Out " + pathImageOut);
    	    		//Creearea noii imagini
    	    		myImage.writeImage(pathImageOut, cPixels); //C:\\eclipse-workspace\\images\\NewBirds.bmp
    	    		
    	    		Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Imaginea a fost modificata!");
    				alert.setHeaderText("Atentie!");
    				alert.setContentText("Imaginea selectata a fost modificata in condormitate cu optiunile selectate!");

    				alert.showAndWait();
    	    	}
    	    }
    	});
        
        hBoxWithChoiceBox.getChildren().addAll(continuareLabelContrastEditScene, choiseBoxMenuName, saveButton);
        hBoxWithChoiceBox.setPadding(new Insets(0, 0, 230, 0));
        hBoxWithChoiceBox.setSpacing(10);
        
        vBox.getChildren().add(hBoxWithChoiceBox);
        
//        HBox hBoxWithSeparatorBeforeBackButton = new HBox();
//        //Adauga un separator intre elemenetele din interfata si butonul de back
//        Separator separatorVBoxBeforeBackButton = new Separator();
//        separatorVBoxBeforeBackButton.setMinWidth(800);
//        separatorVBoxBeforeBackButton.setMinHeight(250);
//        //separatorVBox.setOrientation(Orientation.VERTICAL);
//        hBoxWithSeparatorBeforeBackButton.getChildren().add(separatorVBoxBeforeBackButton);
//        
//        vBox.getChildren().add(hBoxWithSeparatorBeforeBackButton);
        
        HBox hBoxWithBackButton = new HBox();
        
        Separator separatorVBoxLeftBackButton = new Separator();
        separatorVBoxLeftBackButton.setMinWidth(660);
        separatorVBoxLeftBackButton.setOrientation(Orientation.VERTICAL);
        
        Button backButton = new Button();
        backButton.setMaxWidth(90);
        backButton.setPrefWidth(90);
        backButton.setText("Back");
        backButton.setStyle("-fx-base: rgb(99, 147, 139);");
        
        hBoxWithBackButton.getChildren().addAll(separatorVBoxLeftBackButton, backButton);
        
		// add button to VBox
        vBox.getChildren().add(hBoxWithBackButton);
        
    	backButton.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override 
    	    public void handle(ActionEvent e) {
    	    	//When it is reading the LevAdjust.fxml file could occur an IOException
    			Parent levAdjustSceneParent = null;
				try {
					levAdjustSceneParent = FXMLLoader.load(getClass().getResource("LevAdjust.fxml"));
				} catch (IOException levAdjustSceneParentFXMLLoaderException) {
					// TODO Auto-generated catch block
					levAdjustSceneParentFXMLLoaderException.printStackTrace();
				}
    			
    			Scene levAdjustScene = new Scene(levAdjustSceneParent);
    			
    			window.setScene(levAdjustScene);
    			window.show();
    	    }
    	});
    	
		Scene contrastEditScene = new Scene(vBox,774,502);
		
        window.setScene(contrastEditScene);
        window.show();
	}
	
	//When this method is called, it will change EditColours Scene to Help Scene
    @FXML
    public void changeToHelpScene(ActionEvent event) throws Exception {

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
	
	@FXML
	void pressLoadImageButton(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		// Setting the specific directory where the search of the .BMP files is needed o be done
		fileChooser.setInitialDirectory(new File("C:\\Users\\Roxana\\eclipse-workspace\\images"));
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("BMP Files", "*.bmp"));
		
		File selectedFileImage = fileChooser.showOpenDialog(null);

		if (selectedFileImage != null) {
			listView.getItems().add(selectedFileImage.getAbsolutePath());
			imagePath.setText(selectedFileImage.getAbsolutePath());
		} else {
			System.out.println("The file is not valid!");
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fisier invalid!");
			alert.setHeaderText("Atentie!");
			alert.setContentText("Alegeti un fisier valid!");

			alert.showAndWait();

		}
	}
	
	@FXML
	public void pressShowImageButton(ActionEvent event) {
		File file = new File(imagePath.getText());
		Image image = new Image(file.toURI().toString()); //(listView.getSelectionModel().select(0)).toString()
		imageView.setImage(image); 
	}
	
	@FXML
	public void pressContrastToggleButton(ActionEvent event) {
		if (contrastButton.isPressed()) {
			contrastButton.setSelected(false);
			coloursButton.setSelected(true);
		} else {
			contrastButton.setSelected(true);
			coloursButton.setSelected(false);
		}
	}

	@FXML
	public void pressColoursToggleButton(ActionEvent event) {
		if (coloursButton.isPressed()) {
			coloursButton.setSelected(false);
			contrastButton.setSelected(true);
		} else {
			coloursButton.setSelected(true);
			contrastButton.setSelected(false);
		}
	}
}
