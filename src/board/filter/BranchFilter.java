//package board.filter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import board.beans.User;
//
//public class BranchFilter {
//	@WebFilter("/edit,/manage,/signup")
//	public class LoginCheckFilter implements Filter {
//
//		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//				throws IOException, ServletException{
//
//
//			String target = String.valueOf(((HttpServletRequest)request).getRequestURL());
//			//System.out.println(target);
//			HttpSession session =((HttpServletRequest)request).getSession();
//
//
//			if (!isLogined((HttpServletRequest)request)){
//				/* まだ認証されていない */
//
//				if(!target.equals("http://localhost:8080/BoardSystem/signup")){
//					//register errormessage;
//					List<String> messages = new ArrayList<String>();
//					messages.add("このURLは存在しません");
//
//					session.setAttribute("errorMessages", messages);
//
//					//request.getRequestDispatcher("./login.jsp").forward(request, response);
//					((HttpServletResponse)response).sendRedirect("./home");
//
//
//					return;
//				}
//			}
//			chain.doFilter(request, response);
//		}
//
//		public boolean isLogined(HttpServletRequest request){
//
//			User user = (User)request.getSession().getAttribute("loginUser");
//			if(user == null) return false;
//			else return true;
//		}
//
//		@Override
//		public void init(FilterConfig paramFilterConfig) throws ServletException {
//			// TODO 自動生成されたメソッド・スタブ
//
//		}
//
//		@Override
//		public void destroy() {
//			// TODO 自動生成されたメソッド・スタブ
//
//		}
//
//	}
//
//}
