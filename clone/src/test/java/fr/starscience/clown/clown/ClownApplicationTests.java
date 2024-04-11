package fr.starscience.clown.clown;

import fr.starscience.clown.clown.service.ArtefactService;
import fr.starscience.clown.clown.service.CloneService;
import fr.starscience.clown.clown.service.HashService;
import fr.starscience.clown.clown.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
class ClownApplicationTests {

	@Autowired
	private ArtefactService service;

	@Autowired
	private HashService hashService;

	@Autowired
	private CloneService cloneService;

	@Autowired
	private ScoreService scoreService;

	@Test
	void contextLoads() {
	}

	@Test
	void postTest() throws IOException {
	}
}
