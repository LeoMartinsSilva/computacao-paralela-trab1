package principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import leitor.ChamaLeitor;
import leitor.ExecucaoMultipla;
import model.DadosSaidaLeitor;
import view.GUI;
import view.RadioButtonOption;
import view.RadioButtonSelected;

public class Main {
	public static void main(String[] args) throws IOException {
		List<DadosSaidaLeitor> dadosSaida = new ArrayList<DadosSaidaLeitor>();
		
		GUI gui = new GUI();
		
		gui.setOptionsModo(List.of(
				new RadioButtonOption("sync", "Sincrono"), 
				new RadioButtonOption("async", "Assincrono"), 
				new RadioButtonOption("async2PorArquivo", "Assincrono 2 por arquivo"),
				new RadioButtonOption("asyncComMap", "Assincrono com Map")
		));
		gui.setSelectedModo(new RadioButtonSelected("sync"));
		gui.setOptionsArquivo(List.of(new RadioButtonOption("res/P", "Pequeno"), new RadioButtonOption("res/G", "Grande")));
		gui.setSelectedArquivo(new RadioButtonSelected("res/P"));
		gui.getBotaoBuscar().addActionListener(e -> {
			ChamaLeitor chamaLeitor = new ChamaLeitor();
			DadosSaidaLeitor retorno = chamaLeitor.chamar(gui.getSelectedModo().getValue(), gui.getSelectedArquivo().getValue(), gui.getTextNome().getText());
			dadosSaida.add(retorno);
			if(retorno.getArquivo() == null) {
				gui.showPopup("Nome não encontrado. Tempo Gasto: "+retorno.getTempo()+ " milisegundos");
			}else {
				gui.showPopup("Nome encontrado no arquivo "+ retorno.getArquivo()+ " em "+retorno.getTempo()+ " milisegundos");
			}
        });
		
		gui.getBotaoExportar().addActionListener(e -> exportarCsv(dadosSaida));
		
		
		ExecucaoMultipla execucaoMultipla = new ExecucaoMultipla();
		gui.getBotaoExecutarVarios().addActionListener(e -> {
			List<DadosSaidaLeitor> retorno = execucaoMultipla.executar(50);
			dadosSaida.addAll(retorno);
			
		});
		
		gui.montar();
	}
	
	public static void exportarCsv(List<DadosSaidaLeitor> dadosSaida) {
        // Dados exemplo: cada array representa uma linha
        List<String[]> dados = new ArrayList<String[]>();
        dados.add(new String[]{"Modo", "Listagem", "Arquivo", "Nome", "Tempo"});
        for(DadosSaidaLeitor saidaLeitor : dadosSaida) {
        	dados.add(new String[] {saidaLeitor.getModoDeBusca(), saidaLeitor.getListagem(), saidaLeitor.getArquivo(), saidaLeitor.getNome(), saidaLeitor.getTempo().toString()});
        }
        String nomeArquivo = "res/dados.xls";
        
        Workbook workbook = new HSSFWorkbook(); // Formato .xls (HSSF)
        Sheet sheet = workbook.createSheet("Contatos");

        int rowIndex = 0;
        for (String[] linha : dados) {
            Row row = sheet.createRow(rowIndex++);
            for (int col = 0; col < linha.length; col++) {
                Cell cell = row.createCell(col);
                cell.setCellValue(linha[col]);
            }
        }

        // Autoajuste das colunas
        for (int i = 0; i < dados.get(0).length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
        	workbook.write(fos);
            workbook.close();
            System.out.println("Arquivo XLS exportado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
