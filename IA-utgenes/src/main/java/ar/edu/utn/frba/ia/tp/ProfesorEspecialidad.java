package main.java.ar.edu.utn.frba.ia.tp;

import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Bebida;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.ColorAula;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Pasatiempo;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Peculiaridad;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.UbicacionAula;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Clase;

public class ProfesorEspecialidad {

	private Clase clase;
	private ColorAula colorAula;
	private UbicacionAula ubicacionAula;
	private Peculiaridad peculiaridad;
	private Bebida bebida;
	private Pasatiempo pasatiempo;


	public ProfesorEspecialidad(Clase clase, ColorAula colorAula, UbicacionAula ubicacionAula,
				Peculiaridad peculiaridad, Bebida bebida, Pasatiempo pasatiempo) {
		this.clase = clase;
		this.colorAula = colorAula;
		this.ubicacionAula = ubicacionAula;
		this.peculiaridad = peculiaridad;
		this.bebida = bebida;
		this.pasatiempo = pasatiempo;
	}

	public String printProfesorEspecialidad(){
		StringBuffer buffer=new StringBuffer();
			buffer.append("Clase:"+this.clase.toString().toUpperCase());
			buffer.append(" | ");
			buffer.append("Peculiaridad:"+this.peculiaridad.toString().toUpperCase());
			buffer.append(" | ");
			buffer.append("Bebida:"+this.bebida.toString().toUpperCase());
			buffer.append(" | ");		
			buffer.append("Pasatiempo:"+this.pasatiempo.toString().toUpperCase());
			buffer.append(" | ");
			buffer.append("Ubicacion:"+this.ubicacionAula.toString().toUpperCase());
			buffer.append(" | ");		
			buffer.append("Color:"+this.colorAula.toString().toUpperCase());
			buffer.append("\n");
		return buffer.toString();
	}

	public Clase getClase() { return clase; }

	public void setClase(Clase clase) { this.clase = clase; }

	public ColorAula getColorAula() {
		return colorAula;
	}

	public void setColorAula(ColorAula colorAula) {
		this.colorAula = colorAula;
	}

	public UbicacionAula getUbicacionAula() {
		return ubicacionAula;
	}

	public void setUbicacionAula(UbicacionAula ubicacionAula) {
		this.ubicacionAula = ubicacionAula;
	}

	public Peculiaridad getPeculiaridad() {
		return peculiaridad;
	}

	public void setPeculiaridad(Peculiaridad peculiaridad) {
		this.peculiaridad = peculiaridad;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Pasatiempo getPasatiempo() {
		return pasatiempo;
	}

	public void setPasatiempo(Pasatiempo pasatiempo) {
		this.pasatiempo = pasatiempo;
	}

}
