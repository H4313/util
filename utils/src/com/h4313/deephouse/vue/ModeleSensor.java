package com.h4313.deephouse.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import com.h4313.deephouse.housemodel.House;
import com.h4313.deephouse.housemodel.Room;
import com.h4313.deephouse.sensor.Sensor;




public class ModeleSensor extends AbstractTableModel implements GenericModeleVue {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<SensorVue> sensors = new ArrayList<SensorVue>();
	 
    private final String[] entetes = {"Piece","Identifiant", "Type","Valeur"};
	

    
	public ModeleSensor() {
		super();
		List<Room> rooms = House.getInstance().getRooms();
		for(Room r: rooms){
			Set<Map.Entry<String, Sensor<Object>>> setSensor = r.getSensors().entrySet();
			for(Map.Entry<String, Sensor<Object>> entry : setSensor){
				String nom = r.getClass().getName();
				nom=nom.substring(nom.lastIndexOf('.')+1);
				sensors.add(new SensorVue(entry.getValue(),nom));
				
			}
		}

	
	}


	@Override
	public int getRowCount() {
		
		return sensors.size();
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
             return sensors.get(rowIndex).getNomPiece();
         case 1:
             return sensors.get(rowIndex).getSensor().getId();
         case 2:
             return sensors.get(rowIndex).getSensor().getType();
         case 3:
             return sensors.get(rowIndex).getSensor().getLastValue();
         default:
             return null; //Ne devrait jamais arriver
     }
	}
	
	public void addSensor(SensorVue sensor) {
        sensors.add(sensor);
 
        fireTableRowsInserted(sensors.size() -1, sensors.size() -1);
    }
 
    public void removeSensor(int rowIndex) {
        sensors.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


    @Override
	public void refresh(){
    	sensors.clear();
    	List<Room> rooms = House.getInstance().getRooms();
		for(Room r: rooms){
			Set<Map.Entry<String, Sensor<Object>>> setSensor = r.getSensors().entrySet();
			for(Map.Entry<String, Sensor<Object>> entry : setSensor){
				String nom = r.getClass().getName();
				nom=nom.substring(nom.lastIndexOf('.')+1);
				sensors.add(new SensorVue(entry.getValue(),nom));
				
			}
		}
    }
    
	

}
