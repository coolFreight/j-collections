package com.jcomm.warmups;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SmartBuyer {
	Map<String, Item> mapOfItems;
	List<Item> items;

	public SmartBuyer() {

		items = new LinkedList<>();

		String[] s = { "Burger" };
		Item one = new Item(1, 5, s, "Burger");

		String[] b = { "French_Frice" };
		Item two = new Item(2, 4, b, "French_Frice");

		String[] c = { "Coldrink" };
		Item three = new Item(3, 8, c, "Coldrink");

		String[] d = { "Burger", "French_Frice", "Coldrink" };
		Item four = new Item(4, 12, d, "Burger,French_Frice,Coldrink");

		String[] e = { "Burger", "Coldrink" };
		Item five = new Item(5, 14, e, "Burger, Coldrink");

		items.add(one);
		items.add(two);
		items.add(three);
		items.add(four);
		items.add(five);
		mapOfItems = new HashMap<>();
		mapOfItems.put("Burger", one);
		mapOfItems.put("French_Frice", two);
		mapOfItems.put("Coldrink", three);
		mapOfItems.put("Burger,French_Frice,Coldrink", four);
		mapOfItems.put("Burger, Coldrink", five);
	}

	public static void main(String[] args) {

		SmartBuyer s = new SmartBuyer();
		List<String> l = new LinkedList<>();
		l.add("Burger");
		l.add("Coldrink");
		s.buyer(l);
	}

	public void buyer(List<String> itemsToBuy) {

		int itemsSeparate = 0;
		List<Integer> itemListSeparate = new LinkedList<>();

		boolean[] items = new boolean[mapOfItems.size()];
		for (String name : itemsToBuy) {
			itemsSeparate += mapOfItems.get(name).getValue();
			itemListSeparate.add(mapOfItems.get(name).getItemNumber());

			for (Entry<String, Item> o : mapOfItems.entrySet()) {
					if (!o.getValue().contains(name)) {
						// true means does not have the value
						items[o.getValue().getItemNumber() - 1] = true;
					}
			}
		}

		int itemTogetherNumber = -1;
		int together = 999999;
		for (Entry<String, Item> o : mapOfItems.entrySet()) {
			
			if (!items[o.getValue().getItemNumber() - 1]) {

				if (o.getValue().getValue() < together) {
					together = o.getValue().getValue();
					itemTogetherNumber = o.getValue().getItemNumber();
				}

			}
		}

		if (itemsSeparate < together) {
			for (Integer i : itemListSeparate)
				System.out.print(i + ",");
		} else {

			System.out.println(itemTogetherNumber);
		}

	}

	public class Item {

		private final int itemNumber;
		private final Map<String, String> mapItems = new HashMap<>();
		String name;
		private final int value;

		public Item(int itemNumber, int itemPrice, String[] itemNames,
				String name) {
			this.itemNumber = itemNumber;
			this.value = itemPrice;
			this.name = name;

			for (String s : itemNames) {
				mapItems.put(s, s);

			}
		}

		public int getItemNumber() {
			return itemNumber;
		}

		public int getValue() {
			return value;
		}

		public boolean contains(String item) {
			return mapItems.containsKey(item);
		}

		public String getName() {
			return this.name;
		}

		public boolean isCombo() {
			return mapItems.size() > 1;
		}
	}
}
