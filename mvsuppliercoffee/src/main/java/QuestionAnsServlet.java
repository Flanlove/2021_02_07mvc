

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import model.*;

/**
 * Servlet implementation class QuestionAnsServlet
 */
@WebServlet("/QuestionAnsServlet")
public class QuestionAnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAnsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final StandardServiceRegistry registry = new
				StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		
		SessionFactory sessionFactory = new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();
		
		
		Session session = sessionFactory.openSession();// 從對談工廠獲取一個session
		
		
		Transaction t = session.beginTransaction();
		Question question1 = new Question();
		question1.setQname("What is Java?");
		question1.setQid(1);
		Answer ans1 = new Answer();
		ans1.setAnswername("java is a programming language");
		ans1.setPostedby("Ravi Su");
		ans1.setQuestion(question1);
		Answer ans2 = new Answer();
		ans2.setAnswername("java is a platform");
		ans2.setPostedby("Sudhir Wong");
		ans2.setQuestion(question1);
		Question question2 = new Question();
		question2.setQid(2);
		question2.setQname("What is Servlet?");
		Answer ans3 = new Answer();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedby("Jai Li");
		ans3.setQuestion(question2);;;
		Answer ans4 = new Answer();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedby("Arun");
		ans4.setQuestion(question2);;;
		Set<Answer> list1 = new HashSet<Answer>();
		list1.add(ans1);
		list1.add(ans2);
		Set<Answer> list2 = new HashSet<Answer>();
		list2.add(ans3);
		list2.add(ans4);
		question1.setAnswers(list1);
		question2.setAnswers(list2);
		session.save(question1);
		session.save(question2);
		t.commit();
		session.close();
		System.out.println("success");
		response.getWriter().append("Served at:").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
