package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

import controller.ControladorEquipo;
import model.Equipo;

import java.awt.Insets;
import java.util.List;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaClasificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControladorEquipo ce = new ControladorEquipo();
	private List<Equipo> equipos = (List<Equipo>)ce.findAll();
	private DefaultListModel<Equipo> dlm = new DefaultListModel<Equipo>();
	private JList list;
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	public VistaClasificacion() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblClasificacin = new JLabel("Clasificación");
		GridBagConstraints gbc_lblClasificacin = new GridBagConstraints();
		gbc_lblClasificacin.gridwidth = 3;
		gbc_lblClasificacin.insets = new Insets(0, 0, 5, 0);
		gbc_lblClasificacin.gridx = 0;
		gbc_lblClasificacin.gridy = 0;
		add(lblClasificacin, gbc_lblClasificacin);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dlm.clear();
				equipos = (List<Equipo>)ce.findAll();
				showTeams();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 3;
		add(btnReset, gbc_btnReset);
		
		JButton btnArriba = new JButton("Arriba");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveUp();
			}
		});
		GridBagConstraints gbc_btnArriba = new GridBagConstraints();
		gbc_btnArriba.insets = new Insets(0, 0, 5, 5);
		gbc_btnArriba.gridx = 1;
		gbc_btnArriba.gridy = 4;
		add(btnArriba, gbc_btnArriba);
		
		JButton btnAbajo = new JButton("Abajo");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveDown();
			}
		});
		GridBagConstraints gbc_btnAbajo = new GridBagConstraints();
		gbc_btnAbajo.insets = new Insets(0, 0, 5, 5);
		gbc_btnAbajo.gridx = 1;
		gbc_btnAbajo.gridy = 5;
		add(btnAbajo, gbc_btnAbajo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Equipo e = (Equipo)list.getSelectedValue();
				dlm.clear();
				equipos.remove(e);
				showTeams();
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 1;
		gbc_btnEliminar.gridy = 6;
		add(btnEliminar, gbc_btnEliminar);
		
		showTeams();
	}
	
	/**
	 * Método que enseña los equipos en la jlist
	 */
	
	private void showTeams() {
		this.dlm.addAll(equipos);
		
		this.list = new JList<Equipo>(dlm);
		
		// Tamaño mínimo de la JList
		this.list.setMinimumSize(new Dimension(0, 200));
		this.list.setPreferredSize(new Dimension(0, 200));
		
		this.scrollPane.setViewportView(list);
	}

	
	/**
	 * Método que mueve hacia arriba un equipo en la clasificación
	 */
	
	private void moveUp() {
		int aux = list.getSelectedIndex();
		if(aux != 0) {
			Equipo e = dlm.getElementAt(aux);
			dlm.removeElement(e);
			dlm.insertElementAt(e, aux-1);
			list = new JList<Equipo>(dlm);
			scrollPane.setViewportView(list);
			list.setSelectedIndex(aux-1);
		}
	}
	/**
	 * Método que mueve hacia abajo un equipo en la clasificación
	 */
	
	private void moveDown() {
		int aux = list.getSelectedIndex();
		if(aux != dlm.getSize() - 1) {
			Equipo e = dlm.getElementAt(aux);
			dlm.removeElement(e);
			dlm.insertElementAt(e, aux+1);
			list = new JList<Equipo>(dlm);
			scrollPane.setViewportView(list);
			list.setSelectedIndex(aux+1);
		}
	}

}
