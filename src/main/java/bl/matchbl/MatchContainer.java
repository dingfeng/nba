package bl.matchbl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class MatchContainer {
	private TIntObjectMap<Match>  match_map;
	private static  MatchContainer  container;
	private MatchContainer()
	{
		match_map =  new TIntObjectHashMap<Match>();
	}
	
	public static MatchContainer instance()
	{
		if (container == null)
		{
			container = new MatchContainer();
		}
		return container;
	}
	
    public  Match getSeasonMatch(int season)
    {
    	if (match_map.containsKey(season))
    	{
    		return match_map.get(season);
    	}
    	Match match;
    	try {
		match = new Match(season);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	match_map.put(season, match);
       return match;    	
    }
}
