public class Profile {

	private String ip;
	private String pseudo;
	private String unikey;
	private String status;
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

	void setStatus(String status) {
		this.status = status;
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

	String getStatus() {
		return status;
	}

	long getDate() {
		return latestDate;
	}

}
