import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JavaCrud {

	private JFrame frame;
	private JTextField nom;
	private JTextField prenom;
	private JTextField addresse;
	//private JTextField voiture;
	private JTable table;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
	    Connect();
	    table_load();
	}
	
	
	  Connection con;
	  PreparedStatement pst;
	  ResultSet rs;
	
	  public void Connect() {
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
		    } catch (ClassNotFoundException ex) {
		        ex.printStackTrace();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}
 
	  
	  public void table_load()
		{
		    try 
		    {
		    pst = con.prepareStatement("select * from personne");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		    catch (SQLException e) 
		     {
		        e.printStackTrace();
		  } 
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crud Java");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(319, 10, 177, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Formulaire", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 53, 319, 275);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(33, 38, 45, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(33, 86, 125, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Addresse");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(33, 137, 95, 13);
		panel.add(lblNewLabel_1_2);
		
		/**JLabel lblNewLabel_1_3 = new JLabel("Voiture");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(33, 195, 95, 13);
		panel.add(lblNewLabel_1_3);**/
		
		nom = new JTextField();
		nom.setBounds(111, 37, 177, 19);
		panel.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(111, 85, 177, 19);
		panel.add(prenom);
		
		addresse = new JTextField();
		addresse.setColumns(10);
		addresse.setBounds(111, 136, 177, 19);
		panel.add(addresse);
		
		//voiture = new JTextField();
		//voiture.setColumns(10);
		//voiture.setBounds(111, 189, 177, 19);
		//panel.add(voiture);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 String getNom,getPrenom,getAddresse; //getVoiture;
				 
				 
				 getNom = nom.getText();
				 getPrenom=prenom.getText();
				 getAddresse=addresse.getText();
				 //getVoiture = voiture.getText();
				 
				 
				 try {
				        pst = con.prepareStatement("insert into personne(nom,prenom,addresse)values(?,?,?)");
				        pst.setString(1, getNom);
				        pst.setString(2, getPrenom);
				        pst.setString(3, getAddresse);
				        //pst.setString(4, getVoiture);
				        
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Addedddd!!!!!");
				        table_load();
				                       
				        nom.setText("");
				        prenom.setText("");
				        addresse.setText("");
				        //voiture.setText("");
				        nom.requestFocus();
				       }
				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
			
				
				
				
			}
		});
		btnNewButton.setBounds(43, 352, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quitter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(138, 352, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.setBounds(233, 352, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 53, 457, 317);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search Personne", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(22, 395, 319, 68);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				  try {
                      
                      String id = search.getText();
                          pst = con.prepareStatement("select nom,prenom,addresse from personne where id = ?");
                          pst.setString(1, id);
                          ResultSet rs = pst.executeQuery();
                      if(rs.next()==true)
                      {
                        
                          String getNom = rs.getString(1);
                          String getPrenom = rs.getString(2);
                          String getAddresse = rs.getString(3);
                          //String getVoiture = rs.getString(4);
                          
                          nom.setText(getNom);
                          prenom.setText(getPrenom);
                          addresse.setText(getAddresse);
                          //voiture.setText(getVoiture);
                          
  
                      }   
                      else
                      {
                          nom.setText("");
                          prenom.setText("");
                          addresse.setText("");
                          //voiture.setText("");
                      }
                  } 
              
               catch (SQLException ex) {
                     
                  }
			}
		});
		search.setColumns(10);
		search.setBounds(118, 25, 152, 19);
		panel_1.add(search);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Personne ID");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(10, 26, 95, 13);
		panel_1.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton_2_1 = new JButton("Supprimer");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
 String getSearch;
				 
				
				 getSearch = search.getText();
				 
				 try {
				        pst = con.prepareStatement("delete from personne where id=?");
				        
				        pst.setString(1, getSearch);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "successfully deleted!!!!!");
				        table_load();
				                       
				        nom.setText("");
				        prenom.setText("");
				        addresse.setText("");
				        //voiture.setText("");
				        nom.requestFocus();
				       }
				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
				
				
				
			}
		});
		btnNewButton_2_1.setBounds(422, 395, 113, 57);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Modifier");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
 String getNom,getPrenom,getAddresse,getSearch;
				 
				 
				 getNom = nom.getText();
				 getPrenom=prenom.getText();
				 getAddresse=addresse.getText();
				 //getVoiture = voiture.getText();
				 getSearch = search.getText();
				 
				 try {
				        pst = con.prepareStatement("update personne set nom= ?,prenom= ?, addresse= ?, where id=?");
				        pst.setString(1, getNom);
				        pst.setString(2, getPrenom);
				        pst.setString(3, getAddresse);
				        //pst.setString(4, getVoiture);
				        pst.setString(4, getSearch);
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "successfully Update!!!!!");
				        table_load();
				                       
				        nom.setText("");
				        prenom.setText("");
				        addresse.setText("");
				        //voiture.setText("");
				        nom.requestFocus();
				       }
				    catch (SQLException e1) 
				        {            
				       e1.printStackTrace();
				    }
			
				
				
				
				
			}
		});
		btnNewButton_2_1_1.setBounds(559, 395, 113, 57);
		frame.getContentPane().add(btnNewButton_2_1_1);
	}
}