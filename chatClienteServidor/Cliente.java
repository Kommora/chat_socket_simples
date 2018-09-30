package chatClienteServidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente{

	private String ip;
	private int porta;
	private String nome;
	public static Utils utils;

	public Cliente(String ip,int porta,String nome){
		this.ip=ip;
		this.porta=porta;
		this.nome=nome;
		this.utils = new Utils(nome);
	}

	public void connect() throws UnknownHostException, IOException{
		//se conecta ao socket com o ip passado e a porta
		Socket cliente = new Socket(this.ip, this.porta);
		//cria um objeto scanner para capturar as mensagens do teclado
		Scanner teclado = new Scanner(System.in);
		//cria um objeto do tipo recebedor para exibir as mensagens enviadas para o cliente
		Recebedor r = new Recebedor(cliente.getInputStream());
		//cria um objeto para enviar as mensagens do cliente para o servidor
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		//envia o nome para o cliente
		saida.println(this.nome);
		//exibe que o cliente se conectou
		System.out.println("O cliente "+this.nome+" se conectou");
		//inicia uma thread para a exibicao das mensagens enviadas para o cliente
		new Thread(r).start();
		//enquanto digitar no teclado
		while(teclado.hasNextLine()){
			String aux = teclado.nextLine();
			if(!aux.equals("/leave")){
			//captura o que o cliente digitar e envia para o servidor
			saida.println(aux);
			}else{
				saida.println(aux);
				break;
			}
		}
		System.out.println("encerrado");
		saida.close();
		teclado.close();
		cliente.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//instancia com o ip porta e nome
		Cliente c = new Cliente("localhost", 5555, "afonso");
		try {
			//chama o metodo paara conectar
			c.connect();
		} catch (ConnectException e) {
			// TODO: handle exception
			System.out.println(utils.getCLIENT_CANNOT_CONNECT());
		}
	}
}