package main.java.ar.edu.utn.frba.ia.tp;

import java.util.Stack;
import java.util.logging.Logger;

import main.java.ar.edu.utn.frba.ia.ag.Individuo;

public class Cromosoma extends Individuo {
	

	private Esposo daniel;
	private Esposo enrique;
	private Esposo martin;
	private Esposo pablo;

	public enum Apellido{
		Ortega, Lopez, Varela, Garcia
	}
	
	public enum Deporte{
		basquetbol, rugby, handbol, voleibol, natación, equitación, fútbol, tenis 
	}
	
	public enum Esposa{
		Alicia, Natalia, Carolina, Florencia
	}
	
	private void cargarPilaDeportes(Stack<Deporte> pilaDeportes){
		while(Deporte.values().length != pilaDeportes.size()){
			Deporte element=Deporte.values()[(int) (Math.random() * Deporte.values().length)];
			if(!pilaDeportes.contains(element)){
				pilaDeportes.push(element);
			}
		}
	}
	
	private void cargarPilaApellidos(Stack<Apellido> pilaApellidos){
		while(Apellido.values().length != pilaApellidos.size()){
			Apellido element=Apellido.values()[(int) (Math.random() * Apellido.values().length)];
			if(!pilaApellidos.contains(element)){
				pilaApellidos.push(element);
			}
		}
	}
	
	private void cargarPilaEsposas(Stack<Esposa>pilaEsposas){
		while(Esposa.values().length != pilaEsposas.size()){
			Esposa element=Esposa.values()[(int) (Math.random() * Esposa.values().length)];
			if(!pilaEsposas.contains(element)){
				pilaEsposas.push(element);
			}
		}
	}	

	
	@Override
	public double aptitud() {
				
		return this.aptitudDaniel() + this.aptitudEnrique() + this.aptitudPablo() + this.aptitudMartin();
	}
	

	public Cromosoma() {
		
		Stack<Deporte> pilaDeportes=new Stack<Cromosoma.Deporte>();
		Stack<Apellido> pilaApellidos=new Stack<Cromosoma.Apellido>();
		Stack<Esposa> pilaEsposas=new Stack<Cromosoma.Esposa>();		
		
		this.cargarPilaEsposas(pilaEsposas);
		this.cargarPilaApellidos(pilaApellidos);
		this.cargarPilaDeportes(pilaDeportes);
		
		this.setDaniel(new Esposo(pilaApellidos.pop(),pilaDeportes.pop(),pilaEsposas.pop(),pilaDeportes.pop()));
		this.setEnrique(new Esposo(pilaApellidos.pop(),pilaDeportes.pop(),pilaEsposas.pop(),pilaDeportes.pop()));
		this.setMartin(new Esposo(pilaApellidos.pop(),pilaDeportes.pop(),pilaEsposas.pop(),pilaDeportes.pop()));
		this.setPablo(new Esposo(pilaApellidos.pop(),pilaDeportes.pop(),pilaEsposas.pop(),pilaDeportes.pop()));
		
		//this.printCromosoma();
	}
	
	public void printCromosoma(){
		System.out.println("Daniel: "+this.daniel.printEsposo());
		System.out.println("Enrique: "+this.enrique.printEsposo());
		System.out.println("Martin: "+this.martin.printEsposo());
		System.out.println("Pablo: "+this.pablo.printEsposo());
		System.out.println("====================================");
	}
	
	
	private double aptitudDaniel(){
		double value=0;
		Esposo esposo = this.daniel;
		//Daniel no se apellida Ortega.
		if(!esposo.getApellido().equals(Apellido.Ortega)){
			value+=1;
		}else{
			value-=5;
		}				
		//La joven que practica natación no conoce a Daniel.
		if(!esposo.getDeporteMujer().equals(Deporte.natación)){
			value+=1;
		}else{
			value-=5;
		}
		value+=this.aptitudGeneral(esposo);
		return value;
	}
	
	private double aptitudEnrique(){
		double value=0;
		//Enrique se apellida López. 
		Esposo esposo = this.enrique;
		if(!esposo.getApellido().equals(Apellido.Lopez)){
			value+=1;
		}else{
			value-=5;
		}
		value+=this.aptitudGeneral(esposo);
		return value;
	}	
	
	private double aptitudPablo(){
		double value=0;
		//El nombre de García es Pablo.
		if(this.pablo.getApellido().equals(Apellido.Garcia)){
			value+=1;
		}else{
			value-=5;
		}		
		value+=this.aptitudGeneral(this.pablo);		
		return value;
	}	
	
