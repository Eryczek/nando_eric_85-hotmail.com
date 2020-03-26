package SniffNet;

import java.util.ArrayList;
import java.util.Date;

import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class Filtros {
	
	private ArrayList<String> filtroIPOrigen, filtroPuertoDestino, filtroPuertoOrigen, filtroIPDestino, filtroTipo;
	
	Captura captura;
	
	public Filtros() {

		this.filtroIPOrigen = new ArrayList<String>();
		this.filtroIPDestino = new ArrayList<String>();
		this.filtroPuertoOrigen = new ArrayList<String>();
		this.filtroPuertoDestino = new ArrayList<String>();
		this.filtroTipo =  new ArrayList<String>();
		captura = null;
		
		
	}
	
	@SuppressWarnings("unused")
	public Paquete aceptarPaquete(Packet p) {
		
		boolean encontrado = false;
		String srcP = "", dstP = "", srcIp = "", dstIp = "", tipoR = "", tipoT = "";
		
		if(p instanceof TCPPacket) {
			TCPPacket portP = (TCPPacket) p;
			srcP = String.valueOf(portP.src_port);
			dstP = String.valueOf(portP.dst_port);
			tipoT = "TCP";
		}
		if(p instanceof UDPPacket) {
			UDPPacket portP = (UDPPacket) p;
			srcP = String.valueOf(portP.src_port);
			dstP = String.valueOf(portP.dst_port);
			tipoT = "UDP";
		}
		
		if(p instanceof ICMPPacket) {
			ICMPPacket ipP = (ICMPPacket) p;
			srcIp = ipP.src_ip.getHostAddress();
			dstIp = ipP.dst_ip.getHostAddress();
			srcP = "-";
			dstP = "-";
			tipoR = "ICMP";
		} else if(p instanceof IPPacket) {
			IPPacket ipP = (IPPacket) p;
			srcIp = ipP.src_ip.getHostAddress();
			dstIp = ipP.dst_ip.getHostAddress();
			tipoR = "IP";
		}
		
		if(tipoT==null && tipoR == null)
			return null;
		

		if(!(checkFiltro(filtroIPOrigen, srcIp) || checkFiltro(filtroIPDestino, dstIp)) || dstIp.length()>16|| srcIp.length()>16) 
			return null;
		
		
			
		for(String s: filtroTipo)
			if(s.contentEquals(tipoR)) 
				encontrado = true;
						
		if(encontrado == false)  
			return null;
		
		encontrado = false;
		
		if(tipoR.contentEquals("IP")) {
			for(String s: filtroTipo)
				if(s.contentEquals(tipoT)) 
					encontrado = true;
			
			if(encontrado == false)  
				return null;

		}
		
		if(!(p instanceof ICMPPacket)) 
			if(!(checkFiltro(filtroPuertoDestino, dstP) || checkFiltro(filtroPuertoOrigen, srcP)))
				return null;
			
					
		return new Paquete(p, new Date(System.currentTimeMillis()), tipoR, tipoT, srcIp, dstIp, srcP, dstP);
	}
	
	private boolean checkFiltro(ArrayList<String> list, String elem) {
		if(!list.isEmpty()) {
			for(String s: list) 
				if(s.contentEquals(elem)) 
					return true;
				
			return false;
		}
		
		return true;
	}
	
	public void setFiltros(ArrayList<String> fIPO, 
			ArrayList<String> fIPD, 
			ArrayList<String> fPuertoO, 
			ArrayList<String> fPuertoD, 
			ArrayList<String> fTipo) {

		this.filtroIPOrigen = fIPO;
		this.filtroIPDestino = fIPD;
		this.filtroPuertoOrigen = fPuertoO;
		this.filtroPuertoDestino = fPuertoD;
		this.filtroTipo = fTipo;
	}
	
	
	public void setfiltroTipo(ArrayList<String> fT) {
		this.filtroTipo = fT;
	}
	
	public void setCaptura(Captura c) {
		this.captura = c;
	}
	
	public Captura getCaptura() {
		return this.captura;
	}

}
