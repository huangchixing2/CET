package com.electric.cet.mobile.android.pq.utils;

import android.os.AsyncTask;

/**
 * 封装AsyncTask为工具类
 *
 */

public class GetAsyncTaskData extends AsyncTask<String,String,String> {
    private static final boolean IsInstalled = true ;

    //后台做耗时的操作，如网络请求
    @Override
    protected String doInBackground(String... strings) {

//        // 查询手机号码（段）信息*/
//        try {
//            WebServiceTools.getWether();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //将结果返回给onPostExecute方法
//        HttpUtil httpUtil = new HttpUtil();
//        String result = httpUtil.get(strings[0]);


         return "";
    }

    //此方法可以在主线程改变UI
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // 将WebService返回的结果显示在TextView中
            //resultView.setText(result);
    }
}
