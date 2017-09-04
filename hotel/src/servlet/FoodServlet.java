package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Condition;
import utils.PageBean;
import entity.DinnerTable;
import entity.FoodDetail;
import entity.FoodType;


public class FoodServlet extends BaseServlet {
	
	//进入主页显示菜品以及菜系
	protected Object indexDetail(HttpServletRequest request,HttpServletResponse response)
	{
		Object uri=null;
		//1.1接受餐桌id，根据id查询,再把查到的结果保存到session(生成订单yong)
		
		
		HttpSession session=request.getSession();
		if(session.getAttribute("dinnerTable")==null)
		{
			
			String tableId=request.getParameter("tableId");
			DinnerTable dt=dinnerService.findByid(Integer.parseInt(tableId));
			//保存到session
			request.getSession().setAttribute("dinnerTable", dt);
		}
		//1.2查询所有的菜品信息，并且保存
		PageBean<FoodDetail> pb=new PageBean<FoodDetail>();
		//第一次访问
		String curPage=request.getParameter("currentPage");
		//判断
		if(curPage==null||"".equals(curPage.trim())||"0".equals(curPage))
		{
			pb.setCurrentPage(1);
		}else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		//条件对象
		Condition condition=new Condition();
		String foodTypeId=request.getParameter("foodTypeId");
		if(foodTypeId!=null)
		{
		condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		//分页参数
		String foodName=request.getParameter("foodName");
		//设置菜品参数
		condition.setFoodName(foodName);
		pb.setCondition(condition);
		
		//执行分页查询
		foodService.getAll(pb);
		//保存查询后的pb对象
		request.setAttribute("pb", pb);
		
		//1.3查询所有菜系信息，保存
		List<FoodType> listFoodType=foodTypeService.getAll();
		request.setAttribute("listFoodType", listFoodType);
		//跳转
		
		return request.getRequestDispatcher("/app/caidan.jsp");
	}
}
