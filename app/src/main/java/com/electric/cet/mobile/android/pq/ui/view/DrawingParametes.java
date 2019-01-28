package com.electric.cet.mobile.android.pq.ui.view;

import java.util.ArrayList;

/**
 * @author xushsha
 * 
 */
public class DrawingParametes {
	/**
	 * X轴对应的数据
	 */
	private ArrayList<Float> Xdatas;

	/**
	 * Y轴对应的数据
	 */
	private ArrayList<Float> Ydatas1;

	/**
	 * Y轴对应的数据
	 */
	private ArrayList<Float> Ydatas2;

	/**
	 * Y轴对应的数据，用于绘制孩子的作答的对错,true为正确，false为错误
	 */
	private ArrayList<Boolean> Ydatas3;

	/**
	 * 画曲线图时的第三条线
	 */
	private ArrayList<Float> Ydatas4;

	/**
	 * X轴对应的数据，用于画坐标轴的文字
	 */
	private ArrayList<String> Xlist;

	/**
	 * 总分数，用于计算比例
	 */
	private int total_score;

	/**
	 * 显示分数的list，第一条为个人总分，第二条为班级均分，第三条为年级均分
	 */
	private ArrayList<String> score_list;

	/**
	 * X轴对应的数据，每条数据画几个点，如果Xdatas与Xlist一一对应，则不需要设置这个参数
	 */
	private ArrayList<Integer> X_len;

	/**
	 * Y轴对应的数据，用于画坐标轴的文字
	 */
	private ArrayList<String> Ylist;

	/**
	 * X轴坐标轴的描述
	 */
	private String Xtitle;

	/**
	 * Y轴坐标轴的描述
	 */
	private String Ytitle;

	/**
	 * 画什么曲线，暂时这样定义，0为柱状图，1趋势图
	 */
	private int type;

	/**
	 * X轴是否只是绘制两个点，即起点和终点
	 */
	private boolean isXtwoPoint = false;

	/**
	 * Y轴是否只是绘制两个点，即起点和终点
	 */
	private boolean isYtwoPoint = false;

	/**
	 * 绘制对应的矩形的文字描述
	 */
	private boolean isDrawTextAndLine = false;

	/**
	 * 需要绘制的文本
	 */
	private String drawText;

	/**
	 * 绘制在左边还是右边,true为左边，false为右边
	 */
	private boolean isLeftOrRight;

	/**
	 * 绘制在那个位置,对应传入的数据的位置,即Y轴数据 这个位置从1开始
	 */
	private int whichPosition;

	/**
	 * 是否绘制X轴的网格,与X轴平行的线
	 */
	private boolean isDrawXGrid = false;

	/**
	 * 是否绘制Y轴的网格,与Y轴平行的线
	 */
	private boolean isDrawYGrid = false;

	public boolean isXtwoPoint() {
		return isXtwoPoint;
	}

	public void setXtwoPoint(boolean isXtwoPoint) {
		this.isXtwoPoint = isXtwoPoint;
	}

	public boolean isYtwoPoint() {
		return isYtwoPoint;
	}

	public void setYtwoPoint(boolean isYtwoPoint) {
		this.isYtwoPoint = isYtwoPoint;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ArrayList<Float> getXdatas() {
		return Xdatas;
	}

	public void setXdatas(ArrayList<Float> xdatas) {
		Xdatas = xdatas;
	}

	public ArrayList<Float> getYdatas1() {
		return Ydatas1;
	}

	public void setYdatas1(ArrayList<Float> ydatas1) {
		Ydatas1 = ydatas1;
	}

	public ArrayList<Float> getYdatas2() {
		return Ydatas2;
	}

	public void setYdatas2(ArrayList<Float> ydatas2) {
		Ydatas2 = ydatas2;
	}

	public ArrayList<Boolean> getYdatas3() {
		return Ydatas3;
	}

	public void setYdatas3(ArrayList<Boolean> ydatas3) {
		Ydatas3 = ydatas3;
	}

	public ArrayList<String> getXlist() {
		return Xlist;
	}

	public void setXlist(ArrayList<String> xlist) {
		Xlist = xlist;
	}

	public ArrayList<String> getYlist() {
		return Ylist;
	}

	public void setYlist(ArrayList<String> ylist) {
		Ylist = ylist;
	}

	public String getXtitle() {
		return Xtitle;
	}

	public void setXtitle(String xtitle) {
		Xtitle = xtitle;
	}

	public String getYtitle() {
		return Ytitle;
	}

	public void setYtitle(String ytitle) {
		Ytitle = ytitle;
	}

	public boolean isDrawTextAndLine() {
		return isDrawTextAndLine;
	}

	public void setDrawTextAndLine(boolean isDrawTextAndLine) {
		this.isDrawTextAndLine = isDrawTextAndLine;
	}

	public String getDrawText() {
		return drawText;
	}

	public void setDrawText(String drawText) {
		this.drawText = drawText;
	}

	public boolean isLeftOrRight() {
		return isLeftOrRight;
	}

	public void setLeftOrRight(boolean isLeftOrRight) {
		this.isLeftOrRight = isLeftOrRight;
	}

	public int getWhichPosition() {
		return whichPosition;
	}

	public void setWhichPosition(int whichPosition) {
		this.whichPosition = whichPosition;
	}

	public boolean isDrawXGrid() {
		return isDrawXGrid;
	}

	public void setDrawXGrid(boolean isDrawXGrid) {
		this.isDrawXGrid = isDrawXGrid;
	}

	public boolean isDrawYGrid() {
		return isDrawYGrid;
	}

	public void setDrawYGrid(boolean isDrawYGrid) {
		this.isDrawYGrid = isDrawYGrid;
	}

	public ArrayList<Integer> getX_len() {
		return X_len;
	}

	public void setX_len(ArrayList<Integer> x_len) {
		X_len = x_len;
	}

	public ArrayList<Float> getYdatas4() {
		return Ydatas4;
	}

	public void setYdatas4(ArrayList<Float> ydatas4) {
		Ydatas4 = ydatas4;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	public ArrayList<String> getScore_list() {
		return score_list;
	}

	public void setScore_list(ArrayList<String> score_list) {
		this.score_list = score_list;
	}

}
