package chatClienteServidor;

import java.io.PrintStream;
import java.util.Scanner;

public class TratarCliente implements Runnable {
	private Servidor servidor;
	private Usuarios cliente;
	private Utils utils;

	public TratarCliente(Usuarios cliente,Servidor servidor){
		this.cliente=cliente;
		this.servidor=servidor;
		this.utils = new Utils(this.cliente.getNome());
	}

	public void run(){
		//cria um objeto para capturar as mensagens enviadas do cliente
		Scanner scan = new Scanner(this.cliente.getEntrada());
		try{
			//enquanto digitar
			while(scan.hasNextLine()){
				//cria uma variavel auxiliar
				String aux = scan.nextLine();
				//se comecar co uma / he um comando
				if(aux.startsWith("/")){
					//cria um objeto para guardar a mensagens separada
					String split[] = new String[2];
					//separa por espaco
					split=aux.split(" ");
					//se aprimeira mensagens for o /create
					if(split[0].equals("/create")){
						//se o tamanho for igual a um
						if(split.length==1){
							//envia uma mensagem que o comando falta parametros
							new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_CREATE_REQUIRES_ARGUMENT());
						}else{
							//senao armazena o retorno
							boolean verifica = servidor.criarCanal(split[1], this.cliente.getSaida());
							//se for verdadeiro
							if(verifica==true){
								//exibe uma mensagem que o canal foi criado
								new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_CREATE_CHANNEL());
							}else{
								//senao envia uma mensagem que o canal ja existe
								new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_CHANNEL_EXISTS());
							}
						}
					//se a mensagem for um /list e o tamanho for igual a um
					}else if(split[0].equals("/list") && split.length==1){
						//guarda o retorno do metodo
						String verifica = servidor.listarCanais();
						//se for diferente de null quer dizer que existem canais disponiveis
						if(verifica!=null){
							new PrintStream(this.cliente.getSaida()).println(verifica);
						}else{
							//senao exibe uma mensagem que nao existe canais disponiveis
							new PrintStream(this.cliente.getSaida()).println(this.utils.getNO_SERVER_CHANNEL_EXISTS());
						}
					//se for igual a /join
					}else if(split[0].equals("/join")){
						//verifica se o tamanho he igual a um
						if(split.length==1){
							//exibi uma mensagem que o comando requer mais argumentos
							new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_JOIN_REQUIRES_ARGUMENT());
						//se o canal atual do cliente for igual ao canal passado
						}else if(this.cliente.getCanal().equals(split[1])){
							//exibe uma mensagem que o cliente ja esta no canal
							new PrintStream(this.cliente.getSaida()).println(this.utils.getCLIENT_IS_ALREADY_IN_THE_CHANNEL());
						//senao
						}else{
							//recebe o retorno do metodo
							String verifica = servidor.mudarCanal(split[1],this.cliente.getCanal(),this.cliente.getNome(),this.cliente.getHost());
							//se o retorno for diferente de null
							if(verifica!=null){
								//se o canal for diferente do canal invisivel
								if(!this.cliente.getCanal().equals("")){
									//exibe uma mensagem que o determinado cliente deixou o canal
									this.servidor.mandarMensagem(this.utils.getSERVER_CLIENT_LEFT_CHANNEL(), this.cliente.getCanal(), this.cliente.getNome());
								}
								//guarda o novo canal do cliente
								this.cliente.setCanal(verifica);
								//envia uma mensagem que o cliente se juntou ao canal
								this.servidor.mandarMensagem(this.utils.getSERVER_CLIENT_JOINED_CHANNEL(), this.cliente.getCanal(), this.cliente.getNome());
							}else{
								//o canal nao existe para se juntar
								new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_NO_CHANNEL_EXISTS());
							}
						}
					//se a mensagem for igual a /leave
					}else if(aux.equals("/leave")){
						//exibe uma mensagem no canal que o cliente se desconectou
						servidor.mandarMensagem(this.utils.getCLIENT_DESCONNECTED(), this.cliente.getCanal(), this.cliente.getNome());
						//desconecta o cliente
						servidor.desconectar(this.cliente);
						//para a thread do clinente
						break;
					}else{
						//caso o comando estaja errado exibe uma mensagem de comando invalido
						new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_INVALID_CONTROL_MESSAGE());
					}
					//se nao for uma mensagem de comando
				}else{
					//se o canal for diferente do canal invisivel
					if(!this.cliente.getCanal().equals("")){
						//exibe a mensagem para todos do canal
						this.servidor.mandarMensagem(aux, this.cliente.getCanal(), this.cliente.getNome());
					}else{
						//senao exibe uma mensagem que o cliente ainda nao esta em nenhum canal
						new PrintStream(this.cliente.getSaida()).println(this.utils.getSERVER_CLIENT_NOT_IN_CHANNEL());
					}
				}
			}

			scan.close();
		}catch(Exception e){

		}
	}
}
