import java.io.RandomAccessFile;

public class app {
	
	public static void main (String [] args ) throws Exception{
		
		Arquivo a = new Arquivo();
		
		Configuracao config = a.gerarConfiguracao();
		
		//System.out.println(config.getPaginasProcessos());
		
		FIFO f1 = new FIFO (config);
		
		f1.testeFIFO();
			
		}

}
