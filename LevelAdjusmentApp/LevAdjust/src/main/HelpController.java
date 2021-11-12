package main;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpController {
	
    @FXML
    private Hyperlink aiciHyperlink = new Hyperlink("https://www.youtube.com/watch?v=YEY4RID2dlc");
	
    @FXML
    private TableView<PixelImage> tableColourLevel;
    
    @FXML
    private TableColumn<PixelImage, String> numeCuloareColumn;

    @FXML
    private TableColumn<PixelImage, Integer> nivelRosuColumn;

    @FXML
    private TableColumn<PixelImage, Integer> nivelVerdeColumn;

    @FXML
    private TableColumn<PixelImage, Integer> nivelAlbastruColumn;
    
	public HelpController() {
		// TODO Auto-generated constructor stub
	}
	//TODO[pentru linkuri!!!!!!!!!!!!]
	//https://www.youtube.com/watch?v=P9z1dRPmeUQ
	//When this method is called, it will change EditColours Scene to LevAdjust Scene 
	@FXML
	public void changeToPreviousScene(ActionEvent event) throws Exception {
		//When it is reading the LevAdjust.fxml file could occur an IOException
		Parent levAdjustSceneParent = FXMLLoader.load(getClass().getResource("LevAdjust.fxml"));
		
		Scene levAdjustScene = new Scene(levAdjustSceneParent);
		
		//This method is not having directly access to the stage. Therefore, it should get the Stage information
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(levAdjustScene);
		window.show();
	}
	
	@FXML
	public void pressAiciHyperlink(MouseEvent event) {
		try {
			Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=YEY4RID2dlc"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Get the defined PixelImage objects from ObservableList; it was added as I want to show it in the table
	public ObservableList<PixelImage> getPixelImageItemsTableView() {
	    final ObservableList<PixelImage> pixels = FXCollections.observableArrayList(
	        new PixelImage("red", 255, 0, 0), //rosu
	        new PixelImage("yellow",255,255,0),
	    	new PixelImage("green", 0, 255, 0), //verde
	    	new PixelImage("cyans", 0, 255, 255),
	    	new PixelImage("blue", 0, 0, 255), //albastru
	    	//new PixelImage("violet", 238, 130, 238)  //(238, 130, 238)
	    	new PixelImage("magenta", 255, 0, 255)
	    	    	
	    );
	    return pixels;
	}
	
	public void setItemsTableView() {
		//Asociaza data cu tabelul
		numeCuloareColumn.setCellValueFactory(new PropertyValueFactory<PixelImage, String>("name"));
		nivelRosuColumn.setCellValueFactory(new PropertyValueFactory<PixelImage, Integer>("redValue"));
		nivelVerdeColumn.setCellValueFactory(new PropertyValueFactory<PixelImage, Integer>("greenValue"));
		nivelAlbastruColumn.setCellValueFactory(new PropertyValueFactory<PixelImage, Integer>("blueValue"));
		
		// Seteaza data in tabel
		tableColourLevel.setItems(getPixelImageItemsTableView());
	}
}
