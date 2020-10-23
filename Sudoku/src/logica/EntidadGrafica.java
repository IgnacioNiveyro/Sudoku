package logica;

import javax.swing.ImageIcon;

/** 
 * Clase EntidadGrafica
 * Modela la Entidad Gr�fica asociada a la Celda.
 * @author Ignacio Niveyro.
 * */
public class EntidadGrafica {
	/** 
	 * Grafico de la entidad gr�fica.
	 * */
	private ImageIcon grafico;
	/** 
	 * Arreglo con im�genes que cumplen las reglas del sudoku.
	 * */
	private String[] imagenes;
	/** 
	 * Arreglo con im�genes que no cumplen las reglas del sudoku.
	 * */
	private String[] imagenesRojo;
	/** 
	 * Arreglo con im�genes para las celdas que no contengan n�meros.
	 * */
	private String[] imagenInicialSignoPregunta;
	/** 
	 * Inicializa los arreglos de im�genes, im�genes rojo e im�gen para el signo de pregunta.
	 * Inicializa el gr�fico.
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
	 * @param indice �ndice del arreglo de im�genes que cumplen las reglas del sudoku.
	 * */
	public void actualizarImagen(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	/** 
	 * Actualiza la imagen con error
	 * @param indice �ndice del arreglo de im�genes que no cumplen las reglas del sudoku.
	 * */
	public void actualizarImagenError(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenesRojo[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	/** 
	 * Retorna el gr�fico de la clase
	 * @return grafico
	 * */
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	/** 
	 * Setea el gr�fico de la clase
	 * @param grafico imagen a setear.
	 * */
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	/** 
	 * Retorna el arreglo de im�genes.
	 * @return String im�genes.
	 * */
	public String[] getImagenes() {
		return this.imagenes;
	}
	/** 
	 * Retorna el gr�fico de una posici�n dada del arreglo de imagenes.
	 * @param pos posici�n del arreglo de im�genes.
	 * @return Imagen del arreglo.
	 * */
	public ImageIcon getGraficoArreglo(int pos) {
		ImageIcon g=new ImageIcon(this.getClass().getResource(this.imagenes[pos]));
		return g;
	}
	/**
	 * Setea un string de im�genes.
	 * @param imagenes String de im�genes.
	 *  */
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}
