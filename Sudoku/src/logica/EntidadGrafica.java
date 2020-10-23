package logica;

import javax.swing.ImageIcon;

/** 
 * Clase EntidadGrafica
 * Modela la Entidad Gráfica asociada a la Celda.
 * @author Ignacio Niveyro.
 * */
public class EntidadGrafica {
	/** 
	 * Grafico de la entidad gráfica.
	 * */
	private ImageIcon grafico;
	/** 
	 * Arreglo con imágenes que cumplen las reglas del sudoku.
	 * */
	private String[] imagenes;
	/** 
	 * Arreglo con imágenes que no cumplen las reglas del sudoku.
	 * */
	private String[] imagenesRojo;
	/** 
	 * Arreglo con imágenes para las celdas que no contengan números.
	 * */
	private String[] imagenInicialSignoPregunta;
	/** 
	 * Inicializa los arreglos de imágenes, imágenes rojo e imágen para el signo de pregunta.
	 * Inicializa el gráfico.
	 * */
	public EntidadGrafica() {

		this.imagenes=new String[] {"/img/1.png","/img/2.png","/img/3.png",
				"/img/4.png","/img/5.png","/img/6.png","/img/7.png",
				"/img/8.png","/img/9.png"};
		this.imagenesRojo=new String[] {"/img/1r.png","/img/2r.png","/img/3r.png",
				"/img/4r.png","/img/5r.png","/img/6r.png","/img/7r.png",
				"/img/8r.png","/img/9r.png"};
		this.imagenInicialSignoPregunta=new String[] {"/img/pregunta1.png","/img/pregunta2.png"};
		
		this.grafico = new ImageIcon();
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenInicialSignoPregunta[0]));
		this.grafico.setImage(imageIcon.getImage());
		
	}
	/** 
	 * Actualiza la imagen 
	 * @param indice índice del arreglo de imágenes que cumplen las reglas del sudoku.
	 * */
	public void actualizarImagen(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	/** 
	 * Actualiza la imagen con error
	 * @param indice índice del arreglo de imágenes que no cumplen las reglas del sudoku.
	 * */
	public void actualizarImagenError(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenesRojo[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	/** 
	 * Retorna el gráfico de la clase
	 * @return grafico
	 * */
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	/** 
	 * Setea el gráfico de la clase
	 * @param grafico imagen a setear.
	 * */
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	/** 
	 * Retorna el arreglo de imágenes.
	 * @return String imágenes.
	 * */
	public String[] getImagenes() {
		return this.imagenes;
	}
	/** 
	 * Retorna el gráfico de una posición dada del arreglo de imagenes.
	 * @param pos posición del arreglo de imágenes.
	 * @return Imagen del arreglo.
	 * */
	public ImageIcon getGraficoArreglo(int pos) {
		ImageIcon g=new ImageIcon(this.getClass().getResource(this.imagenes[pos]));
		return g;
	}
	/**
	 * Setea un string de imágenes.
	 * @param imagenes String de imágenes.
	 *  */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}
