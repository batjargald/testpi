package mn.has.testpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LedController {
	
	public static GpioPinDigitalOutput pin;
	
	@RequestMapping("/")
	public String index(){
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
		pin.blink(5000);
		
		return "blinked";
	}
	
	@RequestMapping("/pulse")
	public String pulse(){
		if(pin==null){
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		pin.pulse(5000);
		
		return "pulse";
	}
	

}
