package com.example.demo.contoller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class MyController {
	@GetMapping(value = "/downloadFile")
	@CrossOrigin(origins = "*")

	public ResponseEntity<Object> downloadFile() throws IOException {

		String fileName = "src/main/resources/LICENSE.txt";
		File file = new File(fileName);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; fileName=\"%s\"", file.getName()));
		headers.add("cache-control","no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("maxContentLength", "1");
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		
	}

}
