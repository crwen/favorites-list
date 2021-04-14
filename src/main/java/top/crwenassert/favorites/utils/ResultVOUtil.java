package top.crwenassert.favorites.utils;

import top.crwenassert.favorites.vo.Pagination;
import top.crwenassert.favorites.vo.ResultVO;

/**
 * ClassName: ResultVOUtil
 * Description:
 * date: 2020/2/5 23:09
 *
 * @author crwen
 * @create 2020-02-05-23:09
 * @since JDK 1.8
 */
public class ResultVOUtil {

	public static ResultVO success(Object object) {
		ResultVO resultVO = new ResultVO();
		resultVO.setData(object);
		resultVO.setCode(200);
		resultVO.setMsg("成功");
		return resultVO;
	}

	public static ResultVO success(Object object, Pagination pagination) {
		ResultVO resultVO = new ResultVO();
		resultVO.setData(object);
		resultVO.setCode(200);
		resultVO.setMsg("成功");
		resultVO.setPagination(pagination);
		return resultVO;
	}

	public static ResultVO success() {
		return success(null);
	}

	public static ResultVO error(Integer code, String msg) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		return resultVO;
	}

	public static ResultVO error(String msg) {
		return error(400, msg);
	}

	public static ResultVO loginError(String msg) {
		return error(401, msg);
	}
}
