package com.example.usermanagementservice.converter;

import com.example.usermanagementservice.dto.DetailsDto;
import org.dozer.CustomConverter;

import java.util.*;

public class DetailsConverter implements CustomConverter {

    @Override
    public Object convert(Object o, Object o1, Class<?> aClass, Class<?> aClass1) {
        if (o1 != null && o1 instanceof Map) {
            return this.convertToDetailsDto((HashMap<String, List<String>>) o1);
        } else if (o1 != null && o1 instanceof DetailsDto) {
            return this.convertToMap((DetailsDto) o1);
        }
        return null;
    }

    public Map convertToMap(DetailsDto detailsDto) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("phone", Arrays.asList(detailsDto.getPhone()));
        map.put("country", Arrays.asList(detailsDto.getCountry()));
        map.put("city", Arrays.asList(detailsDto.getCity()));
        map.put("address", Arrays.asList(detailsDto.getAddress()));
        map.put("note", Arrays.asList(detailsDto.getNote()));
        return map;
    }

    public DetailsDto convertToDetailsDto(HashMap<String, List<String>> stringListMap) {
        return DetailsDto.builder()
                .phone(extractFromList(stringListMap.get("phone")))
                .country(extractFromList(stringListMap.get("country")))
                .city(extractFromList(stringListMap.get("city")))
                .address(extractFromList(stringListMap.get("address")))
                .note(extractFromList(stringListMap.get("note"))).build();
    }

    private String extractFromList(List<String> values) {
        return Optional.ofNullable(values)
                .map(list -> list.get(0))
                .orElse(null);
    }

}
