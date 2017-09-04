package servlet;
/*
 * 4.��ϵ����Servlet����
 * a.��Ӳ�ϵ
 * b.��ϵ�б�չʾ
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

	//����service
	
	/*
	//��ת��Դ
	private Object uri;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=request.getParameter("method");
		if("addFood".equals(method))
		{
			//���
			try {
				addFoodType(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("list".equals(method)){
			//�б�չʾ
			System.out.println("i am here ");
			try {
				list(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("viewUpdate".equals(method))
		{
			//�������ҳ��
			viewUpdate(request,response);
		}else if("delete".equals(method))
		{
			//ɾ��
			delete(request,response);
			
		}else if("update".equals(method))
		{
			//���²���
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
	

	//a.��Ӳ�ϵ
	protected Object addFoodType(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		Object uri=null;
		
			 
		//1.��ȡ�������ݷ�װ
		String foodTypeName=request.getParameter("foodTypeName");
		FoodType ft=new FoodType();
		ft.setTypeName(foodTypeName);
		
		//2.����service����ҵ���߼�
		foodTypeService.save(ft);
		
		//3.��ת���ɹ����б�չʾservlet
		uri=request.getRequestDispatcher("/foodType?method=list");
		
		//request.getRequestDispatcher(uri).forward(request, response);
		//WebUtils.goTo(request, response,uri);
		return uri;
	}



	
	//b.��ϵ�б�չʾ
	protected Object list(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		Object uri=null;
		
		List<FoodType> list=foodTypeService.getAll();
		//����
		request.setAttribute("listFoodType", list);
		//·��
		uri=request.getRequestDispatcher("sys/type/foodType_list.jsp");
	
		return uri;
	
	}
	
	//c.����
	protected Object viewUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
	
		//1.��ȡ����id
		String id=request.getParameter("id");
		//2.����id��ѯ����
		FoodType ft=foodTypeService.findById(Integer.parseInt(id));
		//3.����
		request.setAttribute("foodType", ft);
		
		//4.��ת
		uri=request.getRequestDispatcher("/sys/type/foodType_update.jsp");

		//request.getRequestDispatcher(uri).forward(request, response);
		//WebUtils.goTo(request, response,uri);
		return uri;
	}
	
	
	//d.ɾ��
	protected Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
	
			//1.��ȡ����id
			String id=request.getParameter("id");
			//2.����Service
			foodTypeService.delete(Integer.parseInt(id));
			//3.��ת
			uri="/foodType?method=list";
		
		//response.sendRedirect(request.getContextPath()+uri);
		//WebUtils.goTo(request, response,uri);
		return uri;
		
		
	}
	
	
	//e.����
	protected Object update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object uri=null;
		//�����������ݷ�װ
	
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("foodTypeName");
		FoodType foodType=new FoodType();
		foodType.setId(id);
		foodType.setTypeName(name);
		//2.����service����
		foodTypeService.update(foodType);
		//3.��ת
		uri="/foodType?method=list";
	
		//response.sendRedirect(request.getContextPath()+uri);
		return uri;
		
	}

}
