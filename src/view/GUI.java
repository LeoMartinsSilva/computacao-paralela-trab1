package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI extends JFrame {
	private JPanel panelPrincipal;
	private JPanel panelBotoes;
	private JPanel panelConteudo;
	private JPanel panelOpcoesModo;
	private JPanel panelOpcoesArquivo;
	private JPanel panelNome;
	private JTextField textNome;
	private RadioButtonSelected selectedModo;
	private RadioButtonSelected selectedArquivo;
	private JButton botaoExportar = new JButton("Exportar");
	private JButton botaoBuscar = new JButton("Buscar");
	private List<RadioButtonOption> optionsModo;
	private List<RadioButtonOption> optionsArquivo;
	
	public void montar() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		panelConteudo = new JPanel();
		panelConteudo.setLayout(new GridLayout(3, 1));
		
		panelBotoes = new JPanel();
		panelBotoes.setLayout(new GridLayout());
		
        
        panelOpcoesModo = new JPanel();
        panelOpcoesModo.setLayout(new FlowLayout());
        panelOpcoesModo.add(new JLabel("Modo de execução: "));
        montarRadioButtons(panelOpcoesModo, optionsModo, selectedModo);
        
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
        
        panelBotoes.add(botaoExportar);
        panelBotoes.add(botaoBuscar);
        
        panelPrincipal.add(panelConteudo);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);
        this.add(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
	}
	
	public void showPopup(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Busca realizada", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void montarRadioButtons(JPanel panel, List<RadioButtonOption> opcoes, RadioButtonSelected selected) {
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

	public void setOptionsModo(List<RadioButtonOption> optionsModo) {
		this.optionsModo = optionsModo;
	}

	public void setSelectedModo(RadioButtonSelected selectedModo) {
		this.selectedModo = selectedModo;
	}

	public void setSelectedArquivo(RadioButtonSelected selectedArquivo) {
		this.selectedArquivo = selectedArquivo;
	}

	public void setOptionsArquivo(List<RadioButtonOption> optionsArquivo) {
		this.optionsArquivo = optionsArquivo;
	}

	public JButton getBotaoExportar() {
		return botaoExportar;
	}

	public JButton getBotaoBuscar() {
		return botaoBuscar;
	}

	public JTextField getTextNome() {
		return textNome;
	}

	public RadioButtonSelected getSelectedModo() {
		return selectedModo;
	}

	public RadioButtonSelected getSelectedArquivo() {
		return selectedArquivo;
	}
	
	
	
}
