package com.lyd.utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class SendMessageUtils {

	public SendMessageUtils() {
		// TODO Auto-generated constructor stub
	}
	public static  void send(String telNum,String name,String time){
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		final String accessKeyId = "LTAIRAqi5kfa7fl7";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "l7uz9nEtQ0EfIETIRBwqLZl4Icz98i";//你的accessKeySecret，参考本文档步骤2
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			 SendSmsRequest request = new SendSmsRequest();
			 request.setMethod(MethodType.POST);
			 request.setPhoneNumbers(telNum);
			 request.setSignName( "叫我小年轻");
			 request.setTemplateCode("SMS_117285605");
			 request.setTemplateParam("{\"name\":\""+name+"\",\"time\":\""+time+"\"}");
			 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			 	
			SendSmsResponse sendSmsResponse =acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null &&sendSmsResponse.getCode().equals("OK")){
					System.out.println("发送成功");
				}else{
					System.out.println("发送失败");
				}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
