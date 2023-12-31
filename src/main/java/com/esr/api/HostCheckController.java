package com.esr.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@lombok.Generated

public class HostCheckController {

	@GetMapping("/hostcheck")
	public String checkHost() throws UnknownHostException {

		String x = InetAddress.getLocalHost().getHostAddress() + "- " + InetAddress.getLocalHost().getHostName();
		System.out.println(x);
		return x;
	}

}
