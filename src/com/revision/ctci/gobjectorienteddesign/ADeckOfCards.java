package com.revision.ctci.gobjectorienteddesign;

public class ADeckOfCards {
    public static void main(String[] args) {
        /* Design a data structures for a generic deck of cards. Explain how you would subclass
         * the data structures to implement blackjack */
        /* STEP 1: WHO WHAT WHEN WHERE HOW WHY */
        /* $ Purpose of building the deck of cards is that any game can be played with it
         *    each color has 13 cards 4 * 13 (52 cards) usually 2 Joker Cards will be present
         *    does a blackjack require a joker?
         *    - some games are played with only the face cards
         *    - some only with numbers
         *    - Factory Pattern can be used to determine the type of game and generate the deck dynamically on that
         * $ How many decks can be used also depends on the user so we would need to determine that as well
         * $ Simple data structure vs custom class?
         *    - 13 * 4 (52 cards)
         *    - card color, card sign (clover | spade | diamond | heart)
         *    - face value (ACE can have 1 or 14 in some games all FACE CARDS will have a point of 10 and so we can have like alternate value variable)
         *    - if shuffling is involved we might need to swap frequently and we can use ARRAY or as we can refer the index to that card
         *    - in case we can draw any card at random then we adjust the deck each time when a card is removed i.e. in this case a LINKED LIST would be good
         *      as even though ARRAY LIST can be used to dynamically resize internally the resizing amortized time is only BigO(1) ideally when there is
         *      frequent resizing the array list not a good approach
         *    - linked list has its own draw back that we have to each time navigate from head or tail in case we use doubly linked list
         *    - CLASSES: [DECK | CARD] DECK will have array of CARDS and each card will have a [COLOR | SIGN | FACE VALUE | ALTERNATE VALUE] attributes
         * $ BLACK JACK | POKER | RUMMY | FIVE CARDS
         *    - BLACK JACK: NO JOKERS
         *    - 21 is the limit one can get
         *    - Since we are not actually going to implement the game we can ignore others and consider only no jokers rule
         * $ Does the Deck have methods for
         *    - drawing the cards?
         *    - shuffle the cards?
         *    - maintain separate stacks for each player? (This depends on the game)
         *    - reveal top cards?
         * */
        /* STEP 2: OVERALL COMPONENTS */
        /* STEP 3: RELATIONS */
        /* STEP 4: FUNCTIONS */
        Game blackJack = Game.createGame(GameType.BLACK_JACK, 5);
        if (blackJack != null) {
            blackJack.start();
        } else {
            System.out.println("Sorry game unavailable");
        }

        Game poker = Game.createGame(GameType.POKER, 3);
        if (poker != null) {
            poker.start();
        } else {
            System.out.println("Sorry game unavailable");
        }
    }
}

enum GameType {
    BLACK_JACK("Black Jack"), POKER("Poker"), RUMMY("Rummy"), FIVE_CARDS("Five Cards");
    private String name;

    private GameType(String name) {
        this.name = name;
    }
}

enum CardColor {RED, BLACK}

enum CardSign {CLOVER, SPADE, DIAMOND, HEARTS}

/* IMPLEMENTED FACE VALUES AND ALTERNATE VALUES FOR HANDLING ACE. IF NEEDED WE CAN ALTER THE BEHAVIOUR HERE */
enum FaceValue {
    ACE(1, 14), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
    private int faceValue;
    private int alternateValue;

    private FaceValue(int faceValue, int alternateValue) {
        this.faceValue = faceValue;
        this.alternateValue = alternateValue;
    }

    private FaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getAlternateValue() {
        return alternateValue;
    }
}

class Deck {
    Card[] cards;

    public Deck(int numberOfDeck, GameType gameType) {
        this.cards = prepareDeck(numberOfDeck, gameType);
    }

