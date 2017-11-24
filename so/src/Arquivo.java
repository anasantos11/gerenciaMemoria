import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	private String nomeArquivo;
	private String linhaArquivo = "";
	private String[] linha;
	private List<String> lista = new ArrayList<String>();

	public Arquivo() throws Exception {
		this.nomeArquivo = pegarArquivo();
	}

	/**
	 * Metodo para pegar um arquivo em algum diretorio da maquina
	 * 
	 * @return nomeArquivo
	 * @throws Exception
	 */

	public static String pegarArquivo() throws Exception {
		
		/*JFileChooser arquivo = new JFileChooser();
		int retorno = arquivo.showOpenDialog(null);
		if (retorno == JFileChooser.CANCEL_OPTION) {
			throw new Exception("Processo cancelado");
		}
		String arquivoSelecionado = arquivo.getSelectedFile().getPath();
		return arquivoSelecionado;*/
	
	
		
		//return "/Users/carlos/git/gerenciaMemoria/so/src/entrada.txt";
		return "src/entrada.txt";
	}
	
	/**
	 * Metodo que criara arquivo de resposta a partir de uma String recebida
	 * 
	 * @param resposta
	 * @throws Exception
	 */

	public static void criarArquivoResposta(String resposta) throws Exception {
		/*JFileChooser arquivo = new JFileChooser();
		int retorno = arquivo.showSaveDialog(null);
		if (retorno == JFileChooser.CANCEL_OPTION) {
			throw new Exception("Processo cancelado");
		}
		String pasta = arquivo.getSelectedFile().getPath();*/
		
		File file = new File("src/saida.txt" );
		file.delete();
		RandomAccessFile rw = new RandomAccessFile(new File("src/saida.txt"), "rw");
		rw.writeBytes(resposta);
		rw.close();

	}
	
	public Configuracao gerarConfiguracao() throws IOException {
		
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");
		
		Configuracao config = new Configuracao();

		//Tamanho(quadros)
		linha = file.readLine().split("=");
		config.setQtdQuadros(Integer.parseInt(linha[1]));
		
		// Processos(paginas):
		file.readLine(); //lendo titulo
		while(!linhaArquivo.contains(";")) {
			linhaArquivo = file.readLine();
			lista.add(linhaArquivo.replaceAll(";", ""));
		}
		lista.forEach(x -> {
			linha = x.split("=");
			config.addPaginasProcessos(linha[0], Integer.parseInt(linha[1]));
			config.setQtdProcessos(1);
		});
		
		//Alocacao
		linha = file.readLine().split("=");
		config.setTipoAlocacao(linha[1]);		
		
		//Substituicao
		linha = file.readLine().split("=");
		config.setTipoSubstituicao(linha[1]);
		           
		//Sequencia
		file.readLine(); //lendo titulo
		lista = new ArrayList<String>();
		linhaArquivo = "";
		while(!linhaArquivo.contains(";")) {
			linhaArquivo = file.readLine();
			lista.add(linhaArquivo.replaceAll(";", ""));
		}
		lista.forEach(x -> {
			linha = x.split(",");
			config.addSequencia(linha[0], Integer.parseInt(linha[1]));
		});		
		file.close();
		
		config.gerarQuadros();
		
		//System.out.println(config.toString());
		
		return config;
		
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	
	
	
}
