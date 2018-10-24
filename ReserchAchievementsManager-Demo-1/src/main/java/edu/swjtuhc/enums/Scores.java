package edu.swjtuhc.enums;

import java.util.ArrayList;

public class Scores {
	public static final int Tb_Level_1_Rank_1=0;
	public static final int Tb_Level_1_Rank_2=1;
	public static final int Tb_Level_1_Rank_3=2;
	public static final int Tb_Level_2_Rank_1=3;
	public static final int Tb_Level_2_Rank_2=4;
	public static final int Tb_Level_2_Rank_3=5;
	public static final int Tb_Level_3_Rank_1=6;
	public static final int Tb_Level_3_Rank_2=7;
	public static final int Tb_Level_3_Rank_3=8;
	public static final int Thesis_Level_1=9;
	public static final int Thesis_Level_2=10;
	public static final int Thesis_Level_3=11;
	public static final int Thesis_Level_4=12;
	public static final int Thesis_Level_5=13;
	public static final int Thesis_Level_6=14;
	public static final int Thesis_Level_7=15;
	public static final int Thesis_Level_8=16;
	public static final int P_Subject_1_Level_1_Rank_1=17;
	public static final int P_Subject_1_Level_1_Rank_2=18;
	public static final int P_Subject_1_Level_2_Rank_1=19;
	public static final int P_Subject_1_Level_2_Rank_2=20;
	public static final int P_Subject_1_Level_3_Rank_1=21;
	public static final int P_Subject_1_Level_3_Rank_2=22;
	public static final int P_Subject_1_Level_4_Rank_1=23;
	public static final int P_Subject_1_Level_4_Rank_2=24;
	public static final int P_Subject_2_Level_1_Rank_1=25;
	public static final int P_Subject_2_Level_1_Rank_2=26;
	public static final int P_Subject_2_Level_2_Rank_1=27;
	public static final int P_Subject_2_Level_2_Rank_2=28;
	public static final int P_Subject_2_Level_3_Rank_1=29;
	public static final int P_Subject_2_Level_3_Rank_2=30;
	public static final int P_Subject_2_Level_4_Rank_1=31;
	public static final int P_Subject_2_Level_4_Rank_2=32;
	public static final int R_P_Rank_1=33;
	public static final int R_P_Rank_2=34;
	public static final int L_Level_1_Rank_1=35;
	public static final int L_Level_1_Rank_2=36;
	public static final int L_Level_2_Rank_1=37;
	public static final int L_Level_2_Rank_2=38;
	public static final int L_Level_3_Rank_1=39;
	public static final int L_Level_3_Rank_2=40;
	public static final int Pa_Type_1_Rank_1=41;
	public static final int Pa_Type_1_Rank_2=42;
	public static final int Pa_Type_1_Rank_3=43;
	public static final int Pa_Type_2_Rank_1=44;
	public static final int Pa_Type_3_Rank_1=45;
	public static final int Pa_Type_4_Rank_1=46;
	public static final int Pa_Type_4_Rank_2=47;
	private static ArrayList<Float> ScoresList;
	public static void reset() {
		ScoresList=new ArrayList<>();
	}
	public static void addScore(Float score) {
		ScoresList.add(score);
	}
	public static void setScore(int i,Float score) {
		ScoresList.set(i, score);
	}
	public static Float getScore(int i) {
		return ScoresList.get(i);
	}
}
