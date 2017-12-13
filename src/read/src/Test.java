package read.src;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXMLApplicationContext("E:\\workspace\\practice\\design\\src\\read\\src\\applicationContext.xml");
		StudentService studentService = (StudentService) context.getBean("StudentService");
		studentService.getStudent().selfIntroDuction();
	}

}
  