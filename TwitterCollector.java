/**
 * A program to download the streaming tweets given keywords or user ids.
 */

import twitter4j.*;

public class TwitterCollector {
    public static void main(String[] args) throws TwitterException {
	String[] tracks = new String[] {"google"};

	StatusListener listener = new StatusListener() {
		@Override
		public void onStatus(Status status) {
		    System.out.println(status.getCreatedAt().toString() + " : " + status.getUser().getScreenName());
		    System.out.println(status.getText());
		}
		
		@Override
		public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		    System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
		}
		
		@Override
		public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		    System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
		}
		
		@Override
		public void onScrubGeo(long userId, long upToStatusId) {
		    System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
		}
		
		@Override
		public void onStallWarning(StallWarning warning) {
		    System.out.println("Got stall warning:" + warning);
		}
		
		@Override
		public void onException(Exception ex) {
		    ex.printStackTrace();
		}
	    };
	
	TwitterStream ts = new TwitterStreamFactory().getInstance();
	ts.addListener(listener);
	FilterQuery fq = new FilterQuery();
	fq = fq.filterLevel("low");
	fq = fq.track(tracks);
	fq = fq.language("en");
	//fq = fq.follow(new long[] {});
	ts.filter(fq);
    }
}
