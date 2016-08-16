package mn.has.testpi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class ServoController {
	
	@RequestMapping("/left2")
	public String left2(){
		if(LedController.pin==null){
			GpioController gpio = GpioFactory.getInstance();
			LedController.pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		
		LedController.pin.blink(1, 100);
		return "";
	}
	
	@RequestMapping("/right2")
	public String right2(){
		if(LedController.pin==null){
			GpioController gpio = GpioFactory.getInstance();
			LedController.pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "", PinState.LOW);
		}
		
		LedController.pin.blink(2, 100);
		return "";
	}

}
