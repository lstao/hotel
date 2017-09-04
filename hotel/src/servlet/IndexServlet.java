package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.WebUtils;
import entity.DinnerTable;

public class IndexServlet extends BaseServlet {
	 /*
	 
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	//获取操作类型
	String method=request.getParameter("method");
	if(method==null)
	{
		method="listTable";
	}
	if("listTable".equals(method))
	{
		//（前台首页）显示所有未预定的餐桌
		try {
			listTable(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if("indexDetail".equals(method))
	{
		
	}
	*/
	//显示餐桌
	protected Object listTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		//保存跳转
		Object uri=null;
		//查询所有未预定餐桌
		List<DinnerTable> list=dinnerService.findNoUseTable();
		request.setAttribute("listDinnerTable", list);
		uri=request.getRequestDispatcher("/app/index.jsp");
		return uri;
	}

	//详细菜品
	protected Object indexDetail(HttpServletRequest request,
			HttpServletResponse response)
	{
		Object uri=null;
		
		return uri;
		
	}
	


	

}
