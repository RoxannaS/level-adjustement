package main;
import java.io.*;

import javax.swing.SingleSelectionModel;

public class Image extends ImageAbstract {
	
	//Vectorul de bytes in care sunt salvati bytes imaginii BMP
	public byte[] bArray;

	//Lungimea vectorului de bytes
	public int sizeImage;
	
	//Vectorul de bytes in care se retine BitmapFileHeader-ul al fisierului BMP
	public byte bitmapFileHeader[] = new byte[14];
	
	//Vectorul in care se retine BitmapInfoHeader-ul al imaginii
	public byte bitmapInfoHeader[] = new byte[40];
	
	//Vectorul de pixeli
	public PixelImage[] pixels;

	//Citeste imaginea specificata prin cale
	public void readImage(String imageFilePath) {
		//Inceputul citirii imaginii
		long startTime = System.nanoTime();
		
		//Bytes dintr-un fisier BMP sunt stocati intr-un vector de bytes 
		File image = new File(imageFilePath);

		try (FileInputStream img = new FileInputStream(image)) {
			this.sizeImage = img.available();
			byte[] bArray = new byte[sizeImage];
			img.read(bArray);

			//Salvarea BitmapFileHeader-ului din fisierul BMP
			for (int i = 0; i < 14; i++) {
				bitmapFileHeader[i] = bArray[i];
			}
			
			//Salvarea BitmapInfoHeader-ului din fisierul BMP
			for (int i = 0; i < 40; i++) {
				bitmapInfoHeader[i] = bArray[14 + i];
			}
			
			//Calcularea numarului de pixeli ai imaginii
			int numberOfPixels = (bArray.length - 54) / 3;
			
			//Crearea vextorului de pixeli
			pixels = new PixelImage[numberOfPixels];
			
			//Crearea unui pixel parcurgang vectorului de bytes ai imaginii incepand de la byte-ul de unde se termina BitMapHeader-ul
			int currentPixel = 0;
			for (int i = 54; i < bArray.length; i += 3) {
				//Citirea valorilor de rosu, verde si albastru ai fiecarui pixel
				int r = helperSign(bArray[i]);
				int g = helperSign(bArray[i + 1]);
				int b = helperSign(bArray[i + 2]);
				
				//Creearea unui pixel
				PixelImage pixel = new PixelImage(r, g, b);
				
				//Used for debug
				//System.out.println(pixel);
				pixels[currentPixel] = pixel;
				currentPixel++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		//Sfarsitul citirii imaginii
		long endTime = System.nanoTime();
		
		long timeDifference = endTime - startTime;
		System.out.println("Timpul de executiei al etapei de citire al imaginii este: " + timeDifference + " ns");
	}
	
	//Metoda folosita pentru a retuna vectorul de pixeli al unei imaginii
	public PixelImage[] getPixels() {
		return this.pixels;
	}
	
	//Metoda mentine valoarea unui pixel in intervalul permis de valori [0,255]
	public int helperSign(int value) {
		if (value < 0) value += 256;
		return value;
	}

	//Creare unei noii imaginii la o cale data
	public void writeImage(String modifiedImageFilePath, PixelImage[] pixels) {
		//Inceputul scrierii noii imagini
		long startTime = System.nanoTime();
		
		//Crearea unui unui nou fisier 
		File modifiedImage = new File(modifiedImageFilePath);

		//Crearea unui vectot de byte de aceeasi dimensiune ca si iamginea originala
		byte[] bImage = new byte[sizeImage];
		
		//Reconstruirea imaginii folosind pixeli dati prin intermediul vectorului pixels
		//Atasarea BitmapFileHeader-ului
		for (int i = 0; i < bitmapFileHeader.length; i++) {
			bImage[i] = bitmapFileHeader[i];
		}
		
		//Atasarea BitmapInfoHeaderului
		for (int i = 0; i < bitmapInfoHeader.length; i++) {
			bImage[i + 14] = bitmapInfoHeader[i];
		}
		
		//Parcurgerea vectorului pixels si atribuirea vectorului de bytes ce va crea imaginea a valorilor de red, green si blue. Reconstruirea imaginii
		int idx = bitmapInfoHeader.length + bitmapFileHeader.length;
		for (PixelImage p : pixels) {
			bImage[idx] = (byte) p.getRedValue();
			bImage[++idx] = (byte) p.getGreenValue();
			bImage[++idx] = (byte) p.getBlueValue();
			idx++;
		}

		try (FileOutputStream os = new FileOutputStream(modifiedImage)) {
			os.write(bImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Sfarsitul scrierii noii imagini
		long endTime = System.nanoTime();
		
		long timeDifference = endTime - startTime;
		System.out.println("Timpul de executiei al etapei de scriere al noii imagini este: " + timeDifference + " ns");
	}
	
}
