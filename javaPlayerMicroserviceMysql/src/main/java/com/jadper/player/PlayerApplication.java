package com.jadper.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.jadper.player.repository.PlayerRepository;
import com.jadper.player.model.Player;

@SpringBootApplication
public class PlayerApplication  implements CommandLineRunner {
	private Player p;
	private String playerName;
	private int playerJersyNo;
	private int playerAge;
	private int playerId;
	private String playerBorn;

	@Autowired
	DataSource dataSource;

	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		int mainMenuChoice;
		do {
			System.out.println("");
			System.out.println("=== Huvudmenu ========");
			System.out.println("1. Add a new player");
			System.out.println("2. Uppdate a player");
			System.out.println("3. Delete a player");
			System.out.println("4. List all players");
			System.out.println("100. Quit");
			System.out.println("======================");
			System.out.print("Choice (1): ");
			try {
				mainMenuChoice = Integer.parseInt(System.console().readLine());
			} catch (Exception e) {
				mainMenuChoice = 1;
			}

			switch (mainMenuChoice) {
				case 1:
					System.out.println("\n=== Add new player ===");
					addPlayer();
					break;
				case 2:
					System.out.println("\n=== Uppdate Player ===");
					updatePlayer();
					break;
				case 3:						
					System.out.println("=== Delete Player ====");
					deletePlayer();
					break;
				case 4:
					System.out.println("\n=== List all players =");
					listAllPlayers();
					break;
				case 100:
					System.out.println("\n======================");
					System.out.println("Ok, bye");
					break;
				default:
					System.out.println("well, how did you end up here?");
					break;
				}
			} while (mainMenuChoice != 100);
			}

	private void listAllPlayers() {
		Iterable<Player> playerlist = playerRepository.findAll();
		System.out.println("No of Players: " + playerRepository.count());
		for(Player p:playerlist){
			printPlayer(p);
		}
		System.out.println("");
	}

	private void printPlayer(Player p) {
		System.out.println("---------------------------------");
  		System.out.println("Player ID: "+p.getId());			  
  		System.out.println("Player name: " + p.getName());
  		System.out.println("Player Jersy No: "+ p.getJersey() );
  		System.out.println("Player Age: "+ p.getAge() );
  		System.out.println("Player Born: "+ p.getBorn() );
	}

	private void deletePlayer() {
		System.out.print("Player ID: ");
		playerId = Integer.parseInt(System.console().readLine());
		if(playerRepository.existsById(playerId))		{
			playerRepository.deleteById(playerId);
		} else {
			System.out.println("[!] Player ID do not exist");
		}
	}

	private void updatePlayer() {
		//System.out.println("\n=== Uppdate Player ===");
		System.out.print("Player ID: ");
		playerId = Integer.parseInt(System.console().readLine());
		   // why optional?
		if (playerRepository.existsById(playerId)) {
			p = playerRepository.findById(playerId).get();	
			System.out.print("Player name ("+p.getName()+"):");
			playerName = System.console().readLine();
			System.out.print("Jersy number ("+p.getJersey()+"):");
			playerJersyNo = Integer.parseInt(System.console().readLine());
			System.out.print("Player age ("+p.getAge()+"):");
			playerAge = Integer.parseInt(System.console().readLine());
			System.out.print("Player born ("+p.getBorn()+"):");
			playerBorn = System.console().readLine();
			p.setName(playerName);
			p.setJersey(playerJersyNo);
			p.setAge(playerAge);
			p.setBorn(playerBorn);
		
			playerRepository.save(p);
			p = playerRepository.findById(playerId).get();
			printPlayer(p);  	

		} else {
			System.out.println("[!] No user by that id");
		}
	}

	private void addPlayer() {
		System.out.print("Player name: ");
		playerName = System.console().readLine();
		System.out.print("Jersy number: ");
		playerJersyNo = Integer.parseInt(System.console().readLine());
		System.out.print("Player age: ");
		playerAge = Integer.parseInt(System.console().readLine());
		System.out.print("Player born: ");
		playerBorn = System.console().readLine();

		p = new Player();
		p.setName(playerName);
		p.setJersey(playerJersyNo);
		p.setAge(playerAge);
		p.setBorn(playerBorn);
		playerRepository.save(p);
	}
}
