package mn.has.testpi.controller;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	
//	public static GpioPinDigitalOutput pin;
	final GpioController gpio = GpioFactory.getInstance();
	 
	//zuun urd
	final GpioPinDigitalOutput trasistorLA1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "Transistor LA1", PinState.LOW);
	final GpioPinDigitalOutput trasistorLA2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Transistor LA2", PinState.LOW);
	//zuun hoid
	final GpioPinDigitalOutput trasistorLB1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "Transistor LB1", PinState.LOW);
	final GpioPinDigitalOutput trasistorLB2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Transistor LB2", PinState.LOW);
	
	//baruun urd
	final GpioPinDigitalOutput trasistorRA1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Transistor RA1", PinState.LOW);
	final GpioPinDigitalOutput trasistorRA2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Transistor RA2", PinState.LOW);
	//baruun hoid
	final GpioPinDigitalOutput trasistorRB1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Transistor RB1", PinState.LOW);
	final GpioPinDigitalOutput trasistorRB2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Transistor RB2", PinState.LOW);
	
	@RequestMapping("/")
	public String index(@RequestParam String param1, @RequestParam String mode){
		if(mode.equals("move")){
			move(param1);
		}else{
			try {
				int degree = Integer.valueOf(param1);
				System.out.println(degree);
				ProcessBuilder pb = new ProcessBuilder("python","/home/pi/jagaa/servoController.py",""+degree);
				pb.start();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return "index";
	}
	
	private void move(String mode){
		switch (mode) {
		case "w":
			//zuun uragshaa
			trasistorLA1.high();
			trasistorLA2.low();
			trasistorLB1.high();
			trasistorLB2.low();
			
			//baruun uragshaa
			trasistorRA1.high();
			trasistorRA2.low();
			trasistorRB1.high();
			trasistorRB2.low();
			System.out.println("FORWARD ...");
			break;
		case "s":
			//zuun hoishoo
			trasistorLA1.low();
			trasistorLA2.high();
			trasistorLB1.low();
			trasistorLB2.high();
			
			//baruun hoishoo
			trasistorRA1.low();
			trasistorRA2.high();
			trasistorRB1.low();
			trasistorRB2.high();
			System.out.println("BACKWARD ...");
			break;
		case "d":
			//zuun uragshaa
			trasistorLA1.high();
			trasistorLA2.low();
			trasistorLB1.high();
			trasistorLB2.low();
			
			//baruun hoishoo
			trasistorRA1.low();
			trasistorRA2.high();
			trasistorRB1.low();
			trasistorRB2.high();
			System.out.println("Turn RIGHT");
			break;
		case "a":
			//zuun hoishoo
			trasistorLA1.low();
			trasistorLA2.high();
			trasistorLB1.low();
			trasistorLB2.high();
			
			//baruun uragshaa
			trasistorRA1.high();
			trasistorRA2.low();
			trasistorRB1.high();
			trasistorRB2.low();
			System.out.println("Turn LEFT");
			break;
		case "q":
			//zuun zogsoh
			trasistorLA1.low();
			trasistorLA2.low();
			trasistorLB1.low();
			trasistorLB2.low();
			
			//baruun zogsoh
			trasistorRA1.low();
			trasistorRA2.low();
			trasistorRB1.low();
			trasistorRB2.low();
			System.out.println("STOP ....");
			break;

		default:
			System.out.println("wrong command");
			break;
		}
	}
	
//	@RequestMapping("/on")
//	public String on(){
//		if(pin==null){
//			GpioController gpio = GpioFactory.getInstance();
//			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
//		}
//		pin.setState(PinState.HIGH);
//		return "Assan";
//	}
//	
//	@RequestMapping("/off")
//	public String off(){
//		if(pin==null){
//			GpioController gpio = GpioFactory.getInstance();
//			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
//		}
//		pin.setState(PinState.LOW);
//		
//		return "Untarsan";
//	}
//	
//	@RequestMapping("/blink")
//	public String blink(){
//		if(pin==null){
//			
//			GpioController gpio = GpioFactory.getInstance();
//			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
//		}
//		pin.blink(500, 5000);
//		
//		return "blinked";
//	}
//	
//	@RequestMapping("/right")
//	public String right() throws InterruptedException, IOException{
//		if(pin==null){
//			GpioController gpio = GpioFactory.getInstance();
//			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
//		}
//		
//		System.in.read();
//		
//		for (int i=0; i<100; i++)
//		{
//			pin.high();
//			java.lang.Thread.sleep(0, 1000);
//			pin.low();
//			java.lang.Thread.sleep(0, 19000);
//		}
//		
//		for (int i=0; i<100; i++)
//		{
//			pin.high();
//			java.lang.Thread.sleep(0, 2000);
//			pin.low();
//			java.lang.Thread.sleep(0, 18000);
//		}
//		
//		return "";
//	}
//	
//	@RequestMapping("/left")
//	public String left(){
//		GpioController gpio = GpioFactory.getInstance();
//		GpioPinPwmOutput pwmPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);
//		
//		pwmPin.setPwm(2);
//		
//		for (int i=0; i<100; i++)
//		{
//			pin.pulse(1);
//			try {
//				java.lang.Thread.sleep(0, 20000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return "";
//	}
	

}
