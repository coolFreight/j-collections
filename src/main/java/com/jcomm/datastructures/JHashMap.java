package com.jcomm.datastructures;


public class JHashMap<K, V> {

	private Bag<K, V>[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public JHashMap(int size) {
		this.size = size;
		array = new Bag[size];
	}

	public JHashMap() {
		this(10);
	}

	public void put(K key, V value) {

		int internalKey = generateKey(key);

		if (array[internalKey] == null) {
			Bag<K, V> bag = new Bag<K, V>(key, value, internalKey);
			array[internalKey] = bag;
		} else {
			int count = 0;
			while (array[internalKey] != null && count < size) {
				internalKey++;
				count++;
				if (internalKey == size) {
					internalKey = 0;
				}
			}
			if (count == size) {
				//throw new NoSuchMethodError("Resizing of the hashmap");
			} else {
				Bag<K, V> bag = new Bag<K, V>(key, value, internalKey);
				array[internalKey] = bag;
			}
		}
	}

	public V get(K key) {

		int internalKey = generateKey(key);
		int count = 0;
		while (array[internalKey] != null && count < size) {

			if (array[internalKey].getKey().equals(key)
					&& array[internalKey].hashCode() == internalKey)
				return array[internalKey].getValue();
			internalKey++;
			count++;
			if (internalKey == size) {
				internalKey = 0;
			}
		}

		return null;
	}

	private int generateKey(K key) {

		int internalKey = key.hashCode();
		return internalKey % size;
	}

}
