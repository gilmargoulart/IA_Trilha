package br.com.trilha.tela;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.com.trilha.mainProgram.MainProgram;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame {
	
	private JLabel lblTabuleiro;
	
	/**
	 * Create the frame.
	 */
	public Tabuleiro() {
		setBounds(100, 100, 1207, 807);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblTabuleiro = new JLabel("");
		lblTabuleiro.setIcon(new ImageIcon(MainProgram.class.getResource("/br/com/trilha/imagens/tabuleiro.png")));
		lblTabuleiro.setBounds(10, 10, 1126, 595);
		getContentPane().add(lblTabuleiro);
		
	}
}
