
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class Arquivo {
	private String nomeArquivo;
	private static 	String linhaArquivo = "";
	private static String[] linha;
	private static List<String> lista = new ArrayList<String>();

	public Arquivo() throws Exception {
		this.nomeArquivo = pegarArquivo();
	}

	/**
	 * Método para pegar um arquivo em algum diretório da máquina
	 * 
	 * @return nomeArquivo
	 * @throws Exception
	 */

	public static String pegarArquivo() throws Exception {
		JFileChooser arquivo = new JFileChooser();
		int retorno = arquivo.showOpenDialog(null);
		if (retorno == JFileChooser.CANCEL_OPTION) {
			throw new Exception("Processo cancelado");
		}
		String arquivoSelecionado = arquivo.getSelectedFile().getPath();
		return arquivoSelecionado;
	}

	/**
	 * Método que criará arquivo de resposta a partir de uma String recebida
	 * 
	 * @param resposta
	 * @throws Exception
	 */

	public void criarArquivoResposta(String resposta) throws Exception {
		JFileChooser arquivo = new JFileChooser();
		int retorno = arquivo.showSaveDialog(null);
		if (retorno == JFileChooser.CANCEL_OPTION) {
			throw new Exception("Processo cancelado");
		}
		String pasta = arquivo.getSelectedFile().getPath();

		File arquivoResposta = new File(pasta + ".txt");
		RandomAccessFile rw = new RandomAccessFile(arquivoResposta, "rw");
		rw.writeUTF(resposta);
		rw.close();

	}
	
	/**
	 * Método que lê dados do arquivo selecionado
	 * @return dadosArquivo
	 * @throws IOException
	 */
	public String pegarDadosArquivo() throws IOException {
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");
		String dadosArquivo = null;
		
		while (file.getFilePointer() < file.length()) {					
			if (dadosArquivo == null) {
				dadosArquivo = file.readLine();
			} else {
				dadosArquivo = dadosArquivo + "\n" + file.readLine();
			}
		}
		file.close();
		return dadosArquivo;
	}
	
	public void gerarConfiguracao(String dados) throws IOException {
		RandomAccessFile file = new RandomAccessFile(nomeArquivo, "r");
		
		Configuracao config = new Configuracao();
		//Tamanho(quadros)
		linha = file.readLine().split("=");
		config.setTamanhoQuadros(Integer.parseInt(linha[1]));
		// Processos(páginas):	
		file.readLine(); //lendo título
		while(!linhaArquivo.contains(";")) {
			linhaArquivo = file.readLine();
			lista.add(linhaArquivo.replaceAll(";", ""));
		}
		lista.forEach(x -> {
			linha = x.split("=");
			config.addPaginasProcessos(linha[0], Integer.parseInt(linha[1]));
		});
		//Alocação
		linha = file.readLine().split("=");
		config.setTipoAlocacao(linha[1]);		
		//Substituição
		linha = file.readLine().split("=");
		config.setTipoSubstituicao(linha[1]);
		//Sequencia
		file.readLine(); //lendo título
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
		
		System.out.println(config.toString());
		
	}
	
	
	
	
}
