package com.rearobot.toy;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.rearobot.builder.RobotPlayActionJsonBuilder;
import com.rearobot.robot.dto.Robot;
import com.rearobot.robot.dto.enuns.Action;
import com.rearobot.robot.dto.enuns.Direction;
import com.rearobot.robot.json.RobotActionJson;
import com.rearobot.robot.json.RobotConverter;
import com.rearobot.robot.json.RobotPlayActionJson;
import com.rearobot.robot.service.RobotService;
import com.rearobot.toy.json.ToyResponse;

/**
 * @author iago
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = MockServletContext.class)
public class ToyControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private MockMvc mockMvc;

    @InjectMocks
    private ToyController controller;

    @Mock
    private RobotService service;

    @Mock
    private RobotConverter converter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).build();
        RobotActionJson result = getResult();
        when(converter.convert(any(Robot.class), any(Boolean.class))).thenReturn(new ToyResponse<RobotActionJson>(
                result, null));
    }

    @Test
    public void performPlayEndPoint() throws Exception {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withActions(Lists.newArrayList(Action.LEFT)).build();

        // WHEN
        ResultActions resultActions = mockMvc.perform(
                post(new URI("/toys/robots"))
                        .content(OBJECT_MAPPER.writeValueAsString(playAction))
                        .contentType(MediaType.APPLICATION_JSON));
        // THEN
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("result.action").value(Action.LEFT.toString()))
                .andExpect(jsonPath("result.positionX").value(playAction.getPositionX()))
                .andExpect(jsonPath("result.positionY").value(playAction.getPositionY()))
                .andExpect(jsonPath("result.direction").value(Direction.WEST.toString()));
    }

    @Test
    public void performPlayWithRobotPositionsEndPoint() throws Exception {
        Integer positionX = 0;
        Integer positionY = 0;
        Direction direction = Direction.NORTH;

        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder()
                .withDirection(null)
                .withActions(Lists.newArrayList(Action.LEFT)).build();
        // WHEN
        ResultActions resultActions = mockMvc.perform(
                post("/toys/robots/{positionX},{positionY},{direction}/play",
                        positionX, positionY, direction)
                                .content(OBJECT_MAPPER.writeValueAsString(playAction))
                                .contentType(MediaType.APPLICATION_JSON));
        // THEN
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("result.action").value(Action.LEFT.toString()))
                .andExpect(jsonPath("result.positionX").value(positionX))
                .andExpect(jsonPath("result.positionY").value(positionY))
                .andExpect(jsonPath("result.direction").value(Direction.WEST.toString()));
    }

    private RobotActionJson getResult() {
        return new RobotActionJson(Action.LEFT, 0, 0, Direction.WEST);
    }

    @Test
    public void verifyPlayWithRobot() {
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder().build();
        Boolean withHistory = true;

        controller.playWithRobot(playAction, withHistory);

        InOrder inOrder = inOrder(service, converter);
        inOrder.verify(service).play(playAction);
        inOrder.verify(converter).convert(any(Robot.class), eq(withHistory));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void verifyPlayWithRobotWithPositions() {
        Boolean withHistory = true;
        Integer positionX = 3;
        Integer positionY = 1;
        Direction direction = Direction.NORTH;
        RobotPlayActionJson playAction = new RobotPlayActionJsonBuilder().build();

        controller.playWithRobotWithPositions(positionX, positionY, direction, playAction, withHistory);

        InOrder inOrder = inOrder(service, converter);
        inOrder.verify(service).play(positionX, positionY, direction, playAction.getActions());
        inOrder.verify(converter).convert(any(Robot.class), eq(withHistory));
        inOrder.verifyNoMoreInteractions();

    }

}
