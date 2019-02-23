package com.electric.cet.mobile.android.pq.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import android.graphics.Point;
import android.util.Log;

import com.electric.cet.mobile.android.pq.model.DataTrend;
import com.electric.cet.mobile.android.pq.utils.DateUtil;

public class GraphicalUtils {
	/**
	 * 将数据转换成坐标
	 * 
	 * @param list
	 *            数据
	 * @param total_coordinate
	 *            控件的总的坐标
	 * @return
	 */
	public static ArrayList<Float> yDatas2Coordinate(ArrayList<Float> list,
			int total_coordinate) {
		ArrayList<Float> new_list = new ArrayList<Float>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) * total_coordinate > 0) {// 算出来的值大于0
					new_list.add(list.get(i) * total_coordinate);
				} else {// 算出来的值等于0，坐标加1
					new_list.add(list.get(i) * total_coordinate + 5);
				}
			}
		} else {
			Log.i("Utils", "input Y datas is error");
		}
		return new_list;
	}

	/**
	 * 趋势图，将左边转换成点
	 * 
	 * @param list
	 * @param total_coordinate
	 * @param point
	 * @return
	 */
	public static ArrayList<Point> yDatas2Point(ArrayList<Float> list,
			int total_coordinate, Point point) {
		Log.i("GraphicalView", point.x + "____" + point.y);
		ArrayList<Point> p_list = new ArrayList<Point>();
		p_list.add(point);// 添加坐标原点的坐标
		for (int i = 0; i < list.size(); i++) {
			Point c_point = new Point();
			c_point.set(point.x + (i + 1)
					* GraphicalParametes.X_COORDINATES_DISTACNE,
					(int) (GraphicalParametes.Y_DISTANCE + (1 - list.get(i))
							* total_coordinate));
			p_list.add(c_point);
		}
		// 加入最后一个点
		Point last_point = new Point();
		last_point.set(point.x + list.size()
				* GraphicalParametes.X_COORDINATES_DISTACNE, point.y);

		p_list.add(last_point);
		return p_list;
	}

	/**
	 * 画质量分析，历次，总趋势图
	 *
	 * @param list
	 *            对应的数据
	 * @param totalScore
	 *            考试满分
	 * @param which
	 *            文本的位置,传入的数字从0开始计算位置，即第一个位置穿0
	 * @return
	 */
	public static DrawingParametes drawDataTrend(
			List<DataTrend> list, int totalScore, int which) {
		DrawingParametes para = new DrawingParametes();
		para.setType(GraphicalParametes.CURVECHAT);
		ArrayList<String> xlist = new ArrayList<String>();
		ArrayList<String> ylist = new ArrayList<String>();
		// 对应电压曲线
		ArrayList<Float> y_flist1 = new ArrayList<Float>();
        ArrayList<Float> y_flist2 = new ArrayList<Float>();
        ArrayList<Float> y_flist4 = new ArrayList<Float>();
		ylist.add("0");
		ylist.add("220");
		para.setYlist(ylist);
		para.setYtwoPoint(true);
		for (int i = 0; i < list.size(); i++) {
			DataTrend dataTrend = list.get(i);
			xlist.add(dataTrend.getData());
			y_flist1.add(Float.parseFloat(dataTrend.getVoltage()));
            y_flist2.add(Float.parseFloat(dataTrend.getCurrent()));
            y_flist4.add(Float.parseFloat(dataTrend.getPower()));
		}
		para.setXlist(xlist);
		para.setYdatas1(y_flist1);
        para.setYdatas2(y_flist2);
        para.setYdatas4(y_flist4);
		para.setTotal_score(totalScore);
		para.setDrawTextAndLine(true);
		para.setWhichPosition(which + 1);
		para.setDrawXGrid(false);
		para.setDrawYGrid(false);
		return para;
	}

	/**
	 * ********** * 课堂练习 单次 错误人 柱状图************** 该方法用于封装的习题信息转换成画图的信息
	 * 
	 * @param list
	 * @return
	 */
