package main.java.ar.edu.utn.frba.ia.tp;

import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Bebida;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.ColorAula;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Pasatiempo;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Peculiaridad;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.UbicacionAula;

public class ProfesorEspecialidad {
	
	private ColorAula colorAula;
	private UbicacionAula ubicacionAula;
	private Peculiaridad peculiaridad;
	private Bebida bebida;
	private Pasatiempo pasatiempo;


	public ProfesorEspecialidad(ColorAula colorAula, UbicacionAula ubicacionAula, 
				Peculiaridad peculiaridad, Bebida bebida, Pasatiempo pasatiempo) {
		this.colorAula = colorAula;
		this.ubicacionAula = ubicacionAula;
		this.peculiaridad = peculiaridad;
		this.bebida = bebida;
		this.pasatiempo = pasatiempo;
	}

	public String printProfesorEspecialidad(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		buffer.append("pasatiempo:"+this.pasatiempo);
		buffer.append(",");
		buffer.append("colorAula:"+this.colorAula);
		buffer.append(",");
		buffer.append("ubicacionAula:"+this.ubicacionAula);
		buffer.append(",");		
		buffer.append("peculiaridad:"+this.peculiaridad);
		buffer.append(",");		
		buffer.append("bebida:"+this.bebida);
		buffer.append("}");		
		return buffer.toString();
	}

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
