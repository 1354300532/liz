package com.jk.controller.book;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jk.entity.book.Book;
import com.jk.entity.book.UserRequest;
import com.jk.entity.book.UserResponse;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;
import com.jk.service.book.BookService;

import common.interceptor.base.MySessionContext;
@Controller
public class BookController {
	
	@Resource
	private BookService bookService;
	
	/**
	 * <pre>selectBookList(查询book数据)   
	 * 创建人：李智     
	 * 创建时间：2017年7月14日 下午5:22:33    
	 * 修改人：李智       
	 * 修改时间：2017年7月14日 下午5:22:33    
	 * 修改备注： 
	 * @param book
	 * @return</pre>
	 */
	  @RequestMapping({"selectBookList"})
	  public ModelAndView selectBookList(Book book) {
	    List boList = this.bookService.selectBookList(book);
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("list", boList);
	    mv.setViewName("book/list");
	    return mv;
	  }
	  
	  	@RequestMapping("selectUserListJson")
		@ResponseBody
		Map<String, Object> selectUserListJson(String pageNumber, UserRequest userRequest,Book book) {
			System.out.println("hello world");
			
			//查询总条数
			int totalCount = bookService.selectUserCount(userRequest);
			userRequest.setTotalCount(totalCount);
			if (null == pageNumber || "".equals(pageNumber.trim())) {
				pageNumber = "1";
			}
			userRequest.setPageIndex(Integer.valueOf(pageNumber));
			//计算分页信息
			userRequest.calculate();
			//查询列表
			List<UserResponse> userList = bookService.selectBookList(book);
			//封装结果
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", totalCount);
			map.put("rows", userList);
			return map;
		}
	  /**
		 * 进入添加界面
		 * @return
		 */
		@RequestMapping("toAddBook")
		public String toAddBook(){
			return "addBook";
		}
	  
		  /**
			 * 添加提交
			 * @return
			 */
		
	  @RequestMapping({"insertBook"})
	  public String insertBook(Book book) {
		  if(book.getBookID()!=null){
				bookService.updatebook(book);
			}else{
				bookService.insertBook(book);
			}
		
	    return "redirect:selectBookList.jk";
	  }
	  
	  /**
		 * 单条删除
		 * @return
		 */
	  
	  @RequestMapping({"deleteBook"})
	 	public String deleteBook(Integer bookID){
	 		this.bookService.deleteBook(bookID);
	 		return "redirect:selectBookList.jk";
	 	}
	  
	  /**
		 * 去修改页面
		 * @return
		 */
	  
		@RequestMapping({"querytoUpdateBook"})
		public ModelAndView querytoUpdateBook(Book book){
			Book boList = bookService.querytoUpdateBook(book.getBookID());
			ModelAndView view = new ModelAndView();
			view.addObject("book", boList);
			view.setViewName("addBook");
			return view;
		}

		  /**
		 * 图片
		 * @return
		 */

  @RequestMapping("uploadPhoto") 
	public void uploadPhoto(Book book,@RequestParam(value="file",required=false) CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException{
		   String realPath = request.getSession().getServletContext().getRealPath("upImg");
	        
	        File fileCheck = new File(realPath);
			if(!fileCheck.exists()){ //判断文件是否存在
				fileCheck.mkdir(); //根据文件的路径  创建文件夹
			}
			//重命名文件名  保证文件的唯一性
			String imgUrl = "\\"+UUID.randomUUID().toString()+file.getOriginalFilename();
			
			String url = "upImg"+imgUrl;
			
			book.setBookPhoto(url);;
	        String path=realPath+imgUrl;
	        System.out.println(path);
	        File newFile=new File(path);
	        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
	        try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        PrintWriter writer = response.getWriter();
	        writer.println(url);
	        
	}
  	
  
	@RequestMapping("login")
	@ResponseBody
	Map<String, Object> login(UserRequest userRequest, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object codeObj = session.getAttribute("imageCode");
		if (null == codeObj) {
			codeObj = "";
		}
		String code = codeObj.toString();
		userRequest.setSysImgCode(code);
		Map<String, Object> map = bookService.login(userRequest);
		Object userInfo = map.get("userInfo");
		if (null != userInfo) {
			UserResponse userResponse = (UserResponse) userInfo;
			//用户信息放进session中
			session.setAttribute("userInfo", userInfo);
			//设置session过期时间(单位：秒)
			session.setMaxInactiveInterval(600);
			
			String userID = userResponse.getUserID() + "";
			MySessionContext.removeSession(userID);
			MySessionContext.addSession(userID, session);
		}
		return map;
	}
	
	@RequestMapping("logout")
	String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:../login.jsp";
	}
	
	//序列化 
    private byte[] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    private Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping("toUserListPage")
	String toUserListPage() {
		
		return "book/userlist";
	}
    
	@RequestMapping("toUserRolePage")
	String toUserRolePage(ModelMap mm, 	Book book) {
		mm.addAttribute("bookID", book.getBookID());
		return "book/user_role";
	}
	
	@RequestMapping("selectUserRoleListJson")
	@ResponseBody
	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		List<RoleResponse> roleList = bookService.selectUserRoleListJson(roleRequest);
		return roleList;
	}
	
	@RequestMapping("insertUserRoleList")
	@ResponseBody
	String insertUserRoleList(@RequestBody List<RoleRequest> roleRequestList) {
		bookService.insertUserRoleList(roleRequestList);
		return "{}";
	}
	
	@RequestMapping("selectTreeListJson")
	@ResponseBody
	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		List<Map<String, Object>> treeList = bookService.selectTreeListJson(menuRequest);
		return treeList;
	}
}
