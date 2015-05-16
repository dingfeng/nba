package data.teamdata;

import java.sql.Connection;

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

}
