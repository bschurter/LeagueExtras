import java.util.Random;

public class Deck {
	private int cards[];
	private int nextCardIndex = 0;
	
	public Deck() {
		cards = new int[40];
		int index = 0;
		for (int i = 1; i <= 10; i++) {
			cards[index] = i;
			index++;
			cards[index] = i;
			index++;
			cards[index] = i;
			index++;
			cards[index] = i;
			index++;
		}
	}
	
	public void shuffle() {
		nextCardIndex=0;
		Random rnd = new Random();
		for (int i = 0; i < 10000; i++) {
			// Get two random card indexes
			int cardIndex1 = rnd.nextInt(cards.length);
			int cardIndex2 = rnd.nextInt(cards.length);
			// Get the card values at those indexes
			int cardValue1 = cards[cardIndex1];
			int cardValue2 = cards[cardIndex2];
			// Swap the values
			cards[cardIndex1] = cardValue2;
			cards[cardIndex2] = cardValue1;
		}
		
	}
	
	public int getCardsLeft() {
		return cards.length - nextCardIndex;
	}
	
	public int dealCard() {
		if (getCardsLeft() > 0) {
			int card = cards[nextCardIndex];
			nextCardIndex++;
			return card;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < cards.length; i++) {
			builder.append(cards[i] + " ");
		}
		return builder.toString();
	}

	
}











