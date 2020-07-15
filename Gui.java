package App;




import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.color.*;
import javax.swing.BoxLayout;

public class Gui extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 1L;
	

	private JButton button;
	private JLabel prezzo;
	private JLabel nome;
	private JPanel panel;
	private JTextField link;
	private JTextField mail;
	private JButton mailb,back;
	private JPanel p2;
	private JPanel p_mail;
	private JPanel p_root,p_rec,p1;
	private JComboBox site;
	
	
	public Gui() {
        super("Shop Track");
		
		link=new JTextField("Inserisci url prodotto Amazon");
		button=new JButton("Search");
		button.addActionListener(this);
		prezzo=new JLabel("Prezzo: ");
		nome=new JLabel("Nome Prodotto:");
		mail= new JTextField("Inserire mail per avere info sul prodotto");
		panel =new JPanel(new BorderLayout());
		p2= new JPanel(new BorderLayout());
		p_root= new JPanel(new BorderLayout());
		p_mail= new JPanel(new BorderLayout());
		p_rec= new JPanel();
		mailb= new JButton("NewsLetter");
		mailb.addActionListener(this);
		p1=new JPanel(new GridLayout(6,1));
		site= new JComboBox();
		back=new JButton("<");
		back.addActionListener(this);
		
		p_root.setLayout( new BoxLayout(p_root,BoxLayout.PAGE_AXIS));
		
	
	
		
		p_root.add(BorderLayout.PAGE_START, panel);
		p_root.add(BorderLayout.PAGE_END, site);
		p_root.add(BorderLayout.CENTER, p2);
		//p_root.add(new JSeparator());
		//p_root.add(p_mail);
	    p_root.setBackground(Color.cyan);
		
		
		
		//ricerca prodotto
		panel.add(BorderLayout.LINE_START,new JLabel("URL:"));
		panel.add(BorderLayout.CENTER, link);
		panel.add(BorderLayout.LINE_END, button);
		//panel.add(BorderLayout.PAGE_END,p1);
		
		//ricerca 
	    //for(String i : ArrayString)
		p1.add(nome);
		p1.add(new JLabel(""));
		p1.add(prezzo);
		p1.add(new JLabel(""));
		//p1.add(lbut);
	
		p1.add(p_mail);
		//p1.add(new JLabel(""));
		p1.add(back);
		
		
		//recenti o varie
		p2.add(BorderLayout.PAGE_START,new JLabel("Recenti"));
		p2.add(BorderLayout.LINE_END,p_rec);
		p2.setBackground(Color.getHSBColor(82, 9, 92));
		
		//mail
		p_mail.add(BorderLayout.PAGE_START,new JLabel("Mail:"));
		p_mail.add(BorderLayout.CENTER, mail);
		p_mail.add(BorderLayout.LINE_END, mailb);
	    
		//recenti
		
		
		setContentPane(p_root);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == button) {
			System.out.println("Prod");
			   Scraper s=new Scraper();
			   Prodotto p = new Prodotto();
			    
			    p=s.getProductFromUrl(link.getText());
			    
			    if(p.getPrezzo()=="Errore"|| p.getNome()=="Errore") {
			   	 JOptionPane.showMessageDialog(this, "Nome o Prezzo del prodoto non trovato. Inserire nome prezzo corretto",
				            "Product Error generation",
				            JOptionPane.INFORMATION_MESSAGE);
			   	
			   	setContentPane(p_root);
			   	
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setSize(500, 500);
				setVisible(true);
			   	 
				    		}
			    else
			      {prezzo.setText(p.getPrezzo());
			    	 nome.setText(p.getNome());
			    	 setContentPane(p1);
						setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						setSize(500, 500);
						setVisible(true);
			      }
			    
			    
			    
			    
			    
			   
			    
		}
	


		if (e.getSource()==mailb) {
			System.out.println("Mail");
			Mail m = new Mail();
			
			String destinatario=new String();
			


			destinatario =mail.getText();
			
			
			
			m.setText("Le confermiamo l'avvenuta registrazione alla news letter di Traccia Prezzi per il prodotto da lei selezionato");
			m.setOggetto("Registrazione alla News Letter di Traccia Prezzi");
			
			
			if(destinatario.matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")==false) {
				 JOptionPane.showMessageDialog(this, "L'email inserita non è valida!",
				            "Invalid Email",
				            JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			else {
			m.send(destinatario);
			
			JOptionPane.showMessageDialog(this,
		    		"Iscrizione confermata,entro 24h riceverete uuna mail di conferma",
		            "Verification Email",
		            JOptionPane.INFORMATION_MESSAGE);
		
			}
			
			    		
			
			if(e.getSource()== back) {
				setContentPane(p_root);
				setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setSize(500,400);
				setVisible(true);
				
			}	
		}
			
			
			

	}
}
