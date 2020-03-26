package InterfazGrafica;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SniffNet.Analizador;
import Utils.Utils;



public class FiltroPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textIP2;
	private JTextField textIP3;
	private JTextField textIP1;
	private JTextField textIP4;
	
	private MainFrame mainFrame;
	private Analizador analizador;
	private JTextField textP;
	/**
	 * Create the panel.
	 */
	public FiltroPanel(MainFrame mf) {
		
		mainFrame = mf;
		analizador = mf.getAnalizador();
		setLayout(null);
		
		JButton btnAbrirCaptura = new JButton("Abrir Captura");
		btnAbrirCaptura.setBounds(258, 690, 130, 40);
		this.add(btnAbrirCaptura);
		
		JButton btnCapturar = new JButton("Capturar");
		btnCapturar.setBounds(100, 690, 130, 40);
		this.add(btnCapturar);
		
		JLabel lblTitulo = new JLabel("Filtros");
		lblTitulo.setBounds(360, 5, 120, 50);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		this.add(lblTitulo);
		
		
	
//////////////////////////////////////////////////////////////////////////		
///////////////////// 		PANELES DEL FILTRO      //////////////////////
//////////////////////////////////////////////////////////////////////////		
		
		
/////////////////////////      FILTRO IP      ////////////////////////////
		
		JPanel filtroIPPanel = new JPanel();
		filtroIPPanel.setBounds(100, 230, 288, 430);
		filtroIPPanel.setBackground(SystemColor.activeCaption);
		this.add(filtroIPPanel);
		filtroIPPanel.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtro IP");
		lblFiltro.setBounds(12, 12, 80, 20);
		filtroIPPanel.add(lblFiltro);
		lblFiltro.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));

		textIP1 = new JTextField();
		textIP1.setBounds(109, 100, 30, 20);
		filtroIPPanel.add(textIP1);
		textIP1.setColumns(10);
		
		JLabel label_puntoID1 = new JLabel(".");
		label_puntoID1.setFont(new Font("Dialog", Font.BOLD, 18));
		label_puntoID1.setBounds(144, 105, 10, 10);
		filtroIPPanel.add(label_puntoID1);
		
		textIP2 = new JTextField();
		textIP2.setBounds(154, 100, 30, 20);
		filtroIPPanel.add(textIP2);
		textIP2.setColumns(10);
		
		JLabel label_puntoID2 = new JLabel(".");
		label_puntoID2.setFont(new Font("Dialog", Font.BOLD, 18));
		label_puntoID2.setBounds(189, 105, 10, 10);
		filtroIPPanel.add(label_puntoID2);
		
		textIP3 = new JTextField();
		textIP3.setBounds(199, 100, 30, 20);
		filtroIPPanel.add(textIP3);
		textIP3.setColumns(10);
		
		JLabel label_puntoID3 = new JLabel(".");
		label_puntoID3.setFont(new Font("Dialog", Font.BOLD, 18));
		label_puntoID3.setBounds(234, 105, 10, 10);
		filtroIPPanel.add(label_puntoID3);
		
		textIP4 = new JTextField();
		textIP4.setBounds(244, 100, 30, 20);
		filtroIPPanel.add(textIP4);
		textIP4.setColumns(10);
		
		JLabel lblDirIP = new JLabel("Dirección IP:");
		lblDirIP.setBounds(12, 100, 100, 15);
		filtroIPPanel.add(lblDirIP);
		
		JButton btnAnadirIP = new JButton("Añadir");
		btnAnadirIP.setBackground(new Color(0, 255, 0));
		btnAnadirIP.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAnadirIP.setBounds(12, 142, 120, 30);
		filtroIPPanel.add(btnAnadirIP);
		
		DefaultListModel<String> ipODListModel = new DefaultListModel<String>();
		JScrollPane scrollPane_listIPO = new JScrollPane();
		scrollPane_listIPO.setBounds(12, 210, 120, 146);
		filtroIPPanel.add(scrollPane_listIPO);
		JList<String> listIPO = new JList<String>(ipODListModel);
		scrollPane_listIPO.setViewportView(listIPO);
		
		DefaultListModel<String> ipDDListModel = new DefaultListModel<String>();
		JScrollPane scrollPane_listIPD = new JScrollPane();
		scrollPane_listIPD.setBounds(156, 210, 120, 146);
		filtroIPPanel.add(scrollPane_listIPD);
		JList<String> listIPD = new JList<String>(ipDDListModel);
		scrollPane_listIPD.setViewportView(listIPD);
		
		
		JButton btnBorrarIP = new JButton("Borrar");
		btnBorrarIP.setBackground(new Color(250, 128, 114));
		btnBorrarIP.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnBorrarIP.setBounds(12, 378, 120, 30);
		btnBorrarIP.setVisible(false);
		filtroIPPanel.add(btnBorrarIP);
		
		JCheckBox chckbxOrigenIP = new JCheckBox("Origen");
		chckbxOrigenIP.setBackground(SystemColor.activeCaption);
		chckbxOrigenIP.setBounds(12, 61, 78, 23);
		filtroIPPanel.add(chckbxOrigenIP);
		
		JCheckBox chckbxDestinoIP = new JCheckBox("Destino");
		chckbxDestinoIP.setBackground(SystemColor.activeCaption);
		chckbxDestinoIP.setBounds(130, 61, 88, 23);
		filtroIPPanel.add(chckbxDestinoIP);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(12, 196, 48, 14);
		filtroIPPanel.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(156, 196, 48, 14);
		filtroIPPanel.add(lblDestino);
		
		
		btnAnadirIP.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
	            
				int ip1, ip2, ip3, ip4;
				
				try {
					
					ip1 = Integer.parseInt(textIP1.getText());
					ip2 = Integer.parseInt(textIP2.getText());
					ip3 = Integer.parseInt(textIP3.getText());
					ip4 = Integer.parseInt(textIP4.getText());
                    
                     
                    } catch (Exception z) { 
                        JOptionPane.showMessageDialog(btnAnadirIP, "Dirección IP incorrecta, carácter no reconocido.", null, JOptionPane.ERROR_MESSAGE);
                        return;
                }
				
				if(!chckbxOrigenIP.isSelected() && !chckbxDestinoIP.isSelected()) {
					
					JOptionPane.showMessageDialog(btnAnadirIP, "Falta marcar Origen y/o Destino.", "", JOptionPane.ERROR_MESSAGE);
					
				} else if((ip1<256 && ip1>=0) && (ip2<256 && ip2>=0) && (ip3<256 && ip3>=0) && (ip4<256 && ip4>=0)) {
					
					String ip  = new String(ip1+"."+ip2+"."+ip3+"."+ip4);
					
					if(chckbxOrigenIP.isSelected())
						Utils.addToListModel(ipODListModel, ip);
					
					if(chckbxDestinoIP.isSelected())
						Utils.addToListModel(ipDDListModel, ip);
					
					if(!btnBorrarIP.isVisible())
						btnBorrarIP.setVisible(true);
					
					chckbxOrigenIP.setSelected(false);
					chckbxDestinoIP.setSelected(false);
					
				} else {
					JOptionPane.showMessageDialog(btnAnadirIP, "Dirección IP incorrecta, número fuera de rango.", null, JOptionPane.ERROR_MESSAGE);
				}
				
				
				
	        }  
	    });
		
		
		
		btnBorrarIP.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				 
				for(String ipO: listIPO.getSelectedValuesList()) {
					ipODListModel.removeElement(ipO); 
				}
				
				for(String ipD: listIPD.getSelectedValuesList()) {
					ipDDListModel.removeElement(ipD); 
				}
				
				if(ipODListModel.isEmpty() && ipDDListModel.isEmpty())
					btnBorrarIP.setVisible(false);	
				
			}
		});
		
		
