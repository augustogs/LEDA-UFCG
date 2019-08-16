package formas;

public class Quadrado implements FormaGeometrica {
	
	private double lado;

	public Quadrado(double lado) {
		this.lado = lado;
	}

	@Override
	public double getArea() {
		return lado*lado;
	}
	
}
