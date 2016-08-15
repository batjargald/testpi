package mn.has.testpi.controller;

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
	public String greeting(){
		return "Hello world";
	}
	
	@RequestMapping("/li")
	public String light(){
		if(pin==null){
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.HIGH);
		}
		pin.toggle();
		return "OK";
	}
	

}
