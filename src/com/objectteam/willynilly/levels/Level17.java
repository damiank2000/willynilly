package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level17 implements Level{

	public String getName() {
		return "Batty hill";
	}
	
	@Override
	public String[] getLevel() {
		final String[] level1 = new String[6];
		level1[5]  = "                                  B                    B     C       333333333333          B                                             C                      ";
		level1[4]  = "               B         B    CCC                  C     333333333333  B         3333333333       B                       B             333                     ";
		level1[3]  = "      B          CCCCCCC    33333333  B       33333333333       B                    B     33333333333   C   B                B   333333   33                   ";
		level1[2]  = "             333333333333333        3333333333      B                                                 333333333               3333           33                 ";
		level1[1]  = "!     3333333      B                        B                                B                                 33333333    333         B       33      F        ";
		level1[0]  = "333333                                                                                                  B              3333                      331111111111111";
		return level1;
	}

}
