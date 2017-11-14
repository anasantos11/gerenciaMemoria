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

}
