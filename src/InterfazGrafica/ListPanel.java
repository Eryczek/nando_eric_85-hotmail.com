package InterfazGrafica;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import SniffNet.Analizador;
import SniffNet.Paquete;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class ListPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9151125134675923549L;
	private Analizador analizador;
	private MainFrame mainFrame;
	private DefaultListModel<String> listModel;
	private JButton btnEmpezar;
	private JLabel lblNumeroDePaquetes;
	private JSpinner spinner;
	
	/**
	 * Create the panel.
	 */
	public ListPanel(MainFrame mf, boolean setList) {
		
		mainFrame = mf;
	    analizador = mainFrame.getAnalizador();
	    
	    setPanel();
	    
	    if(setList) { 
	    	setList();
	    	btnEmpezar.setVisible(false);
	    	spinner.setVisible(false);
	    	lblNumeroDePaquetes.setVisible(false);
	    }    
	}
	
	
	public void setPanel() {

		this.setLayout(null);

		listModel = new DefaultListModel<String>();
	
		JLabel lblTitulo = new JLabel("Captura");
		lblTitulo.setBounds(340, 5, 120, 50);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		this.add(lblTitulo);
		

		lblNumeroDePaquetes = new JLabel("N\u00FAmero de Paquetes:");
		lblNumeroDePaquetes.setFont(new Font("Serif", Font.BOLD, 12));
		lblNumeroDePaquetes.setBounds(100, 11, 132, 20);
		add(lblNumeroDePaquetes);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(100, 50, 1000, 10));
		spinner.setBounds(100, 35, 68, 20);
		add(spinner);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 150, 600, 500);
		this.add(scrollPane);
		
		JList<String> list = new JList<String>(listModel);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mEvent) {
				
			
				JList<String> lista = new JList<String>();
				
				lista = extracted(mEvent);
				
				JPanel PaquetePanel = new PaquetePanel(
						analizador.getFiltros().getCaptura().getPaquetes().get(
								lista.locationToIndex(mEvent.getPoint())), mainFrame);
				PaquetePanel.setBounds(0, 0, 800, 800);
				PaquetePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
				PaquetePanel.setLayout(null);
				
				mainFrame.setContentPane(PaquetePanel);
			}

			@SuppressWarnings("unchecked")
			private JList<String> extracted(MouseEvent mEvent) {
				return (JList<String>) mEvent.getSource();
			}
		});
	

		btnEmpezar = new JButton("Iniciar Captura");
		btnEmpezar.setFont(new Font("Serif", Font.BOLD, 14));
		btnEmpezar.setBounds(100, 70, 132, 30);
		this.add(btnEmpezar);


		
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				analizador.capturar(listModel,Integer.parseInt(spinner.getValue().toString()));
				btnEmpezar.setVisible(false);
				spinner.setVisible(false);
				lblNumeroDePaquetes.setVisible(false);
			}
		});
	
	
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Serif", Font.BOLD, 14));
		btnGuardar.setBounds(100, 680, 132, 30);
		this.add(btnGuardar);
	
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(mainFrame.getAnalizador().getFiltros().getCaptura() != null) {

					JFileChooser fileChooser = new JFileChooser();
					int valorGuardar = fileChooser.showSaveDialog(mainFrame);
					
					if(valorGuardar == JFileChooser.APPROVE_OPTION) {
						
						File archivo = fileChooser.getSelectedFile();
						
						if(archivo != null) 
							analizador.guardarCaptura(archivo);
						
					}

					
				} else {
					
					JOptionPane.showMessageDialog(mainFrame, "No hay ninguna captura disponible", "Guardar captura", JOptionPane.ERROR_MESSAGE);
					
				}				
				
			}
		});
	
	
		JButton btnAbrir = new JButton("Abrir Captura");
		btnAbrir.setFont(new Font("Serif", Font.BOLD, 14));
		btnAbrir.setBounds(256, 680, 132, 30);
		this.add(btnAbrir);
		
		btnAbrir.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int valorAbrir = fileChooser.showOpenDialog(mainFrame);
				
				if(valorAbrir == JFileChooser.APPROVE_OPTION) {
					
					File archivo = fileChooser.getSelectedFile();

					if(archivo != null && archivo.getName().endsWith(".pcap")) {
						

					analizador.abrirCaptura(archivo);
			
					JPanel listPanel = new ListPanel(mainFrame, true);
					listPanel.setBounds(0, 0, 800, 800);
					listPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
					listPanel.setLayout(null);
					
					mainFrame.setContentPane(listPanel);

						
					} else {
						JOptionPane.showMessageDialog(mainFrame, "Archivo no compatible, el archivo debe ser de tipo .pcap", "Archivo no compatible", JOptionPane.ERROR_MESSAGE);
					}
						
				}	
				
			}
		});
		
		JButton btnCapturar = new JButton("Nueva Captura");
		btnCapturar.setFont(new Font("Serif", Font.BOLD, 14));
		btnCapturar.setBounds(412, 680, 132, 30);
		this.add(btnCapturar);
		
		btnCapturar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
		
				JPanel filtroPanel = new FiltroPanel(mainFrame);
				filtroPanel.setBounds(0, 0, 800, 800);
				mainFrame.setContentPane(filtroPanel);
				filtroPanel.setLayout(null);
	
			}
		});
		
		JButton btnEstadisticas = new JButton("Estadísticas");
		btnEstadisticas.setFont(new Font("Serif", Font.BOLD, 14));
		btnEstadisticas.setBounds(568, 680, 125, 30);
		this.add(btnEstadisticas);
		
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(mainFrame.getAnalizador().getFiltros().getCaptura() != null) {
					
					JPanel estadisticasPanel = new EstadisticasPanel(mainFrame);
					estadisticasPanel.setBounds(0, 0, 800, 800);
					estadisticasPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
					estadisticasPanel.setLayout(null);
					
					mainFrame.setContentPane(estadisticasPanel);
					
				} else {
					
					JOptionPane.showMessageDialog(mainFrame, "No hay ninguna captura disponible", "Abrir estadisticas", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
		});
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Serif", Font.BOLD, 12));
		lblId.setBounds(106, 130, 15, 20);
		this.add(lblId);
		
		JLabel lblIpOrigen = new JLabel("IP Origen");
		lblIpOrigen.setFont(new Font("Serif", Font.BOLD, 12));
		lblIpOrigen.setBounds(197, 130, 65, 20);
		this.add(lblIpOrigen);
		
		JLabel lblTipoDePaquete = new JLabel("Tipo de");
		lblTipoDePaquete.setFont(new Font("Serif", Font.BOLD, 12));
		lblTipoDePaquete.setBounds(137, 110, 50, 20);
		this.add(lblTipoDePaquete);
	
		JLabel lblIpDestino = new JLabel("IP Destino");
		lblIpDestino.setFont(new Font("Serif", Font.BOLD, 12));
		lblIpDestino.setBounds(300, 130, 65, 20);
		this.add(lblIpDestino);
		
		JLabel lblPuertoD = new JLabel("Puerto");
		lblPuertoD.setFont(new Font("Serif", Font.BOLD, 12));
		lblPuertoD.setBounds(452, 96, 60, 20);
		this.add(lblPuertoD);
	
		JLabel lblPaquete = new JLabel("Paquete");
		lblPaquete.setFont(new Font("Serif", Font.BOLD, 12));
		lblPaquete.setBounds(137, 130, 50, 20);
		this.add(lblPaquete);
	
		JLabel lblOrigen = new JLabel("Destino");
		lblOrigen.setFont(new Font("Serif", Font.BOLD, 12));
		lblOrigen.setBounds(452, 130, 60, 20);
		this.add(lblOrigen);
	
		JLabel lblDestino = new JLabel("Origen");
		lblDestino.setFont(new Font("Serif", Font.BOLD, 12));
		lblDestino.setBounds(404, 130, 60, 20);
		this.add(lblDestino);
	
		JLabel lblPuertoO = new JLabel("Puerto");
		lblPuertoO.setFont(new Font("Serif", Font.BOLD, 12));
		lblPuertoO.setBounds(404, 96, 50, 20);
		this.add(lblPuertoO);
	
		JLabel lblDeDe = new JLabel("   de             de");
		lblDeDe.setFont(new Font("Serif", Font.BOLD, 12));
		lblDeDe.setBounds(404, 113, 120, 20);
		this.add(lblDeDe);
	
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Serif", Font.BOLD, 12));
		lblHora.setBounds(509, 130, 160, 20);
		this.add(lblHora);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Serif", Font.BOLD, 12));
		lblFecha.setBounds(595, 130, 160, 20);
		this.add(lblFecha);

	}
	
	public void setList() {
				
		for(Paquete p : analizador.getFiltros().getCaptura().getPaquetes()) 	
			this.listModel.addElement(p.paqueteToString());
		
	}
}
