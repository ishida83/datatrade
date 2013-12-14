import org.junit.Test;

interface Playable {
	void play();
}

interface Basketball {
	void play(); 
}

interface Rollable extends Playable, Basketball {
	Ball ball = new Ball("PingPang");
}

class Ball implements Rollable {
	
	private String name;

	public String getName() {
		return name;
	}

	public Ball(String name) {
		this.name = name;
	}
    
	
	public void play() {
		System.out.println(ball.getName());
	}
	
	public static void main(String[] args) {
		Ball.ball.play();
	}
}