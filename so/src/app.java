import java.text.DecimalFormat;
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


		
		FIFO fif = new FIFO (configuracoes.get(0));
		OPT op = new OPT (configuracoes.get(1));
		LRU lr = new LRU (configuracoes.get(2));
		LFU lf = new LFU (configuracoes.get(3));
		MFU mf = new MFU (configuracoes.get(4));
		MY my = new MY (configuracoes.get(5));


		fif.FifoLocal();
		int fifo = fif.hits;
		
		op.OPTLocal();
		int opt = op.getHits();

		lr.LruLocal();
		int lru = lr.hits;

		int requisicoes = configuracoes.get(0).getRequisicoes().size();
		
		DecimalFormat df = new DecimalFormat("#.##");
		String fifoOut = df.format((double)(requisicoes -fifo)/requisicoes);
		String optOut = df.format((double)(requisicoes - opt)/requisicoes);
		String lruOut = df.format((double)(requisicoes -lru)/requisicoes);
		//String lfuOut = df.format((double)(requisicoes -lfu)/requisicoes);
		//String mfuOut = df.format((double)(requisicoes -fifo)/requisicoes);
		//String myOut = df.format((double)(requisicoes -fifo)/requisicoes);

		
		
		System.out.println("Requisições=" + requisicoes );
		System.out.println("TaxasDeErros:");
		System.out.println("FIFO=" + fifoOut);
		System.out.println("OPT=" + optOut );
		System.out.println("LRU=" + lruOut);
		System.out.println("LFU=" + 0);
		System.out.println("MFU=" + 0);
		System.out.println("MY=" + 0);

		


					
		}

}
