package kg.example.muratkanovmuratbeklalafo.service;

import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemDto;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemImageDto;
import kg.example.muratkanovmuratbeklalafo.model.view.FeedItemViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedItemMapper {

    @Mapping(target = "createdAt",
             expression = "java(kg.example.muratkanovmuratbeklalafo.utils.DateUtils.fromUnixSeconds(dto.getCreatedAt()))")
    @Mapping(target = "imageUrl", source = "images", qualifiedByName = "firstImageUrl")
    FeedItemViewModel toViewModel(FeedItemDto dto);

    @Named("firstImageUrl")
    default String firstImageUrl(List<FeedItemImageDto> images) {
        if (images != null && !images.isEmpty()) {
            return images.getFirst().getImageUrl();
        }
        return null;
    }
}
