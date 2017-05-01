package br.com.trilha.enums;

import java.awt.Image;

import javax.swing.ImageIcon;

import br.com.trilha.mainProgram.MainProgram;

public enum TipoPeca {
		
		EM_BRANCO(0, ""),
		PLAYER1(1, "/br/com/trilha/imagens/bean_red.png"),
		PLAYER2(2, "/br/com/trilha/imagens/bean_yellow.png");
	
	private int _tipoPeca;
	private ImageIcon _imageIcon;
	
	TipoPeca(int tipo, String imagePath){
		setTipoPeca(tipo);
		try {
			setImageIcon(new ImageIcon(new ImageIcon(MainProgram.class.getResource(imagePath)).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			setImageIcon(null);
		}
	}

	public int getTipoPeca() {
		return _tipoPeca;
	}

	private void setTipoPeca(int _tipoPeca) {
		this._tipoPeca = _tipoPeca;
	}

	public ImageIcon getImageIcon() {
		return _imageIcon;
	}

	private void setImageIcon(ImageIcon _imageIcon) {
		this._imageIcon = _imageIcon;
	}
	
	
}