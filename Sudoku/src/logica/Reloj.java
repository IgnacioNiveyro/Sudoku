package logica;

import java.util.Timer;
import java.util.TimerTask;
/** 
 * Clase Reloj
 * Implementa un Reloj para el panel de la GUI.
 * @author Ignacio Niveyro.
 * */
public class Reloj {
	/** 
	 * Enteros que representan las horas, minutos y segundos del reloj.
	 * */
	private int horas, minutos, segundos;
	/** 
	 * String que representan las horas minutos y segundos del reloj.
	 * */
	private String minutosString, segundosString, horasString;
	/** 
	 * Inicializa los atributos del reloj.
	 * */
	public Reloj() {
		horas=minutos=segundos=0;
		horasString=minutosString=segundosString="00";
	}
	/** 
	 * Haciendo uso del Timer y TimerTask cada 1 segundo actualiza el reloj.
	 * */
	public void iniciarReloj() {
		Timer timer = new Timer();

		TimerTask tarea=new TimerTask() {
			
			@Override
			public void run() {

				if(segundos<10) {
					segundosString="0"+segundos;
					setSegundos(segundosString);
				}	
				else {
					segundosString=""+segundos;
					setSegundos(segundosString);
				}
				if(minutos<10) {
					minutosString="0"+minutos;
					setMinutos(minutosString);
				}
				else {
					minutosString=""+minutos;
					setMinutos(minutosString);
				}
				if(horas<10) {
					horasString="0"+horas;
					setHoras(horasString);
				}
				else {
					horasString=""+horas;
					setHoras(horasString);
				}
				segundos++;
				if(segundos==60) {
					minutos++;
					segundos=0;
				}
				if(minutos==60) {
					horas++;
					minutos=0;
				}
			}
		};
		timer.schedule(tarea, 0, 1000); // en el 3er atributo debo poner 1000 si quiero que avance cada 1 segundo
	}
	/** 
	 * Setea los segundos del reloj.
	 * @param s segundos del reloj.
	 * */
	public void setSegundos(String s) {
		this.segundosString=s;
	}
	/** 
	 * Setea los minutos del reloj.
	 * @param s minutos del reloj.
	 * */
	public void setMinutos(String s) {
		this.minutosString=s;
	}
	/** 
	 * Setea las horas del reloj.
	 * @param s horas del reloj.
	 * */
	public void setHoras(String s) {
		this.horasString=s;
	}
	/** 
	 * Retorna el String asociado a las horas del reloj
	 * @return horasString horas asociadas al reloj.
	 * */
	public String getHoras() {
		return horasString;
	}
	/** 
	 * Retorna el String asociado a los minutos del reloj
	 * @return minutosString minutos asociadas al reloj.
	 * */
	public String getMinutos() {
		return minutosString;
	}
	/** 
	 * Retorna el String asociado a las segundos del reloj
	 * @return segundosString segundos asociadas al reloj.
	 * */
	public String getSegundos() {
		return segundosString;
	}
	/** 
	 * Retorna el entero asociado a la cantidad de horas del reloj.
	 * @return horas cantidad de horas del reloj.
	 * */
	public int getHorasInt() {
		return horas;
	}
}
