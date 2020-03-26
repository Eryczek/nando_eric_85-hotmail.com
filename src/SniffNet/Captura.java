package SniffNet;

import java.util.ArrayList;
import java.util.Date;

import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class Captura {
	
	private Date tiempoInicio, tiempoFin;
	private ArrayList<Paquete> paquetes;
	
	public Captura(Date tInicio, Date tFin, ArrayList<Paquete> paquetes) {
		
		this.tiempoInicio = tInicio;
		this.tiempoFin = tFin;
		this.paquetes = paquetes;
		
	}
		
	public ArrayList<Paquete> getPaquetes(){
		return paquetes;
	}
	
	@SuppressWarnings("deprecation")
	public String getTiempoInicioToString() {
		String hour, minutes, seconds;
		
		hour = String.valueOf(tiempoInicio.getHours());
		if(hour.length()==1)
			hour = "0" + hour;
		minutes = String.valueOf(tiempoInicio.getMinutes());
		if(minutes.length()==1)
			minutes = "0" + minutes;
		seconds = String.valueOf(tiempoInicio.getSeconds());
		if(seconds.length()==1)
			seconds = "0" + seconds;
		
		return hour + ":" + minutes + ":" + seconds;
	}
	
	@SuppressWarnings("deprecation")
	public String getTiempoFinToString() {

        String hour, minutes, seconds;
		
		hour = String.valueOf(tiempoFin.getHours());
		if(hour.length()==1)
			hour = "0" + hour;
		minutes = String.valueOf(tiempoFin.getMinutes());
		if(minutes.length()==1)
			minutes = "0" + minutes;
		seconds = String.valueOf(tiempoFin.getSeconds());
		if(seconds.length()==1)
			seconds = "0" + seconds;
		
		return hour + ":" + minutes + ":" + seconds;
		
	}
	
	public int getNumPaquetesICMP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof ICMPPacket)
				i++;
		
		return i;
	}
	
	public int getNumPaquetesIP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof IPPacket && !(p.getPaquete() instanceof ICMPPacket))
				i++;
		
		return i;
	}
	
	public int getNumPaquetesUDP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof UDPPacket)
				i++;
		
		return i;
	}
	
	public int getNumPaquetesTCP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof TCPPacket)
				i++;
		
		return i;
	}
	
	public int getTamPaquetesICMP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof ICMPPacket)
				i += p.packet.len;
		
		return i;
	}
	
	public int getTamPaquetesIP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof IPPacket && !(p.getPaquete() instanceof ICMPPacket))
				i += p.packet.len;
		
		return i;
	}
	
	public int getTamPaquetesUDP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof UDPPacket)
				i += p.packet.len;
		
		return i;
	}
	
	public int getTamPaquetesTCP() {
		int i = 0;
		
		for(Paquete p: paquetes)
			if(p.getPaquete() instanceof TCPPacket)
				i += p.packet.len;
		
		return i;
	}
	
	public int getTamPaquetesTotal() {
		int i = 0;
		
		for(Paquete p: paquetes)
				i += p.packet.len;
		
		return i;
	}
	
	public ArrayList<Integer> getNumBytesPS(){
		Date tiempoAux = new Date();
		ArrayList<Integer> lista =  new ArrayList<Integer>();
		int numBytes = 0;
		
		tiempoAux = paquetes.get(0).getTiempoCaptura();
		for(Paquete p : paquetes) {
			if(p.getTiempoCaptura().getTime() - tiempoAux.getTime() < 1000) {
				numBytes += p.getPaquete().len;
			} else {
				lista.add(numBytes);
				numBytes = p.getPaquete().len;
				tiempoAux = p.tiempoCaptura;
			}
		}
		
		if(paquetes.get(paquetes.size() - 1).getTiempoCaptura().getTime() - tiempoAux.getTime() != 0)
			lista.add(numBytes);
		
		return lista;
	}

}
