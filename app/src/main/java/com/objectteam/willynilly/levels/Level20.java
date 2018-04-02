package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level20 implements Level{

	public String getName() {
		return "What happened to level 20?";
	}
	
	@Override
	public String[] getLevel() {
		final String[] level1 = new String[20];
		level1[19] = "                                                                                                                                                                ";
		level1[18] = "                                                                                                                                                                ";
		level1[17] = "                                                                                                                                                                ";
		level1[16] = "                                                                                                                                                                ";
		level1[15] = "                                                                                                                                                                ";
		level1[14] = "                                                                                                                                                                ";
		level1[13] = "                                                                                                                                                                ";
		level1[12] = "                                                                                                                                                                ";
		level1[11] = "                                                                                                                                                                ";
		level1[10] = "                                                                                                                                                                ";
		level1[9]  = "                                                                                                                                                                ";
		level1[8]  = "                                                                                                                                                                ";
		level1[7]  = "                                                                                                                                                                ";
		level1[6]  = "                                                                                                                                                                ";
		level1[5]  = "                                                                                                                                                                ";
		level1[4]  = "                                                                                                                                                                ";
		level1[3]  = "                                                                                                                                                                ";
		level1[2]  = "                                                                                                                                                                ";
		level1[1]  = "       S                                                                                                                                               F        ";
		level1[0]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		return level1;
	}

}
