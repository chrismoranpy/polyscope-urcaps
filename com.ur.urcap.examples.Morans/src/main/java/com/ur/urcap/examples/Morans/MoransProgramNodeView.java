package com.ur.urcap.examples.Morans;

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

	private JButton moveHereButton;
	private JLabel errorLabel;

	public MoransProgramNodeView(Style style) {
		this.style = style;
		this.errorIcon = getErrorImage();
	}

	@Override
	public void buildUI(JPanel panel, final ContributionProvider<MoransProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Box infoSection = createSection(BoxLayout.PAGE_AXIS);
		infoSection.add(createInfo());
		panel.add(infoSection);
		panel.add(createVerticalSpacing());

		Box buttonSection = createSection(BoxLayout.LINE_AXIS);
		buttonSection.add(createHorizontalIndent());
		JButton centerPointButton = createButton("Set center point");
		centerPointButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get().selectCenterPoint();

			}
		});
		centerPointButton.setPreferredSize(style.getButtonSize());
		centerPointButton.setMinimumSize(style.getButtonSize());
		centerPointButton.setMaximumSize(style.getButtonSize());
		buttonSection.add(centerPointButton, FlowLayout.LEFT);
		panel.add(buttonSection);
		panel.add(createVerticalSpacing());

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

		Box errorSection = createSection(BoxLayout.LINE_AXIS);
		errorSection.add(createHorizontalIndent());
		this.errorLabel = new JLabel();
		errorSection.add(this.errorLabel);
		panel.add(errorSection);

	}

	private ImageIcon getErrorImage() {
		try {
			BufferedImage image = ImageIO
					.read(getClass().getResource("/com/ur/urcap/examples/Morans/warning-bigger.png"));
			return new ImageIcon(image);
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

	Box createInfo() {
		Box infoBox = Box.createHorizontalBox();
		infoBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoBox.add(new JLabel("This node creates an ellipse like movement around the selected center point."));
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