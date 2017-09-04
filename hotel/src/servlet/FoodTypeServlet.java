package servlet;
/*
 * 4.菜系管理Servlet开发
 * a.添加菜系
 * b.菜系列表展示
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.BaseServlet;
import utils.WebUtils;
import entity.FoodType;

public class FoodTypeServlet extends BaseServlet {

	//调用service
	
	/*
	//跳转资源
	private Object uri;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=request.getParameter("method");
		if("addFood".equals(method))
		{
			//添加
			try {
				addFoodType(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("list".equals(method)){
			//列表展示
			System.out.println("i am here ");
			try {
				list(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("viewUpdate".equals(method))
		{
			//进入更新页面
			viewUpdate(request,response);
		}else if("delete".equals(method))
		{
			//删除
			delete(request,response);
			
		}else if("update".equals(method))
		{
			//更新操作
			try {
				update(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}*/
	

	//a.添加菜系
	protected Object addFoodType(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		Object uri=null;
		
			 
		//1.获取请求数据封装
		String foodTypeName=request.getParameter("foodTypeName");
		FoodType ft=new FoodType();
		ft.setTypeName(foodTypeName);
		
		//2.调用service处理业务逻辑
		foodTypeService.save(ft);
		
		//3.跳转，成功就列表展示servlet
		uri=request.getRequestDispatcher("/foodType?method=list");
		
		//request.getRequestDispatcher(uri).forward(request, response);
		//WebUtils.goTo(request, response,uri);
		return uri;
	}



	
	//b.菜系列表展示
	protected Object list(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		Object uri=null;
		
		List<FoodType> list=foodTypeService.getAll();
		//保存
		request.setAttribute("listFoodType", list);
		//路径
		uri=request.getRequestDispatcher("sys/type/foodType_list.jsp");
	
		return uri;
	
	}
	
	//c.更新
	protected Object viewUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
	
		//1.获取请求id
		String id=request.getParameter("id");
		//2.根据id查询对象
		FoodType ft=foodTypeService.findById(Integer.parseInt(id));
		//3.保存
		request.setAttribute("foodType", ft);
		
		//4.跳转
		uri=request.getRequestDispatcher("/sys/type/foodType_update.jsp");

		//request.getRequestDispatcher(uri).forward(request, response);
		//WebUtils.goTo(request, response,uri);
		return uri;
	}
	
	
	//d.删除
	protected Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
	
			//1.获取请求id
			String id=request.getParameter("id");
			//2.调用Service
			foodTypeService.delete(Integer.parseInt(id));
			//3.跳转
			uri="/foodType?method=list";
		
		//response.sendRedirect(request.getContextPath()+uri);
		//WebUtils.goTo(request, response,uri);
		return uri;
		
		
	}
	
	
	//e.更新
	protected Object update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object uri=null;
		//调用请求数据封装
	
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("foodTypeName");
		FoodType foodType=new FoodType();
		foodType.setId(id);
		foodType.setTypeName(name);
		//2.调用service更新
		foodTypeService.update(foodType);
		//3.跳转
		uri="/foodType?method=list";
	
		//response.sendRedirect(request.getContextPath()+uri);
		return uri;
		
	}

}
