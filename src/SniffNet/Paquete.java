package SniffNet;

import java.util.ArrayList;
import java.util.Date;

import jpcap.packet.EthernetPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class Paquete {
	
	protected int id;
	protected Date tiempoCaptura;
	protected String tipoRed, tipoTransporte, ipOrigen, ipDestino, puertoOrigen, puertoDestino; 
	protected Packet packet;
	
	public Paquete(Packet paquete, Date tcPaquete, String tipoR, String tipoT, String srcIP, String dstIP, String srcP, String dstP ) {
		this.packet = paquete;
		this.tiempoCaptura = tcPaquete;
		this.tipoRed = tipoR;
		this.tipoTransporte = tipoT;
		this.ipOrigen = srcIP;
		this.ipDestino = dstIP;
		this.puertoOrigen = srcP;
		this.puertoDestino = dstP;
	}
	

	@SuppressWarnings("deprecation")
	public String paqueteToString() {
		
		
		StringBuilder sb = new StringBuilder();
			
		if(id < 1000)
				sb.append("  ");
			
			sb.append(String.valueOf(id) + "    ");
			
			if(id < 100) {
				sb.append("  ");
				if(id < 10)
					sb.append("  ");
			}
			
			sb.append(tipoRed);
			if(tipoRed.contentEquals("ICMP")) {
				sb.append("         ");
			} else {
				sb.append(" - " + tipoTransporte + "    ");
				if(tipoTransporte.contentEquals("TCP"))
					sb.append(" ");
			}
			
			sb.append(ipOrigen + "      ");
			for(int j=ipOrigen.length();j<16;j++)
				sb.append("  ");
			
			sb.append(ipDestino + "      ");
			for(int j=ipDestino.length();j<16;j++)
				sb.append("  ");
			
			sb.append(puertoOrigen + "    ");
			for(int j=puertoOrigen.length(); j<6;j++)
				sb.append("  ");
			if(puertoOrigen.contentEquals("-"))
				sb.append(" ");
			
			sb.append(puertoDestino + "       ");
			for(int j=puertoDestino.length(); j<6;j++)
				sb.append("  ");
			if(puertoDestino.contentEquals("-"))
				sb.append(" ");
			
			String hours, minutes, seconds, day, month, year;
			
			hours = String.valueOf(tiempoCaptura.getHours());
			if(hours.length()==1)
				hours = "0" + hours;
			minutes = String.valueOf(tiempoCaptura.getMinutes());
			if(minutes.length()==1)
				minutes = "0" + minutes;
			seconds = String.valueOf(tiempoCaptura.getSeconds());
			if(seconds.length()==1)
				seconds = "0" + seconds;
			day = String.valueOf(tiempoCaptura.getDate());
			if(day.length()==1)
				day = "0" + day;
			month = String.valueOf(tiempoCaptura.getMonth() + 1);
			if(month.length()==1)
				month = "0" + month;
			year = String.valueOf(tiempoCaptura.getYear() + 1900);
			sb.append(hours+" : "+minutes+" : "+seconds+"          "+day+" / " + month + " / " + year);
			
			
		return sb.toString();	
	}
	
	
	@SuppressWarnings("deprecation")
	public ArrayList<String> mostrarInformacion(){
		ArrayList<String> data = new ArrayList<String>();
		String day, month, year, hour, minutes, seconds;
		
		day = String.valueOf(tiempoCaptura.getDate());
		if(day.length()==1)
			day = "0" + day;
		month = String.valueOf(tiempoCaptura.getMonth()+1);
		if(month.length()==1)
			month = "0" + month;
		year = String.valueOf((tiempoCaptura.getYear() + 1900));
		hour = String.valueOf(tiempoCaptura.getHours());
		if(hour.length()==1)
			hour = "0" + hour;
		minutes = String.valueOf(tiempoCaptura.getMinutes());
		if(minutes.length()==1)
			minutes = "0" + minutes;
		seconds = String.valueOf(tiempoCaptura.getSeconds());
		if(seconds.length()==1)
			seconds = "0" + seconds;
		
		
		data.add("Paquete ID: " + id + "                          Fecha: " + day + "/" + month + "/" + year 
				+ "                            Hora: " + hour + ":" + minutes + ":" + seconds);
		
		if(packet.datalink instanceof EthernetPacket) {
			EthernetPacket ether = (EthernetPacket) packet.datalink;
			data.add("Capa Ethernet:");
			data.add("- Tipo de Trama: " + String.valueOf(ether.frametype));
			data.add("- Dirección MAC de Origen: " + ether.getSourceAddress());
			data.add("- Dirección MAC de Destino: " + ether.getDestinationAddress());
			data.add("- Longitud de la Cabecera Ethernet: " + String.valueOf(packet.header.length) + " Bytes");
			data.add("- Longitud de la Trama: " + String.valueOf(packet.data.length) + " Bytes");
		}
		
		if(packet instanceof IPPacket) {
			IPPacket ip = (IPPacket) packet;
			data.add("Capa de Red:");
			data.add("- Tipo de Paquete: IP");
			data.add("- Versión: " + String.valueOf(ip.version));
			data.add("- Tamaño de la Cabecera: " + String.valueOf(ip.header.length) + " Bytes");
			data.add("- Longitud total: " + String.valueOf(ip.length) + " Bytes");
			data.add("- Identificador: " + String.valueOf(ip.ident));
			data.add("- Flags de Fragmentación: ");
			data.add("      Sin Fragmentar: " + String.valueOf(ip.dont_frag));
			data.add("      Más Fragmentado: " + String.valueOf(ip.more_frag));
			data.add("- Dirección IP de Origen: " + String.valueOf(ip.src_ip).substring(1));
			data.add("- Dirección IP de Destino: " + String.valueOf(ip.dst_ip).substring(1));
		}
		
		if(packet instanceof ICMPPacket) {
			ICMPPacket icmp = (ICMPPacket) packet;
			data.add(" ");
			data.add("- Tipo de Paquete: ICMP");
			data.add("- Tipo: " + String.valueOf(icmp.type));
			data.add("- Cóodigo: " + String.valueOf(icmp.code));
			data.add("- Suma de Verificación: " + String.valueOf(icmp.checksum));
		} 
		
		if(packet instanceof TCPPacket) {
			TCPPacket tcp = (TCPPacket) packet;
			data.add("Capa de Transporte:");
			data.add("- Tipo de Protocolo: TCP");
			data.add("- Puerto de Origen: " + String.valueOf(tcp.src_port));
			data.add("- Puerto de Destino: " + String.valueOf(tcp.dst_port));
			data.add("- Número de Secuencia:" + String.valueOf(tcp.sec));
			data.add("- Número de Acuse de Recibo: " + String.valueOf(tcp.ack_num));
			data.add("- Tamaño de la Cabecera: " + String.valueOf(tcp.header.length) + " Bytes");
			data.add("- Flags:");
			data.add("      ACK: " + String.valueOf(tcp.ack));
			data.add("      FIN: " + String.valueOf(tcp.fin));
			data.add("      PUSH: " + String.valueOf(tcp.psh));
			data.add("      RESET: " + String.valueOf(tcp.rst));
			data.add("      SYN: " + String.valueOf(tcp.syn));
			data.add("      URG:" + String.valueOf(tcp.urg));
			data.add("- Tamaño de la Ventana de Recepción: " + String.valueOf(tcp.window) + " Bytes");
			data.add("- Puntero Urgente: " + String.valueOf(tcp.urgent_pointer));
		} else if(packet instanceof UDPPacket) {
			UDPPacket udp = (UDPPacket) packet;
			data.add("Capa de Transporte:");
			data.add("- Tipo de Protocolo: UDP");
			data.add("- Puerto de Origen: " + String.valueOf(udp.src_port));
			data.add("- Puerto de Destino: " + String.valueOf(udp.dst_port));
			data.add("- Tamaño del Mensaje: " + String.valueOf(udp.length) + " Bytes");
		}
		
		
		
		
		
		return data;
	}
	
	public Packet getPaquete() {
		return this.packet;
	}

	public void setId(int i) {
		this.id = i;
	}

	public Date getTiempoCaptura() {
		return this.tiempoCaptura;
	}
	
	public void setTimeCaptura(Date d) {
		this.tiempoCaptura = d;
	}
}
