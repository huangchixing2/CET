package com.electric.cet.mobile.android.pq.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * 该类用于画曲线图
 *
 * @author xushsha
 */
public class GraphicalView extends View {
    int total_width, total_height;// 控件总的大小
    Point point = new Point();
    int x_coordinates, y_coordinates;// 坐标原点的坐标
    private DrawingParametes para;// 画图的参数类
    private Rect rect;// 矩形方框，用于固定画文字的位置
    private int total_distacne;// 画图的总宽度

    public GraphicalView(Context context) {
        super(context);
    }

    public GraphicalView(Context context, DrawingParametes para) {
        super(context);
        this.para = para;
    }

    /*
     *
     * 用于绘制表格
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawColor(Color.WHITE);// 画布的颜色，可以理解为背景色
        switch (para.getType()) {
            case GraphicalParametes.HISTOGRAM:// 画柱状图
                total_distacne = para.getYdatas1().size()
                        * GraphicalParametes.X_COORDINATES_DISTACNE;
                drawHistogram(canvas);
                break;
            case GraphicalParametes.TENDENCY:// 画趋势图
                total_distacne = para.getYdatas1().size()
                        * GraphicalParametes.X_COORDINATES_DISTACNE;
                drawTendency(canvas);
                break;
            case GraphicalParametes.CURVECHAT:// 曲线图
                total_distacne = para.getYdatas1().size()
                        * GraphicalParametes.X_COORDINATES_DISTACNE;
                drawCurve(canvas);
                break;
            default:
                break;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onSizeChanged(int, int, int, int)
     *
     * 获取控件的坐标,在这里将坐标原点作为中心
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        x_coordinates = GraphicalParametes.X_DISTANCE;
        y_coordinates = getHeight() - GraphicalParametes.Y_DISTANCE;
        total_width = getWidth() - GraphicalParametes.X_DISTANCE;
        total_height = getHeight() - 2 * GraphicalParametes.Y_DISTANCE;
        point.set(x_coordinates, y_coordinates);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    int x0;

    /*
     *
     * 用于处理拖动事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();

        switch (action) {
            case MotionEvent.ACTION_DOWN:// 按下
                x0 = x;
                break;
            case MotionEvent.ACTION_MOVE:// 移动
                if (point.x < (x_coordinates - total_distacne + GraphicalParametes.X_COORDINATES_DISTACNE)
                        && (x - x0) < 0) {

                } else if (point.x > (total_width - GraphicalParametes.X_DISTANCE - 2 * GraphicalParametes.X_COORDINATES_DISTACNE)
                        && (x - x0) > 0) {

                } else {
                    point.x += x - x0;
                    x0 = x;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:// 弹起
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 绘制柱状图
     *
     * @param canvas
     */
    void drawHistogram(Canvas canvas) {
        drawGrid(canvas);
        ArrayList<Float> list1;
        ArrayList<Float> list2;
        if (para.getYdatas1() != null && para.getYdatas1().size() > 0) {
            list1 = GraphicalUtils.yDatas2Coordinate(para.getYdatas1(),
                    total_height);
            list2 = GraphicalUtils.yDatas2Coordinate(para.getYdatas2(),
                    total_height);
        } else {
            Log.i("GraphicalView", "input Ydatas is errorF");
            return;
        }
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#b9d55d"));
        // 绘制左边的柱状图
        for (int i = 0; i < list1.size(); i++) {
            canvas.drawRect(point.x + (i + 1)
                            * GraphicalParametes.X_COORDINATES_DISTACNE
                            - GraphicalParametes.COLUMNAR_DISTANCE
                            - GraphicalParametes.COLUMNAR_SIZE, point.y - list1.get(i),
                    point.x + (i + 1)
                            * GraphicalParametes.X_COORDINATES_DISTACNE
                            - GraphicalParametes.COLUMNAR_DISTANCE, point.y,
                    paint);
        }
        paint.setColor(Color.parseColor("#dc6363"));
        // 绘制右边的柱状图
        for (int i = 0; i < list2.size(); i++) {
            canvas.drawRect(point.x + (i + 1)
                            * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                            * GraphicalParametes.COLUMNAR_DISTANCE,
                    point.y - list2.get(i), point.x + (i + 1)
                            * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                            * GraphicalParametes.COLUMNAR_DISTANCE
                            + GraphicalParametes.COLUMNAR_SIZE, point.y, paint);
        }
        paint.setColor(Color.WHITE);
        // 绘制学生正确与否的矩形,为空就不绘制
        if (para.getYdatas3() != null && para.getYdatas3().size() > 0) {
            for (int i = 0; i < para.getYdatas3().size(); i++) {
                boolean flag = para.getYdatas3().get(i) == null ? false : true;
                if (flag) {// 正确绘制在左边
                    canvas.drawRect(
                            point.x + (i + 1)
                                    * GraphicalParametes.X_COORDINATES_DISTACNE
                                    - GraphicalParametes.COLUMNAR_DISTANCE
                                    - GraphicalParametes.COLUMNAR_SIZE,
                            point.y - list1.get(i)
                                    - GraphicalParametes.STUDENT_CORRECT_HEIGHT,
                            point.x + (i + 1)
                                    * GraphicalParametes.X_COORDINATES_DISTACNE
                                    - GraphicalParametes.COLUMNAR_DISTANCE,
                            point.y - list1.get(i), paint);
                } else {// 错误绘制在右边
                    canvas.drawRect(point.x + (i + 1)
                                    * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                                    * GraphicalParametes.COLUMNAR_DISTANCE, point.y
                                    - list2.get(i)
                                    - GraphicalParametes.STUDENT_CORRECT_HEIGHT,
                            point.x + (i + 1)
                                    * GraphicalParametes.X_COORDINATES_DISTACNE
                                    + 2 * GraphicalParametes.COLUMNAR_DISTANCE
                                    + GraphicalParametes.COLUMNAR_SIZE, point.y
                                    - list2.get(i), paint);
                }
            }
        }
        // 绘制对应的矩形的文本描述
        if (para.isDrawTextAndLine()) {
            drawSigleTextAndLine(canvas, list1, list2);
        }
        // 先绘制坐标轴里面的内容，再画坐标轴
        drawCoordinateAxis(canvas);
    }

