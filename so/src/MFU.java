
public class MFU extends Politica{

	Configuracao config;
	int [] tamMemoria;
	int hits;
	
	public MFU ( Configuracao config ){
		
		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;
		
	}
	
}
