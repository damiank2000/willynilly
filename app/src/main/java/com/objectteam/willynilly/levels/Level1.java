package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level1 implements Level{

	public String getName() {
		return "Introduction";
	}
	
	@Override
	public String[] getLevel() {
		// introduction - jumping, coins, bats
		// at current speed, this level takes about 30s to play through
		final String[] level = new String[10];
		level[9]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		level[8]  = "       1                                                                                                                                             1          ";
		level[7]  = "       1                                                                                                                                             1          ";
		level[6]  = "       1              W                                                                                B                                             1          ";
		level[5]  = "                                                                                                                                    B                1          ";
		level[4]  = "                               B                                                                               B                                     1          ";
		level[3]  = "                C                                                                                             B                                      1          ";
		level[2]  = "            1111111111                      C                                                                B                         B  CCCC       1          ";
		level[1]  = "       S                  C          C     234                                    C                 C       B     C             C         CCCC     F            ";
		level[0]  = "111111111111111111111111111111111111111111111111111111 1 11111111111  111111  11111111   11111   1111111111B    11111 11  11   111111111111111111111111111111111";
		return level;
	}

}
