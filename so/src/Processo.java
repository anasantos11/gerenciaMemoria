public class Processo {
	private String nomeProcesso;
	private int totalPaginas;

	public Processo(String nomeProcesso, int paginasProcesso) {
		setNomeProcesso(nomeProcesso);
		setTotalPaginas(paginasProcesso);
	}
	public Processo() {
		setNomeProcesso(null);
		setTotalPaginas(-1);
	}

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int paginasProcesso) {
		this.totalPaginas = paginasProcesso;
	}
	@Override
	public String toString() {
		return "Processo [nomeProcesso=" + nomeProcesso + ", totalPaginas=" + totalPaginas + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeProcesso == null) ? 0 : nomeProcesso.hashCode());
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
		Processo other = (Processo) obj;
		if (nomeProcesso == null) {
			if (other.nomeProcesso != null)
				return false;
		} else if (!nomeProcesso.equals(other.nomeProcesso))
			return false;
		return true;
	}
	
}
