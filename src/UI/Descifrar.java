package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import cifrador.CifradorFrame;
import cifrador.Grain;
import cifrador.ImageConverter;

import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Descifrar {

	private JFrame frmCifradorGrain;
	private BufferedImage imagenInicial = null;
	private byte[] imagenByteArray;
	private BufferedImage imagenFinal = null;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Descifrar window = new Descifrar();
					window.frmCifradorGrain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Descifrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCifradorGrain = new JFrame();
		frmCifradorGrain.setTitle("GRAIN - Cifrar");
		frmCifradorGrain.getContentPane().setBackground(new Color(0, 0, 0));
		frmCifradorGrain.setBounds(100, 100, 617, 612);
		frmCifradorGrain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmCifradorGrain.getContentPane().setLayout(null);
		JButton btnGuardar = new JButton("Guardar imagen");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 12));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(102, 102, 51));
		btnGuardar.setBounds(382, 503, 159, 31);
		frmCifradorGrain.getContentPane().add(btnGuardar);
		
		JLabel lblResultadoFinal = new JLabel("Imagen descifrada");
		lblResultadoFinal.setOpaque(true);
		lblResultadoFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultadoFinal.setBackground(Color.BLACK);
		lblResultadoFinal.setForeground(Color.BLACK);
		lblResultadoFinal.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 12));
		lblResultadoFinal.setBounds(360, 183, 181, 31);
		frmCifradorGrain.getContentPane().add(lblResultadoFinal);
		
		JLabel lblNewLabel = new JLabel("Cifrador Grain");
		lblNewLabel.setIcon(new ImageIcon(Descifrar.class.getResource("/UI/banner2.png")));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(0, 0, 628, 112);
		frmCifradorGrain.getContentPane().add(lblNewLabel);
		
		JButton buttonDescifrar = new JButton("");
		buttonDescifrar.setFocusPainted(false);
		buttonDescifrar.setEnabled(false);
		buttonDescifrar.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 12));
		buttonDescifrar.setBorder(null);
		buttonDescifrar.setBackground(Color.BLACK);
		buttonDescifrar.setBounds(66, 503, 104, 31);
		frmCifradorGrain.getContentPane().add(buttonDescifrar);
		
		JLabel lblIngreseElArchivo = new JLabel("Ingrese el archivo que desea descifrar");
		lblIngreseElArchivo.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblIngreseElArchivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseElArchivo.setForeground(Color.GRAY);
		lblIngreseElArchivo.setBounds(10, 141, 361, 31);
		frmCifradorGrain.getContentPane().add(lblIngreseElArchivo);
		
		JLabel lblImagenACifrar = new JLabel("");
		lblImagenACifrar.setBounds(66, 238, 181, 240);
		frmCifradorGrain.getContentPane().add(lblImagenACifrar);
		
		JLabel lblImagenCifrada = new JLabel("");
		lblImagenCifrada.setBounds(360, 238, 181, 240);
		frmCifradorGrain.getContentPane().add(lblImagenCifrada);
		
		buttonDescifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (imagenInicial == null) {
					JOptionPane.showMessageDialog(null, "Debe cargar una imagen para cifrar", "Imagen inválida",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					imagenByteArray = ImageConverter.imageToByteArray(imagenInicial);
					Grain grain = new Grain("clave12345".getBytes(),
							"semilla1".getBytes(), imagenByteArray);
					imagenFinal = ImageConverter.byteArrayToImage(grain.xor());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ImageIcon imageIcon = new ImageIcon(imagenFinal.getScaledInstance(200, 240, Image.SCALE_SMOOTH));
				lblImagenCifrada.setIcon(imageIcon);
				lblResultadoFinal.setForeground(Color.WHITE);
				btnGuardar.setEnabled(true);
			}
		});

		JButton btnSeleccionarImagen = new JButton("Seleccionar imagen");
		btnSeleccionarImagen.setForeground(new Color(255, 255, 255));
		btnSeleccionarImagen.setIcon(new ImageIcon(Descifrar.class.getResource("/UI/subirimagen20px.png")));
		btnSeleccionarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "bmp", "gif");
				fileChooser.setFileFilter(fileNameExtensionFilter);
				
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.asList("jpg", "jpeg", "png", "bmp", "gif").contains(fileExtension)) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo de imagen (jpg, jpeg, png, bmp, gif)", "Archivo inválido", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					try {
						imagenInicial = ImageIO.read(file);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Error al abrir la imagen para cifrar", "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}

					ImageIcon imageIcon = new ImageIcon(imagenInicial.getScaledInstance(200, 240, Image.SCALE_SMOOTH));
					lblImagenACifrar.setIcon(imageIcon);
					buttonDescifrar.setFocusPainted(false);
					buttonDescifrar.setBorder(null);
					buttonDescifrar.setForeground(new Color(255, 255, 255));
					buttonDescifrar.setBackground(new Color(0, 51, 0));
					buttonDescifrar.setEnabled(true);
					buttonDescifrar.setText("Descifrar");
					frmCifradorGrain.getContentPane().add(buttonDescifrar);
				}
			}
		});
		btnSeleccionarImagen.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 12));
		btnSeleccionarImagen.setFocusPainted(false);
		btnSeleccionarImagen.setBorder(null);
		btnSeleccionarImagen.setBackground(new Color(102, 102, 51));
		btnSeleccionarImagen.setBounds(66, 183, 181, 31);
		frmCifradorGrain.getContentPane().add(btnSeleccionarImagen);
		
		btnGuardar.setEnabled(false);
		btnGuardar.setIcon(new ImageIcon(Descifrar.class.getResource("/UI/guardarImagen20px.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (imagenFinal == null) {
					JOptionPane.showMessageDialog(null, "Debe cifrar una imagen para poder guardarla", "Imagen inválida", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(null);
				File file = null;
				
				if (result == JFileChooser.APPROVE_OPTION) {
					file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".bmp");
				}
				
				try {
					ImageIO.write(imagenFinal, "bmp", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Descifrar.class.getResource("/UI/flecha.png")));
		lblNewLabel_2.setBounds(276, 317, 55, 37);
		frmCifradorGrain.getContentPane().add(lblNewLabel_2);
	}
}
