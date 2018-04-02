package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level8 implements Level{

	public String getName() {
		return "Birds and platforms";
	}
	
	@Override
	public String[] getLevel() {
		// BIRDS AND PLATFORMS
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
		level1[10] = "                                                                                                                                        CCCCCC                  ";
		level1[9]  = "                                                                                                                                        CCCCCC                 W";
		level1[8]  = "                                                                                                                                        ######    C             ";
		level1[7]  = "                                                                                                 C                           W                                  ";
		level1[6]  = "                                                                                               ######                          ######               C           ";
		level1[5]  = "                                                        C              W                C                                                                       ";
		level1[4]  = "                                                      ######                          ######                          ######                                    ";
		level1[3]  = "                        C         W           C                                C                                                                      C           ";
		level1[2]  = "                      ######                ######                           ######                            ######                                                 ";
		level1[1]  = "       S      W    C                     C                                                                                                               F        ";
		level1[0]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		return level1;
	}

}
