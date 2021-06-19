//Skrivet av Carl Dahlén cada7128
package prog1Uppgiften;

import java.util.ArrayList;
import java.util.Scanner;

public class DogProgram {
	private Registry register = new Registry();
	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		DogProgram program = new DogProgram();
		program.start();

	}

	private String scanString() {
		return input.nextLine();
	}

	private String enterName() {
		String name;
		do {
			name = scanString();
			name = name.trim();
			if (name.isBlank()) {
				System.out.println("Error: name can't be empty");
				System.out.println("Name?>");
			}
		} while (name.isBlank());
		return name;
	}

	private String enterBreed() {
		String breed;
		do {
			breed = scanString();
			breed = breed.trim();
			if (breed.isBlank()) {
				System.out.println("Error: name can't be empty");
				System.out.println("Breed?>");
			}
		} while (breed.isBlank());
		return breed;
	}

	private int scanInt() {
		int number = input.nextInt();
		input.nextLine();
		return number;
	}

	private double scanDouble() {
		return input.nextDouble();
	}

	private void initialize() {
		System.out.println("Hello and welcome to the dog program!");
		System.out.println();
		
	}

	private void runCommandLoop() {
		String command;
		do {
			command = readCommand();
			handleCommand(command);

		} while (!"exit".equals(command));
	}

	private void handleCommand(String command) {
		switch (command) {

		case "register new dog":
		case "rnd":
			registerNewDog();
			break;
		case "remove dog":
			removeDog();
			break;
		case "increase age":
			increaseAge();
			break;
		case "list dogs":
			listDogs();
			break;
		case "exit":
			break;
		case "register new user":
		case "rnu":
			registerNewUser();
			break;
		case "remove user":
			removeUser();
			break;
		case "list users":
			listUser();
			break;
		case "give dog":
			giveDog();
			break;
		case "start auction":
			startAuction();
			break;
		case "make bid":
			makeNewBid();
			break;
		case "list auctions":
			listAuctions();
			break;
		case "list bids":
			listBids();
			break;
		case "close auction":
			closeAuction();
			break;
		default:
			System.out.println("Error, not a command");
		}
	}

	private String readCommand() {
		System.out.print("Command?> ");
		String command = scanString();
		return command;
	}

	private void closeComandLoop() {
		System.out.println();
		System.out.println("Goodbye!");
		input.close();
	}

	private void registerNewDog() {
		System.out.print("Name?>");
		String name = enterName();
		System.out.print("Breed?>");
		String breed = enterBreed();
		System.out.print("Age?> ");
		int age = scanInt();
		System.out.print("Weight?> ");
		int weight = scanInt();
		register.registerNewDog(new Dog(name, breed, age, weight));
		System.out.println(name + " added to the register");

	}

	private void registerNewUser() {
		System.out.print("Name?>");
		String name = enterName();
		register.registerNewUser(new User(name));
		System.out.println(name + " added to the register");
	}

	private void listDogs() {
		ArrayList<Dog> dogs = register.cloneOfDogs();
		if (dogs.isEmpty()) {
			System.out.println("Error: no dogs in the register!");
		} else {
			System.out.print("Smallest tail length to display?> ");
			double tailLengthToShow = scanDouble();
			input.nextLine();
			System.out.println("The following dogs has such a large tail: ");
			for (Dog dog : dogs) {
				if (dog.getTailLength() >= tailLengthToShow && dog.getOwner() != null) {
					System.out.printf("* %s (%s, %d years, %d kilo, %.2f cm tail, owned by %s) \n", dog.getName(),
							dog.getBreed(), dog.getAge(), dog.getWeight(), dog.getTailLength(),
							dog.getOwner().getName());
				} else if (dog.getTailLength() >= tailLengthToShow) {
					System.out.printf("* %s (%s, %d years, %d kilo, %.2f cm tail) \n", dog.getName(), dog.getBreed(),
							dog.getAge(), dog.getWeight(), dog.getTailLength());
				}
			}
		}
	}

	private void listUser() {
		ArrayList<User> users = register.cloneOfUser();
		if (users.isEmpty()) {
			System.out.println("Error: no users in the register!");
		} else {
			for (User user : users) {
				if (user.getUserDog() != null) {
					System.out.printf("%s %s\n", user.getName(), user.printUsersDogs());
				} else
					System.out.printf("%s [] \n", user.getName());

			}
		}
	}

	private void increaseAge() {
		System.out.print("Enter the name of the dog?> ");
		String name = enterName();
		Dog dog = register.getDogsName(name);
		if (dog != null) {
			dog.increaseAge();
			System.out.println(dog.getName() + " is now one year older");

		} else {
			System.out.println("Error: no such dog");
		}
	}

	private void removeDog() {
		ArrayList<User> users = register.cloneOfUser();
		System.out.print("Enter the name of the dog?> ");
		String name = enterName();
		Dog dog = register.getDogsName(name);
		Auction auction = register.getDogInAuctionName(name);
		if (register.dogIsInAuction(dog)) {
			register.removeAuction(auction);
		}
		if (dog != null && dog.getOwner() != null) {
			register.removeDogFromList(dog);
			for (User user : users) {
				user.removeDogFromUser(dog);
			}
			System.out.println(dog.getName() + " is now removed from the register");
		} else if (dog != null) {
			register.removeDogFromList(dog);
			System.out.println(dog.getName() + " is now removed from the register");
		} else {
			System.out.println("Error: no such dog");
		}
	}

	private void removeUser() {
		ArrayList<Auction> auctions = register.cloneOfAuction();
		System.out.print("Enter the name of the user?>");
		String name = enterName();
		User user = register.getUsersName(name);
		if (user != null) {
			register.removeUserFromList(user);
			System.out.println(user.getName() + " is now removed from the register");

			if (user.getUserDog() != null) {
				Dog[] usersDogs = user.getUserDog();
				for (Dog dog : usersDogs) {
					user.removeDogFromUser(dog);
					register.removeDogFromList(dog);
				}
			}
			for (Auction auction : auctions) {
				auction.removeBidFromUser(user);
			}
		} else
			System.out.println("Error: no such user");
	}

	private void giveDog() {

		System.out.print("Enter the name of the dog?> ");
		String name = enterName();
		Dog dog = register.getDogsName(name);
		if (!register.cloneOfDogs().contains(dog)) {
			System.out.println("Error: no such dog");
		}
		if (dog != null && dog.getOwner() != null) {
			System.out.println("Error: the dog already has an owner");
		}
		if (dog != null && dog.getOwner() == null) {
			System.out.print("Enter the name of the new owner?>");
			String ownerName = enterName();
			User user = register.getUsersName(ownerName);
			if (user != null) {
				dog.setOwner(user);
				user.addNewDog(dog);
				System.out.println(ownerName + " now owns " + name);
			} else {
				System.out.println("Error: no such user");
			}
		}
	}

	private void startAuction() {
		System.out.print("Enter the name of the dog?> ");
		String name = enterName();
		Dog dog = register.getDogsName(name);
		int auctionNumber = 1;
		if (dog != null) {
			if (dog.getOwner() != null) {
				System.out.println("Error: this dog already has an owner");
			}
			if (register.dogIsInAuction(dog)) {
				System.out.println("Error: this dog is already up for auction");
			}
			if (dog.getOwner() == null && !register.dogIsInAuction(dog)) {
				Auction auction = new Auction(dog, auctionNumber);
				register.registerNewDogForAuction(dog);
				register.registerNewAuction(auction);
				System.out.printf("%s has been put up for auction in auction #%d\n", dog.getName(),
						auction.getAuctionNumber());
			}
		} else
			System.out.println("Error: no such dog");
	}

	private User getAuctionBidder() {
		System.out.print("Enter the name of the user?> ");
		String biddersName = enterName();
		User bidder = register.getUsersName(biddersName);
		if (!register.cloneOfUser().contains(bidder)) {
			System.out.println("Error: no such user");
		}
		return bidder;
	}

	private Auction getAuctionDog() {
		System.out.print("Enter the name of the dog?> ");
		String auctionForDog = enterName();
		Auction auction = register.getDogInAuctionName(auctionForDog);
		if (!register.cloneOfAuction().contains(auction)) {
			System.out.println("Error: this dog is not up for auction");
		}

		return auction;
	}

	private void makeNewBid() {
		User bidder = getAuctionBidder();
		if (bidder != null) {
			Auction auction = getAuctionDog();
			if (auction != null) {
				boolean bidIsTooLow = true;
				while (bidIsTooLow) {
					System.out.printf("Amount to bid(min %d)?>", auction.getLeastAllowedBid());
					int bidAmount = scanInt();
					if (bidAmount < auction.getLeastAllowedBid()) {
						System.out.println("Error: too low bid!");
					} else if (bidAmount >= auction.getLeastAllowedBid()) {
						Bid bid = new Bid(bidder, bidAmount, auction);
						auction.removeBidFromUser(bidder);
						auction.registerNewBid(bid);
						System.out.println("Bid made");
						bidIsTooLow = false;
					}
				}
			}
		}
	}

	private void listAuctions() {
		ArrayList<Auction> auctions = register.cloneOfAuction();
		if (auctions.isEmpty()) {
			System.out.println("Error: no auctions in progress");
		} else
			for (Auction auction : auctions) {
				if (auction.dogHasBid(auction) && auction.getThirdHighestBid() != null) {
					System.out.printf("Auction #%d: %s. Top bids:[%s kr %s kr %s kr]\n", auction.getAuctionNumber(),
							auction.getDogForAuction(), auction.printHighestBid(), auction.printSecondHighestBid(),
							auction.printThirdHighestBid());
				} else if (auction.dogHasBid(auction) && auction.getSecondHighestBid() != null) {
					System.out.printf("Auction #%d: %s. Top bids:[%s kr %s kr]\n", auction.getAuctionNumber(),
							auction.getDogForAuction(), auction.printHighestBid(), auction.printSecondHighestBid());
				} else if (auction.dogHasBid(auction) && auction.getSecondHighestBid() == null) {
					System.out.printf("Auction #%d: %s. Top bids:[%s kr]\n", auction.getAuctionNumber(),
							auction.getDogForAuction(), auction.printHighestBid());
				}

				else {
					System.out.printf("Auction #%d: %s. Top bids:[]\n", auction.getAuctionNumber(),
							auction.getDogForAuction());
				}
			}

	}

	private void listBids() {
		System.out.print("Enter the name of the dog?> ");
		String dogName = enterName();
		Auction auction = register.getDogInAuctionName(dogName);
		if (auction != null) {
			if (auction.dogHasBid(auction)) {
				System.out.printf(auction.printBids() == null ? "" : auction.printBids() + "\t");

			} else if (!auction.dogHasBid(auction)) {
				System.out.println("No bids registred yet for this dog");
			}
		} else
			System.out.println("Error: this dog is not up for auction");
	}

	private void closeAuction() {
		System.out.print("Enter the name of the dog?> ");
		String auctionForDog = enterName();
		Auction auction = register.getDogInAuctionName(auctionForDog);
		if (auction != null) {
			if (auction.dogHasBid(auction)) {
				Bid bid = auction.getHighestBid();
				auction.getDogForAuction().setOwner(bid.getBidder());
				bid.getBidder().addNewDog(auction.getDogForAuction());
				register.removeAuction(auction);
				System.out.println("The auction is closed. The winning bid was "
						+ auction.getHighestBid().getBidAmount() + "kr and was made by " + bid.getBidder().getName());
			} else if (!auction.dogHasBid(auction)) {
				register.removeAuction(auction);
				System.out.println(
						"The auction is closed. No bids were made for " + auction.getDogForAuction().getName());
			}

		} else
			System.out.println("Error: this dog is not up for auction");
	}

	private void start() {
		initialize();
		runCommandLoop();
		closeComandLoop();
	}

}