	private double aptitudMartin(){
		double value=0;
		//Martín, que no practica basquetbol, no conoce a Alicia.
		if(!this.martin.getDeporteVaron().equals(Deporte.basquetbol)){
			value+=1;
		}else{
			value-=5;
		}
		//Martin no conoce a Alicia
		if(!this.martin.getEsposa().equals(Esposa.Alicia)){
			value+=1;
		}else{
			value-=5;
		}		
		//Florencia no conoce a Martín  
		if(!this.martin.getEsposa().equals(Esposa.Florencia)){
			value+=1;
		}else{
			value-=5;
		}			
		value+=this.aptitudGeneral(this.martin);
		return value;
	}
	
	
	private double aptitudGeneral(Esposo esposo){
		double value=0;
		//El marido de Natalia integra un equipo de rugby.
		if(esposo.getEsposa().equals(Esposa.Natalia))
			if (esposo.getDeporteVaron().equals(Deporte.rugby))
				value++;
			else
				value-=3;
				
		//El que juega al handbol se apellida Varela.
		if(esposo.getDeporteVaron().equals(Deporte.handbol))
			if(esposo.getApellido().equals(Apellido.Varela))
				value++;
			else
				value-=3;
				
		//La joven que juega al voleibol no es la pareja de López. 
		if(esposo.getDeporteMujer().equals(Deporte.voleibol))
			if(!esposo.getApellido().equals(Apellido.Lopez))
				value++;
			else
				value-=3;
						
		//El esposo de Carolina no practica basquetbol ni handbol.
		if(esposo.getEsposa().equals(Esposa.Carolina))
			
			if(!esposo.getDeporteVaron().equals(Deporte.basquetbol) && 
			   !esposo.getDeporteVaron().equals(Deporte.handbol))
				value++;
			else
				value-=3;
					
		//La que practica equitación no se llama Florencia y no conoce a Varela. 
		if(esposo.getDeporteMujer().equals(Deporte.equitación))
			if(!esposo.getEsposa().equals(Esposa.Florencia) &&
			   !esposo.getApellido().equals(Apellido.Varela))
				value++;
			else
				value-=3;
		
		//El que integra un equipo de fútbol está en pareja con la joven que juega al tenis.
		if(esposo.getDeporteVaron().equals(Deporte.fútbol))
			if(esposo.getDeporteMujer().equals(Deporte.tenis))
				value++;
			else
				value-=3;
					
		//Florencia no conoce a Varela. 
		if(esposo.getEsposa().equals(Esposa.Florencia))
			if(!esposo.getApellido().equals(Apellido.Varela))
				value++;
			else
				value-=3;
		
		
		//Ni López ni Ortega conocen a Natalia.
		if(esposo.getEsposa().equals(Esposa.Natalia))
			if(!esposo.getApellido().equals(Apellido.Lopez) &&
			   !esposo.getApellido().equals(Apellido.Ortega))
				value++;
			else
				value-=3;
		
		
		return value;

	}



	public Esposo getDaniel() {
		return daniel;
	}


	public void setDaniel(Esposo daniel) {
		this.daniel = daniel;
	}


	public Esposo getEnrique() {
		return enrique;
	}


	public void setEnrique(Esposo enrique) {
		this.enrique = enrique;
	}


	public Esposo getMartin() {
		return martin;
	}


	public void setMartin(Esposo martin) {
		this.martin = martin;
	}


	public Esposo getPablo() {
		return pablo;
	}


	public void setPablo(Esposo pablo) {
		this.pablo = pablo;
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
		buffer.append("Daniel: "+this.daniel.printEsposo()+"\r\n");
		buffer.append("Enrique: "+this.enrique.printEsposo()+"\r\n");
		buffer.append("Martin: "+this.martin.printEsposo()+"\r\n");
		buffer.append("Pablo: "+this.pablo.printEsposo()+"\r\n");
		buffer.append("Aptitud: "+(new Double(aptitud())).toString()+"\r\n");
		
		return buffer.toString();
		//return (new Double(aptitud())).toString();
	}

	/*
	public Stack<Deporte> getPilaDeportes() {
		return pilaDeportes;
	}

	public void setPilaDeportes(Stack<Deporte> pilaDeportes) {
		this.pilaDeportes = pilaDeportes;
	}

	public Stack<Apellido> getPilaApellidos() {
		return pilaApellidos;
	}

	public void setPilaApellidos(Stack<Apellido> pilaApellidos) {
		this.pilaApellidos = pilaApellidos;
	}

	public Stack<Esposa> getPilaEsposas() {
		return pilaEsposas;
	}

	public void setPilaEsposas(Stack<Esposa> pilaEsposas) {
		this.pilaEsposas = pilaEsposas;
	}
	*/
	
	
}
