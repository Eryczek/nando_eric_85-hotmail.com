package Utils;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class Utils {
	
	public Utils() {
		
	}
	
	public static void addToListModel(DefaultListModel<String> list, String elem) {
		boolean encontrado = false;
		
		for(int i=0; i<list.getSize();i++) 
			if(list.getElementAt(i).contentEquals(elem))
					encontrado = true;
		
		if(!encontrado) 
			list.addElement(elem);
					
	}
	
	public static ArrayList<String> parseDefaulListModelToArrayList(DefaultListModel<String> model){
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i=0; i<model.getSize();i++) {
			list.add(model.get(i));
		}
		
		return list;
	}

}
