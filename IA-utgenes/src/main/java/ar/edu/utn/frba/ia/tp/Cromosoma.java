package main.java.ar.edu.utn.frba.ia.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.print.PrintColor;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;

import static java.util.stream.Collectors.toList;


public class Cromosoma extends Individuo {
	
    private ProfesorEspecialidad literatura;
    private ProfesorEspecialidad gramatica;
    private ProfesorEspecialidad etimologia;
    private ProfesorEspecialidad ortografia;
    private ProfesorEspecialidad redaccion;

    private List<ProfesorEspecialidad> profesores;
	
	public enum Clase {
	    Gramatica, Literatura, Etimologia, Ortografia, Redaccion
    }

	public enum ColorAula{
		Amarilla, Azul, Roja, Blanca, Verde
	}
	
	public enum UbicacionAula{
		Primera(1), Segunda(2), Tercera(3), Cuarta(4), Quinta(5);

		private int ubVal;

        UbicacionAula(int numVal) {
            this.ubVal= numVal;
        }

        public int getNumVal() {
            return ubVal;
        }
	}
	
	public enum Peculiaridad{
		Decano, Calvo, Pipa, Barbon, Gafas
	}
	
	public enum Bebida{
		Agua, Te, Leche, Cafe, Jugo
	}
	
	public enum Pasatiempo{
		Palindromos, Epigrama, Crucigrama, Literati, Trivias
	}
	


    
    public Cromosoma() {

    	cargarCromosomaSinRepetidos();				// Carga ints en las pilas, NO acepta repetidos
//    	cargarCromosomaConRepetidos();			// No usa pilas, asigna valores random directamente, PUEDEN haber repetidos
		
        profesores = new ArrayList<>();
        profesores.add(getRedaccion());
        profesores.add(getEtimologia());
        profesores.add(getGramatica());
        profesores.add(getLiteratura());
        profesores.add(getOrtografia());
      //  this.printCromosoma();
	}
    
    //*************************************************************************************//
    //------------------------------------CALCULO DE APTITUD-------------------------------//
    //*************************************************************************************//
    @Override
    public double aptitud() {
    	int VALOR_PENALIZACION_REPETIDOS = 3;

        double aptitudFinal= this.aptitudGeneral(this.literatura)
        		+ this.aptitudGeneral(this.ortografia)
        		+ this.aptitudGeneral(this.redaccion)
        		+ this.aptitudGeneral(this.etimologia)
        		+ this.aptitudGeneral(this.gramatica)
        		- cantidadRepetidos()*VALOR_PENALIZACION_REPETIDOS;
        
        return aptitudFinal;
    }  
	
    
    private int cantidadRepetidos() {
	    int cantidad_repetidas = repetidosPeculiaridades() 
	    							+ repetidosColorAula() 
	    							+ repetidosUbicacionAula() 
	    							+ repetidosPasatiempos() 
	    							+ repetidosBebidas(); 
	    return cantidad_repetidas;
    }
    
    public double aptitudGeneral(ProfesorEspecialidad profesorEspecialidad) {
		final int POSITIVO1=1;
		final int POSITIVO2=1;
		
		final int NEGATIVO1=0;
		final int NEGATIVO2=0;

        double value = 0;
        								//1) El profesor que fuma pipa imparte c�tedra en el aula roja.
        if (profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Pipa)) {
            if (profesorEspecialidad.getColorAula().equals(ColorAula.Roja)) 
            	value+=POSITIVO1;
            else value+=NEGATIVO1;
        }

