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
 * 通用Servlet，希望所有servlet继承
 */
public abstract class BaseServlet extends HttpServlet{
	//创建service
	 protected DinnerTableService dinnerService=
			 BeanFactory.getInstance("dinnerTableService", DinnerTableService.class);
	 protected IFoodTypeService foodTypeService=BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	 protected IFoodDetailService foodDetailService=BeanFactory.getInstance("foodDetailService", IFoodDetailService.class);
		//菜品类别
	 protected IFoodService foodService=BeanFactory.getInstance("foodService", IFoodService.class);
	 //保存跳转资源（考虑并发问题，可能存在线程安全问题）
	//private Object uri;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 Object uri=null;
		 //获取操作类型；
		 String methodName=request.getParameter("method");
		 if(methodName==null)
			{
				methodName="listTable";
			}
		 try {
			//1.获取当前运行类字节码
			 Class clazz=this.getClass();
			 //2.获取当前执行的方法的method类型
			 Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			 //3.执行方法
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
