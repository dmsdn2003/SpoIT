package project;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class NewsController
 */
@WebServlet("/user.nhn")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private ServletContext ctx;
// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "project/login.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new UserDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		dao = new UserDAO();
		// 자바 리플렉션을 사용해 if, switch 없이 요청에 따라 구현 메서드가 실행되도록 함. 즉 action이름과 동일한 메서드를 호출
		// 리플렉션은 구체적인 클래스 타입을 모를때 사용
		Method m;
		String view = null;
		// action 파라미터 없이 접근한 경우
		if (action == null) {
			action = "login";
		}
		try {
			// 현재 클래스에서 action 이름과 HttpServletRequest 를 파라미터로 하는 메서드 찾음
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			// 메서드 실행후 리턴값 받아옴
			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			// 에러 로그를 남기고 view 를 로그인 화면으로 지정, 앞에서와 같이 redirection 사용도 가능.
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (view.startsWith("redirect:/")) {
			// redirect:/ 문자열 이후 경로만 가지고 옴
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			// 지정된 뷰로 포워딩, 포워딩시 컨텍스트경로는 필요없음.
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	public String signIn(HttpServletRequest request) {
		User u = new User();
		try {
			BeanUtils.populate(u, request.getParameterMap());
			dao.signIn(u);
			ctx.log("회원가입 완료");
			request.setAttribute("success", "회원가입이 정상적으로 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("회원가입 과정에서 문제 발생");
			request.setAttribute("error", "회원가입이 정상적으로 진행되지 않았습니다.");
			return "redirect:/jspbook/project/signIn.jsp";
		}
		return "redirect:/jspbook/project/signIn.jsp";
	}

	public String login(HttpServletRequest request) {
	    String userID = request.getParameter("userID");
	    String password = request.getParameter("userPassword"); // input name에 맞게

	    try {
	        // 로그인 시도: userID와 password로 User 객체 반환
	        User user = dao.login(userID, password);
	        if (user != null) {
	            // 로그인 성공, 세션에 저장
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            // 메인 페이지 등으로 이동
	            return "redirect:/jspbook/project/login.jsp";
	        } else {
	            // 로그인 실패: 에러 메시지 표시, 다시 로그인 폼으로
	            request.setAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
	            return "redirect:/jspbook/project/login.jsp";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("로그인 과정에서 문제 발생: " + e.getMessage());
	        request.setAttribute("error", "로그인 처리 중 오류가 발생했습니다.");
	        return "redirect:/jspbook/project/login.jsp";
	    }
	}
	
	public String delete(HttpServletRequest request) {
	    String userID = request.getParameter("userID");
	    String password = request.getParameter("password");

	    if (userID != null && !userID.trim().isEmpty()
	        && password != null && !password.trim().isEmpty()) {
	        try {
	            // 아이디/비밀번호 확인
	            User user = dao.login(userID, password);
	            if (user != null) {
	                // 삭제
	                dao.delUser(userID);
	                // 성공시 로그인 페이지로 리다이렉트
	                return "redirect:/jspbook/project/login.jsp";
	            } else {
	                // 아이디 또는 비밀번호가 틀린 경우
	                request.setAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
	                return "redirect:/jspbook/project/deleteUser.jsp";
	            }
	        } catch (Exception e) {
	            ctx.log("회원 삭제 중 오류: " + e.getMessage());
	            request.setAttribute("error", "회원 삭제 중 오류가 발생했습니다.");
	            return "/jspbook/project/deleteUser.jsp";
	        }
	    } else {
	        // 아이디 또는 비밀번호 미입력
	        request.setAttribute("error", "아이디와 비밀번호를 모두 입력해 주세요.");
	        return "/jspbook/project/deleteUser.jsp";
	    }
	}
}
