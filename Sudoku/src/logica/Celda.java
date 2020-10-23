package logica;
/** 
 * Clase Celda
 * Implementación de una Celda de sudoku.
 * @author Ignacio Niveyro.
 * */
public class Celda {
	/** 
	 * valor de la celda.
	 * */
	private Integer valor;
	/**
	 * Entidad Grafica que representará la imágen de la Celda.
	 * */
	private EntidadGrafica entidadGrafica;
	/**
	 * fila y columna de la celda.
	 * */
	private int fila, columna;
	/** 
	 * Bandera que me indica si la Celda cumple las condiciones o no.
	 * */
	private boolean cumpleCondiciones;
	/** 
	 * Inicializa la celda.
	 * */
	public Celda() {
		this.cumpleCondiciones=false;
		this.valor=null;
		this.entidadGrafica=new EntidadGrafica();
		fila=columna=0;
	}
	/** 
	 * Inicializa la celda con una fila y una columna.
	 * @param fila fila de la celda.
	 * @param columna columna de la celda.
	 * */
	public Celda(int fila, int columna) {
		this.fila=fila;
		this.columna=columna;
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
	}
	/** 
	 * Retorna la fila de la celda
	 * @return fila fila de la celda.
	 * */
	public int getFila() {
		return fila;
	}
	/** 
	 * Retorna la columna de la celda
	 * @return columna columna de la celda.
	 * */
	public int getColumna() {
		return columna;
	}
	/** 
	 * Actualiza la imágen de la entidad gráfica.
	 * */
	public void actualizarImagen() {
		entidadGrafica.actualizarImagen(this.valor);
	}
	/** 
	 * Retorna la cantidad de elementos del arreglo que contiene las imagenes de las celdas.
	 * @return entero con la cantidad de elementos.
	 * */
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	/** 
	 * Actualiza la entidad gráfica con una imágen de error.
	 * */
	public void actualizarImagenError() {
		entidadGrafica.actualizarImagenError(this.valor);
	}
	/** 
	 * Retorna el valor de la Celda
	 * @return valor valor de la celda.
	 * */
	public Integer getValor() {
		return this.valor;
	}
	/** 
	 * Actualiza el valor de la Celda
	 * */
	public void actualizarValor() {
		if (valor!=null && valor < getCantElementos()-1){//Si no es nulo o si se pasa de 8
			valor++;
		}
		else {
			valor = 0;
		}		
	}
	/** 
	 * Setea un valor a la Celda
	 * @param valor valor a ser seteado.
	 * */
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizarImagen(this.valor);
		}else {
				this.valor = null;
		}
	}
	/** 
	 * Retorna un booleano que indica si la Celda cumple las reglas del sudoku.
	 * @return boolean
	 * */
	public boolean getCumpleCondicion() {
		return this.cumpleCondiciones;
	}
	/** 
	 * Retorna la entidad grafica de la Celda.
	 * @return EntidadGrafica.
	 * */
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	/** 
	 * Setea una entidad gráfica a la Celda
	 * @param g Entidad Gráfica a setear.
	 * */
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	/** 
	 * Setea verdadero o falso a la Celda dependiendo si cumple las reglas del sudoku.
	 * @param b Booleano a setear.
	 * */
	public void setCumplePropiedad(boolean b) {
		this.cumpleCondiciones=b;
	}
}
