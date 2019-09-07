package ru.project.restaurantvoting.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import ru.project.restaurantvoting.to.responseTo.VoteResponseTo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.project.restaurantvoting.TestUtil.readFromJsonMvcResult;
import static ru.project.restaurantvoting.TestUtil.userHttpBasic;
import static ru.project.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        VoteResponseTo returned = readFromJsonMvcResult(mvcResult, VoteResponseTo.class);

        assertEquals(LocalDate.now(), returned.getDateVote());
        assertEquals(START_SEQ, returned.getUserId());
        assertEquals(START_SEQ + 2, returned.getRestaurantId());
    }
}