package practice;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeagueAnalyst {
	private Summoner summoner = null;
	private long summonerLevel;
	private String summonerName;
	private JFrame frame;
	private JTextField SummonerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeagueAnalyst window = new LeagueAnalyst();
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
	public LeagueAnalyst() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Border emptyBorder = BorderFactory.createEmptyBorder();
		RiotAPI.setMirror(Region.NA);
		RiotAPI.setRegion(Region.NA);
		RiotAPI.setRateLimit(100, 10);
		RiotAPI.setRateLimit(new RateLimit(100, 10), new RateLimit(5000, 600));
		RiotAPI.setAPIKey("c71122fa-dde2-4924-acfd-e91cc6014766");
	
		//All for JFrame
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(false);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setType(Type.POPUP);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(new Color(255, 255, 255));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LeagueAnalyst.class.getResource("/league/icons/bkgrnd.jpg")));
		frame.setTitle("League Analyst");
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel nameBackground = new JLabel("");
		nameBackground.setFont(new Font("Impact", Font.PLAIN, 10));
		nameBackground.setIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/SName.png")));
		nameBackground.setBounds(51, 67, 110, 20);
		frame.getContentPane().add(nameBackground);
		
		SummonerName = new JTextField();
		SummonerName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SummonerName.setText("");
			}
		});
		SummonerName.setFont(new Font("Impact", Font.PLAIN, 11));
		SummonerName.setText("Summoner Name");
		SummonerName.setHorizontalAlignment(SwingConstants.CENTER);
		SummonerName.setOpaque(false);
		SummonerName.setBorder(null);
		SummonerName.setBackground(Color.WHITE);
		SummonerName.setForeground(Color.CYAN);
		SummonerName.setBounds(51, 67, 110, 20);
		SummonerName.setColumns(10);
		frame.getContentPane().add(SummonerName);
		
		JLabel EnterSummonerName = new JLabel("League Analyst\r\n");
		EnterSummonerName.setFont(new Font("FREEDOM", Font.PLAIN, 25));
		EnterSummonerName.setForeground(Color.CYAN);
		EnterSummonerName.setBounds(115, 22, 286, 34);
		frame.getContentPane().add(EnterSummonerName);

		
		JEditorPane output = new JEditorPane();
		output.setEditable(false);
		output.setText("Summoner's Information: ");
		output.setOpaque(false);
		output.setForeground(new Color(0, 0, 0));
		output.setToolTipText("");
		output.setFont(new Font("Monospaced", Font.PLAIN, 13));
		output.setBounds(25, 154, 445, 135);
		frame.getContentPane().add(output);
		
		JButton LookUp = new JButton("");
		LookUp.setSelectedIcon(null);
		LookUp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LookUp.setOpaque(false);
		LookUp.setContentAreaFilled(false);
		LookUp.setBackground(new Color(0, 0, 0));
		LookUp.setBorder(null);
		LookUp.setBounds(160, 62, 90, 25);
		LookUp.setIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/Lookup.png")));
		LookUp.setRolloverIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/LookupHighlighted.png")));
		LookUp.setRolloverEnabled(true);
		LookUp.setFocusable(false);
		LookUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				output.setText("");
				System.out.println(SummonerName.getText());
				summonerName = SummonerName.getText();

				//Looks up
				try{
					if(summonerName.equalsIgnoreCase("jimmyn321")){
						System.out.println("I Work");
						output.setText("You found an easter egg\nHeres your reward: https://www.youtube.com/watch?v=B4rcBHtdUU8");
						return;
					}
					summoner = RiotAPI.getSummonerByName(summonerName);
					summonerLevel = summoner.getLevel();
					output.setText("Summoner Name: " + summoner.getName() + "\n-Level " + summonerLevel + "\n-Profile : http://www.lolking.net/summoner/na/"+ summoner.getID() + "\n #Not All Data is updated.");
				}catch(Exception e){
					output.setText("Not able to locate: "+SummonerName.getText());
					return;
				}
				
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(LookUp);
		
		JButton exit = new JButton("");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit.setIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/close.png")));
		exit.setRolloverIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/hoverclose.png")));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBackground(new Color(0, 0, 0));
		exit.setBorder(null);
		exit.setRolloverEnabled(true);
		exit.setFocusable(false);
		exit.setBounds(465, 0, 35, 35);
		frame.getContentPane().add(exit);
		
		
		if (!frame.isUndecorated()){
			exit.setVisible(false);
		}
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(LeagueAnalyst.class.getResource("/league/icons/bkgrnd.jpg")));
		background.setBounds(0, 0, 500, 300);
		frame.getContentPane().add(background);
		
		

		

		

		

	}
}
