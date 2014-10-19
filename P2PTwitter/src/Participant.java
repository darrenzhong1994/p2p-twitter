
public class Participant {

	private String unikey;
	private String pseudo;
	private String ip;
	
	Participant (String unikey, String pseudo, String ip) {
		this.unikey = unikey;
		this.pseudo = pseudo;
		this.ip = ip;
	}
	
	void setUnikey(String unikey) {
		this.unikey = unikey;
	}
	
	void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	void setIp(String ip) {
		this.ip = ip;
	}
	
	String getUnikey() {
		return unikey;
	}
	
	String getPseudo() {
		return pseudo;
	}

	String getIp() {
		return ip;
	}
}
