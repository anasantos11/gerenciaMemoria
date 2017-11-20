
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
	
	@Override
	public String toString() {
		return "" + processo + " , " + pagina + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pagina;
		result = prime * result + ((processo == null) ? 0 : processo.hashCode());
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
		Requisicao other = (Requisicao) obj;
		if (pagina != other.pagina)
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
			return false;
		return true;
	}

	public int compareTo(ReqEspecial req) {
		// TODO Auto-generated method stub
		return 0;
	}

}
