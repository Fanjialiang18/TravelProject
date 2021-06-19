package clearlove3.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
@Component
public final class UuidUtil {
	private UuidUtil(){}
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
//		return UUID.randomUUID().toString();
	}
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
		System.out.println(UuidUtil.getUuid());
	}
}
