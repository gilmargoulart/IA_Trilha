package br.com.trilha.mainProgram;

import java.awt.EventQueue;
import br.com.trilha.tela.Tabuleiro;

public class MainProgram {

	@SuppressWarnings("unused")
	private Tabuleiro frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					MainProgram window = new MainProgram();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainProgram() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new Tabuleiro();
	}
}
