package tp.pr5.mv.observers;

public interface CPUObserver extends ObserverMV {

	void actualizarTextoEntrada();

	void actualizarTextoSalida();

	void actualizarPrograma();

	void maquinaParada();
}
