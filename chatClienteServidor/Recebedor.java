package chatClienteServidor;

import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {

	private InputStream servidor;

	public Recebedor(InputStream servidor){
		this.servidor = servidor;
	}
	public void run(){
		
		//cria um objeto para capturar o que o servidor enviar
		Scanner scan = new Scanner(this.servidor);
		while(scan.hasNextLine()){
			String aux = scan.nextLine();
			if(!aux.equals("/leave")){
			//exibe o que o servidor enviar
			System.out.println(aux);
			}else{
				break;
			}
		}
		scan.close();
	}
}