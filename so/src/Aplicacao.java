import javax.swing.JOptionPane;

public class Aplicacao {
	private static Arquivo arquivo;

	public static void main(String[] args) throws Exception {

		try {
			arquivo = new Arquivo();
			arquivo.criarArquivoResposta("Requisições=??\r\n" + 
					"TaxasDeErros:\r\n" + 
					"FIFO=?,??\r\n" + 
					"OPT=?,??\r\n" + 
					"LRU=?,??\r\n" + 
					"LFU=?,??\r\n" + 
					"MFU=?,??\r\n" + 
					"MY=?,??\r\n" + 
					"");
			System.out.println(arquivo.pegarDadosArquivo());


		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.WARNING_MESSAGE);
		} 

	}
}
