package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level11 implements Level{

	public String getName() {
		return "Big rock";
	}
	
	@Override
	public String[] getLevel() {
		// BIG ROCK
		// can jump over gap of 7 horizontally
		// gap of 6 up 1
		// gap of 5 up 2
		// step 3 long, gap 3 long, step 3 long is a comfortable climb
		final String[] level = new String[20];
		level[19] = "                                                                    333                                                                                         ";
		level[18] = "                                                                                                                       3                                        ";
		level[17] = "                                                              333        C                                             3                                        ";
		level[16] = "                                                                                                                       3                                        ";
		level[15] = "                                                        333                                                            3                                        ";
		level[14] = "                                                          3     C           C                                         3                                         ";
		level[13] = "                                                  333 3   3                                                          3                                          ";
		level[12] = "                                                    C     3                                                         3                                           ";
		level[11] = "                                            333     3333333    3  C           C                                    3                                            ";
		level[10] = "                                                   33                                                             3                                             ";
		level[9]  = "                                      333         3 3                                                            3                                              ";
		level[8]  = "                                                 3  3               C           C                               3             33333                             ";
		level[7]  = "                                333             3   3                                                          3                            CC                  ";
		level[6]  = "                                              C                 3                                                       CCCCC            C      C               ";
		level[5]  = "                          333                     33                  C          C                           3          33333          C                        ";
		level[4]  = "                                        33333     33                                                        3    CCCC                C             C            ";
		level[3]  = "                    333       33333      3333      33               3                                      3     3333       33333                               ";
		level[2]  = "                        3      3333       33       3                    C         C                       3                                         C           ";
		level[1]  = "              333   33333       333       33       3                                                     3                      333333                F         ";
		level[0]  = "     333333333       3333       333       33       3       33333333       3333333333333333333333333333333      333      333            3333333331111111111111111";
		return level;
	}

}
