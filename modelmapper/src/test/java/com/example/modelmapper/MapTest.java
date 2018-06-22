package com.example.modelmapper;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class MapTest {

  @Test
  public void test() {
    ModelMapper mapper = new ModelMapper();

    mapper.createTypeMap(Foo.class, Foo.class).addMappings(new PropertyMap<Foo, Foo>() {
      @Override
      protected void configure() {
        skip().setId(null);
      }
    });
    mapper.createTypeMap(Bar.class, Bar.class).addMappings(new PropertyMap<Bar, Bar>() {
      @Override
      protected void configure() {
        skip().setId(null);
      }
    });

    Foo foo1 = Foo.builder()
        .id("id-foo")
        .bars(ImmutableList.of(Bar.builder().id("id-bar").name("bar").build()))
        .build();
    Foo foo2 = Foo.builder()
        .id("id-boo")
        .bars(ImmutableList.of(Bar.builder().id("id-zar").name("car").build()))
        .build();

    mapper.map(foo1, foo2);

    assertEquals("id-foo", foo1.getId());
    assertEquals("id-boo", foo2.getId());

    assertEquals("bar", foo2.getBars().get(0).getName());
    assertEquals("id-zar", foo2.getBars().get(0).getId()); // java.lang.AssertionError: expected:<id-zar> but was:<null>
  }

}
