package main.java.ar.edu.utn.frba.ia.tp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.junit.Assert;

import main.java.ar.edu.utn.frba.ia.ag.AlgoritmoGenetico;
import main.java.ar.edu.utn.frba.ia.ag.Configuracion;
import main.java.ar.edu.utn.frba.ia.ag.Individuo;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.BinomialAzar;
import main.java.ar.edu.utn.frba.ia.ag.cruzamiento.Simple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionSimple;
import main.java.ar.edu.utn.frba.ia.ag.mutacion.MutacionTemperaturaAscendente;
import main.java.ar.edu.utn.frba.ia.ag.paro.CantidadDeCiclos;
import main.java.ar.edu.utn.frba.ia.ag.paro.TiempoTranscurrido;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Ruleta;
import main.java.ar.edu.utn.frba.ia.ag.seleccion.Torneo;

import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuInicial {

	private JFrame frmAcertijoProfesores;
	private JTable tabResultados;
	private JTextArea txtResultados;
	private DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicial window = new MenuInicial();
					window.frmAcertijoProfesores.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcertijoProfesores = new JFrame();
		frmAcertijoProfesores.setTitle("Acertijo Profesores");
		frmAcertijoProfesores.setResizable(false);
		frmAcertijoProfesores.setBounds(100, 100, 600, 300);
		frmAcertijoProfesores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAcertijoProfesores.getContentPane().setLayout(null);
		// --------------------- PRUEBA 1
		JButton btnPrueba1 = new JButton("Prueba 1");
		btnPrueba1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
				Configuracion config = new ConfiguracionTP();
				config.setCriterioDeParo(new CantidadDeCiclos(1000L));
				config.setPoblacionInicial(600);
				config.setMetodoDeSeleccion(new Torneo());
				config.setCruzamiento(new Simple());
				config.setMutacion(new MutacionSimple(0.5));
				correrTest(config,1);
			}
		});
		btnPrueba1.setBounds(32, 24, 108, 35);
		frmAcertijoProfesores.getContentPane().add(btnPrueba1);
		
		// --------------------- PRUEBA 2
		JButton btnPrueba2 = new JButton("Prueba 2");
		btnPrueba2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
				Configuracion config = new ConfiguracionTP();
				config.setCriterioDeParo(new TiempoTranscurrido(0,2,0));
				config.setPoblacionInicial(100);
				config.setMetodoDeSeleccion(new Torneo());
				config.setCruzamiento(new BinomialAzar());
				config.setMutacion(new MutacionSimple(0.8));
				correrTest(config,2);
			}
		});
		btnPrueba2.setBounds(172, 24, 108, 35);
		frmAcertijoProfesores.getContentPane().add(btnPrueba2);
		
		// --------------------- PRUEBA 3
		JButton btnPrueba3 = new JButton("Prueba 3");
		btnPrueba3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
				Configuracion config = new ConfiguracionTP();
				config.setCriterioDeParo(new CantidadDeCiclos(800L));
				config.setPoblacionInicial(300);
				config.setMetodoDeSeleccion(new Ruleta());
				config.setCruzamiento(new Simple());
				config.setMutacion(new MutacionTemperaturaAscendente());
				correrTest(config,3);
			}
		});
		btnPrueba3.setBounds(312, 24, 108, 35);
		frmAcertijoProfesores.getContentPane().add(btnPrueba3);
		
		// --------------------- PRUEBA 4
		JButton btnPrueba4 = new JButton("Prueba 4");
		btnPrueba4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
				Configuracion config = new ConfiguracionTP();
				config.setCriterioDeParo(new CantidadDeCiclos(5000L));
				config.setPoblacionInicial(200);
				config.setMetodoDeSeleccion(new Torneo());
				config.setCruzamiento(new BinomialAzar());
				config.setMutacion(new MutacionSimple(0.7));
				correrTest(config,4);
			}
		});
		btnPrueba4.setBounds(452, 24, 108, 35);
		frmAcertijoProfesores.getContentPane().add(btnPrueba4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 69, 528, 114);
		frmAcertijoProfesores.getContentPane().add(scrollPane);
		
		tabResultados = new JTable();
		scrollPane.setViewportView(tabResultados);
		tabResultados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Especialidad", "Color Aula", "Ubicacion Aula", "Pasatiempos", "Peculiaridad", "Bebida"
			}
		));
		
		txtResultados = new JTextArea();
		txtResultados.setBounds(32, 189, 375, 57);
		frmAcertijoProfesores.getContentPane().add(txtResultados);
		tabResultados.getColumnModel().getColumn(2).setPreferredWidth(95);
		
		
	}
	
	private void correrTest(Configuracion config, int numero_config) {
		AlgoritmoGenetico ag = new AlgoritmoGeneticoTP(config, Cromosoma.class,numero_config);
		Individuo resultado = ag.ejecutar();
		Assert.assertNotNull(resultado);
		
		Object[] row1 = obtenerDatos(((Cromosoma)resultado).getEtimologia());
		Object[] row2 = obtenerDatos(((Cromosoma)resultado).getGramatica());
		Object[] row3 = obtenerDatos(((Cromosoma)resultado).getLiteratura());
		Object[] row4 = obtenerDatos(((Cromosoma)resultado).getOrtografia());
		Object[] row5 = obtenerDatos(((Cromosoma)resultado).getRedaccion());
		
 
		
		model = (DefaultTableModel) tabResultados.getModel();
		model.addRow(row1);
		model.addRow(row2);
		model.addRow(row3);
		model.addRow(row4);
		model.addRow(row5);
		
		txtResultados.append("- Individuo mas apto: "+ag.masApto());
		txtResultados.append("\n- Aptitud Campeon: "+ag.campeon());
		txtResultados.append("\n- Peor aptitud: "+ag.peor());
		}

	private Object[] obtenerDatos(ProfesorEspecialidad profesor) {
		String especialidad = profesor.getClase().name().toString();
		String colorAula= profesor.getColorAula().name().toString();
		String ubicacionAula = profesor.getUbicacionAula().name().toString();
		String peculiaridad = profesor.getPeculiaridad().name().toString();
		String pasatiempos = profesor.getPasatiempo().name().toString();
		String bebida = profesor.getBebida().name().toString();
		Object[] datos = {especialidad,colorAula,ubicacionAula,pasatiempos,peculiaridad,bebida};
		return datos;
	}
	
	private void limpiarDatos() {
		((DefaultTableModel)tabResultados.getModel()).getDataVector().removeAllElements();
		((DefaultTableModel)tabResultados.getModel()).fireTableDataChanged();
		txtResultados.setText("");
	}
}
