package com.jcomm.junit5examples.templates;

import com.jcomm.datastructures.JSortedArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(JSortedParameterResolver.class)
public class JSortedArrayTest {

    private final JSortedArray array = new JSortedArray(25);


    @BeforeEach
    void init(List<Integer> values) {
        System.out.println("Settign up "+values);

        values.stream().peek(System.out::println).forEach(array::insert);
    }

    @Test
    void insertValuesAreSorted() {

        array.printArray();
    }


//    @Nested
//    @DisplayName("Insertion operations")
//    class InsertOperations {
//
//
//    }

}
