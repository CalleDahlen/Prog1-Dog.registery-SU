//Skrivet av Carl Dahlén cada7128
package prog1Uppgiften;

public class Dog {
	private static final double STANDARD_TAX = 3.7;
	private static final int TAIL_COEFFICIENT = 10;
	private String name;
	private String breed;
	private int age;
	private int weight;
	private User owner;

	public Dog(String name, String breed, int age, int weight) {
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;

	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;

	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public double getTailLength() {
		if (breed.equalsIgnoreCase("tax") || breed.equalsIgnoreCase("dachshund")) {
			return STANDARD_TAX;
		} else {
			return age * ((double) weight / TAIL_COEFFICIENT);
		}
	}

	public void increaseAge() {
		age += 1;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}

}
