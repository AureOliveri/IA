package main.java.ar.edu.utn.frba.ia.tp;

import org.junit.Assert;
import org.junit.Test;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Bebida;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Clase;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.ColorAula;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Pasatiempo;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.UbicacionAula;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Peculiaridad;


public class CromosomaTest {
	
	@Test
	public void testoptimo() {

		Cromosoma  optimo = new Cromosoma();
		optimo.setGramatica(new ProfesorEspecialidad(Clase.Gramatica, ColorAula.Verde,UbicacionAula.Cuarta, Peculiaridad.Barbon, Bebida.Cafe, Pasatiempo.Literati));
		optimo.setLiteratura(new ProfesorEspecialidad(Clase.Literatura, ColorAula.Blanca, UbicacionAula.Quinta, Peculiaridad.Gafas, Bebida.Jugo, Pasatiempo.Trivias));
		optimo.setEtimologia(new ProfesorEspecialidad(Clase.Etimologia, ColorAula.Azul, UbicacionAula.Segunda, Peculiaridad.Calvo, Bebida.Te, Pasatiempo.Epigrama));
		optimo.setOrtografia(new ProfesorEspecialidad(Clase.Ortografia, ColorAula.Roja, UbicacionAula.Tercera, Peculiaridad.Pipa, Bebida.Leche, Pasatiempo.Crucigrama));
		optimo.setRedaccion(new ProfesorEspecialidad(Clase.Redaccion, ColorAula.Amarilla, UbicacionAula.Primera, Peculiaridad.Decano, Bebida.Agua, Pasatiempo.Palindromos));
		
		
		System.out.println("Cromosoma seleccionado");
		System.out.println("Aptitud: "+optimo.aptitud());
		((Cromosoma)optimo).printCromosoma();
		
	}

}
