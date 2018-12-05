import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class War implements MouseListener {
	/* UI Items */
	JFrame frame;
	JPanel topPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JLabel leftCard;
	JLabel rightCard;
	JButton goButton;
	JLabel topMessage;
	JLabel bottomMessage;
	JLabel leftCardTitle;
	JLabel rightCardTitle;
	JLabel leftScore;
	JLabel rightScore;
	
	/* State Data */
	ScoreKeeper scores = new ScoreKeeper(0, 0, 0);
	Deck deck;
	int playerPoints = 0;
	int computerPoints = 0;
	int bonusPoints = 0;
	
	public void Initialize() {
		setFont(new FontUIResource(new Font("Arial", Font.PLAIN, 48)));
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 400);
		frame.setTitle("WAR!");
		
		topPanel = new JPanel();
		topPanel.setBackground(new Color(200,200,200));
		frame.add(topPanel, BorderLayout.NORTH);  // Add topPanel to top of frame
		topMessage = new JLabel();
		topMessage.setText("Welcome to WAR!");
		topPanel.add(topMessage);
		
		leftScore = new JLabel();
		frame.add(leftScore, BorderLayout.WEST);  // Add to left of frame
		
		middlePanel = new JPanel();
		middlePanel.setBackground(new Color(200,200,100));
		frame.add(middlePanel, BorderLayout.CENTER);  // Add middlePanel to center of frame, will fill available space

		rightScore = new JLabel();
		frame.add(rightScore, BorderLayout.EAST);  // Add to right of frame

		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(100,200,100));
		frame.add(bottomPanel, BorderLayout.SOUTH);  // Add bottomPanel to bottom of frame
		bottomMessage = new JLabel();
		bottomPanel.add(bottomMessage);
		
		leftCardTitle = new JLabel();
		leftCardTitle.setText("Your Card >  ");
		middlePanel.add(leftCardTitle);
		leftCard = new JLabel();
		leftCard.setForeground(new Color(0,150,0));
		middlePanel.add(leftCard);
		
		goButton = new JButton();
		goButton.setText("GO!");
		goButton.addMouseListener(this);
		middlePanel.add(goButton);
		
		rightCard = new JLabel();
		rightCard.setForeground(new Color(255,0,0));
		middlePanel.add(rightCard);
		rightCardTitle = new JLabel();
		rightCardTitle.setText("  < Comp Card");
		middlePanel.add(rightCardTitle);
		
		frame.setVisible(true);
		
		deck = new Deck();
		resetGameBoard();
	}
	
	private void resetGameBoard() {
		// Reset the game
		deck.shuffle();
		bottomMessage.setText("Games   You: " + scores.getPlayerScore() + "  Computer: " + scores.getOpponentScore() +"   Total: " + scores.getGamesPlayed());
		playerPoints = 0;
		computerPoints = 0;
		bonusPoints = 0;
		leftScore.setText(Integer.toString(playerPoints));
		rightScore.setText(Integer.toString(computerPoints));
		leftCard.setText(" ");
		rightCard.setText(" ");
	}
	
	
	public static void main(String[] args) {
		testDeck();
		War war = new War();
		war.Initialize();
		//new War().Initialize();
	}
	
	
	private static void testDeck() {
		Deck d = new Deck();
		System.out.println(d);
		d.shuffle();
		System.out.println(d);
		int card = d.dealCard();
		System.out.print(card + " ");
		card = d.dealCard();
		System.out.print(card + " ");
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(goButton)) {
			// Get two cards and see who won
			int playerCard = deck.dealCard();
			leftCard.setText(Integer.toString(playerCard));
			int computerCard = deck.dealCard();
			rightCard.setText(Integer.toString(computerCard));
			
			if (playerCard > computerCard) {
				topMessage.setText("You won this hand!");
				playerPoints += 1 + bonusPoints;
				bonusPoints = 0;
				leftScore.setText(Integer.toString(playerPoints));
			}
			else if (playerCard < computerCard) {
				topMessage.setText("Computer won this hand!");
				computerPoints += 1 + bonusPoints;
				bonusPoints = 0;
				rightScore.setText(Integer.toString(computerPoints));
			}
			else {
				bonusPoints++;
				topMessage.setText("Tie!  Bonus points now: " + bonusPoints);
			}
			
			// Check if game is over
			if (deck.getCardsLeft() < 2) {
				scores.setGamesPlayed(scores.getGamesPlayed() + 1);
				if (playerPoints > computerPoints) {
					JOptionPane.showMessageDialog(null, "You won " + playerPoints + " to " + computerPoints + "!", "Error", JOptionPane.INFORMATION_MESSAGE);
					scores.setPlayerScore(scores.getPlayerScore() + 1);
				}
				else if (playerPoints < computerPoints) {
					JOptionPane.showMessageDialog(null, "You lost " + playerPoints + " to " + computerPoints + "!", "Error", JOptionPane.INFORMATION_MESSAGE);
					scores.setOpponentScore(scores.getOpponentScore() + 1);
				}
				else {
					JOptionPane.showMessageDialog(null, "Tie score!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				resetGameBoard();
			}
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void setFont(FontUIResource myFont) {
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", myFont);
	    UIManager.put("Label.font", myFont);
	    UIManager.put("List.font", myFont);
	    UIManager.put("MenuBar.font", myFont);
	    UIManager.put("Menu.acceleratorFont", myFont);
	    UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.font", myFont);
	    UIManager.put("RadioButtonMenuItem.font", myFont);
	    UIManager.put("CheckBoxMenuItem.font", myFont);
	    UIManager.put("OptionPane.buttonFont", myFont);
	    UIManager.put("OptionPane.messageFont", myFont);
	    UIManager.put("Menu.font", myFont);
	    UIManager.put("PopupMenu.font", myFont);
	    UIManager.put("OptionPane.font", myFont);
	    UIManager.put("Panel.font", myFont);
	    UIManager.put("ProgressBar.font", myFont);
	    UIManager.put("ScrollPane.font", myFont);
	    UIManager.put("Viewport.font", myFont);
	    UIManager.put("TabbedPane.font", myFont);
	    UIManager.put("Slider.font", myFont);
	    UIManager.put("Table.font", myFont);
	    UIManager.put("TableHeader.font", myFont);
	    UIManager.put("TextField.font", myFont);
	    UIManager.put("Spinner.font", myFont);
	    UIManager.put("PasswordField.font", myFont);
	    UIManager.put("TextArea.font", myFont);
	    UIManager.put("TextPane.font", myFont);
	    UIManager.put("EditorPane.font", myFont);
	    UIManager.put("TabbedPane.smallFont", myFont);
	    UIManager.put("TitledBorder.font", myFont);
	    UIManager.put("ToolBar.font", myFont);
	    UIManager.put("ToolTip.font", myFont);
	    UIManager.put("Tree.font", myFont);
	    UIManager.put("FormattedTextField.font", myFont);
	    UIManager.put("IconButton.font", myFont);
	    UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
	    UIManager.put("InternalFrame.paletteTitleFont", myFont);
	    UIManager.put("InternalFrame.titleFont", myFont);
	}
}