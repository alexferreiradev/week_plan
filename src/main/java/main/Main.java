package main;

import view.frame.MainFrame;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		
		System.out.println("Iniciando...");
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.showFrame();
			}
		});
	}

}
