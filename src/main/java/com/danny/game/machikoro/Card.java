package com.danny.game.machikoro;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Danny
 *
 */
@AllArgsConstructor
@Data
@Builder
public class Card {

	private String name;
	private String type;
	private int cost;
	private int activationStartRange;
	private int activationEndRange;
	private int quantity;
	private int amount; //value you give/get on activation
	private String icon;
	
	/**
	 * Constructs a single card object from another card
	 * 
	 * @param card
	 */
	public Card(Card card) {
		name = card.getName();
		type = card.getType();
		cost = card.getCost();
		activationStartRange = card.getActivationStartRange();
		activationEndRange = card.getActivationEndRange();
		quantity = 1;
		amount = card.getAmount();
		icon = card.getIcon();
	}
	
	public boolean isActivated(int roll) {
		return roll >= activationStartRange && roll <= activationEndRange;

	}
	
	public boolean canBuy(int playerMoney) {
		return quantity > 0 && playerMoney >= cost;
	}
	
	/**
	 *  when a card is bought from computer
	 */
	public void playerBought() {
		quantity--;
	}
	
	/**
	 * when player buys a duplicate card, increase quantity
	 */
	public void boughtAdditional() {
		quantity++;
	}
	
	public int amountOnActivation(boolean hasShoppingMall, List<Card> cards) {
		if (hasShoppingMall && (isToastCard() || isCupCard())) {
			return (amount + 1) * quantity;
		} else {
			if (isCheeseFactory()) {
				int count = 0;
				for (Card card : cards) {
					if (card.isCowCard()) {
						count ++;
					}
				}
				return count * amount * quantity;
			} else if (isFurnitureFactory()) {
				int count = 0;
				for (Card card : cards) {
					if (card.isCogCard()) {
						count ++;
					}
				}
				return count * amount * quantity;
			} else if (isFruitMarket()) {
				int count = 0;
				for (Card card : cards) {
					if (card.isWheatCard()) {
						count ++;
					}
				}
				return count * amount * quantity;
			}
			return amount * quantity;
		}
	}
	
	public boolean isLandmark() {
		return "Landmarks".equalsIgnoreCase(type);
	}
	
	public boolean isTrainStation() {
		return "TrainStation".equalsIgnoreCase(name);
	}
	
	public boolean isShoppingMall() {
		return "ShoppingMall".equalsIgnoreCase(name);
	}
	
	public boolean isAmusementPark() {
		return "AmusementPark".equalsIgnoreCase(name);
	}
	
	public boolean isRadioTower() {
		return "RadioTower".equalsIgnoreCase(name);
	}
	
	public boolean isCheeseFactory() {
		return "CheeseFactory".equalsIgnoreCase(name);
	}
	
	public boolean isFurnitureFactory() {
		return "FurnitureFactory".equalsIgnoreCase(name);
	}
	
	public boolean isFruitMarket() {
		return "FruitMarket".equalsIgnoreCase(name);
	}
	
	public boolean isRedCard() {
		return "Restaurants".equalsIgnoreCase(type);
	}
	
	public boolean isBlueCard() {
		return "Primary Industry".equalsIgnoreCase(type);
	}
	
	public boolean isGreenCard() {
		return "Secondary Industry".equalsIgnoreCase(type);
	}
	
	public boolean isPurpleCard() {
		return "Major Establishment".equalsIgnoreCase(type);
	}
	
	public boolean isToastCard() {
		return "Toast".equalsIgnoreCase(icon);
	}
	
	public boolean isCupCard() {
		return "Cup".equalsIgnoreCase(icon);
	}
	
	public boolean isCowCard() {
		return "Cow".equalsIgnoreCase(icon);
	}
	
	public boolean isCogCard() {
		return "Cog".equalsIgnoreCase(icon);
	}
	
	public boolean isWheatCard() {
		return "Wheat".equalsIgnoreCase(icon);
	}
	
	public boolean isUniqueCard() {
		return "Cross".equalsIgnoreCase(icon);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Card) {
			Card otherCard = (Card) other;
			return name.equalsIgnoreCase(otherCard.getName());
		} else {
			return false;
		}
	}
	
}
