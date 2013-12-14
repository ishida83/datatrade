
import org.junit.Test;

public class TestTest {

	@Test
	public void testHello() {
		Hello hello = new Hello();
		hello.hello("test");

	}

}

interface A {
	int x = 0;
}

class B {
	int x = 1;
}

class C extends B implements A {
	public void pX() {
//		System.out.println(x);
	}

	public static void main(String[] args) {
		new C().pX();
	}
}