/////////////////////////   FILTRO INTERFAZ   ////////////////////////////
		
		JPanel filtroInterfazPanel = new JPanel();
		filtroInterfazPanel.setBounds(100, 60, 288, 150);
		filtroInterfazPanel.setBackground(SystemColor.activeCaption);
		this.add(filtroInterfazPanel);
		filtroInterfazPanel.setLayout(null);
		
		JLabel lblFiltroInterfaz = new JLabel("Filtro Interfaz");
		lblFiltroInterfaz.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		lblFiltroInterfaz.setBounds(12, 12, 122, 23);
		filtroInterfazPanel.add(lblFiltroInterfaz);
		
		JComboBox<String> cbxInterfaz = new JComboBox<String>();
		cbxInterfaz.setBounds(12, 100, 264, 24);
		ArrayList<String> interfaces = analizador.getListInt();
		for(String interfaz: interfaces)
			cbxInterfaz.addItem(interfaz);
		filtroInterfazPanel.add(cbxInterfaz);
		
		JLabel lblInterfaz = new JLabel("Interfaz:");
		lblInterfaz.setBounds(12, 60, 70, 15);
		filtroInterfazPanel.add(lblInterfaz);
		

		
/////////////////////////    FILTRO PUERTO    ////////////////////////////		
		
		
		JPanel filtroPuertoPanel = new JPanel();
		filtroPuertoPanel.setBounds(412, 230, 288, 430);
		filtroPuertoPanel.setBackground(SystemColor.activeCaption);
		this.add(filtroPuertoPanel);
		filtroPuertoPanel.setLayout(null);
		
		JLabel lblFiltroPuerto = new JLabel("Filtro Puerto");
		lblFiltroPuerto.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		lblFiltroPuerto.setBounds(12, 12, 120, 23);
		filtroPuertoPanel.add(lblFiltroPuerto);
		
		JCheckBox chckbxOrigenPu = new JCheckBox("Origen");
		chckbxOrigenPu.setBackground(SystemColor.activeCaption);
		chckbxOrigenPu.setBounds(12, 61, 97, 23);
		filtroPuertoPanel.add(chckbxOrigenPu);
		
		JCheckBox chckbxDestinoPu = new JCheckBox("Destino");
		chckbxDestinoPu.setBackground(SystemColor.activeCaption);
		chckbxDestinoPu.setBounds(130, 61, 97, 23);
		filtroPuertoPanel.add(chckbxDestinoPu);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(12, 100, 48, 14);
		filtroPuertoPanel.add(lblPuerto);
		
		textP = new JTextField();
		textP.setBounds(65, 100, 96, 20);
		filtroPuertoPanel.add(textP);
		textP.setColumns(10);
		
		JButton btnBorrarPu = new JButton("Borrar");
		btnBorrarPu.setFont(new Font("Serif", Font.BOLD, 16));
		btnBorrarPu.setBackground(new Color(250, 128, 114));
		btnBorrarPu.setBounds(12, 378, 120, 30);
		filtroPuertoPanel.add(btnBorrarPu);
		btnBorrarPu.setVisible(false);
		
		JButton btnAnadirPu = new JButton("Añadir");
		btnAnadirPu.setFont(new Font("Serif", Font.BOLD, 16));
		btnAnadirPu.setBackground(new Color(0, 255, 0));
		btnAnadirPu.setBounds(12, 142, 120, 30);
		filtroPuertoPanel.add(btnAnadirPu);
		
		DefaultListModel<String> puertoODListModel = new DefaultListModel<String>();
		JScrollPane scrollPane_puertoOList = new JScrollPane();
		scrollPane_puertoOList.setBounds(12, 210, 120, 146);
		filtroPuertoPanel.add(scrollPane_puertoOList);
		JList<String> puertoOList = new JList<String>(puertoODListModel);
		scrollPane_puertoOList.setViewportView(puertoOList);
		
		DefaultListModel<String> puertoDDListModel = new DefaultListModel<String>();
		JScrollPane scrollPane_puertoDList = new JScrollPane();
		scrollPane_puertoDList.setBounds(156, 210, 120, 146);
		filtroPuertoPanel.add(scrollPane_puertoDList);
		JList<String> puertoDList = new JList<String>(puertoDDListModel);
		scrollPane_puertoDList.setViewportView(puertoDList);
		
		JLabel lblOrigen_1 = new JLabel("Origen");
		lblOrigen_1.setBounds(12, 195, 48, 14);
		filtroPuertoPanel.add(lblOrigen_1);
		
		JLabel lblDestino_1 = new JLabel("Destino");
		lblDestino_1.setBounds(156, 195, 48, 14);
		filtroPuertoPanel.add(lblDestino_1);
		
		
		
		btnAnadirPu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				int puerto;
				
				try {
					
					puerto = Integer.parseInt(textP.getText());
					
                     
                    } catch (Exception z) { 
                        JOptionPane.showMessageDialog(btnAnadirIP, "Puerto incorrecto, carácter no reconocido.", null, JOptionPane.ERROR_MESSAGE);
                        return;
                }
				
				if(!chckbxOrigenPu.isSelected() && !chckbxDestinoPu.isSelected()) {
					
					JOptionPane.showMessageDialog(btnAnadirIP, "Falta marcar Origen y/o Destino.", "", JOptionPane.ERROR_MESSAGE);
					
				} else if(puerto<65536 && puerto>0) {
					
					if(chckbxOrigenPu.isSelected())
						Utils.addToListModel(puertoODListModel, textP.getText());
					
					if(chckbxDestinoPu.isSelected())
						Utils.addToListModel(puertoDDListModel, textP.getText());
										
					if(!btnBorrarPu.isVisible())
						btnBorrarPu.setVisible(true);
					
					chckbxOrigenPu.setSelected(false);
					chckbxDestinoPu.setSelected(false);

					
				} else {
					JOptionPane.showMessageDialog(btnAnadirIP, "Puerto incorrecto, número fuera de rango.", null, JOptionPane.ERROR_MESSAGE);
				}
				

					
				
			}
		});

		btnBorrarPu.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				for(String puertoD: puertoDList.getSelectedValuesList()) 
					puertoDDListModel.removeElement(puertoD);
				
				for(String puertoO: puertoOList.getSelectedValuesList()) 
					puertoODListModel.removeElement(puertoO);
				
				
				if(puertoODListModel.isEmpty() && puertoDDListModel.isEmpty())
					btnBorrarPu.setVisible(false);	
				
			}
		});


		
