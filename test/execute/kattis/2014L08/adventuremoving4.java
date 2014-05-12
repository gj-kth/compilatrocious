// Solution to https://kth.kattis.com/problems/adventuremoving4

class adventuremoving4 {
	public static void main(String[] args) {
		System.out.println(new AM4().run());
	}
}

class AM4 {
	int dist;
	int[]dp;
	
	int[] indataStationDist;
	int[] indataStationCost;
	
	int[] stationPrices;
	
	public int getCostDp(int stationIndex, int litresLeft) {
		return dp[stationIndex * 201 + litresLeft];
	}
	public int setCostDp(int stationIndex, int litresLeft, int cost) {
		dp[stationIndex * 201 + litresLeft] = cost;
		return 0;
	}
	
	public int run() {
		int aux;
		int i;
		int j;
		int res;
		int printIndex;
		aux = this.indata();
		
		dp = new int[(dist+1) * 201];
		stationPrices = new int[dist+1];
		
		i = 0;
		while(i<dist+1) {
			stationPrices[i] = 536870912; // (INF = 1 << 29)
			i = i + 1;
		}
		i = 0;
		while(i<indataStationDist.length) {
			if (indataStationCost[i] < stationPrices[indataStationDist[i]]) {
				stationPrices[indataStationDist[i]] = indataStationCost[i];
			} else {}
			i = i + 1;
		}
		i = 0;
		while(i<101) {
			aux = this.setCostDp(0, i, 0);
			i = i + 1;
		}
		while(i<201) {
			if (stationPrices[0] < 536870912) {
				aux = this.setCostDp(0, i, stationPrices[0] * (i - 100));
			} else {
				aux = this.setCostDp(0, i, 536870912);
			}
			i = i + 1;
		}
		printIndex = 0;
		i = 1;
		while(i<dist+1) {
			aux = this.setCostDp(i, 200, 536870912);
			j = 0;
			while(j<200) {
				aux = this.setCostDp(i, j, this.getCostDp(i-1, j+1));
				j = j + 1;
			}
			if (stationPrices[i] < 536870912) {
				j = 1;
				while(j<201) {
					if (this.getCostDp(i, j-1) + stationPrices[i] < this.getCostDp(i, j)) {
						aux = this.setCostDp(i, j, this.getCostDp(i, j-1) + stationPrices[i]);
					} else {}
					j = j + 1;
				}
			} else {}
			
			// Debug print:
			printIndex = printIndex + 1;
			if (!(printIndex < 17)) {
				j = 0;
				while(j<201) {
					System.out.println(this.getCostDp(i, j));
					j = j + 39;
				}
				printIndex = 0;
			} else {}
			
			i = i + 1;
		}
		if (this.getCostDp(dist, 100) < 536870912)
			res = this.getCostDp(dist, 100);
		else
			res = 0 - 1;
		return res;
		
	}
	
	public int indata() {
		dist = 500;
		indataStationDist = new int[7];
		indataStationCost = new int[7];
		indataStationDist[0] = 100; indataStationCost[0] = 999;
		indataStationDist[1] = 150; indataStationCost[1] = 888;
		indataStationDist[2] = 200; indataStationCost[2] = 777;
		indataStationDist[3] = 300; indataStationCost[3] = 999;
		indataStationDist[4] = 400; indataStationCost[4] = 1009;
		indataStationDist[5] = 450; indataStationCost[5] = 1019;
		indataStationDist[6] = 500; indataStationCost[6] = 1399;
		return 0;
	}
}
