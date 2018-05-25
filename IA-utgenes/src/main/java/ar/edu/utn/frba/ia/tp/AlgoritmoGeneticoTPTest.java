package main.java.ar.edu.utn.frba.ia.tp;

import main.java.ar.edu.utn.frba.ia.ag.paro.AptitudMinima;
import main.java.ar.edu.utn.frba.ia.ag.paro.AptitudMinimaPromedio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.Assert;
import org.junit.Test;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Simple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionAdaptativaPorConvergencia;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionTemperaturaAscendente;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionTemperaturaDescendente;
import main.java.ar.edu.utn.frba.ia.ag.paro.CantidadDeCiclos;
import main.java.ar.edu.utn.frba.ia.ag.paro.CriterioDeParo;
import main.java.ar.edu.utn.frba.ia.ag.paro.TiempoTranscurrido;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ranking;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ruleta;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Torneo;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.Clase;
import main.java.ar.edu.utn.frba.ia.tp.Cromosoma.ColorAula;

public class AlgoritmoGeneticoTPTest {
		
	
	@Test
	public void config1() {
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(new CantidadDeCiclos(1000L));
		config.setPoblacionInicial(600);
		config.setMetodoDeSeleccion(new Torneo());
		config.setCruzamiento(new Simple());
		config.setMutacion(new MutacionSimple(0.5));
		correrTest(config,1);
	}
	
	@Test
	public void config2() {
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(new TiempoTranscurrido(0,2,0));
		config.setPoblacionInicial(100);
		config.setMetodoDeSeleccion(new Torneo());
		config.setCruzamiento(new BinomialAzar());
		config.setMutacion(new MutacionSimple(0.8));
		correrTest(config,2);
	}

	@Test
	public void config3() {
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(new CantidadDeCiclos(800L));
		config.setPoblacionInicial(300);
		config.setMetodoDeSeleccion(new Ruleta());
		config.setCruzamiento(new Simple());
		config.setMutacion(new MutacionTemperaturaAscendente());
		correrTest(config,3);
	}
	
	@Test
	public void config4() {
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(new CantidadDeCiclos(5000L));
		config.setPoblacionInicial(200);
		config.setMetodoDeSeleccion(new Torneo());
		config.setCruzamiento(new BinomialAzar());
		config.setMutacion(new MutacionSimple(0.7));
		correrTest(config,4);
	}
	
	//@Test
	public void config5() {				// TEST SOLUCION PERFECTA
		Configuracion config = new ConfiguracionTP();
		config.setCriterioDeParo(new AptitudMinima(15));
		config.setPoblacionInicial(10000);
		config.setMetodoDeSeleccion(new Torneo());
		config.setCruzamiento(new Simple());
		config.setMutacion(new MutacionSimple(0.5));
		correrTest(config,5);
	}
		
	
	private void correrTest(Configuracion config, int numero_config) {
		System.out.println("======CONFIG "+numero_config+"======");
			AlgoritmoGenetico ag = new AlgoritmoGeneticoTP(config, Cromosoma.class,numero_config);
			Individuo resultado = ag.ejecutar();
			Assert.assertNotNull(resultado);
			((Cromosoma) resultado).printCromosoma();
		}
}
