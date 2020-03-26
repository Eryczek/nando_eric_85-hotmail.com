package SniffNet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import jpcap.JpcapCaptor;
import jpcap.JpcapWriter;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;

public class Analizador {
	
	private Filtros filtros;
	private NetworkInterface interfaz;
	
	private JpcapCaptor capturador;
	
	private final NetworkInterface[] interfaces;

	public Analizador(){
		
		this.interfaces = JpcapCaptor.getDeviceList();
		
		this.filtros = new Filtros();
		this.capturador = null;
	}
	
	public void setCapturador() {
		
		try {
			capturador = JpcapCaptor.openDevice(interfaz, 65535, true, 1000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Paquete capturarPaquete(int i) {
		
		Paquete paquete = null;
		Packet p = null;
		
		while(p == null) {
			
			p = capturador.getPacket();
			
			if(p!= null || p != Packet.EOF)
				paquete = filtros.aceptarPaquete(p);
			
			if(paquete == null && p != null)
				p = null;
		}
		
		paquete.setId(i);
		
		return paquete;
	
	}
	
	public void capturar(DefaultListModel<String> list, int numPaquetes) {
	
		ArrayList<Paquete> paquetes = new ArrayList<Paquete>();	
		Date initDate = new Date(System.currentTimeMillis());
		
		setCapturador();
		
		for(int i=1; i<=numPaquetes; i++) {
			
			Paquete p = capturarPaquete(i);
			
			paquetes.add(p);
			list.addElement(p.paqueteToString());

		}
		
		this.filtros.setCaptura(new Captura(initDate, new Date(System.currentTimeMillis()), paquetes));
			
		this.capturador.close();
	}
	
	
	public ArrayList<String> getListInt(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i=0; i<interfaces.length; i++) {
			list.add(interfaces[i].description);
		}
		
		return list;
		
	}
	
	public void guardarCaptura(File archivo) {
		
		JpcapWriter escritor = null;
		
		if(!archivo.getName().endsWith(".pcap"))
			archivo = new File(archivo.getParentFile(), archivo.getName() + ".pcap");
		
		if(archivo.exists()) 
			if(JOptionPane.showConfirmDialog(null, 
					"Desea sobreescribir el archivo" + archivo.getName() + "?",
					"sobreescribir", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) 
				return;
		
		
		try {
			capturador = JpcapCaptor.openDevice(JpcapCaptor.getDeviceList()[0], 65535, true, 10000);
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo: " + archivo.getPath());
		}
		
		try {
			escritor = JpcapWriter.openDumpFile(capturador, archivo.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		for(Paquete p: filtros.getCaptura().getPaquetes()) 
			escritor.writePacket(p.getPaquete());
		
		capturador.close();
		
	
	}

	public void abrirCaptura(File archivo) {
		
		ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
		ArrayList<String> fT = new ArrayList<String>();
		Date initDate = null, endDate = null;
		int id = 1; 

		filtros = new Filtros();
		
		try {
			
			capturador = JpcapCaptor.openFile(archivo.getPath());
			
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido abrir el archivo " + archivo.getName(), " ",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		Packet p = null;
		
		fT.add("TCP");
		fT.add("UDP");
		fT.add("IP");
		fT.add("ICMP");
		
		filtros.setfiltroTipo(fT);
		
		
		while(true) {
			
			p = capturador.getPacket();
			
			if(p == Packet.EOF)
				break;
			
			if(p != null) {
				
				if(id==1)
					initDate = new Date(p.sec * 1000);
				
				Paquete paquete = filtros.aceptarPaquete(p);
				
				if(paquete != null) {
					
					paquete.setId(id);
					id++;
					paquete.setTimeCaptura(new Date(p.sec*1000));
					
					paquetes.add(paquete);
					endDate = new Date(p.sec * 1000);
				}
			}	
		}
		
		filtros.setCaptura(new Captura(initDate, endDate, paquetes));
		
		capturador.close();
    }

	public NetworkInterface getInterfaz() {
		return interfaz;
	}


	public void setInterfaz(String interfaz) {
		
		for(NetworkInterface ni: interfaces) {
			if(ni.description.contentEquals(interfaz)) {
				this.interfaz = ni;
				return;
			}
		}
		
		this.interfaz = null;
		
		
	}


	public void setFiltros(ArrayList<String> fIPO, 
			ArrayList<String> fIPD, 
			ArrayList<String> fPuertoO, 
			ArrayList<String> fPuertoD, 
			ArrayList<String> fTipo) {
		
		this.filtros.setFiltros(fIPO, fIPD, fPuertoO, fPuertoD, fTipo);
	}
	

	public Filtros getFiltros() {
		return this.filtros;
	}
}