package com.danny.game.machikoro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Danny
 *
 */
public class Board {
	
	private int playerTurn;
	private int turnPhase;
	
	private List<Player> players;
	private Map<String, Card> cardMap;
	
	private int bankMoney;
	
	public Board() {
		bankMoney = 2000;
		initCardMap();
	}
	
	public boolean isPlayerTurn(String playerId) {
		return players.get(playerTurn).getPlayerId().equalsIgnoreCase(playerId);
	}
	
	public Player getPlayer(String playerId) {
		for (Player player : players) {
			if (player.getPlayerId().equalsIgnoreCase(playerId)) {
				return player;
			}
		}
		return null;
	}
	
	public boolean canPlayerRoll(String playerId) {
		return isPlayerTurn(playerId) && turnPhase == 0;
	}
	
	public void earnIncome(int roll, Player current, List<Player> others) {
		handleRedCardIncome(roll, current, others);
		handleBlueCardIncome(roll, current, others);
		handleGreenCardIncome(roll, current);
		handlePurpleCardIncome(roll, current, others);
	}
	
	public boolean canPurchaseCard(String playerId, String cardName) {
		Player player = getPlayer(playerId);
		if (isPlayerTurn(playerId) && cardMap.containsKey(cardName)) {
			Card card = cardMap.get(cardName); //check if unique
			return card.canBuy(player.getMoney());
		}
		return false;
	}
	
	public void purchaseCard(String playerId, String cardName) {
		Card card = cardMap.get(cardName);
		Player player = getPlayer(playerId);
		card.playerBought();
		player.purchaseCard(card);
	}
	
	private void handleRedCardIncome(int roll, Player current, List<Player> others) {
		for (Player player : others) {
			for (Card card : player.getCards()) {
				if (card.isRedCard() && card.isActivated(roll)) {
					System.out.println("Amount Taken: " + player.takeBasedOffCard(current, card));
				}
			}
		}
	}
	
	private void handleBlueCardIncome(int roll, Player current, List<Player> others) {
		int amount;
		for (Card card : current.getCards()) {
			if (card.isBlueCard() && card.isActivated(roll)) {
				amount =  current.cardActivated(card);
				bankMoney -= amount;
				System.out.println("Amount Received: " + amount);
			}
		}
		for (Player player : others) {
			for (Card card : player.getCards()) {
				if (card.isBlueCard() && card.isActivated(roll)) {
					amount =  player.cardActivated(card);
					bankMoney -= amount;
					System.out.println("Amount Received: " + amount);
				}
			}
		}
	}
	
	private void handleGreenCardIncome(int roll, Player current) {
		int amount;
		for (Card card : current.getCards()) {
			if (card.isGreenCard() && card.isActivated(roll)) {
				amount = current.cardActivated(card);
				bankMoney -= amount;
				System.out.println("Amount Received: " + amount);
			}
		}
	}
	
	private void handlePurpleCardIncome(int roll, Player current, List<Player> others) {
		for (Player player : others) {
			for (Card card : player.getCards()) {
				if (card.isPurpleCard() && card.isActivated(roll)) {
					System.out.println("Amount Taken: " + current.takeBasedOffCard(player, card)); //tv station is special
				}
			}
		}
	}
	
