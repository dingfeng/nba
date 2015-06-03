package live;

public class TextRecord {
	 private  String time;
	 private  String team;
	 private  String event;
	 private  String pointTopoint;
    public TextRecord(String time, String team, String event,
			String pointTopoint)
    {
		super();
		this.time = time;
		this.team = team;
		this.event = event;
		this.pointTopoint = pointTopoint;
	}
	public String getTime() {
		return time;
	}
	public String getTeam() {
		return team;
	}
	public String getEvent() {
		return event;
	}
	public String getPointTopoint() {
		return pointTopoint;
	}
	
}
