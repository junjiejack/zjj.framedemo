package com.zjj.framedemo;

import com.zjj.framedemo.utils.MD5Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class AppInterfaceTest {
	
	// 测试配置信息 & 很重要
	public final static String ENCRYPT_VAL = "123456@HT"; // MD5加密的值
	public final static String CHANNELCODE = "100053"; // 渠道编码
	
	/**
	 * 
	 * @param toURL
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String requestService(String toURL,String data) throws Exception {
		StringBuffer bs = new StringBuffer();
			URL url = new URL(toURL);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setRequestMethod("POST");
			urlcon.setUseCaches(false);
			urlcon.setConnectTimeout(30000);
			urlcon.setReadTimeout(30000);
			urlcon.setDoInput(true);
			urlcon.setDoOutput(true);
			urlcon.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStream out = urlcon.getOutputStream();
			out.write(data.getBytes("UTF-8"));
			out.flush();
			out.close();
			urlcon.connect();
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(is));

			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
		return bs.toString();
	}

	public void testPolicyJsonBean() throws Exception{ // 投保用例
		// ********************** 测试须知，根据不同的环境 使用 配置信息、报文、地址 *****************************
		
		// 测试报文
		String insureJson = "{\"channelObject\":{\"bizCode\":\"101\",\"channelCode\":\"10053\",\"channelName\":\"聚保物流测试\",\"orderId\":\"10000000000000000000000000000000000000000000000001\",\"createTime\":\"2017-12-15 14:24:13\"},\"insuranceObject\":{\"insuranceCode\":\"3632\",\"insuranceName\":\"无车承运人责任保险（单程）\",\"plan\":\"A\",\"prmCur\":\"01\",\"premium\":\"135.00\",\"amtCur\":\"01\",\"amount\":\"10000.0\",\"rate\":\"1.35\",\"effectiveTime\":\"2017-12-16 14:24:13\",\"terminalTime\":\"2018-01-16 14:24:14\",\"copy\":\"1\",\"docType\":\"\",\"docSN\":\"\"},\"appntObject\":{\"appName\":\"lisea\",\"appType\":\"1\",\"appBirthday\":\"1980-02-26\",\"appEmail\":\"cnlisea@126.com\",\"appGender\":\"1\",\"appIDType\":\"01\",\"appNumber\":\"420281198012318875\",\"appTelNumber\":\"18627819097\",\"appAddr\":\"光谷创意大厦B座723\",\"appContact\":\"\",\"isTaxInvoice\":\"0\",\"taxCertifi\":\"\",\"depositBank\":\"\",\"bankAccount\":\"\"},\"insuredObject\":[{\"insuredName\":\"lihai\",\"insuredType\":\"1\",\"insuredBirthday\":\"1988-10-24\",\"insuredEmail\":\"\",\"insuredGender\":\"\",\"insuredIDType\":\"01\",\"insuredNumber\":\"420281198810248874\",\"insuredTelNumber\":\"18627819097\",\"insuredAddress\":\"\",\"relationship\":\"00\",\"isLegal\":\"1\",\"benefitDTOs\":[]}],\"definedSafeObj\":{\"isDefinedSafe\":\"0\",\"rdrDTOs\":[]},\"payObject\":{\"isSinglePay\":\"0\",\"payMode\":\"0\",\"payDate\":\"0001-01-01 00:00:00\",\"payBankNo\":\"\"},\"agreementObject\":{\"policyDeductible\":\"无免赔\",\"policySpec\":\"聚保无车承运人责任保险测试\"},\"productDiffObject\":{\"goodsName\":\"水果\",\"goodsNum\":\"100\",\"transTime\":\"2017-12-16 14:24:14\",\"arrivalTime\":\"2017-12-25 14:24:14\",\"transFrom\":\"湖北-武汉\",\"transDepot\":\"河南-郑州\",\"transTo\":\"山东-枣庄\",\"vehicleType\":\"0\",\"vehicleNum\":\"鄂XX4444\",\"vehicleFrameNum\":\"xxxx\",\"freightIncome\":\"3000.00\"}}";
		// 针对url传递特殊字符转译问题
		insureJson = insureJson.replaceAll("%","%25");
		
		String signature = MD5Util.MD5Encode(insureJson + ENCRYPT_VAL, "utf-8"); // 加密
		
		// 测试地址
	    String jsonStr = AppInterfaceTest.requestService("http://219.141.242.74:9039/service_platform/InsureInterface", "json="+insureJson+"&channelCode="+CHANNELCODE+"&signature="+signature);
		System.out.println("投保返回JSON串="+jsonStr);
	}
}
