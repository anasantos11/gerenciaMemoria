import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Configuracao {
	
	private int qtdQuadros;
	private int qtdProcessos;
	
	private Map<String, Processo> paginasProcessos;
	private ArrayList <Processo> processos;
	
	private List<Requisicao> requisicoes;
	
	private Politica politica;
	private String tipoAlocacao;
	private String tipoSubstituicao;

	public Configuracao() {
		setQtdQuadros(-1);
		setPaginasProcessos(new LinkedHashMap<String, Processo>());
		setTipoAlocacao(null);
		setTipoSubstituicao(null);
		setRequisicoes( new ArrayList<Requisicao>());
		this.processos = new ArrayList <Processo>();
	}

	public Configuracao(int qtdQuadros, LinkedHashMap<String, Processo> paginasProcesso, String tipoAlocacao,
			String tipoSubstituicao) {
		setQtdQuadros(qtdQuadros);
		setPaginasProcessos(paginasProcesso);
		setTipoAlocacao(tipoAlocacao);
		setTipoSubstituicao(tipoSubstituicao);
		setRequisicoes(requisicoes);
		this.processos = new ArrayList <Processo>();

	}
	
	public int [] gerarQuadros (){
		int [] tamQuadro = new int [this.getQtdProcessos()];
		int i = 0;
		if ( tipoAlocacao.compareTo("Igual") == 0){// Alocacao igual
		
			int temp = this.qtdQuadros/this.qtdProcessos;
			
			if (temp < 0 ){// quadro de memoria < 0
				
				for ( i = 0 ; i < this.getQtdProcessos() ; i++){// preenche cada memoria igual com 1
					
					tamQuadro[i] = 1;
					
				}
				
				return tamQuadro;
				
			}else{
				
				for ( i = 0 ; i < this.getQtdProcessos() ; i++){ //preenche memoria com tamanho de cada um com o temp
					
					tamQuadro[i] = temp;
					
				}
				
				return tamQuadro;
			}
		}else{// Alocacao proporcional
			
			int totalPaginas = 0;
			
			for( Processo e : processos){
				
				totalPaginas+= e.getTotalPaginas();
				
			}
			
			for ( i = 0 ; i < this.getQtdProcessos() ; i++){
				
				double temp = (double)this.getProcessos().get(i).getTotalPaginas()/totalPaginas;
				tamQuadro[i] = (int)((double)temp*this.getQtdQuadros());
				
				if (tamQuadro[i] < 0 )// garante pelo menos um quadro!
					tamQuadro[i] = 1;
				
			}
			
			return tamQuadro;
			
		}
		
		
	}

	/**
	 * Getters and Setters
	 */
	public int getQtdQuadros() {
		return qtdQuadros;
	}

	public void setQtdQuadros(int qtdQuadros) {
		this.qtdQuadros = qtdQuadros;
	}

	public Map<String, Processo> getPaginasProcessos() {
		return paginasProcessos;
	}

	public void setPaginasProcessos(Map<String, Processo> paginasProcessos) {
		this.paginasProcessos = paginasProcessos;
	}

	public String getTipoAlocacao() {
		return tipoAlocacao;
	}

	public void setTipoAlocacao(String tipoAlocacao) {
		this.tipoAlocacao = tipoAlocacao;
	}

	public String getTipoSubstituicao() {
		return tipoSubstituicao;
	}

	public void setTipoSubstituicao(String tipoSubstituicao) {
		this.tipoSubstituicao = tipoSubstituicao;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	public void addPaginasProcessos(String nomeProcesso, int totalPaginas) {
		Processo p = new Processo(nomeProcesso, totalPaginas);
		paginasProcessos.put(nomeProcesso, p);
		this.setProcessos(p);
	}
	
	public void addSequencia(String processo, int valor) {
		requisicoes.add(new Requisicao(processo, valor));
	}

	@Override
	public String toString() {
		return "Configuracao [qtdQuadros=" + qtdQuadros + "\n paginasProcessos=" + paginasProcessos.toString()
				+ "\n tipoAlocacao=" + tipoAlocacao + "\n tipoSubstituicao=" + tipoSubstituicao + "\n requisicoes=\n"
				+ requisicoes.toString();
	}

	public Politica getPolitica() {
		return politica;
	}

	public void setPolitica(Politica politica) {
		this.politica = politica;
	}

	public int getQtdProcessos() {
		return qtdProcessos;
	}

	public void setQtdProcessos(int qtdProcessos) {
		this.qtdProcessos = this.qtdProcessos + qtdProcessos;
	}

	public ArrayList <Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Processo p) {
		this.processos.add(p);
	}



}
