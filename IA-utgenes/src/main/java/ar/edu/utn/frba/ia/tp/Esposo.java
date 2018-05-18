package main.java.ar.edu.utn.frba.ia.tp;

import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Apellido;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Deporte;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Esposa;

public class Esposo {

	private Apellido apellido;
	private Deporte deporteVaron;
	private Esposa esposa;
	private Deporte deporteMujer;

	public Esposo(Apellido apellido, Deporte deporteVaron, Esposa esposa, Deporte deporteMujer) {
		this.apellido = apellido;
		this.deporteVaron = deporteVaron;
		this.esposa = esposa;
		this.deporteMujer = deporteMujer;
	}

	public String printEsposo(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("{");
		buffer.append("apellido:"+this.apellido);
		buffer.append(",");
		buffer.append("deporteVaron:"+this.deporteVaron);
		buffer.append(",");
		buffer.append("esposa:"+this.esposa);
		buffer.append(",");		
		buffer.append("deporteMujer:"+this.deporteMujer);
		buffer.append("}");		
		return buffer.toString();
	}
	
	public Apellido getApellido() {
		return apellido;
	}

	public void setApellido(Apellido apellido) {
		this.apellido = apellido;
	}

	public Deporte getDeporteVaron() {
		return deporteVaron;
	}

	public void setDeporteVaron(Deporte deporteVaron) {
		this.deporteVaron = deporteVaron;
	}

	public Esposa getEsposa() {
		return esposa;
	}

	public void setEsposa(Esposa esposa) {
		this.esposa = esposa;
	}

	public Deporte getDeporteMujer() {
		return deporteMujer;
	}

	public void setDeporteMujer(Deporte deporteMujer) {
		this.deporteMujer = deporteMujer;
	}

}
