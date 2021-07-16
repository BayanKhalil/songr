package com.example.songr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SongrApplicationTests {

	@DisplayName("constructor method test for Albums")
	@Test
	void test1() {
		Album firstOne=new Album("SAPIENTIAL","Sami Yusuf",3,12,"https://pbs.twimg.com/media/Ebmm-XdX0AETq3f.jpg");
		String output = firstOne.toString();
		String expected = "Album{title='SAPIENTIAL', artist='Sami Yusuf', songCount=3.0, length=12.0, imageUrl='https://pbs.twimg.com/media/Ebmm-XdX0AETq3f.jpg'}";
		assertEquals(expected,output);
	}


	@DisplayName(" getters and setters test for Albums")
	@Test
	void test2() {
		Album thirdOne =new Album("ISMAANE","Hamza Namira",7,89,"https://i1.sndcdn.com/artworks-000099840186-qjwlp2-t500x500.jpg");
		thirdOne.setTitle("msafer");
		String output = thirdOne.getTitle();
		String expected = "msafer";
		assertEquals(expected,output);
	}



}
