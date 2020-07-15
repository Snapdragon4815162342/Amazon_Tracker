package App;

public class Prodotto {
	String nome;
	String prezzo;
	String link;
	
	public Prodotto() {
	    super();
	    this.nome ="";
	    this.prezzo = "";
	    this.link = "";
	    //this.linkImmagine = "";
	    
	  }
	
	public Prodotto(String nome, String prezzo, String link) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
		this.link = link;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getLinkn() {
		return link;
	}

	public void setLink(String data_asin) {
		this.link = link;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		return true;
	}

	@Override
	
	public String toString() {
		return "Prodotto [nome=" + nome + ", prezzo=" + prezzo + ", link=" + link + "]";
	}
	
	

}
