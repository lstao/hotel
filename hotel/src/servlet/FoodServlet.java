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
	
	//������ҳ��ʾ��Ʒ�Լ���ϵ
	protected Object indexDetail(HttpServletRequest request,HttpServletResponse response)
	{
		Object uri=null;
		//1.1���ܲ���id������id��ѯ,�ٰѲ鵽�Ľ�����浽session(���ɶ���yong)
		
		
		HttpSession session=request.getSession();
		if(session.getAttribute("dinnerTable")==null)
		{
			
			String tableId=request.getParameter("tableId");
			DinnerTable dt=dinnerService.findByid(Integer.parseInt(tableId));
			//���浽session
			request.getSession().setAttribute("dinnerTable", dt);
		}
		//1.2��ѯ���еĲ�Ʒ��Ϣ�����ұ���
		PageBean<FoodDetail> pb=new PageBean<FoodDetail>();
		//��һ�η���
		String curPage=request.getParameter("currentPage");
		//�ж�
		if(curPage==null||"".equals(curPage.trim())||"0".equals(curPage))
		{
			pb.setCurrentPage(1);
		}else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		//��������
		Condition condition=new Condition();
		String foodTypeId=request.getParameter("foodTypeId");
		if(foodTypeId!=null)
		{
		condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		//��ҳ����
		String foodName=request.getParameter("foodName");
		//���ò�Ʒ����
		condition.setFoodName(foodName);
		pb.setCondition(condition);
		
		//ִ�з�ҳ��ѯ
		foodService.getAll(pb);
		//�����ѯ���pb����
		request.setAttribute("pb", pb);
		
		//1.3��ѯ���в�ϵ��Ϣ������
		List<FoodType> listFoodType=foodTypeService.getAll();
		request.setAttribute("listFoodType", listFoodType);
		//��ת
		
		return request.getRequestDispatcher("/app/caidan.jsp");
	}
}