    private Card[] prepareDeck(int numberOfDeck, GameType gameType) {
        if (gameType == GameType.BLACK_JACK) {
            Card[] cards = new Card[ numberOfDeck * 52 ];
            int index=0;
            for (int deckNumber = 0; deckNumber < numberOfDeck; deckNumber++) {
                for (CardSign cardSign : CardSign.values()) {
                    for (FaceValue item : FaceValue.values()) {
                        CardColor color = (cardSign.name().equalsIgnoreCase(CardSign.CLOVER.name()) || cardSign.name().equalsIgnoreCase(CardSign.SPADE.name())) ? CardColor.BLACK : CardColor.RED;
                        cards[index] = new Card(color.name(), cardSign.name(), item.getFaceValue(), item.getAlternateValue());
                        index++;
                    }
                }
            }
            System.out.println("Prepared Deck....");
            for (Card card : cards) {
                System.out.println(card.getFaceValue() + " of " + card.getSign());
            }
            return cards;
        } else if (gameType == GameType.POKER) {

        } else if (gameType == GameType.RUMMY) {

        } else if (gameType == GameType.FIVE_CARDS) {

        }
        return null;
    }

    public Card drawCard() {
        /* Could be implemented under the deck class if needed */
        System.out.println("Card Drawn");
        return null;
    }

    public void shuffleCards() {
        /* Could be implemented under the deck class if needed */
        System.out.println("Deck Shuffled");
    }
}

class Card {
    private String color;
    private String sign;
    private int faceValue;
    private int alternateValue;

    public Card(String color, String sign, int faceValue, int alternateValue) {
        this.color = color;
        this.sign = sign;
        this.faceValue = faceValue;
        this.alternateValue = alternateValue;
    }

    public String getColor() {
        return color;
    }

    public String getSign() {
        return sign;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getAlternateValue() {
        return alternateValue;
    }
}

/* Created the Game and class and the BlackJack and others as a sub class and declared the variables which
 * are common in all the games under the Game class and implemented an interface Core to handle the game logic
 * which differs based on each games implementation. */
abstract class Game implements Core {
    String gameName;
    int numberOfPlayers;
    Rules rules;

    public abstract void start();

    static Game createGame(GameType gameType, int numberOfPlayers) {
        if (gameType == GameType.BLACK_JACK) {
            return new BlackJack(GameType.BLACK_JACK, numberOfPlayers);
        } else if (gameType == GameType.POKER) {
            return new Poker();
        } else if (gameType == GameType.RUMMY) {
            return new Rummy();
        } else if (gameType == GameType.FIVE_CARDS) {
            return new FiveCards();
        }
        return null;
    }

    public Rules getRules(GameType gameType) {
        return new Rules(gameType);
    }

    /* Getters */
    public String getGameName() {
        return gameName;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Rules getRules() {
        return rules;
    }
}

interface Core {
    public void start();
}

class BlackJack extends Game {
    public Deck deck;

    public BlackJack(GameType gameType, int numberOfPlayers) {
        this.gameName = gameType.name();
        this.numberOfPlayers = numberOfPlayers;
        this.rules = getRules(gameType);
        this.deck = new Deck(2, gameType);
    }

    public void start() {
        /* Created this as an interface (Core) method so that we are bound to implement it */
        System.out.println("Black Jack Game Started");
    }
}

class Poker extends Game {
    public Rules rules;

    public void start() {
        /* Could be implemented */
        System.out.println("Poker Game Started");
    }
}

class Rummy extends Game {
    public Rules rules;

    public void start() {
        /* Could be implemented */
    }
}

class FiveCards extends Game {
    public Rules rules;

    public void start() {
        /* Could be implemented */
    }
}

/* CURRENTLY FOR DESIGNING PURPOSE WE HAVE JUST CREATED A STRING VARIABLE
 * IT CAN BE EXTENDED FOR DEFINING THE RULES OF THE GAME */
class Rules {
    String rules;

    public Rules(String rules) {
        this.rules = rules;
    }

    public Rules(GameType gameType) {
        if (gameType == GameType.BLACK_JACK) {
            new Rules("Black Jack Rules");
        } else if (gameType == GameType.POKER) {
            new Rules("Poker Rules");
        } else if (gameType == GameType.RUMMY) {
            new Rules("Rummy Rules");
        } else if (gameType == GameType.FIVE_CARDS) {
            new Rules("Five Cards Rules");
        }
    }
}