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

	public void OPTLocal() {
		TreeMap<String, Queue<?>> mapQuadros = new TreeMap<String, Queue<?>>();
		TreeMap<String, Integer> mapQuadrosMax = new TreeMap<String, Integer>();
		Map<String, LinkedList<Object>> requisicoesFuturas = new TreeMap<String, LinkedList<Object>>();

		// Criando Maps para todos os processos que serao processados
		for (int i = 0; i < qtdProcessos; i++) {
			mapQuadros.put(processos.get(i).getNomeProcesso(), new LinkedList<Object>());
			requisicoesFuturas.put(processos.get(i).getNomeProcesso(), new LinkedList<Object>());
			mapQuadrosMax.put(processos.get(i).getNomeProcesso() , tamMemoria[i]);
		}
		System.out.println("Total de quadros por processo" + mapQuadrosMax);

		// Colocando todas as requisicoes em uma lista para saber as requisicoes futuras
		for (Requisicao requisicao : requisicoes) {
			requisicoesFuturas.get(requisicao.getProcesso()).add(requisicao.getPagina());
		}
		System.out.println("Lista de requisicoes futuras" + requisicoesFuturas);

		// Realizar processamento das paginas
		
		for (Requisicao requisicao : requisicoes) {
			@SuppressWarnings("unchecked")
			LinkedList<Integer> quadro = (LinkedList<Integer>) mapQuadros.get(requisicao.getProcesso());
			
			
		if(quadro.contains(requisicao.getPagina())) {
			hits++;
			requisicoesFuturas.get(requisicao.getProcesso()).remove();
			System.out.println("Requisicoes Futuras apos remocao" + requisicoesFuturas);
			System.out.println("HIT na requisicao da pagina" + requisicao.getPagina());
		}else {
			if(quadro.size() <  mapQuadrosMax.get(requisicao.getProcesso()) ) {
				quadro.add(requisicao.getPagina());
				System.out.println("Pagina adicionada sem remocao" + requisicao.getPagina());
			}else {
				
				
			}			
		}
			
			
		}

		/*int i = 0;
		for (Requisicao req : requisicoes) {
			@SuppressWarnings("unchecked")
			LinkedList<Integer> queue = (LinkedList<Integer>) mapQuadros.get(req.getProcesso());

			if (queue.size() < mapQuadrosMax.get(req.getProcesso())) {
				if (mapQuadros.get(req.getProcesso()).contains(req.getPagina())) {
					i++;
					hits++;
					// System.out.println( i + " HIT em " + req.getPagina() +" " + map);

				} else {
					queue.add(req.getPagina());
					i++;
					// System.out.println( i + " ADD " + map);
				}
			} else {
				if (mapQuadros.get(req.getProcesso()).contains(req.getPagina())) {
					i++;
					hits++;
					// System.out.println( i + " HIT em " + req.getPagina() +" " + map);
				} else {
					i++;
					queue.remove();
					queue.add(req.getPagina());
					// System.out.println( i + " REM " + map);
				}
			}
		}*/
	}

}
