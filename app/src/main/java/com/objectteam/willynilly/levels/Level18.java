package com.objectteam.willynilly.levels;

import com.objectteam.willynilly.Level;

public class Level18 implements Level{

	public String getName() {
		return "Wafty migration";
	}
	
	@Override
	public String[] getLevel() {
		// WAFTY MIGRATION
		final String[] level1 = new String[20];
		level1[19] = "!          W                                 W                                 W                                 W                                 W            ";
		level1[18] = "            W                               W W                               W W                               W W                               W             ";
		level1[17] = "             W                             W   W                             W   W                             W   W                             W              ";
		level1[16] = "              W                           W     W                           W     W                           W     W                           W               ";
		level1[15] = "               W                         W       W                         W       W                         W       W                         W                ";
		level1[14] = "                W                       W         W                       W         W                       W         W                       W                 ";
		level1[13] = "                 W                     W           W                     W           W                     W           W                     W                  ";
		level1[12] = "                  W                   W             W             CCC   W   CCC       W                   W             W                   W                   ";
		level1[11] = "                   W                 W               W            ###  W    ###        W  CCC            W               W                 W                    ";
		level1[10] = "                    W               W                 W    CCC        W    CCC          W ###           W                 W               W                     ";
		level1[9]  = "                     W             W                   W   ###       W     ###      CCC  W             W                   W             W                      ";
		level1[8]  = "                      W           W                  CCCW           W          CCC  ###   W  CCC      W       CCC           W           W                       ";
		level1[7]  = "                       W         W             CCC   ### W         W           ###         W ###     W  CCC   ###            W         W                        ";
		level1[6]  = "                        W       W              ###        W  CCC  W    CCC                  W   CCC W   ###      CCC          W       W                         ";
		level1[5]  = "                         W     W        CCC                W ### W     ###              CCC  W  ###W             ###           W CCC W                          ";
		level1[4]  = "                          W   W         ###                 W   W                 CCC   ###   W   W CCC             CCC         W###W                           ";
		level1[3]  = "                           W W    CCC         CCC      CCC   W W CCC       CCC    ###          W W  ###    CCC      ###  CCC  ###W W   CCC                      ";
		level1[2]  = "            CCC      CCC    W     ###         ###      ###    W  ###       ###                  W          ###           ###      W    ###                      ";
		level1[1]  = "       S                                                                                                                                               F        ";
		level1[0]  = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		return level1;
	}

}
