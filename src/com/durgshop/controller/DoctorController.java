package com.durgshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.durgshop.entity.Doctor;
import com.durgshop.entity.Pager;
import com.durgshop.entity.Result;
import com.durgshop.service.DoctorService;
import com.durgshop.util.PasswordUtils;
/**
 * 创建时间2020/7/12 
 * @author TonyWang
 *
 */
@Controller
@RequestMapping("sys/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@RequestMapping("list")
	@ResponseBody
	public Pager<Doctor> findList(Doctor doctor, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		Pager<Doctor> pager = new Pager<>(page, size);
		pager.setCondition(doctor);
		List<Doctor> list = doctorService.findByPager(pager);
		int total = doctorService.findTotalByPager(pager);
		pager.setTotal(total);
		pager.setRows(list);

		// 返回的对象网页或浏览器 不能识别，需要转成json字符串；使用jackson进行转换
		return pager;
	}
	/**
	 *
	 * @param goods 搜索参数
	 * @param page  页面
	 * @param size  分页大小
	 * 
	 */

	/**
	 * <pre>
	 *   springmvc接收参数的方法： 
	 *   1.可以使用方法的参数进行接收用户传递的数据：url参数或表单参数，或json  (方法的参数名与URL参数名对应，如果不对应的可以使用@RequestParam进行指定)
	 *   2.可以使用对象进行参数接收：对象的属性名与参数名对应
	 *   3.可以使用数组或集合进行参数接收：
	 * 
	 * </pre>
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result add(Doctor doctor) {
		Result result = new Result(false, "新增失败");
		 
		System.out.println(doctor);
		try {
			if (doctor.getPassword() != null && doctor.getPassword().trim().length() > 0) {
				String salt = PasswordUtils.getSalt();
				System.out.println("slat"+salt);
				String encString = PasswordUtils.encode(doctor.getPassword(), salt);
				System.out.println("encString"+encString);
				doctor.setSalt(salt);
				doctor.setPassword(encString);
			}
			System.out.println(doctor);
			boolean ret = doctorService.add(doctor);
			if (ret) {
				result = new Result(true, "新增成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("edit")
	@ResponseBody
	public Result edit(Doctor doctor,HttpServletRequest request) {
		String oldpass = request.getParameter("opassword");
		String newpass = request.getParameter("newpassword");
		String passString=request.getParameter("password");
		Result result = new Result(false, "修改失败");
		System.out.println("=="+doctor+"--");
		System.out.println("旧密码"+oldpass+"新密码"+newpass);
		Doctor login = doctorService.login(doctor);
		if (login != null) {
			// 盐-从数据库查询除出新增或修改时的盐 
			String salt = login.getSalt(); 
			//用户登录输入的明文 
			String pass= oldpass; 
			//重新加密得到密文
			String encString = PasswordUtils.encode(pass, salt); 
			//密文
			String userPass=login.getPassword();
			if(passString.equals(newpass)) {
				if(encString.equals(userPass)) {
					try {
						if (doctor.getPassword() != null && doctor.getPassword().trim().length() > 0) {
							String salt2 = PasswordUtils.getSalt();
							String encString2 = PasswordUtils.encode(doctor.getPassword(), salt2);
							doctor.setSalt(salt2);
							doctor.setPassword(encString2);
						}
						boolean ret = doctorService.edit(doctor);
						if (ret) {
							result = new Result(true, "修改成功");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else {
					result = new Result(false, "原密码错误！");	
				}
			}
		}else{
			result = new Result(false, "请确认新密码与确认密码是否一致？");
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(Doctor doctor) {
		Result result = new Result(false, "修改失败");
		try {
			if (doctor.getPassword() != null && doctor.getPassword().trim().length() > 0) {
				String salt = PasswordUtils.getSalt();
				String encString = PasswordUtils.encode(doctor.getPassword(), salt);
				doctor.setSalt(salt);
				doctor.setPassword(encString);
				}
				boolean ret = doctorService.edit(doctor);
				if (ret) {
					result = new Result(true, "修改成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Result delete(Doctor doctor) {
		Result result = new Result(false, "删除失败");
		try {
			boolean ret = doctorService.delete(doctor);
			if (ret) {
				result = new Result(true, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("login")
	@ResponseBody
	public Result login(Doctor doctor, String validate, HttpSession session) {
		Result result = new Result(false, "登录失败");
		try {
			// 1.先验证图形验证码，不通过则返回
			String rand = (String) session.getAttribute("rand");
			if (validate == null || !validate.equalsIgnoreCase(rand)) {
				// 验证失败
				result = new Result(false, "验证码验证失败");
				return result;
			}

			// 2.通过图形验证码验证后，再进行数据库验证账号
			Doctor loginDoctor = doctorService.login(doctor);
			if (loginDoctor != null) {
				
				// 盐-从数据库查询除出新增或修改时的盐 
				String salt = loginDoctor.getSalt(); 
				//用户登录输入的明文 
				String pass= doctor.getPassword(); 
				
				//重新加密得到密文
				String encString = PasswordUtils.encode(pass, salt); 
				//密文
				String userPass=loginDoctor.getPassword();
				
				if(encString.equals(userPass)) {
					result = new Result(true, "登录成功");
					// 理解什么是会话跟踪？如何进行会话跟踪
					// 1.在浏览器请求到达服务器后，需要保存相关数据到会话对象中
					// 2.在其它请求中，可以使用会话对象获取相关数据
					// 3.会话使用key/value管理数据，会话有生命周期，可能会超时(默认为30分钟)
					session.setAttribute("LOGIN_DOCTOR", loginDoctor);
					
				}else {
					result = new Result(false, "密码错误");
					System.out.println("密码错误");
				}
				
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		// 1.退出系统时删除会话对象关联的数据
		// session.removeAttribute("LOGIN_USER");
		// 2.设置session活动时间为1秒钟，使session快速失效
		// session.setMaxInactiveInterval(1);
		// 3.直接调用失效方法使session失效
		session.invalidate();

		// 服务器端的跳转技术有两种：请求转发 和 重定向
		// 请求转发：是在同一次请求中，在同一服务器中，进行转发到其它页面或组件
		// 重定向：发生了两次请求，第一次请求得到一个网址，第二次请求得到数据；可以在不同的服务器间重定向
		// spring mvc默认使用的是请求转发，默认会查询视图解析器中的页面
		return "redirect:/login.jsp";
	}

}
