package InterfazGrafica;
import java.awt.EventQueue;

import javax.swing.*;

import SniffNet.Analizador;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private FiltroPanel filtroPanel;
	
	private Analizador analizador;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("SniffNet");
		
		analizador = new Analizador();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		getContentPane().setLayout(null);
		
		filtroPanel = new FiltroPanel(this);
		filtroPanel.setBounds(0, 0, 800, 800);
		setContentPane(filtroPanel);
		filtroPanel.setLayout(null);
	}
	
	
	public Analizador getAnalizador() {
		return this.analizador;
	}
}