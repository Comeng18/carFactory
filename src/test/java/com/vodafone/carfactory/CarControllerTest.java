package com.vodafone.carfactory;

import com.vodafone.carfactory.controller.CarController;
import com.vodafone.carfactory.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest({CarController.class, CarService.class})
@AutoConfigureMockMvc
public class CarControllerTest {

    private final MockMvc mvc;

    @Autowired
    public CarControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void getTypeTestWithoutType() throws Exception {
        mvc.perform(get("/car/type")).andExpect(status().isBadRequest());
    }

    @Test
    void getTypeTestWithType() throws Exception {

        mvc.perform(get("/car/type").param("carType", "cabrio"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cabrio Car has produced."));

        mvc.perform(get("/car/type").param("carType", "sedan"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sedan Car has produced."));

        mvc.perform(get("/car/type").param("carType", "hatchback"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hatchback Car has produced."));
    }

    @Test
    void getTypeTestWithWrongType() throws Exception {

        mvc.perform(get("/car/type").param("carType", "suv"))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().string("Car type is invalid. Car types must be cabrio, sedan or hatchback")
                );
    }
}
