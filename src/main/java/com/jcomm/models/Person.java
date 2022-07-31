package com.jcomm.models;

import java.util.Comparator;
import java.util.TreeSet;

public class Person extends Mammal  implements Runnable, Comparable<Person>{

	private Dog d;
	private byte age;
	private String name;

	public Person(Dog d){
		super(d);
		this.d = d;
	}

	public void run(){
		d.printDogNameHoldLock();
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person{" +
				"age=" + age +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public int compareTo(Person o) {
		return Byte.valueOf(age).compareTo(o.age);
	}

	public static void main(String[] args) {
		Person p1 = new Person(new Dog());
		p1.setAge((byte)9);
		p1.setName("joy");

		Person p2 = new Person(new Dog());
		p2.setAge((byte)15);
		p2.setName("zam");

		Person p3 = new Person(new Dog());
		p3.setAge((byte)27);
		p3.setName("pop");

		Person p4 = new Person(new Dog());
		p4.setAge((byte)30);
		p4.setName("del");

		Person p5 = new Person(new Dog());
		p5.setAge((byte)7);
		p5.setName("red");

		TreeSet<Person> people = new TreeSet<>(Comparator.comparing((Person::getName)));
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);
		people.add(p5);
		people.stream().forEach(System.out::println);

	}

}


