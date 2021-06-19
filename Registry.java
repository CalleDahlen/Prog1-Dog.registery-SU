//Skrivet av Carl Dahlén cada7128
package prog1Uppgiften;

import java.util.ArrayList;

public class Registry {

	private ArrayList<Dog> dogs = new ArrayList<>();
	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<Auction> auctions = new ArrayList<>();
	private ArrayList<Dog> dogsInAuction = new ArrayList<>();

	public void removeAuction(Auction auction) {
		auctions.remove(auction);
	}

	public boolean dogIsInAuction(Dog dog) {
		if (dogsInAuction.contains(dog)) {
			return true;
		}
		return false;
	}

	public void registerNewAuction(Auction auction) {
		auctions.add(auction);
	}

	public void registerNewDogForAuction(Dog dog) {
		dogsInAuction.add(dog);
	}

	public void registerNewDog(Dog dog) {
		sortTheDogs();
		dogs.add(dog);
	}

	public void registerNewUser(User user) {
		users.add(user);
	}

	public ArrayList<Dog> cloneOfDogs() {
		sortTheDogs();
		ArrayList<Dog> cloneOfDogs = new ArrayList<Dog>(dogs);
		return cloneOfDogs;
	}

	public ArrayList<User> cloneOfUser() {
		ArrayList<User> cloneOfUser = new ArrayList<User>(users);
		return cloneOfUser;
	}

	public ArrayList<Auction> cloneOfAuction() {
		ArrayList<Auction> cloneOfAuction = new ArrayList<Auction>(auctions);
		return cloneOfAuction;
	}

	public Dog getDogsName(String name) {
		for (Dog dog : dogs) {
			if (dog.getName().equalsIgnoreCase(name)) {
				return dog;
			}
		}
		return null;
	}

	public User getUsersName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public Auction getDogInAuctionName(String name) {
		for (Auction auction : auctions) {
			if (auction.getDogForAuction().getName().equalsIgnoreCase(name)) {
				return auction;
			}
		}
		return null;
	}

	public void removeDogFromList(Dog dog) {
		dogs.remove(dog);
	}

	public void removeUserFromList(User user) {
		users.remove(user);

	}

	private ArrayList<Dog> sortTheDogs() {

		for (int index = 1; index < dogs.size(); index++) {
			Dog nextDog = dogs.get(index);
			int currentDogPosition = index - 1;

			while (currentDogPosition >= 0
					&& (dogs.get(currentDogPosition).getName()).compareTo(nextDog.getName()) > 0) {
				dogs.set(currentDogPosition + 1, dogs.get(currentDogPosition));
				currentDogPosition--;
			}
			dogs.set(currentDogPosition + 1, nextDog);
		}
		for (int index = 1; index < dogs.size(); index++) {
			Dog nextDog = dogs.get(index);
			int currentDogPosition = index - 1;

			while (currentDogPosition >= 0
					&& (dogs.get(currentDogPosition).getTailLength()) > nextDog.getTailLength()) {
				dogs.set(currentDogPosition + 1, dogs.get(currentDogPosition));
				currentDogPosition--;
			}
			dogs.set(currentDogPosition + 1, nextDog);
		}
		return dogs;
	}

}
