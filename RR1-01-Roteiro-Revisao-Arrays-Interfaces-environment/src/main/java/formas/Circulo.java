package formas;

public class Circulo implements FormaGeometrica {
	
	private double raio;

	public Circulo(double raio) {
		this.raio = raio;
	}

	@Override
	public double getArea() {
		return Math.PI * (raio*raio);
	}	

}
