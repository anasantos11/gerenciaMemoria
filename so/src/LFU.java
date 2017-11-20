
public class LFU extends Politica{
	
	Configuracao config;
	int [] tamMemoria;
	int hits;
	
	public LFU ( Configuracao config ){
		
		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;
		
	}

}
