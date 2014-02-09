package com.h4313.deephouse.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;









import javax.swing.table.AbstractTableModel;

import com.h4313.deephouse.housemodel.House;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.actuator.*;




public class ModeleActuator extends AbstractTableModel implements GenericModeleVue {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<ActuatorVue> actuators = new ArrayList<ActuatorVue>();
	 
    private final String[] entetes = {"Piece","Identifiant", "Type","Valeur"};
	

    
	public ModeleActuator() {
		super();
		List<Room> rooms = House.getInstance().getRooms();
		for(Room r: rooms){
			Set<Map.Entry<String, Actuator<Object>>> setActuator = r.getActuators().entrySet();
			for(Map.Entry<String, Actuator<Object>> entry : setActuator){
				String nom = r.getClass().getName();
				nom=nom.substring(nom.lastIndexOf('.')+1);
				actuators.add(new ActuatorVue(entry.getValue(),nom));
				
			}
		}

	
	}

	@Override
	public int getRowCount() {
		
		return actuators.size();
	}
	
	
	@Override
	public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }

	@Override
	public int getColumnCount() {
		
		return entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch(columnIndex){
         case 0:
             return actuators.get(rowIndex).getNomPiece();
         case 1:
             return actuators.get(rowIndex).getActuator().getId();
         case 2:
             return actuators.get(rowIndex).getActuator().getType();
         case 3:
             return actuators.get(rowIndex).getActuator().getLastValue();
         default:
             return null; //Ne devrait jamais arriver
     }
	}
	
	public void addActuator(ActuatorVue Actuator) {
        actuators.add(Actuator);
 
        fireTableRowsInserted(actuators.size() -1, actuators.size() -1);
    }
 
    public void removeActuator(int rowIndex) {
        actuators.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

	@Override
	public void refresh() {
		actuators.clear();List<Room> rooms = House.getInstance().getRooms();
		for(Room r: rooms){
			Set<Map.Entry<String, Actuator<Object>>> setActuator = r.getActuators().entrySet();
			for(Map.Entry<String, Actuator<Object>> entry : setActuator){
				String nom = r.getClass().getName();
				nom=nom.substring(nom.lastIndexOf('.')+1);
				actuators.add(new ActuatorVue(entry.getValue(),nom));
				
			}
		}
		
	}



	

}
