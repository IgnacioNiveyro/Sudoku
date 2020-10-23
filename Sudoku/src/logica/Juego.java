package logica;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
 * Clase Juego
 * Implementación de la lógica del Sudoku, resuelve las operaciones de la GUI.
 * @author Ignacio Niveyro.
 * */

public class Juego {
	/** 
	 * Matriz de celdas que representan el tablero del sudoku.
	 * */
	private Celda [][] tablero;
	/** 
	 * Número que representa la cantidad de filas y columnas del sudoku.
	 * */
	private int cantFilas;
	/** 
	 * Bandera que me permite saber si el archivo leído es una solución correcta.
	 * */
	private boolean archivoEsCorrecto;
	/** 
	 * logger
	 * */
	private static Logger logger;
	/** 
	 * Matriz de enteros que contendrá la solución del archivo.
	 * */
	private int [][] matrizSolucion;
	/** 
	 * Setea el level del handler y del logger
	 * Chequea si el archivo es correcto, de ser así inicializa la matriz de Celdas.
	 * */
	public Juego() {
		
		if(logger==null) {
			logger= Logger.getLogger(Juego.class.getName());
			
			Handler hnd=new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			logger.setLevel(Level.WARNING);
			
			Logger rootLogger=logger.getParent();
			for(Handler h : rootLogger.getHandlers())
				h.setLevel(Level.OFF);
		}
		
		this.cantFilas = 9;
		tablero = new Celda[this.cantFilas][this.cantFilas];
		matrizSolucion=new int[this.cantFilas][this.cantFilas];
		archivoEsCorrecto=chequearSolucion();
		if(archivoEsCorrecto)
			this.inicializarConArchivo(matrizSolucion);
	}
	/** 
	 * Devuelve si el archivo es correcto
	 * @return archivoEsCorrecto.
	 * */
	public boolean getArchivoEsCorrecto() {
		return archivoEsCorrecto;
	}
	/** 
	 * Actualiza la imágen de la celda
	 * @param c Celda a actualizar
	 * */
	public void accionar(Celda c) {
		c.actualizarImagen();
	}
	/** 
	 * Retora la celda correspondiente a la fila i columna j
	 * @param i fila de la celda
	 * @param j columna de la celda
	 * @return Celda
	 * */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	/** 
	 * Retorta la cantidad de filas y columnas del sudoku
	 * @return cantFilas
	 * */
	public int getCantFilas() {
		return this.cantFilas;
	}
	/** 
	 * Chequea la solución del archivo.
	 * @return verdadero si el archivo contenía una solución válida, falso en caso contrario.
	 * */
	public boolean chequearSolucion() {
		boolean toRet=true;
		Integer c;
		Scanner scn=null;
		InputStream in = Juego.class.getClassLoader().getResourceAsStream("archivos/solucion.txt");	
		try {
			scn =new Scanner(in);
		}catch(NullPointerException e) {
			logger.warning("archivo inválido.");
			System.exit(0);
		}
		c=scn.hasNext() ? scn.nextInt() : null;
		for(int i=0; i<this.cantFilas && toRet ; i++) {
			for(int j=0; j<this.cantFilas && toRet; j++) {
				if(c==null) {
					logger.warning("El archivo que contiene la solución es inválido");
					System.exit(0);
				}
				else {
					if(c>9) {
						logger.warning("El archivo no cumple con el formato");
						toRet=false;
					}else {
						matrizSolucion[i][j]=c;
					}
					c=scn.hasNext() ? scn.nextInt() : null;
				}
			}
		}
		int indiceInicialF;
		int indiceFinalC;
			
			
			for(int i=0; i<this.cantFilas && toRet; i++) {
				for(int j=0; j<this.cantFilas && toRet; j++) {
					if(i < 3) {
						indiceInicialF = 0;
					}
					else {
						if(j < 6) {
							indiceInicialF = 3;
						}
						else {
							indiceInicialF = 6;
						}
					}
					if(j < 3) {
						indiceFinalC = 0;
					}
					else {
						if(j < 6) {
							indiceFinalC = 3;
						}
						else {
							indiceFinalC = 6;
						}
					}
					toRet=verificarPropiedadesArchivo(i,j,indiceInicialF,indiceFinalC,matrizSolucion);
				}
			}
			scn.close();
		return toRet;
	}
	/** 
	 * Verifica que cada elemento del archivo verifique las propiedades para ser una solución válida
	 * @param fila fila del entero del archivo a ser chequeado.
	 * @param columna columna del entero del archivo a ser chequeado.
	 * @param indiceInicialF entero que marca el inicio de la matriz correspondiente del entero a chequear.
	 * @param indiceFinalC entero que marca la columna de la matriz correspondiente del entero a chequear .
	 * @param m matriz que contiene la solución del archivo.
	 * @return seVerifica verdadero si se verifican las reglas del archivo, falso en caso contrario.
	 * */
	private  boolean verificarPropiedadesArchivo(int fila, int columna, int indiceInicialF, int indiceFinalC,int[][]m) {
		boolean seVerifica = true;
		int cantVecesEncontrado=0;
		int nroActual=m[fila][columna]; //este nro puede aparecer 1 vez en la fila, columna y matriz 3x3 perteneciente
		for(int i = 0; i< 9 && seVerifica; i++) {//Recorro toda la fila
			if(m[fila][i] == nroActual) {
				cantVecesEncontrado++;
				if(cantVecesEncontrado>1) 
				seVerifica = false;	
			}
				
		}
		/** si llego hasta esta instancia cantVeces encontrado sera 1 ya que contemplo toda la fila*/
		if(seVerifica) {
			cantVecesEncontrado=0;
			for(int j = 0; j<9 && seVerifica; j++) {//Recorro toda la columna
				if(m[j][columna] == nroActual) //Si se repite el valor de la imagen no se verifica la propiedad
					cantVecesEncontrado++;
					if(cantVecesEncontrado>1)
						seVerifica = false;	
			}
		}
		cantVecesEncontrado=0;
		for(int i = indiceInicialF; i<indiceInicialF + 3 && seVerifica; i++){
			for(int j = indiceFinalC; j<indiceFinalC + 3 && seVerifica; j++) {
				if(nroActual==m[i][j]) {
					if(m[i][j] == nroActual)
						cantVecesEncontrado++;
					if(cantVecesEncontrado>1)
						seVerifica = false;	
				}
				
			}
		}	
		return seVerifica;
	}
	/** 
	 * Inicializa las 9x9 celdas del sudoku de manera random.
	 * @param matriz Matriz Solucion del archivo que contiene la solución válida.
	 * */
	public void inicializarConArchivo(int [][] matriz) {
		Random rnd=new Random();
		Celda celda = new Celda();
		int c=0;;
			
			for (int i =0; i<cantFilas; i++) {
				for (int j =0; j<cantFilas; j++) {
					c=matriz[i][j]-1;
					int aux=rnd.nextInt(3);
					celda = new Celda(i,j);
					tablero[i][j] = celda;
					celda.setCumplePropiedad(true);
					if(aux==1 || aux==0) {
						celda.setValor(c);
					}
				}
		    }
	}
	/** 
	 * Verifica si los elementos cumplen o no las propiedades del sudoku de la fila, columna y matriz
	 * correspondientes a la celda pasada por parámetro.
	 * @param c Celda a verificar.
	 * @return verdadero si toda la fila, columna y matriz correspondiente a la celda c cumplen las propiedades 
	 * del sudoku, falso en caso contrario
	 * */
	public boolean verificarPropiedades(Celda c) {
		boolean seVerifica = true;
		int fila = c.getFila();
		int columna = c.getColumna();
		int nroGraficoCeldaActual = c.getValor();
		int indiceF;
		int indiceC;
		
	    for(int i = 0; i < cantFilas; i++) {//Recorro toda la fila
			if(tablero[fila][i].getValor() != null && !(tablero[fila][i].equals(c))){//Me fijo de no pasar por arriba de la celda actual ni de un ?
				if(nroGraficoCeldaActual == tablero[fila][i].getValor()){
					c.setCumplePropiedad(false);
					tablero[fila][i].setCumplePropiedad(false);
					seVerifica = false;
				}
				else {
					c.setCumplePropiedad(true);
					tablero[fila][i].setCumplePropiedad(true);
				}
			}
		}
					
		for(int j = 0; j < cantFilas; j++) {//Recorro toda la columna.
			if(tablero[j][columna].getValor() != null  && !(tablero[j][columna].equals(c))){//Me fijo de no pasar por arriba de la celda actual ni de un ?
				if(nroGraficoCeldaActual == tablero[j][columna].getValor()) {
					c.setCumplePropiedad(false);
					tablero[j][columna].setCumplePropiedad(false);
					seVerifica = false;
				}
				else {
					c.setCumplePropiedad(true);
					tablero[j][columna].setCumplePropiedad(true);
				}
			}
		}
		
		if(fila < 3) {
			indiceF = 0;
		}
		else {
			if(fila < 6) {
				indiceF = 3;
			}
			else {
				indiceF = 6;
			}
		}
		
		if(columna < 3) {
			indiceC = 0;
		}
		else {
			if(columna < 6) {
				indiceC = 3;
			}
			else {
				indiceC = 6;
			}
		}
		
		for(int i = indiceF; i<indiceF + 3; i++){
			for(int j = indiceC; j<indiceC + 3; j++) {
				
				if(tablero[i][j].getValor() != null  && !(tablero[i][j].equals(c))) {
					if(nroGraficoCeldaActual == tablero[i][j].getValor()) {
						c.setCumplePropiedad(false);
						tablero[i][j].setCumplePropiedad(false);
						seVerifica = false;
					}
					else {
						c.setCumplePropiedad(true);
						tablero[i][j].setCumplePropiedad(true);
					}
				}
			}
		}
		return seVerifica;
	}
	/** 
	 * Actualiza la imagen con error de la celda
	 * @param c Celda a actualizar.
	 * */
	public void accionarError(Celda c) {
		c.actualizarImagenError();
	}
	/** 
	 * Retorna el tablero que contiene las celdas
	 * @return matriz de las celdas.
	 * */
	public Celda[][] getTablero(){
		return tablero;
	}
	/** 
	 * Para cada celda del tablero verifica si cumple con las reglas del sudoku.
	 * @return verdadero si el usuario llego a una solución válida, falso en caso contrario.
	 * */
	public boolean ganar() {
		boolean gane = true;
		Celda c;
	
		for(int i = 0; i<cantFilas && gane; i++) {
			for(int j = 0; j<cantFilas && gane; j++) {
				c = tablero[i][j]; 
				if(c.getValor() == null || !(this.verificarPropiedades(c))) {
					gane = false;
				}   
			}
		}
		return gane;		
	}
}
