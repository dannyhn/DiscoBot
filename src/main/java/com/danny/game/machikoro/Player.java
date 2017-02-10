package com.danny.game.machikoro;

import java.util.List;

import lombok.Data;

/**
 * @author Danny
 *
 */
@Data
public class Player {

	private String playerId;
	private int money;
	private List<Card> cards;
	
	public void purchaseCard(Card card) {
		money -= card.getCost();
		card.playerBought();
		if (cards.contains(card)) {
			Card currentCard = cards.get(cards.indexOf(card));
			currentCard.boughtAdditional();
		} else {
			cards.add(new Card(card));
		}
	}
	
	public boolean canPurchaseCard(Card card) {
		if (card.isUniqueCard() && cards.contains(card)) {
			return false;
		} else {
			return card.canBuy(money);
		}
	}
	
	public boolean hasShoppingMall() {
		for (Card card : cards) {
			if (card.isShoppingMall()) {
				return true;
			}
		}
		return false;
	}
	
	public int give(int amount) {
		if (amount >= money) {
			money = 0;
			return money;
		} else {
			money -= amount;
			return amount;
		}
	}
	
	public int cardActivated(Card card) {
		int amount = card.amountOnActivation(hasShoppingMall(), cards);
		money += amount;
		return amount;
	}
	
	/**
	 * 
	 * 
	 * @param other
	 * @param redCard
	 */
	public int takeBasedOffCard(Player other, Card card) {
		int amount = card.amountOnActivation(hasShoppingMall(), cards);
		int amountTaken = other.give(amount);
		money += amountTaken;
		return amountTaken;
	}
	
}
