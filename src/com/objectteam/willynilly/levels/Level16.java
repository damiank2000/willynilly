package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level16 implements Level{

	public String getName() {
		return "Batty Hole";
	}
	
	@Override
	public String[] getLevel() {
		final String[] level1 = new String[5];
		level1[4]  = "3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333";
		level1[3]  = "            B                  B                B            B              B                   B               B             B                                 ";
		level1[2]  = "                                                                                                                                             B CCCCCCC          ";
		level1[1]  = "!      S             B                  B               B            B               B                  B              B              B        CCCCCCC F        ";
		level1[0]  = "3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333";
		return level1;
	}

}
