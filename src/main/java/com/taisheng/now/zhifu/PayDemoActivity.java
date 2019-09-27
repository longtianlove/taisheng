package com.taisheng.now.zhifu;

import java.util.Map;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.taisheng.now.R;
import com.taisheng.now.zhifu.util.OrderInfoUtil2_0;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 *  重要说明：
 *  
 *  本 Demo 只是为了方便直接向商户展示支付宝的整个支付流程，所以将加签过程直接放在客户端完成
 *  （包括 OrderInfoUtil2_0_HK 和 OrderInfoUtil2_0）。
 *
 *  在真实 App 中，私钥（如 RSA_PRIVATE 等）数据严禁放在客户端，同时加签过程务必要放在服务端完成，
 *  否则可能造成商户私密数据泄露或被盗用，造成不必要的资金损失，面临各种安全风险。
 *
 *  Warning:
 *
 *  For demonstration purpose, the assembling and signing of the request parameters are done on
 *  the client side in this demo application.
 *
 *  However, in practice, both assembling and signing must be carried out on the server side.
 */
public class PayDemoActivity extends AppCompatActivity {
	
	/**
	 * 用于支付宝支付业务的入参 app_id。
	 */
	public static final String APPID = "2019092667858035";
	
	/**
	 * 用于支付宝账户登录授权业务的入参 pid。
	 */
	public static final String PID = "";

	/**
	 * 用于支付宝账户登录授权业务的入参 target_id。
	 */
	public static final String TARGET_ID = "";

