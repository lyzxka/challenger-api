package io.renren.app.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ToolUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(ToolUtil.class);


	/**
	 *
	 * @param paramStr 输入需要加密的字符串
	 * @return
	 */
	public static String entryptPassword(String paramStr,String salt) {
		if (StringUtils.isNotEmpty(paramStr)) {
			byte[] saltStr = Encodes.decodeHex(salt);
			byte[] hashPassword = Digests.sha1(paramStr.getBytes(), saltStr, Constants.HASH_INTERATIONS);
			String password = Encodes.encodeHex(hashPassword);
			return password;
		} else {
			return null;
		}
	}
	/**
	 * 加签
	 * @param sortedMap 待签名的map
	 * @return
	 */
	public static String validateMd5(SortedMap<String, String> sortedMap, String key){
		try {
			String str = mapToStringAndTrim(sortedMap)+"&key="+key;
			System.out.println("-加签字符串-"+str);
			String mac = Md5Utils.md5(str,"utf-8").toUpperCase();
			return mac;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 拼接字符串
	 * @param sortedMap
	 * @return
	 */
	public static String mapToStringAndTrim(SortedMap<String, String> sortedMap){
		StringBuffer sb = new StringBuffer();
		Iterator it =	sortedMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String key = entry.getKey().toString().trim();
			if(entry.getValue()==null){
				continue;
			}
			String value = entry.getValue().toString().trim();
			if (!"".equals(value) && value!=null) {
				sb.append(key+"="+value+"&");
			}
		}
		return sb.substring(0,sb.length()-1);
	}
	/**
	 * url 转Map
	 * @param paramStr
	 * @return
	 */
	public static SortedMap<String, String> paramToMap(String paramStr) {
		String[] params = paramStr.split("&");
		SortedMap<String, String> resMap = new TreeMap<String,String>();
		for (int i = 0; i < params.length; i++) {
			String[] param = params[i].split("=");
			if (param.length >= 2) {
				String key = param[0];
				String value = param[1];
				for (int j = 2; j < param.length; j++) {
					value += "=" + param[j];
				}
				resMap.put(key, value);
			}
		}
		return resMap;
	}

	public static SortedMap<String, String> getJsonMap(HttpServletRequest request){
		InputStream inputStream;
		String json = "";
		//获得响应流，获得输入对象
		try {
			inputStream = request.getInputStream();
			//建立接收流缓冲，准备处理
			StringBuffer requestBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			//读入流，并转换成字符串
			String readLine;
			while ((readLine = reader.readLine()) != null) {
				requestBuffer.append(readLine);
			}
			reader.close();
			json = requestBuffer.toString();
		} catch (Exception e) {
			//logger.error("接收同步消息失败"+e);
		}
		LOGGER.info("接受到参数:"+json);
		SortedMap<String, String> respMap = null;
		try {
			respMap = new TreeMap();
			JSONObject jsonObject = JSONUtil.parseObj(json);
			for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
				respMap.put(stringObjectEntry.getKey(),stringObjectEntry.getValue().toString());
			}
		} catch (Exception e) {
			LOGGER.info("获取url参数格式");
			respMap = ToolUtil.paramToMap(json);
		}
		LOGGER.info("转为map:"+ net.sf.json.JSONObject.fromObject(respMap));
		return respMap;
	}
}
