package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Celda;
import logica.Juego;
import logica.Reloj;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/** 
 * Clase Contenedor
 * Interfaz Grafica que modela el juego Sudoku.
 * @author Ignacio Niveyro.
 * */
public class Contenedor extends JFrame {
	/** 
	 * Panel que auspiciará de contenedor principal de la clase.
	 * */
	private JPanel contentPane;
	/** 
	 * Panel que contendrá la matriz del sudoku.
	 * */
	private JPanel panelCeldas;
	/** 
	 * Panel auxiliar que contendrá el reloj y los botones de Jugar y Finalizar.
	 * */
	private JPanel panelAux;
	/** 
	 * Matriz que contendrá las celdas del sudoku.
	 * */
	private JLabel[][] celdas;
	/** 
	 * Variable que permitirá realizar operaciones de la lógica.
	 * */
	private Juego juego;
	/** 
	 * Bandera que me permite, o no, interactuar con el sudoku.
	 * */
	private boolean sePuedeJugar=false;
	/** 
	 * Labels que modelan el reloj.
	 * */
	private JLabel lblHoras,lblHoras2, labelMinutos,labelMinutos2, labelSegundos, labelSegundos2, lblFondo;
	/** 
	 * String que contiene las imagenes de los fondos.
	 * */
	private String [] fondo=new String[] {"/img/Fondo.png","/img/Fondo1.png","/img/Fondo2.png","/img/Fondo3.png","/img/Fondo4.png"};
	/** 
	 * String que contiene las imagenes del reloj.
	 * */
	private String [] reloj=new String[] {"/imagenesReloj/reloj0.png","/imagenesReloj/reloj1.png","/imagenesReloj/reloj2.png","/imagenesReloj/reloj3.png",
			"/imagenesReloj/reloj4.png","/imagenesReloj/reloj5.png","/imagenesReloj/reloj6.png","/imagenesReloj/reloj7.png","/imagenesReloj/reloj8.png",
			"/imagenesReloj/reloj9.png",};
	/**
	 * Launch the application.
	 * @param args argumento del string.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contenedor frame = new Contenedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el contenedor principal, en el se insertan los dos paneles, de las celdas y del reloj.
	 * En el panel de las celdas, al inicializar la variable de tipo juego, se insertan las 9x9 celdas
	 * con sus respectivas imagenes.
	 * En el panel del reloj, se insertan los labels correspondientes y por debajo dos botones, el de 
	 * Iniciar y el de Finalizar. 
	 */
	public Contenedor() {
		setTitle("Sudoku StardewValley");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/** Crea un objeto de la clase Juego, es decir, inicializa las celdas con sus entidades graficas.
		 * 	Chequea que el archivo ingresado en la clase Juego tenga una solucion valida
		 * 	En caso de ser invalida el juego no iniciará.
		 * */
		juego=new Juego();
		if(!juego.getArchivoEsCorrecto()) {
			JOptionPane.showMessageDialog(null, "Error: no se pudo inicializar el juego, el archivo no contenía una solución válida.");
			System.exit(0);	
		}
		
		/** PANEL DE LAS CELDAS*/
		panelCeldas = new JPanel();
		panelCeldas.setBounds(0, 0, 450, 424);
		contentPane.add(panelCeldas);
		panelCeldas.setLayout(new GridLayout(9, 9, 2, 2));
		panelCeldas.setBackground(UIManager.getColor("Separator.shadow"));
		this.inicializarLabels();
		/** */
		
		
		/** PANEL AUX PARA RELOJ */
		panelAux = new JPanel();
		panelAux.setBackground(SystemColor.control);
		panelAux.setBounds(449, 0, 200, 424);
		contentPane.add(panelAux);
		panelAux.setLayout(null);
		/** */
		
		
		/** label fondo*/
		Random rnd=new Random();
		int imagenFondo=rnd.nextInt(5);
		lblFondo=new JLabel();
		lblFondo.setBounds(0, 0, 200, 424);
		ImageIcon grafico = new ImageIcon(this.getClass().getResource(this.fondo[imagenFondo]));
		lblFondo.setIcon(grafico);
		reDimensionar(lblFondo, grafico);
		panelAux.add(lblFondo);
		/** */
		
		//this.inicializarAux(); /** Inicializa en el panel aux la conversion de frutas a numeros*/
		
		/** Labels para el reloj*/
		lblHoras = new JLabel();
		lblHoras.setBounds(10, 10, 25, 25);
		ImageIcon grafico0 = new ImageIcon(this.getClass().getResource(this.reloj[0]));
		lblHoras.setIcon(grafico0);
		reDimensionar(lblHoras,grafico0);
		lblFondo.add(lblHoras);
		
		lblHoras2 = new JLabel();
		lblHoras2.setBounds(36, 10, 25, 25);
		lblHoras2.setIcon(grafico0);
		reDimensionar(lblHoras2,grafico0);
		lblFondo.add(lblHoras2);
		
		labelMinutos = new JLabel();
		labelMinutos.setBounds(74, 10, 25, 25);
		labelMinutos.setIcon(grafico0);
		reDimensionar(labelMinutos,grafico0);
		lblFondo.add(labelMinutos);
		
		labelMinutos2 = new JLabel();
		labelMinutos2.setBounds(100, 10, 25, 25);
		labelMinutos2.setIcon(grafico0);
		reDimensionar(labelMinutos2,grafico0);
		lblFondo.add(labelMinutos2);
		
		
		labelSegundos = new JLabel();
		labelSegundos.setBounds(137, 10, 25, 25);
		labelSegundos.setIcon(grafico0);
		reDimensionar(labelSegundos, grafico0);
		lblFondo.add(labelSegundos);
		
		labelSegundos2 = new JLabel();
		labelSegundos2.setBounds(163, 10, 25, 25);
		labelSegundos2.setIcon(grafico0);
		reDimensionar(labelSegundos2, grafico0);
		lblFondo.add(labelSegundos2);
		/** */
		
		/** Boton de finalizar*/
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean cumple=true;
				cumple=juego.ganar();
				if(cumple) {
					sePuedeJugar=false;
					JOptionPane.showMessageDialog(null, "¡Felicitaciones! ", "Estado del juego", JOptionPane.INFORMATION_MESSAGE);
					btnNewButton.setEnabled(false);
				}
				else
					JOptionPane.showMessageDialog(null, "Su solucion es incompleta o invalida", "Estado del juego", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton.setBounds(108, 364, 82, 25);
		lblFondo.add(btnNewButton);
		/** */
		
		/** Boton de jugar*/
		JButton btnjugar = new JButton("Jugar");
		btnjugar.setBounds(10, 364, 82, 25);
		lblFondo.add(btnjugar);
		
		btnjugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<juego.getCantFilas(); i++) {
					for(int j=0; j<juego.getCantFilas(); j++) {
							celdas[i][j].setEnabled(true);
					}
				}
				sePuedeJugar=true; //a partir de ahora pueden editarse las celdas!
				btnjugar.setEnabled(false);
				btnNewButton.setEnabled(true);
				iniciarReloj();
				
			}
			
		});
		/** */
		
	} /** Fin del constructor*/
	
	/** 
	 * 	Crea un objeto de la clase reloj y lo inicia
	 * 	si SePuedeJugar es verdadera, es decir, el jugador no finalizo el sudoku, entonces el reloj 
	 * 	se actualizara segundo a segundo, por el contrario si el jugador finalizo el sudoku con una
	 * 	solucion válida entonces el reloj se detendrá.
	 * */
	private void iniciarReloj() {
		Reloj r=new Reloj();
		r.iniciarReloj();
		Timer timer = new Timer();
		TimerTask tarea=new TimerTask() {

			@Override
			public void run() {
				if(sePuedeJugar) {
					/** el metodo getSegundos devuelve 00,01..60 */
					ImageIcon graficoSegundosIzq, graficoSegundosDer;
					int derecha=Integer.parseInt(r.getSegundos().charAt(1)+"");
					int izquierda=Integer.parseInt(r.getSegundos().charAt(0)+"");
					graficoSegundosIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
					graficoSegundosDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
					labelSegundos.setIcon(graficoSegundosIzq);
					reDimensionar(labelSegundos,graficoSegundosIzq);
					labelSegundos2.setIcon(graficoSegundosDer);
					reDimensionar(labelSegundos2,graficoSegundosDer);
					/** */
					ImageIcon graficoMinutosIzq, graficoMinutosDer;
					derecha=Integer.parseInt(r.getMinutos().charAt(1)+"");
					izquierda=Integer.parseInt(r.getMinutos().charAt(0)+"");
					graficoMinutosIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
					graficoMinutosDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
					labelMinutos.setIcon(graficoMinutosIzq);
					reDimensionar(labelMinutos,graficoMinutosIzq);
					labelMinutos2.setIcon(graficoMinutosDer);
					reDimensionar(labelMinutos2,graficoMinutosDer);
					/** */
					if(r.getHorasInt()>0) { //ineficiente repintar 2 labeles cada 1 segundo durante 1 hora por eso el if
						ImageIcon graficoHorasIzq, graficoHorasDer;
						derecha=Integer.parseInt(r.getHoras().charAt(1)+"");
						izquierda=Integer.parseInt(r.getHoras().charAt(0)+"");
						graficoHorasIzq = new ImageIcon(this.getClass().getResource(reloj[izquierda]));
						graficoHorasDer = new ImageIcon(this.getClass().getResource(reloj[derecha]));
						lblHoras.setIcon(graficoHorasIzq);
						reDimensionar(lblHoras,graficoHorasIzq);
						lblHoras2.setIcon(graficoHorasDer);
						reDimensionar(lblHoras2,graficoHorasDer);
					}
					
				}
			}};
			timer.schedule(tarea, 0, 300);
	}
	
	/**
	 *  A cada celda le asocia un label y un listener para que el usuario pueda interactuar
	 */
	public void inicializarLabels() {
		celdas=new JLabel[juego.getCantFilas()][juego.getCantFilas()];
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				
				
				Celda c = juego.getCelda(i,j);
				
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				
				label.setEnabled(false);
				panelCeldas.add(label);
				celdas[i][j]=label;
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});
				
				panelCeldas.add(label);
				
				if(c.getValor() == null) {//Solo modifico a las celdas con ?
					
					label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(sePuedeJugar)
							chequear(c);
					}
					});	
				}
				
			}
		}
	}
	/** Identifica, por cada vez que el usuario clickea una celda, cuales cumplen las condiciones y cuales no
	 * 	y, a partir de eso, las pinta.
	 * @param c Celda celda que el usuario oprimió.
	 */
	private void chequear(Celda c) {
		c.actualizarValor();
		juego.accionar(c);
		Celda[][] tableroJuego = juego.getTablero();
		for(int i = 0; i< juego.getCantFilas(); i++) {
			for(int j = 0; j<juego.getCantFilas(); j++) {
				if(tableroJuego[i][j].getValor()!=null && !juego.verificarPropiedades(tableroJuego[i][j])) {
					juego.accionarError(tableroJuego[i][j]);//le pone la imagen roja
				}
				else {
					if(tableroJuego[i][j].getValor()!=null && juego.verificarPropiedades(tableroJuego[i][j])) {
						juego.accionar(tableroJuego[i][j]);
					}
				}
				
			}
		}
		for(int i = 0; i< juego.getCantFilas(); i++) {
			for(int j = 0; j<juego.getCantFilas(); j++) {
				
				ImageIcon icon = tableroJuego[i][j].getEntidadGrafica().getGrafico();
				reDimensionar(celdas[i][j],icon);
				
			}
		}
	}	
		
	/**
	 *  Este metódo inicializaba en el panel auxiliar una tabla con la conversión de frutas a numeros
	 * 	Se puede "des-comentar" la linea 121 y funciona, pero a mi parecer queda "feo".
	 * 	Si se quiere ejecutar este metodo primero habría que correr el reloj al centro del panel aux.
	 * 	No lo borré, mas que nada, por si en algún momento lo implemento mas bonito estéticamente
	 */
	public void inicializarAux() {
		/** Inicializo con imagenes la conversión*/
			
			JLabel label1 = new JLabel();
			label1.setBounds(35, 10, 35, 35);
			ImageIcon grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(0);
			label1.setIcon(grafico);
			reDimensionar(label1,grafico);
			lblFondo.add(label1);
			
			JLabel label2 = new JLabel();
			label2.setBounds(95, 10, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(1);
			label2.setIcon(grafico);
			reDimensionar(label2,grafico);
			lblFondo.add(label2);
			
			JLabel label3 = new JLabel();
			label3.setBounds(155, 10, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(2);
			label3.setIcon(grafico);
			reDimensionar(label3,grafico);
			lblFondo.add(label3);
			
			JLabel label4 = new JLabel();
			label4.setBounds(35, 50, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(3);
			label4.setIcon(grafico);
			reDimensionar(label4,grafico);
			lblFondo.add(label4);
			
			JLabel label5 = new JLabel();
			label5.setBounds(95, 50, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(4);
			label5.setIcon(grafico);
			reDimensionar(label5,grafico);
			lblFondo.add(label5);
			
			JLabel label6 = new JLabel();
			label6.setBounds(155, 50, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(5);
			label6.setIcon(grafico);
			reDimensionar(label6,grafico);
			lblFondo.add(label6);
			
			JLabel label7 = new JLabel();
			label7.setBounds(35, 90, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(6);
			label7.setIcon(grafico);
			reDimensionar(label7,grafico);
			lblFondo.add(label7);
			
			JLabel label8 = new JLabel();
			label8.setBounds(95, 90, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(7);
			label8.setIcon(grafico);
			reDimensionar(label8,grafico);
			lblFondo.add(label8);
			
			JLabel label9 = new JLabel();
			label9.setBounds(155, 90, 35, 35);
			grafico=juego.getCelda(0, 0).getEntidadGrafica().getGraficoArreglo(8);
			label9.setIcon(grafico);
			reDimensionar(label9,grafico);
			lblFondo.add(label9);
			
			JLabel lblNewLabel = new JLabel("1");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setFont(new Font("Wide Latin", Font.BOLD, 15));
			lblNewLabel.setBounds(10, 10, 20, 35);
			lblFondo.add(lblNewLabel);
			
			JLabel label = new JLabel("2");
			label.setForeground(new Color(255, 255, 255));
			label.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label.setBounds(70, 10, 20, 35);
			lblFondo.add(label);
			
			JLabel label_1 = new JLabel("3");
			label_1.setForeground(new Color(255, 255, 255));
			label_1.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_1.setBounds(130, 10, 20, 35);
			lblFondo.add(label_1);
			
			JLabel label_2 = new JLabel("4");
			label_2.setForeground(new Color(255, 255, 255));
			label_2.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_2.setBounds(8, 50, 20, 35);
			lblFondo.add(label_2);
			
			JLabel label_3 = new JLabel("5");
			label_3.setForeground(new Color(255, 250, 250));
			label_3.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_3.setBounds(70, 50, 20, 35);
			lblFondo.add(label_3);
			
			JLabel label_4 = new JLabel("6");
			label_4.setForeground(new Color(255, 255, 255));
			label_4.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_4.setBounds(130, 50, 20, 35);
			lblFondo.add(label_4);
			
			JLabel label_5 = new JLabel("7");
			label_5.setForeground(new Color(255, 255, 255));
			label_5.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_5.setBounds(10, 90, 20, 35);
			lblFondo.add(label_5);
			
			JLabel label_6 = new JLabel("8");
			label_6.setForeground(new Color(255, 255, 255));
			label_6.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_6.setBounds(70, 90, 20, 35);
			lblFondo.add(label_6);
			
			JLabel label_7 = new JLabel("9");
			label_7.setForeground(new Color(255, 255, 255));
			label_7.setFont(new Font("Wide Latin", Font.BOLD, 15));
			label_7.setBounds(130, 90, 20, 35);
			lblFondo.add(label_7);
			
	}
	/** 
	 * Redimensiona el tamaño del grafico en la label
	 * @param label label a dimensionar.
	 * @param grafico grafico a insertar en el label.
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
