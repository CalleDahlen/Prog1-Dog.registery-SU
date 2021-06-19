//Skrivet av Carl Dahlén cada7128

package prog1Uppgiften;

import java.util.ArrayList;

public class Auction {
	private static int numerOfCurrentAuctions = 1;
	private Dog dogForAuction;
	private int auctionNumber;
	private ArrayList<Bid> bids = new ArrayList<>();

	public Auction(Dog dog, int auctionNumber) {
		this.dogForAuction = dog;
		this.auctionNumber = numerOfCurrentAuctions;
		numerOfCurrentAuctions++;
	}

	public Dog getDogForAuction() {
		return dogForAuction;
	}

	public int getAuctionNumber() {
		return auctionNumber;
	}

	public void registerNewBid(Bid bid) {
		bids.add(bid);
	}

	public boolean dogHasBid(Auction auction) {
		if (bids.isEmpty()) {
			return false;
		}
		return true;
	}

	public Bid getHighestBid() {
		if (!bids.isEmpty()) {
			sortTheBids();
			return bids.get(0);
		}
		return null;
	}

	public Bid getSecondHighestBid() {
		if (bids.size() > 1) {
			sortTheBids();
			return bids.get(1);
		}
		return null;
	}

	public Bid getThirdHighestBid() {
		if (bids.size() > 2) {
			sortTheBids();
			return bids.get(2);
		}
		return null;
	}

	public int getLeastAllowedBid() {
		if (!bids.isEmpty()) {
			int allowedBid = getHighestBid().getBidAmount() + 1;
			return allowedBid;
		} else
			return 1;
	}

	public User getBiddersName() {
		for (Bid bid : bids) {
			return bid.getBidder();
		}
		return null;
	}

	public String printHighestBid() {
		sortTheBids();
		if (!bids.isEmpty()) {
			String printHighest = bids.get(0).getBidder().getName() + " " + bids.get(0).getBidAmount();
			return printHighest.toString();
		}
		return null;
	}

	public String printSecondHighestBid() {
		sortTheBids();
		if (bids.size() > 1) {
			String printSecondHighest = "," + bids.get(1).getBidder().getName() + " " + bids.get(1).getBidAmount();
			return printSecondHighest.toString();
		}
		return null;
	}

	public String printThirdHighestBid() {
		sortTheBids();
		if (bids.size() > 2) {

			String printThirdHighest = "," + bids.get(2).getBidder().getName() + " " + bids.get(2).getBidAmount();
			return printThirdHighest.toString();
		}
		return null;
	}

	public String printBids() {
		sortTheBids();
		if (!bids.isEmpty()) {
			for (int index = 0; index < bids.size(); index++) {
				System.out
						.println(bids.get(index).getBidder().getName() + " " + bids.get(index).getBidAmount() + " kr");
			}
		}
		return null;
	}

	public void removeBidFromUser(User user) {
		Bid bid = null;

		for (Bid currentBid : bids) {
			if (currentBid.getBidder().getName().equals(user.getName())) {
				bid = currentBid;
			}
		}
		if (bid != null) {
			bids.remove(bid);
		}
	}

	private void sortTheBids() {
		bids.sort((Bid firstBid, Bid secondBid) -> {
			if (firstBid.getBidAmount() > secondBid.getBidAmount())
				return -1;
			if (firstBid.getBidAmount() < secondBid.getBidAmount())
				return 1;
			else
				return 0;
		});
	}

}