	/**
	 *  pkcs8 格式的商户私钥。
	 *
	 * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
	 * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
	 * 	RSA2_PRIVATE。
	 *
	 * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
	 * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
	 */
	public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhk" +
			"iG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOkBK/bbYWxedVfoTD8i7Zc" +
			"lO81EoHUQR9MJ3AA3xYrOdJS7qAMVw1HP4TJFG7R5iRMGm9RRjgt7w" +
			"7+vAus5BNYuoDWyWEHVaDAoNxfQ+gnAopdfEh90ECOYao9fFOcC0" +
			"jH/RfynIsLUg9RQiTsj/t4os3SkRtB68GTd29rs7uQe00EITO" +
			"gBkr4N3fgv6CXKdNXykIBbozAgUth2DYZgxkv+EuYDaENuT" +
			"Cm8GS5bNza5HgN+TTvRlLbfuxVi2Y6+zLCgDLX2PAeZDsLmY" +
			"LfccRJTpWibQGCargAZKVAF/zHOLjTm09Co3w5PsFQOJNIqgWI/u" +
			"iUEcn/f8YFuN+pQR7AgMBAAECggEAas2Q3g0JX6JAHxK70Dh91LYL+e" +
			"lo1HZj0I49gH6cB6rxUz4CqMtR8xCXYCvTd2S57fvBG+TKMIA16xGiIrqvnuo" +
			"9uunr/0Ajcd7OLpWswXxlOI59ah7cexgxxB8M+uCbUDc+Uw1N377E4fH37P6bWlJ" +
			"gTbm77ZrWCvkG6HYv3NotqdWxlDj/xKPUXUcjxhRrUcOcxdYzuCuiRb+Hk2s0p4cb0RD" +
			"fX5XbAcFEpqv8eA+cQGWT4Hx43p6+0fDuEnrcKGV2lFJswmnC4J9z5CKmWHcDppiTGWvlU" +
			"2MT2FQncE7o91w5h3LYh7Ck9clGmWYl4+b0MB2LpfIJdgwMufk4YQKBgQDvGGVTaa2JDH" +
			"K5fVtAVuGA23ZofpwxOX/DBNOpS/oRl7t9XI0Wx6KcI4rS1N93cNUY/IfWWAZP5N/AMFc" +
			"es5n7l9Y5IUMIJvCBYf0dQy6g8Kb8MRArcgo8uGerFdDtLEyua4V/jmIk3inE3fZZyR4z" +
			"q4RMnDeJDLRr/xUd3K7GeQKBgQCYpHKE5xZt453bIWjcalyT+yNwU5ZuMXrc0luyXF1r8" +
			"TGMD5OSnEbku33k2KeKhfCkf/GQit/rKULXC9PFXsLaxeLfm8xTpi6XCnTrHoQBr2yhCc" +
			"7I9hxdS9Dipp00n2H7xM+rueBmLol3S7R1yy95dqSa9FwcCQzdeyYGzEI1kwKBgBg4gXM" +
			"8vl6myyxtPYQ8COYT1V/qi4oVCBNKduKslq2XkF+3s3c+TAPDOfn3pJel74BzfJNDIYiQ" +
			"Z/ZBL2Il0ZCCJiptayv1PeHtekWvm5akKlZb5gZwrAbQq3fSYvLTzQTSUZjFZL3VX6fY0" +
			"G7yB8Q2ONXF8X5JsjFKiuoWhAIJAoGAWCIbPUFwYeKasnuH4DxJV+gZfjHFGbrYBcgvyb" +
			"wq9Lg+s3AK9QvmowreldPCaOkNGO4GvD5zK4SXYzMhA0TDnS3E4PdD8shw/iS7fUYRUif" +
			"HwPO4wa/YkMXFxhgNjOS53nS0s6KPPnCATrEXcSvwLWGjiRSuWkGffAIu4p3tXKECgYEA" +
			"rDtCe6LtR0mr43x+SvR7UdvMlW5XJeZSScp40VBvrVw6VpdGJNEj+/Q01mZ8yW6BzQwsA" +
			"3px0XRH7Gpn4umS+Lpj4Rv+hhSSYy2dT/MZ0IKWMcaDbSCR93IP8QXOfDZbx37k/rDpCS" +
			"sTIIoMHJmi+ns8vtZYE/Cpo0pu1aCYqJA=";
	public static final String RSA_PRIVATE = "MIIEvQIBADANBgkqhk" +
			"iG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOkBK/bbYWxedVfoTD8i7Zc" +
			"lO81EoHUQR9MJ3AA3xYrOdJS7qAMVw1HP4TJFG7R5iRMGm9RRjgt7w" +
			"7+vAus5BNYuoDWyWEHVaDAoNxfQ+gnAopdfEh90ECOYao9fFOcC0" +
			"jH/RfynIsLUg9RQiTsj/t4os3SkRtB68GTd29rs7uQe00EITO" +
			"gBkr4N3fgv6CXKdNXykIBbozAgUth2DYZgxkv+EuYDaENuT" +
			"Cm8GS5bNza5HgN+TTvRlLbfuxVi2Y6+zLCgDLX2PAeZDsLmY" +
			"LfccRJTpWibQGCargAZKVAF/zHOLjTm09Co3w5PsFQOJNIqgWI/u" +
			"iUEcn/f8YFuN+pQR7AgMBAAECggEAas2Q3g0JX6JAHxK70Dh91LYL+e" +
			"lo1HZj0I49gH6cB6rxUz4CqMtR8xCXYCvTd2S57fvBG+TKMIA16xGiIrqvnuo" +
			"9uunr/0Ajcd7OLpWswXxlOI59ah7cexgxxB8M+uCbUDc+Uw1N377E4fH37P6bWlJ" +
			"gTbm77ZrWCvkG6HYv3NotqdWxlDj/xKPUXUcjxhRrUcOcxdYzuCuiRb+Hk2s0p4cb0RD" +
			"fX5XbAcFEpqv8eA+cQGWT4Hx43p6+0fDuEnrcKGV2lFJswmnC4J9z5CKmWHcDppiTGWvlU" +
			"2MT2FQncE7o91w5h3LYh7Ck9clGmWYl4+b0MB2LpfIJdgwMufk4YQKBgQDvGGVTaa2JDH" +
			"K5fVtAVuGA23ZofpwxOX/DBNOpS/oRl7t9XI0Wx6KcI4rS1N93cNUY/IfWWAZP5N/AMFc" +
			"es5n7l9Y5IUMIJvCBYf0dQy6g8Kb8MRArcgo8uGerFdDtLEyua4V/jmIk3inE3fZZyR4z" +
			"q4RMnDeJDLRr/xUd3K7GeQKBgQCYpHKE5xZt453bIWjcalyT+yNwU5ZuMXrc0luyXF1r8" +
			"TGMD5OSnEbku33k2KeKhfCkf/GQit/rKULXC9PFXsLaxeLfm8xTpi6XCnTrHoQBr2yhCc" +
			"7I9hxdS9Dipp00n2H7xM+rueBmLol3S7R1yy95dqSa9FwcCQzdeyYGzEI1kwKBgBg4gXM" +
			"8vl6myyxtPYQ8COYT1V/qi4oVCBNKduKslq2XkF+3s3c+TAPDOfn3pJel74BzfJNDIYiQ" +
			"Z/ZBL2Il0ZCCJiptayv1PeHtekWvm5akKlZb5gZwrAbQq3fSYvLTzQTSUZjFZL3VX6fY0" +
			"G7yB8Q2ONXF8X5JsjFKiuoWhAIJAoGAWCIbPUFwYeKasnuH4DxJV+gZfjHFGbrYBcgvyb" +
			"wq9Lg+s3AK9QvmowreldPCaOkNGO4GvD5zK4SXYzMhA0TDnS3E4PdD8shw/iS7fUYRUif" +
			"HwPO4wa/YkMXFxhgNjOS53nS0s6KPPnCATrEXcSvwLWGjiRSuWkGffAIu4p3tXKECgYEA" +
			"rDtCe6LtR0mr43x+SvR7UdvMlW5XJeZSScp40VBvrVw6VpdGJNEj+/Q01mZ8yW6BzQwsA" +
			"3px0XRH7Gpn4umS+Lpj4Rv+hhSSYy2dT/MZ0IKWMcaDbSCR93IP8QXOfDZbx37k/rDpCS" +
			"sTIIoMHJmi+ns8vtZYE/Cpo0pu1aCYqJA=";

	
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
				} else {
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
				}
				break;
			}
			case SDK_AUTH_FLAG: {
				@SuppressWarnings("unchecked")
				AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
				String resultStatus = authResult.getResultStatus();

				// 判断resultStatus 为“9000”且result_code
				// 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
				if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
					// 获取alipay_open_id，调支付时作为参数extern_token 的value
					// 传入，则支付账户为该授权账户
					showAlert(PayDemoActivity.this, getString(R.string.auth_success) + authResult);
				} else {
					// 其他状态值则为授权失败
					showAlert(PayDemoActivity.this, getString(R.string.auth_failed) + authResult);
				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_main);
	}

	/**
	 * 支付宝支付业务示例
	 */
	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
			showAlert(this, getString(R.string.error_missing_appid_rsa_private));
			return;
		}
	
		/*
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * orderInfo 的获取必须来自服务端；
		 */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		final String orderInfo = orderParam + "&" + sign;
		
		final Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayDemoActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * 支付宝账户授权业务示例
	 */
	public void authV2(View v) {
		if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
				|| (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
				|| TextUtils.isEmpty(TARGET_ID)) {
			showAlert(this, getString(R.string.error_auth_missing_partner_appid_rsa_private_target_id));
			return;
		}

		/*
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
		 * 
		 * authInfo 的获取必须来自服务端；
		 */
		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		
		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
		final String authInfo = info + "&" + sign;
		Runnable authRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造AuthTask 对象
				AuthTask authTask = new AuthTask(PayDemoActivity.this);
				// 调用授权接口，获取授权结果
				Map<String, String> result = authTask.authV2(authInfo, true);

				Message msg = new Message();
				msg.what = SDK_AUTH_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread authThread = new Thread(authRunnable);
		authThread.start();
	}
	
	/**
	 * 获取支付宝 SDK 版本号。
	 */
	public void showSdkVersion(View v) {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		showAlert(this, getString(R.string.alipay_sdk_version_is) + version);
	}

	/**
	 * 将 H5 网页版支付转换成支付宝 App 支付的示例
	 */
	public void h5Pay(View v) {
		WebView.setWebContentsDebuggingEnabled(true);
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();

		/*
		 * URL 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
		 *
		 * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
		 * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
		 * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
		 * 进行测试。
		 * 
		 * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
		 * 可以参考它实现自定义的 URL 拦截逻辑。
		 */
		String url = "https://m.taobao.com";
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

	private static void showAlert(Context ctx, String info) {
		showAlert(ctx, info, null);
	}

	private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
		new AlertDialog.Builder(ctx)
				.setMessage(info)
				.setPositiveButton(R.string.confirm, null)
				.setOnDismissListener(onDismiss)
				.show();
	}

	private static void showToast(Context ctx, String msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
	}

	private static String bundleToString(Bundle bundle) {
		if (bundle == null) {
			return "null";
		}
		final StringBuilder sb = new StringBuilder();
		for (String key: bundle.keySet()) {
			sb.append(key).append("=>").append(bundle.get(key)).append("\n");
		}
		return sb.toString();
	}
}
