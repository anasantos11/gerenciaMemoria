import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class OPT extends Politica {

	private Configuracao config;
	private int[] tamMemoria;
	private int hits;
	private int qtdProcessos;
	private List<Requisicao> requisicoes;
	private ArrayList<Processo> processos;

	public OPT(Configuracao config) {
		this.config = config;
		this.tamMemoria = config.gerarQuadros();
		this.hits = 0;
		this.qtdProcessos = config.getQtdProcessos();
		this.requisicoes = config.getRequisicoes();
		this.processos = config.getProcessos();
	}
	
	public void Processar (){
		
		if ( this.config.getTipoSubstituicao().equals("Global") ){
			
			this.Global();
			
		}else{
			
			this.Local();
			
		}
		
	}
	
	public void Local() {
		TreeMap<String, Queue<?>> mapQuadros = new TreeMap<String, Queue<?>>();
		TreeMap<String, Integer> mapQuadrosMax = new TreeMap<String, Integer>();
		Map<String, LinkedList<Integer>> requisicoesFuturas = new TreeMap<String, LinkedList<Integer>>();

		// Criando Maps para todos os processos que serao processados
		for (int i = 0; i < qtdProcessos; i++) {
			mapQuadros.put(processos.get(i).getNomeProcesso(), new LinkedList<Object>());
			requisicoesFuturas.put(processos.get(i).getNomeProcesso(), new LinkedList<Integer>());
			mapQuadrosMax.put(processos.get(i).getNomeProcesso(), tamMemoria[i]);
		}
		System.out.println("Total de quadros por processo" + mapQuadrosMax);

		// Colocando todas as requisicoes em uma lista para saber as requisicoes futuras
		for (Requisicao requisicao : requisicoes) {
			requisicoesFuturas.get(requisicao.getProcesso()).add(requisicao.getPagina());
		}
		System.out.println("Lista de requisicoes futuras" + requisicoesFuturas);

		System.out.println("\n \n \n");
		// Realizar processamento das paginas

		for (Requisicao requisicao : requisicoes) {
			@SuppressWarnings("unchecked")
			LinkedList<Integer> quadro = (LinkedList<Integer>) mapQuadros.get(requisicao.getProcesso());

			if (quadro.contains(requisicao.getPagina())) {
				hits++;
				removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
				System.out.println("HIT - " + requisicao + " " + mapQuadros);
			} else {
				if (quadro.size() < mapQuadrosMax.get(requisicao.getProcesso())) {
					quadro.add(requisicao.getPagina());
					removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
					System.out.println("Pagina adicionada SEM remocao - " + requisicao + " " + mapQuadros);
				} else {
					int indicePag = 0;
					if (quadro.size() > 1) {
						indicePag = quadro.indexOf(retirarPagina(quadro, requisicoesFuturas, requisicao.getProcesso()));
						if(indicePag == -1) {
							indicePag = 0;
						}
							
					}
					quadro.remove(indicePag);
					quadro.add(indicePag, requisicao.getPagina());
					removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
					System.out.println("Pagina adicionada COM substituicao - " + requisicao + " " + mapQuadros);
				}
			}

		}

	}
	
	public void Global (){
		
		
		
	}

	public void removerRequisicaoFutura(Map<String, LinkedList<Integer>> requisicoesFuturas, String processo) {
		requisicoesFuturas.get(processo).remove();
		//System.out.println("Nova lista de requisicoes futuras" + requisicoesFuturas);
	}

	public int retirarPagina(LinkedList<Integer> quadro, Map<String, LinkedList<Integer>> requisicoesFuturas,
			String processo) {

		Map<Integer, Integer> indicesPagFuturas = new TreeMap<Integer, Integer>();
		LinkedList<Integer> paginasSemRequisicaoFuturo = new  LinkedList<Integer>();
		paginasSemRequisicaoFuturo.addAll(quadro);
		
				
		for (Integer pagina : requisicoesFuturas.get(processo)) {
			if (quadro.contains(pagina) && !indicesPagFuturas.containsValue(pagina)) {
				indicesPagFuturas.put(requisicoesFuturas.get(processo).indexOf(pagina), pagina);
				paginasSemRequisicaoFuturo.remove(paginasSemRequisicaoFuturo.indexOf(pagina));
			}
		}
		if(paginasSemRequisicaoFuturo.size() > 0) {
			return paginasSemRequisicaoFuturo.getFirst();
		}else {
			LinkedList<Integer> x = new LinkedList<Integer>();
			x.addAll(indicesPagFuturas.values());

			System.out.println("Indices das paginas " + indicesPagFuturas);
			if (x.size() > 0) {
				return x.getLast();
			} else {
				return -1;
			}
		}
		

	}

	public int getHits() {
		return hits;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}
	
	
}
