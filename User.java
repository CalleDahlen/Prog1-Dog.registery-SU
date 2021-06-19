//Skrivet av Carl Dahlén cada7128
package prog1Uppgiften;

import java.util.Arrays;

public class User {
	private String name;
	private Dog[] userDogs;

	public User(String name) {
		this.name = name;
		this.userDogs = new Dog[0];
	}

	public String getName() {
		return name;
	}

	public void addNewDog(Dog dog) {

		Dog[] newUsersDogs = new Dog[userDogs.length + 1];
		for (int i = 0; i < userDogs.length; i++) {
			newUsersDogs[i] = userDogs[i];
		}
		newUsersDogs[userDogs.length] = dog;
		userDogs = newUsersDogs;
	}

	public String printUsersDogs() {
		if (userDogs != null) {
			return Arrays.toString(userDogs);
		} else
			return null;
	}

	public Dog[] getUserDog() {
		Dog[] cloneOfUserDogs = userDogs.clone();
		return cloneOfUserDogs;
	}

	public void removeDogFromUser(Dog dog) {
		boolean dogHasOwner = false;
		int i = 0;
		Dog[] newUsersDogs = new Dog[userDogs.length];
		for (int j = 0; j < userDogs.length; j++) {
			if (userDogs[j] != null) {
				if (userDogs[j].equals(dog) && !dogHasOwner) {
					dogHasOwner = true;
				} else {
					newUsersDogs[i] = userDogs[j];
					i++;
				}
			}
		}
		if (dogHasOwner) {
			userDogs = Arrays.copyOf(newUsersDogs, newUsersDogs.length - 1);
		}
	}

}
