package main;

public abstract class ImageAbstract {

	public ImageAbstract() {	
	}
	
	public abstract void readImage(String imageFilePath);
	
	public abstract void writeImage(String modifiedImageFilePath, PixelImage[] pixels);
}
