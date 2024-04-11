package fr.starscience.clown.clown;

import fr.starscience.clown.clown.analyzer.CloneDetection;
import fr.starscience.clown.clown.repository.ArtefactRepository;
import fr.starscience.clown.clown.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ClownApplication {

	@Autowired
	private ArtefactService service;

	public static void main(String[] args) {
		SpringApplication.run(ClownApplication.class, args);
	}

	@Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
	public AsyncTaskExecutor asyncTaskExecutor() {
		return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
	}

	@Bean
	public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
		return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
	}

	@Bean
	public HttpMessageConverter<ByteArrayResource> octetStreamMessageConverter() {
		return new AbstractHttpMessageConverter<ByteArrayResource>(MediaType.APPLICATION_OCTET_STREAM) {

			@Override
			protected boolean supports(Class<?> clazz) {
				return ByteArrayResource.class.isAssignableFrom(clazz);
			}

			@Override
			protected ByteArrayResource readInternal(Class<? extends ByteArrayResource> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
				try (InputStream is = inputMessage.getBody()) {
					var os = new ByteArrayOutputStream();
					int b;
					while ((b = is.read()) != -1) {
						os.write(b);
					}
					return new ByteArrayResource(os.toByteArray());
				}
			}

			@Override
			protected void writeInternal(ByteArrayResource byteArrayResource, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
				try (OutputStream os = outputMessage.getBody()) {
					os.write(byteArrayResource.getByteArray());
				}
			}

		};
	}

	/*@Bean
	public CommandLineRunner test(ArtefactRepository repository){

		return (args) -> {

		};

	}*/
}
