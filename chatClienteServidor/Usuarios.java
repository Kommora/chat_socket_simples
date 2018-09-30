package chatClienteServidor;

import java.io.InputStream;
import java.io.OutputStream;

public class Usuarios {
	
	private String nome;
	private String canal;
	private String host;
	private InputStream entrada;
	private OutputStream saida;
	
	public Usuarios(String nome, String canal, String host, InputStream entrada, OutputStream saida) {
		super();
		this.nome = nome;
		this.canal = canal;
		this.host = host;
		this.entrada = entrada;
		this.saida = saida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public InputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(InputStream entrada) {
		this.entrada = entrada;
	}

	public OutputStream getSaida() {
		return saida;
	}

	public void setSaida(OutputStream saida) {
		this.saida = saida;
	}
		
}