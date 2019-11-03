package com.example.modelmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class MapTest {

    @Test
    public void testPassed_When_TypeMapsCreatedInOrderFromParentToChild() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Parent.class, ParentDTO.class).addMappings(new PropertyMap<Parent, ParentDTO>() {
            @Override
            protected void configure() {
                skip().setName(null);
            }
        });

        mapper.createTypeMap(Child.class, ChildDTO.class).addMappings(new PropertyMap<Child, ChildDTO>() {
            @Override
            protected void configure() {
                skip().setAge(0);
            }
        });

        Child child = Child.builder()
                .id("id-child")
                .parent(Parent.builder().id("id-parent").build())
                .build();


        ChildDTO childDTO = mapper.map(child, ChildDTO.class);
        mapper.validate();

        assertEquals("id-child", childDTO.getId());
    }

    @Test
    public void testFailed_When_TypeMapsCreatedInOrderFromChildToParent() {
        ModelMapper mapper = new ModelMapper();

        //First create mapping for Child element
        mapper.createTypeMap(Child.class, ChildDTO.class).addMappings(new PropertyMap<Child, ChildDTO>() {
            @Override
            protected void configure() {
                skip().setAge(0);
            }
        });

        //Then create mapping for parent element
        mapper.createTypeMap(Parent.class, ParentDTO.class).addMappings(new PropertyMap<Parent, ParentDTO>() {
            @Override
            protected void configure() {
                skip().setName(null);
            }
        });

        Child child = Child.builder()
                .id("id-child")
                .parent(Parent.builder().id("id-parent").build())
                .build();


        ChildDTO childDTO = mapper.map(child, ChildDTO.class);
        mapper.validate();

        assertEquals("id-child", childDTO.getId());
    }
}