        								//2) El profesor que usa gafas imparte el curso de literatura.
        if (profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Gafas)) {
            if (profesorEspecialidad.getClase().equals(Clase.Literatura)) 
            	value+=POSITIVO1;
            else value+=NEGATIVO1;
        }

										//3) El profesor que es calvo toma t�
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Calvo)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Te)) 
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}

        								//4) El aula verde esta a la izquierda del aula blanca
        if(profesorEspecialidad.getColorAula().equals(ColorAula.Verde)) {
            List<ProfesorEspecialidad> profe = profesores.stream()
            									.filter(prof -> 
            										(prof.getColorAula().equals(ColorAula.Blanca))
            										&& (prof.getUbicacionAula().getNumVal() == profesorEspecialidad.getUbicacionAula().getNumVal() + 1))
            									.collect(toList());
            if (profe.size() > 0) 
            	value+=POSITIVO2;
            else value+=NEGATIVO2;

        }

										//5) El profesor del aula verde toma caf�
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Verde)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Cafe)) 
				value+=POSITIVO1;
			else value+=NEGATIVO2;
		}

        								//6) El profesor aficionado a los crucigramas imparte el curso de ortograf�a.
        if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Crucigrama)) {
            if (profesorEspecialidad.getClase().equals(Clase.Ortografia)) 
            	value+=POSITIVO1;
            else value+=NEGATIVO1;
        }
		
										//7) El profesor del aula amarilla es aficionado a los pal�ndromos.
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Amarilla)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Palindromos)) 
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}
		
										//8) El que imparte clases en el aula del centro toma leche
		if(profesorEspecialidad.getUbicacionAula().equals(UbicacionAula.Tercera)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Leche)) 
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}
		
										//9) El decano de la universidad imparte su catedra en la primera aula.
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Decano)){
			if(profesorEspecialidad.getUbicacionAula().equals(UbicacionAula.Primera)) 
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}
		
										//10) El profesor aficionado a los epigramas imparte su curso junto al profesor de redacci�n.
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Epigrama)) {
		    if (esVecinoDe(profesorEspecialidad, getRedaccion())) 
		    	value+=POSITIVO2;
		    else value+=NEGATIVO2;
		}


										//11) El profesor de etimolog�as dicta su clase junto al aula del aficionado a los pal�ndromos.
        if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Palindromos)) {
            if(esVecinoDe(profesorEspecialidad, getEtimologia())) 
            	value+=POSITIVO2;
            else value+=NEGATIVO2;
        }

        								//12) El profesor cuyo pasatiempo son las trivias bebe jugo.
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Trivias)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Jugo))
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}
		
										//13) El profesor que es barbon es aficionado al literati (scrabble).
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Barbon)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Literati)) 
				value+=POSITIVO1;
			else value+=NEGATIVO1;
		}
		
											//14) El decano imparte su catedra junto al aula azul.
        if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Decano)) {
            List<ProfesorEspecialidad> profes = profesores.stream()
            									.filter(prof -> prof.getColorAula().equals(ColorAula.Azul) 
            											&& esVecinoDe(profesorEspecialidad,prof))
            									.collect(toList());
		    if(profes.size() > 0) 
		    	value+=POSITIVO2;
            else value+=NEGATIVO2;
        }
		
											//15) El que es aficionado a los epigramas es vecino de aula del que toma agua
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Epigrama)) {
            List<ProfesorEspecialidad> profes = profesores.stream()
            									.filter(prof -> prof.getBebida().equals(Bebida.Agua) 
            											&& esVecinoDe(profesorEspecialidad,prof))
            									.collect(toList());
            if (profes.size() > 0)
            	value+=POSITIVO2;
            else value+=NEGATIVO2;
        }
					
		
		return value;

	}
    

	private Boolean esVecinoDe(ProfesorEspecialidad p1, ProfesorEspecialidad p2) {
	    int ubiP1 = p1.getUbicacionAula().getNumVal();
	    int ubiP2 = p2.getUbicacionAula().getNumVal();
        switch (ubiP1) {
            case 1:
                return ubiP1 + 1 == ubiP2;
            case 5:
                return ubiP1 - 1 == ubiP2;
            default:
                return (ubiP1 + 1 == ubiP2 || ubiP1 - 1 == ubiP2);
        }


    }
    
    //*************************************************************************************************//
    //------------------------------------METODOS DE CARGA DE CROMOSOMAS-------------------------------//
    //*************************************************************************************************//
    
    //								METODO 1
    private void cargarCromosomaConRepetidos() {							// Carga con valores validos random (pueden haber repetidos).
  		this.setGramatica(cargarCromoRandom(Clase.Gramatica));
  		this.setLiteratura(cargarCromoRandom(Clase.Literatura));
  		this.setEtimologia(cargarCromoRandom(Clase.Etimologia));
  		this.setOrtografia(cargarCromoRandom(Clase.Ortografia));
  		this.setRedaccion(cargarCromoRandom(Clase.Redaccion));
      }

	      private ProfesorEspecialidad cargarCromoRandom(Clase clase) {
	      	return (new ProfesorEspecialidad(clase,
	      						ColorAula.values()[(int)(Math.random()*10)%(ColorAula.values().length)],
	      						UbicacionAula.values()[(int)(Math.random()*10)%(UbicacionAula.values().length)],
	      						Peculiaridad.values()[(int)(Math.random()*10)%(Peculiaridad.values().length)],
	      						Bebida.values()[(int)(Math.random()*10)%(Bebida.values().length)],
	      						Pasatiempo.values()[(int)(Math.random()*10)%(Pasatiempo.values().length)]));
	      }		  
	
	      
	 //								METODO 2 	      
	   private void cargarCromosomaSinRepetidos() {						// Carga con valores validos SIN repetir
			Stack<Integer> pilaColoresAula= new Stack<Integer>();
			Stack<Integer> pilaUbicacionesAula=new Stack<Integer>();
			Stack<Integer> pilaPeculiaridades=new Stack<Integer>();
			Stack<Integer> pilaBebidas=new Stack<Integer>();
			Stack<Integer> pilaPasatiempos=new Stack<Integer>();

			this.cargarPila(pilaColoresAula);
			this.cargarPila(pilaUbicacionesAula);
			this.cargarPila(pilaPeculiaridades);
			this.cargarPila(pilaBebidas);
			this.cargarPila(pilaPasatiempos);
			
			this.setGramatica(new ProfesorEspecialidad(Clase.Gramatica, ColorAula.values()[pilaColoresAula.pop()],UbicacionAula.values()[pilaUbicacionesAula.pop()],Peculiaridad.values()[pilaPeculiaridades.pop()],Bebida.values()[pilaBebidas.pop()],Pasatiempo.values()[pilaPasatiempos.pop()]));
			this.setLiteratura(new ProfesorEspecialidad(Clase.Literatura, ColorAula.values()[pilaColoresAula.pop()],UbicacionAula.values()[pilaUbicacionesAula.pop()],Peculiaridad.values()[pilaPeculiaridades.pop()],Bebida.values()[pilaBebidas.pop()],Pasatiempo.values()[pilaPasatiempos.pop()]));
			this.setEtimologia(new ProfesorEspecialidad(Clase.Etimologia, ColorAula.values()[pilaColoresAula.pop()],UbicacionAula.values()[pilaUbicacionesAula.pop()],Peculiaridad.values()[pilaPeculiaridades.pop()],Bebida.values()[pilaBebidas.pop()],Pasatiempo.values()[pilaPasatiempos.pop()]));
			this.setOrtografia(new ProfesorEspecialidad(Clase.Ortografia, ColorAula.values()[pilaColoresAula.pop()],UbicacionAula.values()[pilaUbicacionesAula.pop()],Peculiaridad.values()[pilaPeculiaridades.pop()],Bebida.values()[pilaBebidas.pop()],Pasatiempo.values()[pilaPasatiempos.pop()]));
			this.setRedaccion(new ProfesorEspecialidad(Clase.Redaccion, ColorAula.values()[pilaColoresAula.pop()],UbicacionAula.values()[pilaUbicacionesAula.pop()],Peculiaridad.values()[pilaPeculiaridades.pop()],Bebida.values()[pilaBebidas.pop()],Pasatiempo.values()[pilaPasatiempos.pop()]));
	  }
	   
	   
	   private void cargarPila(Stack<Integer> pila) {
	   	while(pila.size()!=Clase.values().length) {
	   		int valor=(int)(Math.random()*10)%(Clase.values().length);
	   		if(!pila.contains(valor)) pila.push(valor);
	   	}
	   	
	   }


	//*******************************************************************************************//
    //------------------------------------METODOS DE MUTACION------------------------------------//
    //*******************************************************************************************//
	
	@Override
	public void mutar() { 
		ProfesorEspecialidad especialidad = null;
		int profesor = (int) (Math.random()*10/Clase.values().length);
		
		switch(profesor) {
		case 0: especialidad = this.getLiteratura();break;
		case 1: especialidad = this.getGramatica();break;
		case 2: especialidad = this.getEtimologia();break;
		case 3: especialidad = this.getOrtografia();break;
		case 4: especialidad = this.getRedaccion();break;
		}
		
		int atributo = (int) (Math.random()*10/Clase.values().length);
//		mutarTodosAtributos(especialidad);									// Muta TODOS los atributos de alguna especialidad
		mutarUnAtributo(especialidad, atributo);							// Muta un solo atributo de alguna especialidad
//		mutarTodosGenesPrincipales();										// Muta un solo atributo de cada especialidad
	}
	
	
	private void mutarTodosAtributos(ProfesorEspecialidad profesor) {
		for(int i=0;i<5;i++) mutarUnAtributo(profesor,i);
	}
	
	private void mutarTodosGenesPrincipales() {
		int atributo = (int) (Math.random()*10/Clase.values().length);
		mutarUnAtributo(this.getEtimologia(), atributo);

		atributo = (int) (Math.random()*10/Clase.values().length);
		mutarUnAtributo(this.getGramatica(), atributo);

		atributo = (int) (Math.random()*10/Clase.values().length);
		mutarUnAtributo(this.getLiteratura(), atributo);

		atributo = (int) (Math.random()*10/Clase.values().length);
		mutarUnAtributo(this.getOrtografia(), atributo);

		atributo = (int) (Math.random()*10/Clase.values().length);
		mutarUnAtributo(this.getRedaccion(), atributo);
		
		
	}
	
	private void mutarUnAtributo(ProfesorEspecialidad profesor, int atributo) {
		int valorNuevo = (int) (Math.random()*10/Clase.values().length);
		
		switch(atributo){
			case 0: profesor.setBebida(Bebida.values()[valorNuevo]);break;
			case 1: profesor.setColorAula(ColorAula.values()[valorNuevo]);break;
			case 2: profesor.setPasatiempo(Pasatiempo.values()[valorNuevo]);break;
			case 3: profesor.setPeculiaridad(Peculiaridad.values()[valorNuevo]);break;
			case 4: profesor.setUbicacionAula(UbicacionAula.values()[valorNuevo]);break;
		}
		
		Logger.getGlobal().log(Level.INFO, Double.toString(this.aptitud()));
		
	}
	
	
	
	//*******************************************************************************************//
    //------------------------------------CALCULO DE REPETIDOS-----------------------------------//
    //*******************************************************************************************//

    private int repetidosPeculiaridades() {

	    return cantidadConPeculiaridad(Peculiaridad.Decano)
	    		+ cantidadConPeculiaridad(Peculiaridad.Barbon)
	    		+ cantidadConPeculiaridad(Peculiaridad.Gafas)
	    		+ cantidadConPeculiaridad(Peculiaridad.Pipa)
	    		+ cantidadConPeculiaridad(Peculiaridad.Calvo);

    }

    private int cantidadConPeculiaridad(Peculiaridad peculiar) {
        int repetidos = 0;

        int peculiares = profesores.stream()
        				.filter(p -> p.getPeculiaridad().equals(peculiar)).collect(toList()).size();
        if (peculiares > 1) {repetidos+=peculiares-1;}

        return repetidos;
    }


    private int repetidosColorAula() {

	    return cantidadConColorAula(ColorAula.Blanca) 
	    		+ cantidadConColorAula(ColorAula.Verde)
	    		+ cantidadConColorAula(ColorAula.Azul)
	    		+ cantidadConColorAula(ColorAula.Amarilla)
	    		+ cantidadConColorAula(ColorAula.Roja);
    }

    private int cantidadConColorAula(ColorAula color) {
        int repetidos = 0;

        int coloridos = profesores.stream().filter(p -> p.getColorAula().equals(color)).collect(toList()).size();
        if (coloridos > 1) {repetidos+=coloridos-1;}

        return repetidos;
    }

    private int repetidosUbicacionAula() {

	    return cantidadConUbicacionAula(UbicacionAula.Primera)
	    		+ cantidadConUbicacionAula(UbicacionAula.Segunda)
	    		+ cantidadConUbicacionAula(UbicacionAula.Tercera)
	    		+ cantidadConUbicacionAula(UbicacionAula.Cuarta)
	    		+ cantidadConUbicacionAula(UbicacionAula.Quinta);
    }

    private int cantidadConUbicacionAula(UbicacionAula ubicacion) {
        int repetidos = 0;

        int ubicacionados = profesores.stream().filter(p -> p.getUbicacionAula().equals(ubicacion)).collect(toList()).size();
        if (ubicacionados > 1) {repetidos+=ubicacionados-1;}

        return repetidos;
    }

    private int repetidosPasatiempos() {

	    return cantidadConPasatiempos(Pasatiempo.Epigrama)
	    		+ cantidadConPasatiempos(Pasatiempo.Crucigrama)
	    		+ cantidadConPasatiempos(Pasatiempo.Trivias)
	    		+ cantidadConPasatiempos(Pasatiempo.Literati)
	    		+ cantidadConPasatiempos(Pasatiempo.Palindromos);
    }

    private int cantidadConPasatiempos(Pasatiempo pasatiempo) {
        int repetidos = 0;

        int pasatiempos = profesores.stream().filter(p -> p.getPasatiempo().equals(pasatiempo)).collect(toList()).size();
        if (pasatiempos > 1) {repetidos+=pasatiempos-1;}

        return repetidos;
    }

    private int repetidosBebidas() {

        return cantidadConBebida(Bebida.Agua) 
        		+ cantidadConBebida(Bebida.Cafe)
        		+ cantidadConBebida(Bebida.Te)
        		+ cantidadConBebida(Bebida.Leche)
        		+ cantidadConBebida(Bebida.Jugo);
    }

    private int cantidadConBebida(Bebida bebida) {
        int repetidos = 0;

        int bebidas = profesores.stream().filter(p -> p.getBebida().equals(bebida)).collect(toList()).size();
        if (bebidas > 1) {repetidos+=bebidas-1;}

        return repetidos;
    }
    
	//**************************************************************************************//
    //------------------------------------GETTERS/SETTERS-----------------------------------//
    //**************************************************************************************//
	public String stringCromosoma() {
		String cromo=this.gramatica.printProfesorEspecialidad();
			cromo+=(this.literatura.printProfesorEspecialidad());
			cromo+=(this.etimologia.printProfesorEspecialidad());
			cromo+=(this.ortografia.printProfesorEspecialidad());
			cromo+=(this.redaccion.printProfesorEspecialidad());
		return cromo;
	}
    
    public void printCromosoma(){
    	System.out.println(this.gramatica.printProfesorEspecialidad());
		System.out.println(this.literatura.printProfesorEspecialidad());
		System.out.println(this.etimologia.printProfesorEspecialidad());
		System.out.println(this.ortografia.printProfesorEspecialidad());
		System.out.println(this.redaccion.printProfesorEspecialidad());
		System.out.println(this.aptitud()+" ----- Rep: "+cantidadRepetidos());
		System.out.println("====================================");
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

	 public List<ProfesorEspecialidad> getProfesores() {
		    return profesores;
	 }
	
		public void setProfesores(List<ProfesorEspecialidad> profesores) {
		    this.profesores = profesores;
	 }
		
		@Override
		public String toString() {
			return (new Double(aptitud())).toString();
		}
		



	
}
