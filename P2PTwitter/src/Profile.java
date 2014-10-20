public class Profile {

	private String ip;
	private String pseudo;
	private String unikey;
	private String message;
	private long latestDate;

	Profile(String ip, String pseudo, String unikey) {
		this.ip = ip;
		this.pseudo = pseudo;
		this.unikey = unikey;
	}

	void setIp(String ip) {
		this.ip = ip;
	}

	void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	void setUnikey(String unikey) {
		this.unikey = unikey;
	}

	void setDate(long newDate) {
		this.latestDate = newDate;
	}

	void setMessage(String message) {
		this.message = message;
	}

	String getIp() {
		return ip;
	}

	String getPseudo() {
		return pseudo;
	}

	String getUnikey() {
		return unikey;
	}

	String getMessage() {
		return message;
	}

	long getDate() {
		return latestDate;
	}

}
