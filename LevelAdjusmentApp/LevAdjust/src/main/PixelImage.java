package main;

public class PixelImage {
	
	//Valorea de rosu a unui pixel
	private int redValue;
	
	//Valoarea de verde a unui pixel
	private int greenValue;
	
	//Valoarea de albastru a unui pixel
	private int blueValue;
	
	//Numele unui pixel; default este stringul null
	private String name = "";
	
	//Constructor fara parametrii
	public PixelImage() {
	}
	
	//Constructor cu parametrii; folosit pentru a putea atribui valorile date unui pixel
	public PixelImage(int r, int g, int b) {
		this.redValue = r;
		this.greenValue = g;
		this.blueValue = b;
	}
	
	//Constructor cu parametrii; folosit pentru a putea atribui valorile date unui pixel
	public PixelImage(String pixelName, int r, int g, int b) {
			this.name = pixelName;
			this.redValue = r;
			this.greenValue = g;
			this.blueValue = b;
		}
	
	//Getter pentru redValue; folosit pentru a putea avea acces la valoarea de rosu a unui pixel
	public int getRedValue() {
		return redValue;
	}
	
	//Getter pentru greenValue; folosit pentru a putea avea acces la valoarea de verde a unui pixel
	public int getGreenValue() {
		return greenValue;
	}
	
	//Getter pentru blueValue; folosit pentru a putea avea acces la valoarea de albastru a unui pixel
	public int getBlueValue() {
		return blueValue;
	}
	
	//Getter pentru nume
	public String getName() {
		return name;
	}
	
	//Sette folosit pentru a verifica daca valorile de rosu, verde si albastru ale pixelilor sunt in intervalul admis
	public void setPixel(int red, int green, int blue) {
		if (red < 0)
			this.redValue = 0;
		else if (red > 255)
			this.redValue = 255;
			else redValue = red;
		
		if (green < 0)
			this.greenValue = 0;
		else if (green > 255)
			this.greenValue = 255;
			else greenValue = green;
		
		if (blue < 0)
			this.blueValue = 0;
		else if (blue > 255)
			this.blueValue = 255;
			else blueValue = blue;
	}
	
	//Suprascrierea metodei toString pentru a afisa un pixelii dupa creare
	//Folosita la debug
	@Override
	public String toString() {
		return "[" + redValue + ", " + greenValue + ", " + blueValue + "] ";
	}

}
