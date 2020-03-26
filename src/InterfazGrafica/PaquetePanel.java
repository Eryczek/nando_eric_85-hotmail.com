package InterfazGrafica;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SniffNet.Paquete;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PaquetePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5427018386893112713L;
	/**
	 * Create the panel.
	 */
	private MainFrame mainFrame;
	
	public PaquetePanel(Paquete paquete, MainFrame mf) {
		
		this.mainFrame = mf;
		int x = 0, y=0, capa=0, prot=0;
		
		JLabel lblTitulo = new JLabel("Información del Paquete");
		lblTitulo.setBounds(200, 5, 400, 50);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		this.add(lblTitulo);
		
		
		
		for(String data: paquete.mostrarInformacion()) {
			
			if(data.startsWith("Capa")) {
				x= x+3;
				capa++;
			}
			
			if(capa==2 && data.startsWith("- Tipo de Paquete")) {
				prot++;
				if(prot == 2) {
					x = 14;
					y = 350;
				}
			}
			
			if(capa==3 && y==0) {
				x = 4;
				y = 350;
			}
			
			
			JLabel lblInfoPaquete = new JLabel(data);
			lblInfoPaquete.setFont(new Font("Times New Roman", Font.BOLD, 14));
			lblInfoPaquete.setBounds(100 + y , 150 + (x*20), 600, 20);
			add(lblInfoPaquete);
		
			x++ ;
		}
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Serif", Font.BOLD, 14));
		btnVolver.setBounds(590, 670, 110, 30);
		this.add(btnVolver);
	
	
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel listPanel = new ListPanel(mainFrame, true);
				listPanel.setBounds(0, 0, 800, 800);
				listPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
				listPanel.setLayout(null);
				
				mainFrame.setContentPane(listPanel);
				
			}
		});
	
	}
}
