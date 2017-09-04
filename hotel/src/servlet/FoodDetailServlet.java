package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.IFoodDetailService;
import service.IFoodTypeService;
import entity.FoodDetail;
import factory.BeanFactory;

public class FoodDetailServlet extends HttpServlet {

	//����service
		private IFoodDetailService foodDetailService=BeanFactory.getInstance("foodDetailService", IFoodDetailService.class);
		//��ת��Դ
		private Object uri;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method=request.getParameter("method");
		if("list".equals(method))
		{
			try {
				list(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("addFood".equals(method))
		{
			try {
				addFood(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else if("viewUpdate".equals(method))
		{
			try {
				viewUpdate(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("delete".equals(method))
		{
			delete(request,response);
		}else if("update".equals(method))
		{
			update(request,response);
		}
		
		
	}

	//e.����2
	private void update(HttpServletRequest request,
			HttpServletResponse response)
	{
		try {
			FoodDetail food=new FoodDetail();
			upload(request,food);
			
			foodDetailService.update(food);
			uri=request.getRequestDispatcher("/foodDetail?method=list");
			//uri="foodDetail?method=list";
		}catch(Exception e)
		{
			e.printStackTrace();uri="/error/error.jsp";
		}
		try {
			goTo(request, response, uri);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//d.ɾ��
	private void delete(HttpServletRequest request,
			HttpServletResponse response)
	{
		try {
			String id=request.getParameter("id");
			foodDetailService.delete(Integer.parseInt(id));
			uri=request.getRequestDispatcher("/foodDetail?method=list");
			//uri="foodDetail?method=list";
		} catch (NumberFormatException e) {
			uri="/error/error.jsp";
		}
		try {
			goTo(request, response, uri);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//c.����
	private void viewUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException
	{
		try {
			String id=request.getParameter("id");
			FoodDetail food=foodDetailService.findById(Integer.parseInt(id));
			//�õ��Ĳ�Ʒ����
			request.setAttribute("food", food);
			
			uri=request.getRequestDispatcher("/sys/detail/updateFood.jsp");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
		
		
	}

	
	//b.����
	private void addFood(HttpServletRequest request,
			HttpServletResponse response) throws Throwable, Exception {
		try {
			//ȥtm���ļ��ϴ�������һ�����գ�������
			FoodDetail food=new FoodDetail();
			upload(request,food);
			//����service����
			foodDetailService.save(food);
			//��ת
			uri=request.getRequestDispatcher("/foodDetail?method=list");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		
		goTo(request, response, uri);
		
	}
	public void upload(HttpServletRequest request,FoodDetail food) {
		//1.�����ļ��ϴ�������
		FileItemFactory fac=new DiskFileItemFactory();
		//2.�����ļ��ϴ����������
		ServletFileUpload upload=new ServletFileUpload(/*fac*/);
		upload.setFileItemFactory(fac);
		
		//[һ.���õ����ļ����30]
		upload.setFileSizeMax(30*1024*1024);
		//[��.�ļ��ܴ�С]
		upload.setSizeMax(50*1024*1024);
		
		//�ж�,���Ƿ�Ϊ�ļ��ϴ���
		if(upload.isMultipartContent(request))
		{
			try{
				//3.����������תΪFileItem����
				List<FileItem> list=upload.parseRequest(request);
				
				//����FIleItem
				
				for(FileItem item:list)
				{
					
					//�ж���ͨ��Ԫ�ػ����ļ�Ԫ��
					if(item.isFormField())
					{
						//��ȡԪ������
						String name=item.getFieldName();
						//��ȡֵ
						String value=item.getString("UTF-8");//��������
						
						
						if("foodName".equals(name))
						food.setFoodName(value);
						else if("foodType_id".equals(name))
						food.setFoodType_id(Integer.parseInt(value));
						else if("price".equals(name))
						food.setPrice(Double.parseDouble(value));
						else if("mprice".equals(name))
						food.setMprice(Double.parseDouble(value));
						else if("remark".equals(name))
						food.setRemark(value);
						else if(!"".equals(value.trim())&&"id".equals(name))
							food.setId(Integer.parseInt(value));
						
					}else
					{
						//��ȡ�ļ���
						String name=item.getName();
						//System.out.println(item.getName());
						//System.out.println(new String(item.getName().getBytes("ISO8859-1"),"gbk"));
						//String name=new String(item.getName().getBytes("ISO8859-1"),"UTF-8");
						//��ȡ�ϴ�·��,"/"����ǰ��Ŀ��
						String basePath=getServletContext().getRealPath("/sys/style/images")+name;
						food.setImg(name);
						//�����ļ��ϴ�
						
						File file=new File(basePath);
						item.write(file);//д�ļ�
						item.delete();//ɾ����ʱ�ļ�
					}
					
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else{
			System.out.println("������");
		}
	}

	//a.�б�չʾ
	public void list(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		try {
			//1.��ȡ��Ʒ������Ϣ
			List<FoodDetail> list=foodDetailService.getAll();
			//���浽����
			request.setAttribute("listFood", list);
			//ת��
			uri=request.getRequestDispatcher("/sys/detail/foodList.jsp");
		} catch (Exception e) {
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void goTo(HttpServletRequest request, HttpServletResponse response,Object uri)
			throws ServletException, IOException {
		if(uri instanceof RequestDispatcher){
			((RequestDispatcher) uri).forward(request, response);
		}else if(uri instanceof String )
		{
			response.sendRedirect(request.getContextPath()+uri);
		}
	}
}
