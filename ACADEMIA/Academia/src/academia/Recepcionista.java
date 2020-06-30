package academia;

public class Recepcionista {

	long ID = 0;
	String nome = "Recepcionista";
	long CPF = 00000000000;
	String telefone = "(11) 2222-2222";
	String codigoAcesso = "acessar";

	public Recepcionista(long long1, String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}

	public Recepcionista() {
		// TODO Auto-generated constructor stub
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCPF() {
		return CPF;
	}

	public void setCPF(long cPF) {
		CPF = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

}
