package mn.has.testpi.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	
	public static GpioPinDigitalOutput pin;
	
	@RequestMapping("/")
	public String index(String param1){
		try {
			int degree = Integer.valueOf(param1);
			System.out.println(degree);
			ProcessBuilder pb = new ProcessBuilder("python","/home/pi/jagaa/servoController.py",""+degree);
			pb.start();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/on")
	public String on(){
		if(pin==null){
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		pin.setState(PinState.HIGH);
		return "Assan";
	}
	
	@RequestMapping("/off")
	public String off(){
		if(pin==null){
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		pin.setState(PinState.LOW);
		
		return "Untarsan";
	}
	
	@RequestMapping("/blink")
	public String blink(){
		if(pin==null){
			
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		pin.blink(500, 5000);
		
		return "blinked";
	}
	
	@RequestMapping("/right")
	public String right() throws InterruptedException{
		if(pin==null){
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		
		for (int i=0; i<100; i++)
		{
			pin.high();
			java.lang.Thread.sleep(0, 1000);
			pin.low();
			java.lang.Thread.sleep(0, 19000);
		}
		
		for (int i=0; i<100; i++)
		{
			pin.high();
			java.lang.Thread.sleep(0, 2000);
			pin.low();
			java.lang.Thread.sleep(0, 18000);
		}
		
		return "";
	}
	
	@RequestMapping("/left")
	public String left(){
		GpioController gpio = GpioFactory.getInstance();
		GpioPinPwmOutput pwmPin = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);
		
		pwmPin.setPwm(2);
		
		for (int i=0; i<100; i++)
		{
			pin.pulse(1);
			try {
				java.lang.Thread.sleep(0, 20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return "";
	}
	

}
