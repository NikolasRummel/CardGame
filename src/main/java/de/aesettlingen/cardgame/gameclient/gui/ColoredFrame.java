package de.aesettlingen.cardgame.gameclient.gui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class ColoredFrame extends JFrame {

	public ColoredFrame() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.put("control", new Color(0, 0, 0));
			UIManager.put("info", new Color(0, 0, 0));
			UIManager.put("nimbusBase", new Color(18, 30, 49));
			UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
			UIManager.put("nimbusDisabledText", new Color(0, 0, 0));
			UIManager.put("nimbusFocus", new Color(115, 164, 209));
			UIManager.put("nimbusGreen", new Color(176, 179, 50));
			UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
			UIManager.put("nimbusLightBackground", new Color(30, 30, 30));
			UIManager.put("nimbusOrange", new Color(191, 98, 4));
			UIManager.put("nimbusRed", new Color(169, 46, 34));
			UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
			UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
			UIManager.put("text", new Color(0, 150, 255));
			UIManager.put("nimbusBlueGrey", new Color(10, 10, 10));

			//Custom
			UIManager.put("textFieldForeground", new Color(57, 183, 177));
			UIManager.put("hasPermissionColor", new Color(0, 255, 0));
			UIManager.put("hoverColor", new Color(0, 150, 255));

			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException exc) {
			System.err.println("Nimbus: Unsupported Look and feel!");
		}
	}
}