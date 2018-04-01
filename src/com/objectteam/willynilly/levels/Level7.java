package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level7 implements Level{

	public String getName() {
		return "Wafty birds!";
	}
	
	@Override
	public String[] getLevel() {
		// INTRODUCTION TO JUMPING WAFTYBIRDS
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
		level1[10] = "                                                                                                                                                  WWW           ";
		level1[9]  = "                                                                                                                                               W                ";
		level1[8]  = "                                                                                                                                            W     W             ";
		level1[7]  = "            W                                                                                                                             W    W                ";
		level1[6]  = "           W W                                                                                                                           W  W     W             ";
		level1[5]  = "          W W                                                                                                                             W    W                ";
		level1[4]  = "           W W                                                                                                                              W     W             ";
		level1[3]  = "            W                      W                  W                                                      WW                                W                ";
		level1[2]  = "                                                                                                                                             CCC  WWW           ";
		level1[1]  = "       S        W  C        C  W      C      W   C          C  W W     C           WCW        C         WW      C     W       W              CCC       F        ";
		level1[0]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		return level1;
	}

}
