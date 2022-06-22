package com.laboratory;

import com.laboratory.util.equipmentEnum.EquipmentField;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LaboratoryApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(EquipmentField.ID.getColumn());
    }

    @Test
    void testUUID(){
        System.out.println(UUID.randomUUID().toString());
    }
}
