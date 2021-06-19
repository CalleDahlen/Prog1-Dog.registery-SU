//Skrivet av Carl Dahlén cada7128

package prog1Uppgiften;

public class Bid {
	private Auction auction;
	private User bidder;
	private int bidAmount;

	public Bid(User bidder, int bidAmount, Auction auction) {
		this.bidder = bidder;
		this.bidAmount = bidAmount;
		this.auction = auction;

	}

	public int getBidAmount() {
		return bidAmount;
	}

	public User getBidder() {
		return bidder;
	}

	public Auction getAuction() {
		return auction;
	}

}
