package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level4 implements Level{

	public String getName() {
		return "You take the high road...";
	}
	
	@Override
	public String[] getLevel() {
		// you take the high road
		// at current speed, this level takes about 30s to play through
		final String[] level = new String[20];
		level[19] = "                                                                                                                                                     1          ";
		level[18] = "                                                                                                                          B                          1          ";
		level[17] = "                                                                                                     1                 B     B                       1          ";
		level[16] = "                                                                                     111             1              B           B                    1          ";
		level[15] = "                                                                                  111   1            1           B                 B                 1          ";
		level[14] = "                                                                               111       CCCCCCCCCCCC         B                                      1          ";
		level[13] = "                                                                            111         11111111111111     B                                         1          ";
		level[12] = "                                                                         111                                                                         1          ";
		level[11] = "                                                                      111       1111111111               1111                                        1          ";
		level[10] = "                                             E          S          111                                            11111           S                             ";
		level[9]  = "1111111111111111111111111111111111111111111111111111111111      11111111111111111111111111111111111111111111111111111111111111111111       111111111111111111111";
		level[8]  = "       1                         W                                                                                                    C              1          ";
		level[7]  = "       1                B                      CCCCC      1111              C   C    C   C      C      CCC                                         B 1          ";
		level[6]  = "       1                     CCCCC    1 1 1 1 11111111             B   C    1   1    1   1      1     11111        1 1   1   1  1        C           1          ";
		level[5]  = "                           1111111155                                  1                           3                                B      C         1          ";
		level[4]  = "                       111                              111111       5               1115                  11115                                     1          ";
		level[3]  = "             C     C                                            111                                                                           C      1          ";
		level[2]  = "            1111111111                                                                                                                 B         B   1          ";
		level[1]  = "       S              C                 C  234         1  1  1  1  1  1             1  1  1  1  1  S                  1  1  1  1           1 1     F            ";
		level[0]  = "111111111111111111111111111111111111111111111111111111                    111111111               1111111111111111111             11111111      1111111111111111";
		return level;
	}

}
