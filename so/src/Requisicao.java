
public class Requisicao {
	
	private String processo;
	private int pagina;
	
	public Requisicao(String pro,int pag){
		
		this.processo = pro;
		this.pagina = pag;
		
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

}
