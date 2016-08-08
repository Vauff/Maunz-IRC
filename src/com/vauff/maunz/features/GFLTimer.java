package com.vauff.maunz.features;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Colors;

import com.vauff.maunz.core.Logger;
import com.vauff.maunz.core.Util;

public class GFLTimer
{
	private static String lastMap = "";

	public static Runnable timer = new Runnable()
	{
		public void run()
		{
			try
			{
				Document doc = Jsoup.connect("https://stats.gflclan.com/hlstats.php?game=csgoze").get();
				String html = doc.select("td[class=game-table-cell]").text();
				String[] mapSplit = html.split(" ");
				String map = "";

				for (String m : mapSplit)
				{
					if (m.contains("_"))
					{
						map = m;
						break;
					}
				}

				if (!lastMap.equals(map) && !map.equals("") && Util.isEnabled)
				{
					Util.msg(Util.privateChannel, "GFL Zombie Escape is now playing: " + Colors.MAGENTA + Colors.BOLD + map);
					lastMap = map;
				}
			}
			catch (Exception e)
			{
				Logger.log.error("", e);
			}
		}
	};
}