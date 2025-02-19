import java.util.*;

class Card {
    String value;
    String suit;

   
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    
    public void displayCard() {
        System.out.println(value + " of " + suit);
    }

   
    public String toString() {
        return value + " of " + suit;
    }

   
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value.equals(card.value) && suit.equals(card.suit);
    }

    
    public int hashCode() {
        return Objects.hash(value, suit);
    }
}

public class CardCollectionSystem {
    private Set<Card> cardSet; 
    private Map<String, List<Card>> suitMap; 

    public CardCollectionSystem() {
        cardSet = new HashSet<>();
        suitMap = new HashMap<>();
    }

   
    public void addCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (cardSet.contains(card)) {
            System.out.println("Error: Card \"" + card + "\" already exists.");
            return;
        }

        
        cardSet.add(card);

        
        suitMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(card);
        System.out.println("Card added: " + card);
    }

    
    public void findCardsBySuit(String suit) {
        if (suitMap.containsKey(suit) && !suitMap.get(suit).isEmpty()) {
            System.out.println("Cards of " + suit + ":");
            for (Card card : suitMap.get(suit)) {
                card.displayCard();
            }
        } else {
            System.out.println("No cards found for " + suit + ".");
        }
    }

    
    public void displayAllCards() {
        if (cardSet.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            System.out.println("All Cards:");
            for (Card card : cardSet) {
                card.displayCard();
            }
        }
    }

    
    public void removeCard(String value, String suit) {
        Card card = new Card(value, suit);
        if (cardSet.contains(card)) {
            cardSet.remove(card);
            suitMap.get(suit).remove(card);
            if (suitMap.get(suit).isEmpty()) {
                suitMap.remove(suit);
            }
            System.out.println("Card removed: " + card);
        } else {
            System.out.println("Card not found: " + card);
        }
    }

    public static void main(String[] args) {
        CardCollectionSystem cardCollectionSystem = new CardCollectionSystem();

       
        System.out.println("\nTest Case 1: No Cards Initially");
        cardCollectionSystem.displayAllCards(); // Expected: No cards found.

       
        System.out.println("\nTest Case 2: Adding Cards");
        cardCollectionSystem.addCard("Ace", "Spades"); // Expected: Card added: Ace of Spades
        cardCollectionSystem.addCard("King", "Hearts"); // Expected: Card added: King of Hearts
        cardCollectionSystem.addCard("10", "Diamonds"); // Expected: Card added: 10 of Diamonds
        cardCollectionSystem.addCard("5", "Clubs"); // Expected: Card added: 5 of Clubs

        
        System.out.println("\nTest Case 3: Finding Cards by Suit");
        cardCollectionSystem.findCardsBySuit("Hearts"); // Expected: King of Hearts

     
        System.out.println("\nTest Case 4: Searching Suit with No Cards");
        cardCollectionSystem.findCardsBySuit("Diamonds"); // Expected: No cards found for Diamonds.

      
        System.out.println("\nTest Case 5: Displaying All Cards");
        cardCollectionSystem.displayAllCards(); // Expected: Ace of Spades, King of Hearts, 10 of Diamonds, 5 of Clubs

     
        System.out.println("\nTest Case 6: Preventing Duplicate Cards");
        cardCollectionSystem.addCard("King", "Hearts"); // Expected: Error: Card "King of Hearts" already exists.

   
        System.out.println("\nTest Case 7: Removing a Card");
        cardCollectionSystem.removeCard("10", "Diamonds"); // Expected: Card removed: 10 of Diamonds

       
        System.out.println("\nAfter removing a card:");
        cardCollectionSystem.displayAllCards(); // Expected: Ace of Spades, King of Hearts, 5 of Clubs
    }
}
