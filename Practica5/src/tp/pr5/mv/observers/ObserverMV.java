package tp.pr5.mv.observers;

public interface ObserverMV {

	void addCima(String n);

	void pilaCambiada();

	void deleteCima();

	void memoriaCambiada();

	void addCelda(int posicion, int valor);

	void actualizarPrograma();

	void actualizarTextoEntrada();

	void actualizarTextoSalida();

	void maquinaParada();

	void imprimirConsola(String info);

	void mostrarError(String message);

	void lanzarHebraActualizadora();

	void pararMaquina();

}
