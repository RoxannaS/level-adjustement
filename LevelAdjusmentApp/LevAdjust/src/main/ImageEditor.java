package main;

public class ImageEditor {
	//Constructor fara parametrii 
	public ImageEditor() {
	}
	
	//Metoda folosita pentru a schimba contrastul imaginii
	public PixelImage[] changeContrastImage(PixelImage[] pixels, int contrast) {
		//Inceputul etapei de modificare contrast 
		long startTime = System.nanoTime();

		//Crearea unui vector de pixeli cu aceeasi lungime cu cea a vectorului de pixili ai imaginii nemodificate
		PixelImage[] modifiedPixels = new PixelImage[pixels.length];
        
		//Calcularea constantei factor din formula recalcularii valorilor pixelilor folosind valoarea contrastului data
        float factor = (float)(259 * (contrast + 255)) / (float)(255 * (259 - contrast));
        //Debug
        //System.out.println("factor = " + factor);
        
        //Modificarea valorii pixelilor
        for(int i = 0; i < modifiedPixels.length; i++) {
        	int oldRedValue = pixels[i].getRedValue();
            int oldGreenValue = pixels[i].getGreenValue();
            int oldBlueValue = pixels[i].getBlueValue();
        	
            //Recalcularea valorii pixelilor in functie de vechile valori si de constanta factor
    		int redValue = Math.round(factor * (oldRedValue - 128) + 128);
    		int greenValue = Math.round(factor * (oldGreenValue - 128) + 128);
    		int blueValue = Math.round(factor * (oldBlueValue - 128) + 128);
    		
    		//Crearea unui nou pixel
    		PixelImage newP = new PixelImage();
    		//Atribuirea valorilor de rosu, verde si albastru
    		newP.setPixel(redValue, greenValue, blueValue);
    		//Salvarea in vectorul de pixeli
    		modifiedPixels[i] = newP;
    		//Debug
    		//System.out.println("Old Pixel " + pixels[i]);
    		//System.out.println("New Pixel " + modifiedPixels[i]);
    		//System.out.println();
    		
        }
        
        //Sfarsitul etapei de modificare a contrastului imaginii
        long endTime = System.nanoTime();
		
		long timeDifference = endTime - startTime;
		System.out.println("Timpul de executiei al etapei de modificare al contrastului imaginii este: " + timeDifference + " ns");
        //Returnarea unui vector de pixeli ce contine pixelii modificati ai imaginii originale
        return modifiedPixels;
	}
	
	//Modificarea black/white a unei imaginii in functie de nivelele de rosu, galben, verde, turcoaz, albastru si magenta
	public PixelImage[] changeBlackWhiteLevelImage(PixelImage[] pixels, int redLevel, int yellowLevel, int greenLevel, int cyansLevel, int blueLevel, int magentaLevel) {
		//Inceputul etapei de modificare al nivelului black/white a imaginii
		long startTime = System.nanoTime();
		
		//Crearea unui vector de pixeli cu aceeasi lungime cu cea a vectorului de pixili ai imaginii nemodificate
		PixelImage[] modifiedPixels = new PixelImage[pixels.length];
        
		//Modificarea valorii pixelilor in functie de nivelul de rosu
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (redLevel + pixels[i].getGreenValue() + pixels[i].getBlueValue())/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;	
        }
        
        //Modificarea valorii pixelilor in functie de nivelul de galben
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (yellowLevel + yellowLevel + pixels[i].getBlueValue())/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;
        }
        
        //Modificarea valorii pixelilor in functie de nivelul de verde
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (pixels[i].getRedValue() + greenLevel + pixels[i].getBlueValue())/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;
        }
        
        //Modificarea valorii pixelilor in functie de nivelul de turcoaz
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (pixels[i].getRedValue() + cyansLevel + cyansLevel)/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;
        }
        
      //Modificarea valorii pixelilor in functie de nivelul de albastru
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (pixels[i].getRedValue() + pixels[i].getGreenValue() + blueLevel)/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;
        }
        
        //Modificarea valorii pixelilor in functie de nivelul de magenta
        for(int i = 0; i < modifiedPixels.length; i++) {        	
        	int avgValue = (magentaLevel + pixels[i].getGreenValue() + magentaLevel)/3;

    		int redValue = avgValue;
    		int greenValue = avgValue;
    		int blueValue = avgValue;
    		
    		PixelImage newP = new PixelImage();
    		newP.setPixel(redValue, greenValue, blueValue);
    		modifiedPixels[i] = newP;
        }
        
        //Sfarsitul etapei de modificare black/white a imaginii 
        long endTime = System.nanoTime();
		
		long timeDifference = endTime - startTime;
		System.out.println("Timpul de executiei al etapei de modificare black/white a imaginii este: " + timeDifference + " ns");
        
        //Returnarea unui vector de pixeli ce contine pixelii modificati ai imaginii originale
        return modifiedPixels;
	}
}
