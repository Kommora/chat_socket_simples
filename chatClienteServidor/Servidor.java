package chatClienteServidor;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.io.*;

@SuppressWarnings("all")
public class Servidor {

	private int porta;
	private ArrayList<Usuarios> clientes;
	private HashMap<String, ArrayList> canais;

	public Servidor(int porta){
		this.porta = porta;
		this.clientes = new ArrayList<>();
		this.canais = new HashMap<>();
	}

	public void executa() throws IOException{
		//cria um socket com a porta
		ServerSocket servidor = new ServerSocket(this.porta);
		//exibe no servidor que a conexao foi estabelecida
		System.out.println("conexao aberta com a porta:"+servidor.getLocalPort()+"\n");
		//cria um canal invisivel para os novos clientes
		canais.put("", clientes);
		while(!false){
			//espera o cliente se conectar
			Socket cliente = servidor.accept();
			//cria um objeto para receber as mensagens do cliente
			Scanner scan = new Scanner(cliente.getInputStream());
			//quando um cliente se conectar exibi o ip dele
			System.out.println("conectado com o cliente: "+cliente.getInetAddress().getHostAddress());
			//cria um objeto para armazenar:
			//-O nome
			//-O canal padrao do cliente
			//-O ip
			//-O recebimento dos dados
			//-O envio dos dados
			Usuarios usuario = new Usuarios(scan.nextLine() , "",cliente.getInetAddress().getHostAddress(),cliente.getInputStream(),cliente.getOutputStream());
			//cria um objeto que vai tratar as mensagens enviadas	
			TratarCliente tc = new TratarCliente(usuario,this);
			//adiciona o usuario em uma lista
			this.clientes.add(usuario);
			//cria uma thread para o usuario
			new Thread(tc).start();
		}
	}
	//metodo para enviar mensagens para os clientes no canal
	public void mandarMensagem(String msg, String canal, String nome) throws IOException{
		//cria um laco para percorrer a lista de clientes em determinado canal
		for(int i=0;i<this.canais.get(canal).size();i++){
			//faz um cast para saber quando o for o usuario que mandou a mensagen
			if(((Usuarios)this.canais.get(canal).get(i)).getNome().equals(nome)){
				//manda um mensagen para o cliente
				//se for o proprio cliente exibe o EU em vez do nome com a mensagen
				new PrintStream(((Usuarios)this.canais.get(canal).get(i)).getSaida())
				.println("[EU] "+msg);

			}else{
				//senao exibe o nome com a mensagen
				new PrintStream(((Usuarios)this.canais.get(canal).get(i)).getSaida())
				.println("["+nome+"] "+msg);
			}
		}

	}
	//lista os canais disponiveis
	public String listarCanais(){
		//variavel auxiliar para gurdar os canais
		String aux="";
		//laco para percorrer os canais
		for(String canais: this.canais.keySet()){
			//se for o canal é diferente do canal invisivel adiciona
			if(canais!=""){
				aux+=(canais+"\n");
			}
		}
		//se não achar canais retorna null
		if(aux.equals("")){
			return null;
		}else{
			//senao retorna a variavel auxiliar
			return aux;
		}
	}

	//muda o cliente para outro canal
	public String mudarCanal(String novoCanal,String canal, String nome, String ip){
		//se nao conter o canal para mudar retorna null
		if(!this.canais.containsKey(novoCanal)){
			return null;
		}else{
			//cria uma variavel auxiliar para guardar a lista de clientes em determinado canal
			List aux = this.canais.get(canal);
			//laco para percorrer a lista
			for(int i=0;i<aux.size();i++){
				//se o nome e o ip for correspondente
				if(((Usuarios)aux.get(i)).getNome().equals(nome) && ((Usuarios)aux.get(i)).getHost().equals(ip)){
					//adiciona o cliente para a lista do canal
					this.canais.get(novoCanal).add((Usuarios)aux.get(i));
					//remove o cliente do canal antigo
					this.canais.get(canal).remove(i);
					//para o laco
					break;
				}
			}
			//retorna o novo canal
			return novoCanal;
		}
	}
	//metodo para criar canais
	public boolean criarCanal(String canal,OutputStream u){
		//se ja existir o canal
		if(this.canais.containsKey(canal)){
			return false;
		}else{
			//senao coloca o novo canal e cria uma nova lista de usuarios
			this.canais.put(canal, new ArrayList<Usuarios>());
			return true;
		}
	}
	
	//metodo para desconectar o cliente
	public void desconectar(Usuarios u){
		//remove o clinte de determinado canal
		this.canais.get(u.getCanal()).remove(u);
	}

	//MAIN
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//instancia servidor com a porta
		Servidor s = new Servidor(5555);
		try {
			//chama o metodo para executar
			s.executa();
		} catch (BindException e) {
			// TODO: handle exception
			System.out.println("Servidor ja aberto");
		}
	}
}
