package chatClienteServidor;

public class Utils{
	//Cliente
	private String CLIENT_CANNOT_CONNECT="Incapaz de conectar.";
	private String CLIENT_SERVER_DISCONNECTED="Servidor desconectado.";
	private String CLIENT_MESSAGE_PREFIX="[Eu]";
	//Servidor
	private String SERVER_INVALID_CONTROL_MESSAGE="Mensagen de controle invalida.";
	private String SERVER_NO_CHANNEL_EXISTS="Canal com esse nome nao existe.";
	private String SERVER_JOIN_REQUIRES_ARGUMENT="O comando join deve ser seguido pelo nome da canal.";
	private String SERVER_CLIENT_JOINED_CHANNEL;
	private String SERVER_CLIENT_LEFT_CHANNEL;
	private String SERVER_CHANNEL_EXISTS="O canal nao pode ser criado, pois ja existe uma canal com o mesmo nome.";
	private String SERVER_CREATE_REQUIRES_ARGUMENT="O comando create deve ser seguido pelo nome do canal.";
	private String SERVER_CLIENT_NOT_IN_CHANNEL="Atualmente nao esta em nenhum canal. Deve se juntar a um canal antes de enviar mensagens.";
	private String SERVER_CREATE_CHANNEL="Canal criado com sucesso";
	private String NO_SERVER_CHANNEL_EXISTS="Nao existe canais disponiveis";
	private String CLIENT_DESCONNECTED;
	private String CLIENT_IS_ALREADY_IN_THE_CHANNEL="O cliente ja esta no canal";
	
	public Utils(String nome){
		this.SERVER_CLIENT_JOINED_CHANNEL="O cliente "+nome+" se juntou ao canal.";
		this.SERVER_CLIENT_LEFT_CHANNEL="O cliente "+nome+" deixou o canal.";
		this.CLIENT_DESCONNECTED="O cliente "+nome+" foi desconectado";
	}
	
	public String getCLIENT_CANNOT_CONNECT() {
		return CLIENT_CANNOT_CONNECT;
	}
	public String getCLIENT_SERVER_DISCONNECTED() {
		return CLIENT_SERVER_DISCONNECTED;
	}
	public String getCLIENT_MESSAGE_PREFIX() {
		return CLIENT_MESSAGE_PREFIX;
	}
	public String getSERVER_INVALID_CONTROL_MESSAGE() {
		return SERVER_INVALID_CONTROL_MESSAGE;
	}
	public String getSERVER_NO_CHANNEL_EXISTS() {
		return SERVER_NO_CHANNEL_EXISTS;
	}
	public String getSERVER_JOIN_REQUIRES_ARGUMENT() {
		return SERVER_JOIN_REQUIRES_ARGUMENT;
	}
	public String getSERVER_CLIENT_JOINED_CHANNEL() {
		return SERVER_CLIENT_JOINED_CHANNEL;
	}
	public String getSERVER_CLIENT_LEFT_CHANNEL() {
		return SERVER_CLIENT_LEFT_CHANNEL;
	}
	public String getSERVER_CHANNEL_EXISTS() {
		return SERVER_CHANNEL_EXISTS;
	}
	public String getSERVER_CREATE_REQUIRES_ARGUMENT() {
		return SERVER_CREATE_REQUIRES_ARGUMENT;
	}
	public String getSERVER_CLIENT_NOT_IN_CHANNEL() {
		return SERVER_CLIENT_NOT_IN_CHANNEL;
	}
	public String getSERVER_CREATE_CHANNEL() {
		return SERVER_CREATE_CHANNEL;
	}
	public String getNO_SERVER_CHANNEL_EXISTS() {
		return NO_SERVER_CHANNEL_EXISTS;
	}
	public String getCLIENT_DESCONNECTED(){
		return CLIENT_DESCONNECTED;
	}
	public String getCLIENT_IS_ALREADY_IN_THE_CHANNEL(){
		return CLIENT_IS_ALREADY_IN_THE_CHANNEL;
	}
}