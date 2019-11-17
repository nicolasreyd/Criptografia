package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PrimerPantalla {

	private JFrame frmCifradorGrain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimerPantalla window = new PrimerPantalla();
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
	public PrimerPantalla() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCifradorGrain = new JFrame();
		frmCifradorGrain.setTitle("GRAIN");
		frmCifradorGrain.getContentPane().setBackground(new Color(0, 0, 0));
		frmCifradorGrain.setBounds(100, 100, 377, 358);
		frmCifradorGrain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCifradorGrain.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("CIFRAR");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cifrar.main();
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(new Color(0, 51, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 15));
		btnNewButton.setBounds(103, 178, 155, 36);
		frmCifradorGrain.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("DESCIFRAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Descifrar.main();
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setFocusPainted(false);
		button.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 15));
		button.setBorder(null);
		button.setBackground(new Color(0, 0, 102));
		button.setBounds(103, 225, 155, 36);
		frmCifradorGrain.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrimerPantalla.class.getResource("/UI/banner.png")));
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("HelveticaNeueLT Com 45 Lt", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(0, 0, 361, 112);
		frmCifradorGrain.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("Seleccione una opci\u00F3n");
		label.setFont(new Font("Monospaced", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(102, 102, 102));
		label.setBounds(0, 136, 361, 31);
		frmCifradorGrain.getContentPane().add(label);
	}
}