    /**
     * 画趋势图
     *
     * @param canvas
     */
    void drawTendency(Canvas canvas) {
        drawGrid(canvas);
        Paint paint = new Paint();
        // paint.setStyle(Paint.Style.FILL);
        Paint paint1 = new Paint();
        if (para.getYdatas1() != null && para.getYdatas1().size() > 0) {
            total_distacne = para.getYdatas1().size()
                    * GraphicalParametes.X_COORDINATES_DISTACNE;
            ArrayList<Point> list = GraphicalUtils.yDatas2Point(
                    para.getYdatas1(), total_height, point);
            // 画第一个多边形

            paint.setColor(Color.parseColor("#8bd49a"));
            paint.setAntiAlias(true);
            paint.setAlpha(180);
            drawPolygon(canvas, list, paint);

            paint1.setColor(Color.WHITE);
            drawMline(canvas, list, paint1);
            paint1.setColor(Color.WHITE);
            drawMpoint(canvas, list, paint1);
        }

        if (para.getYdatas2() != null && para.getYdatas2().size() > 0) {
            total_distacne = para.getYdatas2().size()
                    * GraphicalParametes.X_COORDINATES_DISTACNE;
            ArrayList<Point> list1 = GraphicalUtils.yDatas2Point(
                    para.getYdatas2(), total_height, point);
            // 画第二个多边形
            paint.setColor(Color.parseColor("#9abadc"));
            paint.setAlpha(180);
            drawPolygon(canvas, list1, paint);
            paint1.setColor(Color.parseColor("#d3dee3"));
            drawMline(canvas, list1, paint1);
            paint1.setColor(Color.parseColor("#d3dee3"));
            drawMpoint(canvas, list1, paint1);
        }
        drawCoordinateAxis(canvas);
    }

