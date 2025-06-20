package com.jcomm.datastructures;

public class Bag<K, V> {

	private final K key;
	private final V value;
	private final int hashCode;
	
	public Bag(K key, V value, int hashCode){
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}

	public V getValue() {
		return value;
	}
	
	public K getKey(){
		return key;
	}
	
	public int hashCode(){
		return hashCode;
	}	
}
