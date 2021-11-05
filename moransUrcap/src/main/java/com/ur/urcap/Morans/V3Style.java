package com.ur.urcap.Morans;

import java.awt.Dimension;

public class V3Style extends Style {

	private static final int VERTICAL_SPACING = 10;
	private static final int HORIZONTAL_INDENT = 20;
	private static final Dimension BUTTON_SIZE = new Dimension(190, 30);
	private static final Dimension INPUT_SIZE = new Dimension(150, 30);

	@Override
	public int getVerticalSpacing() {
		return VERTICAL_SPACING;
	}

	@Override
	public int getHorizontalIndent() {
		return HORIZONTAL_INDENT;
	}

	public Dimension getButtonSize(){
		return BUTTON_SIZE;
	}
	
	@Override
	public Dimension getInputSize() {
		return INPUT_SIZE;
	}

}
