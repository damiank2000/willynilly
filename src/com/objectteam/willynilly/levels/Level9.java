package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level9 implements Level{

	public String getName() {
		return "Why did the echidna cross the road?";
	}
	
	@Override
	public String[] getLevel() {
		// ECHIDNA ROAD
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
		level1[7]  = "                                                                                                                                               CCCCC            ";
		level1[6]  = "                                                                                                                                               CCCCC            ";
		level1[5]  = "                                                                                                                                                                ";
		level1[4]  = "                                                                                                                                    #####                       ";
		level1[3]  = "                                                                                                                           #####                                ";
		level1[2]  = "              CCCCCC       CCCCCC       CCCCCC         CCCCCC         CCCCCC          CCCCCC           CCCCCC      #####                                        ";
		level1[1]  = "       S         E             E             E          E         E          E          E           E                                                  F        ";
		level1[0]  = "3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333";
		return level1;
	}

}
