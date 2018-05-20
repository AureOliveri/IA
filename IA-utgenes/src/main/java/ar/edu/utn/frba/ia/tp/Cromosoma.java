package main.java.ar.edu.utn.frba.ia.tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

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

        return this.aptitudGeneral(this.literatura) + this.aptitudGeneral(this.ortografia) + this.aptitudGeneral(this.redaccion) + this.aptitudGeneral(this.etimologia) + this.aptitudGeneral(this.gramatica);
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
		
		this.setGramatica(new ProfesorEspecialidad(Clase.Gramatica, pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setLiteratura(new ProfesorEspecialidad(Clase.Literatura, pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setEtimologia(new ProfesorEspecialidad(Clase.Etimologia, pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setOrtografia(new ProfesorEspecialidad(Clase.Ortografia, pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
		this.setRedaccion(new ProfesorEspecialidad(Clase.Redaccion, pilaColoresAula.pop(),pilaUbicacionesAula.pop(),pilaPeculiaridades.pop(),pilaBebidas.pop(),pilaPasatiempos.pop()));
        profesores = new ArrayList<>();
		profesores.add(getRedaccion());
		profesores.add(getEtimologia());
        profesores.add(getGramatica());
        profesores.add(getLiteratura());
        profesores.add(getOrtografia());

        this.printCromosoma();
	}
	
	public void printCromosoma(){
		System.out.println("Gramatica: "+this.gramatica.printProfesorEspecialidad());
		System.out.println("Literatura: "+this.literatura.printProfesorEspecialidad());
		System.out.println("Etimolog�a: "+this.etimologia.printProfesorEspecialidad());
		System.out.println("Ortograf�a: "+this.ortografia.printProfesorEspecialidad());
		System.out.println("Redacci�n: "+this.redaccion.printProfesorEspecialidad());
		System.out.println("====================================");
	}

	private double aptitudGeneral(ProfesorEspecialidad profesorEspecialidad) {
        double value = 0;

        //El profesor que fuma pipa imparte c�tedra en el aula roja.
        if (profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Pipa)) {
            if (profesorEspecialidad.getColorAula().equals(ColorAula.Roja)) {
                value += 10;
            } else {
                value -= 10;
            }
        }

        // El profesor que usa gafas imparte el curso de literatura.
        if (profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Gafas)) {
            if (profesorEspecialidad.getClase().equals(Clase.Literatura)) {
                value += 10;
            } else {
                value -= 5;
            }
        }

		//El profesor que es calvo toma t�
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Calvo)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Te)){
				value+=10;
			} else {
				value-=10;
			}
		}

        //El aula verde esta a la derecha del aula blanca
        if(profesorEspecialidad.getColorAula().equals(ColorAula.Verde)) {
            if (profesorEspecialidad.getUbicacionAula().getNumVal() + 1 == profesores.stream().filter(prof -> prof.getColorAula().equals(ColorAula.Blanca)).collect(toList()).get(0).getUbicacionAula().getNumVal()) {
                value += 10;
            } else {
                value -= 10;
            }
        }

		//El profesor del aula verde toma caf�
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Verde)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Cafe)){
				value+=10;
			} else {
				value-=10;
			}
		}

        // El profesor aficionado a los crucigramas imparte el curso de ortograf�a.
        if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Crucigrama)) {
            if (profesorEspecialidad.getClase().equals(Clase.Ortografia)) {
                value += 10;
            } else {
                value -= 5;
            }
        }
		
		//El profesor del aula amarilla es aficionado a los pal�ndromos.
		if(profesorEspecialidad.getColorAula().equals(ColorAula.Amarilla)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Palindromos)){
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
		
		//El decano de la universidad imparte su catedra en la primera aula.
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Decano)){
			if(profesorEspecialidad.getUbicacionAula().equals(UbicacionAula.Primera)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//El profesor aficionado a los epigramas imparte su curso junto al profesor de redacci�n.
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Epigrama)) {
		    if (esVecinoDe(profesorEspecialidad, getRedaccion())) {
		        value+=10;
		    } else {
		        value-=10;
		    }
		}


		//El profesor de etimolog�as dicta su clase junto al aula del aficionado a los pal�ndromos.
        if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Palindromos)) {
            if(esVecinoDe(profesorEspecialidad, getEtimologia())) {
		        value+=10;
            } else {
                value-=10;
            }
        }

        // El profesor cuyo pasatiempo son las trivias bebe jugo.
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Trivias)){
			if(profesorEspecialidad.getBebida().equals(Bebida.Jugo)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		// El profesor que es barbon es aficionado al literati (scrabble).
		if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Barbon)){
			if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Literati)){
				value+=10;
			} else {
				value-=10;
			}
		}
		
		//El decano imparte su catedra junto al aula azul.
        if(profesorEspecialidad.getPeculiaridad().equals(Peculiaridad.Decano)) {
		    if(esVecinoDe(profesorEspecialidad, profesores.stream().filter(prof -> prof.getColorAula().equals(ColorAula.Azul)).collect(toList()).get(0))) {
		        value+=10;
            } else {
		        value-=10;
            }
        }
		
		//El que es aficionado a los epigramas es vecino de aula del que toma agua
		if(profesorEspecialidad.getPasatiempo().equals(Pasatiempo.Epigrama)) {
            if (esVecinoDe(profesorEspecialidad, profesores.stream().filter(prof -> prof.getBebida().equals(Bebida.Agua)).collect(toList()).get(0))) {
                value += 10;
            } else {
                value -= 10;
            }
        }
					
		
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

    public List<ProfesorEspecialidad> getProfesores() {
	    return profesores;
    }

	public void setProfesores(List<ProfesorEspecialidad> profesores) {
	    this.profesores = profesores;
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
		return this.clone();
	}
	
	@Override
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("\r\n");
		buffer.append("Gramatica: "+this.gramatica.printProfesorEspecialidad()+"\r\n");
		buffer.append("Literatura: "+this.literatura.printProfesorEspecialidad()+"\r\n");
		buffer.append("Etimologia: "+this.etimologia.printProfesorEspecialidad()+"\r\n");
		buffer.append("Ortografia: "+this.ortografia.printProfesorEspecialidad()+"\r\n");
		buffer.append("Redaccion: "+this.redaccion.printProfesorEspecialidad()+"\r\n");
		buffer.append("Aptitud: "+(new Double(aptitud())).toString()+"\r\n");
		
		return buffer.toString();
		//return (new Double(aptitud())).toString();
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

	
}