//////////////////////////   FILTRO PROTOCOLO   ////////////////////////////
		

		JPanel filtroProtocoloPanel = new JPanel();
		filtroProtocoloPanel.setBounds(412, 60, 288, 150);
		filtroProtocoloPanel.setBackground(SystemColor.activeCaption);
		this.add(filtroProtocoloPanel);
		filtroProtocoloPanel.setLayout(null);
		
		JLabel lblFiltroProtocolo = new JLabel("Filtro Protocolo");
		lblFiltroProtocolo.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		lblFiltroProtocolo.setBounds(12, 12, 152, 20);
		filtroProtocoloPanel.add(lblFiltroProtocolo);
		
		JLabel lblCapaDeTransporte = new JLabel("Capa de Transporte :");
		lblCapaDeTransporte.setFont(new Font("Serif", Font.BOLD, 14));
		lblCapaDeTransporte.setBounds(12, 90, 130, 30);
		filtroProtocoloPanel.add(lblCapaDeTransporte);
		
		JLabel lblCapaDeRed = new JLabel("Capa de Red :");
		lblCapaDeRed.setFont(new Font("Serif", Font.BOLD, 14));
		lblCapaDeRed.setBounds(12, 55, 120, 30);
		filtroProtocoloPanel.add(lblCapaDeRed);
		
		JComboBox<String> cbxTransporte = new JComboBox<String>();
		cbxTransporte.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS","TCP","UDP"}));
		cbxTransporte.setBounds(171, 96, 70, 22);
		filtroProtocoloPanel.add(cbxTransporte);
		
		JComboBox<String> cbxRed = new JComboBox<String>();
		cbxRed.setModel(new DefaultComboBoxModel<String>(new String[] {"TODOS", "IP", "ICMP"}));
		cbxRed.setBounds(171, 61, 70, 22);
		filtroProtocoloPanel.add(cbxRed);
		
		cbxRed.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				cbxTransporte.setVisible(!cbxRed.getItemAt(cbxRed.getSelectedIndex()).contentEquals("ICMP"));
				lblCapaDeTransporte.setVisible(!cbxRed.getItemAt(cbxRed.getSelectedIndex()).contentEquals("ICMP"));
				
			}
		});
		
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

		btnCapturar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
			analizador.setInterfaz( cbxInterfaz.getItemAt(cbxInterfaz.getSelectedIndex()));
			
			ArrayList<String> tipoProt = new ArrayList<String>();
			
			if(cbxTransporte.getItemAt(cbxTransporte.getSelectedIndex()).contentEquals("TODOS")) {
				tipoProt.add("TCP");
				tipoProt.add("UDP");
			} else {
				tipoProt.add(cbxTransporte.getItemAt(cbxTransporte.getSelectedIndex()));
			}
			
			if(cbxRed.getItemAt(cbxRed.getSelectedIndex()).contentEquals("TODOS")) {
				tipoProt.add("IP");
				tipoProt.add("ICMP");
			} else {
				tipoProt.add(cbxRed.getItemAt(cbxRed.getSelectedIndex()));
			}
			
			analizador.setFiltros(Utils.parseDefaulListModelToArrayList(ipODListModel), 
					Utils.parseDefaulListModelToArrayList(ipDDListModel), 
					Utils.parseDefaulListModelToArrayList(puertoODListModel), 
					Utils.parseDefaulListModelToArrayList(puertoDDListModel),
					tipoProt);
			
			
			JPanel listPanel = new ListPanel(mainFrame, false);
			listPanel.setBounds(0, 0, 800, 800);
			listPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			listPanel.setLayout(null);
			mainFrame.setContentPane(listPanel);
	

			}
		});
		
		btnAbrirCaptura.addActionListener(new ActionListener(){  
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
						JOptionPane.showMessageDialog(mainFrame, 
								"Archivo no compatible, el archivo debe ser de tipo .pcap",
								"Archivo no compatible", JOptionPane.ERROR_MESSAGE);
					}
						
				}	
				
			}
		});

	}

}
