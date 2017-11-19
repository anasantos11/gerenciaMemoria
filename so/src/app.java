import java.util.ArrayList;
import java.util.LinkedList;

public class app {
	
	public static void main (String [] args ) throws Exception{
		
		LinkedList <Arquivo> arquivos = new LinkedList<Arquivo>();
		ArrayList <Configuracao> configuracoes = new ArrayList<Configuracao>();
		
		for (int i = 0; i < 6 ; i++){
			
			arquivos.add( new Arquivo () );
			configuracoes.add(arquivos.remove().gerarConfiguracao());

		}
		
		System.gc();


		
		FIFO f1 = new FIFO (configuracoes.get(0));
		OPT opt1 = new OPT (configuracoes.get(1));
		LRU l1 = new LRU (configuracoes.get(2));
		//LFU f1 = new LFU (configuracoes.get(3));
		//MFU f1 = new MFU (configuracoes.get(4));
		//MY f1 = new MY (configuracoes.get(4));


		f1.FifoLocal();
		int fifo = f1.hits;
		
		opt1.OPTLocal();
		int opt = opt1.getHits();

		l1.LruLocal();
		int lru = l1.hits;

		int requisicoes = configuracoes.get(0).getRequisicoes().size();
		
		System.out.println("Requisições=" + requisicoes );
		System.out.println("TaxasDeErros:");
		System.out.println("FIFO=" + (double)(requisicoes -fifo)/requisicoes);
		System.out.println("OPT=" + (double)(requisicoes - opt)/requisicoes);
		System.out.println("LRU=" + (double)(requisicoes -lru)/requisicoes);
		System.out.println("LFU=" + 0);
		System.out.println("MFU=" + 0);
		System.out.println("MY=" + 0);

		


					
		}

}
