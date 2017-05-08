package br.com.trilha.tela;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.trilha.mainProgram.MainProgram;
import br.com.trilha.componentes.Peca;
import br.com.trilha.componentes.PecaMouseAdapter;
import br.com.trilha.componentes.Player;
import br.com.trilha.enums.TipoPeca;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame {
	
	public PecaMouseAdapter pecaMouseAdapter;
	public boolean gameStarted; //Indica que o jogo começou
	public boolean gameOver; //Indica fim de jogo
	
	public static final int QTD_PECAS_INICIAIS = 9;

	private JLabel lblTabuleiro;
	private Player player1;
	private Player player2;
	private Player jogadorDaVez;
	
	//Peças
	private Peca a1;
	private Peca a2;
	private Peca a3;
	private Peca b1;
	private Peca b2;
	private Peca b3;
	private Peca c1;
	private Peca c2;
	private Peca c3;
	private Peca d1;
	private Peca d2;
	private Peca d3;
	private Peca e1;
	private Peca e2;
	private Peca e3;
	private Peca f1;
	private Peca f2;
	private Peca f3;
	private Peca g1;
	private Peca g2;
	private Peca g3;
	private Peca h1;
	private Peca h2;
	private Peca h3;
	private List<Peca> pecas = new ArrayList<>();
	
	//Status
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	private JLabel lblPlayer1PecasRestantes;
	private JLabel lblPlayer1PecasEmJogo;
	private JLabel lblPlayer1PecasAdversario;
	private JTextField txtPlayer1PecasRestantes;
	private JTextField txtPlayer1PecasEmJogo;
	private JTextField txtPlayer1PecasAdversario;
	private JLabel lblPlayer2PecasRestantes;
	private JTextField txtPlayer2PecasRestantes;
	private JTextField txtPlayer2PecasEmJogo;
	private JLabel lblPlayer2PecasEmJogo;
	private JLabel lblPlayer2PecasAdversario;
	private JTextField txtPlayer2PecasAdversario;
	private JSeparator separator;
	private ImageIcon imgJogadorDaVez;
	private ImageIcon imgJogadorAguardando;
	private JSeparator separator_1;
	private JLabel lblPecaPlayer1;
	private JLabel lblPecaPlayer2;
	private JProgressBar pbBotPlaying;
	
	/**
	 * Create the frame.
	 */
	public Tabuleiro() {
		setResizable(false);
		setName("Tabuleiro");
		setBounds(10, 10, 1207, 807);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		initialize();
		definirPecasVizinhas();
		definirMoinhos();
		atribuirPecasAoTabuleiro();
		refreshStatusCaptions();
		setVisible(true);
	}
	
	private void atribuirPecasAoTabuleiro() {
		pecas.addAll(new ArrayList<Peca>(Arrays.asList(a1, a2, a3, b1, b2, b3, c1, c2, c3, d1, d2, d3, e1, d2, d3, e1, e2, e3, f1, f2, f3, g1, g2, g3, h1, h2, h3)));
	}

	private void initialize() {
		gameStarted = false;
		gameOver = false;
		
		pecaMouseAdapter = new PecaMouseAdapter(this);
		
		imgJogadorDaVez = new ImageIcon(MainProgram.class.getResource("/br/com/trilha/imagens/vez_do_jogador.png"));
		imgJogadorAguardando = new ImageIcon(MainProgram.class.getResource("/br/com/trilha/imagens/jogador_aguardando.png"));
		
		player1 = new Player("Bot Good Player", TipoPeca.PLAYER1);
		player1.setQtdPecasIniciaisRestantes(QTD_PECAS_INICIAIS);
		player1.setVezDoJogador(false);
		
 		//String nomePlayer = ""; //Utilizar para facilitar ao usar o editor WIndowBuilder
		String nomePlayer = JOptionPane.showInputDialog(null, "Informe seu nome:", "Informar nome", JOptionPane.INFORMATION_MESSAGE);
		if (nomePlayer == null || nomePlayer.isEmpty()){
			nomePlayer = "Player 2";
		}
		player2 = new Player(nomePlayer, TipoPeca.PLAYER2);
		player2.setQtdPecasIniciaisRestantes(QTD_PECAS_INICIAIS);
		player2.setVezDoJogador(true);
		
		player1.setAdversario(player2);
		player2.setAdversario(player1);
		
		a1 = new Peca("a1");
		a1.setLocation(4, 10);
		a1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(a1);
		
		a2 = new Peca("a2");
		a2.setLocation(524, 9);
		a2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(a2);
		
		a3 = new Peca("a3");
		a3.setLocation(1068, 10);
		a3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(a3);
		
		b1 = new Peca("b1");
		b1.setLocation(5, 257);
		b1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(b1);
		
		b2 = new Peca("b2");
		b2.setLocation(89, 257);
		b2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(b2);
		
		b3 = new Peca("b3");
		b3.setLocation(162, 259);
		b3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(b3);
		
		c1 = new Peca("c1");
		c1.setLocation(5, 546);
		c1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(c1);
		
		c2 = new Peca("c2");
		c2.setLocation(532, 546);
		c2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(c2);
		
		c3 = new Peca("c3");
		c3.setLocation(1069, 541);
		c3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(c3);
		
		d1 = new Peca("d1");
		d1.setLocation(92, 82);
		d1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(d1);
		
		d2 = new Peca("d2");
		d2.setLocation(524, 74);
		d2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(d2);
		
		d3 = new Peca("d3");
		d3.setLocation(984, 81);
		d3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(d3);
		
		e1 = new Peca("e1");
		e1.setLocation(161, 146);
		e1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(e1);
		
		e2 = new Peca("e2");
		e2.setLocation(527, 141);
		e2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(e2);
		
		e3 = new Peca("e3");
		e3.setLocation(901, 149);
		e3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(e3);
		
		f1 = new Peca("f1");
		f1.setLocation(89, 469);
		f1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(f1);
		
		f2 = new Peca("f2");
		f2.setLocation(531, 476);
		f2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(f2);
		
		f3 = new Peca("f3");
		f3.setLocation(984, 471);
		f3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(f3);
		
		g1 = new Peca("g1");
		g1.setLocation(165, 399);
		g1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(g1);
		
		g2 = new Peca("g2");
		g2.setLocation(530, 406);
		g2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(g2);
		
		g3 = new Peca("g3");
		g3.setLocation(901, 399);
		g3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(g3);
		
		h1 = new Peca("h1");
		h1.setLocation(906, 272);
		h1.addMouseListener(pecaMouseAdapter);
		getContentPane().add(h1);
		
		h2 = new Peca("h2");
		h2.setLocation(986, 273);
		h2.addMouseListener(pecaMouseAdapter);
		getContentPane().add(h2);
		
		h3 = new Peca("h3");
		h3.setLocation(1072, 270);
		h3.addMouseListener(pecaMouseAdapter);
		getContentPane().add(h3);
		
		lblTabuleiro = new JLabel("");
		lblTabuleiro.setIcon(new ImageIcon(MainProgram.class.getResource("/br/com/trilha/imagens/tabuleiro.png")));
		lblTabuleiro.setBounds(10, 10, 1126, 595);
		getContentPane().add(lblTabuleiro);
		
		lblPlayer1 = new JLabel(player1.getNome());
		lblPlayer1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlayer1.setBounds(10, 624, 188, 48);
		getContentPane().add(lblPlayer1);
		
		lblPlayer1PecasRestantes = new JLabel("Pe\u00E7as Restantes:");
		lblPlayer1PecasRestantes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer1PecasRestantes.setToolTipText("Total de pe\u00E7as restantes para inserir no tabuleiro.");
		lblPlayer1PecasRestantes.setBounds(10, 676, 122, 20);
		getContentPane().add(lblPlayer1PecasRestantes);
		
		txtPlayer1PecasRestantes = new JTextField();
		txtPlayer1PecasRestantes.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer1PecasRestantes.setDisabledTextColor(Color.BLACK);
		txtPlayer1PecasRestantes.setEditable(false);
		txtPlayer1PecasRestantes.setEnabled(false);
		txtPlayer1PecasRestantes.setBounds(136, 676, 48, 20);
		getContentPane().add(txtPlayer1PecasRestantes);
		txtPlayer1PecasRestantes.setColumns(10);
		
		lblPlayer1PecasEmJogo = new JLabel("Pe\u00E7as em jogo:");
		lblPlayer1PecasEmJogo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer1PecasEmJogo.setToolTipText("Total de pe\u00E7as em jogo.");
		lblPlayer1PecasEmJogo.setBounds(10, 704, 122, 20);
		getContentPane().add(lblPlayer1PecasEmJogo);
		
		txtPlayer1PecasEmJogo = new JTextField();
		txtPlayer1PecasEmJogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer1PecasEmJogo.setDisabledTextColor(Color.BLACK);
		txtPlayer1PecasEmJogo.setEditable(false);
		txtPlayer1PecasEmJogo.setEnabled(false);
		txtPlayer1PecasEmJogo.setColumns(10);
		txtPlayer1PecasEmJogo.setBounds(136, 704, 48, 20);
		getContentPane().add(txtPlayer1PecasEmJogo);
		
		lblPlayer1PecasAdversario = new JLabel("Pe\u00E7as do advers\u00E1rio:");
		lblPlayer1PecasAdversario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer1PecasAdversario.setToolTipText("Total de pe\u00E7as removidas do advers\u00E1rio.");
		lblPlayer1PecasAdversario.setBounds(10, 729, 122, 20);
		getContentPane().add(lblPlayer1PecasAdversario);
		
		txtPlayer1PecasAdversario = new JTextField();
		txtPlayer1PecasAdversario.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer1PecasAdversario.setDisabledTextColor(Color.BLACK);
		txtPlayer1PecasAdversario.setEditable(false);
		txtPlayer1PecasAdversario.setEnabled(false);
		txtPlayer1PecasAdversario.setColumns(10);
		txtPlayer1PecasAdversario.setBounds(136, 729, 48, 20);
		getContentPane().add(txtPlayer1PecasAdversario);
		
		lblPlayer2 = new JLabel(player2.getNome());
		lblPlayer2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlayer2.setBounds(304, 624, 235, 48);
		getContentPane().add(lblPlayer2);
		
		lblPlayer2PecasRestantes = new JLabel("Pe\u00E7as Restantes:");
		lblPlayer2PecasRestantes.setToolTipText("Total de pe\u00E7as restantes para inserir no tabuleiro.");
		lblPlayer2PecasRestantes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer2PecasRestantes.setBounds(295, 676, 123, 20);
		getContentPane().add(lblPlayer2PecasRestantes);
		
		txtPlayer2PecasRestantes = new JTextField();
		txtPlayer2PecasRestantes.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer2PecasRestantes.setDisabledTextColor(Color.BLACK);
		txtPlayer2PecasRestantes.setEditable(false);
		txtPlayer2PecasRestantes.setEnabled(false);
		txtPlayer2PecasRestantes.setColumns(10);
		txtPlayer2PecasRestantes.setBounds(425, 676, 48, 20);
		getContentPane().add(txtPlayer2PecasRestantes);
		
		lblPlayer2PecasEmJogo = new JLabel("Pe\u00E7as em jogo:");
		lblPlayer2PecasEmJogo.setToolTipText("Total de pe\u00E7as em jogo.");
		lblPlayer2PecasEmJogo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer2PecasEmJogo.setBounds(295, 704, 123, 20);
		getContentPane().add(lblPlayer2PecasEmJogo);
		
		txtPlayer2PecasEmJogo = new JTextField();
		txtPlayer2PecasEmJogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer2PecasEmJogo.setDisabledTextColor(Color.BLACK);
		txtPlayer2PecasEmJogo.setEditable(false);
		txtPlayer2PecasEmJogo.setEnabled(false);
		txtPlayer2PecasEmJogo.setColumns(10);
		txtPlayer2PecasEmJogo.setBounds(425, 704, 48, 20);
		getContentPane().add(txtPlayer2PecasEmJogo);
		
		lblPlayer2PecasAdversario = new JLabel("Pe\u00E7as do advers\u00E1rio:");
		lblPlayer2PecasAdversario.setToolTipText("Total de pe\u00E7as removidas do advers\u00E1rio.");
		lblPlayer2PecasAdversario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayer2PecasAdversario.setBounds(295, 729, 123, 20);
		getContentPane().add(lblPlayer2PecasAdversario);
		
		txtPlayer2PecasAdversario = new JTextField();
		txtPlayer2PecasAdversario.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPlayer2PecasAdversario.setDisabledTextColor(Color.BLACK);
		txtPlayer2PecasAdversario.setEditable(false);
		txtPlayer2PecasAdversario.setEnabled(false);
		txtPlayer2PecasAdversario.setColumns(10);
		txtPlayer2PecasAdversario.setBounds(425, 729, 48, 20);
		getContentPane().add(txtPlayer2PecasAdversario);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(8, 611, 1127, 2);
		getContentPane().add(separator_1);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(292, 611, 2, 138);
		getContentPane().add(separator);
		
		lblPecaPlayer1 = new JLabel("");
		lblPecaPlayer1.setBounds(208, 616, 60, 60);
		lblPecaPlayer1.setIcon(TipoPeca.PLAYER1.getImageIcon());
		getContentPane().add(lblPecaPlayer1);
		
		lblPecaPlayer2 = new JLabel("");
		lblPecaPlayer2.setBounds(542, 616, 60, 60);
		lblPecaPlayer2.setIcon(TipoPeca.PLAYER2.getImageIcon());
		getContentPane().add(lblPecaPlayer2);
		
		pbBotPlaying = new JProgressBar();
		pbBotPlaying.setBounds(10, 753, 463, 19);
		getContentPane().add(pbBotPlaying);
	}

	public Player getPlayer1(){
		return player1;
	}
	
	public Player getPlayer2(){
		return player2;
	}
	
	public void refreshStatusCaptions(){
		
		if (getPlayer1().getQtdPecasIniciaisRestantes() == 0 && getPlayer2().getQtdPecasIniciaisRestantes() == 0) {
			gameStarted = true;
			System.out.println("Peças colocadas. Jogo começa agora.");
		}
		
		txtPlayer1PecasRestantes.setText(String.valueOf(player1.getQtdPecasIniciaisRestantes()));
		txtPlayer1PecasEmJogo.setText(String.valueOf(player1.getQtdPecas()));
		txtPlayer1PecasAdversario.setText(String.valueOf(player1.getQtdPecasAdversario()));
		//
		txtPlayer2PecasRestantes.setText(String.valueOf(player2.getQtdPecasIniciaisRestantes()));
		txtPlayer2PecasEmJogo.setText(String.valueOf(player2.getQtdPecas()));
		txtPlayer2PecasAdversario.setText(String.valueOf(player2.getQtdPecasAdversario()));
		
		if (gameStarted) {
			//Verifica se o player pode movimentar a peça para qualquer casa em branco 
			if (player1.getQtdPecas() == 3) {
				player1.setMovimentaPecasEmQualquerLugar();
			} else if (player1.getQtdPecas() < 3) { //Verifica fim de jogo
				pbBotPlaying.setString("VENCEDOR: " + player2.getNome() + ".");
				pbBotPlaying.setStringPainted(true);
				JOptionPane.showMessageDialog(null, "Parabéns " + player2.getNome() + ", você venceu!", "Vencedor.", JOptionPane.INFORMATION_MESSAGE);
				gameOver = true;
			}
		
			if (player2.getQtdPecas() == 3) {
				player2.setMovimentaPecasEmQualquerLugar();
			} else if (player2.getQtdPecas() < 3) {
				pbBotPlaying.setString("VENCEDOR: " + player1.getNome() + ".");
				pbBotPlaying.setStringPainted(true);
				JOptionPane.showMessageDialog(null, "Parabéns " + player1.getNome() + ", você venceu!", "Vencedor.", JOptionPane.INFORMATION_MESSAGE);
				gameOver = true;
			}
			
			if (gameOver) {
				player1.setVezDoJogador(false);
				player2.setVezDoJogador(false);
			}
		}
		if (player1.isVezDoJogador()){
			lblPlayer1.setIcon(imgJogadorDaVez);
			lblPlayer1.setToolTipText("Efetuando jogada.");
			jogadorDaVez = player1;
			
			lblPlayer2.setIcon(imgJogadorAguardando);
			lblPlayer2.setToolTipText("Aguardando " + player1.getNome() + " realizar a jogada.");
			
			lblPecaPlayer1.setEnabled(true);
			lblPecaPlayer2.setEnabled(false);

			pbBotPlaying.setString("Aguardando " + player1.getNome() + " realizar a jogada.");
			pbBotPlaying.setStringPainted(true);
			pbBotPlaying.setIndeterminate(true);
			jogadaBot();
		} else if (player2.isVezDoJogador()){
			lblPlayer2.setIcon(imgJogadorDaVez);
			lblPlayer2.setToolTipText("Efetuando jogada.");
			jogadorDaVez = player2;
			
			lblPlayer1.setIcon(imgJogadorAguardando);
			lblPlayer1.setToolTipText("Aguardando " + player2.getNome() + " realizar a jogada.");
			
			pbBotPlaying.setString("Aguardando " + player2.getNome() + " realizar a jogada.");
			pbBotPlaying.setStringPainted(true);
			
			lblPecaPlayer1.setEnabled(false);
			lblPecaPlayer2.setEnabled(true);
			
			pbBotPlaying.setIndeterminate(false);
		}
		
		
	}
	
	private void definirPecasVizinhas(){
		
		a1.removerPecasVizinhas();
		a1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(a2,b1)));
		a2.removerPecasVizinhas();
		a2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(a1,d2,a3)));
		a3.removerPecasVizinhas();
		a3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(a2,h3)));
		//
		b1.removerPecasVizinhas();
		b1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(a1,b2,c1)));
		b2.removerPecasVizinhas();
		b2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b1,b3,d1,f1)));
		b3.removerPecasVizinhas();
		b3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b2,e1,g1)));
		//
		c1.removerPecasVizinhas();
		c1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b1,c2)));
		c2.removerPecasVizinhas();
		c2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(c1,f2,c3)));
		c3.removerPecasVizinhas();
		c3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(h3,c2)));
		//
		d1.removerPecasVizinhas();
		d1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b2,d2)));
		d2.removerPecasVizinhas();
		d2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(d1,a2,e2,d3)));
		d3.removerPecasVizinhas();
		d3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(d2,h2)));
		//
		e1.removerPecasVizinhas();
		e1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b3,e2)));
		e2.removerPecasVizinhas();
		e2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(e1,d2,e3)));
		e3.removerPecasVizinhas();
		e3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(e2,h1)));
		//
		f1.removerPecasVizinhas();
		f1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b2,f2)));
		f2.removerPecasVizinhas();
		f2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(f1,g2,c2,f3)));
		f3.removerPecasVizinhas();
		f3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(f2,h2)));
		//
		g1.removerPecasVizinhas();
		g1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(b3,g2)));
		g2.removerPecasVizinhas();
		g2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(g1,f2,g3)));
		g3.removerPecasVizinhas();
		g3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(g2,h1)));
		//
		h1.removerPecasVizinhas();
		h1.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(g3,e3,h2)));
		h2.removerPecasVizinhas();
		h2.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(h1,d3,f3,h3)));
		h3.removerPecasVizinhas();
		h3.addPecaVizinha(new ArrayList<Peca>(Arrays.asList(h2,a3,c3)));
	}
	
	private void definirMoinhos(){
		a1.removerMoinhos();
		a1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b1, c1)));
		a1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a2, a3)));
		
		a2.removerMoinhos();
		a2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a1, a3)));
		a2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d2, e2)));
		
		a3.removerMoinhos();
		a3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a1, a2)));
		a3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h3, c3)));
		// ----------------------------------------------------------
		b1.removerMoinhos();
		b1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b2, b3)));
		b1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a1, c1)));
		
		b2.removerMoinhos();
		b2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b1, b3)));
		b2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d1, f1)));
		
		b3.removerMoinhos();
		b3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b1, b2)));
		b3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(e1, g1)));
		// ----------------------------------------------------------
		c1.removerMoinhos();
		c1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(c2, c3)));
		c1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a1, b1)));
		
		c2.removerMoinhos();
		c2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(c1, c3)));
		c2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(f2, g2)));
		
		c3.removerMoinhos();
		c3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(c1, c2)));
		c3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h3, a3)));
		// ----------------------------------------------------------
		d1.removerMoinhos();
		d1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d2, d3)));
		d1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b2, f1)));
		
		d2.removerMoinhos();
		d2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d1, d3)));
		d2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a2, e2)));
		
		d3.removerMoinhos();
		d3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d1, d2)));
		d3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h2, f3)));
		// ----------------------------------------------------------
		e1.removerMoinhos();
		e1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(e2, e3)));
		e1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b3, g1)));
		
		e2.removerMoinhos();
		e2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(e1, e3)));
		e2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d2, a2)));
		
		e3.removerMoinhos();
		e3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(e1, e2)));
		e3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h1, g3)));
		// ----------------------------------------------------------
		f1.removerMoinhos();
		f1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(f2, f3)));
		f1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b2, d1)));
		
		f2.removerMoinhos();
		f2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(f1, f3)));
		f2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(g2, c2)));
		
		f3.removerMoinhos();
		f3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(f1, f2)));
		f3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h2, d3)));
		// ----------------------------------------------------------
		g1.removerMoinhos();
		g1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(g2, g3)));
		g1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(b3, e1)));
		
		g2.removerMoinhos();
		g2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(g1, g3)));
		g2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(f2, c2)));
		
		g3.removerMoinhos();
		g3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(g1, g2)));
		g3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h1, e3)));
		// ----------------------------------------------------------
		h1.removerMoinhos();
		h1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h2, h3)));
		h1.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(e3, g3)));
		
		h2.removerMoinhos();
		h2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h1, h3)));
		h2.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(d3, f3)));
		
		h3.removerMoinhos();
		h3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(h1, h2)));
		h3.addPecaMoinho(new ArrayList<Peca>(Arrays.asList(a3, c3)));
	}

	public Player getJogadorDaVez() {
		return jogadorDaVez;
	}
	
	private void jogadaBot(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//Delay de 2 segundos antes do processamento, para simular que outro player está "Pensando"
				sleep(2);
				
				Peca pecajogada = null;
				
				//Jogada do bot
				if (gameStarted) {
					
				} else { // Ainda colocando as peças
					boolean jogadaEfetuada = false;
					
					System.out.println("Verificando possíveis moinhos do Bot...");
					//Verificar se tem algum possível moinho pro Bot
					for (Peca p : player1.getPecas()) {
						Peca possivelMoinho = p.isPossivelMoinho();
						if (possivelMoinho != null){ //Efetuar jogada
							possivelMoinho.setTipoPeca(player1.getTipoPeca());
							player1.addPeca(possivelMoinho);
							player1.diminuiQtdPecasIniciais();
							jogadaEfetuada = true;
							pecajogada = possivelMoinho;
							break;
						}
					}
					
					if (!jogadaEfetuada) {
						//Identificar possível moinho do adversário
						System.out.println("Verificando possíveis moinhos do adversário...");
						for (Peca p : player2.getPecas()) {
							//Trancar possível moinho do adversário.
							Peca possivelMoinho = p.isPossivelMoinho();
							if (possivelMoinho != null) { //Efetuar jogada
								possivelMoinho.setTipoPeca(player1.getTipoPeca());
								player1.addPeca(possivelMoinho);
								player1.diminuiQtdPecasIniciais();
								jogadaEfetuada = true;
								pecajogada = possivelMoinho;
								System.out.println("Adversário tem um possível moinho... Trancando");
								break;
							}
						}
					}
					
					if (!jogadaEfetuada) {
						//Buscar peças que não tenham peças vizinhas do adversário ou do bot.
						System.out.println("Buscando peças com peças vizinhas em branco também...");
						for (Peca p : pecas) {
							if (p.getTipoPeca() == TipoPeca.EM_BRANCO){
								boolean pecasVizinhasEmBranco = true;
								for (Peca p2 : p.getPecasVizinhas()){
									if (p2.getTipoPeca() == player1.getTipoPeca() || p2.getTipoPeca() == player2.getTipoPeca()) {
										pecasVizinhasEmBranco = false;
										break;
									}
								}
								if (pecasVizinhasEmBranco){ //Efetuar jogada
									p.setTipoPeca(player1.getTipoPeca());
									player1.addPeca(p);
									player1.diminuiQtdPecasIniciais();
									jogadaEfetuada = true;
									pecajogada = p;
									break;
								}
							}
						}
					}
					
					if (!jogadaEfetuada) {
						//Buscar peças que não tenham peças vizinhas do adversário.
						System.out.println("Buscando peças do bot com peças vizinhas em branco...");
						for (Peca p : pecas) {
							if (p.getTipoPeca() == TipoPeca.EM_BRANCO){
								boolean pecasVizinhasEmBranco = true;
								for (Peca p2 : p.getPecasVizinhas()){
									if (p2.getTipoPeca() == player2.getTipoPeca()) {
										pecasVizinhasEmBranco = false;
										break;
									}
								}
								if (pecasVizinhasEmBranco){ //Efetuar jogada
									p.setTipoPeca(player1.getTipoPeca());
									player1.addPeca(p);
									player1.diminuiQtdPecasIniciais();
									jogadaEfetuada = true;
									pecajogada = p;
									break;
								}
							}
						}
					}
					
					if (!jogadaEfetuada) {
						// Verificar se o Bot tem alguma Peça em que a Peça vizinha está em branco.
						System.out.println("Buscando peças vizinhas do Bot em branco...");
						for (Peca p : player1.getPecas()) {
							for (Peca p2 : p.getPecasVizinhas()) {
								if (p2.getTipoPeca() == TipoPeca.EM_BRANCO){
									p2.setTipoPeca(player1.getTipoPeca());
									player1.addPeca(p2);
									player1.diminuiQtdPecasIniciais();
									jogadaEfetuada = true;
									pecajogada = p2;
									break;
								}
							}
							if (jogadaEfetuada) break;
						}
					}
					
					//Se nenhum caso der certo, jogar em qualquer lugar que tenha uma peça em branco.
					if (!jogadaEfetuada) {
						List<Peca> pecasEmBranco = new ArrayList<>();
						System.out.println("Jogando em qualquer lugar em branco...");
						for (Peca p : pecas) {
							if (p.getTipoPeca() == TipoPeca.EM_BRANCO) {
								pecasEmBranco.add(p);
							}
						}
						int index = new Random().nextInt(pecasEmBranco.size()-1);
						Peca p = pecas.get(index);
						p.setTipoPeca(player1.getTipoPeca());
						player1.addPeca(p);
						player1.diminuiQtdPecasIniciais();
						pecajogada = p;
						jogadaEfetuada = true;
					}
				}
				
				if (pecajogada != null) {
					boolean moinhoDetectado = pecajogada.isMoinho(); 
					if (moinhoDetectado){
						System.out.println("Moinho detectado... Removendo uma peça.");
						pbBotPlaying.setString("Moinho detectado... Removendo uma peça.");
						pbBotPlaying.setStringPainted(true);
						
						sleep(1);
						boolean pecaRemovida = false;
						//Verificar peças do adversário que "formariam" um moinho
						for (Peca p : pecas) {
							if (p.getTipoPeca() == player2.getTipoPeca()) {
								for (List<Peca> lp : p.getMoinhos()) {
									if (lp.get(0).getTipoPeca() == player2.getTipoPeca()) {
										lp.get(0).setTipoPeca(TipoPeca.EM_BRANCO);
										player2.removerPeca(lp.get(0));
										player1.addPecaAdversario(lp.get(0));
										player1.setMoinhoDetectado(false);
										pecaRemovida = true;
										break;
									}
									if (lp.get(1).getTipoPeca() == player2.getTipoPeca()){
										lp.get(1).setTipoPeca(TipoPeca.EM_BRANCO);
										player2.removerPeca(lp.get(1));
										player1.addPecaAdversario(lp.get(1));
										player1.setMoinhoDetectado(false);
										pecaRemovida = true;
										break;
									}
								}
								if (pecaRemovida) {
									break;
								}
							}
						}
						
						if (!pecaRemovida) {
							for (Peca p : pecas) {
								if (p.getTipoPeca() == player2.getTipoPeca()) {
									p.setTipoPeca(TipoPeca.EM_BRANCO);
									player2.removerPeca(p);
									player1.addPecaAdversario(p);
									player1.setMoinhoDetectado(false);
									pecaRemovida = true;
									break;
								}
							}
						}
					}
				}
				
				player1.passarJogada();
				refreshStatusCaptions();
			}
		}).start();
	}
	
	private void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
