package com.electric.cet.mobile.android.pq.ui.view;

/**
 * 接口中定义画图需要的参数
 * 
 * @author xushsha
 * 
 */
public interface GraphicalParametes {
	/**
	 * 坐标原点距离左边的距离
	 */
	public final static int X_DISTANCE = 70;

	/**
	 * 左边原点距离底部的距离
	 */
	public final static int Y_DISTANCE = 45;

	/**
	 * 曲线图
	 */
	public final static int CURVECHAT = 2;

	/**
	 * 柱状图
	 */
	public final static int HISTOGRAM = 0;

	/**
	 * 趋势图
	 */
	public final static int TENDENCY = 1;

	/**
	 * X轴两个坐标点的距离
	 */
	public final static int X_COORDINATES_DISTACNE = 20;

	/**
	 * Y轴两个坐标点的距离
	 */
	public final static int Y_COORDINATES_DISTACNE = 180;

	/**
	 * 画图时由于美观需要的一点误差值
	 */
	public final static int ERROR_DISTANCE = 5;

	/**
	 * 画柱状图时两个柱状的平均距离
	 */
	public final static int COLUMNAR_DISTANCE = 5;

	/**
	 * 柱状图的大小
	 */
	public final static int COLUMNAR_SIZE = 35;

	/**
	 * 绘制学生正确还是错误的柱状图的高度
	 */
	public final static int STUDENT_CORRECT_HEIGHT = 5;

	/****************************************
	 * 绘制文本矩形的宽度
	 */
	public final static int DRAWTEXT_WIDTH = 120;

	/*
	 * 绘制文本矩形的高度
	 */
	public final static int DRAWTEXT_HEIGHT = 20;

	/**
	 * 网格的间距
	 */
	public final static int GRID_INSTANCE = 200;

}
