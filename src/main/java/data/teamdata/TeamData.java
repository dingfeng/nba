package data.teamdata;

import java.sql.Connection;

import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import dataservice.teamdataservice.TeamDataService;

public class TeamData implements TeamDataService{
    private Connection conn;
	public TeamData(Connection conn)
	{
		this.conn = conn;
	}
	@Override
	public TeamPO[] getAllTeamData() {
		return null;
	}

	@Override
	public TeamPO findTeam(String teamName) {
		return null;
	}
	@Override
	public TeamNormalPO getTeamNormalTotal(int season, String teama) {
		return null;
	}
	@Override
	public TeamNormalPO[] sortTeamNormalTotaln(int season, String sort, int n) {
		return null;
	}
	@Override
	public TeamNormalPO getTeamNormalAve(int season, String teama) {
		return null;
	}
	@Override
	public TeamNormalPO[] sortTeamNormalAven(int season, String sort, int n) {
		return null;
	}
	@Override
	public TeamHighPO getTeamHigh(int season, String teama) {
		return null;
	}
	@Override
	public TeamHighPO[] sortTeamHighn(String sort, int n) {
		return null;
	}
	@Override
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama) {
		return null;
	}
	@Override
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama) {
		return null;
	}
	@Override
	public TeamHighPO[] getTeamSeasonHigh(String teama) {
		return null;
	}

}
