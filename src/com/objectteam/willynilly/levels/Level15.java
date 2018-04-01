package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level15 implements Level{

	public String getName() {
		return "Jumpin' Jack Flash";
	}
	
	@Override
	public String[] getLevel() {
		// JUMPIN JACK FLASH
		final String[] level1 = new String[20];
		level1[19] = "                                      E                    E                         C                           E                                              ";
		level1[18] = "          ######################################################################   ##############################################################               ";
		level1[17] = "                      E                             C                      E                E                          C        E                               ";
		level1[16] = "          #########################################################   ###############################   #########################################               ";
		level1[15] = "                                  E                                                  C                       E                                                  ";
		level1[14] = "          #####################################   ##############################   ##############################################   #############               ";
		level1[13] = "                                                               E                                   E      C               C                                     ";
		level1[12] = "          #####################   ##################################################   ##########################################################               ";
		level1[11] = "                    E                                      CCCCCC         E                                                                                     ";
		level1[10] = "          ###########################################   ##########################################   #################   ########################               ";
		level1[9]  = "                                                E                                                             E              C                                  ";
		level1[8]  = "          ##########################################   ###################################   ####################################################               ";
		level1[7]  = "                                 E                           C                        E                                          E                              ";
		level1[6]  = "          ###########################################################################################   #########################################               ";
		level1[5]  = "                     C                     C                                  C                                  E                                              ";
		level1[4]  = "          ###################   ########################   ############################################   #####################   ###############               ";
		level1[3]  = "             C       E              C                        E                                E                             E                                   ";
		level1[2]  = "          ####################################   ################################   #############################   #############################               ";
		level1[1]  = "       S                                                                                                                                               F        ";
		level1[0]  = "1111111111                                                                                                                                       111111111111111";
		return level1;
	}

}