// EXT:CEQ

/// Test case for execute - implements a vector and does some small checks.
///
/// @version 31-03-2014

class ClockTest{

	public static void main(String[] args){
		boolean temp;
		Clock clock;
		clock = new Clock();
		temp = clock.init(1000, 45000);
		temp = clock.tick();
		
		System.out.println(clock.getTime()); // 45000
	}
}

class Clock{
	int currTime;
	int finishTime;

	/**
	 * Initializes the clock.
	 */
	public boolean init(int startTime, int endTime){
		currTime = startTime;
		finishTime = endTime;
		return true;
	}
	
	/**
	 * Increments clock by 1 until finishTime has been reached.
	 */
	public boolean tick(){
		boolean temp;
		while(currTime < finishTime){
			temp = this.simulateWait();
			currTime = currTime + 1;
		}
		return true;
	}
	
	/**
	 * Returns currTime.
	 */
	public int getTime(){
		return currTime;
	}
	
	/**
	 * Bogus method designed to do some work (so that tick is not executed too quickly).
	 */
	public boolean simulateWait(){
		int temp;
		temp = this.work(100);
		return true;
	}
	
	/**
	 * Do some work.
	 */
	public int work(int i){
		int res;
		if(i == 0){
			res = 1;
		}
		else{
			res = i*this.work(i-1);
		}
		return res;
	}
}