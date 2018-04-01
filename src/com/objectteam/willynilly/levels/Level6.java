package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level6 implements Level{

	public String getName() {
		return "And I'll take the low road";
	}
	
	@Override
	public String[] getLevel() {
		// you take the high road 2
		final String[] level1 = new String[20];
		level1[19] = "                                                                                                                                                     1          ";
		level1[18] = "                                                                                                                          B                          1          ";
		level1[17] = "                                                                                                     1                 B     B                       1          ";
		level1[16] = "                                                                                     111             1              B           B                    1          ";
		level1[15] = "                                                                                  111   1CCCCCCCCCCCC1           B                 B                 1          ";
		level1[14] = "                                                                               111      1CCCCCCCCCCCC1        B                                      1          ";
		level1[13] = "                                                                            111         11111111111111     B                                         1          ";
		level1[12] = "                                                                         111      E                                                                  1          ";
		level1[11] = "                                                                      111       2333333334               1111                                        1          ";
		level1[10] = "                                             E          S          111          TTTTTTTTTT                        11111    E      S          E     F            ";
		level1[9]  = "1111111111111111111111111111111111111111111111111111111111      11111111111111111111111111111111111111111111111111111111111111111111       111111111111111111111";
		level1[8]  = "       1                                                                                                                                             1          ";
		level1[7]  = "       1                B                      CCCCC      1111              C   C    C   C      C      CCC                                 CC      B 1          ";
		level1[6]  = "       1                     CCCCC    1     1 11111111             B   C    1   1    1   1      1     11111        1 1   1   1  1        CCCCCC      1          ";
		level1[5]  = "                           1111111155                                  1                           3                                B     CCCC       1          ";
		level1[4]  = "                       111                              111111       5               1115                  11115                            C        1          ";
		level1[3]  = "             C     C                                            111                                                                                  1          ";
		level1[2]  = "            2333333334                                                                                                                 B         B   1          ";
		level1[1]  = "       S    6777777778C   E     E       C              1  1  1  1  1  1    E        1  1  1  1  1  S    E             1  1  1  1           1 1       1          ";
		level1[0]  = "111111111111111111111111111111111111111111111111111111                    111111111               1111111111111111111             11111111      1111111111111111";
		return level1;
	}

}
