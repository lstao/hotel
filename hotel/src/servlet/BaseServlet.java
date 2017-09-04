package servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IFoodDetailService;
import service.IFoodService;
import service.IFoodTypeService;
import service.impl.DinnerTableService;
import utils.WebUtils;
import factory.BeanFactory;

/*
 * ͨ��Servlet��ϣ������servlet�̳�
 */
public abstract class BaseServlet extends HttpServlet{
	//����service
	 protected DinnerTableService dinnerService=
			 BeanFactory.getInstance("dinnerTableService", DinnerTableService.class);
	 protected IFoodTypeService foodTypeService=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	 protected IFoodDetailService foodDetailService=BeanFactory.getInstance("foodDetailService", IFoodDetailService.class);
		//��Ʒ���
	 protected IFoodService foodService=BeanFactory.getInstance("foodService", IFoodService.class);
	 //������ת��Դ�����ǲ������⣬���ܴ����̰߳�ȫ���⣩
	//private Object uri;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Object uri=null;
		 //��ȡ�������ͣ�
		 String methodName=request.getParameter("method");
		 if(methodName==null)
			{
				methodName="listTable";
			}
		 try {
			//1.��ȡ��ǰ�������ֽ���
			 Class clazz=this.getClass();
			 //2.��ȡ��ǰִ�еķ�����method����
			 Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			 //3.ִ�з���
			 uri=method.invoke(this, request,response);
			 
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uri="/error/error.jsp";
		} 
		 
		WebUtils.goTo(request, response, uri);
		 
		
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
