package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level10 implements Level{

	public String getName() {
		return "Echidna rise";
	}
	
	@Override
	public String[] getLevel() {
		// ECHIDNA HILL
		final String[] level1 = new String[20];
		level1[19] = "                                                                                                                                                                ";
		level1[18] = "                                                                                                                                                                ";
		level1[17] = "                                                                              CC                                                                                ";
		level1[16] = "                                                                              CC                                                                                ";
		level1[15] = "                                                                     ####                                                                                       ";
		level1[14] = "                                                                C                                                                                               ";
		level1[13] = "                                                               ####                                                                                             ";
		level1[12] = "                                                            E                                                                                                   ";
		level1[11] = "                                                         ############                                                                                           ";
		level1[10] = "                                                   E                                                                                                            ";
		level1[9]  = "                                           C     ############                                                                                                   ";
		level1[8]  = "                                         ####                                                                                                                   ";
		level1[7]  = "                                    E                                                                                                                           ";
		level1[6]  = "                                 #########                                                                                                                      ";
		level1[5]  = "                             E                                                                                                                                  ";
		level1[4]  = "                        ###############                                                                                                                         ";
		level1[3]  = "                                                                                                                                                                ";
		level1[2]  = "                #####                                                                                                                         CCCC              ";
		level1[1]  = "       S                                                                           E         E   C     E      C      E         C      E       CCCC     F        ";
		level1[0]  = "111111111111111111111                                                         1111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		return level1;
	}

}
