import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class MFU extends Politica {

	Configuracao config;
	int[] tamMemoria;
	int hits;

	public MFU(Configuracao config) {

		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;

	}

	public void Processar() {

		if (this.config.getTipoSubstituicao().equals("Global")) {

			this.Global();

		} else {

			this.Local();

		}

	}

	public void Global() {

		int i;

		int espaco = 0;

		for (i = 0; i < tamMemoria.length; i++) {

			espaco += tamMemoria[i];

		}

		i = 0;

		hits = 0;

		LinkedList<RequisicaoMFU> queue = new LinkedList<RequisicaoMFU>();
		
		for (Requisicao req : config.getRequisicoes()) {
			
			RequisicaoMFU mfu = new RequisicaoMFU (req);

			if (queue.size() < espaco) {// memoria nao cheia

				if (queue.contains(mfu)) {

					i++;
					hits++;
					for (RequisicaoMFU r : queue) {

						r.setCont(1);

						if (mfu.equals(r)) {

							r.setHits(1);

						}

					}

					//System.out.println(i + " HIT em " + mfu.getPagina() + "  " + queue);

				} else {

					queue.add(mfu);
					i++;
					//System.out.println(i + " ADD " + queue);

					for (RequisicaoMFU r : queue) {

						r.setCont(1);

					}

				}

			} else {// memoria cheia

				if (queue.contains(mfu)) {

					i++;
					hits++;

					for (RequisicaoMFU r : queue) {

						r.setCont(1);
						if (mfu.equals(r)) {

							r.setHits(1);

						}

					}

					//System.out.println(i + " HIT em " + mfu.getPagina() + "  " + queue);

				} else {
					// ESTA CHEIO E PRECISA REMOVER
					i++;

					queue.remove(Collections.max(queue));
					queue.add(mfu);
					//System.out.println(i + " REM " + queue);

					for (RequisicaoMFU r : queue) {

						r.setCont(1);

					}

				}
			}

		}

		//System.out.println(queue);
		//System.out.println(hits);

	}

	public void Local() {

		int i;

		TreeMap<String, LinkedList<?>> map = new TreeMap<String, LinkedList<?>>();
		TreeMap<String, Integer> mapMax = new TreeMap<String, Integer>();

		for (i = 0; i < config.getQtdProcessos(); i++) {

			map.put(config.getProcessos().get(i).getNomeProcesso(), new LinkedList<RequisicaoMFU>());
			mapMax.put(config.getProcessos().get(i).getNomeProcesso(), tamMemoria[i]);

		}

		i = 0;
		hits = 0;

		for (Requisicao req : config.getRequisicoes()) {
			
			RequisicaoMFU mfu = new RequisicaoMFU (req);

			@SuppressWarnings("unchecked")
			LinkedList<RequisicaoMFU> queue = (LinkedList<RequisicaoMFU>) map.get(mfu.getProcesso());

			if (queue.size() < mapMax.get(mfu.getProcesso())) {// memoria nao
																// cheia

				if (map.get(mfu.getProcesso()).contains(mfu)) {

					i++;
					hits++;
					for (RequisicaoMFU r : queue) {

						r.setCont(1);

						if (mfu.equals(r)) {

							r.setHits(1);

						}

					}
					// System.out.println( i + " HIT em " + mfu.getPagina() +" " + map);

				} else {

					queue.add(mfu);
					i++;
					 //System.out.println( i + " ADD " + map);

					for (RequisicaoMFU r : queue) {

						r.setCont(1);

					}

				}

			} else {// memoria cheia

				if (map.get(mfu.getProcesso()).contains(mfu)) {

					i++;
					hits++;

					for (RequisicaoMFU r : queue) {

						r.setCont(1);
						if (mfu.equals(r)) {

							r.setHits(1);

						}

					}

					//System.out.println( i + " HIT em " + mfu.getPagina() +" "  + map);

				} else {
					// ESTA CHEIO E PRECISA REMOVER
					i++;

					queue.remove(Collections.max(queue));
					queue.add(mfu);
					// System.out.println( i + " REM " + map);

					for (RequisicaoMFU r : queue) {

						r.setCont(1);

					}

				}
			}

		}

		//System.out.println(map);
		//System.out.println(hits);

	}

}