//	public static DrawingParametes list2DrawingPara(
//			List<QuestionAnalysisVO> list) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画柱状图的左边的比列
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画柱状图的右边的比例
//		ArrayList<Boolean> Ydatas3 = new ArrayList<Boolean>();// 小孩正确与否
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		for (int i = 0; i < list.size(); i++) {
//			QuestionAnalysisVO vo = list.get(i);
//			float correct = Float.parseFloat(vo.getCorrectNumber());
//			float wrong = Float.parseFloat(vo.getWrongNumber());
//
//			Log.e("单次分析头像--correct--", (correct / (correct + wrong)) + "");
//			Log.e("单次分析头像--wrong--", (wrong / (correct + wrong)) + "");
//
//			Ydatas1.add((correct / (correct + wrong))); // 正确人数比例
//
//			Ydatas2.add((wrong / (correct + wrong))); // 错误人数比例
//
//			Ydatas3.add(ParseType(vo.getIsTrue())); // 是否回答正确
//
//			Xlist.add(vo.getQsort()); // 题目编号
//		}
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		para.setYdatas3(Ydatas3);
//		para.setXlist(Xlist);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.HISTOGRAM);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * **********课堂练习 单次 知识点************** 课堂练习 单次 知识点薄弱分析 柱状图
	 */
//	public static DrawingParametes list2DrawingParaRate(
//			List<QuestionAnalysisVO> list) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画柱状图的左边的比列
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画柱状图的右边的比例
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		for (int i = 0; i < list.size(); i++) {
//			QuestionAnalysisVO vo = list.get(i);
//
//			Log.e("个人的命中率--", ParsePersionRate(vo.getIsTrue()) + "");
//			Log.e("班级命中率--", Float.parseFloat(vo.getClassRate()) / 100 + "");
//
//			Ydatas1.add(ParsePersionRate(vo.getIsTrue())); // 个人的命中率
//
//			Ydatas2.add(Float.parseFloat(vo.getClassRate()) / 100); // 班级命中率
//
//			Xlist.add(vo.getQsort()); // 题目编号
//		}
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		para.setXlist(Xlist);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.HISTOGRAM);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * **********课堂练习 历次分析************** 课堂练习 历次 画个人 班级命中率 趋势图
	 * 
	 * @param almList
	 * @param type
	 *            0画两个，1对应Ydatas1，2对应Ydatas2
	 * @return
	 */
//	public static DrawingParametes list2DrawingTendencyPara(
//			List<AnalysisMoreVO> almList, int type) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画趋势图个人的命中率
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画趋势图班级命中率
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<Integer> XLen = new ArrayList<Integer>();// 对应X坐标的参数的个数
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		Random random = new Random();
//		Log.e("所有的练习的数量：", almList.size() + "");
//		String saveDate = "";
//		int len = 1;
//		for (int i = 0; i < almList.size(); i++) {
//			AnalysisMoreVO vo = almList.get(i);
//
//			String str = DateUtil.formatDateMday(Long.parseLong(vo
//					.getCreateTime()));
//			Log.e("--------->", str);
//
//			if (saveDate.equalsIgnoreCase(str)) {
//				len++;
//			} else {
//				if (saveDate.length() > 0) {
//					Xlist.add(saveDate);
//					XLen.add(len);
//				}
//				saveDate = str;
//				len = 1;
//			}
//
//			if (type == 0) {
//				Ydatas1.add(Float.parseFloat(vo.getStudentRate()) / 100); // 个人的命中率
//				Ydatas2.add(Float.parseFloat(vo.getClassRate()) / 100); // 班级命中率
//				// Ydatas1.add(random.nextFloat());
//				// Ydatas2.add(random.nextFloat());
//			} else if (type == 1) {
//				Ydatas1.add(Float.parseFloat(vo.getStudentRate()) / 100); // 个人的命中率
//				// Ydatas1.add(random.nextFloat());
//			} else if (type == 2) {
//				Ydatas2.add(Float.parseFloat(vo.getClassRate()) / 100); // 班级命中率
//				// Ydatas2.add(random.nextFloat());
//			}
//
//			// Xlist.add(String.valueOf(i + 1)); // 题目编号
//		}
//		Xlist.add(saveDate);
//		XLen.add(len);
//
//		Log.e("ASS", XLen.size() + "");
//		Log.e("CCC", Xlist.size() + "");
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		para.setXlist(Xlist);
//		para.setX_len(XLen);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.TENDENCY);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * 3. 教学质量分析单次考试小题分详情 将数据封装成画图信息
	 * 
	 * @param list
	 * @return
	 */
//	public static DrawingParametes drawSubjectQuestion(
//			List<SingleQuestionVO> list) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画柱状图的左边的比列
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画柱状图的右边的比例
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		for (int i = 0; i < list.size(); i++) {
//			SingleQuestionVO vo = list.get(i);
//			Ydatas1.add(Float.parseFloat(vo.getSTUSCORERATE())/100); // 个人的命中率
//
//			Ydatas2.add(Float.parseFloat(vo.getCLASSSCORERATE())/100); // 班级命中率
//
//			Xlist.add(vo.getQUID()); // 题目编号
//		}
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		para.setXlist(Xlist);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.HISTOGRAM);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	

	/**
	 * 画质量分析，历次，总趋势图
	 * 
	 * @param list
	 *            对应的数据
	 * @param totalScore
	 *            考试满分
	 * @param which
	 *            文本的位置,传入的数字从0开始计算位置，即第一个位置穿0
	 * @return
	 */
//	public static DrawingParametes drawGeneralTrend(
//			List<QualityAnalysisScoreInfoVO> list, int totalScore, int which) {
//		DrawingParametes para = new DrawingParametes();
//		para.setType(GraphicalParametes.CURVECHAT);
//		ArrayList<String> xlist = new ArrayList<String>();
//		ArrayList<String> ylist = new ArrayList<String>();
//		// 对应个人总分即第一条曲线
//		ArrayList<Float> y_flist1 = new ArrayList<Float>();
//		// 对应班级均分即第二条曲线
//		ArrayList<Float> y_flist2 = new ArrayList<Float>();
//		// 对应年纪均分即第三条曲线
//		ArrayList<Float> y_flist4 = new ArrayList<Float>();
//		ArrayList<String> score_list = new ArrayList<String>();
//		score_list.add("个人总分：" + list.get(which).getSUMMARK());
//		score_list.add("班级均分：" + list.get(which).getCLASSAVG());
//		score_list.add("年级均分：" + list.get(which).getSCHOOLAVG());
//		ylist.add("");
//		ylist.add("满分");
//		para.setYlist(ylist);
//		para.setYtwoPoint(true);
//		for (int i = 0; i < list.size(); i++) {
//			QualityAnalysisScoreInfoVO vo = list.get(i);
//			xlist.add(vo.getEXAM_CREATE_TIME().substring(5,
//					vo.getEXAM_CREATE_TIME().length()));
//			y_flist1.add(Float.parseFloat(vo.getSUMMARK()));
//			y_flist2.add(Float.parseFloat(vo.getCLASSAVG()));
//			y_flist4.add(Float.parseFloat(vo.getSCHOOLAVG()));
//		}
//		para.setXlist(xlist);
//		para.setYdatas1(y_flist1);
//		para.setYdatas2(y_flist2);
//		para.setYdatas4(y_flist4);
//		para.setTotal_score(totalScore);
//		para.setDrawTextAndLine(true);
//		para.setWhichPosition(which + 1);
//		para.setScore_list(score_list);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * 画质量分析，单次，单科走势图
	 * 
	 * @param list
	 * @param totalScore
	 * @param which
	 * @return
	 */
//	public static DrawingParametes drawSingleTrend(
//			List<QualityAnalysisSinglerSubjectInfoVO> list, int totalScore,
//			int which) {
//		DrawingParametes para = new DrawingParametes();
//		para.setType(GraphicalParametes.CURVECHAT);
//		ArrayList<String> xlist = new ArrayList<String>();
//		ArrayList<String> ylist = new ArrayList<String>();
//		// 对应个人总分即第一条曲线
//		ArrayList<Float> y_flist1 = new ArrayList<Float>();
//		// 对应班级均分即第二条曲线
//		ArrayList<Float> y_flist2 = new ArrayList<Float>();
//		// 对应年纪均分即第三条曲线
//		ArrayList<Float> y_flist4 = new ArrayList<Float>();
//		ArrayList<String> score_list = new ArrayList<String>();
//		score_list.add("个人得分：" + list.get(which).getSTUMARK());
//		score_list.add("班级均分：" + list.get(which).getCLASSAVG());
//		score_list.add("年级均分：" + list.get(which).getSCHOOLAVG());
//		ylist.add("");
//		ylist.add("满分");
//		para.setYlist(ylist);
//		para.setYtwoPoint(true);
//		for (int i = 0; i < list.size(); i++) {
//			QualityAnalysisSinglerSubjectInfoVO vo = list.get(i);
//			xlist.add(vo.getEXAM_CREATE_TIME().substring(5,
//					vo.getEXAM_CREATE_TIME().length()));
//			y_flist1.add(Float.parseFloat(vo.getSTUMARK()));
//			y_flist2.add(Float.parseFloat(vo.getCLASSAVG()));
//			y_flist4.add(Float.parseFloat(vo.getSCHOOLAVG()));
//		}
//		para.setXlist(xlist);
//		para.setYdatas1(y_flist1);
//		para.setYdatas2(y_flist2);
//		para.setYdatas4(y_flist4);
//		para.setTotal_score(totalScore);
//		para.setDrawTextAndLine(true);
//		para.setWhichPosition(which + 1);
//		para.setScore_list(score_list);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * **********课后作业 单次分析************** 该方法用于封装的习题信息转换成画图的信息
	 * 
	 * @param list
	 * @return
	 */
//	public static DrawingParametes DrawHomeWorkParaOne(
//			List<QuestionAnalysisVO> list) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画柱状图的左边的比列
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画柱状图的右边的比例
//		// ArrayList<Boolean> Ydatas3 = new ArrayList<Boolean>();// 小孩正确与否
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		Random random = new Random();
//		for (int i = 0; i < list.size(); i++) {
//			QuestionAnalysisVO vo = list.get(i);
//			if(null == vo.getStudentScoreRate()){
//				break;
//			}
//			float studentScore = Float.parseFloat(vo.getStudentScoreRate()) / 100;
//			float classScore = Float.parseFloat(vo.getClassScoreRate()) / 100;
//
//			Log.e("studentScore--", (studentScore + ""));
//			Log.e("classScore--", (classScore + ""));
//
//			Ydatas1.add(studentScore); // 个人得分率
//			Ydatas2.add(classScore); // 班级得分率
//			Xlist.add(vo.getQsort()); // 题目编号
//
//			// Ydatas1.add(random.nextFloat()); // 个人得分率
//			// Ydatas2.add(random.nextFloat()); // 班级得分率
//			// Xlist.add(String.valueOf(i)); // 题目编号
//		}
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		// para.setYdatas3(Ydatas3);
//		para.setXlist(Xlist);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.HISTOGRAM);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}
	
	/**
	 * 
	 * 课后作业 历次 画个人 班级命中率 趋势图
	 * 
	 * @param almList
	 * @param type
	 *            0画两个，1对应Ydatas1，2对应Ydatas2
	 * @return
	 */
//	public static DrawingParametes DrawHomeWorkParaMore(
//			List<AnalysisMoreVO> almList) {
//		DrawingParametes para = new DrawingParametes();
//		ArrayList<Float> Ydatas1 = new ArrayList<Float>();// 画趋势图个人的得分率
//		ArrayList<Float> Ydatas2 = new ArrayList<Float>();// 画趋势图班级得分率
//		ArrayList<String> Xlist = new ArrayList<String>();// 绘制X坐标
//		ArrayList<Integer> XLen = new ArrayList<Integer>();// 对应X坐标的参数的个数
//		ArrayList<String> Ylist = new ArrayList<String>();// 绘制Y坐标
//		Ylist.add("0%");
//		Ylist.add("100%");
//		Random random = new Random();
//		Log.e("所有的练习的数量：", almList.size() + "");
//		String saveDate = "";
//		int len = 1;
//		for (int i = 0; i < almList.size(); i++) {
//			AnalysisMoreVO vo = almList.get(i);
//			float studentScoreRate;
//			float classScoreRate;
//
//			String studentScore = vo.getStudentScoreRate();
//			String classScore = vo.getClassScoreRate();
//			if (null != studentScore && null != classScore) { // 得分率不为空时
//				studentScoreRate = Float.parseFloat(studentScore) / 100f;
//				classScoreRate = Float.parseFloat(classScore) / 100f;
//			} else { // 其余情况
//				continue;
//			}
//			String str = DateUtil.formatDateMday(Long.parseLong(vo
//					.getCreateTime()));
//			Log.e("--------->", str);
//
//			if (saveDate.equalsIgnoreCase(str)) {
//				len++;
//			} else {
//				if (saveDate.length() > 0) {
//					Xlist.add(saveDate);
//					XLen.add(len);
//				}
//				saveDate = str;
//				len = 1;
//			}
//			Ydatas1.add(studentScoreRate);
//			Ydatas2.add(classScoreRate);
//
//			// Ydatas1.add(random.nextFloat());
//			// Ydatas2.add(random.nextFloat());
//			// Xlist.add(String.valueOf(i + 1)); // 题目编号
//		}
//		Xlist.add(saveDate);
//		XLen.add(len);
//
//		// Log.e("ASS", XLen.size() + "");
//		Log.e("Xlist.size()", Xlist.size() + "");
//		para.setYdatas1(Ydatas1);
//		para.setYdatas2(Ydatas2);
//		para.setXlist(Xlist);
//		para.setX_len(XLen);
//		para.setYlist(Ylist);
//		para.setType(GraphicalParametes.TENDENCY);
//		para.setYtwoPoint(true);
//		para.setDrawXGrid(true);
//		para.setDrawYGrid(true);
//		return para;
//	}

	/**
	 * 曲线图将数据转换成点
	 * 
	 * @param list
	 *            对应的数据
	 * @param total_coordinate
	 *            图标的高度
	 * @param total_score
	 *            总分数
	 * @param point
	 *            原点的坐标
	 * @return
	 */
	public static ArrayList<Point> yDatas2PointCurve(ArrayList<Float> list,
			int total_coordinate, int total_score, Point point) {
		ArrayList<Point> p_list = new ArrayList<Point>();
		for (int i = 0; i < list.size(); i++) {
			Point c_point = new Point();
			c_point.set(
					point.x + (i + 1)
							* GraphicalParametes.X_COORDINATES_DISTACNE,
					(int) (GraphicalParametes.Y_DISTANCE + (1 - (list.get(i) / total_score))
							* total_coordinate));
			p_list.add(c_point);
		}
		return p_list;
	}

	private static Boolean ParseType(String isTrue) {
		if ("0".equals(isTrue)) {
			return false;
		} else if ("1".equals(isTrue)) {
			return true;
		}
		return null;
	}

	private static float ParsePersionRate(String isTrue) {
		if ("0".equals(isTrue)) {
			return 0.0f;
		} else if ("1".equals(isTrue)) {
			return 1.0f;
		}
		return 0.0f;
	}
}
