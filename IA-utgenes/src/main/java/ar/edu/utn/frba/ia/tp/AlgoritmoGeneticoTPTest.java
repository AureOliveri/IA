package main.java.ar.edu.utn.frba.ia.tp;

import main.java.ar.edu.utn.frba.ia.ag.paro.AptitudMinima;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.ag.paro.CantidadDeCiclos;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;

public class AlgoritmoGeneticoTPTest {
	

	@Test
	public void testUnaVuelta() {
		System.out.println(crearPerfecto().aptitud());
	
		
		Configuracion config = new ConfiguracionTP();

		List<Integer> valores = new ArrayList<Integer>();
		
		for(int i=0;i<10;i++) {
			AlgoritmoGenetico ag = new AlgoritmoGeneticoTP(config, Cromosoma.class);
			Individuo resultado = ag.ejecutar();
			Assert.assertNotNull(resultado);
			System.out.println("Cromosoma seleccionado");
			System.out.println("Aptitud: "+resultado.aptitud());
			((Cromosoma)resultado).printCromosoma();
			int valor=0;
				valor+=(((Cromosoma)resultado).getGramatica().getPeculiaridad()==Cromosoma.Peculiaridad.Barbon)?1:0;
				valor+=(((Cromosoma)resultado).getGramatica().getBebida()==Cromosoma.Bebida.Cafe)?1:0;
				valor+=(((Cromosoma)resultado).getGramatica().getPasatiempo()==Cromosoma.Pasatiempo.Literati)?1:0;
				valor+=(((Cromosoma)resultado).getGramatica().getUbicacionAula()==Cromosoma.UbicacionAula.Cuarta)?1:0;
				valor+=(((Cromosoma)resultado).getGramatica().getColorAula()==Cromosoma.ColorAula.Verde)?1:0;
		valores.add(valor);
		}
		valores.forEach(a->System.out.print(a+" | "));
	
		
	}
	
	private Cromosoma crearPerfecto() {
		Cromosoma perfecto = new Cromosoma();
			perfecto.getGramatica().setPeculiaridad(Cromosoma.Peculiaridad.Barbon);
			perfecto.getGramatica().setBebida(Cromosoma.Bebida.Cafe);
			perfecto.getGramatica().setPasatiempo(Cromosoma.Pasatiempo.Literati);
			perfecto.getGramatica().setUbicacionAula(Cromosoma.UbicacionAula.Cuarta);
			perfecto.getGramatica().setColorAula(Cromosoma.ColorAula.Verde);
			
			perfecto.getRedaccion().setPeculiaridad(Cromosoma.Peculiaridad.Decano);
			perfecto.getRedaccion().setBebida(Cromosoma.Bebida.Agua);
			perfecto.getRedaccion().setPasatiempo(Cromosoma.Pasatiempo.Palindromos);
			perfecto.getRedaccion().setUbicacionAula(Cromosoma.UbicacionAula.Primera);
			perfecto.getRedaccion().setColorAula(Cromosoma.ColorAula.Amarilla);
	
			perfecto.getEtimologia().setPeculiaridad(Cromosoma.Peculiaridad.Calvo);
			perfecto.getEtimologia().setBebida(Cromosoma.Bebida.Te);
			perfecto.getEtimologia().setPasatiempo(Cromosoma.Pasatiempo.Epigrama);
			perfecto.getEtimologia().setUbicacionAula(Cromosoma.UbicacionAula.Segunda);
			perfecto.getEtimologia().setColorAula(Cromosoma.ColorAula.Azul);
			
			perfecto.getOrtografia().setPeculiaridad(Cromosoma.Peculiaridad.Pipa);
			perfecto.getOrtografia().setBebida(Cromosoma.Bebida.Leche);
			perfecto.getOrtografia().setPasatiempo(Cromosoma.Pasatiempo.Crucigrama);
			perfecto.getOrtografia().setUbicacionAula(Cromosoma.UbicacionAula.Tercera);
			perfecto.getOrtografia().setColorAula(Cromosoma.ColorAula.Roja);
			
			perfecto.getLiteratura().setPeculiaridad(Cromosoma.Peculiaridad.Gafas);
			perfecto.getLiteratura().setBebida(Cromosoma.Bebida.Jugo);
			perfecto.getLiteratura().setPasatiempo(Cromosoma.Pasatiempo.Trivias);
			perfecto.getLiteratura().setUbicacionAula(Cromosoma.UbicacionAula.Quinta);
			perfecto.getLiteratura().setColorAula(Cromosoma.ColorAula.Blanca);
			
		return perfecto;
	}
	
	
	public void testCruzamiento() {

		
	}
	
	public void testLoggearEstado() {
	}
	
}
