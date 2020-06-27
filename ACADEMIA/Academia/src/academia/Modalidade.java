package academia;

public class Modalidade {

	long ID;
	String tipo;
	String nivel;
	int qtdAtividades;

	public int getQtdAtividades() {
		return qtdAtividades;
	}

	public void setQtdAtividades(int qtdAtividades) {
		this.qtdAtividades = qtdAtividades;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
