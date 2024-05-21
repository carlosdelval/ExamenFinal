package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ControladorEquipo;
import controller.ControladorSocio;
import model.Equipo;
import model.Socio;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VistaGestionSocios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfNombre;
	private JTextField jtfPrimerApellido;
	private JTextField jtfSegundoApellido;
	private JTextField jtfFechaNacimiento;
	private JComboBox<Equipo> jcbEquipo;
	private JSlider slider;
	private JCheckBox chckBoxSocio;
	private ControladorSocio cs = new ControladorSocio();
	private ControladorEquipo ce = new ControladorEquipo();
	private Socio actual = null;
	private DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
	
	/**
	 * Create the panel.
	 */
	public VistaGestionSocios() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cs.findFirst();
				showInfo();
			}
		});
		btnFirst.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/gotostart.png")));
		GridBagConstraints gbc_btnFirst = new GridBagConstraints();
		gbc_btnFirst.insets = new Insets(0, 0, 5, 5);
		gbc_btnFirst.gridx = 0;
		gbc_btnFirst.gridy = 0;
		add(btnFirst, gbc_btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actual != cs.findFirst() && actual != null) {
					int id = actual.getId();
					actual = cs.findPrevious(id);
					showInfo();
				}
			}
		});
		btnPrevious.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/previous.png")));
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevious.gridx = 1;
		gbc_btnPrevious.gridy = 0;
		add(btnPrevious, gbc_btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actual != cs.findLast() && actual != null) {
					int id = actual.getId();
					actual = cs.findNext(id);
					showInfo();
				}
			}
		});
		btnNext.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/next.png")));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 5);
		gbc_btnNext.gridx = 2;
		gbc_btnNext.gridy = 0;
		add(btnNext, gbc_btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actual = cs.findLast();
				showInfo();
			}
		});
		btnLast.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/gotoend.png")));
		GridBagConstraints gbc_btnLast = new GridBagConstraints();
		gbc_btnLast.insets = new Insets(0, 0, 5, 5);
		gbc_btnLast.gridx = 3;
		gbc_btnLast.gridy = 0;
		add(btnLast, gbc_btnLast);
		
		JButton btnNew = new JButton("");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearInfo();
			}
		});
		btnNew.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/nuevo.png")));
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.insets = new Insets(0, 0, 5, 5);
		gbc_btnNew.gridx = 4;
		gbc_btnNew.gridy = 0;
		add(btnNew, gbc_btnNew);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actual.setNombre(jtfNombre.getText());
					actual.setApellido1(jtfPrimerApellido.getText());
					actual.setApellido2(jtfSegundoApellido.getText());
					isDateValid();
					actual.setFechaNacimiento(sdf.parse(jtfFechaNacimiento.getText()));
					actual.setActivo(chckBoxSocio.isSelected());
					actual.setAntiguedadAnios(slider.getValue());
					actual.setEquipo((Equipo)jcbEquipo.getSelectedItem());
					cs.save(actual);
					JOptionPane.showMessageDialog(null,
							"Se ha guardado correctamente.");
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error. No se ha podido guardar.");
				}
			}
		});
		btnSave.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/guardar.png")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 0;
		add(btnSave, gbc_btnSave);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cs.remove(actual);
					clearInfo();
					JOptionPane.showMessageDialog(null,
							"Se ha borrado correctamente.");
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error. No se ha podido borrar.");
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(VistaGestionSocios.class.getResource("/utils/eliminar.png")));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 6;
		gbc_btnDelete.gridy = 0;
		add(btnDelete, gbc_btnDelete);
		
		JLabel lblGestinDeSocios = new JLabel("Gestión de Socios");
		GridBagConstraints gbc_lblGestinDeSocios = new GridBagConstraints();
		gbc_lblGestinDeSocios.gridwidth = 5;
		gbc_lblGestinDeSocios.insets = new Insets(0, 0, 5, 5);
		gbc_lblGestinDeSocios.gridx = 1;
		gbc_lblGestinDeSocios.gridy = 1;
		add(lblGestinDeSocios, gbc_lblGestinDeSocios);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		add(lblNombre, gbc_lblNombre);
		
		jtfNombre = new JTextField();
		GridBagConstraints gbc_jtfNombre = new GridBagConstraints();
		gbc_jtfNombre.gridwidth = 5;
		gbc_jtfNombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfNombre.gridx = 2;
		gbc_jtfNombre.gridy = 3;
		add(jtfNombre, gbc_jtfNombre);
		jtfNombre.setColumns(10);
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		GridBagConstraints gbc_lblPrimerApellido = new GridBagConstraints();
		gbc_lblPrimerApellido.gridwidth = 2;
		gbc_lblPrimerApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrimerApellido.gridx = 0;
		gbc_lblPrimerApellido.gridy = 4;
		add(lblPrimerApellido, gbc_lblPrimerApellido);
		
		jtfPrimerApellido = new JTextField();
		GridBagConstraints gbc_jtfPrimerApellido = new GridBagConstraints();
		gbc_jtfPrimerApellido.gridwidth = 5;
		gbc_jtfPrimerApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfPrimerApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfPrimerApellido.gridx = 2;
		gbc_jtfPrimerApellido.gridy = 4;
		add(jtfPrimerApellido, gbc_jtfPrimerApellido);
		jtfPrimerApellido.setColumns(10);
		
		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		GridBagConstraints gbc_lblSegundoApellido = new GridBagConstraints();
		gbc_lblSegundoApellido.gridwidth = 2;
		gbc_lblSegundoApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblSegundoApellido.gridx = 0;
		gbc_lblSegundoApellido.gridy = 5;
		add(lblSegundoApellido, gbc_lblSegundoApellido);
		
		jtfSegundoApellido = new JTextField();
		GridBagConstraints gbc_jtfSegundoApellido = new GridBagConstraints();
		gbc_jtfSegundoApellido.gridwidth = 5;
		gbc_jtfSegundoApellido.insets = new Insets(0, 0, 5, 0);
		gbc_jtfSegundoApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfSegundoApellido.gridx = 2;
		gbc_jtfSegundoApellido.gridy = 5;
		add(jtfSegundoApellido, gbc_jtfSegundoApellido);
		jtfSegundoApellido.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.gridwidth = 2;
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.gridx = 0;
		gbc_lblFechaNacimiento.gridy = 6;
		add(lblFechaNacimiento, gbc_lblFechaNacimiento);
		
		jtfFechaNacimiento = new JTextField();
		GridBagConstraints gbc_jtfFechaNacimiento = new GridBagConstraints();
		gbc_jtfFechaNacimiento.gridwidth = 5;
		gbc_jtfFechaNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_jtfFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFechaNacimiento.gridx = 2;
		gbc_jtfFechaNacimiento.gridy = 6;
		add(jtfFechaNacimiento, gbc_jtfFechaNacimiento);
		jtfFechaNacimiento.setColumns(10);
		
		JLabel lblAntigedadaos = new JLabel("Antigüedad (años)");
		GridBagConstraints gbc_lblAntigedadaos = new GridBagConstraints();
		gbc_lblAntigedadaos.gridwidth = 2;
		gbc_lblAntigedadaos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAntigedadaos.gridx = 0;
		gbc_lblAntigedadaos.gridy = 7;
		add(lblAntigedadaos, gbc_lblAntigedadaos);
		
		slider = new JSlider();
		slider.setMaximum(200);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 4;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 2;
		gbc_slider.gridy = 7;
		add(slider, gbc_slider);
		
		JLabel lblAntiguedad = new JLabel("");
		GridBagConstraints gbc_lblAntiguedad = new GridBagConstraints();
		gbc_lblAntiguedad.insets = new Insets(0, 0, 5, 0);
		gbc_lblAntiguedad.gridx = 6;
		gbc_lblAntiguedad.gridy = 7;
		add(lblAntiguedad, gbc_lblAntiguedad);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblAntiguedad.setText(slider.getValue() + " años");
			}
		});
		
		chckBoxSocio = new JCheckBox("Socio en activo");
		GridBagConstraints gbc_chckBoxSocio = new GridBagConstraints();
		gbc_chckBoxSocio.gridwidth = 4;
		gbc_chckBoxSocio.insets = new Insets(0, 0, 5, 5);
		gbc_chckBoxSocio.gridx = 2;
		gbc_chckBoxSocio.gridy = 8;
		add(chckBoxSocio, gbc_chckBoxSocio);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		GridBagConstraints gbc_lblEquipo = new GridBagConstraints();
		gbc_lblEquipo.gridwidth = 2;
		gbc_lblEquipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblEquipo.gridx = 0;
		gbc_lblEquipo.gridy = 9;
		add(lblEquipo, gbc_lblEquipo);
		
		jcbEquipo = new JComboBox();
		GridBagConstraints gbc_jcbEquipo = new GridBagConstraints();
		gbc_jcbEquipo.gridwidth = 5;
		gbc_jcbEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbEquipo.gridx = 2;
		gbc_jcbEquipo.gridy = 9;
		add(jcbEquipo, gbc_jcbEquipo);
		
		loadEquipos();
	}
	
	/**
	 * Método que carga los equipos en el combobox
	 */
	
	private void loadEquipos() {
		List<Equipo> equipos = (List<Equipo>)ce.findAll();
		for(Equipo e : equipos) {
			this.jcbEquipo.addItem(e);
		}
	}
	
	/**
	 * Método que carga la info en pantalla
	 */
	
	private void showInfo() {
		this.jtfNombre.setText(this.actual.getNombre());
		this.jtfPrimerApellido.setText(this.actual.getApellido1());
		this.jtfSegundoApellido.setText(this.actual.getApellido2());
		this.jtfFechaNacimiento.setText(sdf.format(this.actual.getFechaNacimiento()));
		this.slider.setValue(this.actual.getAntiguedadAnios());
		this.chckBoxSocio.setSelected(this.actual.getActivo());
		this.jcbEquipo.setSelectedItem(this.actual.getEquipo());
	}
	
	/**
	 * Método que limpia la info
	 */
	
	private void clearInfo() {
		this.jtfNombre.setText("");
		this.jtfPrimerApellido.setText("");
		this.jtfSegundoApellido.setText("");
		this.jtfFechaNacimiento.setText("");
		this.slider.setValue(0);
		this.chckBoxSocio.setSelected(false);
		this.jcbEquipo.setSelectedItem(null);
		actual = null;
	}
	
	/**
	 * Método que comprueba si la fecha es válida y pone el fondo de color rojo si no lo es
	 */
	
	private void isDateValid() {
		try {
			sdf.parse(jtfFechaNacimiento.getText());
		} catch (Exception e) {
			jtfFechaNacimiento.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "Error en la fecha");
		}
	}
}
