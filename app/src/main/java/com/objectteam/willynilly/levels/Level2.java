package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level2 implements Level{

	public String getName() {
		return "Slightly harder";
	}
	
	@Override
	public String[] getLevel() {
		// slightly harder
		// at current speed, this level takes about 30s to play through
		final String[] level = new String[10];
		level[9]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		level[8]  = "       1                                                                                                                                             1          ";
		level[7]  = "       1                                              C                                                            C                                 1          ";
		level[6]  = "       1              W                     C                                                          B    C               B                        1          ";
		level[5]  = "                               B   C    111111111                                                          111                      B                1          ";
		level[4]  = "                          C    111111111                                                              C                                              1          ";
		level[3]  = "                C     111111111                                                                      111                        B                   1          ";
		level[2]  = "            1111111111                                                                           C                                     B  CCCC       1          ";
		level[1]  = "       S                 234      234      234                                        C         111                                 C     CCCC     F            ";
		level[0]  = "11111111111111111111111111111111111111111111111111111111111111 11111  111111  11  111111111111                        111111111111111111111111111111111111111111";
		return level;
	}
}
