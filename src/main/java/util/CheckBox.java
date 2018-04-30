package util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckBox {

	public static void scaleCheckBoxIcon(JCheckBox checkbox) {
		boolean previousState = checkbox.isSelected();
		checkbox.setSelected(false);
		FontMetrics boxFontMetrics = checkbox.getFontMetrics(checkbox.getFont());
		Icon boxIcon = UIManager.getIcon("CheckBox.icon");
		BufferedImage boxImage = new BufferedImage(
				boxIcon.getIconWidth(), boxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
		);
		Graphics graphics = boxImage.createGraphics();
		try {
			boxIcon.paintIcon(checkbox, graphics, 0, 0);
		} finally {
			graphics.dispose();
		}
		ImageIcon newBoxImage = new ImageIcon(boxImage);
		Image finalBoxImage = newBoxImage.getImage().getScaledInstance(
				boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
		);
		checkbox.setIcon(new ImageIcon(finalBoxImage));

		checkbox.setSelected(true);
		Icon checkedBoxIcon = UIManager.getIcon("CheckBox.icon");
		BufferedImage checkedBoxImage = new BufferedImage(
				checkedBoxIcon.getIconWidth(), checkedBoxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
		);
		Graphics checkedGraphics = checkedBoxImage.createGraphics();
		try {
			checkedBoxIcon.paintIcon(checkbox, checkedGraphics, 0, 0);
		} finally {
			checkedGraphics.dispose();
		}
		ImageIcon newCheckedBoxImage = new ImageIcon(checkedBoxImage);
		Image finalCheckedBoxImage = newCheckedBoxImage.getImage().getScaledInstance(
				boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
		);
		checkbox.setSelectedIcon(new ImageIcon(finalCheckedBoxImage));
		checkbox.setSelected(false);
		checkbox.setSelected(previousState);
	}
}
