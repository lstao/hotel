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
	//��ȡ��������
	String method=request.getParameter("method");
	if(method==null)
	{
		method="listTable";
	}
	if("listTable".equals(method))
	{
		//��ǰ̨��ҳ����ʾ����δԤ���Ĳ���
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
	//��ʾ����
	protected Object listTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		//������ת
		Object uri=null;
		//��ѯ����δԤ������
		List<DinnerTable> list=dinnerService.findNoUseTable();
		request.setAttribute("listDinnerTable", list);
		uri=request.getRequestDispatcher("/app/index.jsp");
		return uri;
	}

	//��ϸ��Ʒ
	protected Object indexDetail(HttpServletRequest request,
			HttpServletResponse response)
	{
		Object uri=null;
		
		return uri;
		
	}
	


	

}
