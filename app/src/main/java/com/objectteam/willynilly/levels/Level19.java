package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level19 implements Level{

	public String getName() {
		return "Almost there!";
	}
	
	@Override
	public String[] getLevel() {
		final String[] level1 = new String[20];
		level1[19] = "                                                                                                                                                                ";
		level1[18] = "                                                                                                                                                                ";
		level1[17] = "                                                                                                                                 B                              ";
		level1[16] = "                                                                                                             W                                                  ";
		level1[15] = "                                                         W                                                                                                      ";
		level1[14] = "              B                                     B                                                                                                           ";
		level1[13] = "                                                                                               W                                                                ";
		level1[12] = "                                                                                                                                                                ";
		level1[11] = "                                                                                                                                                                ";
		level1[10] = "                                                           B                    B                             B                                                 ";
		level1[9]  = "                                                                                                                                                                ";
		level1[8]  = "                               W                                                                                                                                ";
		level1[7]  = "                  W                                                                                                   C        C                                ";
		level1[6]  = "                                                                             C                                       111      111                               ";
		level1[5]  = "                                                                            111           W                 111                           B                     ";
		level1[4]  = "                                                          W                                          111                                                        ";
		level1[3]  = "                                        C         E                 111              111                                                111                     ";
		level1[2]  = "                                C      111       111111111 111                                111                                                               ";
		level1[1]  = "!      S                       111                                                                                                             111     F        ";
		level1[0]  = "11111111      111      111                                                                                                                           11111111111";
		return level1;
	}

}
