package InterfazGrafica;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class EstadisticasPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4961804806653851631L;
	private MainFrame mainFrame;
	
	public EstadisticasPanel(MainFrame mf) {
		
		this.mainFrame = mf;
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Serif", Font.BOLD, 14));
		btnVolver.setBounds(100, 20, 110, 30);
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
		
		JLabel lblTitulo = new JLabel("Estadísticas");
		lblTitulo.setBounds(300, 5, 300, 50);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 34));
		this.add(lblTitulo);
		
	
		JLabel lblInicioCaptura = new JLabel("Tiempo de Inicio de la Captura: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTiempoInicioToString());
		lblInicioCaptura.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInicioCaptura.setBounds(100 , 90, 400, 20);
		add(lblInicioCaptura);
		
		JLabel lblFinCaptura = new JLabel("Tiempo Fin de la Captura: "
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTiempoFinToString());
		lblFinCaptura.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFinCaptura.setBounds(100, 120, 400, 20);
		add(lblFinCaptura);
		
		JLabel lblNumIP = new JLabel("Número de Paquetes con Protocolo IP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesIP());
		lblNumIP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumIP.setBounds(100, 150, 400, 20);
		add(lblNumIP);
		
		JLabel lblNumICMP = new JLabel("Número de Paquetes con Protocolo ICMP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesICMP());
		lblNumICMP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumICMP.setBounds(100, 180, 400, 20);
		add(lblNumICMP);
		

		JLabel lblNumTCP = new JLabel("Número de Paquetes con Protocolo TCP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesTCP());
		lblNumTCP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumTCP.setBounds(100, 210, 400, 20);
		add(lblNumTCP);
		

		JLabel lblNumUDP = new JLabel("Número de Paquetes con Protocolo UDP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesUDP());
		lblNumUDP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumUDP.setBounds(100, 240, 400, 20);
		add(lblNumUDP);
		
		JLabel lblNumTotal = new JLabel("Número de Total de Paquetes: " + mainFrame.getAnalizador().getFiltros().getCaptura().getPaquetes().size());
		lblNumTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumTotal.setBounds(100, 270, 400, 20);
		add(lblNumTotal);
		
		JLabel lblTamIP = new JLabel("Tamaño de los paquetes con Protocolo IP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTamPaquetesIP() + " Bytes");
		lblTamIP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTamIP.setBounds(100, 300, 400, 20);
		add(lblTamIP);

		JLabel lblTamICMP = new JLabel("Tamaño de los paquetes con Protocolo ICMP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTamPaquetesICMP() + " Bytes");
		lblTamICMP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTamICMP.setBounds(100, 330, 400, 20);
		add(lblTamICMP);
		
		JLabel lblTamTCP = new JLabel("Tamaño de los paquetes con Protocolo TCP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTamPaquetesTCP() + " Bytes");
		lblTamTCP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTamTCP.setBounds(100, 360, 400, 20);
		add(lblTamTCP);
		
		JLabel lblTamUDP = new JLabel("Tamaño de los paquetes con Protocolo UDP: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTamPaquetesUDP() + " Bytes");
		lblTamUDP.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTamUDP.setBounds(100, 390, 400, 20);
		add(lblTamUDP);
		
		
		JLabel lblTamTotal = new JLabel("Tamaño total de los paquetes: " 
				+ mainFrame.getAnalizador().getFiltros().getCaptura().getTamPaquetesTotal() + " Bytes");
		lblTamTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTamTotal.setBounds(100, 420, 400, 20);
		add(lblTamTotal);
		
		
		DefaultPieDataset dataGSecT = new DefaultPieDataset();
		dataGSecT.setValue("TCP", mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesTCP());
				dataGSecT.setValue("UDP", mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesUDP());
		JFreeChart chartT = ChartFactory.createPieChart("Protocolos de Transporte", dataGSecT, true, true, true);
		BufferedImage imageT = chartT.createBufferedImage(200, 200);
		JLabel lblgraficoT = new JLabel();
		lblgraficoT.setIcon(new ImageIcon(imageT));
		lblgraficoT.setBounds(500, 25, 200, 200);
		lblgraficoT.setBackground(SystemColor.activeCaption);
		add(lblgraficoT);
		
		
		DefaultPieDataset dataGSecR = new DefaultPieDataset();
		dataGSecR.setValue("IP", mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesIP());
				dataGSecR.setValue("ICMP", mainFrame.getAnalizador().getFiltros().getCaptura().getNumPaquetesICMP());
		JFreeChart chartR = ChartFactory.createPieChart("Protocolos de Red", dataGSecR, true, true, true);
		BufferedImage imageR = chartR.createBufferedImage(200, 200);
		JLabel lblgraficoR = new JLabel();
		lblgraficoR.setIcon(new ImageIcon(imageR));
		lblgraficoR.setBounds(500, 250, 200, 200);
		lblgraficoR.setBackground(SystemColor.activeCaption);
		add(lblgraficoR);

		
		XYSeries series = new XYSeries("Paquetes");
		ArrayList<Integer> listaBytes = mainFrame.getAnalizador().getFiltros().getCaptura().getNumBytesPS();
		for(int i = 0; i < listaBytes.size(); i++) 
			series.add(i + 1, listaBytes.get(i));
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		JFreeChart chartBPS = ChartFactory.createXYLineChart("Número de Bytes por segundo",
				"Tiempo", "Bytes", dataset, PlotOrientation.VERTICAL, false, true,true);
		BufferedImage imageBPS = chartBPS.createBufferedImage(600, 250);
		JLabel lblgraficoBPS = new JLabel();
		lblgraficoBPS.setIcon(new ImageIcon(imageBPS));
		lblgraficoBPS.setBounds(100, 475, 600, 250);
		lblgraficoBPS.setBackground(SystemColor.activeCaption);
		add(lblgraficoBPS);

	}

}
