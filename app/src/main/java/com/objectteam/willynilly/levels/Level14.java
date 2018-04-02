package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level14 implements Level{

	public String getName() {
		return "Don't forget to wave";
	}
	
	@Override
	public String[] getLevel() {
		// don't forget to wave
		// POSSIBLE!
		final String[] level1 = new String[20];
		level1[19] = "!                                                                                         5555                                                W                 ";
		level1[18] = "                                                                                    5555          W                                                             ";
		level1[17] = " 555555  C                                                           C        C                                                          W                      ";
		level1[16] = "                                                                                              55555     W                                                       ";
		level1[15] = "       55555  C                                                                55555                                                            W               ";
		level1[14] = "                                                    C                C        C                                                      W                          ";
		level1[13] = "            55555  C                  C                                               C            55555                                                        ";
		level1[12] = "                                                                C         55555                             W                                                   ";
		level1[11] = "                 55555  C                                                                    C                                                                  ";
		level1[10] = "                                                                                                        55555                            W                      ";
		level1[9]  = "                      55555                   CCC           C       555555                        C                                                             ";
		level1[8]  = "                              C              CCCCC     C                                                                                                        ";
		level1[7]  = "                            555555           CCCCC                                                 W         55555                                              ";
		level1[6]  = "                                              CCC              55555                                                                        W                   ";
		level1[5]  = "                                    C                                                                                                                           ";
		level1[4]  = "                                  55555                                                    W                      55555                                         ";
		level1[3]  = "                                                 C        55555                                          555555555                                              ";
		level1[2]  = "                                       5555                        W                             55555555              55555                                    ";
		level1[1]  = "                                                    555555                               55555555                                   55555       W      F        ";
		level1[0]  = "                                            555555555555555555555555555555555555555555555                                     55555      11111111111111111111111";
		return level1;
	}

}
