package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level12 implements Level{

	public String getName() {
		return "Tree of death";
	}
	
	@Override
	public String[] getLevel() {
		// tree of DEATH
		// POSSIBLE!
		final String[] level1 = new String[20];
		level1[19] = "!                                         CCCCCCCCCCCC ###                                                                    W                                    ";
		level1[18] = "                                      ########################                                                                                                     ";
		level1[17] = "                              C   ####                 ###    ###  CCCCC                                                                                           ";
		level1[16] = "                           #######     W          ########     W #############                                                                 W                   ";
		level1[15] = "                     C  ###           B      #####     ###   CCC              ###  C                                                                               ";
		level1[14] = "                   #####               C  ###      CC  ###########               ######                                                       W                    ";
		level1[13] = "                  #   B         B     ####        ########        ########     B       ####                                                                        ";
		level1[12] = "                ##             C  ####       #####     ###  CCC           ###### B       B                                                                         ";
		level1[11] = "              ##          B  #####        ###       C  ###########         B    ####  CCCCC                                                             W          ";
		level1[10] = "           ###      B    ####       W  ###        #### ###        #####             #########                                                                      ";
		level1[9]  = "       ####                         ###        ###     ###  W          ######                                                                                      ";
		level1[8]  = "    ###      B   B                          ###        ###        B          #####  C   C  C                                                   W                   ";
		level1[7]  = "                                   B                 B ###      B      B          ############                                                                     ";
		level1[6]  = "                             B           B             ###  5555555555555555555555            #######                                                              ";
		level1[5]  = "                                                 B     ###  B                                                                                                      ";
		level1[4]  = "                        B          W                   ###                                                                                                         ";
		level1[3]  = "                                                       ###                                                                                                         ";
		level1[2]  = "                                                       ###                                                                                                         ";
		level1[1]  = "       S           E         E         E               ###           B           E                   E                              E         E           F        ";
		level1[0]  = "111111111111111111111111111111111111111111111111111111111111                     1111111111111111111111111111511151115111511151111111111111111111111111111111111111";
		return level1;
	}

}
