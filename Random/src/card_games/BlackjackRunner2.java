/*
 * BlackjackRunner2.java
 * http://villa7.github.io
 *
 * Copyright (C) 2014-2014 Theodore Kluge
 */

package card_games;

import java.util.*;
import villa7.Print;

public class BlackjackRunner2 {
	
	private static double ver = 1.1;
	
	private static Bjp player[];
	private static Bjd dealer = new Bjd("Dealer");

	private static int suit;
	private static int value;
	private static int computerCard, userCard, aiCard;
	private static int numPlayers = 0;
	private static Print p = new Print();
	private static Hand hand = new Hand();
	private static Scanner s = new Scanner(System.in);
	private static String[] players;
	private static ArrayList<String> winners = new ArrayList<String>();

	private static boolean playerTurn = true, aiTurn = true, computerTurn = true;
	private static boolean running = true;
	private static boolean[] isFirstTurn, isAIFT;
	
	private static int userTotal[],
					   aiTotal[],
					   computerTotal;
	
	public static void main(String[] args) {
		
		p.nl("BlackjackGame v" + ver);
		p.nl();
		do {
			p.nl("Number of players (1 - 6):");
			numPlayers = s.nextInt();
		} while (numPlayers <= 0 || numPlayers > 6);

		//players = new String[numPlayers];
		player = new Bjp[numPlayers];

		for (int i = 0; i < player.length; i++) {
			p.nl("Enter name of player " + (i + 1));
			String n = s.next();
			//p.nl(i);
			if (n.equalsIgnoreCase("AI") || n.equalsIgnoreCase("BOT")) {
				for (int c = i; c < player.length; c++) {
					p.nl("Creating AI: " + c);
					player[c] = new Bjp("AI" + n, true);
				}
				i = player.length;
			} else {
				player[i] = new Bjp(n);
			}
		}
		
		while (running) {
			hand.newDeck(52);
			hand.newUsedDeck(52);
			
			if (playBlackjack()) {
				int numWinners = 0;
				p.nl("Winners:");
				for (String s : winners) {
					p.nl(s);
				}
				
			} else {
				p.nl("Dealer wins.");
			}
			p.nbsp(3);
			break;
		}
	}
	
	private static boolean playBlackjack() {
		//userTotal = new int[numPlayers - numAI];
		//aiTotal = new int[numAI];
		//computerTotal = 0;
		//computerTurn = true;
		//isFirstTurn = new boolean[numPlayers];
		//isAIFT = new boolean[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			p.nl("Set playerFirstTurn of player " + i + " to true.");
			player[i].setFirstTurn(true);
		}
		
		//draw 2 cards to start
		for (int i = 0; i < player.length; i++) {	
			player[i].addTotal(hand.drawRandomCard());
			player[i].addTotal(hand.drawRandomCard());
		}

		dealer.addTotal(hand.drawRandomCard());
		p.nl("The dealer has a " + computerTotal + " and an unknown card.");
		dealer.addTotal(hand.drawRandomCard());
		
		while(true) {
			
			for (int i = 0; i < numPlayers; i++) {
				p.nl("Player " + (i + 1) + " (" + player[i].getName() + "'s) turn:");
				playerTurn = true;
				while (playerTurn) {
					if (!player[i].isAI()) { //player is a human
						while (player[i].getTotal() > 21 && player[i].isFirstTurn()) {
							//playerTurn = false;
							//p.nl("Sorry, redrawing...");
							player[i].setTotal(0);
							player[i].addTotal(hand.drawRandomCard());
							player[i].addTotal(hand.drawRandomCard());
						}
						p.nl("Your cards add up to " + player[i].getTotal());
						
						p.nl("Enter an action: [hit | stand]");
						String userAction = player[i].getAction();
						
						if(userAction.equalsIgnoreCase("quit")) {
							running = false;
							return false;
						} else if (userAction.equalsIgnoreCase("hit")) {
							userCard = hand.drawRandomCard();
							p.nl("You hit.");
							p.nl("You drew a " + userCard);
							player[i].addTotal(userCard);
							p.nl("Your total is " + player[i].getTotal());
							if (player[i].getTotal() > 21) {
								p.nl("Player " + (i + 1) +  " loses.");
								playerTurn = false;
							}
						} else if (userAction.equalsIgnoreCase("stand")) {
							p.nl("You stand.");
							playerTurn = false;
						} else {
							p.nl("Please enter a valid action. [hit | stand]");
						}
						player[i].setFirstTurn(false);
					} else { //player is an AI
						while (player[i].getTotal() > 21 && player[i].isFirstTurn()) {
							player[i].setTotal(0);
							player[i].addTotal(hand.drawRandomCard());
							player[i].addTotal(hand.drawRandomCard());
						}
						if (player[i].getTotal() < 17) { //AI uses the dealer's logic
							aiCard = hand.drawRandomCard();
							p.nl("AI #" + (i+1) + " drew " + aiCard);
							player[i].addTotal(aiCard);
							p.nl("AI #" + (i+1) + " has a total of " + player[i].getTotal());
							if (aiTotal[i] > 21) {
								p.nl("AI #" + (i+1) + " loses.");
								playerTurn = false;
							}
						} else {
							p.nl("AI #" + (i+1) + " stands.");
							playerTurn = false;
						}
						player[i].setFirstTurn(false);
					}
				}
			}
			
			//Dumb AI
			while (computerTurn) {
				if (dealer.getTotal() == 21) { //if dealer has 21, he wins
					for (int i = 0; i < numPlayers; i++) {
						if (player[i].getTotal() == 21) { //unless player also has 21
							p.nl("checking total of p" + (i+1));
							winners.add(player[i].getName());
						}
					}
					if (winners.size() > 0) { //if there is at least one winner
						computerTurn = false;
						p.nl("There are " + winners.size() + " winners.");
						return true;
					} else {
						return false;
					}
				} else if (dealer.getTotal() < 17) {
					computerCard = hand.drawRandomCard();
					p.nl("Dealer drew " + computerCard);
					dealer.addTotal(computerCard);
					p.nl("Dealer has " + computerTotal);
				} else {
					p.nl("Dealer stands.");
					for (int i = 0; i < numPlayers; i++) {
						if (player[i].getTotal() > dealer.getTotal()) {
							p.nl("checking total of p" + (i+1));
							winners.add(player[i].getName());
						}
					}
					if (winners.size() > 0) {
						computerTurn = false;
						p.nl("There are " + winners.size() + " winners.");
						return true;
					} else {
						return false;
					}
				}
			}
			
			//win logic
			if (dealer.getTotal() > 21) {
				for (int i = 0; i < numPlayers; i++) {
					winners.add(player[i].getName());
				}
				if (winners.size() > 0) {
					p.nl("There are " + winners.size() + " winners.");
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