	private void initCardMap() {
		cardMap = new HashMap<>();
		cardMap.put("WheatField", Card.builder()
									  .name("WheatField")
									  .type("Primary Industry")
									  .icon("Wheat")
									  .cost(1)
									  .activationStartRange(1)
									  .activationEndRange(1)
									  .quantity(10)
									  .amount(1)
									  .build());
		cardMap.put("Ranch", Card.builder()
				  .name("Ranch")
				  .type("Primary Industry")
				  .icon("Cow")
				  .cost(1)
				  .activationStartRange(2)
				  .activationEndRange(2)
				  .quantity(6)
				  .amount(1)
				  .build());
		cardMap.put("Forest", Card.builder()
				  .name("Forest")
				  .type("Primary Industry")
				  .icon("gear")
				  .cost(3)
				  .activationStartRange(5)
				  .activationEndRange(5)
				  .quantity(6)
				  .amount(1)
				  .build());
		cardMap.put("Mine", Card.builder()
				  .name("Mine")
				  .type("Primary Industry")
				  .icon("gear")
				  .cost(6)
				  .activationStartRange(9)
				  .activationEndRange(9)
				  .quantity(6)
				  .amount(5)
				  .build());
		cardMap.put("AppleOrchard", Card.builder()
				  .name("AppleOrchard")
				  .type("Primary Industry")
				  .icon("Wheat")
				  .cost(3)
				  .activationStartRange(10)
				  .activationEndRange(10)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("Bakery", Card.builder()
				  .name("Bakery")
				  .type("Secondary Industry")
				  .icon("Toast")
				  .cost(1)
				  .activationStartRange(2)
				  .activationEndRange(3)
				  .quantity(10)
				  .amount(1)
				  .build());
		cardMap.put("ConvenienceStore", Card.builder()
				  .name("ConvenienceStore")
				  .type("Secondary Industry")
				  .icon("Toast")
				  .cost(2)
				  .activationStartRange(4)
				  .activationEndRange(4)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("CheeseFactory", Card.builder()
				  .name("CheeseFactory")
				  .type("Secondary Industry")
				  .icon("factory")
				  .cost(5)
				  .activationStartRange(7)
				  .activationEndRange(7)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("FurnitureFactory", Card.builder()
				  .name("FurnitureFactory")
				  .type("Secondary Industry")
				  .icon("factory")
				  .cost(3)
				  .activationStartRange(8)
				  .activationEndRange(8)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("FruitMarket", Card.builder()
				  .name("FruitMarket")
				  .type("Secondary Industry")
				  .icon("factory")
				  .cost(2)
				  .activationStartRange(11)
				  .activationEndRange(12)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("Cafe", Card.builder()
				  .name("Cafe")
				  .type("Restaurants")
				  .icon("Cup")
				  .cost(2)
				  .activationStartRange(3)
				  .activationEndRange(3)
				  .quantity(6)
				  .amount(1)
				  .build());
		cardMap.put("FamilyRestaurant", Card.builder()
				  .name("FamilyRestaurant")
				  .type("Restaurants")
				  .icon("Cup")
				  .cost(3)
				  .activationStartRange(9)
				  .activationEndRange(10)
				  .quantity(6)
				  .amount(3)
				  .build());
		cardMap.put("Stadium", Card.builder()
				  .name("Stadium")
				  .type("Major Establishment")
				  .icon("Cross")
				  .cost(6)
				  .activationStartRange(6)
				  .activationEndRange(6)
				  .quantity(6)
				  .amount(2)
				  .build());
		cardMap.put("TvStation", Card.builder()
				  .name("TvStation")
				  .type("Major Establishment")
				  .icon("Cross")
				  .cost(7)
				  .activationStartRange(6)
				  .activationEndRange(6)
				  .quantity(6)
				  .amount(5)
				  .build());
		cardMap.put("BusinessCenter", Card.builder()
				  .name("BusinessCenter")
				  .type("Major Establishment")
				  .icon("Cross")
				  .cost(8)
				  .activationStartRange(6)
				  .activationEndRange(6)
				  .quantity(6)
				  .amount(0)
				  .build());
		cardMap.put("TrainStation", Card.builder()
				  .name("TrainStation")
				  .type("Landmarks")
				  .icon("Cross")
				  .cost(4)
				  .activationStartRange(100)
				  .activationEndRange(100)
				  .quantity(6)
				  .amount(0)
				  .build());
		cardMap.put("ShoppingMall", Card.builder()
				  .name("ShoppingMall")
				  .type("Landmarks")
				  .icon("Cross")
				  .cost(10)
				  .activationStartRange(100)
				  .activationEndRange(100)
				  .quantity(6)
				  .amount(0)
				  .build());
		cardMap.put("AmusementPark", Card.builder()
				  .name("AmusementPark")
				  .type("Landmarks")
				  .icon("Cross")
				  .cost(16)
				  .activationStartRange(100)
				  .activationEndRange(100)
				  .quantity(6)
				  .amount(0)
				  .build());
		cardMap.put("RadioTower", Card.builder()
				  .name("RadioTower")
				  .type("Landmarks")
				  .icon("Cross")
				  .cost(22)
				  .activationStartRange(100)
				  .activationEndRange(100)
				  .quantity(6)
				  .amount(0)
				  .build());
	}

}
