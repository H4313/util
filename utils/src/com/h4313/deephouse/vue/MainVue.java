package com.h4313.deephouse.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



/**
 * Permet d'afficher les sensors ou les actuators.
 * Pour afficher les actuators : MainVue.init(MainVue.VUE_ACTUATOR);
 * Pour afficher les sensors : MainVue.init(MainVue.VUE_SENSOR);
 * @author Alexis
 *
 */
public class MainVue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTable tabFrame ;
	protected GenericModeleVue modele;
	public static final int VUE_ACTUATOR = 1;
	public static final int VUE_SENSOR = 0;
	
	
	public MainVue(AbstractTableModel mS,String titre) {
        super();
        this.modele=(GenericModeleVue) mS;
        setTitle(titre);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
         tabFrame = new JTable(mS);
      /*  JPanel panelTabSensor = new JPanel ();
        panelTabSensor.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                titre,
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelTabSensor.add(new JScrollPane(tabFrame));
        tabFrame.setAutoCreateRowSorter(true);
        tabFrame.setPreferredSize(new Dimension(600, 1000));
        panelTabSensor.setPreferredSize(new Dimension(600, 1000));*/
        BorderLayout blayout1 = new BorderLayout();
        this.setPreferredSize(new Dimension(500, 1000));
        this.setLayout(blayout1);
        getContentPane().add(new JScrollPane(tabFrame), BorderLayout.CENTER);
        
        
        pack();
    }
	


	/**
	 * Permet d'instancier une fenetre soit de capteur soit d'actuateurs
	 * @param vue <actuator> : on charge les actuators
	 * 				<sensor> : on charge les sensors
	 */
	public static MainVue init(int vue){
		
		MainVue mainVue =null;
		if(vue ==0){
				ModeleSensor modele = new ModeleSensor();
				 mainVue = new MainVue(modele,"liste capteurs");
				 mainVue.setVisible(true);
		}
		else if(vue ==1){
			ModeleActuator modele = new ModeleActuator();
			mainVue= new MainVue(modele,"liste activateurs");
			mainVue.setVisible(true);
		}
		else{
			
		}
		return mainVue;
	}
	
	
	/**
	 * Permet de raffraichir l'affichage  de la fênetre
	 */
	public void refresh (){
		
		this.modele.refresh();
		this.tabFrame.setModel((TableModel) this.modele);
		this.repaint();
	}



	public GenericModeleVue getModele() {
		return modele;
	}



	public void setModele(GenericModeleVue modele) {
		this.modele = modele;
	}
	
}
