package edu.swjtuhc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.swjtuhc.enums.Scores;
import edu.swjtuhc.model.Laws;
import edu.swjtuhc.model.Patent;
import edu.swjtuhc.model.Project;
import edu.swjtuhc.model.ReformProject;
import edu.swjtuhc.model.Textbook;
import edu.swjtuhc.model.Thesis;

public class ModelUtil {
	public static Object updateModelByModel(Object origin, Object newModel) {
		if (!origin.getClass().equals(newModel.getClass())) {
			System.err.println("model type not match!");
			return origin;
		}
		Field[] newFields = newModel.getClass().getDeclaredFields();
		for (int i = 0; i < newFields.length; i++) {
			Field f = newFields[i];
			f.setAccessible(true);
			try {
				if (f.get(newModel) != null) {
					f.set(origin, f.get(newModel));
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return origin;
	}

	public static String appendPath(String path1, String path2) {
		if (path1 == null) {
			return path2;
		}
		return path1 + "|" + path2;
	}

	public static String deletePath(String path1, String path2) {
		if (path1 == null) {
			return null;
		}
		String[] paths = path1.split("\\|");
		if (paths.length == 0) {
			return path1;
		}
		List<String> list = new ArrayList<>(Arrays.asList(paths));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(path2)) {
				list.remove(i);
				break;
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return String.join("|", list);
	}

	public static Map<String,Float> caculateScore(Object o) {
		Map<String,Float> result = new HashMap<>();
		Float maxScore =0.0f;
		Float score = 0.0f;
		switch (o.getClass().getSimpleName()) {
		case "Thesis":
			Thesis t = (Thesis) o;
			Integer tLevel = t.getJournalLevel();
			maxScore=500f;
			switch (tLevel) {
			case 1:
				score = Scores.getScore(Scores.Thesis_Level_1);
				break;
			case 2:
				score = Scores.getScore(Scores.Thesis_Level_2);
			case 3:
				score = Scores.getScore(Scores.Thesis_Level_3);
			case 4:
				score = Scores.getScore(Scores.Thesis_Level_4);
			case 5:
				score = Scores.getScore(Scores.Thesis_Level_5);
			case 6:
				score = Scores.getScore(Scores.Thesis_Level_6);
			case 7:
				score = Scores.getScore(Scores.Thesis_Level_7);
			case 8:
				score = Scores.getScore(Scores.Thesis_Level_8);
			default:
				break;
			}
			break;
		case "TextBook":
			Textbook tb = (Textbook) o;
			Integer tbLevel = tb.getLevel();
			Integer tbRank = tb.getInvolvement();
			if (tbLevel == 1) {
				maxScore=45f;
				if (tbRank == 1) {
					score = Scores.getScore(Scores.Tb_Level_1_Rank_1);
				} else if (tbRank == 2) {
					score = Scores.getScore(Scores.Tb_Level_1_Rank_2);
				} else if (tbRank == 3) {
					score = Scores.getScore(Scores.Tb_Level_1_Rank_3);
				}
			} else if (tbLevel == 2) {
				maxScore=15f;
				if (tbRank == 1) {
					score = Scores.getScore(Scores.Tb_Level_2_Rank_1);
				} else if (tbRank == 2) {
					score = Scores.getScore(Scores.Tb_Level_2_Rank_2);
				} else if (tbRank == 3) {
					score = Scores.getScore(Scores.Tb_Level_2_Rank_3);
				}
			} else if (tbLevel == 3) {
				maxScore=5f;
				if (tbRank == 1) {
					score = Scores.getScore(Scores.Tb_Level_3_Rank_1);
				} else if (tbRank == 2) {
					score = Scores.getScore(Scores.Tb_Level_3_Rank_2);
				} else if (tbRank == 3) {
					score = Scores.getScore(Scores.Tb_Level_3_Rank_3);
				}
			}
			break;
		case "Patent":
			Patent pa = (Patent) o;
			Integer paRank = pa.getAuthorRank();
			Integer paType = pa.getType();
			if (paType == 1) {
				maxScore=24f;
				if (paRank == 1) {
					score = Scores.getScore(Scores.Pa_Type_1_Rank_1);
				} else if (paRank == 2) {
					score = Scores.getScore(Scores.Pa_Type_1_Rank_2);
				} else if (paRank == 3) {
					score = Scores.getScore(Scores.Pa_Type_1_Rank_3);
				}
			} else if (paType == 2) {
				maxScore=15f;
				if (paRank == 1) {
					score = Scores.getScore(Scores.Pa_Type_2_Rank_1);
				}
			} else if (paType == 3) {
				maxScore=12f;
				if (paRank == 1) {
					score = Scores.getScore(Scores.Pa_Type_3_Rank_1);
				}
			} else if (paType == 4) {
				maxScore=12f;
				if (paRank == 1) {
					score = Scores.getScore(Scores.Pa_Type_4_Rank_1);
				} else if (paRank == 2) {
					score = Scores.getScore(Scores.Pa_Type_4_Rank_2);
				}
			}
			break;
		case "Project":
			Project p = (Project) o;
			Integer pRank = p.getInvolvement();
			Integer pSubject = p.getSubject();
			Integer pLevel = p.getLevel();  
			if (pSubject == 1) {
				if (pLevel == 1) {
					maxScore=300f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_1_Level_1_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_1_Level_1_Rank_2);
					}
				} else if (pLevel == 2) {
					maxScore=90f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_1_Level_2_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_1_Level_2_Rank_2);
					}
				} else if (pLevel == 3) {
					maxScore=30f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_1_Level_3_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_1_Level_3_Rank_2);
					}
				} else if (pLevel == 4) {
					maxScore=20f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_1_Level_4_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_1_Level_4_Rank_2);
					}
				}
			} else if (pSubject == 2) {
				if (pLevel == 1){
					maxScore=100f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_2_Level_1_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_2_Level_1_Rank_2);
					}
				} else if (pLevel == 2) {
					maxScore=30f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_2_Level_2_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_2_Level_2_Rank_2);
					}
				} else if (pLevel == 3) {
					maxScore=15f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_2_Level_3_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_2_Level_3_Rank_2);
					}
				} else if (pLevel == 4) {
					maxScore=10f;
					if (pRank == 1) {
						score = Scores.getScore(Scores.P_Subject_2_Level_4_Rank_1);
					} else if (pRank == 2) {
						score = Scores.getScore(Scores.P_Subject_2_Level_4_Rank_2);
					}
				}
			}
			break;
		case "ReformProject":
			ReformProject rp = (ReformProject) o;
			Integer rpRank = rp.getInvolvement();
			maxScore=24f;
			if (rpRank == 1) {
				score = Scores.getScore(Scores.R_P_Rank_1);
			} else if (rpRank == 2) {
				score = Scores.getScore(Scores.R_P_Rank_2);
			}
			break;
		case "Laws":
			Laws l = (Laws) o;
			Integer lLevel = l.getLevel();
			Integer lRank = l.getInvolvement();
			if (lLevel == 1) {
				maxScore=300f;
				if (lRank == 1) {
					score = Scores.getScore(Scores.L_Level_1_Rank_1);
				} else if (lRank == 2) {
					score = Scores.getScore(Scores.L_Level_1_Rank_2);
				} 
			} else if (lLevel == 2) {
				maxScore=90f;
				if (lRank == 1) {
					score = Scores.getScore(Scores.L_Level_2_Rank_1);
				} else if (lRank == 2) {
					score = Scores.getScore(Scores.L_Level_2_Rank_2);
				} 
			} else if (lLevel == 3) {
				maxScore=45f;
				if (lRank == 1) {
					score = Scores.getScore(Scores.L_Level_3_Rank_1);
				} else if (lRank == 2) {
					score = Scores.getScore(Scores.L_Level_3_Rank_2);
				} 
			}
			break;
		default:
			break;
		}
		result.put("score", score);
		result.put("maxScore", maxScore);
		return result;
	}

	public static void main(String[] args) {
		Thesis t = new Thesis();
		t.setJournalLevel(1);
		System.out.println(ModelUtil.caculateScore(t));
	}
}
