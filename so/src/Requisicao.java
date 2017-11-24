public class Requisicao implements Comparable<Requisicao>{
	
	private String processo;
	private int pagina;
	private int hits;
	private int cont;
	
	public Requisicao(String pro,int pag){
		
		this.processo = pro;
		this.pagina = pag;
		
	}
	
	protected Requisicao(){
		
	}
	
	protected Requisicao(Requisicao c){
		this.processo = c.getProcesso();
		this.pagina = c.getPagina();
		this.hits = c.getHits();
		this.cont = c.getCont();
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
		return "" + processo + " , " + pagina + " , " + hits + " , " + cont + "\n";
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
	@Override
	public int compareTo(Requisicao reqs){

		if (this.hits == reqs.getHits()){
			if (this.cont > reqs.getCont() )
				return -1;  
			else
				return 1;
		}else if (this.hits > reqs.getHits() )
			return 1;
		else
			return -1;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits += hits;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont += cont;
	}

}
