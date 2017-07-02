package com.erwindev.apptracker

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 6/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class ApplicationTest {

    @Test
    public void contextLoads() throws Exception {
    }

}