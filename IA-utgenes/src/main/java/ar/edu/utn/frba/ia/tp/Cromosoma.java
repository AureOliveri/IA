package main.java.ar.edu.utn.frba.ia.tp;

import java.util.Stack;
import java.util.logging.Logger;

import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class Cromosoma extends Individuo {
	

	private ProfesorEspecialidad gramatica;
	private ProfesorEspecialidad literatura;
	private ProfesorEspecialidad etimologia;
	private ProfesorEspecialidad ortografia;
	private ProfesorEspecialidad redaccion;
	
	
	public enum ColorAula{
		Amarilla, Azul, Roja, Blanca, Verde
	}
	
	public enum UbicacionAula{
		Primera, Segunda, Tercera, Cuarta, Quinta
	}
	
	public enum Peculiaridad{
		Decano, Calvo, Pipa, Barbon, Gafas
	}
	
	public enum Bebida{
		Agua, T�, Leche, Caf�, Jugo
	}
	
	public enum Pasatiempo{
		Pal�ndromos, Epigrama, Crucigrama, Literati, Trivias
	}
	
	private void cargarPilaColorAula(Stack<ColorAula> pilaColoresAula){
		while(ColorAula.values().length != pilaColoresAula.size()){
			ColorAula element=ColorAula.values()[(int) (Math.random() * ColorAula.values().length)];
			if(!pilaColoresAula.contains(element)){
				pilaColoresAula.push(element);
			}
		}
	}
	
	private void cargarPilaUbicacionesAula(Stack<UbicacionAula> pilaUbicacionesAula){
		while(UbicacionAula.values().length != pilaUbicacionesAula.size()){
			UbicacionAula element=UbicacionAula.values()[(int) (Math.random() * UbicacionAula.values().length)];
			if(!pilaUbicacionesAula.contains(element)){
				pilaUbicacionesAula.push(element);
			}
		}
	}
	
	private void cargarPilaPeculiaridades(Stack<Peculiaridad> pilaPeculiaridades){
		while(Peculiaridad.values().length != pilaPeculiaridades.size()){
			Peculiaridad element=Peculiaridad.values()[(int) (Math.random() * Peculiaridad.values().length)];
			if(!pilaPeculiaridades.contains(element)){
				pilaPeculiaridades.push(element);
			}
		}
	}	
	
	private void cargarPilaBebidas(Stack<Bebida> pilaBebidas){
		while(Bebida.values().length != pilaBebidas.size()){
			Bebida element=Bebida.values()[(int) (Math.random() * Bebida.values().length)];
			if(!pilaBebidas.contains(element)){
				pilaBebidas.push(element);
			}
		}
	}
	
	private void cargarPilaPasatiempos(Stack<Pasatiempo> pilaPasatiempos){
		while(Pasatiempo.values().length != pilaPasatiempos.size()){
			Pasatiempo element=Pasatiempo.values()[(int) (Math.random() * Pasatiempo.values().length)];
			if(!pilaPasatiempos.contains(element)){
				pilaPasatiempos.push(element);
			}
		}
	}

	
	@Override
	public double aptitud() {
				
		return this.aptitudLiteratura() + this.aptitudOrtografia();
	}
	

	public Cromosoma() {
		
		Stack<ColorAula> pilaColoresAula= new Stack<Cromosoma.ColorAula>();
		Stack<UbicacionAula> pilaUbicacionesAula=new Stack<Cromosoma.UbicacionAula>();
		Stack<Peculiaridad> pilaPeculiaridades=new Stack<Cromosoma.Peculiaridad>();
		Stack<Bebida> pilaBebidas=new Stack<Cromosoma.Bebida>();
		Stack<Pasatiempo> pilaPasatiempos=new Stack<Cromosoma.Pasatiempo>();
		
		this.cargarPilaColorAula(pilaColoresAula);
		this.cargarPilaUbicacionesAula(pilaUbicacionesAula);
		this.cargarPilaPeculiaridades(pilaPeculiaridades);
		this.cargarPilaBebidas(pilaBebidas);
		this.cargarPilaPasatiempos(pilaPasatiempos);
		
		this.setGramatica(new ProfesorEspecialidad(pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setLiteratura(new ProfesorEspecialidad(pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setEtimologia(new ProfesorEspecialidad(pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setOrtografia(new ProfesorEspecialidad(pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setRedaccion(new ProfesorEspecialidad(pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		//this.printCromosoma();
	}
	
	public void printCromosoma(){
		System.out.println("Gramatica: "+this.gramatica.printProfesorEspecialidad());
		System.out.println("Literatura: "+this.literatura.printProfesorEspecialidad());
		System.out.println("Etimolog�a: "+this.etimologia.printProfesorEspecialidad());
		System.out.println("Ortograf�a: "+this.ortografia.printProfesorEspecialidad());
		System.out.println("Redacci�n: "+this.redaccion.printProfesorEspecialidad());
		System.out.println("====================================");
	}
	
	private double aptitudLiteratura(){
		double value=0;
		// El profesor que usa gafas imparte el curso de literatura.
		ProfesorEspecialidad literatura = this.literatura;
		if(literatura.getPeculiaridad().equals(Peculiaridad.Gafas)){
			value+=10;
		} else {
			value-=5;
		}
		value+=this.aptitudGeneral(literatura);
		return value;
	}
	
	private double aptitudOrtografia(){
		double value=0;
		// El profesor aficionado a los crucigramas imparte el curso de ortograf�a.
		ProfesorEspecialidad ortografia = this.ortografia;
		if(ortografia.getPasatiempo().equals(Pasatiempo.Crucigrama)){
			value+=10;
		} else {
			value-=5;
		}
		value+=this.aptitudGeneral(ortografia);
		return value;
	}
	
	private double aptitudGeneral(ProfesorEspecialidad profesorEspecialidad){
		double value=0;
		
		//El profesor que fuma pipa imparte c�tedra en el aula roja.
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Pipa)){
			if(profesorEspecialidad.getColorAula().equals(ColorAula.Roja)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//El profesor que es calvo toma t�
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Calvo)){
			if(profesorEspecialidad.getBebida().equals(Bebida.T�)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//TODO El aula verde est� a la derecha del aula blanca 
		
		//El profesor del aula verde toma caf�
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Verde)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Caf�)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//El profesor del aula amarilla es aficionado a los pal�ndromos.
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Amarilla)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Pal�ndromos)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		// El que imparte clases en el aula del centro toma leche
		if(profesorEspecialidad.getUbicacionAula().equals(UbicacionAula.Tercera)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Leche)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//El decano de la universidad imparte su c�tedra en la primera aula.
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Decano)){
			if(profesorEspecialidad.getUbicacionAula().equals(UbicacionAula.Primera)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//TODO El profesor aficionado a los epigramas imparte su curso junto al profesor de redacci�n.
		
		//TODO El profesor de etimolog�as dicta su clase junto al aula del aficionado a los pal�ndromos.

		// El profesor cuyo pasatiempo son las trivias bebe jugo.
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Trivias)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Jugo)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		// El profesor que es barb�n es aficionado al literati (scrabble).
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Barbon)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Literati)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//TODO El decano imparte su c�tedra junto al aula azul.
		
		//TODO El que es aficionado a los epigramas es vecino de aula del que toma agua
		
			
		
		return value;

	}

	public ProfesorEspecialidad getGramatica() {
		return gramatica;
	}
	public void setGramatica(ProfesorEspecialidad gramatica) {
		this.gramatica = gramatica;
	}
	public ProfesorEspecialidad getLiteratura() {
		return literatura;
	}
	public void setLiteratura(ProfesorEspecialidad literatura) {
		this.literatura = literatura;
	}
	public ProfesorEspecialidad getEtimologia() {
		return etimologia;
	}
	public void setEtimologia(ProfesorEspecialidad etimologia) {
		this.etimologia = etimologia;
	}
	public ProfesorEspecialidad getOrtografia() {
		return ortografia;
	}
	public void setOrtografia(ProfesorEspecialidad ortografia) {
		this.ortografia = ortografia;
	}
	public ProfesorEspecialidad getRedaccion() {
		return redaccion;
	}
	public void setRedaccion(ProfesorEspecialidad redaccion) {
		this.redaccion = redaccion;
	}


	@Override
	public Individuo generarRandom() {
		Individuo nuevoInd;
		
		try {
			nuevoInd = this.getClass().newInstance();
			return nuevoInd;
		} catch (Exception e) {
			Logger.getLogger(
				Logger.GLOBAL_LOGGER_NAME).severe(
					"No se puede crear una instancia de "
					+ this.getClass().getName()
					+ ". Probablemente no tenga un constructor vacio."
					+ " // CAUSA: " + e);
		}
		//return this.clone();
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("\r\n");
		buffer.append("Gramatica: "+this.gramatica.printProfesorEspecialidad()+"\r\n");
		buffer.append("Literatura: "+this.literatura.printProfesorEspecialidad()+"\r\n");
		buffer.append("Etimolog�a: "+this.etimologia.printProfesorEspecialidad()+"\r\n");
		buffer.append("Ortograf�a: "+this.ortografia.printProfesorEspecialidad()+"\r\n");
		buffer.append("Redacci�n: "+this.redaccion.printProfesorEspecialidad()+"\r\n");
		buffer.append("Aptitud: "+(new Double(aptitud())).toString()+"\r\n");
		
		return buffer.toString();
		//return (new Double(aptitud())).toString();
	}

	
}