    /**
     * 绘制多边形
     *
     * @param canvas
     * @param list   需要绘制多边形的点的集合,需要自己加两个点，即如果20个数据，需要22个点
     */
    void drawPolygon(Canvas canvas, ArrayList<Point> list, Paint paint) {
        // 绘制多边形时，当大概40到50边的时候，不会覆盖区域，所以每30条边绘制一次多边形
        int what = 0;// 需要绘制多少个多边形
        if (list.size() % 30 == 0) {
            what = list.size() / 30;
        } else {
            what = (list.size() / 30) + 1;
        }
        Log.i("GraphicalView", "" + what);
        for (int i = 0; i < what; i++) {
            Path path = new Path();
            path.moveTo(list.get(i * 30).x, list.get(0).y);
            if (i != 0) {
                path.lineTo(list.get(i * 30).x, list.get(i * 30).y);
            }
            if (i == (list.size() / 30)) {
                for (int j = i * 30 + 1; j < list.size(); j++) {
                    path.lineTo(list.get(j).x, list.get(j).y);
                }
            } else {
                for (int j = i * 30 + 1; j < (i + 1) * 30 + 1; j++) {
                    if (list.size() == j) {
                        continue;
                    }
                    path.lineTo(list.get(j).x, list.get(j).y);
                    if (j == (i + 1) * 30) {
                        path.lineTo(list.get(j).x, list.get(0).y);
                    }

                }
            }
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    /**
     * 画曲线图
     *
     * @param canvas
     */
    void drawCurve(Canvas canvas) {
        drawGrid(canvas);
        ArrayList<Point> list = GraphicalUtils.yDatas2PointCurve(
                para.getYdatas1(), total_height, para.getTotal_score(), point);
//		ArrayList<Point> list1 = GraphicalUtils.yDatas2PointCurve(
//				para.getYdatas2(), total_height, para.getTotal_score(), point);
//		ArrayList<Point> list2 = GraphicalUtils.yDatas2PointCurve(
//				para.getYdatas4(), total_height, para.getTotal_score(), point);
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setColor(Color.RED);
        drawMline(canvas, list, paint);
        drawMpoint(canvas, list, paint);
//		paint.setColor(Color.BLUE);
//		drawMline(canvas, list1, paint);
//		drawMpoint(canvas, list1, paint);
//		paint.setColor(Color.GREEN);
//		drawMline(canvas, list2, paint);
//		drawMpoint(canvas, list2, paint);
        if (para.isDrawTextAndLine()) {
            drawCurveTextAndLine(canvas);
        }
        drawCoordinateAxis(canvas);
    }

    /**
     * 绘制曲线图上的文字
     *
     * @param canvas
     */
    void drawCurveTextAndLine(Canvas canvas) {
        if (para.getScore_list() == null || para.getScore_list().size() != 3) {
            Log.i("GraphicalView", "Score_list is error");
            return;
        }
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        int i = para.getWhichPosition();
        canvas.drawLine(
                point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE,
                point.y - total_height, point.x + i
                        * GraphicalParametes.X_COORDINATES_DISTACNE, point.y,
                paint);
        rect = new Rect(point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE
                - GraphicalParametes.DRAWTEXT_WIDTH / 2,
                point.y - total_height, point.x + i
                * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                + GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y
                - total_height + GraphicalParametes.DRAWTEXT_HEIGHT);
        canvas.drawRect(rect, paint);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        FontMetrics fontMertics = paint.getFontMetrics();
        float baseline = rect.top
                + (rect.bottom - rect.top - fontMertics.bottom + fontMertics.top)
                / 2 - fontMertics.top;
        canvas.drawText(para.getScore_list().get(0), rect.centerX(), baseline,
                paint);
        rect = new Rect(point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE
                - GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y - total_height
                + GraphicalParametes.DRAWTEXT_HEIGHT, point.x + i
                * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                + GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y - total_height
                + 2 * GraphicalParametes.DRAWTEXT_HEIGHT);
        paint.setColor(Color.WHITE);
        canvas.drawRect(rect, paint);
        float baseline1 = rect.top
                + (rect.bottom - rect.top - fontMertics.bottom + fontMertics.top)
                / 2 - fontMertics.top;
        paint.setColor(Color.BLACK);
        canvas.drawText(para.getScore_list().get(1), rect.centerX(), baseline1,
                paint);
        rect = new Rect(point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE
                - GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y - total_height
                + 2 * GraphicalParametes.DRAWTEXT_HEIGHT, point.x + i
                * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                + GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y - total_height
                + 3 * GraphicalParametes.DRAWTEXT_HEIGHT);
        paint.setColor(Color.WHITE);
        canvas.drawRect(rect, paint);
        float baseline2 = rect.top
                + (rect.bottom - rect.top - fontMertics.bottom + fontMertics.top)
                / 2 - fontMertics.top;
        paint.setColor(Color.BLACK);
        canvas.drawText(para.getScore_list().get(2), rect.centerX(), baseline2,
                paint);
    }

    /**
     * 绘制多边形的点
     *
     * @param canvas
     * @param list
     */
    void drawMpoint(Canvas canvas, ArrayList<Point> list, Paint paint) {
        // Paint paint = new Paint();
        // paint.setColor(Color.GREEN);
        if (GraphicalParametes.CURVECHAT == para.getType()) {
            for (int i = 0; i < list.size(); i++) {
                canvas.drawCircle(list.get(i).x, list.get(i).y, 10, paint);
            }
        } else {
            for (int i = 1; i < list.size() - 1; i++) {
                canvas.drawCircle(list.get(i).x, list.get(i).y, 10, paint);
            }
        }
    }

    /**
     * 绘制多边形的线
     *
     * @param canvas
     * @param list
     */
    void drawMline(Canvas canvas, ArrayList<Point> list, Paint paint) {
        // Paint paint = new Paint();
        // paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        for (int i = 1; i < list.size(); i++) {
            canvas.drawLine(list.get(i - 1).x, list.get(i - 1).y,
                    list.get(i).x, list.get(i).y, paint);
        }
    }

    /**
     * 绘制网格
     *
     * @param canvas
     */
    void drawGrid(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAlpha(80);
        int x_last = 0;
        if (para.isDrawXGrid()) {
            for (int i = 0; (y_coordinates - (i + 1)
                    * GraphicalParametes.GRID_INSTANCE) > GraphicalParametes.Y_DISTANCE; i++) {
                canvas.drawLine(x_coordinates, y_coordinates - (i + 1)
                        * GraphicalParametes.GRID_INSTANCE, x_coordinates
                        + total_width, y_coordinates - (i + 1)
                        * GraphicalParametes.GRID_INSTANCE, paint);
                x_last = y_coordinates - (i + 1)
                        * GraphicalParametes.GRID_INSTANCE;
            }
        }

        if (para.isDrawYGrid()) {
            for (int i = 0; (x_coordinates + (i + 1)
                    * GraphicalParametes.GRID_INSTANCE) < total_width
                    + GraphicalParametes.X_DISTANCE; i++) {
                canvas.drawLine(x_coordinates + (i + 1)
                                * GraphicalParametes.GRID_INSTANCE, y_coordinates,
                        x_coordinates + (i + 1)
                                * GraphicalParametes.GRID_INSTANCE, x_last,
                        paint);
            }
        }
    }

    /**
     * 画坐标轴
     *
     * @param canvas
     */
    void drawCoordinateAxis(Canvas canvas) {
        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setStrokeWidth(4);
        canvas.drawLine(x_coordinates, y_coordinates, x_coordinates + getWidth(),
                y_coordinates, paint1);// 画X轴
        canvas.drawLine(x_coordinates, y_coordinates, x_coordinates, 0, paint1);// 画Y轴
        // 画X,Y轴对应的坐标文字
        if (para.getXlist() == null || para.getYlist() == null) {
            // 这里需要判断是否为空，为空不再绘制
            Log.i("GraphicalView", "input X_list or Y_list data error");
            return;
        }
        paint1.setTextSize(40);
        // 绘制X轴文字
        if (para.isXtwoPoint()) {
            canvas.drawText(para.getXlist().get(0), point.x, point.y, paint1);
            canvas.drawText(para.getXlist().get(1), getWidth() - 30, point.y,
                    paint1);
        } else {
            if (para.getX_len() == null) {// 一一对应的关系
                for (int i = 0; i < para.getXlist().size(); i++) {
                    canvas.drawText(para.getXlist().get(i) == null ? "" : para.getXlist().get(i), point.x + (i + 1)
                                    * GraphicalParametes.X_COORDINATES_DISTACNE,
                            point.y + GraphicalParametes.Y_DISTANCE - GraphicalParametes.ERROR_DISTANCE, paint1);
                }
            } else {// x轴坐标对应多条数据
                for (int i = 0; i < para.getXlist().size(); i++) {
                    int len = 0;
                    for (int j = 0; j < i; j++) {
                        len += para.getX_len().get(j);
                    }
                    canvas.drawText(para.getXlist().get(i) == null ? "" : para.getXlist().get(i), point.x + len
                                    * GraphicalParametes.X_COORDINATES_DISTACNE,
                            point.y + GraphicalParametes.Y_DISTANCE, paint1);
                }
            }
        }
        // 绘制Y轴文字
        if (para.isYtwoPoint()) {
            canvas.drawText(para.getYlist().get(0), 0
                    , y_coordinates
                            - GraphicalParametes.ERROR_DISTANCE, paint1);
            canvas.drawText(para.getYlist().get(1), 0
                    ,
                    GraphicalParametes.Y_DISTANCE + GraphicalParametes.ERROR_DISTANCE, paint1);
        } else {
            for (int i = 0; i < para.getYlist().size(); i++) {
                canvas.drawText(para.getYlist().get(i), point.x
                        + GraphicalParametes.ERROR_DISTANCE, point.y
                        - GraphicalParametes.ERROR_DISTANCE - i
                        * GraphicalParametes.Y_COORDINATES_DISTACNE, paint1);
            }
        }
    }

    /**
     * 画柱状图上单一的对应的线和文字
     *
     * @param canvas
     */
    void drawSigleTextAndLine(Canvas canvas, ArrayList<Float> list1,
                              ArrayList<Float> list2) {
        // 先判断画矩形文字的位置，看是否需要画线
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        int i = para.getWhichPosition();// 绘制在那组数据上
        // 绘制在左边还是右边
        if (para.isLeftOrRight()) {
            canvas.drawLine(
                    point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE
                            - GraphicalParametes.COLUMNAR_DISTANCE
                            - GraphicalParametes.COLUMNAR_SIZE / 2,
                    point.y - list1.get(i - 1),
                    point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE
                            - GraphicalParametes.COLUMNAR_DISTANCE
                            - GraphicalParametes.COLUMNAR_SIZE / 2,
                    point.y - total_height + GraphicalParametes.DRAWTEXT_HEIGHT,
                    paint);
            Rect rect = new Rect(point.x + i
                    * GraphicalParametes.X_COORDINATES_DISTACNE
                    - GraphicalParametes.COLUMNAR_DISTANCE
                    - GraphicalParametes.COLUMNAR_SIZE / 2
                    - GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y
                    - total_height, point.x + i
                    * GraphicalParametes.X_COORDINATES_DISTACNE
                    - GraphicalParametes.COLUMNAR_DISTANCE
                    - GraphicalParametes.COLUMNAR_SIZE / 2
                    + GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y
                    - total_height + GraphicalParametes.DRAWTEXT_HEIGHT);
            canvas.drawRect(rect, paint);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            FontMetrics fontMertics = paint.getFontMetrics();
            float baseline = rect.top
                    + (rect.bottom - rect.top - fontMertics.bottom + fontMertics.top)
                    / 2 - fontMertics.top;
            // 这里需要在矩形中绘制文本，需要将文本在矩形中居中
            canvas.drawText(para.getDrawText(), rect.centerX(), baseline, paint);
        } else {
            canvas.drawLine(
                    point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                            * GraphicalParametes.COLUMNAR_DISTANCE
                            + GraphicalParametes.COLUMNAR_SIZE / 2,
                    point.y - list2.get(i - 1),
                    point.x + i * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                            * GraphicalParametes.COLUMNAR_DISTANCE
                            + GraphicalParametes.COLUMNAR_SIZE / 2,
                    point.y - total_height + GraphicalParametes.DRAWTEXT_HEIGHT,
                    paint);
            Rect rect = new Rect(point.x + i
                    * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                    * GraphicalParametes.COLUMNAR_DISTANCE
                    + GraphicalParametes.COLUMNAR_SIZE / 2
                    - GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y
                    - total_height, point.x + i
                    * GraphicalParametes.X_COORDINATES_DISTACNE + 2
                    * GraphicalParametes.COLUMNAR_DISTANCE
                    + GraphicalParametes.COLUMNAR_SIZE / 2
                    + GraphicalParametes.DRAWTEXT_WIDTH / 2, point.y
                    - total_height + GraphicalParametes.DRAWTEXT_HEIGHT);
            canvas.drawRect(rect, paint);
            paint.setColor(Color.BLACK);
            paint.setTextAlign(Paint.Align.CENTER);
            FontMetrics fontMertics = paint.getFontMetrics();
            float baseline = rect.top
                    + (rect.bottom - rect.top - fontMertics.bottom + fontMertics.top)
                    / 2 - fontMertics.top;
            // 这里需要在矩形中绘制文本，需要将文本在矩形中居中
            canvas.drawText(para.getDrawText(), rect.centerX(), baseline, paint);
        }
    }
}
