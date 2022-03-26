package telran.sport;

public class Footballer implements Sportman {
	String team;
	
	public Footballer(String team) {
		this.team = team;
	}

	@Override
	public void sportAction() {
		System.out.printf("I'm footballer in the team %s\n", team);

	}

}
