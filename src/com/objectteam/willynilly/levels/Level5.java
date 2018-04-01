package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level5 implements Level{

	public String getName() {
		return "The 39 steps";
	}
	
	@Override
	public String[] getLevel() {
		// steps of 8 are boring
		final String[] level = new String[32];
		level[31] = "                                                                                                                                                                                                                     F                                                                ";
		level[30] = "                                                                                                                                                                                                                  #######                                                           ";
		level[29] = "                                                                                                                                                                                                           #######                                                           ";
		level[28] = "                                                                                                                                                                                                    #######                                                           ";
		level[27] = "                                                                                                                                                                                             #######                                                           ";
		level[26] = "                                                                                                                                                                                      #######                                                           ";
		level[25] = "                                                                                                                                                                               #######                                                           ";
		level[24] = "                                                                                                                                                                        #######                                                           ";
		level[23] = "                                                                                                                                                                 #######                                                           ";
		level[22] = "                                                                                                                                                          #######                                                           ";
		level[21] = "                                                                                                                                                   #######                                                           ";
		level[20] = "                                                                                                                                            #######                                                           ";
		level[19] = "                                                                                                                                     #######                                                           ";
		level[18] = "                                                                                                                              #######                                                           ";
		level[17] = "       B       B         B         B          B             B               B             B          B                 #######                                                           ";
		level[16] = "                                                                                                                #######                                                           ";
		level[15] = "                                                                                                         #######                                                           ";
		level[14] = "                                                                                                  #######                                                           ";
		level[13] = "                                                                                           #######                                                           ";
		level[12] = "                                                                                    #######                                                           ";
		level[11] = "                                                                             #######                                                           ";
		level[10] = "                                                                     ########                                                           ";
		level[9] = "                                                               #######                                                           ";
		level[8] = "                                                        #######     #                                                     ";
		level[7] = "                                                 #######          #                                                ";
		level[6] = "                                          #######               #                                           ";
		level[5] = "                                   #######  #                 #                                      ";
		level[4] = "                            #######       #                 #                                 ";
		level[3] = "                     #######            #                 #                            ";
		level[2] = "              #######  #              #                                         ";
		level[1] = "       #######       #                                                   ";
		level[0] = "#######                                                           ";
		return level;
	}


}
