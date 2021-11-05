package com.ur.urcap.Morans;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MoransProgramNodeView implements SwingProgramNodeView<MoransProgramNodeContribution> {

	private final Style style;
	private final Icon errorIcon;

	private JButton centerPointButton;
	private JButton moveHereButton;
	private JLabel errorLabel;
	private JLabel boxLengthLabel; // BOX LENGTH LABEL VARIABLE
	private JLabel boxWidthLabel; // BOX WIDTH LABEL VARIABLE
	private JLabel boxHeightLabel; // BOX HEIGHT LABEL VARIABLE	
	private JTextField boxLengthInput;
	private JTextField boxWidthInput;
	private JTextField boxHeightInput;
	
	
	public MoransProgramNodeView(Style style) {
		this.style = style;
		this.errorIcon = getErrorImage();
	}

	@Override
	public void buildUI(JPanel panel, final ContributionProvider<MoransProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Box infoSection = createSection(BoxLayout.PAGE_AXIS);
		infoSection.add(createInfo("This node creates a spraygun toolpath around the selected center point."));
		panel.add(infoSection);
		panel.add(createVerticalSpacing());
// START OF CENTER POINT BUTTON
		Box buttonSection = createSection(BoxLayout.LINE_AXIS);
		buttonSection.add(createHorizontalIndent());
		this.centerPointButton = createButton("Set center point");
		this.centerPointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get().selectCenterPoint();

			}
		});
		
		this.centerPointButton.setPreferredSize(style.getButtonSize());
		this.centerPointButton.setMinimumSize(style.getButtonSize());
		this.centerPointButton.setMaximumSize(style.getButtonSize());
		buttonSection.add(this.centerPointButton, FlowLayout.LEFT);
		panel.add(buttonSection);
		panel.add(createVerticalSpacing());
// END OF CENTER POINT BUTTON		
// START OF MOVE HERE BUTTON
		buttonSection = createSection(BoxLayout.LINE_AXIS);
		buttonSection.add(createHorizontalIndent());
		this.moveHereButton = createButton("Move here");
		this.moveHereButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get().moveRobot();
			}
		});
		
		this.moveHereButton.setPreferredSize(style.getButtonSize());
		this.moveHereButton.setMinimumSize(style.getButtonSize());
		this.moveHereButton.setMaximumSize(style.getButtonSize());
		buttonSection.add(this.moveHereButton, FlowLayout.LEFT);
		panel.add(buttonSection);
		panel.add(createVerticalSpacing());
// END OF MOVE HERE BUTTON
		
// START OF INPUT BOXES
		
		this.boxLengthInput = new JTextField();
		this.boxLengthInput.setPreferredSize(style.getInputSize());
		this.boxLengthInput.setMinimumSize(style.getInputSize());
		this.boxLengthInput.setMaximumSize(style.getInputSize());
		
		this.boxWidthInput = new JTextField();
		this.boxWidthInput.setPreferredSize(style.getInputSize());
		this.boxWidthInput.setMinimumSize(style.getInputSize());
		this.boxWidthInput.setMaximumSize(style.getInputSize());
		
		this.boxHeightInput = new JTextField();
		this.boxHeightInput.setPreferredSize(style.getInputSize());
		this.boxHeightInput.setMinimumSize(style.getInputSize());
		this.boxHeightInput.setMaximumSize(style.getInputSize());
		
		userInputSection.add(boxLengthInput);
		panel.add(userInputSection);
		panel.add(createVerticalSpacing());	
		
		userInputSection.add(boxWidthInput);
		panel.add(userInputSection);
		panel.add(createVerticalSpacing());	
		
		userInputSection.add(boxHeightInput);
		panel.add(userInputSection);
		panel.add(createVerticalSpacing());	
		
// START OF LENGTH LABEL
		Box userLabelSection = createSection(BoxLayout.LINE_AXIS);
		userLabelSection.add(createHorizontalIndent());	
					

		this.boxLengthLabel = new JLabel();
		this.boxLengthLabel.setText("Enter the length of box ");	
		userLabelSection.add(this.boxLengthLabel);
		panel.add(userLabelSection);
		panel.add(createVerticalSpacing());
				
		Box userInputSection = createSection(BoxLayout.LINE_AXIS);
		userInputSection.add(createHorizontalIndent());		
		
// START OF WIDTH LABEL

		userLabelSection.add(createHorizontalIndent());		

		this.boxWidthLabel = new JLabel();
		this.boxWidthLabel.setText("Enter the Width of box ");
		userLabelSection.add(this.boxWidthLabel);
		panel.add(userLabelSection);
		panel.add(createVerticalSpacing());
		
// START OF HEIGHT LABEL

		userLabelSection.add(createHorizontalIndent());		

		this.boxHeightLabel = new JLabel();
		this.boxHeightLabel.setText("Enter the Height of box ");
		userLabelSection.add(this.boxHeightLabel);
		panel.add(userLabelSection);
		panel.add(createVerticalSpacing());		
		
		Box errorSection = createSection(BoxLayout.LINE_AXIS);
		errorSection.add(createHorizontalIndent());
		this.errorLabel = new JLabel();
		errorSection.add(this.errorLabel);
		panel.add(errorSection);

	}

	private ImageIcon getErrorImage() {
		try {
			BufferedImage image = ImageIO
					.read(getClass().getResource("/com/ur/urcap/warning-bigger.png"));
			ImageIcon icon = new ImageIcon(image);
			return icon;
		} catch (IOException e) {
			// Should not happen.
			throw new RuntimeException("Unexpected exception while loading icon.", e);
		}
	}

	void clearErrors() {
		if (errorLabel != null) {
			errorLabel.setVisible(false);
		}
	}

	void updateError(String errorText, boolean isVisible) {
		if (errorLabel != null) {
			errorLabel.setVisible(isVisible);
			errorLabel.setText("<html>Error: " + errorText + "</html>");
			errorLabel.setIcon(errorIcon);
		}
	}

	Box createInfo(String text) {
		Box infoBox = Box.createHorizontalBox();
		infoBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoBox.add(new JLabel(text));
		return infoBox;
	}

	Component createHorizontalIndent() {
		return Box.createRigidArea(new Dimension(style.getHorizontalIndent(), 0));
	}

	Component createVerticalSpacing() {
		return Box.createRigidArea(new Dimension(0, style.getVerticalSpacing()));
	}

	JButton createButton(String text) {
		return new JButton(text);
	}

	Box createSection(int axis) {
		Box panel = new Box(axis);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		return panel;
	}

	public void enableMoveButton(boolean isEnabled) {
		moveHereButton.setEnabled(isEnabled);
	}
}
