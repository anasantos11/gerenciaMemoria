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
		MY m = new MY (configuracoes.get(5));


		fif.Processar();
		int fifo = fif.hits;
		
		op.Processar();
		int opt = op.getHits();

		lr.Processar();
		int lru = lr.hits;
		
		mf.Processar();
		int mfu = mf.hits;
		
		lf.Processar();
		int lfu = lf.hits;
		
		m.Processar();
		int my = m.hits;

		int requisicoes = configuracoes.get(0).getRequisicoes().size();
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		String fifoOut = df.format((double)(requisicoes -fifo)/requisicoes);
		String optOut = df.format((double)(requisicoes - opt)/requisicoes);
		String lruOut = df.format((double)(requisicoes -lru)/requisicoes);
		String lfuOut = df.format((double)(requisicoes -lfu)/requisicoes);
		String mfuOut = df.format((double)(requisicoes - mfu)/requisicoes);
		String myOut = df.format((double)(requisicoes -my)/requisicoes);

		
		
		System.out.println("Requisicoes=" + requisicoes );
		System.out.println("TaxasDeErros:");
		System.out.println("FIFO=" + fifoOut);
		System.out.println("OPT=" + optOut );
		System.out.println("LRU=" + lruOut);
		System.out.println("LFU=" + lfuOut);
		System.out.println("MFU=" + mfuOut);
		System.out.println("MY=" + myOut);
		
		
		String resposta = "Requisicoes=" + requisicoes + "\n"+
					"TaxasDeErros:" +  "\n"+
					"FIFO=" + fifoOut +  "\n"+
					"OPT=" + optOut +  "\n"+
					"LRU=" + lruOut +  "\n"+
					"LFU=" + lfuOut +  "\n"+
					"MFU=" + mfuOut+  "\n"+
					"MY=" + myOut;
		Arquivo.criarArquivoResposta(resposta);

					
		}

}
