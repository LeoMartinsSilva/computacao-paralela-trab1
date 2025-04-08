package principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import view.RadioButtonOption;
import view.RadioButtonSelected;

public class Main {
	
	private static JFrame frame;

	private static JPanel panelPrincipal;
	private static JPanel panelConteudo;
	private static JPanel panelOpcoesModo;
	private static JPanel panelOpcoesArquivo;
	private static JPanel panelNome;
	private static JTextField textNome;
	private static RadioButtonSelected selectedModo = new RadioButtonSelected("sync");
	private static RadioButtonSelected selectedArquivo = new RadioButtonSelected("res/P");
	private static JButton botaoExportar = new JButton("Exportar");
	private static JButton botaoBuscar = new JButton("Buscar");
	
	public static void main(String[] args) throws IOException {
		List<DadosSaidaLeitor> dadosSaida = new ArrayList<DadosSaidaLeitor>();
		
		montarGUI();
		botaoBuscar.addActionListener(e -> {
			ChamaLeitor chamaLeitor = new ChamaLeitor();
			dadosSaida.add(chamaLeitor.chamar(selectedModo.getValue(), selectedArquivo.getValue(), textNome.getText()));
        });
		
		botaoExportar.addActionListener(e -> exportarCsv(dadosSaida));
	}
	
	private static void montarGUI() {
		frame = new JFrame("Buscar na lista");
		
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		panelConteudo = new JPanel();
		panelConteudo.setLayout(new GridLayout(3, 1));
		
        
        List<RadioButtonOption> optionsModo = List.of(new RadioButtonOption("sync", "Sincrono"), new RadioButtonOption("async", "Assincrono"));
        panelOpcoesModo = new JPanel();
        panelOpcoesModo.setLayout(new FlowLayout());
        panelOpcoesModo.add(new JLabel("Modo de execução: "));
        montarRadioButtons(panelOpcoesModo, optionsModo, selectedModo);
        
        
        List<RadioButtonOption> optionsArquivo = List.of(new RadioButtonOption("res/P", "Pequeno"), new RadioButtonOption("res/G", "Grande"));
        panelOpcoesArquivo = new JPanel();
        panelOpcoesArquivo.setLayout(new FlowLayout());
        panelOpcoesArquivo.add(new JLabel("Listagem: "));
        montarRadioButtons(panelOpcoesArquivo, optionsArquivo, selectedArquivo);
        
        panelNome = new JPanel();
        panelNome.add(new JLabel("Nome:"));
        textNome = new JTextField(30);
        panelNome.add(textNome);
        
        panelConteudo.add(panelOpcoesModo);
        panelConteudo.add(panelOpcoesArquivo);
        panelConteudo.add(panelNome);
        
        panelPrincipal.add(panelConteudo);
        panelPrincipal.add(botaoBuscar, BorderLayout.SOUTH);
        panelPrincipal.add(botaoExportar, BorderLayout.LINE_END);
        frame.add(panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
	}
	
	private static void montarRadioButtons(JPanel panel, List<RadioButtonOption> opcoes, RadioButtonSelected selected) {
		ButtonGroup grupo = new ButtonGroup();
		for(RadioButtonOption opcao : opcoes) {
			JRadioButton radioButton = new JRadioButton(opcao.getLabel(), opcao.getValue().equals(selected.getValue()));
			radioButton.addItemListener(e -> {
				selected.setValue(opcao.getValue());
			});
			grupo.add(radioButton);
			panel.add(radioButton);
		}
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
