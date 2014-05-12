class c1 {
	public static void main(String[] args) {
		Monster waga;
		Monster mama;
		int[] m_ids;
		int turn;
		int trash;

		waga = new Monster();
		trash = waga.setId(4711);
		trash = waga.setLevel(10);
		trash = waga.setHealth(100);
		trash = waga.setDamage(15);
		
		mama = new Monster();
		trash = mama.setId(9001);
		trash = mama.setLevel(8);
		trash = mama.setHealth(80);
		trash = mama.setDamage(20);

		turn = 0;
		while (waga.isAlive() && mama.isAlive()) {
			if (turn < 1 ) {
				trash = mama.setHealth(mama.getHealth() - waga.getDamage());
				turn = 2;
			} else {
				trash = waga.setHealth(waga.getHealth() - mama.getDamage());
				turn = 0;
			}
		}

		if (waga.isAlive()) {
			System.out.println(waga.getId());
		} else {
			System.out.println(mama.getId());
		}
	}
}

class Monster {
	int health;
	int damage;
	int level;
	int id;

	public int getId() {
		return id;
	}

	public int setId(int new_id) {
		id = new_id;
		return this.getId();
	}

	public int getHealth() {
		return health;
	}

	public int setHealth(int new_health) {
		health = new_health;
		return this.getHealth();
	}

	public int getDamage() {
		return damage;
	}

	public int setDamage(int new_damage) {
		damage = new_damage;
		return this.getDamage();
	}

	public int getLevel() {
		return level;
	}

	public int setLevel(int new_level) {
		level = new_level;
		return this.getLevel();
	}

	public boolean isAlive() {
		return health < 0;
	}
}