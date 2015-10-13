package practice;

import java.util.Scanner;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.api.RateLimit;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;

public class Main {

	public static void main(String[] args) throws APIException {
		Summoner summoner = null;
		Scanner keyboard = new Scanner(System.in);
		String summonerName;
		RiotAPI.setMirror(Region.NA);
		RiotAPI.setRegion(Region.NA);
		RiotAPI.setRateLimit(100, 10);
		RiotAPI.setRateLimit(new RateLimit(100, 10), new RateLimit(5000, 600));
		RiotAPI.setAPIKey("c71122fa-dde2-4924-acfd-e91cc6014766");

		System.out.println("What is your Summoner Name?");
		summonerName = keyboard.nextLine();
		keyboard.close();

		try {
			summoner = RiotAPI.getSummonerByName(summonerName);
		} catch (Exception e) {
			System.out.println("##Invalid Name");
			System.exit(0);
		}
		System.out.println(summoner.getName() + " is a level " + summoner.getLevel());
		try {
			System.out.println(summoner.getTeams());
		} catch (Exception e) {
			System.out.println(summoner.getName() + " is not on a team");
		}
		try {
			System.out.println(summoner.getRunePages());
		} catch (Exception e) {
			System.out.println(summoner.getName() + " doesn't have any rune pages.");
		}
		try {
			System.out.println(summoner.getMasteryPages());
		} catch (Exception e) {
			System.out.println(summoner.getName() + " doesn't have any master pages.");
		}
	}
}