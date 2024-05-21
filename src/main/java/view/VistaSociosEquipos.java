package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ControladorEquipo;
import controller.ControladorSocio;
import model.Equipo;
import model.Socio;
import table.MiTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaSociosEquipos extends JPanel {

	private static final long serialVersionUID = 1L;
	private MiTableModel tableModel;
	private JTable table = new JTable(tableModel);
	private JComboBox jcbEquipos;
	private JRadioButton rdbtnOrdenarPorSegundo;
	private JRadioButton rdbtnOrdenarPorPrimer;
	private JRadioButton rdbtnOrdenarPorFecha;
	private JRadioButton rdbtnNombre;
	private ControladorEquipo ce = new ControladorEquipo();
	private ControladorSocio cs = new ControladorSocio();
	private List<Equipo> equipos = (List<Equipo>)ce.findAll();
	private List<Socio> socios = new ArrayList<Socio>();
	private JScrollPane scrollPane;
	private Socio actual;

	/**
	 * Create the panel.
	 */
	public VistaSociosEquipos() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSociosDeEquipos = new JLabel("Socios de equipo");
		GridBagConstraints gbc_lblSociosDeEquipos = new GridBagConstraints();
		gbc_lblSociosDeEquipos.gridwidth = 3;
		gbc_lblSociosDeEquipos.insets = new Insets(0, 0, 5, 0);
		gbc_lblSociosDeEquipos.gridx = 0;
		gbc_lblSociosDeEquipos.gridy = 0;
		add(lblSociosDeEquipos, gbc_lblSociosDeEquipos);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.anchor = GridBagConstraints.EAST;
		gbc_lblEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 2;
		add(lblEquipo, gbc_lblEquipo);
		
		jcbEquipos = new JComboBox();
		jcbEquipos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				createTable();
			}
		});
		GridBagConstraints gbc_jcbEquipos = new GridBagConstraints();
		gbc_jcbEquipos.insets = new Insets(0, 0, 5, 0);
		gbc_jcbEquipos.gridwidth = 2;
		gbc_jcbEquipos.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipos.gridx = 1;
		gbc_jcbEquipos.gridy = 2;
		add(jcbEquipos, gbc_jcbEquipos);
		
		rdbtnNombre = new JRadioButton("Ordenar por nombre");
		rdbtnNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		GridBagConstraints gbc_rdbtnNombre = new GridBagConstraints();
		gbc_rdbtnNombre.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNombre.gridx = 1;
		gbc_rdbtnNombre.gridy = 3;
		add(rdbtnNombre, gbc_rdbtnNombre);
		
		rdbtnOrdenarPorPrimer = new JRadioButton("Ordenar por primer apellido");
		rdbtnOrdenarPorPrimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		GridBagConstraints gbc_rdbtnOrdenarPorPrimer = new GridBagConstraints();
		gbc_rdbtnOrdenarPorPrimer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorPrimer.gridx = 1;
		gbc_rdbtnOrdenarPorPrimer.gridy = 4;
		add(rdbtnOrdenarPorPrimer, gbc_rdbtnOrdenarPorPrimer);
		
		rdbtnOrdenarPorSegundo = new JRadioButton("Ordenar por segundo apellido");
		rdbtnOrdenarPorSegundo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		GridBagConstraints gbc_rdbtnOrdenarPorSegundo = new GridBagConstraints();
		gbc_rdbtnOrdenarPorSegundo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorSegundo.gridx = 1;
		gbc_rdbtnOrdenarPorSegundo.gridy = 5;
		add(rdbtnOrdenarPorSegundo, gbc_rdbtnOrdenarPorSegundo);
		
		rdbtnOrdenarPorFecha = new JRadioButton("Ordenar por fecha nacimiento");
		rdbtnOrdenarPorFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTable();
			}
		});
		GridBagConstraints gbc_rdbtnOrdenarPorFecha = new GridBagConstraints();
		gbc_rdbtnOrdenarPorFecha.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOrdenarPorFecha.gridx = 1;
		gbc_rdbtnOrdenarPorFecha.gridy = 6;
		add(rdbtnOrdenarPorFecha, gbc_rdbtnOrdenarPorFecha);
		
		ButtonGroup grupoOpciones = new ButtonGroup();
		grupoOpciones.add(rdbtnOrdenarPorFecha);
		grupoOpciones.add(rdbtnOrdenarPorSegundo);
		grupoOpciones.add(rdbtnOrdenarPorPrimer);
		grupoOpciones.add(rdbtnNombre);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		add(scrollPane, gbc_scrollPane);
		
		loadEquipos();
	}
	
	/**
	 * Método que carga equipos en combobox
	 */
	
	private void loadEquipos() {
		for(Equipo e : equipos) {
			this.jcbEquipos.addItem(e);
		}
	}
	
	/**
	 * Método que filtra según los ratio buttons
	 * @return
	 */
	
	private String filter() {
		String filtro = "";
		if(this.rdbtnNombre.isSelected()) filtro = "nombre";
		if(this.rdbtnOrdenarPorFecha.isSelected()) filtro = "fechaNacimiento";
		if(this.rdbtnOrdenarPorPrimer.isSelected()) filtro = "apellido1";
		if(this.rdbtnOrdenarPorSegundo.isSelected()) filtro = "apellido2";
		return filtro;
	}
	
	/**
	 * Método que crea la tabla
	 */
	
	private void createTable() {
		socios.addAll((List<Socio>) cs.findByLikeOperator("idEquipo", "" + ((Equipo)jcbEquipos.getSelectedItem()).getId()));
		tableModel = new MiTableModel((Equipo)jcbEquipos.getSelectedItem(), filter());
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) { // Verifica si el clic es doble
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		            	JOptionPane.showMessageDialog(null,
								"Has seleccionado a " + table.getValueAt(selectedRow, 0) + " con id: " + socios.get(selectedRow).getId());
		            }
		        }
		    }
		});
	}
}
