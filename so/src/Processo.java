public class Processo {
	private String nomeProcesso;
	private int paginaProcesso;

	public Processo(String nomeProcesso, int paginasProcesso) {
		setNomeProcesso(nomeProcesso);
		setPaginasProcesso(paginasProcesso);
	}
	public Processo() {
		setNomeProcesso(null);
		setPaginasProcesso(-1);
	}

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public int getPaginasProcesso() {
		return paginaProcesso;
	}

	public void setPaginasProcesso(int paginasProcesso) {
		this.paginaProcesso = paginasProcesso;
	}
	@Override
	public String toString() {
		return "Processo [nomeProcesso=" + nomeProcesso + ", paginaProcesso=" + paginaProcesso + "]";
	}

}
