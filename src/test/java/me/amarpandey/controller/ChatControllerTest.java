package me.amarpandey.controller;

import static org.junit.Assert.assertEquals;

import me.amarpandey.exceptions.EmptyMessageException;
import me.amarpandey.model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChatControllerTest {

    private ChatController sut;

    @After
    public void tearDown() {
        sut = null;
    }


    @Before
    public void setUp() {
        sut = new ChatController();
    }

    @Test(expected = EmptyMessageException.class)
    public void testGetMessage_Catch_Exception() throws EmptyMessageException {
        // given
        UserResponse response = new UserResponse();
        response.setContent("");

        // when
        sut.getMessage(response);
    }

    @Test
    public void testGetMessage_Success() throws EmptyMessageException {
        // given
        UserResponse response = new UserResponse();
        response.setContent("ANY");

        // when
        UserResponse actual = sut.getMessage(response);

        // then
        assertEquals("ANY", actual.getContent());
    }
}
