package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level3 implements Level{

	public String getName() {
		return "Big jumper!";
	}
	
	@Override
	public String[] getLevel() {
		// bigger gaps
		// can jump over gap of 7 horizontally
		// gap of 6 up 1
		// gap of 5 up 2
		// step 3 long, gap 3 long, step 3 long is a comfortable climb
		final String[] level1 = new String[20];
		level1[19] = "                                                                                                                           5                                    ";
		level1[18] = "                                                                                                                                                                ";
		level1[17] = "                                                                                                                                                                ";
		level1[16] = "                                                                                                                                                                ";
		level1[15] = "                                                                                                                                                                ";
		level1[14] = "                                                                                                                                                                ";
		level1[13] = "                                                                                                                                                                ";
		level1[12] = "                                                                                                                                                                ";
		level1[11] = "                                                                                                                                                                ";
		level1[10] = "                                                                                                                                                                ";
		level1[9]  = "                                                                                                      C                                                         ";
		level1[8]  = "                                                                                                     111   B                                                    ";
		level1[7]  = "                                                                                              C                  C                                              ";
		level1[6]  = "                                                                                             111                111    B                                        ";
		level1[5]  = "                                                                                      C                                      C                                  ";
		level1[4]  = "                                                                                     111                                    111    B    C                       ";
		level1[3]  = "                                                                              C                                                        111                      ";
		level1[2]  = "                                               C         C          C        111                                                                                ";
		level1[1]  = "       S          C         C         C       111       111       11111                                                                                F        ";
		level1[0]  = "1111111111       111       111       111                                                                                                         111111111111111";
		return level1;
	}

}
