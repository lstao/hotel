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

	//调用service
		private IFoodDetailService foodDetailService=BeanFactory.getInstance("foodDetailService", IFoodDetailService.class);
		//跳转资源
		private Object uri;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
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

	//e.更新2
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
	
	//d.删除
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
	
	//c.更新
	private void viewUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException
	{
		try {
			String id=request.getParameter("id");
			FoodDetail food=foodDetailService.findById(Integer.parseInt(id));
			//得到的菜品保存
			request.setAttribute("food", food);
			
			uri=request.getRequestDispatcher("/sys/detail/updateFood.jsp");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			uri="/error/error.jsp";
		}
		goTo(request, response, uri);
		
		
	}

	
	//b.增加
	private void addFood(HttpServletRequest request,
			HttpServletResponse response) throws Throwable, Exception {
		try {
			//去tm的文件上传表单还不一样了日，妈卖批
			FoodDetail food=new FoodDetail();
			upload(request,food);
			//调用service保存
			foodDetailService.save(food);
			//跳转
			uri=request.getRequestDispatcher("/foodDetail?method=list");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
		
		goTo(request, response, uri);
		
	}
	public void upload(HttpServletRequest request,FoodDetail food) {
		//1.创建文件上传工厂类
		FileItemFactory fac=new DiskFileItemFactory();
		//2.创建文件上传核心类对象
		ServletFileUpload upload=new ServletFileUpload(/*fac*/);
		upload.setFileItemFactory(fac);
		
		//[一.设置单个文件最大：30]
		upload.setFileSizeMax(30*1024*1024);
		//[二.文件总大小]
		upload.setSizeMax(50*1024*1024);
		
		//判断,表单是否为文件上传表单
		if(upload.isMultipartContent(request))
		{
			try{
				//3.把请求数据转为FileItem集合
				List<FileItem> list=upload.parseRequest(request);
				
				//遍历FIleItem
				
				for(FileItem item:list)
				{
					
					//判断普通表单元素还是文件元素
					if(item.isFormField())
					{
						//获取元素名称
						String name=item.getFieldName();
						//获取值
						String value=item.getString("UTF-8");//处理中文
						
						
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
						//获取文件名
						String name=item.getName();
						//System.out.println(item.getName());
						//System.out.println(new String(item.getName().getBytes("ISO8859-1"),"gbk"));
						//String name=new String(item.getName().getBytes("ISO8859-1"),"UTF-8");
						//获取上传路径,"/"代表当前项目下
						String basePath=getServletContext().getRealPath("/sys/style/images")+name;
						food.setImg(name);
						//处理文件上传
						
						File file=new File(basePath);
						item.write(file);//写文件
						item.delete();//删除临时文件
					}
					
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else{
			System.out.println("不处理");
		}
	}

	//a.列表展示
	public void list(HttpServletRequest request,HttpServletResponse response) throws Exception, IOException {
		try {
			//1.获取菜品所有信息
			List<FoodDetail> list=foodDetailService.getAll();
			//保存到域中
			request.setAttribute("listFood", list);
			//转发
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
